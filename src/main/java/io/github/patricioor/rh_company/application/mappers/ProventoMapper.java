package io.github.patricioor.rh_company.application.mappers;

import io.github.patricioor.rh_company.application.dto.FolhaPagamento.ProventoDTO;
import io.github.patricioor.rh_company.application.dto.FolhaPagamento.ProventoManipularDTO;
import io.github.patricioor.rh_company.domain.FolhaPagamento.Provento;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProventoMapper {
    public Provento toProvento(ProventoDTO dto){
        Provento provento = new Provento();
        provento.setId(UUID.randomUUID());
        provento.setDescricao(dto.getDescricao());
        provento.setValor(dto.getValor());

        return provento;
    }
    public ProventoDTO toProventoDTO(Provento provento){
        ProventoDTO dto = new ProventoDTO();
        dto.setId(provento.getId().toString());
        dto.setDescricao(provento.getDescricao());
        dto.setValor(provento.getValor());
        dto.setFolhaPagamentoId(provento.getFolhaPagamentoId());

        return dto;
    }
    public Provento toProventoByManipular(ProventoManipularDTO dto){
        Provento provento = new Provento();
        provento.setId(UUID.randomUUID());
        provento.setDescricao(dto.getDescricao());
        provento.setValor(dto.getValor());

        return provento;
    }

    public List<Provento> toListProventoByListManipular(UUID id, List<ProventoManipularDTO> list){
        List<Provento> newList = new ArrayList<>();

        for(ProventoManipularDTO dto: list){
            Provento prov = new Provento();
            prov.setId(UUID.randomUUID());
            prov.setValor(dto.getValor());
            prov.setDescricao(dto.getDescricao());
            prov.setFolhaPagamentoId(id.toString());
            newList.add(prov);
        }

        return newList;
    };

    public List<ProventoDTO> toListProventoDtoByListProvento(List<Provento> list){
        List<ProventoDTO> newList = new ArrayList<>();

        for(Provento prov: list){
            newList.add(toProventoDTO(prov));
        }

        for(int i = 0; i < newList.size(); i++){
            Provento prov = list.get(i);
            prov.setFolhaPagamentoId(prov.getFolhaPagamentoId());
            newList.set(i, toProventoDTO(prov));
        }

        return newList;
    };
}
