package br.edu.ifsc.fsbilling.view.planodiscagem;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.edu.ifsc.fsbilling.business.planodiscagem.PlanoDiscagemManagerBean;
import br.edu.ifsc.fsbilling.entity.PlanoDiscagem;

public class LazyPlanoDiscagemDataModel extends LazyDataModel<PlanoDiscagem> {

	private static final long serialVersionUID = 1L;

	private PlanoDiscagemManagerBean planoDiscagemManager;
	
	public LazyPlanoDiscagemDataModel(PlanoDiscagemManagerBean planoDiscagemManager) {
		super();
		this.planoDiscagemManager = planoDiscagemManager;
	}

	@Override
	public List<PlanoDiscagem> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		Long rowCount = planoDiscagemManager.count();
		setRowCount(rowCount > Integer.MAX_VALUE ? Integer.MAX_VALUE : rowCount.intValue());
		return planoDiscagemManager.search(first, pageSize);
	}

}
