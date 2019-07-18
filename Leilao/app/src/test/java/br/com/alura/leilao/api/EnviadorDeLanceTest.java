package br.com.alura.leilao.api;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import br.com.alura.leilao.api.retrofit.client.LeilaoWebClient;
import br.com.alura.leilao.api.retrofit.client.RespostaListener;
import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException;
import br.com.alura.leilao.exception.LanceSeguidoDoMesmoUsuarioException;
import br.com.alura.leilao.exception.UsuarioJaDeuCincoLancesException;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.dialog.AvisoDialogManager;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class EnviadorDeLanceTest {

    @Mock
    private EnviadorDeLance.LanceProcessadoListener listener;

    @Mock
    private LeilaoWebClient client;

    @Mock
    private AvisoDialogManager manager;

    @Mock
    private Leilao leilao;

    private EnviadorDeLance enviadorDeLance;


    @Before
    public void criaInstanciaDoEnviador() {
        enviadorDeLance = new EnviadorDeLance(client, listener, manager);
    }

    @Test
    public void deve_MostrarmensagemDeFalha_QuandoOLanceForMenorQueUltimoLance() {
        doThrow(LanceMenorQueUltimoLanceException.class)
                .when(leilao).propoe(any(Lance.class));

        enviadorDeLance.envia(leilao, new Lance(new Usuario("Fram"), 1000));
        verify(manager).mostraAvisoLanceMenorQueUltimoLance();
    }

    @Test
    public void deve_MostrarMensagemDeFalha_QuandoUsuarioComCincoLances() {
        doThrow(UsuarioJaDeuCincoLancesException.class).when(leilao).propoe(any(Lance.class));
        enviadorDeLance.envia(leilao, new Lance(new Usuario("Fran"), 100));

        verify(manager).mostraAvisoUsuarioJaDeuCincoLances();
    }

    @Test
    public void deve_MostrarMensagemDeFalha_QuandoUsuarioDerLanceSeguidoDoSeuLance() {
        doThrow(LanceSeguidoDoMesmoUsuarioException.class).when(leilao).propoe(any(Lance.class));
        enviadorDeLance.envia(leilao, new Lance(new Usuario("Fran"), 100));

        verify(manager).mostraAvisoLanceSeguidoDoMesmoUsuario();
        verify(client, never()).propoe(any(Lance.class), anyLong(), any(RespostaListener.class));
    }

    @Test
    public void deve_MostraMensagemDeFalha_QuandoFalharEnvioDeLanceParaAPI() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                RespostaListener<Void> argumentAt = invocation.getArgumentAt(2, RespostaListener.class);
                argumentAt.falha("");
                return null;
            }
        }).when(client).propoe(any(Lance.class), anyLong(), any(RespostaListener.class));
        enviadorDeLance.envia(new Leilao("Computador"),
                new Lance(new Usuario("Alex"), 200));

        verify(manager).mostraToastFalhaNoEnvio();
        verify(listener, never()).processado(new Leilao("Computador"));
    }

    @Test
    public void deve_NotificarLanceProcessado_QuandoEnviarLanceParaAPIComSucesso() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                RespostaListener<Void> argumentAt = invocation.getArgumentAt(2, RespostaListener.class);
                argumentAt.sucesso(null);
                return null;
            }
        }).when(client).propoe(any(Lance.class), anyLong(), any(RespostaListener.class));
        enviadorDeLance.envia(new Leilao("Computador"),
                new Lance(new Usuario("Alex"), 200));
        verify(listener).processado(any(Leilao.class));
        verify(manager, never()).mostraToastFalhaNoEnvio();
    }
}