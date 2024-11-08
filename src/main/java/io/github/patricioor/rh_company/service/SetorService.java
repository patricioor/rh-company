package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.domain.Setor;
import io.github.patricioor.rh_company.exception.ElementNotFoundException;
import io.github.patricioor.rh_company.repository.ISetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SetorService {

    private final ISetorRepository repository;

    @Autowired
    public SetorService(ISetorRepository repository) {
        this.repository = repository;
    }

    List<Setor>  listaTodosOsSetores(){
        return repository.findAll();
    }

    public Setor buscarPorId (UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Setor"));
    }

    Setor retornarSetorPeloNome(String nome){
        return repository.findSetorByName(nome);
    }

    void AlterarSetor(UUID id, Setor setorUptade){
        var setor = buscarPorId(id);
        setor.setNome(setorUptade.getNome());
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
