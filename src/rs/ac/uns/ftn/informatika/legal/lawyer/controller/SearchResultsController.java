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

public class SearchResultsController extends HttpServlet {
	
	private static final long serialVersionUID = -6006424460725471501L;
	
	private static Logger log = Logger.getLogger(SearchResultsController.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long startTime = System.nanoTime();
		String provisionId = request.getParameter("provisionId");
		if (provisionId != null && !provisionId.equals("")) {
			RDFQueryService qs = ServiceRegistry.getRDFQueryService();
			Collection<LegalNorm> legalNorms = qs.getLegalNormsByProvisionId(provisionId); 
			request.setAttribute("legalNorms", legalNorms);
			long endTime = System.nanoTime();
			request.setAttribute("duration", endTime - startTime);
		}
		request.getRequestDispatcher("/searchResults.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
