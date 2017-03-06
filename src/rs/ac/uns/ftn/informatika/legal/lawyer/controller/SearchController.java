package rs.ac.uns.ftn.informatika.legal.lawyer.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalBranch;
import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalInstitution;
import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalRelation;
import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalSubject;
import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalSystem;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.ServiceRegistry;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.query.rdf.RDFQueryService;

public class SearchController extends HttpServlet {

	private static final long serialVersionUID = 7910115738475795672L;
	
	private static Logger log = Logger.getLogger(SearchController.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");
		
		RDFQueryService qs = ServiceRegistry.getRDFQueryService();
		
		Collection<LegalSystem> legalSystems = qs.getLegalSystems();
		request.setAttribute("legalSystems", legalSystems);
		Collection<LegalBranch> legalBranches = qs.getLegalBranches();
		request.setAttribute("legalBranches", legalBranches);
		Collection<LegalInstitution> legalInstitutions = qs.getLegalInstitutions();
		request.setAttribute("legalInstitutions", legalInstitutions);
		Collection<LegalSubject> legalSubjects = qs.getLegalSubjects();
		request.setAttribute("legalSubjects", legalSubjects);
		Collection<LegalRelation> legalRelations = qs.getLegalRelations();
		request.setAttribute("legalRelations", legalRelations);
		
		/*
		Collection<LegalSubject> ljs = qs.getLegalRelations();
		request.setAttribute("legalJurisdictions", ljs);
		*/
		
		request.getRequestDispatcher("/search.jsp").forward(request, response);		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
