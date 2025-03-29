package br.edu.fatecpg.model;
//Crie um classe produto(nome , categoria , preço)
//Filtre os produtos de categoria "Eletronico"
//Aplique 10% de desconto nos produdos de preço maior que R$100
//Apresentar a soma do preço de todos os produtos

public class Produto {
    String nome;
    String categoria;
    Double preco;

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                ", preco=" + preco +
                '}';
    }


    public Produto(String nome, String categoria, Double preco) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;

    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }

}

