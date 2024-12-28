package com.gugawag.pdist.ejbs;

import com.gugawag.pdist.model.Mensagem;
import com.gugawag.pdist.model.Usuario;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless(name = "usuarioService")
@Remote
public class UsuarioService {

    @EJB
    private UsuarioDAO usuarioDao;

    @EJB
    private MensagemDAO mensagemDao;

    public List<Usuario> listar() {
        return usuarioDao.listar();
    }

    public void inserir(long id, String nome) {
        Usuario novoUsuario = new Usuario(id, nome);
        usuarioDao.inserir(novoUsuario);

//        mensagemDao.inserir(mensagem);
        if (id==3L) {
            throw new RuntimeException("Menor de idade não permitido!");
        }
        if (id == 4L) {
            novoUsuario.setNome(nome + " alterado");
        }
    }

    public void inserirMensagem(String mensagem){
        for (String palavra : mensagem.split(" ")) {
            if (palavra.length() > 10 ) {
                String message = String.format("Você escreveu o seguinte palavrão: %s \n", palavra);
                throw new RuntimeException(message +"Escreva palavras com até  10 caracteres");
            }
        }
        Mensagem novaMensagem = new Mensagem();
        novaMensagem.setMensagem(mensagem);

    }

    public List<Mensagem> listarMensagens() {
        return mensagemDao.listar();
    }
}