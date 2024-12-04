package authorizer.infrastructure.protocol;

import java.io.Serializable;

public class ISO8583Request0200 extends ISO8583 implements Serializable{

	private static final long serialVersionUID = 4692091011290915725L;
	
	private String numeroCartao;
	private String codigoProcessamento;
	private String dataVencimentoCartao;
	private String modoEntrada;
	private String codigoSeguranca;
	private String numeroParcelas;

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getCodigoProcessamento() {
		return codigoProcessamento;
	}

	public void setCodigoProcessamento(String codigoProcessamento) {
		this.codigoProcessamento = codigoProcessamento;
	}

	public String getDataVencimentoCartao() {
		return dataVencimentoCartao;
	}

	public void setDataVencimentoCartao(String dataVencimentoCartao) {
		this.dataVencimentoCartao = dataVencimentoCartao;
	}

	public String getModoEntrada() {
		return modoEntrada;
	}

	public void setModoEntrada(String modoEntrada) {
		this.modoEntrada = modoEntrada;
	}

	public String getCodigoSeguranca() {
		return codigoSeguranca;
	}

	public void setCodigoSeguranca(String codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}

	public String getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(String numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

}