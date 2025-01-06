package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioSetorDTO;
import io.github.patricioor.rh_company.application.mappers.SetorMapper;
import io.github.patricioor.rh_company.domain.Funcionario;
import io.github.patricioor.rh_company.domain.Setor;
import io.github.patricioor.rh_company.application.dto.Setor.SetorDTO;
import io.github.patricioor.rh_company.domain.exception.ElementNotFoundException;
import io.github.patricioor.rh_company.repository.ISetorFuncionariosRepository;
import io.github.patricioor.rh_company.repository.ISetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SetorService {

    private final ISetorRepository repository;
    private final ISetorFuncionariosRepository setorFuncionariosRepository;
    private final SetorMapper setorMapper;

    private final FuncionarioService funcionarioService;

    @Autowired
    public SetorService(ISetorRepository repository, ISetorFuncionariosRepository setorFuncionariosRepository, SetorMapper setorMapper, FuncionarioService funcionarioService) {
        this.repository = repository;
        this.setorFuncionariosRepository = setorFuncionariosRepository;
        this.setorMapper = setorMapper;
        this.funcionarioService = funcionarioService;
    }

    public List<String> listaSetoresNome(){
        return repository.findAll()
                .stream()
                .map(Setor::getNome)
                .collect(Collectors.toList());
    }

    public Setor buscarPorId (UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Setor"));
    }

    public SetorDTO buscarSetorPeloNome(String nome){
        try {
            Setor setor = repository.findSetorByName(nome);
            return setorMapper.toSetorDto(setor);
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Setor");
        }
    }

    public SetorDTO criarSetor(String nome){
        if(buscarSetorPeloNome(nome) == null) {
            var setor = new Setor();
            setor.setNome(nome);
            repository.save(setor);
            return setorMapper.toSetorDto(setor);
        }
        return null;
    }

    public SetorDTO AtualizarSetor(String nomeSetorAntigo, String nomeSetorNovo){
        var setorDto = buscarSetorPeloNome(nomeSetorAntigo);
        repository.updateSetorByName(setorDto.getNome(), nomeSetorNovo);

        List<FuncionarioSetorDTO> listaFuncionario = new ArrayList<>();
        List<UUID> listaIdFuncionarios = setorFuncionariosRepository.listarFuncionariosPorSetor(UUID.fromString(setorDto.getId()));

        for(UUID uuid: listaIdFuncionarios){
            var funcionario = funcionarioService.buscarPorId(uuid.toString());
            listaFuncionario.add(funcionario);
        }

        setorDto.setFuncionarios();

        return setorDto;
    }

    public Setor ApagarSetorPeloId(String id){
        try{
            var setor = repository.findById(UUID.fromString(id));
            repository.deleteById(setor.get().getId());
            return setor.get();
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Setor");
        }
    }

    public void InserirFuncionarioNoSetor( UUID setorId,UUID funcionarioId) {
        setorFuncionariosRepository.inserirFuncionarioNoSetor(setorId, funcionarioId);
    }

    public void DeletarFuncionarioDoSetor(UUID funcionarioId) {
        setorFuncionariosRepository.deleteSetorFuncionariosById(funcionarioId);
    }
}
