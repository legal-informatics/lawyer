package rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import com.clarkparsia.empire.annotation.Namespaces;
import com.clarkparsia.empire.annotation.RdfProperty;
import com.clarkparsia.empire.annotation.RdfsClass;

@Entity
@MappedSuperclass
@Namespaces({"norms", "http://informatika.ftn.uns.ac.rs/legal/norms.owl#"})
@RdfsClass("norms:Policy")
public class Policy extends Thing {
	@ManyToOne
	@RdfProperty("norms:isPolicy")
	protected Set<LegalNorm> legalNorms = new HashSet<LegalNorm>();
	
	@RdfProperty("norms:hasTextualFormulation")
	protected String textualFormulation = null;
	
	@RdfProperty("norms:hasURI")
	protected URI uri = null;
	
	public Policy() {
		super();
	}

	public Set<LegalNorm> getLegalNorms() {
		return legalNorms;
	}

	public void setLegalNorms(Set<LegalNorm> legalNorms) {
		this.legalNorms = legalNorms;
	}
	
	public String getTextualFormulation() {
		return textualFormulation;
	}

	public void setTextualFormulation(String textualFormulation) {
		this.textualFormulation = textualFormulation;
	}

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}
}