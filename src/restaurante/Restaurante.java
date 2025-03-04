package restaurante;

import java.util.ArrayList;
import java.util.Collections;

public class Restaurante {
    private ArrayList<Producto> productos;
    private ArrayList<Pedido> pedidos;
    private Reporte reporte = new Reporte();

    public Restaurante(ArrayList<Producto> productos, ArrayList<Pedido> pedidos, ArrayList<Usuario> usuarios) {
        this.productos = productos;
        this.pedidos = pedidos;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public ArrayList<Pedido> pedidosPorPrecio() {
        ArrayList<Pedido> pedidosCopia = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            pedidosCopia.add(pedido);
        }
        ArrayList<Pedido> pedidosOrdenados = ordenarPedidosPorPrecioHelper(pedidosCopia);
        Collections.reverse(pedidosOrdenados);
        return pedidosOrdenados;
    }

    private ArrayList<Pedido> ordenarPedidosPorPrecioHelper(ArrayList<Pedido> pedidos) {
        if (pedidos.size() <= 1) {
            return pedidos;
        }

        ArrayList<Pedido> izquierda = new ArrayList<>();
        ArrayList<Pedido> derecha = new ArrayList<>();

        for (int i = 0; i < pedidos.size(); i++) {
            if (i < pedidos.size() / 2) {
                izquierda.add(pedidos.get(i));
            } else {
                derecha.add(pedidos.get(i));
            }
        }

        izquierda = ordenarPedidosPorPrecioHelper(izquierda);
        derecha = ordenarPedidosPorPrecioHelper(derecha);

        return merge(izquierda, derecha);
    }

    private void updateResultado(ArrayList<Pedido> resultado, ArrayList<Pedido> pedidos, int i) {
        resultado.add(pedidos.get(i));
        pedidos.remove(i);
    }

    private ArrayList<Pedido> merge(ArrayList<Pedido> izquierda, ArrayList<Pedido> derecha) {
        ArrayList<Pedido> resultado = new ArrayList<>();

        while (izquierda.size() > 0 && derecha.size() > 0) {
            if (izquierda.get(0).calcularTotal() < derecha.get(0).calcularTotal()) {
                this.updateResultado(resultado, izquierda, 0);
            } else {
                this.updateResultado(resultado, derecha, 0);
            }
        }

        while (izquierda.size() > 0) {
            this.updateResultado(resultado, izquierda, 0);
        }

        while (derecha.size() > 0) {
            this.updateResultado(resultado, derecha, 0);
        }

        return resultado;
    }

    public static void main(String[] args) {
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Hamburguesa", 100));
        productos.add(new Producto("Papas", 50));
        productos.add(new Producto("Refresco", 30));
        productos.add(new Producto("Helado", 20));

        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Juan", "Calle 1", new ArrayList<Pedido>()));
        usuarios.add(new Usuario("Pedro", "Calle 2", new ArrayList<Pedido>()));

        ArrayList<Producto> productosPedido1 = new ArrayList<>();
        productosPedido1.add(productos.get(0));
        productosPedido1.add(productos.get(1));

        ArrayList<Producto> productosPedido2 = new ArrayList<>();
        productosPedido2.add(productos.get(2));
        productosPedido2.add(productos.get(3));

        ArrayList<Producto> productosPedido3 = new ArrayList<>();
        productosPedido3.add(productos.get(0));
        productosPedido3.add(productos.get(1));
        productosPedido3.add(productos.get(2));

        ArrayList<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido(usuarios.get(0), productosPedido1));
        pedidos.add(new Pedido(usuarios.get(0), productosPedido2));
        pedidos.add(new Pedido(usuarios.get(1), productosPedido3));

        ArrayList<Pedido> pedidosUsuario1 = new ArrayList<>();
        pedidosUsuario1.add(pedidos.get(0));
        pedidosUsuario1.add(pedidos.get(1));

        ArrayList<Pedido> pedidosUsuario2 = new ArrayList<>();
        pedidosUsuario2.add(pedidos.get(2));

        usuarios.get(0).setPedidos(pedidosUsuario1);
        usuarios.get(1).setPedidos(pedidosUsuario2);

        Restaurante restaurante = new Restaurante(productos, pedidos, usuarios);

        restaurante.reporte.generarReporte(restaurante);
        restaurante.reporte.generarReporte(usuarios.get(1));
    }
}
