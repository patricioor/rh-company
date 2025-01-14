package io.github.patricioor.rh_company.application.mappers;

import io.github.patricioor.rh_company.application.dto.FolhaPagamento.DescontoDTO;
import io.github.patricioor.rh_company.application.dto.FolhaPagamento.DescontoManipularDTO;
import io.github.patricioor.rh_company.domain.FolhaPagamento.Desconto;
import org.springframework.stereotype.Service;

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

        return dto;
    }
    public Desconto toDescontoByManipular(DescontoManipularDTO dto){
        Desconto desconto = new Desconto();
        desconto.setDescricao(dto.getDescricao());
        desconto.setValor(dto.getValor());

        return desconto;
    }
}
