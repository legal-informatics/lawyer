package rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import com.clarkparsia.empire.annotation.Namespaces;
import com.clarkparsia.empire.annotation.RdfProperty;
import com.clarkparsia.empire.annotation.RdfsClass;

@Entity
@MappedSuperclass
@Namespaces({"norms", "http://informatika.ftn.uns.ac.rs/legal/norms.owl#",
	"rdfs", "http://www.w3.org/2000/01/rdf-schema#"})
@RdfsClass("norms:LegalSubject")
public class LegalSubject extends Subject {
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@RdfProperty("norms:hasIssued")
	protected Set<LegalAct> legalActs = new HashSet<LegalAct>();
	
	public LegalSubject() {
		super();
	}

	public Set<LegalAct> getLegalActs() {
		return legalActs;
	}

	public void setLegalActs(Set<LegalAct> legalActs) {
		this.legalActs = legalActs;
	}
}