package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.application.dto.FolhaPagamento.FolhaPagamentoDTO;
import io.github.patricioor.rh_company.application.dto.FolhaPagamento.FolhaPagamentoManipularDTO;
import io.github.patricioor.rh_company.application.mappers.DescontoMapper;
import io.github.patricioor.rh_company.application.mappers.FolhaPagamentoMapper;
import io.github.patricioor.rh_company.application.mappers.FuncionarioMapper;
import io.github.patricioor.rh_company.application.mappers.ProventoMapper;
import io.github.patricioor.rh_company.domain.FolhaPagamento.Desconto;
import io.github.patricioor.rh_company.domain.FolhaPagamento.FolhaPagamento;
import io.github.patricioor.rh_company.domain.FolhaPagamento.Provento;
import io.github.patricioor.rh_company.domain.Funcionario;
import io.github.patricioor.rh_company.domain.exception.ElementNotFoundException;
import io.github.patricioor.rh_company.domain.tabelas_relacionamentos.FolhaDesconto;
import io.github.patricioor.rh_company.domain.tabelas_relacionamentos.FolhaFuncionario;
import io.github.patricioor.rh_company.domain.tabelas_relacionamentos.FolhaProvento;
import io.github.patricioor.rh_company.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FolhaPagamentoService {
    private final IFolhaPagamentoRepository repository;
    private final FolhaPagamentoMapper mapper;

    private final IFolhaFuncionarioRepository folhaFuncionarioRepository;

    private final IFolhaProventoRepository folhaProventoRepository;
    private final IProventoRepository proventoRepository;
    private final ProventoMapper proventoMapper;

    private final IFolhaDescontoRepository folhaDescontoRepository;
    private final IDescontoRepository descontoRepository;
    private final DescontoMapper descontoMapper;

    private final CalcularSalarioService calcularSalarioService;

    private final IFuncionarioRepository funcionarioRepository;

    @Autowired
    public FolhaPagamentoService(IFolhaPagamentoRepository repository, IFolhaFuncionarioRepository folhaFuncionarioRepository, IFolhaProventoRepository folhaProventoRepository, IFolhaDescontoRepository folhaDescontoRepository, FolhaPagamentoMapper mapper, ProventoMapper proventoMapper, DescontoMapper descontoMapper, IProventoRepository proventoRepository1, IDescontoRepository descontoRepository1, CalcularSalarioService calcularSalarioService, IFuncionarioRepository funcionarioRepository, FuncionarioMapper funcionarioMapper) {
        this.repository = repository;
        this.folhaFuncionarioRepository = folhaFuncionarioRepository;
        this.folhaProventoRepository = folhaProventoRepository;
        this.folhaDescontoRepository = folhaDescontoRepository;
        this.proventoMapper = proventoMapper;
        this.descontoMapper = descontoMapper;
        this.mapper = mapper;
        this.proventoRepository = proventoRepository1;
        this.descontoRepository = descontoRepository1;
        this.calcularSalarioService = calcularSalarioService;
        this.funcionarioRepository = funcionarioRepository;
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

    @Transactional
    public FolhaPagamentoDTO criarFolhaPagamento(FolhaPagamentoManipularDTO folhaPagamentoManipularDTO){
        FolhaPagamento folha = mapper.toFolhaPagamentoByManipular(folhaPagamentoManipularDTO);

        Funcionario funcionario = funcionarioRepository.findById(UUID.fromString(folhaPagamentoManipularDTO.getFuncionarioId())).orElseThrow(() -> new ElementNotFoundException("Funcionário não encontrado"));

        folha.setFuncionario(funcionario);

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
            proventoRepository.saveAll(folha.getProventos());
        }

        if(!folha.getDescontos().isEmpty()) {
            descontoRepository.saveAll(folha.getDescontos());
        }

        folha.setSalarioBruto(calcularSalarioService.calcularSalarioBruto(folha.getProventos()));
        folha.setSalarioLiquido(calcularSalarioService.calcularSalarioLiquido(folha.getDescontos(), folha.getSalarioBruto()));
        repository.save(folha);

        persistindoTabelasDeRelacionamentos(folha);

        return mapper.toFolhaPagamentoDto(folha,
                descontoMapper.toListDescontoDtoByListDesconto(folha.getDescontos()),
                proventoMapper.toListProventoDtoByListProvento(folha.getProventos())
        );
    }

    private void persistindoTabelasDeRelacionamentos(FolhaPagamento folha) {
        List<FolhaProvento> folhaProventos = folha.getProventos().stream().map(provento -> {
            var folhaProvento = new FolhaProvento();
            folhaProvento.setId(UUID.randomUUID());
            folhaProvento.setFolhaPagamentoId(folha.getId());
            folhaProvento.setProventoId(provento.getId());
            return folhaProvento;
        }).toList();

        folhaProventoRepository.saveAll(folhaProventos);

        List<FolhaDesconto> folhaDescontos = folha.getDescontos().stream().map(desconto -> {
            var folhaDesconto = new FolhaDesconto();
            folhaDesconto.setId(UUID.randomUUID());
            folhaDesconto.setFolhaPagamentoId(folha.getId());
            folhaDesconto.setDescontoId(desconto.getId());
            return folhaDesconto;
        }).toList();

        folhaDescontoRepository.saveAll(folhaDescontos);

        var folhaFunc = new FolhaFuncionario();
        folhaFunc.setId(UUID.randomUUID());
        folhaFunc.setFolhaPagamentoId(folha.getId());
        folhaFunc.setFuncionarioId(folha.getFuncionario().getId());

        folhaFuncionarioRepository.save(folhaFunc);
    }
}
