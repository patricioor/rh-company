package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.domain.Setor;
import io.github.patricioor.rh_company.exception.ElementNotFoundException;
import io.github.patricioor.rh_company.repository.ISetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SetorService {
    @Autowired
    private ISetorRepository repository;

    List<Setor>  listaTodosOsSetores(){
        return repository.findAll();
    }

    Optional<Setor> retornarSetorPeloIdOptional(UUID id){
        return Optional.ofNullable(repository.findById(id).orElseThrow(() -> new ElementNotFoundException("Setor")));
    }

    Setor retornarSetorPeloNome(String nome){
        return repository.GetSetorByName(nome);
    }

    void AlterarSetor(UUID id, Setor setor){
        repository.UpdateSetor(id, setor);
    }

    void SalvarSetor(Setor setor){
        repository.save(setor);
    }

    void  ApagarSetorPeloId(UUID id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Setor não encontrada");
        }
    }

}
