package br.edu.ifsc.fsbilling.view.tarifacompra;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.edu.ifsc.fsbilling.business.tarifacompra.TarifaCompraManagerBean;
import br.edu.ifsc.fsbilling.entity.TarifaCompra;

public class LazyTarifaCompraDataModel extends LazyDataModel<TarifaCompra> {

	private static final long serialVersionUID = 1L;

	private TarifaCompraManagerBean tarifaManager;
	
	public LazyTarifaCompraDataModel(TarifaCompraManagerBean tarifaManager) {
		super();
		this.tarifaManager = tarifaManager;
	}

	@Override
	public List<TarifaCompra> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		Long rowCount = tarifaManager.count();
		setRowCount(rowCount > Integer.MAX_VALUE ? Integer.MAX_VALUE : rowCount.intValue());
		return tarifaManager.search(first, pageSize);
	}

}
