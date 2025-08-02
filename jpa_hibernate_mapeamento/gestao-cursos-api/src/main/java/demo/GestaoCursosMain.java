package demo;

import entities.*;
import models.AlunoModel;
import models.CursoModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GestaoCursosMain {
    public static void main(String[] args){
        AlunoModel alunoModel = new AlunoModel();
        CursoModel cursoModel = new CursoModel();
        Date d1 = new Date();
        Calendar calendar = Calendar.getInstance();
        d1 = calendar.getTime();

        Aluno a1 = new Aluno("Fulaninho de Tal",
                "00001", d1,
                "fulaninho@mailcom");

        Telefone t1 = new Telefone(
                "11",
                "9999-0000"
        );
        Endereco e1 = new Endereco(
                "logradouro",
                "endereco",
                "numero",
                "bairro",
                "cidade",
                "estado",
                1230123
        );
        Curso c1 = new Curso(
                "Curso Maneiro",
                "CM"
        );

        MaterialCurso mc1 = new MaterialCurso(
                "https:://link.redirect-mc1.com"
        );

        Professor p1 = new Professor(
              "Professor Maneiro",
              "matricula dahora",
              "professor@maneiro.com.mail.br"
        );

        try{
            //Create
            System.out.println("Criando aluno " + a1.getNomeCompleto());
            alunoModel.create(a1);
            System.out.println("Aluno criado");

            System.out.println("Criando curso " + c1.getNome());
            cursoModel.create(c1);
            System.out.println("Curso criado");

            //Read
            System.out.println("Buscando aluno pelo id 0 ");
            Aluno alunoBuscar = alunoModel.findById(0L);
            System.out.println("Aluno de ID 0 " + alunoBuscar.getNomeCompleto() + " encontrado");

            System.out.println("Buscando curso pelo id 0");
            Curso cursoBuscar = cursoModel.findById(0L);
            System.out.println("Curso de ID 0 " + cursoBuscar.getNome() + " encontrado com sucesso");

            System.out.println("Buscando todos os alunos");
            List<Aluno> alunos = alunoModel.findAll();
            System.out.println("Sucesso !");
            for(Aluno a : alunos){
                System.out.println("Aluno nº " + a.getId() + " de nome: " + a.getNomeCompleto() + " encontrado");
            }

            System.out.println("Buscando todos os cursos");
            List<Curso> cursos = cursoModel.findAll();
            System.out.println("Sucesso !");
            for(Curso c : cursos){
                System.out.println("Aluno nº " + c.getId() + " de nome: " + c.getNome() + " encontrado");
            }

            //Update

            System.out.println("Testando update do Aluno 0 (mudança de nome)");
            System.out.println("Nome atual: " + a1.getNomeCompleto());
            a1.setNomeCompleto("beltrano de tal");
            alunoModel.update(a1);
            System.out.println("Sucesso! Nome atualizado: " + a1.getNomeCompleto());

            System.out.println("Testando update do Curso 0 (mudança de nome)");
            System.out.println("Nome atual: " + c1.getNome());
            a1.setNomeCompleto("Curso dahorinha ");
            cursoModel.update(c1);
            System.out.println("Sucesso! Nome atualizado: " + c1.getNome());

            //Delete
            System.out.println("Lista atual de alunos: " + alunoModel.findAll());
            System.out.println("Deletando um aluno da DB");

            alunoModel.delete(a1);
            System.out.println("Aluno deletado. Lista atual de alunos: " + alunoModel.findAll());

            System.out.println("Lista atual de cursos: " + cursoModel.findAll());
            System.out.println("Deletando um curso da DB");

            cursoModel.delete(c1);
            System.out.println("Curso deletado. Lista atual de cursos: " + cursoModel.findAll());

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
