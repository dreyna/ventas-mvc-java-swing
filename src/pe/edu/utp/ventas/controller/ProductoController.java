package pe.edu.utp.ventas.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import pe.edu.utp.ventas.dao.ProductoDAO;
import pe.edu.utp.ventas.entity.Producto;
import pe.edu.utp.ventas.vista.ProductoForm;

/**
 *
 * @author Docente
 */
public class ProductoController implements ActionListener {

    private final Producto modelo;
    private final ProductoDAO pdao;
    private final ProductoForm vista;

    public ProductoController(Producto modelo, ProductoDAO pdao, ProductoForm vista) {
        this.modelo = modelo;
        this.pdao = pdao;
        this.vista = vista;
        initialize();
    }
    private void initialize() {
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("Productos");
        vista.setLocationRelativeTo(null);
        vista.txtId.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnGuardar) {
            modelo.setCodigo(vista.txtCodigo.getText());
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setPrecio(Double.valueOf(vista.txtPrecio.getText()));
            modelo.setCantidad(Integer.parseInt(vista.txtCantidad.getText()));

            if (pdao.createProducto(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
                limpiar();
            }
        }

        if (e.getSource() == vista.btnModificar) {
            modelo.setId(Integer.parseInt(vista.txtId.getText()));
            modelo.setCodigo(vista.txtCodigo.getText());
            modelo.setNombre(vista.txtNombre.getText());
            modelo.setPrecio(Double.valueOf(vista.txtPrecio.getText()));
            modelo.setCantidad(Integer.parseInt(vista.txtCantidad.getText()));

            if (pdao.updateProducto(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar");
                limpiar();
            }
        }

        if (e.getSource() == vista.btnEliminar) {
            modelo.setId(Integer.parseInt(vista.txtId.getText()));

            if (pdao.deleteProducto(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }
        }

        if (e.getSource() == vista.btnBuscar) {
            modelo.setCodigo(vista.txtCodigo.getText());

            if (pdao.readProducto(modelo)) {
                vista.txtId.setText(String.valueOf(modelo.getId()));
                vista.txtCodigo.setText(modelo.getCodigo());
                vista.txtNombre.setText(modelo.getNombre());
                vista.txtPrecio.setText(String.valueOf(modelo.getPrecio()));
                vista.txtCantidad.setText(String.valueOf(modelo.getCantidad()));

            } else {
                JOptionPane.showMessageDialog(null, "No se encontro registro");
                limpiar();
            }
        }

        if (e.getSource() == vista.btnLimpiar) {
            limpiar();
        }
    }

    public void limpiar() {
        vista.txtId.setText(null);
        vista.txtCodigo.setText(null);
        vista.txtNombre.setText(null);
        vista.txtPrecio.setText(null);
        vista.txtCantidad.setText(null);
    }

}
