package rs.ac.uns.ftn.informatika.legal.lawyer.controller;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalNorm;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.ServiceRegistry;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.query.rdf.RDFQueryService;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.resolver.ResolverService;
import rs.ac.uns.ftn.informatika.legal.lawyer.util.IdComparator;

public class NormElementsController extends HttpServlet {

	private static final long serialVersionUID = 8404278303729190140L;
	
	private static Logger log = Logger.getLogger(NormElementsController.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String provisionId = request.getParameter("provisionId");
		String legalNormId = request.getParameter("legalNormId");
		
		RDFQueryService qs = ServiceRegistry.getRDFQueryService();
		LegalNorm legalNorm = null;
		if (provisionId != null && !provisionId.equals("")) {
			legalNormId = qs.getLegalNormIdByProvisionId(provisionId); 
			if (legalNormId != null && !legalNormId.equals("")) {
				legalNorm = qs.getLegalNorm(legalNormId);
			}	
		} else if (legalNormId != null && !legalNormId.equals("")) {
			legalNorm = qs.getLegalNorm(legalNormId);
		}
		
		if (legalNorm != null) {
			ResolverService rs = ServiceRegistry.getResolverService();

			List<String> dispositionUrls = new ArrayList<String>();
			if (legalNorm.getDisposition() != null) {
				
				Set<URI> uriReferences = legalNorm.getDisposition().getUri();
				for (URI uriReference: uriReferences) {
					String fragmentId = rs.getFragmentIdFromUriReference(uriReference);
					dispositionUrls.add(format(fragmentId));
				}
			}
			Collections.sort(dispositionUrls, new IdComparator());
			request.setAttribute("dispositionUrls", dispositionUrls);
			
			List<String> dispositionHypothesisUrls = new ArrayList<String>();
			if (legalNorm.getDispositionHypothesis() != null) {
				Set<URI> uriReferences = legalNorm.getDispositionHypothesis().getUri();
				for (URI uriReference: uriReferences) {
					String fragmentId = rs.getFragmentIdFromUriReference(uriReference);
					dispositionHypothesisUrls.add(format(fragmentId));
				}
			}
			Collections.sort(dispositionHypothesisUrls, new IdComparator());
			request.setAttribute("dispositionHypothesisUrls", dispositionHypothesisUrls);
			
			List<String> sanctionUrls = new ArrayList<String>();
			if (legalNorm.getSanction() != null) {
				Set<URI> uriReferences = legalNorm.getSanction().getUri();
				for (URI uriReference: uriReferences) {
					String fragmentId = rs.getFragmentIdFromUriReference(uriReference);
					sanctionUrls.add(format(fragmentId));
				}
			}
			Collections.sort(sanctionUrls, new IdComparator());
			request.setAttribute("sanctionUrls", sanctionUrls);
			
			List<String> sanctionHypothesisUrls = new ArrayList<String>();
			if (legalNorm.getSanctionHypothesis() != null) {
				Set<URI> uriReferences = legalNorm.getSanctionHypothesis().getUri();
				for (URI uriReference: uriReferences) {
					String fragmentId = rs.getFragmentIdFromUriReference(uriReference);
					sanctionHypothesisUrls.add(format(fragmentId));
				}
			}
			Collections.sort(sanctionHypothesisUrls, new IdComparator());
			request.setAttribute("sanctionHypothesisUrls", sanctionHypothesisUrls);
			
			List<String> exceptionUrls = new ArrayList<String>();
			if (legalNorm.getException() != null) {
				Set<URI> uriReferences = legalNorm.getException().getUri();
				for (URI uriReference: uriReferences) {
					String fragmentId = rs.getFragmentIdFromUriReference(uriReference);
					exceptionUrls.add(format(fragmentId));
				}
			}
			Collections.sort(exceptionUrls, new IdComparator());
			request.setAttribute("exceptionUrls", exceptionUrls);
		}		
		
		request.getRequestDispatcher("/normElements.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String format(String fragmentId) {
		String[] tokens = fragmentId.split("-");
		String reference = "";
		int art = 0;
		int it = 0;
		int pt = 0;
		int spt = 0;
		int ln = 0;
		
		for (String token: tokens) {
			if (token.startsWith("art")) {
				art = Integer.parseInt(token.substring(3));
				reference += "Article " + art;
			} else if (token.startsWith("it")) {
				it = Integer.parseInt(token.substring(2));
				reference += ", Item " + it;
			} else if (token.startsWith("pt")) {
				pt = Integer.parseInt(token.substring(2));
				reference += ", Point " + pt;
			} else if (token.startsWith("spt")) {
				spt = Integer.parseInt(token.substring(3));
				reference += ", Subpoint " + spt;
			} else if (token.startsWith("ln")) {
				ln = Integer.parseInt(token.substring(2));
				reference += ", Line " + art;
			}
		}
		
		return reference;
	}
}
