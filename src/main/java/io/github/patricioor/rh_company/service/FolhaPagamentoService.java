package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.application.dto.FolhaPagamento.FolhaPagamentoDTO;
import io.github.patricioor.rh_company.application.dto.FolhaPagamento.FolhaPagamentoManipularDTO;
import io.github.patricioor.rh_company.application.mappers.DescontoMapper;
import io.github.patricioor.rh_company.application.mappers.FolhaPagamentoMapper;
import io.github.patricioor.rh_company.application.mappers.ProventoMapper;
import io.github.patricioor.rh_company.domain.FolhaPagamento.Desconto;
import io.github.patricioor.rh_company.domain.FolhaPagamento.FolhaPagamento;
import io.github.patricioor.rh_company.domain.FolhaPagamento.Provento;
import io.github.patricioor.rh_company.domain.exception.ElementNotFoundException;
import io.github.patricioor.rh_company.domain.tabelas_relacionamentos.FolhaDesconto;
import io.github.patricioor.rh_company.domain.tabelas_relacionamentos.FolhaProvento;
import io.github.patricioor.rh_company.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FolhaPagamentoService {
    private final IFolhaPagamentoRepository repository;
    private final FolhaPagamentoMapper mapper;

    private final IFolhaProventoRepository folhaProventoRepository;
    private final IProventoRepository proventoRepository;
    private final ProventoMapper proventoMapper;

    private final IFolhaDescontoRepository folhaDescontoRepository;
    private final IDescontoRepository descontoRepository;
    private final DescontoMapper descontoMapper;

    @Autowired
    public FolhaPagamentoService(IFolhaPagamentoRepository repository, IFolhaProventoRepository folhaProventoRepository, IFolhaDescontoRepository folhaDescontoRepository, FolhaPagamentoMapper mapper, ProventoMapper proventoMapper, DescontoMapper descontoMapper, IProventoRepository proventoRepository, IDescontoRepository descontoRepository, IProventoRepository proventoRepository1, IDescontoRepository descontoRepository1) {
        this.repository = repository;
        this.folhaProventoRepository = folhaProventoRepository;
        this.folhaDescontoRepository = folhaDescontoRepository;
        this.proventoMapper = proventoMapper;
        this.descontoMapper = descontoMapper;
        this.mapper = mapper;
        this.proventoRepository = proventoRepository1;
        this.descontoRepository = descontoRepository1;
    }

    public FolhaPagamentoDTO BuscarPorId(String id){
        FolhaPagamento folhaPagamento = repository.findById(UUID.fromString(id)).orElseThrow(() -> new ElementNotFoundException("Folha de pagamento"));
        List<UUID> listUuid = folhaProventoRepository.listarProventoPorFolhaPagamentoId(UUID.fromString(id));
        List<Provento> listProventos = new ArrayList<>();

        for(UUID uuid: listUuid){
            listProventos.add(proventoRepository.findById(uuid).orElseThrow(() -> new ElementNotFoundException("Provento")));
        }

        listUuid = folhaDescontoRepository.listarDescontoPorFolhaPagamentoId(UUID.fromString(id));
        List<Desconto> listDescontos = new ArrayList<>();

        for(UUID uuid: listUuid){
            listDescontos.add(descontoRepository.findById(uuid).orElseThrow(() -> new ElementNotFoundException("Desconto")));
        }

        return mapper.toFolhaPagamentoDto(folhaPagamento,
                descontoMapper.toListDescontoDtoByListDesconto(listDescontos),
                proventoMapper.toListProventoDtoByListProvento(listProventos));
    }


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
            for(Provento provento: folha.getProventos()){
                var folhaProvento = new FolhaProvento();
                folhaProvento.setId(UUID.randomUUID());
                folhaProvento.setFolhaPagamentoId(folha.getId());
                folhaProvento.setProventoId(provento.getId());
                folhaProventoRepository.save(folhaProvento);
            }
        }

        if(!folha.getDescontos().isEmpty()) {
            for(Desconto desconto: folha.getDescontos()){
                var folhaDesconto = new FolhaDesconto();
                folhaDesconto.setId(UUID.randomUUID());
                folhaDesconto.setFolhaPagamentoId(folha.getId());
                folhaDesconto.setDescontoId(desconto.getId());
                folhaDescontoRepository.save(folhaDesconto);
            }
        }

        folha.toSalarioLiquido();

        repository.save(folha);

        return mapper.toFolhaPagamentoDto(folha,
                descontoMapper.toListDescontoDtoByListDesconto(folha.getDescontos()),
                proventoMapper.toListProventoDtoByListProvento(folha.getProventos())
        );
    }
}
