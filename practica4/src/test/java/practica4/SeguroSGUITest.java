package practica4;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.unican.is2.seguros.SegurosGUI;

public class SeguroSGUITest {

	private FrameFixture demo;

	@Before
	public void setUp() {
		SegurosGUI gui = new SegurosGUI();
		demo = new FrameFixture(gui);
		gui.setVisible(true);
	}

	@After
	public void tearDown() {
		demo.cleanUp();
	}

	@Test
	public void testMinusvalia() {
		//comprobamos que funciona con el boton minusvalia desactivado
		demo.textBox("txtPotencia").deleteText();
		demo.textBox("txtPotencia").enterText("50");

		demo.textBox("txtFechaUltimoSiniestro").deleteText();
		demo.textBox("txtFechaUltimoSiniestro").enterText("10-01-2000");

		//el 0 corresponde con TODO_RIESGO
		demo.comboBox("comboCobertura").selectItem(0);

		//demo.radioButton("btnMinusvalia").click();

		demo.button("btnCalcular").click();

		demo.textBox("txtPrecio").requireText("1000.0");	
		
		//////////
		
		//comprobamos que funciona con el boton minusvalia activado

		demo.radioButton("btnMinusvalia").click();

		demo.button("btnCalcular").click();

		demo.textBox("txtPrecio").requireText("750.0");	
	}

	@Test
	public void testPotencia() {
		//comprobamos que funciona el campo potencia para un valor en el intervalo bajo (50)
		demo.textBox("txtPotencia").deleteText();
		demo.textBox("txtPotencia").enterText("50");

		demo.textBox("txtFechaUltimoSiniestro").deleteText();
		demo.textBox("txtFechaUltimoSiniestro").enterText("10-01-2000");

		//el 0 corresponde con TODO_RIESGO
		demo.comboBox("comboCobertura").selectItem(0);

		//demo.radioButton("btnMinusvalia").click();

		demo.button("btnCalcular").click();

		demo.textBox("txtPrecio").requireText("1000.0");
		
		//comprobamos que funciona el campo potencia para un valor intermedio (100)
		demo.textBox("txtPotencia").deleteText();
		demo.textBox("txtPotencia").enterText("100");

		//demo.radioButton("btnMinusvalia").click();

		demo.button("btnCalcular").click();

		demo.textBox("txtPrecio").requireText("1050.0");
		
		//comprobamos que funciona el campo potencia para un valor alto (120)
		demo.textBox("txtPotencia").deleteText();
		demo.textBox("txtPotencia").enterText("120");

		//demo.radioButton("btnMinusvalia").click();

		demo.button("btnCalcular").click();

		demo.textBox("txtPrecio").requireText("1200.0");			
	}
	
	@Test
	public void testCobertura() {
		//comprobamos que funciona el campo de cobertura con TODO_RIESGO
				demo.textBox("txtPotencia").deleteText();
				demo.textBox("txtPotencia").enterText("50");

				demo.textBox("txtFechaUltimoSiniestro").deleteText();
				demo.textBox("txtFechaUltimoSiniestro").enterText("10-01-2000");

				//el 0 corresponde con TODO_RIESGO
				demo.comboBox("comboCobertura").selectItem(0);

				demo.button("btnCalcular").click();

				demo.textBox("txtPrecio").requireText("1000.0");
				
				//comprobamos que funciona el campo de cobertura con TERCEROS_LUNAS


				demo.comboBox("comboCobertura").selectItem("TERCEROS_LUNAS");


				demo.button("btnCalcular").click();

				demo.textBox("txtPrecio").requireText("600.0");
				
				//comprobamos que funciona el campo de cobertura con TERCEROS


				demo.comboBox("comboCobertura").selectItem("TERCEROS");


				demo.button("btnCalcular").click();

				demo.textBox("txtPrecio").requireText("400.0");		
		
	}
}
