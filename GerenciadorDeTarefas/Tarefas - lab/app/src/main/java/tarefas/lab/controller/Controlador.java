package tarefas.lab.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.security.auth.login.LoginException;

import tarefas.lab.domain.Tarefa;
import tarefas.lab.domain.Usuario;
import tarefas.lab.exceptions.*;
import tarefas.lab.view.ConsoleColors;

public class Controlador {
    private List<Usuario> listaDeUsuario;
    private Usuario usuarioAtual;
    
    public Controlador() {
        this.listaDeUsuario = new ArrayList<>();
    }
    
    public Usuario getUsuarioAtual() {
        return usuarioAtual;
    }

    public void setUsuarioAtual(Usuario usuarioAtual) {
        this.usuarioAtual = usuarioAtual;
    }

    public boolean entrar(String nomeDeUsuario, String senhaDeUsuario) throws LoginException {
        Optional<Usuario> usuario = listaDeUsuario.stream(). filter(
            u -> u.getNomeDeUsuario().equals(nomeDeUsuario) &&
            u.getSenhaDeUsuario().equals(senhaDeUsuario)).findFirst();
        if (usuario.isPresent()) {
            setUsuarioAtual(usuario.get());
            return true;
        }

        return false;
    }

    /*public boolean validarCadastro(String nomeUsuario) throws UsuarioExistenteException {
        if (listaDeUsuario.isEmpty()) {
            return true;
        } else {
            for (Usuario usuario: listaDeUsuario) {
                if (!(usuario.getNomeDeUsuario().equals(nomeUsuario))) {
                    return true;
                }
            }
        }
    } 
  */ 
    // metodo cadastrar e validar cadastro de forma refatorada
    public void cadastrar(String nome, String senha) throws UsuarioExistenteException {
        Optional<Usuario> usuario = listaDeUsuario.stream().filter( u -> u.getNomeDeUsuario().equals(nome)).findFirst();
        if (usuario.isPresent()) {
            throw new UsuarioExistenteException("Usuário já cadastrado!");
        } else {
            Usuario novoUsuario = new Usuario(nome, senha);
            listaDeUsuario.add(novoUsuario);
        }
    }

    public String formatarData(String data) throws DataInvalidaException {
        Pattern dataFormato = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");

        if (dataFormato.matcher(data).matches()) {
            return data;
        } else {
            throw new DataInvalidaException("Data inválida.");
        }
    }

    public String formatarPrioridade(String prioridade) throws PrioridadeInvalidaException {
        String prioridadeFormatada = "";

        switch(prioridade) {
            case "1":
                prioridadeFormatada = "MÁXIMA";
                return prioridadeFormatada;
            case "2":
                prioridadeFormatada = "COMUM";
                return prioridadeFormatada;
            case "3":
                prioridadeFormatada = "MÍNIMA";
                return prioridadeFormatada;
            default:
                throw new PrioridadeInvalidaException("Prioridade inválida.");
        }   
    }

    public void criarTarefa(String titulo, String descricao, String prioridade, String categoria, String prazo) {

        Tarefa novaTarefa = new Tarefa(titulo, descricao, prioridade, categoria, prazo, false);
        getUsuarioAtual().adicionarNovaTarefa(novaTarefa);
    }

    public boolean verificarListaVazia() throws ListaVaziaException {
        if (getUsuarioAtual().getListaDeTarefa().isEmpty()) {
            throw new ListaVaziaException("Lista de tarefas vazia.");
        } else {
            return true;
        }  
    }
    public void listarTarefas(List<Tarefa> listaDesejada) throws ListaVaziaException { // menu de tarefas
        if (listaDesejada.isEmpty()) {
            throw new ListaVaziaException("A lista de tarefas está vazia.");
        } else {
            for (Tarefa tarefa : listaDesejada) {
                Object[] params = new String[] {tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getPrioridade(), tarefa.getCategoria(), tarefa.getPrazoDeConclusao(), tarefa.getStatus()}; 
                System.out.printf("%s================ %s[TAREFA %d]%s ===============%s%n", ConsoleColors.BLACK_BOLD, ConsoleColors.CYAN_BOLD, (getUsuarioAtual().getListaDeTarefa().indexOf(tarefa)  + 1), ConsoleColors.BLACK_BOLD, ConsoleColors.RESET);
                System.out.printf("""
                        TÍTULO: %s\nDESCRIÇÃO: %s\nPRIORIDADE: %s\nCATEGORIA: %s\nPRAZO %s\nSTATUS DE CONCLUSÃO: %s\n
                        """, params);
                System.out.printf("%s============================================%s%n", ConsoleColors.BLACK_BOLD, ConsoleColors.RESET);
                System.out.println();
            }
        }
    }

    public void filtrarTarefas(String prioridade) throws PrioridadeInvalidaException, ListaVaziaException {
        List<Tarefa> tarefasFiltradas = new ArrayList<>();
        
        for (Tarefa tarefa : getUsuarioAtual().getListaDeTarefa()) {
            if (tarefa.getPrioridade().equals(this.formatarPrioridade(prioridade))) {
                tarefasFiltradas.add(tarefa);
            }
        }

        if (tarefasFiltradas.isEmpty()) {
            throw new PrioridadeInvalidaException("Nenhuma ocorrência foi encontrada na prioridade " + this.formatarPrioridade(prioridade));
        }
        this.listarTarefas(tarefasFiltradas);
    }

    public void concluirTarefa(int indiceTarefa) throws IndiceInvalidoException { // muda o status da tarefa
        if (indiceTarefa >= 1 && indiceTarefa <= getUsuarioAtual().getListaDeTarefa().size()) {
            // acessa o status da tarefa indicada e muda
            getUsuarioAtual().getListaDeTarefa().get(indiceTarefa - 1).setStatus(!(getUsuarioAtual().getListaDeTarefa().get(indiceTarefa - 1).isStatus()));
        } else {
            throw new IndiceInvalidoException("Indice inválido!");
        }
    }

    // mudar a lógica de funcionamento do método
    public void editarTarefa(int indiceTarefa, int opcaoEscolhida, String mudanca) {
        if (getUsuarioAtual().getListaDeTarefa().isEmpty()) {
            return;
        }

        if (indiceTarefa >= 1 && indiceTarefa <= getUsuarioAtual().getListaDeTarefa().size()) {
            Tarefa tarefaEditada = getUsuarioAtual().getListaDeTarefa().get(indiceTarefa - 1);
            switch (opcaoEscolhida) {
                case 1:
                    tarefaEditada.setTitulo(mudanca);
                    break;
                case 2:
                    tarefaEditada.setDescricao(mudanca);
                    break;
                case 3:
                    tarefaEditada.setPrioridade(mudanca);
                    break;
                case 4:
                    tarefaEditada.setCategoria(mudanca);
                    break;
                case 5:
                    tarefaEditada.setPrazoDeConclusao(mudanca);
                    break;
                default: 
                    break;
            }
        }
    }

    public List<Tarefa> buscarTarefas(String opcaoBuscar) {
        if (opcaoBuscar == null || opcaoBuscar.isEmpty())
            return new ArrayList<>();

        String termo = opcaoBuscar.toLowerCase().trim();
        List<Tarefa> resultado = getUsuarioAtual().getListaDeTarefa().stream()
            .filter(t -> (t.getTitulo() + t.getDescricao()).toLowerCase().contains(termo)).toList();

        return resultado;
    }
    

    public void removerTarefa(int indiceTarefa) throws IndiceInvalidoException { // remove uma tarefa de um usuário através do indice
        if (indiceTarefa >= 1 && indiceTarefa <= getUsuarioAtual().getListaDeTarefa().size()) {
            getUsuarioAtual().getListaDeTarefa().remove(indiceTarefa - 1);
        } else {
            throw new IndiceInvalidoException("Indice inválido!");
        }
    }

}
