package br.com.alura.leilao.ui.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;

public class AvisoDialogManager {

    private final String MENSAGEM_PADRAO_BOTAO_POSITIVO = "Ok";
    private final String MENSAGEM_AVISO_JA_DEU_CINCO_LANCES = "Usuário não pode dar mais de cinco lances no mesmo leilão";
    private final String MENSAGEM_AVISO_LANCE_SEGUIDO_MESMO_USUARIO = "O mesmo usuário do último lance não pode propror novos lances";
    private final String MENSAGEM_AVISO_LANCE_MENOR_QUE_ULTIMO_LANCE = "Lance precisa ser maior que o último lance";
    private final String MENSAGEM_AVISO_FALHA_NO_ENVIO_DO_LANCE = "Não foi possível enviar Lance";
    private final String MENSAGEM_AVISO_VALOR_INVALIDO = "Valor inválido";
    private Context context;

    public AvisoDialogManager(Context context) {
        this.context = context;
    }

    public void mostraToastFalhaNoEnvio() {
        mostraDialog(MENSAGEM_AVISO_FALHA_NO_ENVIO_DO_LANCE);
    }

    public void mostraAvisoUsuarioJaDeuCincoLances() {
        mostraDialog(MENSAGEM_AVISO_JA_DEU_CINCO_LANCES);
    }

    public void mostraAvisoLanceSeguidoDoMesmoUsuario() {
        mostraDialog(this.MENSAGEM_AVISO_LANCE_SEGUIDO_MESMO_USUARIO);
    }

    public void mostraAvisoLanceMenorQueUltimoLance() {
        mostraDialog(MENSAGEM_AVISO_LANCE_MENOR_QUE_ULTIMO_LANCE);
    }

    public void mostraAvisoValorInvalido() {
        mostraDialog(MENSAGEM_AVISO_VALOR_INVALIDO);
    }

    private void mostraDialog(String mensagem) {
        new AlertDialog.Builder(context)
                .setMessage(mensagem)
                .setPositiveButton(MENSAGEM_PADRAO_BOTAO_POSITIVO, null)
                .show();
    }

}
