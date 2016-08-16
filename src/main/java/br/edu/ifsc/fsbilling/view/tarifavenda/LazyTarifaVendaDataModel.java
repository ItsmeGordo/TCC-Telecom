package br.edu.ifsc.fsbilling.view.tarifavenda;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.edu.ifsc.fsbilling.business.tarifavenda.TarifaVendaManagerBean;
import br.edu.ifsc.fsbilling.entity.TarifaVenda;

public class LazyTarifaVendaDataModel extends LazyDataModel<TarifaVenda> {

	private static final long serialVersionUID = 1L;

	private TarifaVendaManagerBean tarifaManager;
	
	public LazyTarifaVendaDataModel(TarifaVendaManagerBean tarifaManager) {
		super();
		this.tarifaManager = tarifaManager;
	}

	@Override
	public List<TarifaVenda> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		Long rowCount = tarifaManager.count();
		setRowCount(rowCount > Integer.MAX_VALUE ? Integer.MAX_VALUE : rowCount.intValue());
		return tarifaManager.search(first, pageSize);
	}

}
