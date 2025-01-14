package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.application.dto.FolhaPagamento.DescontoDTO;
import io.github.patricioor.rh_company.application.dto.FolhaPagamento.DescontoManipularDTO;
import io.github.patricioor.rh_company.application.mappers.DescontoMapper;
import io.github.patricioor.rh_company.domain.FolhaPagamento.Desconto;
import io.github.patricioor.rh_company.repository.IDescontoRepository;
import org.springframework.stereotype.Service;

@Service
public class DescontoService {
    private final IDescontoRepository repository;
    private final DescontoMapper mapper;

    public DescontoService(IDescontoRepository repository, DescontoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public DescontoDTO criarDesconto(DescontoManipularDTO dto){
        Desconto desconto = mapper.toDescontoByManipular(dto);


        return mapper.toDescontoDTO(desconto);
    }
}
