package br.edu.ifsc.fsbilling.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifsc.fsbilling.utils.Redirect;
import br.edu.ifsc.fsbilling.view.Path;
import br.edu.ifsc.fsbilling.view.login.LoginBean;

@WebFilter(urlPatterns = { "/home/*", "/planodediscagem/*" })
public class SessionFilter implements Filter {

	@Inject
	private LoginBean loginBean;
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		// recupera o request e o response
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// recupera a sessao corrente
		HttpSession session = httpRequest.getSession(true);

		// verifica se o acesso È a pagina de login
		if ((httpRequest.getRequestURI().contains("login.jsf"))) {

			// encaminha a requisicao para adiante
			chain.doFilter(request, response);

		} else if ((session == null) || loginBean.getAdministrador() == null) {

			// redireciona para a pagina de sessao expirada
			Redirect.toUrl(Path.LOGIN).go();

		} else {

			// caso contrario, continua o processamento
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

}
