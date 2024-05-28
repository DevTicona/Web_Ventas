package com.emergentes.dao;

import com.emergentes.modelo.Venta;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO_impl extends ConexionDB implements VentaDAO {

    @Override
    public void insertar(Venta venta) throws Exception {
        try {
            this.conectar();
            String insert = "insert into ventas (producto_id, cliente_id, fecha) values (?,?,?)";
            PreparedStatement ps = this.conn.prepareStatement(insert);
            ps.setInt(1, venta.getProducto_id());
            ps.setInt(2, venta.getCliente_id());
            ps.setDate(3, venta.getFecha());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void actualizar(Venta venta) throws Exception {
        try {
            this.conectar();
            String update = "update ventas set producto_id=?, cliente_id=?, fecha=? where id=?";
            PreparedStatement ps = this.conn.prepareStatement(update);
            ps.setInt(1, venta.getProducto_id());
            ps.setInt(2, venta.getCliente_id());
            ps.setDate(3, venta.getFecha());
            ps.setInt(4, venta.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        try {
            this.conectar();
            String delete = "delete from ventas where id=?";
            PreparedStatement ps = this.conn.prepareStatement(delete);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Venta obtenerId(int id) throws Exception {
        Venta ve = new Venta();
        try {
            this.conectar();
            String obtener = "SELECT * FROM ventas where id=?";
            PreparedStatement ps = this.conn.prepareStatement(obtener);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ve.setId(rs.getInt("id"));
                ve.setProducto_id(rs.getInt("producto_id"));
                ve.setCliente_id(rs.getInt("cliente_id"));
                ve.setFecha(rs.getDate("fecha"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return ve;
    }

    @Override
    public List<Venta> obtenerTodo() throws Exception {
        List<Venta> lista = null;
        try {
            this.conectar();
            String obtenerTodo = "SELECT v.*,p.nombre as producto, c.nombre as cliente FROM ventas v";
                   obtenerTodo += "	LEFT JOIN productos p ON v.producto_id = p.id";
                   obtenerTodo += "	LEFT JOIN clientes c ON v.cliente_id = c.id";
            PreparedStatement ps = this.conn.prepareStatement(obtenerTodo);

            lista = new ArrayList<Venta>();

            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Venta ve = new Venta();
                ve.setId(rs.getInt("id"));
                ve.setProducto_id(rs.getInt("producto_id"));
                ve.setCliente_id(rs.getInt("cliente_id"));
                ve.setFecha(rs.getDate("fecha"));
                ve.setCliente(rs.getString("cliente"));
                ve.setProducto(rs.getString("producto"));
                lista.add(ve);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }

}
