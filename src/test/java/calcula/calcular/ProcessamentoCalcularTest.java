package calcula.calcular;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import calcula.calcular.process.ProcessamentoCalcular;

public class ProcessamentoCalcularTest {

	@Test
	public void validaCalculo() {
		double valor = ProcessamentoCalcular.CalculoValorCobranca(42, 50, 38);
		
		Assertions.assertEquals(31.92, valor);
	}
}
