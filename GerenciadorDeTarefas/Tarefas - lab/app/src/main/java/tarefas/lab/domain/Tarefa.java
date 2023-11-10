package tarefas.lab.domain;

import java.util.UUID;

public class Tarefa {
    // atributos
    private UUID id;
    
    private String titulo;
    private String descricao;
    private String prioridade;
    private String categoria;
    private String prazoDeConclusao;
    private boolean status;
    
    // construtor
    public Tarefa(String titulo, String descricao, String prioridade, String categoria, String prazoDeConclusao,
    boolean status) {
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.prazoDeConclusao = prazoDeConclusao;
        this.status = status;
    }
    
    public UUID getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPrazoDeConclusao() {
        return prazoDeConclusao;
    }

    public void setPrazoDeConclusao(String prazoDeConclusao) {
        this.prazoDeConclusao = prazoDeConclusao;
    }

    public boolean isStatus() {
        return status;
    }

    public String getStatus() {
        String statusAtividade = "";
        if (this.status) {
            statusAtividade = "Concluída";
        } else {
            statusAtividade = "Pendente";
        }
        return statusAtividade;
    }    

    public void setStatus(boolean status) {
        this.status = status;
    }
}
