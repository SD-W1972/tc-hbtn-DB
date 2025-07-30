package demo;

import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;

import java.time.LocalDate;
import java.util.List;

public class AdministrativoApp {

    public static void main(String[] args) {
        ProdutoModel produtoModel = new ProdutoModel();

        Produto p1 = new Produto();
        p1.setNome("TV");
        p1.setPreco(300.0);
        p1.setQuantidade(100);
        p1.setStatus(true);

        // 1) Criando um produto
        System.out.println("1 - Criando um produto");
        produtoModel.create(p1);

        //2) Buscando todos os produtos na base de dados
        System.out.println("2 - Buscando por todos os produtos");
        List<Produto> produtos = produtoModel.findAll();
        System.out.println("Qtde de produtos encontrados : " + produtos.size());

        //3) Buscando um produto específico por seu ID
        System.out.println("3 - Buscando produto por ID");
        Produto copiaP1 = produtoModel.findById(0L);

        //4) Atualizando um produto
        System.out.println("4 - Atualizando um produto");
        Produto produtoParaAtualizar = produtos.get(0);
        produtoParaAtualizar.setNome("Novo nome");
        produtoModel.update(produtoParaAtualizar);

        //5) Deletando produto
        System.out.println("5 - Deletando um produto");
        produtoModel.delete(p1);


        /*
         *metodos crud Pessoa
         *
         */
        PessoaModel pessoaModel = new PessoaModel();
        // 1) Criando pessoas
        System.out.println("\n1. Criando pessoas...");

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("João Silva");
        pessoa1.setEmail("joao.silva@email.com");
        pessoa1.setIdade(30);
        pessoa1.setCpf("12345678901");
        pessoa1.setDataDeNascimento(LocalDate.of(1993, 5, 15));
        pessoaModel.create(pessoa1);

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Maria Santos");
        pessoa2.setEmail("maria.santos@email.com");
        pessoa2.setIdade(25);
        pessoa2.setCpf("98765432100");
        pessoa2.setDataDeNascimento(LocalDate.of(1998, 12, 3));
        pessoaModel.create(pessoa2);

        Pessoa pessoa3 = new Pessoa();
        pessoa3.setNome("Pedro Costa");
        pessoa3.setEmail("pedro.costa@email.com");
        pessoa3.setIdade(35);
        pessoa3.setCpf("11122233344");
        pessoa3.setDataDeNascimento(LocalDate.of(1988, 8, 20));
        pessoaModel.create(pessoa3);

        // 2) Buscando todas as pessoas
        System.out.println("\n2. Buscando todas as pessoas...");
        List<Pessoa> pessoas = pessoaModel.findAll();
        for (Pessoa pessoa : pessoas) {
            System.out.println("- " + pessoa);
        }

        // 3) Buscando pessoa por ID
        System.out.println("\n3. Buscando pessoa por ID...");
        if (!pessoas.isEmpty()) {
            Long idPrimeiraPessoa = pessoas.get(0).getId();
            Pessoa pessoaEncontrada = pessoaModel.findById(idPrimeiraPessoa);
            System.out.println("Pessoa encontrada: " + pessoaEncontrada);
        }

        // 4) Atualizando pessoa
        System.out.println("\n4. Atualizando pessoa...");
        if (!pessoas.isEmpty()) {
            Pessoa pessoaParaAtualizar = pessoas.get(0);
            pessoaParaAtualizar.setIdade(31);
            pessoaParaAtualizar.setEmail("joao.silva.novo@email.com");
            pessoaModel.update(pessoaParaAtualizar);
        }

        // 5) Deletando pessoa
        System.out.println("\n5. Deletando pessoa...");
        if (pessoas.size() > 2) {
            Pessoa pessoaParaDeletar = pessoas.get(2);
            pessoaModel.delete(pessoaParaDeletar);
        }

        // Verificação final
        System.out.println("\n--- VERIFICAÇÃO FINAL ---");
        System.out.println("Produtos restantes: " + produtoModel.findAll().size());
        System.out.println("Pessoas restantes: " + pessoaModel.findAll().size());

        System.out.println("\n=================================");
        System.out.println("   TESTES CONCLUÍDOS COM SUCESSO!");
        System.out.println("=================================");
    }
}
