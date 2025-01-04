package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioDTO;
import io.github.patricioor.rh_company.application.dto.Funcionario.FuncionarioIdDTO;
import io.github.patricioor.rh_company.application.mappers.FuncionarioMapper;
import io.github.patricioor.rh_company.domain.Funcionario;
import io.github.patricioor.rh_company.domain.exception.ElementAlreadyExistsException;
import io.github.patricioor.rh_company.domain.exception.ElementNotFoundException;
import io.github.patricioor.rh_company.repository.IFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FuncionarioService{
    private final IFuncionarioRepository repository;
    private final SetorService setorService;
    private final FuncionarioMapper funcionarioMapper;

    @Autowired
    public FuncionarioService(IFuncionarioRepository repository, SetorService setorService, FuncionarioMapper funcionarioMapper) {
        this.repository = repository;
        this.setorService = setorService;
        this.funcionarioMapper = funcionarioMapper;
    }

    public FuncionarioDTO buscarPorId (String id){
        return funcionarioMapper.toFuncionarioDTO(repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ElementNotFoundException("Funcionário")));
    }

    public FuncionarioIdDTO buscarPorCpf (String cpf){
        try{
            Funcionario funcionario = repository.findFuncionarioByCpf(cpf);
            return funcionarioMapper.toFuncionarioIdDTO(funcionario);
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public List<String> listarTodos(){
        return repository
                .findAll()
                .stream()
                .map(Funcionario::getNome)
                .collect(Collectors.toList());
    }

    public FuncionarioIdDTO buscarPorNome (String nome){
        try {
            Funcionario funcionario = repository.findFuncionarioByNome("%" + nome + "%");
            return funcionarioMapper.toFuncionarioIdDTO(funcionario);
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    };

    public List<FuncionarioDTO> retornarFuncionariosPeloCargo(String cargo){
        try {
            List<Funcionario> lista = repository.findFuncionariosByCargo(cargo);
            List<FuncionarioDTO> listaDto = new ArrayList<>();
            for(Funcionario funcionario: lista)
                listaDto.add(funcionarioMapper.toFuncionarioDTO(funcionario));
            return listaDto;
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public List<FuncionarioDTO> retornarFuncionariosPeloSetorId(UUID id){
        try {
            List<Funcionario> lista = repository.findFuncionariosBySetor(id);
            List<FuncionarioDTO> listaDto = new ArrayList<>();
            for(Funcionario funcionario: lista)
                listaDto.add(funcionarioMapper.toFuncionarioDTO(funcionario));
            return listaDto;
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public List<FuncionarioDTO> retornarFuncionariosPeloStatus(Boolean status){
        try {
            List<Funcionario> lista = repository.findFuncionariosByStatus(status);
            List<FuncionarioDTO> listaDto = new ArrayList<>();
            for(Funcionario funcionario: lista)
                listaDto.add(funcionarioMapper.toFuncionarioDTO(funcionario));
            return listaDto;
        }   catch (ElementNotFoundException e) {
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public FuncionarioDTO criarFuncionario(FuncionarioDTO funcionarioDTO, String cpf){
        if (repository.findFuncionarioByCpf(cpf) != null) {
            throw new ElementAlreadyExistsException("Funcionário");
        }

        Funcionario funcionario = funcionarioMapper.toFuncionario(funcionarioDTO, cpf);

        repository.save(funcionario);

        return funcionarioMapper.toFuncionarioDTO(funcionario);
    }

    public FuncionarioIdDTO atualizarFuncionario(String cpf, FuncionarioDTO funcionarioDTO){
        try {
            repository.updateFuncionarioByCpf(funcionarioMapper.toFuncionario(funcionarioDTO,cpf));

            return buscarPorCpf(cpf);
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public FuncionarioDTO alterarSetorFuncionar(String cpf, String nomeSetor){
        try {
            var funcionario = repository.findFuncionarioByCpf(cpf);
            var setor = setorService.buscarSetorPeloNome(nomeSetor);

            if(setor != null){
                setorService.InserirFuncionarioNoSetor(setor.getId(), funcionario.getId());
            }

            funcionario.setSetor(setor);
            repository.save(funcionario);

            return funcionarioMapper.toFuncionarioDTO(funcionario);
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Setor");
        }
    }

    public FuncionarioDTO excluirFuncionario(String cpf){
        var funcionario = repository.findFuncionarioByCpf(cpf);
        setorService.DeletarFuncionarioDoSetor(funcionario.getId());
        repository.delete(funcionario);
        return funcionarioMapper.toFuncionarioDTO(funcionario);
    }
}