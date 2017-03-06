package rs.ac.uns.ftn.informatika.legal.lawyer.controller;

import java.io.IOException;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalAct;
import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalNorm;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.ServiceRegistry;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.query.rdf.RDFQueryService;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.query.xml.XMLQueryService;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.resolver.ResolverService;

public class ContentController extends HttpServlet {

	private static final long serialVersionUID = 4699435268269701236L;
	
	private static Logger log = Logger.getLogger(ContentController.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RDFQueryService qs = ServiceRegistry.getRDFQueryService();
		
		String legalNormUri = request.getParameter("legalNormUri");
		LegalNorm legalNorm = null;
		String legalActUri = request.getParameter("legalActUri");
		LegalAct legalAct = null;
		if (legalNormUri != null && !legalNormUri.equals("")) {
			legalNorm = qs.getLegalNorm(legalNormUri);
			request.setAttribute("legalNorm", legalNorm);
			request.setAttribute("legalAct", legalNorm.getLegalAct());
		} else if (legalActUri != null && !legalActUri.equals("")) {
			legalAct = qs.getGeneralLegalAct(legalActUri);
			request.setAttribute("legalAct", legalAct);
		}
		request.getRequestDispatcher("/content.jsp").forward(request, response);		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
