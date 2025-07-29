package models;

import entities.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

    public void update(Produto p){

    }

}
