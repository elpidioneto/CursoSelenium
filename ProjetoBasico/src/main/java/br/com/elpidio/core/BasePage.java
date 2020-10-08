package br.com.elpidio.core;

import static br.com.elpidio.core.DriverFactory.getDriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {

	/********* TextField e TextArea ************/
	public void escreve(By by, String texto) {
		getDriver().findElement(by).clear();
		getDriver().findElement(by).sendKeys(texto);
	}

	public void escreve(String id_campo, String texto) {
		escreve(By.id(id_campo), texto);
	}

	public String obterValueElemento(By by) {
		return getDriver().findElement(by).getAttribute("value");
	}

	public String obterValueCampo(String id_campo) {
		return obterValueElemento(By.id(id_campo));
	}

	/********* Radio e Chek ************/

	public void clicarRadio(By by) {
		getDriver().findElement(by).click();
	}

	public void clicarRadio(String id) {
		clicarRadio(By.id(id));
	}

	public void clicarCheck(String id) {
		getDriver().findElement(By.id(id)).click();
	}

	public boolean isRadioMarcado(String id) {
		return getDriver().findElement(By.id(id)).isSelected();
	}

	public boolean isCheckMarcado(String id) {
		return getDriver().findElement(By.id(id)).isSelected();
	}

	/************** Combo ************/
	public void selecionarCombo(By by, String valor) {
		WebElement element = getDriver().findElement(by);
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}

	public void selecionarCombo(String id, String valor) {
		selecionarCombo(By.id(id), valor);
	}

	public void deselecionarCombo(String id, String opcao) {
		Select combo = new Select(getDriver().findElement(By.id(id)));
		combo.deselectByVisibleText(opcao);
	}

	public String obterValorCombo(By by) {
		WebElement element = getDriver().findElement(by);
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}

	public String obterValorCombo(String id) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}

	public List<String> obterValoresCombo(String id) {
		WebElement element = getDriver().findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);

		List<WebElement> allSelectOptions = combo.getAllSelectedOptions();
		List<String> lista = new ArrayList<String>();
		for (WebElement opcao : allSelectOptions) {
			lista.add(opcao.getText());
		}

		return lista;
	}

	public int obterQuantidadeOpcoesCombo(String id) {
		Select combo = new Select(getDriver().findElement(By.id(id)));
		List<WebElement> options = combo.getOptions();
		return options.size();
	}

	public boolean verificarOpcaoCombo(String id, String opcao) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for (WebElement option : options) {
			if (option.getText().contentEquals(opcao)) {
				return true;

			}
		}
		return false;
	}

	public void selecionarComboPrime(String radical, String valor) {
		clicarRadio(By.xpath(".//*[@id='" + radical + "_input']/../..//span"));
		clicarRadio(By.xpath(".//*[@id='" + radical + "_items']//li[.='" + valor + "']"));

	}

	/********* Botao ************/

	public void clicarBotao(By by) {
		getDriver().findElement(by).click();
	}
	
	public void clicarBotaoPorTexto(String texto) {
		clicarBotao(By.xpath("//button[.='"+texto+"']"));
	}

	public void clicarBotao(String id) {
		clicarBotao(By.id(id));
	}

	/********* Link ************/

	public void clicarLinks(String link) {
		getDriver().findElement(By.linkText(link)).click();
	}

	/********* Textos ************/

	public String obterTexto(By by) {

		return getDriver().findElement(by).getText();

	}

	public String obterTexto(String id) {

		return obterTexto(By.id(id));

	}

	/********* Alerts ************/

	public String alertaObterTexto() {
		Alert alert = getDriver().switchTo().alert();
		return alert.getText();
	}

	public String alertaObterTextoEAceita() {
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;

	}

	public String alertaObterTextoENega() {
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;

	}

	public void alertaEscrever(String valor) {
		Alert alert = getDriver().switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}

	/********* Frames e Janelas ************/

	public void entrarFrame(String id) {
		getDriver().switchTo().frame(id);
	}

	public void sairFrame() {
		getDriver().switchTo().defaultContent();
	}

	public void trocarJanela(String id) {
		getDriver().switchTo().window(id);
	}

	/************************ JS ***********************/

	public Object executarJS(String cmd, Object... params) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		return js.executeScript(cmd, params);
	}

	/************************ Tabelas ***********************/

	public WebElement obterCelula(String colunaBusca, String valor, String colunaBotao, String idTabela) {
		// procurar coluna do registro
		WebElement tabela = getDriver().findElement(By.xpath("//*[@id='" + idTabela + "']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);

		// encontrar a linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);

		// procurar coluna do botão
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);

		// clicar no botão da celula
		WebElement celula = tabela.findElement(By.xpath(".//tr[" + idLinha + "]/td[" + idColunaBotao + "]"));
		return celula;
	}

	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela) {
		// clicar no botão da celula
		WebElement celula = obterCelula(colunaBusca, valor, colunaBotao, idTabela);
		celula.findElement(By.xpath(".//input")).click();
	}

	protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td[" + idColuna + "]"));
		int idLinha = -1;
		for (int i = 0; i < linhas.size(); i++) {
			if (linhas.get(i).getText().equals(valor)) {
				idLinha = i + 1;
				break;
			}
		}
		return idLinha;
	}

	protected int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for (int i = 0; i < colunas.size(); i++) {
			if (colunas.get(i).getText().equals(coluna)) {
				idColuna = i + 1;
				break;
			}
		}
		return idColuna;
	}

}
