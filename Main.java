import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorEventos gerenciador = new GerenciadorEventos();
        Usuario usuarioAtual = null;

        while (true) {
            System.out.println("\n=========================================");
            System.out.println("  SISTEMA DE EVENTOS - PORTO ALEGRE");
            if (usuarioAtual != null) {
                System.out.println("  Sessão ativa: " + usuarioAtual.getNome());
            }
            System.out.println("=========================================");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Selecionar / Trocar Usuário Ativo");
            System.out.println("3. Cadastrar Novo Evento");
            System.out.println("4. Consultar Eventos (Ordenados por horário)");
            System.out.println("5. Confirmar Presença em um Evento");
            System.out.println("6. Ver Minhas Presenças Confirmadas / Cancelar Presença");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = Integer.parseInt(scanner.nextLine());

            if (opcao == 0) {
                gerenciador.salvarEventosNoArquivo();
                System.out.println("Dados salvos em 'events.data'. Encerrando o programa...");
                break;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("E-mail: ");
                    String email = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String tel = scanner.nextLine();
                    System.out.print("Bairro em Porto Alegre: ");
                    String bairro = scanner.nextLine();
                    Usuario novoU = new Usuario(nome, email, tel, bairro);
                    gerenciador.cadastrarUsuario(novoU);
                    usuarioAtual = novoU;
                    break;

                case 2:
                    System.out.print("Digite o e-mail do usuário cadastrado: ");
                    String emailBusca = scanner.nextLine();
                    Usuario uEncontrado = gerenciador.buscarUsuarioPorEmail(emailBusca);
                    if (uEncontrado != null) {
                        usuarioAtual = uEncontrado;
                        System.out.println("✅ Usuário " + usuarioAtual.getNome() + " selecionado!");
                    } else {
                        System.out.println("❌ Usuário não encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Nome do evento: ");
                    String nomeEv = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String endEv = scanner.nextLine();

                    System.out.println("Categorias disponíveis:");
                    for (CategoriaEvento cat : CategoriaEvento.values()) {
                        System.out.println(" - " + cat);
                    }
                    System.out.print("Digite a categoria: ");
                    CategoriaEvento catEv = CategoriaEvento.valueOf(scanner.nextLine().toUpperCase());

                    System.out.print("Data e Horário (ex: 15/12/2026 20:00): ");
                    LocalDateTime horEv = LocalDateTime.parse(scanner.nextLine(), Evento.FMT);

                    System.out.print("Descrição do evento: ");
                    String descEv = scanner.nextLine();

                    gerenciador.cadastrarEvento(new Evento(nomeEv, endEv, catEv, horEv, descEv));
                    break;

                case 4:
            List<Evento> ordenados = gerenciador.getEventosOrdenados();
            if (ordenados.isEmpty()) {
                System.out.println("\nNenhum evento cadastrado.");
            } else {
                System.out.println("\n--- LISTA DE EVENTOS ---");
                for (int i = 0; i < ordenados.size(); i++) {
                    System.out.println((i + 1) + ". " + ordenados.get(i).exibirResumo());
                }
            }
            System.out.println("\nPressione ENTER para voltar ao menu...");
            scanner.nextLine();
            break;

                case 5:
                    if (usuarioAtual == null) {
                        System.out.println("❌ Cadastre ou selecione um usuário primeiro (opção 1 ou 2).");
                        break;
                    }
                    List<Evento> evs = gerenciador.getEventosOrdenados();
                    System.out.println("Selecione o número do evento para confirmar presença:");
                    for (int i = 0; i < evs.size(); i++) {
                        System.out.println((i + 1) + ". " + evs.get(i).getNome());
                    }
                    int idxConfirm = Integer.parseInt(scanner.nextLine()) - 1;
                    if (idxConfirm >= 0 && idxConfirm < evs.size()) {
                        evs.get(idxConfirm).adicionarParticipante(usuarioAtual);
                        System.out.println("✅ Presença confirmada no evento!");
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;

                case 6:
                    if (usuarioAtual == null) {
                        System.out.println("❌ Selecione um usuário primeiro.");
                        break;
                    }
                    System.out.println("\n--- SUAS PRESENÇAS CONFIRMADAS ---");
                    List<Evento> listaEvs = gerenciador.getEventosOrdenados();
                    boolean temPresenca = false;
                    for (int i = 0; i < listaEvs.size(); i++) {
                        Evento ev = listaEvs.get(i);
                        if (ev.getParticipantes().contains(usuarioAtual)) {
                            temPresenca = true;
                            System.out.println((i + 1) + ". " + ev.exibirResumo());
                        }
                    }
                    if (!temPresenca) {
                        System.out.println("Você não está inscrito em nenhum evento.");
                    } else {
                        System.out.print("Digite o número do evento para CANCELAR a presença (ou 0 para voltar): ");
                        int idxCanc = Integer.parseInt(scanner.nextLine()) - 1;
                        if (idxCanc >= 0 && idxCanc < listaEvs.size()) {
                            listaEvs.get(idxCanc).removerParticipante(usuarioAtual);
                            System.out.println("✅ Presença cancelada com sucesso.");
                        }
                    }
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }
}
