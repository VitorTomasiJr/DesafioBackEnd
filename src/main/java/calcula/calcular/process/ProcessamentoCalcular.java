package calcula.calcular.process;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.ArrayList;
import java.util.List;

import calcula.calcular.JSON.JSONCalcular;
import calcula.calcular.JSON.JSONRetorno;
import calcula.calcular.JSON.JSONValor;

import calcula.calcular.PagSeguro.PagSeguroIntegracao;

public class ProcessamentoCalcular {

	public static List<JSONRetorno> Calcular(JSONCalcular json) throws IOException {

		// DecimalFormat df = new DecimalFormat("#.##");
		double valorCalculo = 0d;
		double valorTotal = 0d;
		double valorFinal = 0d;
		JSONRetorno auxiliarRetorno;
		String linkRetorno = "";
		List<JSONValor> auxiliarInteracao = json.getValores();
		List<JSONRetorno> retorno = new ArrayList<>();

		if (json.getValores().size() <= 1) {
			return retorno;// tratar o retorno
		}

		for (JSONValor val : json.getValores()) {
			valorCalculo += val.getValor();
		}

		valorTotal = valorCalculo + json.getAcrescimoReais() - json.getDescontoReais();

		if (json.getDescontoPerc() > 0) {
			valorTotal -= (valorCalculo * (json.getDescontoPerc() / 100));

		}

		if (json.getAcrescimoPerc() > 0) {
			valorTotal += (valorCalculo * (json.getAcrescimoPerc() / 100));
		}

		for (int i = 0; i < auxiliarInteracao.size(); i++) {

			valorFinal = CalculoValorCobranca(auxiliarInteracao.get(i).getValor(), valorCalculo, valorTotal);
			linkRetorno = "";

			try {
				if (i != 0) {
					linkRetorno =  PagSeguroIntegracao.gerarLinkPagamento(valorFinal,
											 auxiliarInteracao.get(i).getNome());
				}
			} catch (Exception e) {
				linkRetorno = "";
			}

			auxiliarRetorno = new JSONRetorno();
			auxiliarRetorno.setNome(auxiliarInteracao.get(i).getNome());
			auxiliarRetorno.setValor(valorFinal);
			auxiliarRetorno.setLinkRet(linkRetorno);

			retorno.add(auxiliarRetorno);
		}

		return retorno;
	}

	public static double CalculoValorCobranca(double valorOriginal, double valorCalculoOriginal, double valorTotal) {
		BigDecimal valor = new BigDecimal(((valorOriginal / valorCalculoOriginal) * valorTotal));
		BigDecimal valorArredondado = valor.setScale(2, RoundingMode.HALF_UP);

		return valorArredondado.doubleValue();
	}
}
