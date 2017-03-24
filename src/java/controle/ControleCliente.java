/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.ClienteDAO;

/**
 *
 * @author 11151104481
 */
@WebServlet(name = "ControleCliente", urlPatterns = {"/controle"})
public class ControleCliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        
        String acao = request.getParameter("acao");
        
        if(acao.equals("Cadastrar")){
            
            String nome = request.getParameter("txtNome");
        String telefone = request.getParameter("txtTelefone");
        String endereco = request.getParameter("txtEndereco");
        
        //instanciar o objeto e setar os atributos
        
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        cliente.setEndereco(endereco);
        
        //criar o obj DAO e acionar os metodos necessarios
        
        ClienteDAO dao = new ClienteDAO();
        dao.cadastrar(cliente);
        
        response.getWriter().println("Cliente Cadastrado: ");
        
        } else
            if(acao.equals("Listar")){
                
                ClienteDAO dao = new ClienteDAO();
                //o metodo listar vai devolver uma lista de clientes
                //chamada listaCliente
                List<Cliente> listaCliente =  dao.listar();
               
                //adicionar a lista na requisição
                //passei a listaCliente como requisição chamando ela de lista
                request.setAttribute("lista", listaCliente);
                
                //enviar o request com a lista para a jsp
                //esse obj é uma interface que precisa do construtor
                //getRequestDispatcher passando o atributo da jsp
                RequestDispatcher rd = request.getRequestDispatcher("/lista.jsp");
                //quem vai acionar toda essa ação é o forward
                rd.forward(request, response);
                
            } else
                if(acao.equals("Excluir")){
                    
                   int id = Integer.parseInt(request.getParameter("id"));
                   
                   Cliente cliente = new Cliente();
                   cliente.setId(id);
                   
                   ClienteDAO dao = new ClienteDAO();
                   
                   dao.Excluir(cliente);
                   
                    
                } else
                        if(acao.equals("Consultar")) {
                            
                            int id = Integer.parseInt(request.getParameter("id"));
                            Cliente cliente = new Cliente();
                            cliente.setId(id);
                                                        
                            ClienteDAO dao = new ClienteDAO();
                            
                            List<Cliente> consultarCliente =  dao.Consultar(cliente);
                            
                             request.setAttribute("consulta", consultarCliente);
                             
                            RequestDispatcher rd = request.getRequestDispatcher("/alterarCliente.jsp");
                              //quem vai acionar toda essa ação é o forward
                            rd.forward(request, response);

                        
                        } else
                            if(acao.equals("Alterar")) {
                               
                            int id = Integer.parseInt(request.getParameter("txtId"));
                            String nome = request.getParameter("txtNome");
                            String telefone = request.getParameter("txtTelefone");
                            String endereco = request.getParameter("txtEndereco");
                            
                            Cliente cliente = new Cliente();
                            cliente.setId(id);
                            cliente.setNome(nome);
                            cliente.setTelefone(telefone);
                            cliente.setEndereco(endereco);
                            
                            ClienteDAO dao = new ClienteDAO();
                            
                            dao.Alterar(cliente);
                            
                              PrintWriter out = response.getWriter();
      
                            
                            out.println("Cliente ALTERADO ");
                            
                            }
        
        
        //recuperar os parametros
        
        
        
                    
}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        processRequest(request, response);
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
