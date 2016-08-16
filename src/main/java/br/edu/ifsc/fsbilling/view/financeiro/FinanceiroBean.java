package br.edu.ifsc.fsbilling.view.financeiro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.edu.ifsc.fsbilling.business.cdr.CdrManagerBean;
import br.edu.ifsc.fsbilling.business.usuario.UsuarioManagerBean;
import br.edu.ifsc.fsbilling.dto.Extrato;
import br.edu.ifsc.fsbilling.entity.LogCredito;
import br.edu.ifsc.fsbilling.entity.Usuario;
import br.edu.ifsc.fsbilling.view.AbstractBean;

@Named(value = "financeiroBean")
@ViewScoped
public class FinanceiroBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	CdrManagerBean cdrManager;

	@EJB
	UsuarioManagerBean usuarioManager;

	private StreamedContent file;
	private Usuario usuario;
	private String initTime;
	private String endTime;
	private List<Usuario> usuarios;

	private static final String TEMP = "/tmp/";
	private static final String EXTENSION = ".csv";

	@PostConstruct
	public void postConstruct() {
		setUsuarios(usuarioManager.listAll());
	}

	public StreamedContent downloadCSV() {
		try {
			String[] parseInit = initTime.split("/"); 
			String[] parseEnd = endTime.split("/"); 
			Calendar initDate = new GregorianCalendar(Integer.parseInt(parseInit[2]), Integer.parseInt(parseInit[1]), Integer.parseInt(parseInit[0]), 0, 0, 0);
			Calendar endDate = new GregorianCalendar(Integer.parseInt(parseEnd[2]), Integer.parseInt(parseEnd[1]), Integer.parseInt(parseEnd[0]), 23, 59, 59);
			InputStream is = new FileInputStream(generateCsvFile(usuario, initDate.getTime(), endDate.getTime()));
			file = new DefaultStreamedContent(is);
			return file;
		} catch (FileNotFoundException e) {
			return null;
		}

	}

	private String generateCsvFile(Usuario usuario, Date initTime, Date endTime) {
		try {
			Double random = Math.random();
			String[] randomString = random.toString().split(".");
			String fileName = TEMP + usuario.getCallerId() + randomString + EXTENSION;
			FileWriter writer = new FileWriter(fileName);

			List<Extrato> extratos = cdrManager.listAllExtrato(usuario, initTime, endTime);
			writer.append(new Extrato().getTitle());
			for (Extrato extrato : extratos) {
				writer.append("\n");
				writer.append(extrato.toString());
			}

			writer.flush();
			writer.close();
			return fileName;
		} catch (Exception e) {
			return null;
		}
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getInitTime() {
		return initTime;
	}

	public void setInitTime(String initTime) {
		this.initTime = initTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
