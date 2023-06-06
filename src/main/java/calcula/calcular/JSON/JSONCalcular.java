package calcula.calcular.JSON;

import java.util.List;

public class JSONCalcular {
	private double descontoPerc;
	private double acrescimoPerc;
	private double descontoReais;
	private double acrescimoReais;
	private List<JSONValor> valores;

	public double getDescontoPerc() {
		return descontoPerc;
	}

	public void setDescontoPerc(double descontoPerc) {
		this.descontoPerc = descontoPerc;
	}

	public double getAcrescimoPerc() {
		return acrescimoPerc;
	}

	public void setAcrescimoPerc(double acrescimoPerc) {
		this.acrescimoPerc = acrescimoPerc;
	}

	public double getDescontoReais() {
		return descontoReais;
	}

	public void setDescontoReais(double descontoReais) {
		this.descontoReais = descontoReais;
	}

	public double getAcrescimoReais() {
		return acrescimoReais;
	}

	public void setAcrescimoReais(double acrescimoReais) {
		this.acrescimoReais = acrescimoReais;
	}
	
	public List<JSONValor> getValores() {
		return valores;
	}

	public void setValores(List<JSONValor> valores) {
		this.valores = valores;
	}

}