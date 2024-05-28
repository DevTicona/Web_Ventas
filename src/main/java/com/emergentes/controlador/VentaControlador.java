package com.emergentes.controlador;

import com.emergentes.dao.ClienteDAO;
import com.emergentes.dao.ClienteDAO_impl;
import com.emergentes.dao.ProductoDAO;
import com.emergentes.dao.ProductoDAO_impl;
import com.emergentes.dao.VentaDAO;
import com.emergentes.dao.VentaDAO_impl;
import com.emergentes.modelo.Cliente;
import com.emergentes.modelo.Producto;
import com.emergentes.modelo.Venta;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VentaControlador", urlPatterns = {"/VentaControlador"})
public class VentaControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id;
            Venta venta = new Venta();
            VentaDAO daoVenta = new VentaDAO_impl();
            
            ProductoDAO daoProducto = new ProductoDAO_impl();
            ClienteDAO daoCliente = new ClienteDAO_impl();
            List<Producto> listaProductos = null;
            List<Cliente> listaClientes = null;
            
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    listaProductos = daoProducto.obtenerTodo();
                    listaClientes = daoCliente.getAll();
                    request.setAttribute("listaProductos", listaProductos);
                    request.setAttribute("listaClientes", listaClientes);
                    
                    request.setAttribute("venta", venta);
                    request.getRequestDispatcher("frmventa.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    venta = daoVenta.obtenerId(id);
                    request.setAttribute("venta", venta);
                    listaProductos = daoProducto.obtenerTodo();
                    listaClientes = daoCliente.getAll();
                    request.setAttribute("listaProductos", listaProductos);
                    request.setAttribute("listaClientes", listaClientes);
                    request.getRequestDispatcher("frmventa.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    daoVenta.eliminar(id);
                    response.sendRedirect("VentaControlador");
                    break;
                case "view":
                    List<Venta> listaVentas = daoVenta.obtenerTodo();
                    request.setAttribute("ventas", listaVentas);
                    request.getRequestDispatcher("ventas.jsp").forward(request, response);
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
        int cliente_id = Integer.parseInt(request.getParameter("cliente_id"));
        int producto_id =Integer.parseInt(request.getParameter("producto_id"));
        Date fecha = Date.valueOf(request.getParameter("fecha"));
        
        Venta venta = new Venta();
        VentaDAO dao = new VentaDAO_impl();
        
        venta.setId(id);
        venta.setCliente_id(cliente_id);
        venta.setProducto_id(producto_id);
        venta.setFecha(fecha);
        
        if (id == 0) {
            try {
                dao.insertar(venta);
            } catch (Exception ex) {
                System.err.println("Error al insertar "+ ex.getMessage());
            }
        }else{
            try {
                dao.actualizar(venta);
            } catch (Exception ex) {
                System.out.println("Error al modificar "+ ex.getMessage());
            }
        }
        response.sendRedirect("VentaControlador");
    }
}
