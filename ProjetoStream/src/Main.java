import br.edu.fatecpg.model.Funcionario;
import br.edu.fatecpg.model.Produto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // 1. Criação da Classe Funcionario (Interna para este exemplo)
        // Veja a classe Funcionario mais abaixo

        // 2. População da Lista
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Ana Silva", "Vendas", 4000.0, 5));
        funcionarios.add(new Funcionario("Bruno Oliveira", "Marketing", 6000.0, 12));
        funcionarios.add(new Funcionario("Carla Souza", "Vendas", 3500.0, 8));
        funcionarios.add(new Funcionario("Daniel Pereira", "TI", 8000.0, 15));
        funcionarios.add(new Funcionario("Eduarda Lima", "Marketing", 5500.0, 7));
        funcionarios.add(new Funcionario("Felipe Rocha", "TI", 7000.0, 10));
        funcionarios.add(new Funcionario("Gabriela Mendes", "RH", 4500.0, 3));
        funcionarios.add(new Funcionario("Henrique Costa", "RH", 5000.0, 18));
        funcionarios.add(new Funcionario("Isabela Vieira", "Vendas", 3000.0, 2));
        funcionarios.add(new Funcionario("João Pedro", "TI", 9000.0, 20));

        
        System.out.println(                                                            );
        System.out.println("2-Manipulação de Dados de Funcionários com Streams e Lambdas"  );
        System.out.println(                                                            );


        // Filtragem: Salário superior a R$ 3000
        System.out.println("\nFuncionários com salário superior a R$ 3000:");
        funcionarios.stream()
                .filter(f -> f.getSalario() > 3000)
                .forEach(System.out::println);

        // Mapeamento: Aumento de 5% para funcionários com mais de 10 anos de serviço
        System.out.println("\nFuncionários com aumento de 5% (mais de 10 anos de serviço):");
        List<Funcionario> funcionariosComAumento = funcionarios.stream()
                .map(f -> {
                    if (f.getAnosDeServico() > 10) {
                        return new Funcionario(f.getNome(), f.getDepartamento(), f.getSalario() * 1.05, f.getAnosDeServico()); //Cria um novo objeto
                    }
                    return f; // Retorna o funcionário original se não houver aumento
                })
                .collect(Collectors.toList());

        funcionariosComAumento.forEach(System.out::println);

        // Ordenação: Pelo nome em ordem alfabética
        System.out.println("\nFuncionários ordenados por nome:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);

        // Redução: Total gasto com salários
        double totalSalarios = funcionarios.stream()
                .mapToDouble(Funcionario::getSalario)
                .sum();
        System.out.println("\nTotal gasto com salários: R$" + totalSalarios);

        // Agrupamento: Média salarial por departamento
        System.out.println("\nMédia salarial por departamento:");
        Map<String, Double> mediaSalarialPorDepartamento = funcionarios.stream()
                .collect(Collectors.groupingBy(
                        Funcionario::getDepartamento,
                        Collectors.averagingDouble(Funcionario::getSalario)
                ));

        mediaSalarialPorDepartamento.forEach((departamento, media) ->
                System.out.println(departamento + ": R$" + String.format("%.2f", media)));


        // Funcionário com o maior tempo de serviço
        Optional<Funcionario> funcionarioMaisAntigo = funcionarios.stream()
                .max(Comparator.comparingInt(Funcionario::getAnosDeServico));

        System.out.println("\nFuncionário com o maior tempo de serviço:");
        funcionarioMaisAntigo.ifPresent(System.out::println);

        // Formatação personalizada da saída
        System.out.println("\nFuncionários com formatação personalizada:");
        funcionarios.forEach(f ->
                System.out.println("Funcionário: " + f.getNome() +
                        ", Departamento: " + f.getDepartamento() +
                        ", Salário: R$" + f.getSalario()));


/// ////////////////////////////////////////////////////////////////
        System.out.println(                                  );
        System.out.println("1-Lista de Produtos:");
        //Crie um classe produto(nome , categoria , preço)
        //Filtre os produtos de categoria "Eletronico"
        //Aplique 10% de desconto nos produdos de preço maior que R$100
        //Apresentar a soma do preço de todos os produtos

        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Celular", "Eletronico", 2000.0));
        produtos.add(new Produto("Sofá", "Móvel", 1200.0));
        produtos.add(new Produto("Headset", "Eletronico", 500.0));
        produtos.add(new Produto("TV", "Eletronico", 2500.0));
        produtos.add(new Produto("Armario", "Móvel", 900.0));
        produtos.add(new Produto("Carregador", "Eletronico", 50.0));


        //Listar a Categoria Eletronicos
        List<Produto> eletronicos = produtos.stream()
                .filter(p -> p.getCategoria().equals("Eletronico"))
                .toList();

        System.out.println("Produtos Eletrônicos: " + eletronicos);


        // Apresenta a soma do preço de todos os produtos
        double somaPrecosOriginais = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();


        System.out.println("Soma dos preços dos produtos (sem  desconto): R$" + somaPrecosOriginais);


        // Aplica 10% de desconto em produtos com preço maior que R$100
        List<Produto> promocao = produtos.stream()
                .filter(p -> p.getPreco() > 100)
                .map(p -> {
                    p.setPreco(p.getPreco() * 0.9); // Aplica o desconto diretamente no objeto
                    return p; // Retorna o objeto modificado
                })
                .toList();
        System.out.println("Desconto nos produtos com o valor maior que 100: " + promocao);

        // Apresenta a soma do preço de todos os produtos
        double somaPrecosDesconto = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
        System.out.println("Soma dos preços dos produtos (com  desconto): R$" + somaPrecosDesconto);
    }
}

/// /////////////////////////////////////////////////////////////////////////////////////////////////////



















/// ////////////////////////////////////////////////////////////////////////////////////////////////////
//        //Listar os maiores de idade
//        List<Pessoa> maiorIdade = pessoas.stream()
//                .filter(p-> p.getIdade() > 17 )
//                .toList();
//        maiorIdade.forEach(System.out::println);
//
//        List<Pessoa> comA = pessoas.stream()
//                .filter(p -> p.getNome().startsWith("A"))
//                .toList();
//        comA.forEach(System.out::println);
/// /////////////////////////////////////////////////////////////////////////////////////////////////////
//        List<Integer> numeros = Arrays.asList(1,2,3,4,5);
//
//        numeros.stream()
//                .map(num -> num * 2)
//                .forEach(System.out::println);
//
//        int nomaDobroPares = numeros.stream()
//                .filter(num -> num%2==0)//ve se o numero é par
//                .map(num -> num*2)//multiplica o numeo par por 2
//                .reduce(0,Integer::sum);
//        System.out.println("\n"+nomaDobroPares);
//
//        List<String> frutas = Arrays.asList("Banana","Maça","Abacaxi","Uva","Laranja");
//
//        List<String> frutasfiltradas = frutas.stream()
//                .filter(fru -> fru.length()>5)
//                .toList();
//        System.out.println("\n"+frutasfiltradas);
