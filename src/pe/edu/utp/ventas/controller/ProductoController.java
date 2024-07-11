package pe.edu.utp.ventas.controller;

import com.google.gson.Gson;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pe.edu.utp.ventas.dao.ProductoDAO;
import pe.edu.utp.ventas.entity.Producto;
import pe.edu.utp.ventas.vista.ProductoForm;

/**
 *
 * @author Docente
 */
public class ProductoController implements ActionListener {

    private Producto modelo;
    private ProductoDAO pdao;
    private ProductoForm vista;
    private  DefaultTableModel dtmodel = new DefaultTableModel();
    private JTable tabla;
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
        listar();
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
                limpiarTabla();
                listar();
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
                limpiarTabla();
                listar();
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
                limpiarTabla();
                listar();
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
    public void listar(){
        Gson g = new Gson();
    dtmodel = (DefaultTableModel) vista.tbproductos.getModel();
    vista.tbproductos.setModel(dtmodel);
        List<Producto> lista = pdao.readAllProducto();
        System.out.println(g.toJson(lista));
        Object[] objeto = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = i+1;
            objeto[1] = lista.get(i).getId();
            objeto[2] = lista.get(i).getCodigo();
            objeto[3] = lista.get(i).getNombre();
            objeto[4] = lista.get(i).getPrecio();
            objeto[5] = lista.get(i).getCantidad();
            dtmodel.addRow(objeto);
        }
        vista.tbproductos.setModel(dtmodel);
    }

    private void limpiarTabla() {        
        for (int i = 0; i < vista.tbproductos.getRowCount(); i++) {
            dtmodel.removeRow(i);
            i = i - 1;
        }
    }


}
