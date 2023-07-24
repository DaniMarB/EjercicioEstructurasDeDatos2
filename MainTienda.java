package TallerClase210723.RegistroProductos;


import java.util.*;

public class MainTienda {
    public static void main(String[] args) {
        List<Producto> productos = new ArrayList<>();
        Map<String,List<Producto>> productosPorCategoria = new HashMap<>();
        productos.add(new Producto(1,"Mouse",40,50));
        productos.add(new Producto(2,"Teclado",80,20));
        productos.add(new Producto(3,"Procesador",350,10));
        productos.add(new Producto(4,"USB",10,50));
        Scanner sc = new Scanner(System.in);
        System.out.println("¡Bienvenido!\n");
        int opcion = 0;
        while(opcion != 10){
            System.out.println("Menú principal\n");
            System.out.println("1.  Agregar nuevo producto");
            System.out.println("2.  Mostrar todos los productos");
            System.out.println("3.  Agregar un producto a una categoría");
            System.out.println("4.  Mostrar categorías y productos asociados");
            System.out.println("5.  Buscar producto por codigo");
            System.out.println("6.  Actualizar stock de producto");
            System.out.println("7.  Eliminar producto");
            System.out.println("8.  Calcular valor total de inventario");
            System.out.println("9.  Mostrar productos con precio mas bajo y mas alto");
            System.out.println("10. Salir del programa");
            System.out.println("\nSeleccione la accion que desea realizar por el número en el menú");
            opcion = sc.nextInt();
            switch (opcion){
                case 1:
                    agregarProducto(productos);
                    break;
                case 2:
                    mostrarProductos(productos);
                    break;
                case 3:
                    agregarProductoACategoria(productos);
                    break;
                case 4:
                    mostrarProductosPorCategorias(productosPorCategoria,productos);
                    break;
                case 5:
                    buscarProducto(productos);
                    break;
                case 6:
                    actualizarStock(productos);
                    break;
                case 7:
                    eliminarProducto(productos);
                    break;
                case 8:
                    valorTotalStock(productos);
                    break;
                case 9:
                    precioMayorYMenor(productos);
                    break;
                case 10:
                    System.out.println("¡Saliendo del programa!");
                    break;
                default:
                    System.out.println("Por favor ingresa una opcion valida");
            }
        }

    }

    public static void agregarProducto(List<Producto> productos){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa los datos del producto");
        System.out.println("Codigo:");
        Integer codigo = sc.nextInt();
        System.out.println("Nombre:");
        String nombre = sc.next();
        System.out.println("Precio:");
        Double precio = sc.nextDouble();
        System.out.println("Stock:");
        Integer stock = sc.nextInt();
        productos.add(new Producto(codigo,nombre,precio,stock));
        System.out.println("El empleado ha sido agregado");
    }

    public static void mostrarProductos(List<Producto> productos){
        System.out.println("Lista completa de productos: ");
        for(Producto prod: productos){
            System.out.println(prod.toString());

        }
        System.out.println("\n");
        System.out.println("--------------------------------");
    }

    public static void agregarProductoACategoria(List<Producto> productos){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa el codigo del producto");
        System.out.println("Codigo:");
        Integer codigo = sc.nextInt();
        System.out.println("Ingresa la categoría a asignar al producto");
        String categoria = sc.next();
        boolean codigoEncontrado = false;
        for(Producto prod: productos){
            if(codigo.equals(prod.getCodigo())){
                prod.setCategoria(categoria);
                System.out.println("Categoria asignada al producto "+prod.getNombre());
                codigoEncontrado = true;
            }
        }
        if(codigoEncontrado=false){
            System.out.println("El codigo del producto no fue encontrado en la base de datos");
        }

    }

    public static void mostrarProductosPorCategorias (Map<String,List<Producto>> productosPorCategoria, List<Producto> productos){
        for(Producto prod: productos){
            List<Producto> arrayCategoria;
            if(productosPorCategoria.containsKey(prod.getCategoria())){
                arrayCategoria = productosPorCategoria.get(prod.getCategoria());
            }else{
                arrayCategoria = new ArrayList<>();
            }
            arrayCategoria.add(prod);
            productosPorCategoria.put(prod.getCategoria(),arrayCategoria);
        }

        System.out.println("Lista de productos por categoria:");
        for(String categ: productosPorCategoria.keySet()){
            System.out.println(categ);
            List<Producto> arrayCategoria = productosPorCategoria.get(categ);
            for(Producto prod: arrayCategoria){
                System.out.println(prod.toString());
            }
            System.out.println("\n");
        }

    }

    public static void buscarProducto(List<Producto> productos){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa el codigo del producto");
        System.out.println("Codigo:");
        Integer codigo = sc.nextInt();
        boolean codigoEncontrado = false;
        for(Producto prod: productos){
            if(codigo.equals(prod.getCodigo())){
                System.out.println("Los datos del producto que buscas son los siguientes");
                System.out.println(prod.toString()+"\n");
                codigoEncontrado = true;
            }
        }
        if(codigoEncontrado==false){
            System.out.println("El codigo del producto no fue encontrado en la base de datos");
        }
    }

    public static void actualizarStock(List<Producto> productos){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa el codigo del producto");
        System.out.println("Codigo:");
        Integer codigo = sc.nextInt();
        System.out.println("Ingresa el nuevo valor de stock del producto");
        Integer stocknuevo = sc.nextInt();
        boolean empleadoEncontrado = false;
        for(Producto prod: productos){
            if(codigo.equals(prod.getCodigo())){
                prod.setStock(stocknuevo);
                System.out.println("Stock actualizado");
                empleadoEncontrado = true;
            }
        }
        if(empleadoEncontrado==false){
            System.out.println("El codigo del producto no fue encontrado en la base de datos, no fue posible cambiar el stock");
        }
    }

    public static void eliminarProducto(List<Producto> productos) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa el codigo del producto");
        System.out.println("Codigo:");
        Integer codigo = sc.nextInt();
        boolean codigoEncontrado = false;
        int cont = 0;
        for (Producto prod : productos) {
            if (codigo.equals(prod.getCodigo()) ) {
                productos.remove(cont);
                System.out.println("Producto eliminado de la base de datos");
                codigoEncontrado = true;
                break;
            }
            cont++;
        }
        if (codigoEncontrado == false) {
            System.out.println("El producto no fue encontrado en la base de datos");
        }
    }


    public static void valorTotalStock(List<Producto> productos){
        double total = 0;
        for(Producto prod: productos){
            total = total+(prod.getStock()*prod.getPrecio());
        }
        System.out.println("El valor total del inventario actual es de $"+total);
    }

    public static void precioMayorYMenor(List<Producto> productos){
        double mayor = 0;
        String productoMayor = null;
        double menor = 0;
        String productoMenor = null;
        for(int i = 0 ; i<productos.size();i++){
            if(i==0){
                productoMenor = productos.get(i).getNombre();
                productoMayor = productos.get(i).getNombre();
                mayor = productos.get(i).getPrecio();
                menor = productos.get(i).getPrecio();
            }else{
                if(productos.get(i).getPrecio()>mayor){
                    productoMayor = productos.get(i).getNombre();
                    mayor = productos.get(i).getPrecio();
                }
                if(productos.get(i).getPrecio()<menor){
                    productoMenor = productos.get(i).getNombre();
                    menor = productos.get(i).getPrecio();
                }
            }
        }
        System.out.println("El precio mas bajo es del producto "+ productoMenor+" y es de "+menor);
        System.out.println("El precio mas alto es del producto "+ productoMayor+" y es de "+mayor);
    }
}
