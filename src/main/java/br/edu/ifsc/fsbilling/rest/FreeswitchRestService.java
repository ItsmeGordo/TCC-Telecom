package br.edu.ifsc.fsbilling.rest;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import br.edu.ifsc.fsbilling.business.planodiscagem.PlanoDiscagemManagerBean;
import br.edu.ifsc.fsbilling.business.usuario.UsuarioManagerBean;
import br.edu.ifsc.fsbilling.entity.PlanoDiscagem;
import br.edu.ifsc.fsbilling.entity.Usuario;
import br.edu.ifsc.fsbilling.rest.entity.Action;
import br.edu.ifsc.fsbilling.rest.entity.Condition;
import br.edu.ifsc.fsbilling.rest.entity.Context;
import br.edu.ifsc.fsbilling.rest.entity.Extension;
import br.edu.ifsc.fsbilling.rest.entity.Result;
import br.edu.ifsc.fsbilling.rest.entity.User;
import br.edu.ifsc.fsbilling.rest.entity.Document;
import br.edu.ifsc.fsbilling.rest.entity.Domain;
import br.edu.ifsc.fsbilling.rest.entity.Group;
import br.edu.ifsc.fsbilling.rest.entity.Param;
import br.edu.ifsc.fsbilling.rest.entity.Section;

@Path("/freeswitch")
public class FreeswitchRestService {

	@EJB
	UsuarioManagerBean usuarioManager;

	@EJB
	PlanoDiscagemManagerBean planoDiscagemManager;

	@POST
	@Path("/directory")
	@Produces(MediaType.TEXT_XML)
	public String directory(@FormParam("user") String userName, @FormParam("domain") String domainName, @FormParam("section") String sectionName) {

		Usuario usuario = usuarioManager.searchByUsername(userName);
		Document resposta = new Document();
		if (usuario == null) {
			Section section = new Section();
			section.setName("result");

			Result result = new Result();
			result.setStatus("not found");

			section.setResult(result);
			resposta.setSection(section);
		} else {
			resposta.setType("freeswitch/xml");

			Section section = new Section();
			section.setName(sectionName);

			Domain domain = new Domain();
			domain.setName(domainName);
			List<Param> domainParams = new ArrayList<Param>();
			domainParams.add(new Param("dial-string", "{presence_id=${dialed_user}@${dialed_domain}}${sofia_contact(${dialed_user}@${dialed_domain})}"));
			domain.setParams(domainParams);

			List<Group> groups = new ArrayList<Group>();
			List<Param> userParams = new ArrayList<Param>();
			List<User> users = new ArrayList<User>();
			Group group = new Group();
			group.setName("default");
			User user = new User();
			user.setId(usuario.getUsername());
			userParams.add(new Param("password", usuario.getPassword()));
			user.setParams(userParams);
			users.add(user);
			group.setUsers(users);
			groups.add(group);

			domain.setGroups(groups);
			section.setDomain(domain);
			resposta.setSection(section);
		}
		JAXBContext jc;
		StringWriter writer = new StringWriter();
		try {
			jc = JAXBContext.newInstance(Document.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			marshaller.marshal(resposta, writer);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return writer.toString();

	}

	@POST
	@Path("/dialplan")
	@Produces(MediaType.TEXT_XML)
	public String dialplan(@FormParam("Caller-Context") String contextName, @FormParam("section") String sectionName,
			@FormParam("Caller-Destination-Number") String destinationNumber, @FormParam("Caller-Username") String username) {

		Usuario callerUser = usuarioManager.searchByUsername(username);

		PlanoDiscagem pd = planoDiscagemManager.searchByRegexp(destinationNumber, callerUser.getPlano().getId());
		Document resposta = new Document();

		if (pd == null) {
			Section section = new Section();
			section.setName("result");

			Result result = new Result();
			result.setStatus("not found");

			section.setResult(result);
			resposta.setSection(section);
		} else {

			Integer heartbeat = pd.getTarifaVenda().getCadencia();
			BigDecimal valor = pd.getTarifaVenda().getValorVenda();
			BigDecimal rate = calcRate(valor, heartbeat);

			resposta.setType("freeswitch/xml");

			Section section = new Section();
			section.setName(sectionName);
			section.setDescription("Rest Dialplan");

			Context context = new Context();
			context.setName(contextName);

			Extension extension = new Extension();
			extension.setName(pd.getDescricao().toLowerCase().replace(" ", "_"));

			Condition condition = new Condition();
			condition.setField("destination_number");
			condition.setExpression(destinationNumber);

			Action callAction = new Action(pd.getAction(), pd.getActionAtt().replace("$1", destinationNumber));
			List<Action> lista = new ArrayList<Action>();
			if (pd.getPlano().isPrePago()) {
				Action setNibbleAccount = new Action("set", "nibble_account=" + callerUser.getId());
				Action setNibbleRate = new Action("set", "nibble_rate=" + rate);
				Action setHeartbeat = new Action("nibblebill", "heartbeat " + heartbeat);
				lista.add(setNibbleAccount);
				lista.add(setNibbleRate);
				lista.add(setHeartbeat);
			}
			lista.add(callAction);
			condition.setActions(lista);
			extension.setCondition(condition);
			context.setExtension(extension);
			section.setContext(context);
			resposta.setSection(section);
		}
		JAXBContext jc;
		StringWriter writer = new StringWriter();
		try {
			jc = JAXBContext.newInstance(Document.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			marshaller.marshal(resposta, writer);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return writer.toString();
	}

	private BigDecimal calcRate(BigDecimal rate, Integer heatbeat) {
		BigDecimal configuredHeartbeat = new BigDecimal(60);
		BigDecimal newRate = BigDecimal.ZERO;
		newRate = configuredHeartbeat.multiply(rate);
		newRate = newRate.divide(new BigDecimal(heatbeat));
		return newRate;
	}
}
