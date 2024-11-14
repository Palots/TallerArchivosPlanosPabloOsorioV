/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tallerarchivosplano;

/**
 *
 * @author osori
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RegistroProductos extends JFrame {

    private JTextField txtCodigo, txtNombre, txtPrecio, txtCategoria;
    private JTable tableProductos;
    private DefaultTableModel model;

    public RegistroProductos() {
        setTitle("Registro de Productos");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(20, 20, 80, 25);
        add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(100, 20, 120, 25);
        add(txtCodigo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 60, 80, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 60, 120, 25);
        add(txtNombre);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(20, 100, 80, 25);
        add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(100, 100, 120, 25);
        add(txtPrecio);

        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setBounds(20, 140, 80, 25);
        add(lblCategoria);

        txtCategoria = new JTextField();
        txtCategoria.setBounds(100, 140, 120, 25);
        add(txtCategoria);

        JButton btnAgregar = new JButton("Agregar Producto");
        btnAgregar.setBounds(250, 20, 150, 30);
        add(btnAgregar);

        JButton btnEditar = new JButton("Editar Producto");
        btnEditar.setBounds(250, 60, 150, 30);
        add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar Producto");
        btnEliminar.setBounds(250, 100, 150, 30);
        add(btnEliminar);

        tableProductos = new JTable();
        model = new DefaultTableModel(new String[]{"Código", "Nombre", "Precio", "Categoría"}, 0);
        tableProductos.setModel(model);
        JScrollPane scrollPane = new JScrollPane(tableProductos);
        scrollPane.setBounds(20, 200, 550, 200);
        add(scrollPane);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarProducto();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        cargarProductos();
    }

    private void agregarProducto() {
        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        double precio = Double.parseDouble(txtPrecio.getText());
        String categoria = txtCategoria.getText();

        Producto producto = new Producto(codigo, nombre, precio, categoria);
        ArchivoManager.guardarProducto(producto);

        model.addRow(new Object[]{codigo, nombre, precio, categoria});

        limpiarCampos();
    }

    private void cargarProductos() {
        List<Producto> productos = ArchivoManager.leerProductos();
        for (Producto producto : productos) {
            model.addRow(new Object[]{producto.getCodigo(), producto.getNombre(), producto.getPrecio(), producto.getCategoria()});
        }
    }

    private void editarProducto() {
        int selectedRow = tableProductos.getSelectedRow();
        if (selectedRow >= 0) {
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            String categoria = txtCategoria.getText();

            Producto productoEditado = new Producto(codigo, nombre, precio, categoria);

            model.setValueAt(codigo, selectedRow, 0);
            model.setValueAt(nombre, selectedRow, 1);
            model.setValueAt(precio, selectedRow, 2);
            model.setValueAt(categoria, selectedRow, 3);

            List<Producto> productos = obtenerProductosDeTabla();
            productos.set(selectedRow, productoEditado);
            ArchivoManager.actualizarProductos(productos);

            limpiarCampos();
        }
    }

    private void eliminarProducto() {
        int selectedRow = tableProductos.getSelectedRow();
        if (selectedRow >= 0) {
            model.removeRow(selectedRow);

            List<Producto> productos = obtenerProductosDeTabla();
            ArchivoManager.actualizarProductos(productos);

            limpiarCampos();
        }
    }

    private List<Producto> obtenerProductosDeTabla() {
        List<Producto> productos = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String codigo = model.getValueAt(i, 0).toString();
            String nombre = model.getValueAt(i, 1).toString();
            double precio = Double.parseDouble(model.getValueAt(i, 2).toString());
            String categoria = model.getValueAt(i, 3).toString();
            productos.add(new Producto(codigo, nombre, precio, categoria));
        }
        return productos;
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtCategoria.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistroProductos().setVisible(true));
    }
}
