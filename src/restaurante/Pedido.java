package restaurante;

import java.util.ArrayList;

public class Pedido {
    private Usuario cliente;
    private ArrayList<Producto> productos;

    public Pedido(Usuario cliente, ArrayList<Producto> productos) {
        this.cliente = cliente;
        this.productos = productos;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public int calcularTotal() {
        int total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecio();
        }
        return total;
    }
}
