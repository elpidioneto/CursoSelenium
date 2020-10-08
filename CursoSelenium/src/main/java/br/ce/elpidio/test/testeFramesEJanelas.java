package br.ce.elpidio.test;
import static br.ce.elpidio.core.DriverFactory.getDriver;
import static br.ce.elpidio.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.ce.elpidio.core.DSL;

public class testeFramesEJanelas {

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
	public void deveInteragirComFrame() {
		dsl.entrarFrame("frame1");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();

		Assert.assertEquals("Frame OK!", msg);
		dsl.sairFrame();
		dsl.escreve("elementosForm:nome", msg);
		Assert.assertEquals("Frame OK!", dsl.obterValueCampo("elementosForm:nome"));
	}
	@Test
	public void deveInteragirComFrameEscondido() {
		WebElement frame = getDriver().findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0,arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame2");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
	}

	@Test
	public void deveInteragirComJanelas() {

		dsl.clicarBotao("buttonPopUpEasy");
		dsl.trocarJanela("Popup");
		dsl.escreve(By.tagName("textarea"), "Deu Certo?");
		String escrito = dsl.obterValueElemento(By.tagName("textarea"));
		getDriver().close();
		dsl.trocarJanela("");
		dsl.escreve("elementosForm:sugestoes", escrito);

		Assert.assertEquals("Deu Certo?", dsl.obterValueCampo("elementosForm:sugestoes"));
	}

	@Test
	public void deveInteragirComJanelasSemTitulo() {
		dsl.clicarBotao("buttonPopUpHard");
		dsl.trocarJanela(getDriver().getWindowHandles().toArray()[1].toString());
		dsl.escreve(By.tagName("textarea"), "Deu Certo?");
		dsl.trocarJanela(getDriver().getWindowHandles().toArray()[0].toString());
		dsl.escreve(By.tagName("textarea"), "E agora?");
	}

}
