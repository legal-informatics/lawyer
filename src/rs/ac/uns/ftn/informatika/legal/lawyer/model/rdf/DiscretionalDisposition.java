package rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import com.clarkparsia.empire.annotation.Namespaces;
import com.clarkparsia.empire.annotation.RdfsClass;

@Entity
@MappedSuperclass
@Namespaces({"norms", "http://informatika.ftn.uns.ac.rs/legal/norms.owl#"})
@RdfsClass("norms:DiscretionalDisposition")
public class DiscretionalDisposition extends ImperativeDisposition {
	
	public DiscretionalDisposition() {
		super();
	}
}