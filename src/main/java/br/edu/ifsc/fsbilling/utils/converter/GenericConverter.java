package br.edu.ifsc.fsbilling.utils.converter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import br.edu.ifsc.fsbilling.business.GenericManagerBean;
import br.edu.ifsc.fsbilling.entity.AbstractEntity;


@ViewScoped
@Named("genericConverter")
public class GenericConverter implements Converter, Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private GenericManagerBean genericManager;

	private Map<String, Class<?>> classes = new HashMap<String, Class<?>>();

	public Object getAsObject(final FacesContext context, final UIComponent component,
			final String value) {
		try {
			return genericManager.findById(Long.parseLong(value),
					classes.get(component.getClientId()));
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public String getAsString(final FacesContext context, final UIComponent component,
			final Object value) {
		if (value != null) {
			AbstractEntity entity = (AbstractEntity) value;
			classes.put(component.getClientId(), entity.getClass());
			String string = (entity == null || entity.getId() == null) ? null
					: ((AbstractEntity) value).getId().toString();
			return string;
		}
		return null;
	}

}
