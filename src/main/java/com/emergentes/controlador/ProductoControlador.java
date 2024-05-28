package com.emergentes.controlador;

import com.emergentes.dao.ProductoDAO;
import com.emergentes.dao.ProductoDAO_impl;
import com.emergentes.modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductoControlador", urlPatterns = {"/ProductoControlador"})
public class ProductoControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id;
            Producto prod = new Producto();
            ProductoDAO dao = new ProductoDAO_impl();
            String action = (request.getParameter("action") != null)? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    request.setAttribute("producto", prod);
                    request.getRequestDispatcher("frmproducto.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    prod = dao.obtenerId(id);
                    request.setAttribute("producto", prod);
                    request.getRequestDispatcher("frmproducto.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(id);
                    response.sendRedirect("ProductoControlador");
                    break;
                case "view":
                    List<Producto> listaProductos = dao.obtenerTodo();
                    request.setAttribute("productos", listaProductos);
                    request.getRequestDispatcher("productos.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        Float precio = Float.valueOf(request.getParameter("precio"));
        
        Producto prod = new Producto();
        ProductoDAO dao = new ProductoDAO_impl();
        
        prod.setId(id);
        prod.setNombre(nombre);
        prod.setDescripcion(descripcion);
        prod.setPrecio(precio);
        
        if (id == 0) {
            try {
                dao.insertar(prod);
            } catch (Exception ex) {
                System.out.println("Error al insertar "+ ex.getMessage());
            }
        }else{
            try {
                dao.actualizar(prod);
            } catch (Exception ex) {
                System.out.println("Error al modificar " + ex.getMessage());
            }
        }
        response.sendRedirect("ProductoControlador");
    }
}
