package com.emergentes.dao;

import com.emergentes.modelo.Producto;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO_impl extends ConexionDB implements ProductoDAO{

    @Override
    public void insertar(Producto producto) throws Exception {
        try {
            this.conectar();
            String insert = "insert into productos (nombre,descripcion,precio) values (?,?,?)";
            PreparedStatement ps = this.conn.prepareStatement(insert);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setFloat(3, producto.getPrecio());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
    }

    @Override
    public void actualizar(Producto producto) throws Exception {
        try {
            this.conectar();
            String update = "update productos set nombre=?,descripcion=?,precio=? where id=?";
            PreparedStatement ps = this.conn.prepareStatement(update);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setFloat(3, producto.getPrecio());
            ps.setInt(4, producto.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        try {
            this.conectar();
            String delete = "delete from productos where id=?";
            PreparedStatement ps = this.conn.prepareStatement(delete);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
    }

    @Override
    public Producto obtenerId(int id) throws Exception {
        Producto prod = new Producto();
        try {
            this.conectar();
            String obtener = "select * from productos where id=?";
            PreparedStatement ps = this.conn.prepareStatement(obtener);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                prod.setId(rs.getInt("id"));
                prod.setNombre(rs.getString("nombre"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setPrecio(rs.getFloat("precio"));
            }
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
        return prod;
    }

    @Override
    public List<Producto> obtenerTodo() throws Exception {
        List<Producto> lista = null;
        try {
            this.conectar();
            String obtenerTodo = "select * from productos";
            PreparedStatement ps = this.conn.prepareStatement(obtenerTodo);
            
            lista = new ArrayList<Producto>();
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Producto prod = new Producto();
                prod.setId(rs.getInt("id"));
                prod.setNombre(rs.getString("nombre"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setPrecio(rs.getFloat("precio"));
                
                lista.add(prod);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally{
            this.desconectar();
        }
        return lista;
    }
}
