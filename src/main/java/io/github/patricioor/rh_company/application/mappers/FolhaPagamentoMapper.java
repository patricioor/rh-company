package io.github.patricioor.rh_company.application.mappers;

import io.github.patricioor.rh_company.application.dto.FolhaPagamento.DescontoDTO;
import io.github.patricioor.rh_company.application.dto.FolhaPagamento.FolhaPagamentoDTO;
import io.github.patricioor.rh_company.application.dto.FolhaPagamento.FolhaPagamentoManipularDTO;
import io.github.patricioor.rh_company.application.dto.FolhaPagamento.ProventoDTO;
import io.github.patricioor.rh_company.domain.FolhaPagamento.FolhaPagamento;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public FolhaPagamentoDTO toFolhaPagamentoDto(FolhaPagamento folha, List<DescontoDTO> listDesc, List<ProventoDTO> listProv){
        FolhaPagamentoDTO dto = new FolhaPagamentoDTO();
        dto.setId(folha.getId());
        dto.setDataEmissao(folha.getDataEmissao());
        dto.setPeriodoReferencia(folha.getPeriodoReferencia());
        dto.setSalarioBruto(folha.getSalarioBruto());
        dto.setSalarioLiquido(folha.getSalarioLiquido());
        dto.setFuncionarioId(folha.getFuncionarioId());
        dto.setDescontos(listDesc);
        dto.setProventos(listProv);

        return dto;
    }

    public FolhaPagamento toFolhaPagamentoByManipular(FolhaPagamentoManipularDTO dto){
        FolhaPagamento folha = new FolhaPagamento();
        folha.setId(UUID.randomUUID());
        folha.setDataEmissao(dto.getDataEmissao());
        folha.setPeriodoReferencia(dto.getPeriodoReferencia());
        folha.setFuncionarioId(dto.getFuncionarioId());

        return folha;
    }
}

