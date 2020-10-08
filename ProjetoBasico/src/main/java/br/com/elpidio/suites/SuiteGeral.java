package br.com.elpidio.suites;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.elpidio.core.DriverFactory;
import br.com.elpidio.pages.LoginPage;
import br.com.elpidio.tests.ContaTest;
import br.com.elpidio.tests.MovimentacaoTest;
import br.com.elpidio.tests.RemoverContaComMovimentacao;
import br.com.elpidio.tests.ResumoTest;
import br.com.elpidio.tests.SaldoTest;

@RunWith(Suite.class)
@SuiteClasses({
	ContaTest.class,
	MovimentacaoTest.class,
	RemoverContaComMovimentacao.class,
	SaldoTest.class,
	ResumoTest.class
})
public class SuiteGeral {
	private static LoginPage page = new LoginPage();
	@BeforeClass
	public static void reset() {
		page.acessarTelaInicial();
		
		page.setEmail("elpidio@test.com.br");
		page.setSenha("asdf");
		page.entrar();
		
		page.reset();
		
		DriverFactory.killDriver();
		
	}
	
}
