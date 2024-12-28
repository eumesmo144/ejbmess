package com.gugawag.pdist.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gugawag.pdist.ejbs.UsuarioService;

@WebServlet(urlPatterns = {"/message.do"})
public class MensageServlet extends HttpServlet {

    @EJB(lookup = "java:module/usuarioService")
    private UsuarioService usuarioService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException { // Removido exceção desnecessária no método
        String oper = request.getParameter("oper");

        PrintWriter resposta = response.getWriter();

        try {
            if ("1".equals(oper)) { // Inserir Mensagem
                String texto = request.getParameter("mensagem");

                usuarioService.inserirMensagem(texto);
                resposta.println("A mensagem foi enviada com sucesso!");

            } else if ("2".equals(oper)) { // Listar Mensagens
                usuarioService.listarMensagens().forEach(msg -> {
                    resposta.println(msg.getMensagem());
                });
            }
        } catch (Exception e) {
            resposta.println("Erro: " + e.getMessage());
        }
    }
}