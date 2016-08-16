package br.edu.ifsc.fsbilling.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "LOG_CREDITO")
@NamedQueries({ 
	@NamedQuery(name = LogCredito.SEARCH, query = "select lc from LogCredito lc"
			+ " left join fetch lc.admin a "
			+ " left join fetch lc.usuario u "
			+ " ORDER BY lc.id"),
	@NamedQuery(name = LogCredito.COUNT, query = "select count(lc) from LogCredito lc")
})
public class LogCredito extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	public static final String SEARCH = "LogCredito.SEARCH";
	public static final String COUNT = "LogCredito.COUNT";
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "VALOR_ADICIONADO")
	private BigDecimal valorAdicionado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ADMIN")
	private Administrador admin;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorAdicionado() {
		return valorAdicionado;
	}

	public void setValorAdicionado(BigDecimal valorAdicionado) {
		this.valorAdicionado = valorAdicionado;
	}

	public Administrador getAdmin() {
		return admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((valorAdicionado == null) ? 0 : valorAdicionado.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogCredito other = (LogCredito) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (valorAdicionado == null) {
			if (other.valorAdicionado != null)
				return false;
		} else if (!valorAdicionado.equals(other.valorAdicionado))
			return false;
		return true;
	}

}