package model;

public class Usuario {
    int id;
    String nome;
    String status;
    boolean fotoPerfil = false;

    public Usuario(int id, String nome, String status, boolean fotoPerfil){
        this.id = id;
        this.nome = nome;
        this.status  =  status;
        this.fotoPerfil = fotoPerfil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(boolean fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
