package br.com.alura.leilao.ui;

import android.support.v7.widget.RecyclerView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.alura.leilao.database.dao.UsuarioDAO;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaUsuarioAdapter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AtualizadorDeUsuarioTest {

    @Mock
    private UsuarioDAO dao;

    @Mock
    private ListaUsuarioAdapter adapter;

    @Mock
    private RecyclerView recyclerView;


    @Test
    public void deve_AtualizarListaDeUsuario_QuandoSalvarUsuario() {
        AtualizadorDeUsuario atualizadorDeUsuario = new AtualizadorDeUsuario(dao, adapter, recyclerView);

        Usuario elias = new Usuario("Elias");
        when(dao.salva(elias)).thenReturn(new Usuario(1, "Elias"));
        when(adapter.getItemCount()).thenReturn(1);

        atualizadorDeUsuario.salva(elias);

        verify(dao).salva(new Usuario("Elias"));
        verify(adapter).adiciona(new Usuario(1, "Elias"));
        verify(recyclerView).smoothScrollToPosition(0);
    }
}