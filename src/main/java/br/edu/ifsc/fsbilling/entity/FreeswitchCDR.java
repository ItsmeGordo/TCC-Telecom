package br.edu.ifsc.fsbilling.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name = "FREESWITCH_CDR")
@NamedQueries({
	@NamedQuery(name = FreeswitchCDR.LIST_CDR_FROM_CALLERID, query = "select cdr from FreeswitchCDR where cdr.callerIdNumber = :callerId")
})
@SqlResultSetMappings({
    @SqlResultSetMapping(name = "fcdrMapping", columns = {
            @ColumnResult(name = "calledIdName"),
            @ColumnResult(name = "callerIdNumber"),
            @ColumnResult(name = "destinationNumber"),
            @ColumnResult(name = "context"),
            @ColumnResult(name = "startStamp"),
            @ColumnResult(name = "answerStamp"),
            @ColumnResult(name = "endStamp"),
            @ColumnResult(name = "duration"),
            @ColumnResult(name = "billSec"),
            @ColumnResult(name = "hangUpCause"),
            @ColumnResult(name = "uuid"),
            @ColumnResult(name = "blegUuid"),
            @ColumnResult(name = "accountcode"),
            @ColumnResult(name = "domainName")
    })
})
@NamedNativeQuery(name = "freeswitchCDR", 
query = "SELECT CALLER_ID_NAME, CALLER_ID_NUMBER,DESTINATION_NUMBER, CONTEXT,START_STAMP, ANSWER_STAMP ,END_STAMP,DURATION,BILLSEC,HANGUP_CAUSE,UUID,BLEG_UUID,ACCOUNTCODE,DOMAIN_NAME FROM FREESWITCH_CDR where CALLER_ID_NUMBER = ? and BILLSEC != 0 and ANSWER_STAMP BETWEEN ? AND ?;", 
        resultSetMapping = "fcdrMapping")
public class FreeswitchCDR implements Serializable {

	public static final String LIST_CDR_FROM_CALLERID = "FreeswitchCDR.LIST_CDR_FROM_CALLERID";
	@Column(name = "CALLER_ID_NAME")
	private String callerIdName;
	@Column(name = "CALLER_ID_NUMBER")
	private String callerIdNumber;
	@Column(name = "DESTINATION_NUMBER")
	private String destinationNumber;
	@Column(name = "CONTEXT")
	private String context;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_STAMP")
	private Date startStamp;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ANSWER_STAMP")
	private Date answerStamp;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_STAMP")
	private Date endStamp;
	@Column(name = "DURATION")
	private Integer duration;
	@Column(name = "BILLSEC")
	private Integer billSec;
	@Column(name = "HANGUP_CAUSE")
	private String hangUpCause;
	@Column(name = "UUID")
	private String uuid;
	@Column(name = "BLEG_UUID")
	private String blegUuid;
	@Column(name = "ACCOUNTCODE")
	private String accountcode;
	@Column(name = "DOMAIN_NAME")
	private String domainName;

	public String getCallerIdName() {
		return callerIdName;
	}

	public void setCallerIdName(String callerIdName) {
		this.callerIdName = callerIdName;
	}

	public String getCallerIdNumber() {
		return callerIdNumber;
	}

	public void setCallerIdNumber(String callerIdNumber) {
		this.callerIdNumber = callerIdNumber;
	}

	public String getDestinationNumber() {
		return destinationNumber;
	}

	public void setDestinationNumber(String destinationNumber) {
		this.destinationNumber = destinationNumber;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getStartStamp() {
		return startStamp;
	}

	public void setStartStamp(Date startStamp) {
		this.startStamp = startStamp;
	}

	public Date getAnswerStamp() {
		return answerStamp;
	}

	public void setAnswerStamp(Date answerStamp) {
		this.answerStamp = answerStamp;
	}

	public Date getEndStamp() {
		return endStamp;
	}

	public void setEndStamp(Date endStamp) {
		this.endStamp = endStamp;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getBillSec() {
		return billSec;
	}

	public void setBillSec(Integer billSec) {
		this.billSec = billSec;
	}

	public String getHangUpCause() {
		return hangUpCause;
	}

	public void setHangUpCause(String hangUpCause) {
		this.hangUpCause = hangUpCause;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getBlegUuid() {
		return blegUuid;
	}

	public void setBlegUuid(String blegUuid) {
		this.blegUuid = blegUuid;
	}

	public String getAccountcode() {
		return accountcode;
	}

	public void setAccountcode(String accountcode) {
		this.accountcode = accountcode;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountcode == null) ? 0 : accountcode.hashCode());
		result = prime * result + ((answerStamp == null) ? 0 : answerStamp.hashCode());
		result = prime * result + ((billSec == null) ? 0 : billSec.hashCode());
		result = prime * result + ((blegUuid == null) ? 0 : blegUuid.hashCode());
		result = prime * result + ((callerIdName == null) ? 0 : callerIdName.hashCode());
		result = prime * result + ((callerIdNumber == null) ? 0 : callerIdNumber.hashCode());
		result = prime * result + ((context == null) ? 0 : context.hashCode());
		result = prime * result + ((destinationNumber == null) ? 0 : destinationNumber.hashCode());
		result = prime * result + ((domainName == null) ? 0 : domainName.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((endStamp == null) ? 0 : endStamp.hashCode());
		result = prime * result + ((hangUpCause == null) ? 0 : hangUpCause.hashCode());
		result = prime * result + ((startStamp == null) ? 0 : startStamp.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FreeswitchCDR other = (FreeswitchCDR) obj;
		if (accountcode == null) {
			if (other.accountcode != null)
				return false;
		} else if (!accountcode.equals(other.accountcode))
			return false;
		if (answerStamp == null) {
			if (other.answerStamp != null)
				return false;
		} else if (!answerStamp.equals(other.answerStamp))
			return false;
		if (billSec == null) {
			if (other.billSec != null)
				return false;
		} else if (!billSec.equals(other.billSec))
			return false;
		if (blegUuid == null) {
			if (other.blegUuid != null)
				return false;
		} else if (!blegUuid.equals(other.blegUuid))
			return false;
		if (callerIdName == null) {
			if (other.callerIdName != null)
				return false;
		} else if (!callerIdName.equals(other.callerIdName))
			return false;
		if (callerIdNumber == null) {
			if (other.callerIdNumber != null)
				return false;
		} else if (!callerIdNumber.equals(other.callerIdNumber))
			return false;
		if (context == null) {
			if (other.context != null)
				return false;
		} else if (!context.equals(other.context))
			return false;
		if (destinationNumber == null) {
			if (other.destinationNumber != null)
				return false;
		} else if (!destinationNumber.equals(other.destinationNumber))
			return false;
		if (domainName == null) {
			if (other.domainName != null)
				return false;
		} else if (!domainName.equals(other.domainName))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (endStamp == null) {
			if (other.endStamp != null)
				return false;
		} else if (!endStamp.equals(other.endStamp))
			return false;
		if (hangUpCause == null) {
			if (other.hangUpCause != null)
				return false;
		} else if (!hangUpCause.equals(other.hangUpCause))
			return false;
		if (startStamp == null) {
			if (other.startStamp != null)
				return false;
		} else if (!startStamp.equals(other.startStamp))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
}