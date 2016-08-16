package br.edu.ifsc.fsbilling.entity;

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
@Table(name = "PLANO_DE_DISCAGEM")
@NamedQueries({ 
	@NamedQuery(name = PlanoDiscagem.SEARCH, query = "select pd from PlanoDiscagem pd ORDER BY pd.id"), 
	@NamedQuery(name = PlanoDiscagem.COUNT, query = "select count(pd) from PlanoDiscagem pd ORDER BY pd.id")
})
public class PlanoDiscagem extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	public static final String SEARCH = "PlanoDiscagem.SEARCH";
	public static final String COUNT = "PlanoDiscagem.COUNT";
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "REGULAR_EXPRESSION")
	private String expressaoRegular;
	@Column(name = "ACTION")
	private String action;
	@Column(name = "ACTION_ATTRIBUTES")
	private String actionAtt;
	@Column(name = "DESCRICAO")
	private String descricao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PLANO")
	private Plano plano;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_TARIFA_VENDA")
	private TarifaVenda tarifaVenda;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExpressaoRegular() {
		return expressaoRegular;
	}

	public void setExpressaoRegular(String expressaoRegular) {
		this.expressaoRegular = expressaoRegular;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActionAtt() {
		return actionAtt;
	}

	public void setActionAtt(String actionAtt) {
		this.actionAtt = actionAtt;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public TarifaVenda getTarifaVenda() {
		return tarifaVenda;
	}

	public void setTarifaVenda(TarifaVenda tarifaVenda) {
		this.tarifaVenda = tarifaVenda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((actionAtt == null) ? 0 : actionAtt.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((expressaoRegular == null) ? 0 : expressaoRegular.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		PlanoDiscagem other = (PlanoDiscagem) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (actionAtt == null) {
			if (other.actionAtt != null)
				return false;
		} else if (!actionAtt.equals(other.actionAtt))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (expressaoRegular == null) {
			if (other.expressaoRegular != null)
				return false;
		} else if (!expressaoRegular.equals(other.expressaoRegular))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}