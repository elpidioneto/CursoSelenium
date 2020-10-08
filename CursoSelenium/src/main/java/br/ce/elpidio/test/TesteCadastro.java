package br.ce.elpidio.test;
import static br.ce.elpidio.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.elpidio.core.BaseTest;
import br.ce.elpidio.page.CampoTreinamentoPage;

public class TesteCadastro extends BaseTest {

	private CampoTreinamentoPage page;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage();

	}

	

	@Test
	public void preencherCadastroComSucesso() {
		page.setNome("Francelino");
		page.setSobrenome("Silva");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridade("Superior");
		page.setEsporte("Karate");
		page.cadastrar();

		Assert.assertEquals("Cadastrado!",page.obterResultadoCadastro());
		Assert.assertEquals("Francelino",page.obterNomeCadastro());
		Assert.assertEquals("Silva", page.obterSobrenomeCadastro());
		Assert.assertEquals("Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Pizza", page.obterComidaCadastro());
		Assert.assertEquals("superior", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Karate", page.obterEsporteCadastro());
	}
}
