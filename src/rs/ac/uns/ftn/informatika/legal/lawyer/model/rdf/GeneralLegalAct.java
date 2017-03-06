package rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import rs.ac.uns.ftn.informatika.legal.lawyer.util.Util;

import com.clarkparsia.empire.annotation.Namespaces;
import com.clarkparsia.empire.annotation.RdfProperty;
import com.clarkparsia.empire.annotation.RdfsClass;

@Entity
@MappedSuperclass
@Namespaces({"norms", "http://informatika.ftn.uns.ac.rs/legal/norms.owl#",
	"rdfs", "http://www.w3.org/2000/01/rdf-schema#",
	"pf", "http://jena.hpl.hp.com/ARQ/property#"})
@RdfsClass("norms:GeneralLegalAct")
public class GeneralLegalAct extends LegalAct {
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:contains")
	protected Set<LegalNorm> legalNorms = new HashSet<LegalNorm>();
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasBylaw")
	protected Set<Bylaw> bylaws = new HashSet<Bylaw>();
	
	@RdfProperty("norms:isEnacted")
	protected Date enacted;
	
	@RdfProperty("norms:isPromulgated")
	protected Date promulgated;
	
	@RdfProperty("norms:isPublished")
	protected Date published;
	
	@RdfProperty("norms:hasEnteredIntoForce")
	protected Date enteredIntoForce;
	
	@RdfProperty("norms:isRepealed")
	protected Date repealed;
	
	@RdfProperty("norms:hasEfficacy")
	protected Date efficacy;
	
	@ManyToOne
	@RdfProperty("norms:isIssued")
	protected LegalSubject legalSubject;
	
	@RdfProperty("norms:hasGazette")
	protected String gazette;
	
	public GeneralLegalAct() {
	
	}
	
	public Set<LegalNorm> getLegalNorms() {
		return legalNorms;
	}

	public void setLegalNorms(Set<LegalNorm> legalNorms) {
		this.legalNorms = legalNorms;
	}
	
	public Set<Bylaw> getBylaws() {
		return bylaws;
	}

	public void setBylaws(Set<Bylaw> bylaws) {
		this.bylaws = bylaws;
	}

	public Date getEnacted() {
		return enacted;
	}

	public void setEnacted(Date enacted) {
		this.enacted = enacted;
	}

	public Date getPromulgated() {
		return promulgated;
	}

	public void setPromulgated(Date promulgated) {
		this.promulgated = promulgated;
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
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

	public LegalSubject getLegalSubject() {
		return legalSubject;
	}

	public void setLegalSubject(LegalSubject legalSubject) {
		this.legalSubject = legalSubject;
	}

	public String getGazette() {
		return Util.escape(gazette);
	}

	public void setGazette(String gazette) {
		this.gazette = gazette;
	}
}