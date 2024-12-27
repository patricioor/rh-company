package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioAtualizarDTO;
import io.github.patricioor.rh_company.application.mappers.FuncionarioMapper;
import io.github.patricioor.rh_company.domain.Funcionario;
import io.github.patricioor.rh_company.domain.exception.ElementAlreadyExistsException;
import io.github.patricioor.rh_company.domain.exception.ElementNotFoundException;
import io.github.patricioor.rh_company.repository.IFuncionarioRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FuncionarioService{
    private final IFuncionarioRepository repository;
    private final SetorService setorService;
    private final FuncionarioMapper funcionarioMapper;

    @Autowired
    public FuncionarioService(IFuncionarioRepository repository, SetorService setorService, FuncionarioMapper funcionarioMapper) {
        this.repository = repository;
        this.setorService = setorService;
        this.funcionarioMapper = funcionarioMapper;
    }

    public Funcionario buscarPorId (String id){
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ElementNotFoundException("Funcionário"));
    }

    public Funcionario buscarPorCpf (String cpf){
        try{
            return repository.findFuncionarioByCpf(cpf);
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public List<String> listarTodos(){
        return repository
                .findAll()
                .stream()
                .map(Funcionario::getNome)
                .collect(Collectors.toList());
    }

    public Funcionario buscarPorNome (String nome){
        try {
            return repository.findFuncionarioByNome("%" + nome + "%");
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    };

    public List<Funcionario> retornarFuncionariosPeloCargo(String cargo){
        try {
            return repository.findFuncionariosByCargo(cargo);
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public List<Funcionario> retornarFuncionariosPeloSetorId(UUID id){
        try {
            return repository.findFuncionariosBySetor(id);
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public List<Funcionario> retornarFuncionariosPeloStatus(Boolean status){
        try {
            return repository.findFuncionariosByStatus(status);
        }   catch (ElementNotFoundException e) {
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public Funcionario criarFuncionario(FuncionarioAtualizarDTO funcionarioDTO, String cpf){
        if (repository.findFuncionarioByCpf(cpf) != null) {
            throw new ElementAlreadyExistsException("Funcionário");
        }

        Funcionario funcionario = updateFuncionario(funcionarioDTO, cpf);

        repository.save(funcionario);

        return funcionario;
    }

    public Funcionario atualizarFuncionario(String cpf, FuncionarioAtualizarDTO funcionarioUpdated){
        try {
            var funcionario = buscarPorCpf(cpf);

            funcionario = updateFuncionario(funcionarioUpdated,cpf);
            repository.(funcionario);

            return funcionario;
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public Funcionario alterarSetorFuncionar(String cpf, String nomeSetor){
        try {
            var funcionario = buscarPorCpf(cpf);
            var setor = setorService.retornarSetorPeloNome(nomeSetor);

            if(setor != null){
                setorService.InserirFuncionarioNoSetor(setor.getId(), funcionario.getId());
            }

            funcionario.setSetor(setor);
            repository.save(funcionario);

            return funcionario;
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Setor");
        }
    }

    public Funcionario excluirFuncionario(String cpf){
        var funcionario = buscarPorCpf(cpf);
        repository.delete(funcionario);
        return funcionario;
    }

    @NotNull
    private Funcionario updateFuncionario(FuncionarioAtualizarDTO funcionarioDTO, String cpf) {
        return funcionarioMapper.toFuncionario(funcionarioDTO, cpf);
    }
}