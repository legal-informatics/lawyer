package rs.ac.uns.ftn.informatika.legal.lawyer.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.GeneralLegalAct;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.ServiceRegistry;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.query.rdf.RDFQueryService;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.resolver.ResolverService;

public class ActMetadataController extends HttpServlet {
	
	private static final long serialVersionUID = 1212693853896553449L;
	
	private static Logger log = Logger.getLogger(ActMetadataController.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uriReference = request.getParameter("legalActUri");
		//System.out.println("uriReference: " + uriReference);
		if (uriReference != null && !uriReference.equals("")) {
			RDFQueryService qs = ServiceRegistry.getRDFQueryService(); 
			GeneralLegalAct legalAct = qs.getGeneralLegalAct(uriReference.toString());
			//System.out.println("legalAct: " + legalAct);
			request.setAttribute("legalAct", legalAct);
			request.getRequestDispatcher("/actMetadata.jsp").forward(request, response);				
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
