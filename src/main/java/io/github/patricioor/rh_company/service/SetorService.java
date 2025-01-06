package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioSetorDTO;
import io.github.patricioor.rh_company.application.dto.Setor.SetorDTO;
import io.github.patricioor.rh_company.application.mappers.FuncionarioMapper;
import io.github.patricioor.rh_company.application.mappers.SetorMapper;
import io.github.patricioor.rh_company.domain.Funcionario;
import io.github.patricioor.rh_company.domain.Setor;
import io.github.patricioor.rh_company.domain.exception.ElementNotFoundException;
import io.github.patricioor.rh_company.repository.IFuncionarioRepository;
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
    private final IFuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;

    @Autowired
    public SetorService(ISetorRepository repository, ISetorFuncionariosRepository setorFuncionariosRepository, SetorMapper setorMapper, FuncionarioMapper funcionarioMapper, IFuncionarioRepository funcionarioRepository) {
        this.repository = repository;
        this.setorFuncionariosRepository = setorFuncionariosRepository;
        this.setorMapper = setorMapper;
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioMapper = funcionarioMapper;
    }

    public List<String> listaSetoresNome(){
        return repository.findAll()
                .stream()
                .map(Setor::getNome)
                .collect(Collectors.toList());
    }

    public SetorDTO buscarPorId (UUID id){
        Setor setor = repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Setor"));

        if (setor == null) {
            throw new ElementNotFoundException("Setor não encontrado.");
        }

        var setorDto = setorMapper.toSetorDto(setor);

        var listaFuncionarios = funcionarioRepository.findFuncionariosBySetor(setor.getId());
        List<FuncionarioSetorDTO> listaFuncionariosSetorDto = new ArrayList<>();

        for(Funcionario funcionario : listaFuncionarios){
            listaFuncionariosSetorDto.add(funcionarioMapper.toFuncionarioSetorDtoByFuncDTO(funcionarioMapper.toFuncionarioDTO(funcionario)));
        }

        setorDto.setFuncionarios(listaFuncionariosSetorDto);

        return setorDto;
    }
    public SetorDTO buscarSetorPeloNome(String nome){
        Setor setor = repository.findSetorByName(nome);

        if (setor == null) {
            throw new ElementNotFoundException("Setor não encontrado.");
        }

        var setorDto = setorMapper.toSetorDto(setor);

        var listaFuncionarios = funcionarioRepository.findFuncionariosBySetor(setor.getId());
        List<FuncionarioSetorDTO> listaFuncionariosSetorDto = new ArrayList<>();

        for(Funcionario funcionario : listaFuncionarios){
            listaFuncionariosSetorDto.add(funcionarioMapper.toFuncionarioSetorDtoByFuncDTO(funcionarioMapper.toFuncionarioDTO(funcionario)));
        }

        setorDto.setFuncionarios(listaFuncionariosSetorDto);

        return setorDto;
    }

    public SetorDTO criarSetor(String nome){
        if(repository.findSetorByName(nome) != null) {
            throw new IllegalArgumentException("Setor já existe.");
        }
        var setor = new Setor();
        setor.setNome(nome);
        repository.save(setor);
        return setorMapper.toSetorDto(setor);
    }

    public SetorDTO atualizarSetor(String nomeSetorAntigo, String nomeSetorNovo){
        var setorDto = buscarSetorPeloNome(nomeSetorAntigo);
        var listaFuncionarios = funcionarioRepository.findFuncionariosBySetor(UUID.fromString(setorDto.getId()));
        List<FuncionarioSetorDTO> listaFuncionariosSetorDto = new ArrayList<>();
        repository.updateSetorByName(setorDto.getNome(), nomeSetorNovo);

        for(Funcionario funcionario : listaFuncionarios){
            listaFuncionariosSetorDto.add(funcionarioMapper.toFuncionarioSetorDtoByFuncDTO(funcionarioMapper.toFuncionarioDTO(funcionario)));
        }

        setorDto.setFuncionarios(listaFuncionariosSetorDto);

        return setorDto;
    }

    public void apagarSetorPeloId(String id){
        var setor = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ElementNotFoundException("Setor não encontrado."));
        repository.deleteById(setor.getId());
    }

    public void inserirFuncionarioNoSetor(UUID setorId, UUID funcionarioId) {
        setorFuncionariosRepository.inserirFuncionarioNoSetor(setorId, funcionarioId);
    }

    public void deletarFuncionarioDoSetor(UUID funcionarioId) {
        setorFuncionariosRepository.deleteSetorFuncionariosById(funcionarioId);
    }
}
