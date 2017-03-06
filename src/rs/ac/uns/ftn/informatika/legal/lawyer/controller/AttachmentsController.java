package rs.ac.uns.ftn.informatika.legal.lawyer.controller;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import rs.ac.uns.ftn.informatika.legal.lawyer.services.ServiceRegistry;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.query.xml.XMLQueryService;
import rs.ac.uns.ftn.informatika.legal.lawyer.services.resolver.ResolverService;

public class AttachmentsController extends HttpServlet {

	private static final long serialVersionUID = 274748817530577089L;
	
	private static Logger log = Logger.getLogger(AttachmentsController.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		String uriReference = request.getParameter("legalActUri");
		if (uriReference != null && !uriReference.equals("")) {
			ResolverService rs = ServiceRegistry.getResolverService();
			try {
				URI urn = rs.getUriFromUriReference(new URI(uriReference));
				URI url = rs.getUrlFromUrn(urn);
				
				XMLQueryService qs = ServiceRegistry.getXMLQueryService();
				Node node = qs.getResourceAsNode(url.toString());
				String string = qs.getResourceAsString(url.toString());
				
				// TODO: Use FileService instead
				String path = getServletContext().getRealPath("WEB-INF/xslt/attachments.xslt");
				Writer writer = response.getWriter();
				writer.write("<div id=\"toc\">\n");
				TransformerFactory factory = TransformerFactory.newInstance();
				Transformer transformer = factory.newTransformer(new StreamSource(new File(path)));
				transformer.transform(new StreamSource(new StringReader(string), "UTF-8"), new StreamResult(writer));
				writer.write("</div>\n");
				writer.flush();
				writer.close();
			} catch (URISyntaxException e) {
				log.error(e);
			} catch (TransformerException e) {
				log.error(e);
			}	
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
