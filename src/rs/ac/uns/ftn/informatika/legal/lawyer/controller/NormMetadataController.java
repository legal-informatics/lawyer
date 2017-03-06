package rs.ac.uns.ftn.informatika.legal.lawyer.controller;

import java.io.IOException;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalNorm;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.ServiceRegistry;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.query.rdf.RDFQueryService;

public class NormMetadataController extends HttpServlet {

	private static final long serialVersionUID = 8404278303729190140L;
	
	private static Logger log = Logger.getLogger(NormMetadataController.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String provisionId = request.getParameter("provisionId");
		String legalNormId = request.getParameter("legalNormId");
		
		LegalNorm legalNorm = null;
		if (provisionId != null && !provisionId.equals("")) {
			RDFQueryService qs = ServiceRegistry.getRDFQueryService();
			try {
				legalNormId = qs.getLegalNormIdByProvisionId(provisionId);
				if (legalNormId != null && !legalNormId.equals("")) {
					legalNorm = qs.getLegalNorm(legalNormId);
				}
			} catch (NoResultException e) {
				// Do nothing
			} catch (Exception e) {
				log.error("Following exception has occured: ", e);
			}
		} else if (legalNormId != null && !legalNormId.equals("")) {
			RDFQueryService qs = ServiceRegistry.getRDFQueryService(); 
			try {
				legalNorm = qs.getLegalNorm(legalNormId);
			} catch (NoResultException e) {
				// Do nothing
			} catch (Exception e) {
				log.error("Following exception has occured: ", e);
			}
		}
		request.setAttribute("legalNorm", legalNorm);
		request.getRequestDispatcher("/normMetadata.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
