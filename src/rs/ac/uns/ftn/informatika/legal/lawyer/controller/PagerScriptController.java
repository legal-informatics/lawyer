package rs.ac.uns.ftn.informatika.legal.lawyer.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalNorm;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.ServiceRegistry;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.query.rdf.RDFQueryService;

public class PagerScriptController extends HttpServlet {
	
	private static final long serialVersionUID = -6006424460725471501L;
	
	private static Logger log = Logger.getLogger(PagerScriptController.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String provisionId = request.getParameter("provisionId");
		if (provisionId != null && !provisionId.equals("")) {
			RDFQueryService qs = ServiceRegistry.getRDFQueryService();
			Collection<LegalNorm> legalNorms = qs.getLegalNormsByProvisionId(provisionId); 
			request.setAttribute("legalNorms", legalNorms);
		}
		request.getRequestDispatcher("/pagerScript.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
