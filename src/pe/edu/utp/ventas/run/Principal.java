package pe.edu.utp.ventas.run;

import pe.edu.utp.ventas.controller.ProductoController;
import pe.edu.utp.ventas.dao.ProductoDAO;
import pe.edu.utp.ventas.daoImpl.ProductoDaoImpl;
import pe.edu.utp.ventas.entity.Producto;
import pe.edu.utp.ventas.vista.ProductoForm;

/**
 *
 * @author Docente
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Producto mod = new Producto();
        ProductoDAO modC = new ProductoDaoImpl();
        ProductoForm frm = new ProductoForm();

        ProductoController ctrl = new ProductoController(mod, modC, frm);
        ctrl.iniciar();
        frm.setVisible(true);
    }
}
