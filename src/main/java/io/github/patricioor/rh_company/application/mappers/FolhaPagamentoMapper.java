package io.github.patricioor.rh_company.application.mappers;

import io.github.patricioor.rh_company.application.dto.FolhaPagamento.FolhaPagamentoDTO;
import io.github.patricioor.rh_company.application.dto.FolhaPagamento.FolhaPagamentoManipularDTO;
import io.github.patricioor.rh_company.domain.FolhaPagamento.FolhaPagamento;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FolhaPagamentoMapper {
    public FolhaPagamento toFolhaPagamento(FolhaPagamentoDTO dto){
        FolhaPagamento folha = new FolhaPagamento();
        folha.setId(UUID.randomUUID());
        folha.setDataEmissao(dto.getDataEmissao());
        folha.setPeriodoReferencia(dto.getPeriodoReferencia());
        folha.setSalarioBruto(dto.getSalarioBruto());
        folha.setSalarioLiquido(dto.getSalarioLiquido());
        folha.setFuncionarioId(dto.getFuncionarioId());

        return folha;
    }
    public FolhaPagamentoDTO toFolhaPagamentoDto( FolhaPagamento folha){
        FolhaPagamentoDTO dto = new FolhaPagamentoDTO();
        dto.setId(folha.getId());
        dto.setDataEmissao(folha.getDataEmissao());
        dto.setPeriodoReferencia(folha.getPeriodoReferencia());
        dto.setSalarioBruto(folha.getSalarioBruto());
        dto.setSalarioLiquido(folha.getSalarioLiquido());
        dto.setFuncionarioId(folha.getFuncionarioId());

        return dto;
    }

    public FolhaPagamento toFolhaPagamentoByManipular(FolhaPagamentoManipularDTO dto){
        FolhaPagamento folha = new FolhaPagamento();
        folha.setDataEmissao(dto.getDataEmissao());
        folha.setPeriodoReferencia(dto.getPeriodoReferencia());
        folha.setSalarioBruto(dto.getSalarioBruto());
        folha.setFuncionarioId(dto.getFuncionarioId());


        return folha;
    }
}

