package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.domain.Funcionario;
import io.github.patricioor.rh_company.exception.ElementAlreadyExistsException;
import io.github.patricioor.rh_company.exception.ElementNotFoundException;
import io.github.patricioor.rh_company.repository.IFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FuncionarioService{
    @Autowired
    private IFuncionarioRepository repository;

    public Optional<Funcionario> buscarPorId (UUID id){
        return Optional.ofNullable(repository.findById(id).orElseThrow(() -> new ElementNotFoundException("Funcionário")));
    }

    public List<Funcionario> listarTodos(){
        return repository.findAll();
    }

    public List<Funcionario> retornarFuncionariosPeloCargo(String cargo){
        return repository.GetFuncionariosByCargo(cargo);
    }

    public List<Funcionario> retornarFuncionariosPeloSetorId(UUID id){
        return repository.GetFuncionariosBySetorId(id);
    }

    public List<Funcionario> retornarFuncionariosPeloStatus(Boolean status){
        return repository.GetFuncionariosByStatus(status);
    }

    public void salvarFuncionario(Funcionario funcionario){
        if(repository.existsById(funcionario.getId())) {
            repository.save(funcionario);
        } else {
            throw new ElementAlreadyExistsException("Funcionário");
        }
    }

    public void alterarFuncionario(UUID id, Funcionario funcionario){
        if(repository.existsById(id)){
            repository.UpdateFuncionario(id, funcionario);
        } else {
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public void excluirFuncionario(UUID id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        } else {
            throw new ElementNotFoundException("Funcionário");
        }
    }
}
