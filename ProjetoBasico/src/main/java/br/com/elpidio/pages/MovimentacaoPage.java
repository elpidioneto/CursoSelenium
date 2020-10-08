package br.com.elpidio.pages;

import static br.com.elpidio.core.DriverFactory.getDriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.com.elpidio.core.BasePage;

public class MovimentacaoPage extends BasePage {

	public void setDataDaMovimentacao(String data) {
		escreve("data_transacao", data);
	}

	public void setDataDoPagamento(String data) {
		escreve("data_pagamento", data);
	}

	public void setDescricao(String descricao) {
		escreve("descricao", descricao);
	}

	public void setInteressado(String interessado) {
		escreve("interessado", interessado);
	}

	public void setValor(String valor) {
		escreve("valor", valor);
	}

	public void setConta(String conta) {
		selecionarCombo("conta", conta);
	}

	public void setSituacao(String situacao) {
		if (situacao.equals("pago"))
			clicarRadio("status_pago");

		if (situacao.equals("pendente"))
			clicarRadio("status_pendente");
	}

	public void salvar() {
		clicarBotaoPorTexto("Salvar");
	}
	
	public List<String> obterErros() {
		List<WebElement> erros = getDriver().findElements(By.xpath("//div[@class='alert alert-danger']//li"));
		List<String> retorno = new ArrayList<String>();
		for(WebElement erro : erros) {
			retorno.add(erro.getText());
		}
		return retorno;
	}
	
	public String obterMensagemSucesso() {
		return obterTexto(By.xpath("//div[@class='alert alert-success']"));
	}
}
