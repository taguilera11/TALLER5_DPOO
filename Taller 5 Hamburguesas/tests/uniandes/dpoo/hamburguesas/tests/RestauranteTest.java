package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

class RestauranteTest {
	
	Restaurante restaurante1;
	

	@BeforeEach
	void setUp() throws Exception {
		
		restaurante1 = new Restaurante();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
    public void testIniciarPedidoExitoso() throws YaHayUnPedidoEnCursoException {
        restaurante1.iniciarPedido("Tomas", "Bogota");
        Pedido pedido = restaurante1.getPedidoEnCurso();
        
        assertNotNull(pedido);
        assertEquals("Tomas", pedido.getNombreCliente());
    }
	 @Test
	    void testIniciarPedidoConPedidoEnCurso() {
	        assertDoesNotThrow(() -> restaurante1.iniciarPedido("Tomas", "Bogota"));

	        YaHayUnPedidoEnCursoException exception = assertThrows(
	            YaHayUnPedidoEnCursoException.class,
	            () -> restaurante1.iniciarPedido("Ana", "Calle 456")
	        );

	        assertEquals(
	            "Ya existe un pedido en curso, para el cliente Tomas así que no se puede crear un pedido para Ana",
	            exception.getMessage()
	        );
	    }
	 
	 
	 @Test
	 public void testCerrarYGuardarPedidoSinPedido() {
	     NoHayPedidoEnCursoException exception = assertThrows(
	         NoHayPedidoEnCursoException.class,
	         () -> restaurante1.cerrarYGuardarPedido()
	     );

	     assertNotNull(exception);
	 } 
	@Test
	void testCerrarYGuardarPedido() {
		assertDoesNotThrow(() -> restaurante1.iniciarPedido("Tomas", "Bogota"));
		assertDoesNotThrow(() -> restaurante1.cerrarYGuardarPedido());
	    // Aquí creamos un archivo temporal para evitar efectos secundarios
	    File archivo = new File("./facturas/factura_1.txt");
	    assertTrue(archivo.exists(), "El archivo de factura no fue creado con el nombre esperado");
	  
	    
	    // Verificamos que el pedido en curso ahora sea null
	    assertNull(restaurante1.getPedidoEnCurso());

	    // Limpieza
	    archivo.delete();
	}

	@Test
	void testGetPedidoEnCurso() {
		assertDoesNotThrow(() -> restaurante1.iniciarPedido("Tomas", "Bogota"));
		
		assertEquals("Tomas",restaurante1.getPedidoEnCurso().getNombreCliente(), "No se recibió el nombre del cliente del pedido en curso esperado.");
		
		
	}

	@Test
	void testGetPedidos() {
		assertDoesNotThrow(() -> restaurante1.iniciarPedido("Tomas", "Bogota"));
		assertDoesNotThrow(() -> restaurante1.cerrarYGuardarPedido());
		
		assertEquals(1,restaurante1.getPedidos().size());
	}


	@Test
	public void testCargarInformacionRestaurante() throws Exception {
		String ruta = System.getProperty("user.dir") + "/Taller 5 Hamburguesas/data/";
		
	    File archivoIngredientes = new File(ruta+"ingredientes.txt");
	    File archivoMenu = new File(ruta+"menu.txt");
	    File archivoCombos = new File(ruta+"combos.txt");

	    restaurante1.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);

	    // Verificamos que las listas no estén vacías después de la carga
	    assertFalse(restaurante1.getIngredientes().isEmpty());
	    assertFalse(restaurante1.getMenuBase().isEmpty());
	    assertFalse(restaurante1.getMenuCombos().isEmpty());
	}

}
