package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

class ComboTest {
	
	ProductoMenu producto1;
	ProductoMenu producto2;
	ArrayList<ProductoMenu> items;
	Combo combo1;
	
	@BeforeEach
	void setUp() throws Exception {
		items = new ArrayList<>();
		
		producto1 = new ProductoMenu("papas",10000);
		producto2 = new ProductoMenu("bebida",5000);
		items.add(producto1);
		items.add(producto2);
		
		combo1 = new Combo("McCombo",0.2, items);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetNombre() {
		assertEquals("McCombo",combo1.getNombre(),"No se recibió el nombre esperado.");
	}
	
	@Test
	void testGetPrecio() {
		assertEquals(12000,combo1.getPrecio(),"No se recibió el precio esperado.");
	}
	
	@Test
	void testGenerarTextoFactura() {
		String expected = "Combo McCombo\n"
				+ " Descuento: 0.2\n"
				+ "            12000\n";
		assertEquals(expected,combo1.generarTextoFactura(),"No se recibió la factura esperada.");
	}

}
