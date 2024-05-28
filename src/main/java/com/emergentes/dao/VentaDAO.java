
package com.emergentes.dao;

import com.emergentes.modelo.Venta;
import java.util.List;

public interface VentaDAO {
    public void insertar(Venta venta) throws Exception;
    public void actualizar(Venta venta) throws Exception;
    public void eliminar(int id) throws Exception;
    public Venta obtenerId(int id) throws Exception;
    public List<Venta> obtenerTodo() throws Exception;
}
