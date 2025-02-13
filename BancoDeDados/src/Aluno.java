public class Aluno {
    public String cpf;
    public String nome;

    @Override
    public String toString() {
        return "Aluno{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }

    public Aluno(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }
}
