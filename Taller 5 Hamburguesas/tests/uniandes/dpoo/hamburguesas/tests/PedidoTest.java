package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

class PedidoTest {
	
	Producto producto1;
	Producto producto2;
	Pedido pedido1; 
	ArrayList<Producto>productos;
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		productos = new ArrayList<>();
		
		pedido1 = new Pedido("Tomas","Bogota");
		producto1 = new ProductoMenu("papas",10000);
		producto2 = new ProductoMenu("bebida",5000);
		pedido1.agregarProducto(producto1);
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetNombreCliente() {
		assertEquals("Tomas",pedido1.getNombreCliente(),"No se recibió el nombre del cliente esperado.");
	}
	@Test
	void testGetIdPedido() {
		assertEquals(1,pedido1.getIdPedido(),"No se recibió el ID esperado.");
	}
	
	@Test
	void testAgregarProducto() {
		pedido1.agregarProducto(producto2);
		assertEquals(producto2,pedido1.getProductos().get(1),"No se recibió el ID esperado.");
	}
	
	@Test
	void testGetPrecioTotal() {
		assertEquals(11900,pedido1.getPrecioTotalPedido(),"No se el precio total esperado.");
	}
	@Test
	void testGenerarTextoFactura() {
		pedido1.agregarProducto(producto2);
		String expected = "Cliente: Tomas\n" +
                "Dirección: Bogota\n" +
                "----------------\n" +
                "papas\n            10000\n" + 
                "bebida\n            5000\n" +
                "----------------\n" +
                "Precio Neto:  15000\n" +
                "IVA:          2850\n" +
                "Precio Total: 17850\n";
		
		assertEquals(expected,pedido1.generarTextoFactura(),"No se el texto de factura esperado.");
	}
	@Test
    public void testGuardarFactura() throws IOException {
        // Crear archivo temporal
        File archivoTemporal = File.createTempFile("factura", ".txt");
        archivoTemporal.deleteOnExit();  // Asegura que el archivo se elimine al final de la prueba

        // Ejecutar el método guardarFactura
        pedido1.guardarFactura(archivoTemporal);

        // Leer el contenido del archivo y compararlo con el texto esperado
        String contenidoArchivo = new String(Files.readAllBytes(archivoTemporal.toPath()));

        String expected = "Cliente: Tomas\n" +
                "Dirección: Bogota\n" +
                "----------------\n" +
                "papas\n            10000\n" + 
                "----------------\n" +
                "Precio Neto:  10000\n" +
                "IVA:          1900\n" +
                "Precio Total: 11900\n";

        assertEquals(expected, contenidoArchivo);
    }
	
}
