package br.edu.ifsc.fsbilling.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.slf4j.Logger;

/** Representa um redirecionamento de URL dentro do sistema. Abstração da lógica
 * de redirecionamento do faces para a implementação de uma navegação facilitada
 * nas views do sistema. Exemplos de uso: Redirect.toUrl(HOME).go();
 * @author Cainã Fuck dos Santos <caina@substractum.com.br> */
public class Redirect {

	private String url;
	private Map<String, String> params = new HashMap<String, String>();

	/** Cria um novo redirecionamento para a URL informada.
	 * @param url
	 * @return */
	public static Redirect toUrl(String url) {
		Redirect redirect = new Redirect();
		redirect.url = url;
		return redirect;
	}

	/** Adiciona o parâmetro informado ao corpo da URL.
	 * @param key
	 * @param value
	 * @return */
	public Redirect param(String key, Object value) {
		this.params.put(key, String.valueOf(value));
		return this;
	}

	/** Aciona o redirecionamento criado, enviando o usuário à pagina
	 * representada pela URL configurada neste objeto. */
	public void go() {
		if (url == null) {
			throw new NullPointerException("A URL do redirecionamento não foi configurada");
		}
		try {
			StringBuilder sb = new StringBuilder();
			String formattedParam = "";
			if (params.size() > 0) {
				sb.append("?");
				for (String key : params.keySet()) {
					sb.append(key);
					sb.append("=");
					sb.append(params.get(key));
					sb.append("&");
				}
				formattedParam = sb.substring(0, sb.length() - 1);
			}
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String contextPath = facesContext.getExternalContext().getRequestContextPath();
			facesContext.getExternalContext().redirect(contextPath + "/" + url + formattedParam);
		} catch (IOException e) {
		}
	}

}
