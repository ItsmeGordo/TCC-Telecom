package br.edu.ifsc.fsbilling.view.logcredito;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.edu.ifsc.fsbilling.business.logcredito.LogCreditoManagerBean;
import br.edu.ifsc.fsbilling.entity.LogCredito;

public class LazyLogCreditoDataModel extends LazyDataModel<LogCredito> {

	private static final long serialVersionUID = 1L;

	private LogCreditoManagerBean logManager;
	
	public LazyLogCreditoDataModel(LogCreditoManagerBean logManager) {
		super();
		this.logManager = logManager;
	}

	@Override
	public List<LogCredito> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		Long rowCount = logManager.count();
		setRowCount(rowCount > Integer.MAX_VALUE ? Integer.MAX_VALUE : rowCount.intValue());
		return logManager.search(first, pageSize);
	}

}
