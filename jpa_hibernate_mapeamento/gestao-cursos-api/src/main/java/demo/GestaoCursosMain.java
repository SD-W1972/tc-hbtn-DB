package demo;

import entities.*;
import models.AlunoModel;
import models.CursoModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

        Professor p1 = new Professor(
                "Professor Maneiro",
                "matricula dahora",
                "professor@maneiro.com.mail.br"
        );

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

        List<Telefone> telefones = new ArrayList<>();
        telefones.add(t1);
        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(e1);
        List<Curso> cursos = new ArrayList<>();
        cursos.add(c1);
        List<Aluno> alunos = new ArrayList<>();
        alunos.add(a1);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(p1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        a1.setTelefones(telefones);
        a1.setEnderecos(enderecos);
        a1.setCursos(cursos);

        t1.setAluno(a1);
        e1.setAluno(a1);

        c1.setAlunos(alunos);
        c1.setProfessor(p1);
        c1.setMaterialCurso(mc1);
        mc1.setCurso(c1);

        p1.setCursos(cursos);

        try{
            System.out.println("Criando aluno " + a1.getNomeCompleto());
            alunoModel.create(a1);
            System.out.println("Aluno criado");

            System.out.println("Criando curso " + c1.getNome());
            cursoModel.create(c1);
            System.out.println("Curso criado");

            System.out.println("Buscando aluno pelo id 1 ");
            Aluno alunoBuscar = alunoModel.findById(1L);
            System.out.println("Aluno de ID 1 " + alunoBuscar.getNomeCompleto() + " encontrado");

            System.out.println("Buscando curso pelo id 1");
            Curso cursoBuscar = cursoModel.findById(1L);
            System.out.println("Curso de ID 1 " + cursoBuscar.getNome() + " encontrado com sucesso");

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
                System.out.println("Curso nº " + c.getId() + " de nome: " + c.getNome() + " encontrado");
            }

            System.out.println("Testando update do Aluno 1 (mudança de nome)");
            System.out.println("Nome atual: " + a1.getNomeCompleto());
            a1.setNomeCompleto("beltrano de tal");
            alunoModel.update(a1);
            System.out.println("Sucesso! Nome atualizado: " + a1.getNomeCompleto());

            System.out.println("Testando update do Curso 1 (mudança de nome)");
            System.out.println("Nome atual: " + c1.getNome());
            c1.setNome("Curso dahorinha");
            cursoModel.update(c1);
            System.out.println("Sucesso! Nome atualizado: " + c1.getNome());

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
        } finally {
            emf.close();
        }
    }
}