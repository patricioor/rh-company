package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.domain.Banco;
import io.github.patricioor.rh_company.domain.Funcionario;
import io.github.patricioor.rh_company.domain.Setor;
import io.github.patricioor.rh_company.domain.dto.FuncionarioDTO;
import io.github.patricioor.rh_company.exception.ElementAlreadyExistsException;
import io.github.patricioor.rh_company.exception.ElementNotFoundException;
import io.github.patricioor.rh_company.repository.IFuncionarioRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FuncionarioService{
    private final IFuncionarioRepository repository;
    private final SetorService setorService;
    private final BancoService bancoService;

    @Autowired
    public FuncionarioService(IFuncionarioRepository repository, SetorService setorService, BancoService bancoService) {
        this.repository = repository;
        this.setorService = setorService;
        this.bancoService = bancoService;
    }

    public Funcionario buscarPorId (UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Funcionário"));
    }

    public Funcionario buscarPorCpf (String cpf){
        try{
            return repository.findByCpf(cpf);
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

    public List<Funcionario> buscarPorNome (String nome){
        try {
            return repository.findFuncionarioByNome(nome);
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    };

    public List<Funcionario> retornarFuncionariosPeloCargo(String cargo){
        try {
            return repository.findFuncionariosByCargo(cargo);
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public List<Funcionario> retornarFuncionariosPeloSetorId(UUID id){
        try {
            return repository.findFuncionariosBySetor(id);
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public List<Funcionario> retornarFuncionariosPeloStatus(Boolean status){
        try {
            return repository.findFuncionariosByStatus(status);
        }   catch (ElementNotFoundException e) {
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public Funcionario criarFuncionario(FuncionarioDTO funcionarioDTO){
        if (repository.findFuncionarioByCpf(funcionarioDTO.getCpf()) != null) {
            throw new ElementAlreadyExistsException("Funcionário");
        }

        Setor setor= setorService.retornarSetorPeloNome(funcionarioDTO.getSetor());

        if(setor == null){
            setorService.criarSetor(funcionarioDTO.getNome());
            setor = setorService.retornarSetorPeloNome(funcionarioDTO.getSetor());
        }

        Banco banco = bancoService.buscarBancoById(funcionarioDTO.getBanco());

        Funcionario funcionario = updateFuncionario(funcionarioDTO, setor, banco);

        repository.save(funcionario);

        return funcionario;
    }

    public Funcionario atualizarFuncionario(String id, FuncionarioDTO funcionarioUpdated){
        try {
            var funcionario = buscarPorId(UUID.fromString(id));

            Setor setor = setorService.retornarSetorPeloNome(funcionario.getSetor().getNome());
            Banco banco = bancoService.buscarBancoById(funcionarioUpdated.getBanco());

            funcionario = updateFuncionario(funcionarioUpdated,setor,banco);
            repository.save(funcionario);

            return funcionario;
        } catch (ElementNotFoundException e){
            throw new ElementNotFoundException("Funcionário");
        }
    }

    public Funcionario excluirFuncionario(String cpf){
        var funcionario = buscarPorCpf(cpf);
        repository.delete(funcionario);
        return funcionario;
    }

    @NotNull
    private static Funcionario updateFuncionario(FuncionarioDTO funcionarioDTO, Setor setor, Banco banco) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setCpf(funcionarioDTO.getCpf());
        funcionario.setCargo(funcionarioDTO.getCargo());
        funcionario.setGenero(funcionarioDTO.getGenero());
        funcionario.setEmail(funcionarioDTO.getEmail());
        funcionario.setDataContratacao(funcionarioDTO.getDataContratacao());
        funcionario.setDataNascimento(funcionarioDTO.getDataNascimento());
        funcionario.setSalarioBase(funcionarioDTO.getSalarioBase());
        funcionario.setStatus(funcionarioDTO.getStatus());
        funcionario.setSetor(setor);
        funcionario.setBanco(banco);
        return funcionario;
    }
}
