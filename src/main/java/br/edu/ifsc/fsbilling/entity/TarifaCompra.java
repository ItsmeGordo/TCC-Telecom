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
@Table(name = "TARIFA_COMPRA")
@NamedQueries({ 
	@NamedQuery(name = TarifaCompra.SEARCH, query = "select tc from TarifaCompra tc ORDER BY tc.id"), 
	@NamedQuery(name = TarifaCompra.COUNT, query = "select count(tc) from TarifaCompra tc"),
	@NamedQuery(name = TarifaCompra.LIST_ALL, query = "select tc from TarifaCompra tc") 
})
public class TarifaCompra extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	public final static String SEARCH = "TarifaCompra.SEARCH";
	public final static String COUNT = "TarifaCompra.COUNT";
	public final static String LIST_ALL = "TarifaCompra.LIST_ALL";
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "VALOR_COMPRA")
	private BigDecimal valorCompra;
	@Column(name = "CADENCIA")
	private Integer cadencia;
	@Column(name = "DESCRICAO")
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}

	public Integer getCadencia() {
		return cadencia;
	}

	public void setCadencia(Integer cadencia) {
		this.cadencia = cadencia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cadencia == null) ? 0 : cadencia.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((valorCompra == null) ? 0 : valorCompra.hashCode());
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
		TarifaCompra other = (TarifaCompra) obj;
		if (cadencia == null) {
			if (other.cadencia != null)
				return false;
		} else if (!cadencia.equals(other.cadencia))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (valorCompra == null) {
			if (other.valorCompra != null)
				return false;
		} else if (!valorCompra.equals(other.valorCompra))
			return false;
		return true;
	}
	
}