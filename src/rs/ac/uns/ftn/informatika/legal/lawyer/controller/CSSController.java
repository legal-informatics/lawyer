package rs.ac.uns.ftn.informatika.legal.lawyer.controller;

import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.Disposition;
import rs.ac.uns.ftn.informatika.legal.lawyer.model.rdf.LegalNorm;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.ServiceRegistry;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.file.FileService;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.query.rdf.RDFQueryService;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.resolver.ResolverService;

public class CSSController extends HttpServlet {

	private static final long serialVersionUID = 2516579398163047105L;
	
	private static Logger log = Logger.getLogger(CSSController.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Writer writer = response.getWriter();
		writer.write("<style id=\"dynamic\" type=\"text/css\">\n");
		
		String legalNormId = request.getParameter("legalNormId");
		String provisionId = request.getParameter("provisionId");
		LegalNorm legalNorm = null;
		if (provisionId != null && !provisionId.equals("")) {
			RDFQueryService qs = ServiceRegistry.getRDFQueryService();
			legalNormId = qs.getLegalNormIdByProvisionId(provisionId);
			if (legalNormId != null && !legalNormId.equals("")) {
				legalNorm = qs.getLegalNorm(legalNormId);
			}
		} else if (legalNormId != null && !legalNormId.equals("")) {
			RDFQueryService qs = ServiceRegistry.getRDFQueryService();
			legalNorm = qs.getLegalNorm(legalNormId);
		}
			
		if (legalNorm != null) {
			ResolverService rs = ServiceRegistry.getResolverService();
			
			if (legalNorm.getDisposition() != null) {
				Set<URI> uriReferences = legalNorm.getDisposition().getUri();
				for (URI uriReference: uriReferences) {
					String fragmentId = rs.getFragmentIdFromUriReference(uriReference);
					writer.write("#" + fragmentId + " { background-color: yellow }\n");
				}
				writer.write("\n");
			}
			
			if (legalNorm.getDispositionHypothesis() != null) {
				Set<URI> uriReferences = legalNorm.getDispositionHypothesis().getUri();
				for (URI uriReference: uriReferences) {
					String fragmentId = rs.getFragmentIdFromUriReference(uriReference);
					writer.write("#" + fragmentId + " { background-color: lime }\n");
				}
				writer.write("\n");
			}
			
			if (legalNorm.getSanction() != null) {
				Set<URI> uriReferences = legalNorm.getSanction().getUri();
				for (URI uriReference: uriReferences) {
					String fragmentId = rs.getFragmentIdFromUriReference(uriReference);
					writer.write("#" + fragmentId + " { background-color: aqua }\n");
				}
				writer.write("\n");
			}
			
			if (legalNorm.getSanctionHypothesis() != null) {
				Set<URI> uriReferences = legalNorm.getSanctionHypothesis().getUri();
				for (URI uriReference: uriReferences) {
					String fragmentId = rs.getFragmentIdFromUriReference(uriReference);
					writer.write("#" + fragmentId + " { background-color: fuchsia }\n");
				}
				writer.write("\n");
			}
			
			if (legalNorm.getException() != null) {
				Set<URI> uriReferences = legalNorm.getException().getUri();
				for (URI uriReference: uriReferences) {
					String fragmentId = rs.getFragmentIdFromUriReference(uriReference);
					writer.write("#" + fragmentId + " { background-color: silver }\n");
				}
				writer.write("\n");
			}
		}		
				
		writer.write("</style>\n");
		writer.flush();
		writer.close();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
