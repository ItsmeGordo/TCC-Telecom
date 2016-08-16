package br.edu.ifsc.fsbilling.business.cdr;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.edu.ifsc.fsbilling.business.plano.PlanoManagerBean;
import br.edu.ifsc.fsbilling.business.planodiscagem.PlanoDiscagemManagerBean;
import br.edu.ifsc.fsbilling.dto.Extrato;
import br.edu.ifsc.fsbilling.entity.FreeswitchCDR;
import br.edu.ifsc.fsbilling.entity.Plano;
import br.edu.ifsc.fsbilling.entity.PlanoDiscagem;
import br.edu.ifsc.fsbilling.entity.Usuario;

@Stateless
public class CdrManagerBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "fsbilling")
	protected EntityManager em;

	@EJB
	PlanoDiscagemManagerBean planoDiscagemManager;
	
	@EJB
	PlanoManagerBean planoManager;
	
	public List<FreeswitchCDR> listByCallerID(String callerId, Date initTime, Date endTime) {
		Query qry = em.createNativeQuery("SELECT * FROM FREESWITCH_CDR where CALLER_ID_NUMBER = ? and BILLSEC != 0 and ANSWER_STAMP BETWEEN ? AND ?;");
		qry.setParameter(1, callerId);
		qry.setParameter(2, initTime);
		qry.setParameter(3, endTime);
		List results = qry.getResultList();
		List<FreeswitchCDR> retorno = new ArrayList<FreeswitchCDR>();
		for (int i = 0; i < results.size(); i++) {
			FreeswitchCDR cdr = new FreeswitchCDR();
			Object[] obj = (Object[]) results.get(i);
			cdr.setCallerIdName(obj[0].toString());
			cdr.setCallerIdNumber(obj[1].toString());
			cdr.setDestinationNumber(obj[2].toString());
			cdr.setContext(obj[3].toString());
			cdr.setStartStamp((Date) obj[4]);
			cdr.setAnswerStamp((Date) obj[5]);
			cdr.setEndStamp((Date) obj[6]);
			cdr.setDuration(Integer.parseInt(obj[7].toString()));
			cdr.setBillSec(Integer.parseInt(obj[8].toString()));
			cdr.setHangUpCause(obj[9].toString());
			cdr.setUuid(obj[10].toString());
			cdr.setBlegUuid(obj[11].toString());
			cdr.setAccountcode(obj[12].toString());
			cdr.setDomainName(obj[13].toString());
			retorno.add(cdr);
		}
		return retorno;
	}

	public List<Extrato> listAllExtrato(Usuario usuario, Date initTime, Date endTime) {
		List<FreeswitchCDR> cdrs = listByCallerID(usuario.getCallerId(), initTime, endTime);
		List<Extrato> extratos = new ArrayList<Extrato>();
		
		for (FreeswitchCDR cdr : cdrs) {
			Plano plano = planoManager.retrievePlanoFromUsuario(usuario);
			PlanoDiscagem planoDiscagem = planoDiscagemManager.searchByRegexp(cdr.getDestinationNumber(), plano.getId());
			BigDecimal valorPorSegundo = planoDiscagem.getTarifaVenda().getValorVenda().divide(new BigDecimal(planoDiscagem.getTarifaVenda().getCadencia()));
			BigDecimal valor = valorPorSegundo.multiply(new BigDecimal(cdr.getBillSec()));
			extratos.add(new Extrato(usuario, valor, cdr));
		}
		
 		return extratos;
		
	}
}
