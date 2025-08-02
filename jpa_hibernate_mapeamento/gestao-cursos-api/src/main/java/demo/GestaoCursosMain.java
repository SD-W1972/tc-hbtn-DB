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

        List<Telefone> telefones = new ArrayList<>();
        telefones.add(t1);
        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(e1);
        List<Curso> cursos = new ArrayList<>();
        cursos.add(c1);
        List<Aluno> alunos = new ArrayList<>();
        alunos.add(a1);

        //adicionando relacoes entre as entidades mapeadas
        a1.setTelefones(telefones);
        a1.setEnderecos(enderecos);
        a1.setCursos(cursos);

        t1.setAluno(a1);
        e1.setAluno(a1);
        c1.setAlunos(alunos);

        p1.setCursos(cursos);
        mc1.setCurso(c1);
        c1.setMaterialCurso(mc1);
        c1.setProfessor(p1);
        try{
            //Create
            System.out.println("Criando aluno " + a1.getNomeCompleto());
            alunoModel.create(a1);
            System.out.println("Aluno criado");

            System.out.println("Criando curso " + c1.getNome());
            cursoModel.create(c1);
            System.out.println("Curso criado");

            //Read
            System.out.println("Buscando aluno pelo id  " + a1.getId());
            Aluno alunoBuscar = alunoModel.findById(a1.getId());
         if(alunoBuscar != null) {
             System.out.println("Aluno de ID " + a1.getId() + " e nome " + alunoBuscar.getNomeCompleto() + " encontrado");
         }else{
            System.out.println("Aluno com ID " + a1.getId() + " nao existe no banco de dados");
         }
            System.out.println("Buscando curso pelo id " + c1.getId());
            Curso cursoBuscar = cursoModel.findById(c1.getId());
          if(cursoBuscar != null) {
              System.out.println("Curso de ID 0 " + cursoBuscar.getNome() + " encontrado com sucesso");
          }else{
              System.out.println("Curso com ID " + c1.getId() + " nao existe no banco de dados");
          }
            System.out.println("Buscando todos os alunos");
            List<Aluno> todosAlunos = alunoModel.findAll();
            System.out.println("Sucesso !");
            for(Aluno a : todosAlunos){
                System.out.println("Aluno nº " + a.getId() + " de nome: " + a.getNomeCompleto() + " encontrado");
            }

            System.out.println("Buscando todos os cursos");
            List<Curso> todosCursos = cursoModel.findAll();
            System.out.println("Sucesso !");
            for(Curso c : todosCursos){
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
            c1.setNome("Curso dahorinha ");
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
