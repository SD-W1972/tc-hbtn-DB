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
                System.out.println("Aluno com ID " + id + "não encontrada no banco de dados !!!");
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
            em.getTransaction().begin();
            TypedQuery<Aluno> query = em.createQuery("SELECT p from Aluno p", Aluno.class);
            alunos = query.getResultList();
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


    public void delete(Aluno aluno) {

    }
}
