public class Usuario extends EntidadeBase {
    private String nome;
    private String email;
    private String telefone;
    private String bairroEmPortoAlegre;

    public Usuario(String nome, String email, String telefone, String bairroEmPortoAlegre) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.bairroEmPortoAlegre = bairroEmPortoAlegre;
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getBairroEmPortoAlegre() { return bairroEmPortoAlegre; }

    @Override
    public String getIdentificador() {
        return email;
    }

    @Override
    public String exibirResumo() {
        return String.format("Usuário: %s (%s) - Bairro: %s", nome, email, bairroEmPortoAlegre);
    }
}
