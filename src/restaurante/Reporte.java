package restaurante;

import java.util.ArrayList;

public class Reporte {

  public Reporte() {
  }

  private void generarReportePedidos(ArrayList<Pedido> pedidos) {
    for (Pedido pedido : pedidos) {
      generarReporte(pedido);
    }
  }

  public void generarReporte(Pedido pedido) {
    System.out.println("------------------------");
    System.out.println("Pedido de " + pedido.getCliente().getNombre());
    System.out.println("Productos:");
    for (Producto producto : pedido.getProductos()) {
      System.out.println(producto.getNombre() + " - " + producto.getPrecio());
    }
    System.out.println("Total: " + pedido.calcularTotal());
  }

  public void generarReporte(Restaurante restaurante) {
    int total = this.calcularTotal(restaurante.getPedidos());

    System.out.println("------------------------");
    System.out.println("El total de ventas para el restaurante es: " + total);
    ArrayList<Pedido> pedidosOrdenados = restaurante.pedidosPorPrecio();
    this.generarReportePedidos(pedidosOrdenados);
  }

  private int calcularTotal(ArrayList<Pedido> pedidos) {
    return pedidos.stream()
        .flatMap(pedido -> pedido.getProductos().stream())
        .mapToInt(Producto::getPrecio)
        .sum();
  }

  public void generarReporte(Usuario usuario) {
    int total = this.calcularTotal(usuario.getPedidos());

    System.out.println("------------------------");
    System.out.println("El total de compras para " + usuario.getNombre() + " es: " + total);
    ArrayList<Pedido> pedidosOrdenados = usuario.ordenarPedidosPorPrecio();
    generarReportePedidos(pedidosOrdenados);
  }
}
