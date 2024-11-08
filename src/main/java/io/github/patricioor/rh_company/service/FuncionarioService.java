package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.domain.Funcionario;
import io.github.patricioor.rh_company.exception.ElementAlreadyExistsException;
import io.github.patricioor.rh_company.exception.ElementNotFoundException;
import io.github.patricioor.rh_company.repository.IFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FuncionarioService{
    private final IFuncionarioRepository repository;

    @Autowired
    public FuncionarioService(IFuncionarioRepository repository) {
        this.repository = repository;
    }

    public Funcionario buscarPorId (UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Funcionário"));
    }

    public List<Funcionario> listarTodos(){
        return repository.findAll();
    }

    public List<Funcionario> buscarPorNome (String nome){
        return repository.findFuncionarioByNome(nome);
    };

    public List<Funcionario> retornarFuncionariosPeloCargo(String cargo){
        return repository.findFuncionariosByCargo(cargo);
    }

    public List<Funcionario> retornarFuncionariosPeloSetorId(UUID id){
        return repository.findFuncionariosBySetorId(id);
    }

    public List<Funcionario> retornarFuncionariosPeloStatus(Boolean status){
        return repository.findFuncionariosByStatus(status);
    }

    public void salvarFuncionario(Funcionario funcionario){
        if(repository.existsById(funcionario.getId())) {
            repository.save(funcionario);
        } else {
            throw new ElementAlreadyExistsException("Funcionário");
        }
    }

    public void alterarFuncionario(UUID id, Funcionario funcionarioUpdated){
        var funcionario = buscarPorId(id);
        funcionario.setNome(funcionarioUpdated.getNome());
        funcionario.setCargo(funcionarioUpdated.getCargo());
        funcionario.setSalarioBase(funcionarioUpdated.getSalarioBase());
        funcionario.setSetorId(funcionarioUpdated.getSetorId());
        funcionario.setStatus(funcionarioUpdated.getStatus());
        funcionario.setData(funcionarioUpdated.getData());
        repository.save(funcionario);
    }

    public void excluirFuncionario(UUID id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        } else {
            throw new ElementNotFoundException("Funcionário");
        }
    }
}
