package models;

import entities.Aluno;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoModel {

    public void create(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar um aluno !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Aluno findById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        Aluno aluno = null;

        try{
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            aluno = em.find(Aluno.class, id);
            if(aluno == null){
                System.out.println("Aluno com ID " + id + " não encontrado no banco de dados !!!");
            }else {
                System.out.println("Aluno " + aluno.getNomeCompleto() + " " + aluno.getId() + " encontrado com sucesso");
            }
        }catch(Exception e){
            em.close();
            System.err.println("Erro ao procurar Aluno de ID " + id + " " + e.getMessage());
        }finally{
            em.close();
            emf.close();

            System.out.println("Finalizando a transação.");
        }
        return aluno;
    }

    public List<Aluno> findAll() {
        List<Aluno> alunos = new ArrayList<>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try{
            System.out.println("Iniciando transação");
            em.getTransaction().begin();
            TypedQuery<Aluno> query = em.createQuery("SELECT p from Aluno p", Aluno.class);
            alunos = query.getResultList();
            System.out.println("Lista de alunos retornada com sucesso");
        }catch(Exception e){
            em.close();
            System.err.println("Erro ao buscar  todas as pessoas " +  e.getMessage());
        }finally{
            em.close();
            emf.close();

            System.out.println("Finalizando a transação");
        }
        return alunos;
    }

    public void update(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try{
            System.out.println("Iniciando transação ");
            em.getTransaction().begin();
        if(aluno != null) {
            em.merge(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno com ID " + aluno.getId() + " atualizado no banco de dados");
        }else{
            System.out.println("Aluno com ID " + aluno.getId() + " nao encontrado no banco de dados");
        }
        }catch(Exception e){
            em.getTransaction().rollback();
            em.close();
            System.out.println("Erro ao fazer update do aluno com id " + aluno.getId() + " por favor tente " +
                    "novamente mais tarde");
        }finally{
            em.close();
            emf.close();

            System.out.println("Finalizando a transação");
        }
    }

    public void delete(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try{
            System.out.println("Iniciando transação");
            if(aluno != null) {
                em.getTransaction().begin();
                em.remove(aluno);
                em.getTransaction().commit();
                System.out.println("Aluno com ID " + aluno.getId() + " deletado com sucesso");
            }else{
                System.out.println("Aluno com ID " + aluno.getId() + " nao encontrado no banco de dados");
            }
            }catch(Exception e){
            em.getTransaction().rollback();
            em.close();
            System.out.println("Erro ao deletar aluno com ID " + aluno.getId());
        }finally{
            em.close();
            emf.close();
            System.out.println("Finalizando transação");
        }
    }
}
