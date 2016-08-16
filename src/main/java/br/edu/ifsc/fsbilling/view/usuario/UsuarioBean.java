package br.edu.ifsc.fsbilling.view.usuario;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import br.edu.ifsc.fsbilling.business.plano.PlanoManagerBean;
import br.edu.ifsc.fsbilling.business.usuario.UsuarioManagerBean;
import br.edu.ifsc.fsbilling.entity.Plano;
import br.edu.ifsc.fsbilling.entity.Usuario;
import br.edu.ifsc.fsbilling.utils.Redirect;
import br.edu.ifsc.fsbilling.view.AbstractBean;
import br.edu.ifsc.fsbilling.view.Path;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Named(value = "usuarioBean")
@ViewScoped
public class UsuarioBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private UsuarioManagerBean usuarioManager;

	@EJB
	private PlanoManagerBean planoManager;
	
	private LazyUsuarioDataModel usuarioDataModel;
	private Usuario usuario;
	private List<Plano> planos;
	
	@PostConstruct
	public void postConstruct() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		
		setPlanos(planoManager.listAll());
	}

	@Override
	public boolean preEdit() {
		if (usuario.getId() == null) {
			return false;
		} else {
			usuario = usuarioManager.findById(usuario.getId(), Usuario.class);
			return true;
		}

	}

	public void viewInit() {
		usuarioDataModel = new LazyUsuarioDataModel(usuarioManager);
	}

	public void delete() {
		usuario = usuarioManager.findById(usuario.getId(), Usuario.class);
		usuarioManager.delete(usuario);
	}

	public void save() {
		if (usuario.getCredito() == null) {
			usuario.setCredito(BigDecimal.ZERO);
		}
		usuarioManager.update(usuario);
		Redirect.toUrl(Path.USUARIO_SEARCH).go();
	}

	public LazyUsuarioDataModel getUsuarioDataModel() {
		return usuarioDataModel;
	}

	public void setUsuarioDataModel(LazyUsuarioDataModel usuarioDataModel) {
		this.usuarioDataModel = usuarioDataModel;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Plano> getPlanos() {
		return planos;
	}

	public void setPlanos(List<Plano> planos) {
		this.planos = planos;
	}

}
