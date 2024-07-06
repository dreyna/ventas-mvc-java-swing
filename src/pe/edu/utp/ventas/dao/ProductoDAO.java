package pe.edu.utp.ventas.dao;

import java.util.List;
import pe.edu.utp.ventas.entity.Producto;

/**
 *
 * @author Docente
 */
public interface ProductoDAO {
    public boolean createProducto(Producto p);
    public boolean updateProducto(Producto p);
    public boolean deleteProducto(Producto p);
    public boolean readProducto(Producto p);
    public List<Producto> readAllProducto();
}
