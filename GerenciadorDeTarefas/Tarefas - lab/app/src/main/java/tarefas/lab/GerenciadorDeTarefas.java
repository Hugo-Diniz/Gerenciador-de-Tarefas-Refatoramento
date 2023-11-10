package tarefas.lab;

import java.util.List;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import tarefas.lab.controller.Controlador;
import tarefas.lab.domain.Tarefa;
import tarefas.lab.exceptions.*;
import tarefas.lab.view.ConsoleColors;

public class GerenciadorDeTarefas {

    public static void main(String[] args) throws LoginException, OpcaoInvalidaException, DataInvalidaException,
            UsuarioExistenteException, IndiceInvalidoException, PrioridadeInvalidaException, ListaVaziaException {
        Controlador controlador = new Controlador();

        boolean continuarUsuario = true;
        boolean continuarTarefa = true;

        
        while (continuarUsuario) {
            try {
                
                System.out.println(ConsoleColors.YELLOW_BOLD + "GERENCIADOR DE TAREFAS" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.BLACK_BOLD + "[1] - Fazer login");
                System.out.println("[2] - Cadastrar");
                System.out.println("[3] - Sair" + ConsoleColors.RESET);
    
                System.out.print("-> ");
                Scanner scannerPrincipal = new Scanner(System.in);
                String escolha = scannerPrincipal.nextLine();
                System.out.println();
    
                switch (escolha) {
                    case "1":
                        Scanner scannerLogin = new Scanner(System.in);
    
                        System.out.print("Nome de usuário: ");
                        String nomeLogin = scannerLogin.nextLine();
                        System.out.print("\nSenha de usuário: ");
                        String senhaLogin = scannerLogin.nextLine();
                        System.out.println();

                        if (controlador.entrar(nomeLogin, senhaLogin)) {
                            System.out.println(ConsoleColors.GREEN + "Login bem-sucedido!" + ConsoleColors.RESET);
                            List<Tarefa> listaDeTarefas = controlador.getUsuarioAtual().getListaDeTarefa();
                            
                            continuarTarefa = true;
                            while (continuarTarefa) {
                                System.out
                                        .println(ConsoleColors.YELLOW_BOLD + "[MENU TAREFAS]: " + ConsoleColors.RESET);
                                System.out.println(ConsoleColors.BLACK_BOLD + "[1] -  Adicionar tarefa");
                                System.out.println("[2] -  Listar tarefas");
                                System.out.println("[3] -  Editar tarefa");
                                System.out.println("[4] -  Atualizar status da tarefa");
                                System.out.println("[5] -  Remover tarefa");
                                System.out.println("[6] -  Filtrar tarefas");
                                System.out.println("[7] -  Buscar tarefas");
                                System.out.println("[8] -  Sair" + ConsoleColors.RESET);

                                Scanner scannerTarefa = new Scanner(System.in);
                                System.out.print("-> ");
                                String opcao = scannerTarefa.nextLine();
                                System.out.println();

                                try {
                                    switch (opcao) {
                                        case "1":
                                            Scanner scannerCriaTarefa = new Scanner(System.in);
    
                                            System.out.print("Digite o título da tarefa: ");
                                            String tituloCriacao = scannerCriaTarefa.nextLine();
                                            System.out.print("\nDigite a descrição da tarefa: ");
                                            String descricaoCriacao = scannerCriaTarefa.nextLine();
    
                                            boolean prioridadeValidada = false;
                                            String prioridadeCriacao = "";
                                            while (!prioridadeValidada) {
                                                System.out.println("\nDigite a prioridade da tarefa (1, 2 OU 3)");
                                                System.out.println("[1] MÁXIMA\n[2] COMUM\n[3] MÍNIMA");
                                                System.out.print("-> ");
                                                prioridadeCriacao = scannerCriaTarefa.nextLine();
                                                try {
                                                    prioridadeCriacao = controlador.formatarPrioridade(prioridadeCriacao);
                                                    prioridadeValidada = true;
                                                } catch (PrioridadeInvalidaException e) {
                                                    System.out.println(e.getMessage());
                                                }
                                            }
    
                                            System.out.print("\nDigite a categoria da tarefa: ");
                                            String categoriaCriacao = scannerCriaTarefa.nextLine();
    
                                            String prazoCriacao = "";
                                            boolean prazoValidado = false;
                                            while (!prazoValidado) {
                                                System.out.print("\nDigite o prazo da tarefa (dd/mm/aaaa): ");
                                                prazoCriacao = scannerCriaTarefa.nextLine();
                                                try {
                                                    if (controlador.formatarData(prazoCriacao) == null) {
                                                        // PADRONIZAR TODOS OS EXCEPTIONS
                                                        throw new DataInvalidaException("Data inválida!");
                                                    } else {
                                                        prazoValidado = true;
                                                    }
                                                } catch (DataInvalidaException e) {
                                                    System.out.println(e.getMessage());
                                                }
                                            }
    
                                            controlador.criarTarefa(tituloCriacao, descricaoCriacao, prioridadeCriacao,
                                                    categoriaCriacao, prazoCriacao);
                                                    System.out.println(ConsoleColors.sentencaColorida(ConsoleColors.GREEN, "Atividade criada com sucesso!"));
                                            break;
    
                                        case "2":
                                            try {
                                                controlador.listarTarefas(listaDeTarefas);
                                            } catch(ListaVaziaException e) {
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                        case "3":
                                            try {
                                                controlador.verificarListaVazia();
                                                Scanner scannerEdicao = new Scanner(System.in);
                                                System.out.println("Selecione a tarefa");
                                                System.out.print("-> ");
                                                int tarefaEditar = scannerEdicao.nextInt();
                                                System.out.println();
        
                                                System.out.println(
                                                        ConsoleColors.BLACK_BOLD + "Escolha uma Opção:" + ConsoleColors.RESET);
                                                System.out.println(ConsoleColors.BLACK_BOLD + "[1] - Título");
                                                System.out.println("[2] - Descrição");
                                                System.out.println("[3] - Prioridade");
                                                System.out.println("[4] - Categoria");
                                                System.out.println("[5] - Prazo de conclusão" + ConsoleColors.RESET);
                                                Scanner leitor = new Scanner(System.in);
                                                System.out.print("-> ");
                                                int opcaoEditar = leitor.nextInt();
                                                System.out.println();
                                                boolean repeticaoOpcao = false;
                                                try {
                                                    Scanner leitorMudanca = new Scanner(System.in);
                                                    while (!repeticaoOpcao) {
                                                        String novaMudanca;
                                                        switch (opcaoEditar) { 
                                                            case 1:
                                                                System.out.print("Digite o novo título: ");
                                                                novaMudanca = leitorMudanca.nextLine();
                                                                controlador.editarTarefa(tarefaEditar, opcaoEditar,
                                                                        novaMudanca);
                                                                repeticaoOpcao = true;
                                                                break;
                                                            case 2:
                                                                System.out.print("Digite a nova descrição: ");
                                                                novaMudanca = leitorMudanca.nextLine();
                                                                controlador.editarTarefa(tarefaEditar, opcaoEditar,
                                                                        novaMudanca);
                                                                repeticaoOpcao = true;
                                                                break;
                                                            case 3:
                                                                System.out.println(
                                                                        "Digite a prioridade:\n[1] - MÁXIMA\n[2] - COMUM\n[3] - MÍNIMA\n");
                                                                System.out.print("-> ");
                                                                Scanner sc = new Scanner(System.in);
                                                                String novaPrioridade = sc.nextLine();
                                                                try {
                                                                    String prioridade = controlador.formatarPrioridade(novaPrioridade);
                                                                    controlador.editarTarefa(tarefaEditar, opcaoEditar, prioridade);
                                                                    repeticaoOpcao = true;
                                                                
                                                                } catch (PrioridadeInvalidaException e) {
                                                                    System.out.println(e.getMessage());
                                                                }
                                                                break;
                                                            case 4:
                                                                System.out.print("Digite a nova categoria: ");
                                                                novaMudanca = leitorMudanca.nextLine();
                                                                controlador.editarTarefa(tarefaEditar, opcaoEditar,
                                                                        novaMudanca);
                                                                repeticaoOpcao = true;
                                                                break;
                                                            case 5:
                                                                System.out.print("Digite a data (dd/mm/aaaa): ");
                                                                Scanner leitorNovaData = new Scanner(System.in);
                                                                String novaData = leitorNovaData.nextLine();
        
                                                                try {
                                                                    String data = controlador.formatarData(novaData);
                                                                    controlador.editarTarefa(tarefaEditar, opcaoEditar, data);
                                                                    repeticaoOpcao = true;
                                                                    
                                                                } catch (DataInvalidaException e) {
                                                                    System.out.println(e.getMessage());
                                                                }
                                                                break;
                                                            default:
                                                                throw new OpcaoInvalidaException("Opção inválida.");
                                                        }
                                                    }
                                                    System.out.println(ConsoleColors.sentencaColorida(ConsoleColors.GREEN,
                                                            "Tarefa atualizada com sucesso."));
                                                } catch (OpcaoInvalidaException e) {
                                                    System.out.println(e.getMessage());
                                                }
                                            } catch  (ListaVaziaException e) {
                                                System.out.println(e.getMessage());
                                            }
                                            break;

                                        case "4":
                                            try {
                                                controlador.verificarListaVazia();                                         
                                                System.out.print("Informe um índice de tarefa para atualizar.\n");

                                                Scanner scannerAtualizacao = new Scanner(System.in);
                                                System.out.print("-> ");
                                                int indiceAtualizacao = scannerAtualizacao.nextInt();
                                                controlador.concluirTarefa(indiceAtualizacao); // muda o status da tarefa
                                                System.out.println(ConsoleColors.sentencaColorida(ConsoleColors.GREEN, "Status da tarefa atualizado com sucesso!"));
                                            } catch  (ListaVaziaException e) {
                                                System.out.println(e.getMessage());
                                            }         
                                            break;

                                        case "5":
                                            try {
                                                controlador.verificarListaVazia();
                                                System.out.print("Informe um índice de tarefa para remover\n");
        
                                                Scanner scannerRemocao = new Scanner(System.in);
                                                System.out.print("-> ");
                                                int indiceRemocao = scannerRemocao.nextInt();
                                                try {
                                                    controlador.removerTarefa(indiceRemocao);    
                                                    System.out.println(ConsoleColors.sentencaColorida(ConsoleColors.GREEN, "Tarefa removida com sucesso!"));
                                                } catch (IndiceInvalidoException e){
                                                    System.out.println(e.getMessage());
                                                }

                                            } catch  (ListaVaziaException e) {
                                                System.out.println(e.getMessage());
                                            }                                              
                                            break;
                                            
                                        case "6":
                                            try {
                                                controlador.verificarListaVazia();                                            
                                                System.out.println("Escolha uma opção de prioridade: \n");
                                                System.out.println("[1] - MÁXIMA\n[2] - COMUM\n[3] - MÍNIMA");
        
                                                Scanner scannerFiltro = new Scanner(System.in);
                                                System.out.print("-> ");
                                                String prioridade = scannerFiltro.nextLine();
                                                System.out.println();

                                                try {
                                                    controlador.filtrarTarefas(prioridade);
                                                } catch (PrioridadeInvalidaException e) {
                                                    System.out.println(e.getMessage());
                                                }

                                            } catch (ListaVaziaException e) {
                                                System.out.println(e.getMessage());
                                            }
                                            break;
    
                                        case "7":
                                            try {
                                                controlador.verificarListaVazia(); 
                                                Scanner scannerBuscarTarefa = new Scanner(System.in);
                                                System.out.print("Digite o que você gostaria de buscar: ");
                                                String textoBuscado = scannerBuscarTarefa.nextLine();
                                                System.out.println();

                                                List<Tarefa> resultado = controlador.buscarTarefas(textoBuscado);

                                                controlador.listarTarefas(resultado);
                                            } catch (ListaVaziaException e) {
                                                System.out.println(e.getMessage());
                                            }
                                            break;
    
                                        case "8":
                                            continuarTarefa = false;
                                            break;
    
                                        default:
                                            throw new OpcaoInvalidaException("Opção Inválida!");
                                    }
                                } catch (OpcaoInvalidaException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        } else {
                            System.out.println("Nome de usuário ou senha incorretos.");
                            break;
                        }
                        break;
                    case "2":
                        Scanner scannerCadastro = new Scanner(System.in);
    
                        try {
                            System.out.print("Digite seu nome de usuário: ");
                            String nomeCadastro = scannerCadastro.nextLine();
                            System.out.println();
                            //controlador.validarCadastro(nomeCadastro);
                            System.out.print("Digite sua senha: ");
                            String senhaCadastro = scannerCadastro.nextLine();
                            System.out.println();
                            controlador.cadastrar(nomeCadastro, senhaCadastro);
                            System.out.println(ConsoleColors.sentencaColorida(ConsoleColors.GREEN, "Usuário " + nomeCadastro + " foi cadastrado com sucesso."));
                        } catch (UsuarioExistenteException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "3":
                        continuarUsuario = false;
                        scannerPrincipal.close();
                        break;
    
                    default:
                        throw new OpcaoInvalidaException("Opção inválida!");
                }
            } catch (OpcaoInvalidaException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
