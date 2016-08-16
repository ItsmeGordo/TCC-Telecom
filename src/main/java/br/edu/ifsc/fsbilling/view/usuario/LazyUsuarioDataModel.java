package br.edu.ifsc.fsbilling.view.usuario;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.edu.ifsc.fsbilling.business.usuario.UsuarioManagerBean;
import br.edu.ifsc.fsbilling.entity.Usuario;

public class LazyUsuarioDataModel extends LazyDataModel<Usuario> {

	private static final long serialVersionUID = 1L;

	private UsuarioManagerBean usuarioManager;
	
	public LazyUsuarioDataModel(UsuarioManagerBean usuarioManager) {
		super();
		this.usuarioManager = usuarioManager;
	}

	@Override
	public List<Usuario> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		Long rowCount = usuarioManager.count();
		setRowCount(rowCount > Integer.MAX_VALUE ? Integer.MAX_VALUE : rowCount.intValue());
		return usuarioManager.search(first, pageSize);
	}

}
