package rs.ac.uns.ftn.informatika.legal.lawyer.controller;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.GeneralLegalAct;
import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalAct;
import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalBranch;
import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalInstitution;
import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalSubject;
import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalSystem;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.ServiceRegistry;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.query.rdf.RDFQueryService;

public class ActsController extends HttpServlet {

	private static final long serialVersionUID = 3549443772940608337L;
	
	private static final String dateFormat = "yyyy-MM-dd";
	
	private static Logger log = Logger.getLogger(ActsController.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		long startTime = System.nanoTime();
		RDFQueryService qs = ServiceRegistry.getRDFQueryService();
		
		String legalSystemId = request.getParameter("legalSystem");
		LegalSystem legalSystem = null;
		if (legalSystemId != null && !legalSystemId.equals("")) {
			legalSystem = qs.getLegalSystem(legalSystemId);
		}
		
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
		
		String title = request.getParameter("title");
		
		String gazette = request.getParameter("gazette");
		
		String content = request.getParameter("content");
		
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
		
		Collection<GeneralLegalAct> legalActs = qs.getLegalActs(legalInstitution, title, gazette, content, date, jurisdiction);
		long endTime = System.nanoTime();
		request.setAttribute("legalActs", legalActs);
		request.setAttribute("duration", endTime - startTime);
		
		request.getRequestDispatcher("/acts.jsp").forward(request, response);		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
