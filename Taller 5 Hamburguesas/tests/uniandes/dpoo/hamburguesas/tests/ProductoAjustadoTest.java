package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

class ProductoAjustadoTest {
	
	private ProductoMenu productoBase;
	private ProductoAjustado productoAjustado;
	
	@BeforeEach
	void setUp() throws Exception {
		productoBase = new ProductoMenu("papas",10000);
		productoAjustado = new ProductoAjustado(productoBase);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetNombre() {
		assertEquals("papas",productoAjustado.getNombre());
	}
	@Test
	void testGetPrecio() {
		assertEquals(10000,productoAjustado.getPrecio());
	}

}
