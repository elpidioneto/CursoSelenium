package br.ce.elpidio.suites;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.elpidio.core.DriverFactory;
import br.ce.elpidio.test.TesteCadastro;
import br.ce.elpidio.test.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({ TesteCadastro.class,
	TesteRegrasCadastro.class,
	})
public class SuiteTeste {

	@AfterClass
	public static void finalizaTudo() {
		DriverFactory.killDriver();
	}
}
