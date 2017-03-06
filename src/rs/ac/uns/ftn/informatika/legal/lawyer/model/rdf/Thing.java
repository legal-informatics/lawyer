package rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import com.clarkparsia.empire.SupportsRdfId;
import com.clarkparsia.empire.annotation.Namespaces;
import com.clarkparsia.empire.annotation.RdfProperty;
import com.clarkparsia.empire.annotation.RdfsClass;
import com.clarkparsia.empire.annotation.SupportsRdfIdImpl;

@Entity
@MappedSuperclass
@Namespaces({"norms", "http://informatika.ftn.uns.ac.rs/legal/norms.owl#",
	"rdfs", "http://www.w3.org/2000/01/rdf-schema#"})
@RdfsClass("norms:Thing")
public class Thing implements SupportsRdfId {
	protected SupportsRdfId supportsRdfId = new SupportsRdfIdImpl();
	
	@RdfProperty("rdfs:label")
	protected String label;
	
	public Thing() {
		super();
	}
	
	public RdfKey getRdfId() {
		return supportsRdfId.getRdfId();
	}

	public void setRdfId(final RdfKey theId) {
		supportsRdfId.setRdfId(theId);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@Override
	public boolean equals(final java.lang.Object object) {
		if (this == object) {
			return true;
		}
		
		if (object == null || getClass() != object.getClass()) {
			return false;
		}

		final Thing thing = (Thing) object;
		if (getRdfId() != null) {
			return getRdfId().equals(thing.getRdfId());
		}

		return true;
	}
	
	@Override
	public int hashCode() {
		return getRdfId() == null ? 0 : getRdfId().value().hashCode();
	}
	
	@Override
	public String toString() {
		return "[" + getClass().getSimpleName() + ": <" + getRdfId() + ">, " + getLabel() + "]";
	}
}