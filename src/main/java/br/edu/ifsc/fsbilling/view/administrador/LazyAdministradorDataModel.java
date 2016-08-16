package br.edu.ifsc.fsbilling.view.administrador;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.edu.ifsc.fsbilling.business.administrador.AdministradorManagerBean;
import br.edu.ifsc.fsbilling.entity.Administrador;

public class LazyAdministradorDataModel extends LazyDataModel<Administrador> {

	private static final long serialVersionUID = 1L;

	private AdministradorManagerBean administradorManager;
	
	public LazyAdministradorDataModel(AdministradorManagerBean administradorManager) {
		super();
		this.administradorManager = administradorManager;
	}

	@Override
	public List<Administrador> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		Long rowCount = administradorManager.count();
		setRowCount(rowCount > Integer.MAX_VALUE ? Integer.MAX_VALUE : rowCount.intValue());
		return administradorManager.search(first, pageSize);
	}

}
