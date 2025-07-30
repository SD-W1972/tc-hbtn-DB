package models;

import entities.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ProdutoModel {
    public void create(Produto p){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try{
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Produto criado com sucesso !!!");
        }catch(Exception e){
            em.close();
            System.err.println("Erro ao criar o produto !!!" + e.getMessage());
        }finally{
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Produto findById(Long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        Produto produto = null;
        try{
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            produto = em.find(Produto.class, id);
            em.getTransaction().commit();
            System.out.println("Produto encontrado com sucesso !!!");
        }catch(Exception e){
            em.close();
            System.err.println("Erro ao procurar o produto !!!" + e.getMessage());
        }finally{
            em.close();
            System.out.println("Finalizando a transação");
        }
        
        return produto;
    }

    public List<Produto> findAll(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        List<Produto> produtos = new ArrayList<>();

        try{
            TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p", Produto.class);
            produtos = query.getResultList();
            System.out.println("Total de produtos encontrados: " + produtos.size());
        }catch(Exception e){
            System.err.println("Erro ao buscar todos os produtos !!! " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }

        return produtos;
    }

    public void update(Produto p){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try{
           em.getTransaction().begin();
           em.merge(p);
           em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            System.err.println("Erro ao atualizar o produto !!!" + e.getMessage());
        }finally{
            em.close();
            emf.close();
            System.out.println("Finalizando a transação de atualização");
        }
    }

    public void delete(Produto p){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            System.err.println("Erro ao deletar o produto !!!" + e.getMessage());
        }finally{
            em.close();
            emf.close();

        }

    }
}
