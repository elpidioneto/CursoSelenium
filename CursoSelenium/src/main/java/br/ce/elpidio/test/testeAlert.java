package br.ce.elpidio.test;
import static br.ce.elpidio.core.DriverFactory.getDriver;
import static br.ce.elpidio.core.DriverFactory.killDriver;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ce.elpidio.core.DSL;

public class testeAlert {

	private DSL dsl;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}

	@After
	public void finaliza() {
		killDriver();
	}

	@Test
	public void deveInteragirComAlertSimples() {
		dsl.clicarBotao("alert");
		String texto = dsl.alertaObterTextoEAceita();
		assertEquals("Alert Simples", texto);
		dsl.escreve("elementosForm:nome",texto);

	}

	@Test
	public void deveInteragirComAlertConfirm() {
		// clicando em confirm
		dsl.clicarBotao("confirm");
		assertEquals("Confirm Simples", dsl.alertaObterTextoEAceita());
		assertEquals("Confirmado", dsl.alertaObterTextoEAceita());
		// clicando em cancel
		dsl.clicarBotao("confirm");
		dsl.alertaObterTextoENega();
		assertEquals("Negado", dsl.alertaObterTextoENega());
	}

	@Test
	public void deveInteragirComAlertPrompt() {
		dsl.clicarBotao("prompt");
		assertEquals("Digite um numero", dsl.alertaObterTexto());
		dsl.alertaEscrever("12");
		assertEquals("Era 12?", dsl.alertaObterTextoEAceita());
		assertEquals(":D", dsl.alertaObterTextoEAceita());
		// usando o cancel na confirmação
		dsl.clicarBotao("prompt");
		assertEquals("Digite um numero", dsl.alertaObterTexto());
		dsl.alertaEscrever("5");
		assertEquals("Era 5?", dsl.alertaObterTextoENega());
		assertEquals(":(", dsl.alertaObterTextoEAceita());

		// apertando cancel sem preencher nada
		dsl.clicarBotao("prompt");
		assertEquals("Digite um numero", dsl.alertaObterTextoENega());
		assertEquals("Era null?", dsl.alertaObterTextoEAceita());
		assertEquals(":D", dsl.alertaObterTexto());
	}
}
