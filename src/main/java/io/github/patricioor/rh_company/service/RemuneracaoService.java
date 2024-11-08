package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.domain.Funcionario;
import io.github.patricioor.rh_company.domain.Remuneracao;
import io.github.patricioor.rh_company.exception.ElementAlreadyExistsException;
import io.github.patricioor.rh_company.exception.ElementNotFoundException;
import io.github.patricioor.rh_company.repository.IRemuneracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RemuneracaoService {
    @Autowired
    private IRemuneracaoRepository repository;

    public List<Remuneracao> retornarRemuneracaoPeloFuncionarioId(UUID id){
        return repository.findByFuncionarioId(id);
    }


    public Remuneracao buscarPorId (UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Remuneracao"));
    }

    public List<Remuneracao> listarTodos(){
        return repository.findAll();
    }

    public List<Remuneracao> retornarRemuneracaoPeloTipo(String tipo){
        return repository.findByTipo(tipo);
    }

    public void salvarRemuneracao(Remuneracao remuneracao){
        if(repository.existsById(remuneracao.getId())) {
            repository.save(remuneracao);
        } else {
            throw new ElementAlreadyExistsException("Remuneração");
        }
    }

    public void alterarRemuneracaoPeloId(UUID id,Remuneracao remuneracaoUpdated){
        var remuneracao = buscarPorId(id);
        remuneracao.setTipo(remuneracaoUpdated.getTipo());
        remuneracao.setData(remuneracaoUpdated.getData());
        remuneracao.setDescricao(remuneracaoUpdated.getDescricao());
        remuneracao.setFuncionario(remuneracaoUpdated.getFuncionario());
        repository.save(remuneracao);
    }

    public void excluirRemuneracao(UUID id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        } else {
            throw new ElementNotFoundException("Remuneração");
        }
    }
}
