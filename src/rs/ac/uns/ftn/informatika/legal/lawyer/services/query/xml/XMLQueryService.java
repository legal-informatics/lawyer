package rs.ac.uns.ftn.informatika.legal.lawyer.services.query.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

import javax.xml.transform.OutputKeys;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.BinaryResource;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import rs.ac.uns.ftn.informatika.legal.lawyer.services.Service;

public class XMLQueryService extends Service {

	private static Logger log = Logger.getLogger(XMLQueryService.class);

	String uri = null;
	String coll = null;
	String driver = null;
	String username = null;
	String password = null;

	protected final String[][] namespaces = { { "pf",
			"http://informatika.ftn.uns.ac.rs/legal/metalex/1.0/strict" } };

	public XMLQueryService() {
		// Set service name
		serviceName = "rs.ac.uns.ftn.informatika.metalex.services.query.xml.XMLQueryService";

		// Initialize database
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("lawyer.properties");
		Properties properties = new Properties();
		try {
			properties.load(is);
			uri = properties.getProperty("xml.uri");
			coll = properties.getProperty("xml.collection");
			driver = properties.getProperty("xml.driver");
			username = properties.getProperty("xml.username");
			password = properties.getProperty("xml.password");
			Class<?> cl = Class.forName(driver); // TODO
			Database database = (Database) cl.newInstance();
			DatabaseManager.registerDatabase(database);
			log.info("XMLQueryService Initialized.");
		} catch (IOException e) {
			log.error(e);
		} catch (ClassNotFoundException e) {
			log.error(e);
		} catch (InstantiationException e) {
			log.error(e);
		} catch (IllegalAccessException e) {
			log.error(e);
		} catch (XMLDBException e) {
			log.error(e);
		}
	}

	/**
	 * @return The article from its identifier
	 */
	public String getArticle(String id) {
		try {
			ResourceSet rs = query("//pf:article[@id='" + id + "']");
			if (rs != null) {
				ResourceIterator ri = rs.getIterator();

				if (ri.hasMoreResources()) {
					Resource r = ri.nextResource();
					if (r != null) {
						return (String) r.getContent();
					}
				}
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] getAttachment(String resourceId) {
		System.out.println("resourceId: " + resourceId);
		try { 
			Collection collection = DatabaseManager.getCollection(uri + coll);
			//BinaryResource resource = (BinaryResource)collection.getResource(resourceId); 
			BinaryResource resource = (BinaryResource)collection.getResource(resourceId);
			if(resource != null) 
				return (byte[])resource.getContent(); 
		  } catch (XMLDBException e) { 
			  log.error(e, e); 
		}
		  
		 return null; 
	}

	public String getAttachments(String resource) {
		String query = "//pf:attachment";
		ResourceSet rs = query(resource, query);
		StringBuffer sb = new StringBuffer();

		try {
			if (rs != null) {
				ResourceIterator ri = rs.getIterator();
				while (ri.hasMoreResources()) {
					Resource r = ri.nextResource();
					if (r != null) {
						sb.append((String) r.getContent());
					}
				}
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public String getElementText(String resource, String id) {
		String query = "//*[@id=\"" + id + "\"]";
		ResourceSet rs = query(resource, query);
		try {
			if (rs != null) {
				ResourceIterator ri = rs.getIterator();
				if (ri.hasMoreResources()) {
					Resource r = ri.nextResource();
					if (r != null) {
						return ((String) r.getContent()).replaceAll("\\<.*?>",
								"").replaceAll("\\s+", " ");
					}
				}
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Node getHeadingsAsNode(String resource) {
		String query = "//pf:heading";
		ResourceSet rs = query(resource, query);
		Node node = null;

		try {
			if (rs != null) {
				ResourceIterator ri = rs.getIterator();
				while (ri.hasMoreResources()) {
					Resource r = ri.nextResource();
					if (r != null && r.getResourceType().equals("XMLResource")) {
						node = ((XMLResource) r).getContentAsDOM();
					}
				}
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		return node;
	}

	public String getHeadingsAsString(String resource) {
		String query = "//pf:heading";
		ResourceSet rs = query(resource, query);
		StringBuffer sb = new StringBuffer();

		try {
			if (rs != null) {
				ResourceIterator ri = rs.getIterator();
				while (ri.hasMoreResources()) {
					Resource r = ri.nextResource();
					if (r != null) {
						sb.append((String) r.getContent());
					}
				}
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * @return The provision from its identifier
	 */
	public String getProvision(String resource, String id) {
		String query = "//pf:provision[@id='" + id + "']";
		ResourceSet rs = query(resource, query);
		try {
			if (rs != null) {
				ResourceIterator ri = rs.getIterator();
				if (ri.hasMoreResources()) {
					Resource r = ri.nextResource();
					if (r != null) {
						return (String) r.getContent();
					}
				}
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return The provision content from its identifier
	 */
	public String getProvisionText(String resource, String id) {
		String query = "//pf:provision[@id=\"" + id + "\"]/text()";
		ResourceSet rs = query(resource, query);
		try {
			if (rs != null) {
				ResourceIterator ri = rs.getIterator();
				if (ri.hasMoreResources()) {
					Resource r = ri.nextResource();
					if (r != null) {
						return (String) r.getContent();
					}
				}
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return The resource from its identifier
	 */
	public Resource getResource(String resourceId) {
		try {
			// Get the collection
			Collection collection = DatabaseManager.getCollection(uri + coll);
			collection.setProperty(OutputKeys.INDENT, "no");

			XPathQueryService service = (XPathQueryService) collection
					.getService("XPathQueryService", "1.0");
			service.setProperty("indent", "yes");

			XMLResource resource = (XMLResource) collection
					.getResource(resourceId);
			if (resource != null)
				return resource;
		} catch (XMLDBException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Node getResourceAsNode(String resourceId) {
		try {
			Collection collection = DatabaseManager.getCollection(uri + coll);
			collection.setProperty(OutputKeys.INDENT, "no");

			XPathQueryService service = (XPathQueryService) collection
					.getService("XPathQueryService", "1.0");
			service.setProperty("indent", "yes");

			XMLResource resource = (XMLResource) collection
					.getResource(resourceId);
			if (resource != null) {
				Node node = resource.getContentAsDOM();
				return node;
			}
		} catch (XMLDBException e) {
			log.error(e);
		}
		return null;
	}

	public String getResourceAsString(String resourceId) {
		try {
			Collection collection = DatabaseManager.getCollection(uri + coll);
			collection.setProperty(OutputKeys.INDENT, "no");
			XMLResource resource = (XMLResource) collection
					.getResource(resourceId);
			if (resource != null
					&& resource.getResourceType().equals("XMLResource")) {
				return (String) resource.getContent();
			}
		} catch (XMLDBException e) {
			log.error(e);
		}

		return null;
	}

	/**
	 * @return Executes custom XPath query
	 */
	public ResourceSet query(String query) {
		try {
			// Get the collection
			Collection collection = DatabaseManager.getCollection(uri + coll);

			XPathQueryService service = (XPathQueryService) collection
					.getService("XPathQueryService", "1.0");
			service.setProperty("indent", "yes");
			service.setNamespace(namespaces[0][0], namespaces[0][1]);

			ResourceSet rs = service.query(query);
			if (rs != null)
				return rs;
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ResourceSet query(String resource, String query) {
		try {
			// Get the collection
			Collection collection = DatabaseManager.getCollection(uri + coll);

			XPathQueryService service = (XPathQueryService) collection
					.getService("XPathQueryService", "1.0");
			service.setProperty("indent", "yes");
			service.setNamespace(namespaces[0][0], namespaces[0][1]);

			ResourceSet rs = service.queryResource(resource, query);
			if (rs != null)
				return rs;
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		XMLQueryService qs = new XMLQueryService();
		/*
		 * String resource = "privacy.xml"; String query =
		 * "//pf:provision[@id=\"art63-it1-p1\"]/text()"; //String query = "/";
		 * ResourceSet rs = qs.query(resource, query); try { if (rs != null) {
		 * ResourceIterator ri = rs.getIterator(); while (ri.hasMoreResources())
		 * { Resource r = ri.nextResource(); if (r != null) {
		 * System.out.println((String)r.getContent()); } } } } catch
		 * (XMLDBException e) { e.printStackTrace(); }
		 */
		System.out.println(qs.getElementText("privacy.xml", "art19"));
		// System.out.println(qs.getHeadings("privacy.xml"));
		// System.out.println(qs.getXMLResourceAsString("privacy.xml"));
	}

}