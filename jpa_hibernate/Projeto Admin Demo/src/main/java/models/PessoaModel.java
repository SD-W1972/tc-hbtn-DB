package models;

import entities.Pessoa;
import entities.Produto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaModel {

    public void create(Pessoa p){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try{
            System.out.println("Iniciando transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Pessoa registrada com sucesso !!!");
        }catch(Exception e){
            em.close();
            System.err.println("Erro ao registrar a pessoa !!!" + e.getMessage());
        }finally{
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Pessoa findById(Long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Pessoa pessoa = null;
        try{
            System.out.println("Iniciando transação");
            em.getTransaction().begin();
            pessoa = em.find(Pessoa.class, id);
            if(pessoa == null){
                System.out.println("Pessoa com ID" + id + " nao encontrada no db");
            }
            System.out.println("Pessoa encontrada com sucesso !!!");
        }catch(Exception e){
            em.close();
            System.err.println("Erro ao procurar a pessoa !!!" + e.getMessage());
        }finally{
            em.close();
            emf.close();

            System.out.println("Finalizando a transação");
        }

        return pessoa;
    }

    public List<Pessoa> findAll(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        List<Pessoa> pessoas = new ArrayList<>();
        try{
            TypedQuery<Pessoa> query = em.createQuery("SELECT p from Pessoa p", Pessoa.class);
            pessoas = query.getResultList();
        }catch(Exception e){
            System.err.println("Erro ao buscar todos os produtos !!!" + e.getMessage());
        }finally{
            em.close();
            emf.close();
        }

        return pessoas;
    }

    public void update(Pessoa p){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            System.err.println("Erro ao atualizar a pessoa !!!" + e.getMessage());
        }finally{
            em.close();
            emf.close();
            System.out.println("Finalizando a transação de atualização");
        }
    }

    public void delete(Pessoa p){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            System.err.println("Erro ao deletar a pessoa !!!" + e.getMessage());
        }finally{
            em.close();
            emf.close();
        }

    }

}
