package br.ce.elpidio.test;
import static br.ce.elpidio.core.DriverFactory.getDriver;
import static br.ce.elpidio.core.DriverFactory.killDriver;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import br.ce.elpidio.core.DSL;

public class TesteCampoTreinamento {

	private DSL dsl;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();

	}

	@After
	public void finaliza() {
		killDriver();;
	}

	@Test
	public void testeTextField() {
		dsl.escreve("elementosForm:nome", "Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.obterValueCampo("elementosForm:nome"));

	}

	@Test

	public void deveInteragirComTextArea() {
		dsl.escreve("elementosForm:sugestoes", "teste\n\nasjdkfja\nUltima linha");
		
		Assert.assertEquals("teste\n\nasjdkfja\nUltima linha", dsl.obterValueCampo("elementosForm:sugestoes"));

	}

	@Test
	public void deveInteragirComRadioButton() {
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));

	}

	@Test
	public void deveInteragirComCheckBox() {
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:2"));
	}

	@Test
	public void deveInteragirComComboBox() {
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
	}

	@Test
	public void deveVerificarValoresCombo() {
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Superior"));
	}

	@Test
	public void deveVerificarValoresComboMultiplo() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());

		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));

	}

	@Test
	public void deveInteragirComBotoes() {
		// executando a ação
		dsl.clicarBotao("buttonSimple");
		// verificando a saída
		assertEquals("Obrigado!",dsl.obterValueCampo("buttonSimple"));

	}

	@Test
	public void deveInteragirComLinks() {
		// executando a ação
		dsl.clicarLinks("Voltar");

		assertEquals("Voltou!", dsl.obterTexto("resultado"));

	}

	@Test
	public void deveBuscarTextosNaPagina() {
		assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));

	}
	
	@Test
	public void testJavascript() {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		
		WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	
	@Test
	public void deveClicarBotaoTabela() {
		dsl.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
	}
}
