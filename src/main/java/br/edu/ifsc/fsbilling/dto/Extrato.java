package br.edu.ifsc.fsbilling.dto;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.ifsc.fsbilling.entity.FreeswitchCDR;
import br.edu.ifsc.fsbilling.entity.Usuario;

public class Extrato {

	private Usuario usuario;
	private BigDecimal valorTotal;
	private FreeswitchCDR cdr;

	public Extrato() {
		super();
	}

	public Extrato(Usuario usuario, BigDecimal valorTotal, FreeswitchCDR cdr) {
		super();
		this.usuario = usuario;
		this.valorTotal = valorTotal;
		this.cdr = cdr;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public FreeswitchCDR getCdr() {
		return cdr;
	}

	public void setCdr(FreeswitchCDR cdr) {
		this.cdr = cdr;
	}

	@Override
	public String toString() {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(usuario.getNomeCompleto());
		strBuffer.append(";");
		strBuffer.append(usuario.getUsername());
		strBuffer.append(";");
		strBuffer.append(usuario.getCallerId());
		strBuffer.append(";");
		strBuffer.append(cdr.getBillSec());
		strBuffer.append(";");
		strBuffer.append(valorTotal);
		strBuffer.append(";");
		return strBuffer.toString();
	}

	public String getTitle() {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("Nome completo");
		strBuffer.append(";");
		strBuffer.append("Username");
		strBuffer.append(";");
		strBuffer.append("CallerID");
		strBuffer.append(";");
		strBuffer.append("Tempo Cobrado (segundos)");
		strBuffer.append(";");
		strBuffer.append("Valor");
		strBuffer.append(";");
		return strBuffer.toString();
		
	}
	
}
