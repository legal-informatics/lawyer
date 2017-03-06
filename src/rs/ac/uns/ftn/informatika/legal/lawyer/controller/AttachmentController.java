package rs.ac.uns.ftn.informatika.legal.lawyer.controller;

import java.io.IOException;
import java.io.OutputStream;
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

public class AttachmentController extends HttpServlet {

	private static final long serialVersionUID = 274748817530577089L;
	
	private static Logger log = Logger.getLogger(AttachmentController.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		String attachmentUri = request.getParameter("attachmentUri");
		String fileName = request.getParameter("fileName");
		System.out.println("fileName: " + fileName);
		if (attachmentUri != null && !attachmentUri.equals("") && fileName != null && !fileName.equals("")) {
			ResolverService rs = ServiceRegistry.getResolverService();
			try {
				URI attachmentUrl = rs.getUrlFromUrn(new URI(attachmentUri));
				String contentType = rs.getContentTypeFromUrn(new URI(attachmentUri));
				XMLQueryService qs = ServiceRegistry.getXMLQueryService();
				byte[] content = qs.getAttachment(attachmentUrl.toString());
				response.setContentType(contentType);
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
				OutputStream out = response.getOutputStream();
				out.write(content);
				out.flush();
				out.close();
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
