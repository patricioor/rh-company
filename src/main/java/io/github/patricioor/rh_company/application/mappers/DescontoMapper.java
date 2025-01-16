package io.github.patricioor.rh_company.application.mappers;

import io.github.patricioor.rh_company.application.dto.FolhaPagamento.DescontoDTO;
import io.github.patricioor.rh_company.application.dto.FolhaPagamento.DescontoManipularDTO;
import io.github.patricioor.rh_company.domain.FolhaPagamento.Desconto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DescontoMapper {
    public Desconto toDesconto(DescontoDTO dto){
        Desconto desconto = new Desconto();
        desconto.setDescricao(dto.getDescricao());
        desconto.setValor(dto.getValor());

        return desconto;
    }
    public DescontoDTO toDescontoDTO(Desconto desconto){
        DescontoDTO dto = new DescontoDTO();
        dto.setId(desconto.getId().toString());
        dto.setDescricao(desconto.getDescricao());
        dto.setValor(desconto.getValor());
        dto.setFolhaPagamentoId(desconto.getFolhaPagamentoId());

        return dto;
    }
    public Desconto toDescontoByManipular(DescontoManipularDTO dto){
        Desconto desconto = new Desconto();
        desconto.setDescricao(dto.getDescricao());
        desconto.setValor(dto.getValor());

        return desconto;
    }

    public List<Desconto> toListDescontoByListManipular(UUID id, List<DescontoManipularDTO> list){
        List<Desconto> newList = new ArrayList<>();

        for(DescontoManipularDTO dto: list){
            newList.add(toDescontoByManipular(dto));
        }

        for(int i = 0; i < newList.size();i++){
            Desconto desc = newList.get(i);
            desc.setFolhaPagamentoId(id.toString());
            newList.set(i, desc);
        }

        return newList;
    };

    public List<DescontoDTO> toListDescontoDtoByListDesconto(List<Desconto> list){
        List<DescontoDTO> newList = new ArrayList<>();

        for(Desconto desc: list){
            newList.add(toDescontoDTO(desc));
        }

        for(int i = 0; i < list.size(); i++){
            DescontoDTO desc = toDescontoDTO(list.get(i));
            newList.set(i,desc);
        }

        return newList;
    };
}
