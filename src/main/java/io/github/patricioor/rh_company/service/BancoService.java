package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.domain.Banco;
import io.github.patricioor.rh_company.domain.dto.BancoDTO;
import io.github.patricioor.rh_company.exception.ElementNotFoundException;
import io.github.patricioor.rh_company.repository.IBancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BancoService {
    private IBancoRepository repository;

    @Autowired
    public BancoService(IBancoRepository repository) {
        this.repository = repository;
    }

    Banco buscarBancoById(String id){
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ElementNotFoundException("Banco"));
    }

    public Banco criarBanco(BancoDTO bancoDTO){
        Banco banco = new Banco();

        atualizandoBanco(bancoDTO, banco);

        repository.save(banco);
        return banco;
    }

    public Banco atualizarBanco(BancoDTO bancoDTO){
        Banco banco = buscarBancoById(bancoDTO.getId());

        atualizandoBanco(bancoDTO, banco);

        repository.save(banco);
        return banco;
    }

    public Banco deletarBanco(BancoDTO bancoDTO){
        Banco banco = buscarBancoById(bancoDTO.getId());

        if(banco == null){
            throw new ElementNotFoundException("Banco");
        }

        repository.delete(banco);
        return banco;
    }

    private static void atualizandoBanco(BancoDTO bancoDTO, Banco banco) {
        banco.setAgencia(banco.getAgencia());
        banco.setBanco(bancoDTO.getNomeBanco());
        banco.setConta((bancoDTO.getConta()));
    }
}
