package br.com.alura.leilao.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LeilaoTest {

    private final double DELTA = 0.0001;
    private final Leilao LEILAO_AMOSTRA = new Leilao("Console");
    private final Usuario LANCE_USUARIO_AMOSTRA = new Usuario("Usuario");

    @Test
    public void deveRetornar_MenorLance_QuandoHaverUmUnicoLance() {
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 350.0));

        double menorLanceDevolvido = LEILAO_AMOSTRA.getMenorLance();
        assertEquals(350.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deveRetornar_MenorLance_QuandoHAverMaisDeUmLance() {
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 150.0));
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 200.0));
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 250.0));

        double menorLanceDevolvido = LEILAO_AMOSTRA.getMenorLance();
        assertEquals(150.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deveRetornar_MenorLance_QuandoHaverLancesEmOrdemDecrescente() {
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 350.0));
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 300.0));
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 200.0));

        double menorLanceDevolvido = LEILAO_AMOSTRA.getMenorLance();
        assertEquals(200.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deveRetornar_MenorLance_QuandoHaverLancesEmOrdemCrescente() {
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 200.0));
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 300.0));
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 350.0));

        double menorLanceDevolvido = LEILAO_AMOSTRA.getMenorLance();
        assertEquals(200.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deveRetornar_MaiorLance_QuandoHaverUmUnicoLance() {
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 350.0));

        double maiorLanceDevolvido = LEILAO_AMOSTRA.getMaiorLance();
        assertEquals(350.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deveRetornar_MaiorLance_QuandoHaverMaisDeUmLance() {
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 150.0));
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 200.0));

        double maiorLanceDevolvido = LEILAO_AMOSTRA.getMaiorLance();
        assertEquals(200, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deveRetornar_MaiorLance_QuandoHaverLancesEmOrdemCrescente() {
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 200.0));
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 300.0));
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 350.0));

        double maiorLanceDevolvido = LEILAO_AMOSTRA.getMaiorLance();
        assertEquals(350.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deveRetornar_MaiorLance_QuandoHaverLancesEmOrdemDecrescente() {
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 350.0));
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 300.0));
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 200.0));

        double maiorLanceDevolvido = LEILAO_AMOSTRA.getMaiorLance();
        assertEquals(350.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_RetornarOsTresMaioresLance_QuandoReceExatosTresLances() {
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 350.0));
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 375.0));
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 385.0));

        List<Lance> tresMaioresLancesDevolvidos = LEILAO_AMOSTRA.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(350.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
        assertEquals(375.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(385.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_RetornarOsTresMaioresLance_QuandoNaoHaverLances() {
        List<Lance> tresMaioresLancesDevolvidos = LEILAO_AMOSTRA.tresMaioresLances();

        assertEquals(0, tresMaioresLancesDevolvidos.size());
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasUmLance() {
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 385.0));
        List<Lance> tresMaioresLancesDevolvidos = LEILAO_AMOSTRA.tresMaioresLances();
        assertEquals(1, tresMaioresLancesDevolvidos.size());
        assertEquals(385.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_RetornarOsTresMaioresLance_QuandoReceApenasDoisLances() {
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 375.0));
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 385.0));

        List<Lance> tresMaioresLancesDevolvidos = LEILAO_AMOSTRA.tresMaioresLances();

        assertEquals(2, tresMaioresLancesDevolvidos.size());
        assertEquals(375.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(385.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_RetornarOsTresMaioresLance_QuandoMaisQueTresLances() {
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 305.0));
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 310.0));
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 320.0));
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 350.0));
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 375.0));
        LEILAO_AMOSTRA.propoem(new Lance(LANCE_USUARIO_AMOSTRA, 385.0));

        List<Lance> tresMaioresLancesDevolvidos = LEILAO_AMOSTRA.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(350.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
        assertEquals(375.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(385.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
    }
}