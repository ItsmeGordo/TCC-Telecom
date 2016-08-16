package br.edu.ifsc.fsbilling.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "PLANO")
@NamedQueries({ @NamedQuery(name = Plano.SEARCH, query = "select p from Plano p ORDER BY p.id"), 
		@NamedQuery(name = Plano.COUNT, query = "select count(p) from Plano p"),
		@NamedQuery(name = Plano.RETRIEVE_PLANO, query = "select p from Plano p where p.nomePlano like :nome"),
		@NamedQuery(name = Plano.RETRIEVE_PLANO_FROM_USUARIO, query = "select u.plano from Usuario u left join u.plano p where u = :usuario")})
public class Plano extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	public final static String SEARCH = "Plano.SEARCH";
	public final static String COUNT = "Plano.COUNT";
	public final static String RETRIEVE_PLANO = "Plano.RETRIEVE_PLANO";
	public final static String RETRIEVE_PLANO_FROM_USUARIO = "Plano.RETRIEVE_PLANO_FROM_USUARIO";

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOME_PLANO")
	private String nomePlano;

	@Column(name = "PRE_PAGO")
	private boolean prePago;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomePlano() {
		return nomePlano;
	}

	public void setNomePlano(String nomePlano) {
		this.nomePlano = nomePlano;
	}

	public boolean isPrePago() {
		return prePago;
	}

	public void setPrePago(boolean prePago) {
		this.prePago = prePago;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomePlano == null) ? 0 : nomePlano.hashCode());
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
		Plano other = (Plano) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomePlano == null) {
			if (other.nomePlano != null)
				return false;
		} else if (!nomePlano.equals(other.nomePlano))
			return false;
		return true;
	}

}