package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

class ProductoMenuTest {
	
	private ProductoMenu menu1;
	@BeforeEach
	void setUp() throws Exception {
		menu1 = new ProductoMenu("papas",10000);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetNombre() {
		assertEquals("papas",menu1.getNombre(),"El nombre del producto no era el esperado.");
	}
	@Test
	void testGetPrecio() {
		assertEquals(10000,menu1.getPrecio(),"El precio del producto no era el esperado.");
	}
	@Test
	void testGenerarTextoFactura() {
		assertEquals("papas\n            10000\n",menu1.generarTextoFactura(),"La factura no era la esperada.");
	}
}
