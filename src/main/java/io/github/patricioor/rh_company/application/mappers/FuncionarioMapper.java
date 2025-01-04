package io.github.patricioor.rh_company.application.mappers;

import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioIdDTO;
import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioDTO;
import io.github.patricioor.rh_company.domain.Funcionario;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioMapper {
    public Funcionario toFuncionario(FuncionarioDTO dto, String cpf){
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.getNome());
        funcionario.setCpf(cpf);
        funcionario.setCargo(dto.getCargo());
        funcionario.setGenero(dto.getGenero());
        funcionario.setDataContratacao(dto.getDataContratacao());
        funcionario.setDataNascimento(dto.getDataNascimento());
        funcionario.setSalarioBase(dto.getSalarioBase());
        funcionario.setStatus(dto.getStatus());

        return funcionario;
    }
    public FuncionarioDTO toFuncionarioDTO (Funcionario funcionario){
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setNome(funcionario.getNome());
        dto.setCargo(funcionario.getCargo());
        dto.setGenero(funcionario.getGenero());
        dto.setDataContratacao(funcionario.getDataContratacao());
        dto.setDataNascimento(funcionario.getDataNascimento());
        dto.setSalarioBase(funcionario.getSalarioBase());
        dto.setStatus(funcionario.getStatus());

        return dto;
    }
    public FuncionarioIdDTO toFuncionarioIdDTO (Funcionario funcionario){
        FuncionarioIdDTO dto = new FuncionarioIdDTO();
        dto.setId(funcionario.getId().toString());
        dto.setNome(funcionario.getNome());
        dto.setCargo(funcionario.getCargo());
        dto.setGenero(funcionario.getGenero());
        dto.setDataContratacao(funcionario.getDataContratacao());
        dto.setDataNascimento(funcionario.getDataNascimento());
        dto.setSalarioBase(funcionario.getSalarioBase());
        dto.setStatus(funcionario.getStatus());

        return dto;
    }

    public FuncionarioDTO toFuncionarioDtoByFuncIdDto(FuncionarioIdDTO funcionarioIdDTO){
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setNome(funcionarioIdDTO.getNome());
        dto.setCargo(funcionarioIdDTO.getCargo());
        dto.setGenero(funcionarioIdDTO.getGenero());
        dto.setDataContratacao(funcionarioIdDTO.getDataContratacao());
        dto.setDataNascimento(funcionarioIdDTO.getDataNascimento());
        dto.setSalarioBase(funcionarioIdDTO.getSalarioBase());
        dto.setStatus(funcionarioIdDTO.getStatus());

        return dto;
    }
}
