
package com.emergentes.dao;

import com.emergentes.modelo.Producto;
import java.util.List;

public interface ProductoDAO {
    public void insertar(Producto producto) throws Exception;
    public void actualizar(Producto producto) throws Exception;
    public void eliminar(int id) throws Exception;
    public Producto obtenerId(int id) throws Exception;
    public List<Producto> obtenerTodo() throws Exception;
}
