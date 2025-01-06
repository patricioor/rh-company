package io.github.patricioor.rh_company.application.mappers;

import io.github.patricioor.rh_company.application.dto.Setor.SetorDTO;
import io.github.patricioor.rh_company.domain.Setor;
import org.springframework.stereotype.Service;

@Service
public class SetorMapper {
    public Setor toSetor(SetorDTO setorDTO){
        var setor = new Setor();
        setor.setNome(setorDTO.getNome());

        return setor;
    }

    public SetorDTO toSetorDto(Setor setor){
        var setorDto = new SetorDTO();
        setorDto.setId(setor.getId().toString());
        setorDto.setNome(setor.getNome());

        return setorDto;
    }
}
