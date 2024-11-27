package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.domain.Setor;
import io.github.patricioor.rh_company.domain.dto.SetorDTO;
import io.github.patricioor.rh_company.exception.ElementNotFoundException;
import io.github.patricioor.rh_company.repository.ISetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SetorService {

    private final ISetorRepository repository;

    @Autowired
    public SetorService(ISetorRepository repository) {
        this.repository = repository;
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

    Setor retornarSetorPeloNome(String nome){
        try {
            return repository.findSetorByName(nome);
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Setor");
        }
    }

    public Setor criarSetor(String nome){
        if(retornarSetorPeloNome(nome) == null) {
            var setor = new Setor();
            setor.setNome(nome);
            repository.save(setor);
            return setor;
        }
        return null;
    }

    public Setor AtualizarSetor(String id, SetorDTO setorDTO){
        var setor = buscarPorId(UUID.fromString(id));
        setor.setNome(setorDTO.getNome());
        repository.save(setor);
        return setor;
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
}
