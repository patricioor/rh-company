package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.application.dto.FolhaPagamento.FolhaPagamentoDTO;
import io.github.patricioor.rh_company.application.dto.FolhaPagamento.FolhaPagamentoManipularDTO;
import io.github.patricioor.rh_company.application.mappers.DescontoMapper;
import io.github.patricioor.rh_company.application.mappers.FolhaPagamentoMapper;
import io.github.patricioor.rh_company.application.mappers.ProventoMapper;
import io.github.patricioor.rh_company.domain.FolhaPagamento.FolhaPagamento;
import io.github.patricioor.rh_company.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FolhaPagamentoService {
    private final IFolhaPagamentoRepository repository;
    private final IFolhaProventoRepository folhaProventoRepository;
    private final IFolhaDescontoRepository folhaDescontoRepository;
    private final FolhaPagamentoMapper mapper;
    private final ProventoMapper proventoMapper;
    private final DescontoMapper descontoMapper;

    @Autowired
    public FolhaPagamentoService(IFolhaPagamentoRepository repository, IFolhaProventoRepository folhaProventoRepository, IFolhaDescontoRepository folhaDescontoRepository, FolhaPagamentoMapper mapper, ProventoMapper proventoMapper, DescontoMapper descontoMapper, IProventoRepository proventoRepository, IDescontoRepository descontoRepository) {
        this.repository = repository;
        this.folhaProventoRepository = folhaProventoRepository;
        this.folhaDescontoRepository = folhaDescontoRepository;
        this.proventoMapper = proventoMapper;
        this.descontoMapper = descontoMapper;
        this.mapper = mapper;
    }

    // public FolhaPagamentoDTO BuscarPorId(String id){ return mapper.toFolhaPagamentoDto(repository.findById(UUID.fromString(id)).orElseThrow(() -> new ElementNotFoundException("Funcionário"))); }

    public FolhaPagamentoDTO criarFolhaPagamento(FolhaPagamentoManipularDTO folhaPagamentoManipularDTO){
        FolhaPagamento folha = mapper.toFolhaPagamentoByManipular(folhaPagamentoManipularDTO);

        folha.setDescontos(
                folhaPagamentoManipularDTO.getDescontos() != null ?
                        descontoMapper.toListDescontoByListManipular(folha.getId(), folhaPagamentoManipularDTO.getDescontos()) :
                        new ArrayList<>()
        );
        folha.setProventos(
                folhaPagamentoManipularDTO.getProventos() != null ?
                        proventoMapper.toListProventoByListManipular(folha.getId(), folhaPagamentoManipularDTO.getProventos()) :
                        new ArrayList<>()
        );

        if(!folha.getProventos().isEmpty()){
            folhaProventoRepository.saveAll(folha.getProventos());
        }

        if(!folha.getDescontos().isEmpty()) {
            folhaDescontoRepository.saveAll(folha.getDescontos());
        }

        folha.toSalarioLiquido();

        repository.save(folha);

        return mapper.toFolhaPagamentoDto(folha,
                descontoMapper.toListDescontoDtoByListDesconto(folha.getDescontos()),
                proventoMapper.toListProventoDtoByListProvento(folha.getProventos())
        );
    }
}
