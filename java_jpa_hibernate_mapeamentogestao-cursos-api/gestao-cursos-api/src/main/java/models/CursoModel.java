package models;

import entities.Curso;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class CursoModel {

    public void create(Curso Curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(Curso);
            em.getTransaction().commit();
            System.out.println("Curso criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar um Curso !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Curso findById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        Curso Curso = null;

        try{
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            Curso = em.find(Curso.class, id);
            if(Curso == null){
                System.out.println("Curso com ID " + id + "não encontrada no banco de dados !!!");
            }else {
                System.out.println("Curso com id" + Curso.getId() + " encontrado com sucesso");
            }
        }catch(Exception e){
            em.close();
            System.err.println("Erro ao procurar Curso de ID " + id + " " + e.getMessage());
        }finally{
            em.close();
            emf.close();

            System.out.println("Finalizando a transação.");
        }
        return Curso;
    }

    public List<Curso> findAll() {
        List<Curso> Cursos = new ArrayList<>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try{
            System.out.println("Iniciando transação");
            em.getTransaction().begin();
            TypedQuery<Curso> query = em.createQuery("SELECT p from Curso p", Curso.class);
            Cursos = query.getResultList();
            System.out.println("Lista de Cursos retornada com sucesso");
        }catch(Exception e){
            em.close();
            System.err.println("Erro ao buscar  todas as pessoas " +  e.getMessage());
        }finally{
            em.close();
            emf.close();

            System.out.println("Finalizando a transação");
        }
        return Cursos;
    }

    public void update(Curso Curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try{
            System.out.println("Iniciando transação ");
            em.getTransaction().begin();
            em.merge(Curso);
            em.getTransaction().commit();
            System.out.println("Curso com ID " + Curso.getId() + " atualizado no banco de dados");
        }catch(Exception e){
            em.getTransaction().rollback();
            em.close();
            System.out.println("Erro ao fazer update do Curso com id " + Curso.getId() + " por favor tente " +
                    "novamente mais tarde");
        }finally{
            em.close();
            emf.close();

            System.out.println("Finalizando a transação");
        }
    }

    public void delete(Curso Curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try{
            System.out.println("Iniciando transação");
         if(Curso != null){
            em.getTransaction().begin();
            em.remove(Curso);
            em.getTransaction().commit();
            System.out.println("Curso com ID " + Curso.getId() + " deletado com sucesso");
        }else{
             System.out.println("Curso com ID " + Curso.getId() + " nao encontrado no banco de dados");

         }
         }catch(Exception e){
            em.getTransaction().rollback();
            em.close();
            System.out.println("Erro ao deletar Curso com ID " + Curso.getId());
        }finally{
            em.close();
            emf.close();
            System.out.println("Finalizando transação");
        }
    }
}
