package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = Double.NEGATIVE_INFINITY;
    private double menorLance = Double.POSITIVE_INFINITY;

    public void propoem(Lance lance) {
        lances.add(lance);
        Collections.sort(lances);
        double lanceValor = lance.getValor();
        calculaMaiorLance(lanceValor);
        calculaMenorLance(lanceValor);
    }

    private void calculaMenorLance(double lanceValor) {
        if (lanceValor < menorLance) {
            menorLance = lanceValor;
        }
    }

    private void calculaMaiorLance(double lanceValor) {
        if (lanceValor > maiorLance) {
            maiorLance = lanceValor;
        }
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public double getMenorLance() {
        return menorLance;
    }

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Lance> tresMaioresLances() {
        int quantiaMaximaDeLances = lances.size();
        if (quantiaMaximaDeLances > 3) quantiaMaximaDeLances = 3;
        return lances.subList(0, quantiaMaximaDeLances);
    }
}
