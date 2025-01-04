package io.github.patricioor.rh_company.application.mappers;

import io.github.patricioor.rh_company.application.dto.SetorDTO;
import io.github.patricioor.rh_company.domain.Setor;

public class SetorMapper {
    public Setor toSetor(SetorDTO setorDTO){
        var setor = new Setor();
        setor.setNome(setorDTO.getNome());

        return setor;
    }

    public SetorDTO toSetorDto(Setor setor){
        var setorDto = new SetorDTO();
        setorDto.setNome(setor.getNome());

        return setorDto;
    }
}
