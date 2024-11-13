package model;

import java.sql.Timestamp;

public class Mensagens {
    int id;
    String remetente;
    String destinatario;
    String conteudo;
    Timestamp horario;
    String  statusMensagem;

    public Mensagens(int id,  String remetente, String destinatario, String conteudo, Timestamp horario, String statusMensagem){
        this.id =id;
        this.remetente =  remetente;
        this.destinatario  = destinatario;
        this.conteudo = conteudo;
        this.horario = horario;
        this.statusMensagem = statusMensagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Timestamp getHorario() {
        return horario;
    }

    public void setHorario(Timestamp horario) {
        this.horario = horario;
    }

    public String getStatusMensagem() {
        return statusMensagem;
    }

    public void setStatusMensagem(String statusMensagem) {
        this.statusMensagem = statusMensagem;
    }
}
