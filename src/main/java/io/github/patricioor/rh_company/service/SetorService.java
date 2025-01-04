package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.domain.Setor;
import io.github.patricioor.rh_company.application.dto.SetorDTO;
import io.github.patricioor.rh_company.domain.exception.ElementNotFoundException;
import io.github.patricioor.rh_company.repository.ISetorFuncionariosRepository;
import io.github.patricioor.rh_company.repository.ISetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SetorService {

    private final ISetorRepository repository;
    private final ISetorFuncionariosRepository setorFuncionariosRepository;

    @Autowired
    public SetorService(ISetorRepository repository, ISetorFuncionariosRepository setorFuncionariosRepository) {
        this.repository = repository;
        this.setorFuncionariosRepository = setorFuncionariosRepository;
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

    Setor buscarSetorPeloNome(String nome){
        try {
            return repository.findSetorByName(nome);
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Setor");
        }
    }

    public Setor criarSetor(String nome){
        if(buscarSetorPeloNome(nome) == null) {
            var setor = new Setor();
            setor.setNome(nome);
            repository.save(setor);
            return setor;
        }
        return null;
    }

    public Setor AtualizarSetor(SetorDTO setorDTO){
        var setor = buscarSetorPeloNome(setorDTO.getNome());
        setor.setNome(setor.getNome());
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

    public void InserirFuncionarioNoSetor( UUID setorId,UUID funcionarioId) {
        setorFuncionariosRepository.inserirFuncionarioNoSetor(setorId, funcionarioId);
    }

    public void DeletarFuncionarioDoSetor(UUID funcionarioId) {
        setorFuncionariosRepository.deleteSetorFuncionariosById(funcionarioId);
    }
}
