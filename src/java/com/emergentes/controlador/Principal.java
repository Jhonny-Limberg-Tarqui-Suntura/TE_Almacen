package com.emergentes.controlador;

import com.emergentes.modelo.Almacen;
import com.emergentes.modelo.AlmacenDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Principal", urlPatterns = {"/Principal"})
public class Principal extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        
        AlmacenDAO gestor = (AlmacenDAO) ses.getAttribute("gestor");
        
        if(gestor == null){
            
            AlmacenDAO auxi = new AlmacenDAO();
            ses.setAttribute("gestor", auxi);
        }
        String op = request.getParameter("op");
        if(op == null){
            op = "";
        }
        
        if(op.trim().equals("")){
            response.sendRedirect("vista/listado.jsp");
        }
        if(op.trim().equals("nuevo")){
            ses = request.getSession();
                
            Almacen obj = new Almacen();
                
            obj.setId(gestor.getCorrelativo()+1);
            request.setAttribute("item", obj);
            request.getRequestDispatcher("vista/nuevo.jsp").forward(request, response);
        }
        
        if(op.trim().equals("editar")){
            int pos = gestor.posicion(Integer.parseInt(request.getParameter("id")));
            Almacen obj = gestor.getAlm().get(pos);
            
            request.setAttribute("item", obj);
            request.getRequestDispatcher("vista/editar.jsp").forward(request, response);
        }
        
        if(op.trim().equals("eliminar")){
            int pos = gestor.posicion(Integer.parseInt(request.getParameter("id")));
            gestor.getAlm().remove(pos);
            
            response.sendRedirect("vista/listado.jsp");
        }
                
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        
        AlmacenDAO gestor = (AlmacenDAO) ses.getAttribute("gestor");
        Almacen obj = new  Almacen();
        
        gestor.setCorrelativo(gestor.getCorrelativo()+1);
        
        obj.setId(gestor.getCorrelativo());
        obj.setProducto(request.getParameter("producto"));
        obj.setPrecio(Integer.parseInt(request.getParameter("precio")));
        obj.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        
        String tipo = request.getParameter("tipo");
        
        if(tipo.equals("-1")){
            gestor.insertar(obj);
        } else{
            gestor.modificar(obj.getId(), obj);
        }
        response.sendRedirect("vista/listado.jsp");
        
    }

}
