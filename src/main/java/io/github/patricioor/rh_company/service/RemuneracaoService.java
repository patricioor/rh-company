package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.application.dto.Remuneracao.RemuneracaoDTO;
import io.github.patricioor.rh_company.application.dto.Remuneracao.RemuneracaoManipularDTO;
import io.github.patricioor.rh_company.application.mappers.RemuneracaoMapper;
import io.github.patricioor.rh_company.domain.Remuneracao;
import io.github.patricioor.rh_company.domain.exception.ElementNotFoundException;
import io.github.patricioor.rh_company.repository.IRemuneracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class RemuneracaoService {
    private final IRemuneracaoRepository repository;
    private final RemuneracaoMapper mapper;

    @Autowired
    public RemuneracaoService(IRemuneracaoRepository repository, RemuneracaoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public RemuneracaoDTO buscarPorId (String id){
        return mapper.toRemuneracaoDTO(repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ElementNotFoundException("Remuneracao")));
    }

    public List<Remuneracao> retornarRemuneracaoPeloTipo (String tipoContrato, LocalDate data){
        return repository.findListaRemuneracaoByTipoContrato(tipoContrato, data);
    }

    public RemuneracaoDTO criarRemuneracao (RemuneracaoManipularDTO remuneracaoManipularDto){
        var remuneracao = mapper.toRemuneracaoByManipular(remuneracaoManipularDto);
        repository.save(remuneracao);
        return mapper.toRemuneracaoDTO(remuneracao);
    }

    public void alterarRemuneracaoPeloId (RemuneracaoManipularDTO remuneracaoManipularDTO){
        var remuneracao = buscarPorId(remuneracaoManipularDTO.getFuncionarioId());

        repository.updateRemuneracao(mapper.toRemuneracao(remuneracao));
    }

    public void excluirRemuneracao (UUID id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        } else {
            throw new ElementNotFoundException("Remuneração");
        }
    }
}
