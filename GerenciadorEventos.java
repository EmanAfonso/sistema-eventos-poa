import java.io.*;
import java.util.*;

public class GerenciadorEventos {
    private List<Evento> eventos = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private final String ARQUIVO_DADOS = "events.data";

    public GerenciadorEventos() {
        carregarEventosDoArquivo();
    }

    public void cadastrarUsuario(Usuario u) {
        usuarios.add(u);
        System.out.println("✅ Usuário cadastrado com sucesso!");
    }

    public void cadastrarEvento(Evento e) {
        eventos.add(e);
        salvarEventosNoArquivo();
        System.out.println("✅ Evento cadastrado em Porto Alegre com sucesso!");
        notificarUsuarios(e);
    }

    private void notificarUsuarios(Evento e) {
        System.out.println("\n🔔 [NOTIFICAÇÃO SISTEMA] Notificando usuários sobre novo evento:");
        for (Usuario u : usuarios) {
            System.out.println(" -> Notificação enviada para " + u.getEmail() + ": Novo evento em POA - '" + e.getNome() + "'");
        }
    }

    public List<Evento> getEventosOrdenados() {
        Collections.sort(eventos);
        return eventos;
    }

    public void salvarEventosNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_DADOS))) {
            for (Evento e : eventos) {
                writer.write(e.paraLinhaArquivo());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar eventos no arquivo: " + e.getMessage());
        }
    }

    private void carregarEventosDoArquivo() {
        File file = new File(ARQUIVO_DADOS);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Evento ev = Evento.deLinhaArquivo(linha);
                if (ev != null) {
                    eventos.add(ev);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados do arquivo events.data: " + e.getMessage());
        }
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) return u;
        }
        return null;
    }
}
