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
@Table(name = "USUARIO")
@NamedQueries({ 
	@NamedQuery(name = Usuario.SEARCH, query = "select u from Usuario u left join fetch u.plano ORDER BY u.id"), 
	@NamedQuery(name = Usuario.COUNT, query = "select count(u) from Usuario u"),
	@NamedQuery(name = Usuario.SEARCH_BY_USERNAME, query = "select u from Usuario u left join fetch u.plano where u.username = :username"),
	@NamedQuery(name = Usuario.RETRIEVE_USER, query = "select u from Usuario u where upper(u.username) like upper(:filter) or upper(u.callerId) like upper(:filter) or upper(u.nomeCompleto) like upper(:filter)"),
	@NamedQuery(name = Usuario.LIST_ALL, query = "select u from Usuario u")
})
public class Usuario extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	public static final String SEARCH = "Usuario.SEARCH";
	public static final String COUNT = "Usuario.COUNT";
	public static final String SEARCH_BY_USERNAME = "Usuario.SEARCH_BY_USERNAME";
	public static final String RETRIEVE_USER = "Usuario.RETRIEVE_USER";
	public static final String LIST_ALL = "Usuario.LIST_ALL";
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "CALLER_ID")
	private String callerId;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "CREDIT")
	private BigDecimal credito;

	@Column(name = "NOME_COMPLETO")
	private String nomeCompleto;

	@Column(name = "EMAIL")
	private String email;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PLANO")
	private Plano plano;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCallerId() {
		return callerId;
	}

	public void setCallerId(String callerId) {
		this.callerId = callerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getCredito() {
		return credito;
	}

	public void setCredito(BigDecimal credito) {
		this.credito = credito;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((callerId == null) ? 0 : callerId.hashCode());
		result = prime * result + ((credito == null) ? 0 : credito.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomeCompleto == null) ? 0 : nomeCompleto.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Usuario other = (Usuario) obj;
		if (callerId == null) {
			if (other.callerId != null)
				return false;
		} else if (!callerId.equals(other.callerId))
			return false;
		if (credito == null) {
			if (other.credito != null)
				return false;
		} else if (!credito.equals(other.credito))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeCompleto == null) {
			if (other.nomeCompleto != null)
				return false;
		} else if (!nomeCompleto.equals(other.nomeCompleto))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
