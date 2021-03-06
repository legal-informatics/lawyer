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
@RdfsClass("norms:Bylaw")
public class Bylaw extends GeneralLegalAct {
	
	@ManyToOne
	@RdfProperty("norms:isBylaw")
	protected GeneralLegalAct generalLegalAct = null;
	
	public Bylaw() {
	
	}

	public GeneralLegalAct getGeneralLegalAct() {
		return generalLegalAct;
	}

	public void setGeneralLegalAct(GeneralLegalAct generalLegalAct) {
		this.generalLegalAct = generalLegalAct;
	}
}