package br.com.alura.leilao.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.leilao.api.retrofit.client.LeilaoWebClient;
import br.com.alura.leilao.api.retrofit.client.RespostaListener;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class AtualizaLeiloesTest {

    @Mock
    private ListaLeilaoAdapter adapter;

    @Mock
    private LeilaoWebClient cliente;

    @Spy
    private AtualizaLeiloes atualizaLeiloes;

    @Mock
    private AtualizaLeiloes.ErrorListener listener;

    @Test
    public void deve_AtualizarListaDeLeiloes_QuandoBuscarListaDaApi() {

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                RespostaListener<List<Leilao>> arguments = invocation.getArgumentAt(0, RespostaListener.class);
                arguments.sucesso(new ArrayList<>(Arrays.asList(
                        new Leilao("Casa"),
                        new Leilao("Computador")
                )));
                return null;
            }
        }).when(cliente).todos(any(RespostaListener.class));

        atualizaLeiloes.buscaLeiloes(adapter, cliente, listener);
        verify(cliente).todos(any(RespostaListener.class));
        verify(adapter).atualiza(new ArrayList<>(Arrays.asList(
                new Leilao("Casa"),
                new Leilao("Computador")
        )));
    }

    @Test
    public void deve_ApresentarMensagemDeFalha_QuandoFalhaABuscaDeLeiloes() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                RespostaListener<List<Leilao>> arguments = invocation.getArgumentAt(0, RespostaListener.class);
                arguments.falha(anyString());
                return null;
            }
        }).when(cliente).todos(any(RespostaListener.class));
        atualizaLeiloes.buscaLeiloes(adapter, cliente, listener);
        verify(listener).onError(anyString());
    }
}