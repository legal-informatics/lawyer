package rs.ac.uns.ftn.informatika.legal.lawyer.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalBranch;
import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalInstitution;
import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalNorm;
import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalRelation;
import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalSubject;
import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalSystem;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.ServiceRegistry;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.query.rdf.RDFQueryService;

public class NormsController extends HttpServlet {
	
	private static final long serialVersionUID = -6541106696481362323L;
	
	private static final String dateFormat = "yyyy-MM-dd";
	
	private static Logger log = Logger.getLogger(NormsController.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long startTime = System.nanoTime();
		RDFQueryService qs = ServiceRegistry.getRDFQueryService();
		
		String legalSystemId = request.getParameter("legalSystem");
		LegalSystem legalSystem = null;
		if (legalSystemId != null && !legalSystemId.equals("")) {
			legalSystem = qs.getLegalSystem(legalSystemId);
		}
		
		/*
		String legalAreaId = request.getParameter("legalArea");
		LegalArea legalArea = null;
		if (legalAreaId != null && !legalAreaId.equals("")) {
			legalArea = qs.getLegalArea(legalAreaId);
		}
		*/
		
		String legalBranchId = request.getParameter("legalBranch");
		LegalBranch legalBranch = null;
		if (legalBranchId != null && !legalBranchId.equals("")) {
			legalBranch = qs.getLegalBranch(legalBranchId);
		}
		
		String legalInstitutionId = request.getParameter("legalInstitution");
		LegalInstitution legalInstitution = null;
		if (legalInstitutionId != null && !legalInstitutionId.equals("")) {
			legalInstitution = qs.getLegalInstitution(legalInstitutionId);
		}
		
		String legalRelationId = request.getParameter("legalRelation");
		LegalRelation legalRelation = null;
		if (legalRelationId != null && !legalRelationId.equals("")) {
			legalRelation = qs.getLegalRelation(legalRelationId);
		}

		String legalSubjectId = request.getParameter("legalSubject");
		LegalSubject legalSubject = null;
		if (legalSubjectId != null && !legalSubjectId.equals("")) {
			legalSubject = qs.getLegalSubject(legalSubjectId);
		}
		
		boolean rightSubject = Boolean.parseBoolean(request.getParameter("rightSubject"));
		
		boolean dutySubject = Boolean.parseBoolean(request.getParameter("dutySubject"));
		
		boolean obligationSubject = Boolean.parseBoolean(request.getParameter("obligationSubject"));
		
		boolean prohibitionSubject = Boolean.parseBoolean(request.getParameter("prohibitionSubject"));
		
		boolean competenceSubject = Boolean.parseBoolean(request.getParameter("competenceSubject"));
		
		String validity = request.getParameter("validity");
		Date date = null;
		if (validity.equals("today")) {
			date = new Date();
		} else if (validity.equals("specific")) {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			try {
				date = sdf.parse(request.getParameter("date"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String jurisdictionId = request.getParameter("jurisdiction");
		LegalSubject jurisdiction = null;
		if (jurisdictionId != null && !jurisdictionId.equals("")) {
			jurisdiction = qs.getLegalSubject(jurisdictionId);
		}
		
		Collection<LegalNorm> legalNorms = qs.getLegalNorms(legalInstitution, legalRelation, legalSubject, rightSubject, dutySubject, obligationSubject, prohibitionSubject, competenceSubject, date, jurisdiction);
		long endTime = System.nanoTime();
		request.setAttribute("legalNorms", legalNorms);
		request.setAttribute("duration", endTime - startTime);
		
		request.getRequestDispatcher("/norms.jsp").forward(request, response);		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
