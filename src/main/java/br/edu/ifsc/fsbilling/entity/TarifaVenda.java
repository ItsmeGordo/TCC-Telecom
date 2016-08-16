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
@Table(name = "TARIFA_VENDA")
@NamedQueries({ 
	@NamedQuery(name = TarifaVenda.SEARCH, query = "select tv from TarifaVenda tv ORDER BY tv.id"),
	@NamedQuery(name = TarifaVenda.COUNT, query = "select count(tv) from TarifaVenda tv"), 
	@NamedQuery(name = TarifaVenda.LIST_ALL, query = "select tv from TarifaVenda tv") 
})
public class TarifaVenda extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	public final static String SEARCH = "TarifaVenda.SEARCH";
	public final static String COUNT = "TarifaVenda.COUNT";
	public final static String LIST_ALL = "TarifaVenda.LIST_ALL";

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "VALOR_VENDA")
	private BigDecimal valorVenda;
	@Column(name = "CADENCIA")
	private Integer cadencia;
	@Column(name = "DESCRICAO")
	private String descricao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TARIFA_COMPRA")
	private TarifaCompra tarifaCompra;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
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

	public TarifaCompra getTarifaCompra() {
		return tarifaCompra;
	}

	public void setTarifaCompra(TarifaCompra tarifaCompra) {
		this.tarifaCompra = tarifaCompra;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cadencia == null) ? 0 : cadencia.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((valorVenda == null) ? 0 : valorVenda.hashCode());
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
		TarifaVenda other = (TarifaVenda) obj;
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
		if (valorVenda == null) {
			if (other.valorVenda != null)
				return false;
		} else if (!valorVenda.equals(other.valorVenda))
			return false;
		return true;
	}

	
}
