package io.github.patricioor.rh_company.application.mappers;

import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioAtualizarDTO;
import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioDTO;
import io.github.patricioor.rh_company.domain.Funcionario;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioMapper {
    public Funcionario toFuncionario(FuncionarioAtualizarDTO dto, String cpf){
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
        dto.setCpf(funcionario.getCpf());
        dto.setCargo(funcionario.getCargo());
        dto.setGenero(funcionario.getGenero());
        dto.setDataContratacao(funcionario.getDataContratacao());
        dto.setDataNascimento(funcionario.getDataNascimento());
        dto.setSalarioBase(funcionario.getSalarioBase());
        dto.setStatus(funcionario.getStatus());

        return dto;
    }
}
