package pe.edu.utp.ventas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Docente
 */
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Producto {

    private int id;
    private String codigo;
    private String nombre;
    private Double precio;
    private int cantidad;

}
