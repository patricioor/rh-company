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
import org.springframework.transaction.annotation.Transactional;

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

    public List<FuncionarioSetorDTO> listaFuncionariosPorSetorId(String id){
        List<FuncionarioSetorDTO> dtoList = new ArrayList<>();
        List<UUID> uuidList = setorFuncionariosRepository.listarFuncionariosPorSetorId(UUID.fromString(id));

        if(!uuidList.isEmpty())
            for(UUID uuid: uuidList){
                Funcionario funcionario = funcionarioRepository.findById(uuid).orElseThrow(() -> new ElementNotFoundException("Funcionario"));

                dtoList.add(funcionarioMapper.toFuncionarioSetorDtoByFuncDTO(funcionarioMapper.toFuncionarioDTO(funcionario)));
            }

        return dtoList;
    }

    public SetorDTO buscarPorId (UUID id){
        Setor setor = repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Setor"));

        if (setor == null) {
            throw new ElementNotFoundException("Setor não encontrado.");
        }

        var setorDto = setorMapper.toSetorDto(setor);

        var listaFuncionarios = funcionarioRepository.findListFuncionariosBySetor(setor.getId());
        List<FuncionarioSetorDTO> listaFuncionariosSetorDto = new ArrayList<>();

        for(Funcionario funcionario : listaFuncionarios){
            listaFuncionariosSetorDto.add(funcionarioMapper.toFuncionarioSetorDtoByFuncDTO(funcionarioMapper.toFuncionarioDTO(funcionario)));
        }

        setorDto.setFuncionarios(listaFuncionariosSetorDto);

        return setorDto;
    }
    public SetorDTO buscarPeloNome(String nome){
        Setor setor = repository.findByNome(nome);

        if (setor == null) {
            throw new ElementNotFoundException("Setor");
        }

        var setorDto = setorMapper.toSetorDto(setor);
        setorDto.setId(setor.getId().toString());

        var listaFuncionarios = funcionarioRepository.findListFuncionariosBySetor(setor.getId());
        List<FuncionarioSetorDTO> listaFuncionariosSetorDto = new ArrayList<>();

        for(Funcionario funcionario : listaFuncionarios){
            listaFuncionariosSetorDto.add(funcionarioMapper.toFuncionarioSetorDtoByFuncDTO(funcionarioMapper.toFuncionarioDTO(funcionario)));
        }

        setorDto.setFuncionarios(listaFuncionariosSetorDto);

        return setorDto;
    }

    public SetorDTO criarSetor(String nome){
        if(repository.findByNome(nome) != null) {
            throw new IllegalArgumentException("Setor já existe.");
        }
        var setor = new Setor();
        setor.setNome(nome);
        repository.save(setor);
        return setorMapper.toSetorDto(setor);
    }

    public SetorDTO atualizarSetor(String nomeSetorAntigo, String nomeSetorNovo){
        var setorDto = buscarPeloNome(nomeSetorAntigo);
        var listaFuncionarios = funcionarioRepository.findListFuncionariosBySetor(UUID.fromString(setorDto.getId()));
        List<FuncionarioSetorDTO> listaFuncionariosSetorDto = new ArrayList<>();
        repository.updateSetorByName(setorDto.getNome(), nomeSetorNovo);

        for(Funcionario funcionario : listaFuncionarios){
            listaFuncionariosSetorDto.add(funcionarioMapper.toFuncionarioSetorDtoByFuncDTO(funcionarioMapper.toFuncionarioDTO(funcionario)));
        }

        setorDto.setFuncionarios(listaFuncionariosSetorDto);

        return setorDto;
    }

    @Transactional
    public void apagarSetorPeloId(String id){
        var setor = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ElementNotFoundException("Setor não encontrado."));
        for(Funcionario func : setor.getFuncionarios()) {
            deletarFuncionarioDoSetor(func.getId());
        }

        repository.deleteById(setor.getId());
    }

    public void inserirFuncionarioNoSetor(UUID setorId, UUID funcionarioId) {
        Setor setor = repository.findById(setorId).orElseThrow(() -> new ElementNotFoundException("Setor"));
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId).orElseThrow(() -> new ElementNotFoundException("Funcionário"));
        setorFuncionariosRepository.deleteSetorFuncionariosByFuncionarioId(funcionarioId);
        setorFuncionariosRepository.inserirFuncionarioNoSetor(setor.getId(), funcionario.getId());
    }

    public void deletarFuncionarioDoSetor(UUID funcionarioId) {

        setorFuncionariosRepository.deleteSetorFuncionariosByFuncionarioId(funcionarioId);
    }
}
