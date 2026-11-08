import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Evento extends EntidadeBase implements Comparable<Evento> {
    private String nome;
    private String endereco;
    private CategoriaEvento categoria;
    private LocalDateTime horario;
    private String descricao;
    private List<Usuario> participantes;

    public static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Evento(String nome, String endereco, CategoriaEvento categoria, LocalDateTime horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
        this.participantes = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public CategoriaEvento getCategoria() { return categoria; }
    public LocalDateTime getHorario() { return horario; }
    public String getDescricao() { return descricao; }
    public List<Usuario> getParticipantes() { return participantes; }

    public void adicionarParticipante(Usuario u) {
        if (!participantes.contains(u)) {
            participantes.add(u);
        }
    }

    public void removerParticipante(Usuario u) {
        participantes.remove(u);
    }

    public String getStatusHorario() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime fimEstimado = horario.plusHours(3);

        if (agora.isAfter(horario) && agora.isBefore(fimEstimado)) {
            return "🔴 OCORRENDO AGORA";
        } else if (agora.isAfter(fimEstimado)) {
            return "⚪ JÁ OCORREU";
        } else {
            return "🟢 FUTURO";
        }
    }

    @Override
    public String getIdentificador() {
        return nome;
    }

    @Override
    public String exibirResumo() {
        return String.format("[%s] %s - %s (%s) em %s", getStatusHorario(), nome, horario.format(FMT), categoria, endereco);
    }

    public String paraLinhaArquivo() {
        return String.format("%s;%s;%s;%s;%s",
                nome, endereco, categoria.name(), horario.format(FMT), descricao);
    }

    public static Evento deLinhaArquivo(String linha) {
        String[] partes = linha.split(";");
        if (partes.length < 5) return null;
        String nome = partes[0];
        String endereco = partes[1];
        CategoriaEvento cat = CategoriaEvento.valueOf(partes[2]);
        LocalDateTime hor = LocalDateTime.parse(partes[3], FMT);
        String desc = partes[4];
        return new Evento(nome, endereco, cat, hor, desc);
    }

    @Override
    public int compareTo(Evento outro) {
        return this.horario.compareTo(outro.horario);
    }
}
