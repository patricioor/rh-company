package io.github.patricioor.rh_company.application.mappers;

import io.github.patricioor.rh_company.application.dto.FolhaPagamento.ProventoDTO;
import io.github.patricioor.rh_company.application.dto.FolhaPagamento.ProventoManipularDTO;
import io.github.patricioor.rh_company.domain.FolhaPagamento.Provento;
import org.springframework.stereotype.Service;

@Service
public class ProventoMapper {
    public Provento toProvento(ProventoDTO dto){
        Provento provento = new Provento();
        provento.setDescricao(dto.getDescricao());
        provento.setValor(dto.getValor());

        return provento;
    }
    public ProventoDTO toProventoDTO(Provento provento){
        ProventoDTO dto = new ProventoDTO();
        dto.setId(provento.getId().toString());
        dto.setDescricao(provento.getDescricao());
        dto.setValor(provento.getValor());

        return dto;
    }
    public Provento toProventoByManipular(ProventoManipularDTO dto){
        Provento provento = new Provento();
        provento.setDescricao(dto.getDescricao());
        provento.setValor(dto.getValor());

        return provento;
    }
}
