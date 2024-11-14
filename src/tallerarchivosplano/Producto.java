/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tallerarchivosplano;

/**
 *
 * @author osori
 */
public class Producto {

    private String codigo;
    private String nombre;
    private double precio;
    private String categoria;

    public Producto(String codigo, String nombre, double precio, String categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return codigo + "," + nombre + "," + precio + "," + categoria;
    }

    public static Producto fromString(String line) {
        String[] fields = line.split(",");
        String codigo = fields[0];
        String nombre = fields[1];
        double precio = Double.parseDouble(fields[2]);
        String categoria = fields[3];
        return new Producto(codigo, nombre, precio, categoria);
    }
}
