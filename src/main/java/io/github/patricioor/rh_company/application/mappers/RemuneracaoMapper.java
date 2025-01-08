package io.github.patricioor.rh_company.application.mappers;

import io.github.patricioor.rh_company.application.dto.Remuneracao.RemuneracaoDTO;
import io.github.patricioor.rh_company.application.dto.Remuneracao.RemuneracaoManipularDTO;
import io.github.patricioor.rh_company.domain.Remuneracao;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RemuneracaoMapper {
    public Remuneracao toRemuneracao(RemuneracaoDTO remuneracaoDTO){
        Remuneracao remuneracao = new Remuneracao();
        remuneracao.setSalarioBase(remuneracaoDTO.getSalarioBase());
        remuneracao.setDataInicio(remuneracaoDTO.getDataInicio());
        remuneracao.setDataFim(remuneracaoDTO.getDataFim());
        remuneracao.setTipoContrato(remuneracaoDTO.getTipoContrato());
        remuneracao.setCargaHorariaSemanal(remuneracaoDTO.getCargaHorariaSemanal());
        remuneracao.setAdicionalPericulosidade(remuneracaoDTO.getAdicionalPericulosidade());
        remuneracao.setAdicionalInsalubridade(remuneracaoDTO.getAdicionalInsalubridade());
        remuneracao.setBonus(remuneracaoDTO.getBonus());

        remuneracao.setFuncionarioId(UUID.fromString(remuneracaoDTO.getFuncionarioId()));

        return remuneracao;
    }

    public RemuneracaoDTO toRemuneracaoDTO(Remuneracao remuneracao){
        RemuneracaoDTO remuneracaoDto = new RemuneracaoDTO();
        remuneracaoDto.setId(remuneracao.getId());
        remuneracaoDto.setSalarioBase(remuneracao.getSalarioBase());
        remuneracaoDto.setDataInicio(remuneracao.getDataInicio());
        remuneracaoDto.setDataFim(remuneracao.getDataFim());
        remuneracaoDto.setTipoContrato(remuneracao.getTipoContrato());
        remuneracaoDto.setCargaHorariaSemanal(remuneracao.getCargaHorariaSemanal());
        remuneracaoDto.setAdicionalPericulosidade(remuneracao.getAdicionalPericulosidade());
        remuneracaoDto.setAdicionalInsalubridade(remuneracao.getAdicionalInsalubridade());
        remuneracaoDto.setBonus(remuneracao.getBonus());

        remuneracaoDto.setFuncionarioId(remuneracao.getFuncionarioId().toString());

        return remuneracaoDto;
    }

    public Remuneracao toRemuneracaoByManipular(RemuneracaoManipularDTO remuneracaoManipularDTO){
        Remuneracao remuneracao = new Remuneracao();
        remuneracao.setSalarioBase(remuneracaoManipularDTO.getSalarioBase());
        remuneracao.setDataInicio(remuneracaoManipularDTO.getDataInicio());
        remuneracao.setDataFim(remuneracaoManipularDTO.getDataFim());
        remuneracao.setTipoContrato(remuneracaoManipularDTO.getTipoContrato());
        remuneracao.setCargaHorariaSemanal(remuneracaoManipularDTO.getCargaHorariaSemanal());
        remuneracao.setAdicionalPericulosidade(remuneracaoManipularDTO.getAdicionalPericulosidade());
        remuneracao.setAdicionalInsalubridade(remuneracaoManipularDTO.getAdicionalInsalubridade());
        remuneracao.setBonus(remuneracaoManipularDTO.getBonus());
        remuneracao.setFuncionarioId(UUID.fromString(remuneracaoManipularDTO.getFuncionarioId()));

        return remuneracao;
    }
}
