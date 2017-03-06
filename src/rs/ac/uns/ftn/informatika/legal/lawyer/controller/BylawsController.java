package rs.ac.uns.ftn.informatika.legal.lawyer.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.Bylaw;
import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.GeneralLegalAct;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.ServiceRegistry;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.query.rdf.RDFQueryService;

public class BylawsController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(BylawsController.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uriReference = request.getParameter("legalActUri");
		if (uriReference != null && !uriReference.equals("")) {
			RDFQueryService qs = ServiceRegistry.getRDFQueryService(); 
			GeneralLegalAct legalAct = qs.getGeneralLegalAct(uriReference.toString());
			Collection<Bylaw> bylaws = legalAct.getBylaws();
			request.setAttribute("bylaws", bylaws);
			request.getRequestDispatcher("/bylaws.jsp").forward(request, response);				
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
