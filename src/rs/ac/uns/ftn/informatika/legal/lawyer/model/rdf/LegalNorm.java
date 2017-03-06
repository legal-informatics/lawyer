package rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.namespace.QName;

import rs.ac.uns.ftn.informatika.legal.lawyer.services.ServiceRegistry;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.query.xml.XMLQueryService;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.resolver.ResolverService;
import rs.ac.uns.ftn.informatika.legal.lawyer.util.IdComparator;
import rs.ac.uns.ftn.informatika.legal.lawyer.util.Util;

import com.clarkparsia.empire.annotation.Namespaces;
import com.clarkparsia.empire.annotation.RdfProperty;
import com.clarkparsia.empire.annotation.RdfsClass;

@Entity
@MappedSuperclass
@Namespaces({"norms", "http://informatika.ftn.uns.ac.rs/legal/norms.owl#",
	"rdfs", "http://www.w3.org/2000/01/rdf-schema#"})
@RdfsClass("norms:LegalNorm")
public class LegalNorm extends Thing {
	@Transient
	private final int synopsisLength = 300; 
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasCaseLaw")
	protected Set<Sentence> caseLaw = new HashSet<Sentence>();
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasExpertOpinion")
	protected Set<ExpertOpinion> expertOpinions = new HashSet<ExpertOpinion>();
	
	@ManyToOne
	@RdfProperty("norms:hasDisposition")
	protected Disposition disposition;
	
	@ManyToOne
	@RdfProperty("norms:hasDispositionHypothesis")
	protected DispositionHypothesis dispositionHypothesis;
	
	@ManyToOne
	@RdfProperty("norms:hasSanction")
	protected Sanction sanction;
	
	@ManyToOne
	@RdfProperty("norms:hasSanctionHypothesis")
	protected SanctionHypothesis sanctionHypothesis;

	@ManyToOne
	@RdfProperty("norms:hasException")
	protected Exception exception;
	
	@ManyToOne
	@RdfProperty("norms:regulates")
	protected LegalRelation legalRelation;
	
	@ManyToOne
	@RdfProperty("norms:hasPolicy")
	protected Policy policy;
	
	@ManyToOne
	@RdfProperty("norms:isLegalNorm")
	protected LegalInstitution legalInstitution;
	
	@ManyToOne
	@RdfProperty("norms:isContained")
	protected GeneralLegalAct legalAct;
	
	@RdfProperty("norms:hasEnteredIntoForce")
	protected Date enteredIntoForce;
	
	@RdfProperty("norms:isRepealed")
	protected Date repealed;
	
	@RdfProperty("norms:hasEfficacy")
	protected Date efficacy;
	
	public LegalNorm() {
		super();
	}
	
	public Set<Sentence> getCaseLaw() {
		return caseLaw;
	}

	public void setCaseLaw(Set<Sentence> caseLaw) {
		this.caseLaw = caseLaw;
	}

	public Set<ExpertOpinion> getExpertOpinions() {
		return expertOpinions;
	}

	public void setExpertOpinions(Set<ExpertOpinion> expertOpinions) {
		this.expertOpinions = expertOpinions;
	}

	public Disposition getDisposition() {
		return disposition;
	}

	public void setDisposition(Disposition disposition) {
		this.disposition = disposition;
	}

	public DispositionHypothesis getDispositionHypothesis() {
		return dispositionHypothesis;
	}

	public void setDispositionHypothesis(DispositionHypothesis dispositionHypothesis) {
		this.dispositionHypothesis = dispositionHypothesis;
	}

	public Sanction getSanction() {
		return sanction;
	}

	public void setSanction(Sanction sanction) {
		this.sanction = sanction;
	}

	public SanctionHypothesis getSanctionHypothesis() {
		return sanctionHypothesis;
	}

	public void setSanctionHypothesis(SanctionHypothesis sanctionHypothesis) {
		this.sanctionHypothesis = sanctionHypothesis;
	}
	
	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public LegalRelation getLegalRelation() {
		return legalRelation;
	}

	public void setLegalRelation(LegalRelation legalRelation) {
		this.legalRelation = legalRelation;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public LegalInstitution getLegalInstitution() {
		return legalInstitution;
	}

	public void setLegalInstitution(LegalInstitution legalInstitution) {
		this.legalInstitution = legalInstitution;
	}

	public GeneralLegalAct getLegalAct() {
		return legalAct;
	}

	public void setLegalAct(GeneralLegalAct legalAct) {
		this.legalAct = legalAct;
	}

	public Date getEnteredIntoForce() {
		return enteredIntoForce;
	}

	public void setEnteredIntoForce(Date enteredIntoForce) {
		this.enteredIntoForce = enteredIntoForce;
	}
	
	public Date getRepealed() {
		return repealed;
	}

	public void setRepealed(Date repealed) {
		this.repealed = repealed;
	}

	public Date getEfficacy() {
		return efficacy;
	}

	public void setEfficacy(Date efficacy) {
		this.efficacy = efficacy;
	}
	
	public String getSynopsis() {
		
		String synopsis = "";
		
		if (label != null && !label.equals("")) {
			synopsis =  label.length() > synopsisLength ? label.substring(0, synopsisLength - 1) : label;
		} else {
			Set<URI> uriReferences = disposition.getUri();
			
			ResolverService rs = ServiceRegistry.getResolverService();
			URI urn = rs.getUriFromUriReference(uriReferences.iterator().next());
			URI url = rs.getUrlFromUrn(urn);
			
			List<String> fragmentIds = new ArrayList<String>();
			for(URI uriReference: uriReferences) {
				String fragmentId = rs.getFragmentIdFromUriReference(uriReference);
				fragmentIds.add(fragmentId);
			}
			
			Collections.sort(fragmentIds, new IdComparator());
			
			for(String fragmentId: fragmentIds) {
				XMLQueryService qs = ServiceRegistry.getXMLQueryService();
				String content = qs.getElementText(url.toString(), fragmentId);
				synopsis += content + " ";
			}
		
			// trim synopsis
			synopsis = synopsis.length() > synopsisLength ? synopsis.substring(0, synopsisLength - 1) : synopsis;
			//System.out.println("URL: " + url);
			//System.out.println("fragmentId: " + fragmentId);
			//System.out.println("content: " + content);
		}
		
		if (synopsis != null)
			return Util.escape(synopsis);
		else
			return null;
	}
	
	public void setSynopsis(String synopsis) {
		
	}
	
	public String getFragment() {
		ResolverService rs = ServiceRegistry.getResolverService();
		
		Set<URI> uriReferences = disposition.getUri();
		List<String> fragmentIds = new ArrayList<String>();
		for(URI uriReference: uriReferences) {
			String fragmentId = rs.getFragmentIdFromUriReference(uriReference);
			fragmentIds.add(fragmentId);
		}
		
		Collections.sort(fragmentIds, new IdComparator());
		
		return fragmentIds.iterator().next();
	}
	
	public void setFragment(String fragment) {
		
	}
	
	public static void main(String[] args) {
		
	}
}