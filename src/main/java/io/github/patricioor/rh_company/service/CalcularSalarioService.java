package io.github.patricioor.rh_company.service;

import io.github.patricioor.rh_company.domain.FolhaPagamento.Desconto;
import io.github.patricioor.rh_company.domain.FolhaPagamento.Provento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalcularSalarioService {
    public double calcularSalarioBruto(List<Provento> listaProv){
        double somatorioProvento = 0.0;
        double salarioBruto = 0.0;

        for(Provento prov : listaProv){
            somatorioProvento =+ prov.getValor();
        }
        salarioBruto = salarioBruto + somatorioProvento;

        return salarioBruto;
    }
    public double calcularSalarioLiquido(List<Desconto> listaDesc, double salarioBruto){
        double somatorioDesconto = 0.0;
        double salarioLiquido = 0.0;

        for(Desconto desc : listaDesc){
            somatorioDesconto =+ desc.getValor();
        }

        salarioLiquido = salarioBruto - somatorioDesconto;

        return salarioLiquido < 0 ? 0.0: salarioLiquido;
    }
}
