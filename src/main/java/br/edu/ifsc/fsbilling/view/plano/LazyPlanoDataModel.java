package br.edu.ifsc.fsbilling.view.plano;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.edu.ifsc.fsbilling.business.plano.PlanoManagerBean;
import br.edu.ifsc.fsbilling.entity.Plano;

public class LazyPlanoDataModel extends LazyDataModel<Plano> {

	private static final long serialVersionUID = 1L;

	private PlanoManagerBean planoManager;
	
	public LazyPlanoDataModel(PlanoManagerBean planoManager) {
		super();
		this.planoManager = planoManager;
	}

	@Override
	public List<Plano> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		Long rowCount = planoManager.count();
		setRowCount(rowCount > Integer.MAX_VALUE ? Integer.MAX_VALUE : rowCount.intValue());
		return planoManager.search(first, pageSize);
	}

}
