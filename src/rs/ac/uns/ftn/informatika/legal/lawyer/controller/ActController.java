package rs.ac.uns.ftn.informatika.legal.lawyer.controller;

import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.legal.lawyer.services.ServiceRegistry;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.query.xml.XMLQueryService;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.resolver.ResolverService;

public class ActController extends HttpServlet {

	private static final long serialVersionUID = 4231449412796771593L;
	
	private static Logger log = Logger.getLogger(ActController.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("Act Start");
		String uriReference = request.getParameter("legalActUri");
		//System.out.println("legalActUri: " + uriReference);
		if (uriReference != null && !uriReference.equals("")) {
			ResolverService rs = ServiceRegistry.getResolverService();

			try {
				URI urn = rs.getUriFromUriReference(new URI(uriReference));
				//System.out.println("urn: " + urn);
				URI url = rs.getUrlFromUrn(urn);
				//System.out.println("resource: " + url);
				XMLQueryService qs = ServiceRegistry.getXMLQueryService();
				String content = qs.getResourceAsString(url.toString());
				
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/xml");
				Writer writer = response.getWriter();
				writer.write("<div id=\"act\">\n");
				writer.write(content);
				writer.write("</div>\n");
			} catch (URISyntaxException e) {
				log.error(e);
			}	
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
