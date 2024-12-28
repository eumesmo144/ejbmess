package com.gugawag.pdist.ejbs;

import com.gugawag.pdist.model.Mensagem;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class MensagemDAO {

    @PersistenceContext(unitName = "bd2")
    private EntityManager em2;

    public void inserir(Mensagem novaMensagem) throws Exception {
        em2.persist(novaMensagem);
    }

    @SuppressWarnings("unchecked")
    public List<Mensagem> listar() {
        return em2.createQuery("FROM Mensagem").getResultList();
    }
}