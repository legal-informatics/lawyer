package rs.ac.uns.ftn.informatika.legal.lawyer.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalNorm;
import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.Sentence;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.ServiceRegistry;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.query.rdf.RDFQueryService;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.resolver.ResolverService;

public class CaseLawController extends HttpServlet {

	private static final long serialVersionUID = -1821191896931342171L;
	
	private static Logger log = Logger.getLogger(CaseLawController.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String provisionId = request.getParameter("provisionId");
		String legalNormId = request.getParameter("legalNormId");
		
		LegalNorm legalNorm = null;
		if (provisionId != null && !provisionId.equals("")) {
			RDFQueryService qs = ServiceRegistry.getRDFQueryService();
			legalNormId = qs.getLegalNormIdByProvisionId(provisionId); 
			if (legalNormId != null && !legalNormId.equals("")) {
				legalNorm = qs.getLegalNorm(legalNormId);
			}
		} else if (legalNormId != null && !legalNormId.equals("")) {
			RDFQueryService qs = ServiceRegistry.getRDFQueryService(); 
			legalNorm = qs.getLegalNorm(legalNormId);
		}
		
		Set<Sentence> caseLaw = legalNorm.getCaseLaw();
		request.setAttribute("caseLaw", caseLaw);	
		request.getRequestDispatcher("/caseLaw.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
