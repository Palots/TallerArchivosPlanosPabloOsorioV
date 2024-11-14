/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tallerarchivosplano;

/**
 *
 * @author osori
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoManager {

    private static final String FILE_NAME = "productos.txt";

    public static void guardarProducto(Producto producto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(producto.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Producto> leerProductos() {
        List<Producto> productos = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Producto producto = Producto.fromString(line);
                productos.add(producto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productos;
    }

    public static void actualizarProductos(List<Producto> productos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Producto producto : productos) {
                writer.write(producto.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
