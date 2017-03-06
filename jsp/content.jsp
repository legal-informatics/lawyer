<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- Regulation CSS -->
<?xml-stylesheet href="css/regulation.css" type="text/css"?>
<!-- Lawyer CSS -->
<?xml-stylesheet href="css/lawyer.css" type="text/css"?>
<!-- JQuery CSS -->
<?xml-stylesheet href="css/humanity/jquery-ui-1.8.9.custom.css" type="text/css"?>
<?xml-stylesheet href="css/pagination.css" type="text/css"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

	<head>
		<title>Lawyer</title>
		
		<!-- Regulation CSS -->
		<link type="text/css" href="css/regulation.css" rel="stylesheet" />
		<!-- Lawyer CSS -->
		<link type="text/css" href="css/lawyer.css" rel="stylesheet" />
		<!-- JQuery CSS -->
    	<link type="text/css" href="css/humanity/jquery-ui-1.8.9.custom.css" rel="stylesheet" />
    	<link type="text/css" href="css/pagination.css" rel="stylesheet" />
    	<!-- Custom CSS -->
    	<style id="dynamic" type="text/css">
    	
    	</style>
    	
    	<!-- JQuery Scripts -->
    	<script type="text/javascript" src="js/jquery-1.4.4.min.js" ></script>
    	<script type="text/javascript" src="js/jquery-ui-1.8.9.custom.min.js" ></script>
    	<script type="text/javascript" src="js/pagination.js" ></script>
		<!-- Custom Scripts -->
		<script type="text/javascript" src="js/lawyer.js" ></script>
		<script type="text/javascript">
			
			
			function searchResults(provision) {
				jQuery.post("SearchResults", {provisionId: provision}, function(data) {
		        	$("div#searchResults").replaceWith(data);
		        }, "html");
				/*jQuery.post("PagerScript", {provisionId: provision}, function(data) {
		        	$("script#pagerScript").replaceWith(data);
		        }, "html");	
				$(".pagination").pagination(items.length - 1, {
		        	items_per_page: 5,
		        	callback: callback
				});
				*/
			}
			
			function css(provision, legalNorm) {
				jQuery.post("CSS", {provisionId: provision, legalNormId: legalNorm}, function(data) {
					$("style#dynamic").replaceWith(data);
		        }, "html");
			}
			
			function normMetadata(provision, legalNorm) {
				jQuery.post("NormMetadata", {provisionId: provision, legalNormId: legalNorm}, function(data) {
		        	$("div#normMetadata").replaceWith(data);
		        }, "html");
			}
			
			function normElements(provision, legalNorm) {
				jQuery.post("NormElements", {provisionId: provision, legalNormId: legalNorm}, function(data) {
					$("div#normElements").replaceWith(data);
		        }, "html");
			}
			
			function caseLaw(provision, legalNorm) {
				jQuery.post("CaseLaw", {provisionId: provision, legalNormId: legalNorm}, function(data) {
					$("div#caseLaw").replaceWith(data);
				}, "html");
			}
			
			function expertOpinions(provision, legalNorm) {
				jQuery.post("ExpertOpinions", {provisionId: provision, legalNormId: legalNorm}, function(data) {
					$("div#expertOpinions").replaceWith(data);
				}, "html");
			}
			
			function toc(legalAct) {
				jQuery.post("TOC", {legalActUri: legalAct}, function(data) {
					$("div#toc").replaceWith(data);
				}, "html");
			}
			
			function attachments(legalAct) {
				jQuery.post("Attachments", {legalActUri: legalAct}, function(data) {
					$("div#attachments").replaceWith(data);
				}, "html");
			}
			
			function bylaws(legalAct) {
				jQuery.post("Bylaws", {legalActUri: legalAct}, function(data) {
				       	$("div#bylaws").replaceWith(data);
				}, "html");
			}
			
			function actMetadata(legalAct) {
				jQuery.post("ActMetadata", {legalActUri: legalAct}, function(data) {
					$("div#actMetadata").replaceWith(data);
				}, "html");
			}
			
			// Moves the divider between norm's metadata and act's content
			var position = "center";
			
			function left() {
				if (position == "center") {
				 	$("#norm").css({"display": "none"});
				 	$("#buttons").css({"left": "0%", "right": "96%"});
				 	$("#act").css({"left": "4%", "right": "0%", "width": "90%"});
					position = "left";
				} else if (position == "right") {
					$("#norm").css({"left": "0%", "right": "52%", "width": "44%"});
					$("#buttons").css({"left": "48%", "right": "48%"});
					$("#act").css({"display": "block"});
					position = "center";
				}
			}
			
			function right() {
				if (position == "center") {
				 	$("#norm").css({"left": "0", "right": "4%", "width": "90%"});
				 	$("#buttons").css({"left": "96%", "right": "6%"});
				 	$("#act").css({"display": "none"});
					position = "right";
				} else if (position == "left") {
					$("#norm").css({"display": "block"});
					$("#buttons").css({"left": "48%", "right": "48%"});
					$("#act").css({"left": "52%", "right": "0%", "width": "44%"});
					position = "center";
				}
			}
			
			function provision(event) {
				var prefix = $("regulation").attr("xml:base");
				var id = event.target.id;
				
				// norm properties
				searchResults(prefix + "#" + id)
				css(prefix + "#" + id, "");
				normMetadata(prefix + "#" + id, "");
				normElements(prefix + "#" + id, "");
				caseLaw(prefix + "#" + id, "");
				expertOpinions(prefix + "#" + id, "");
			}
			
			$(document).ready(function() {
				// Initialize AJAX components
				
				// Norm properties
				css("", "${legalNorm.rdfId}");
				normMetadata("", "${legalNorm.rdfId}");
				normElements("", "${legalNorm.rdfId}");
				caseLaw("", "${legalNorm.rdfId}");
				expertOpinions("", "${legalNorm.rdfId}");
				
				// Act properties
				// Pay attention to differences between rdfId and uri properties!!!
				toc("${legalAct.uri}"); 
				attachments("${legalAct.uri}");
				bylaws("${legalAct.rdfId}");
				actMetadata("${legalAct.rdfId}");
				
				// Sets JQuery UI elements
				
				// Tabs
				$(".tabs").tabs({ 
					selected: 1 
				});
				
				
				// Accordion
				// JSP page is called from find norms
				<c:if test="${legalNorm.disposition != null}">
				$(".accordion").accordion({
					fillSpace: true,
					active: 1
				});
				</c:if>
				// JSP page is called from find acts
				<c:if test="${legalNorm.disposition == null}">
				$(".accordion").accordion({
					fillSpace: true,
					collapsible: true,
					active: false
				});
				left();
				</c:if>
				
				// Sets event handlers
				
				// Left button clicked
				$("#leftButton").click(left);
				
				// Right button clicked
				$("#rightButton").click(right);
				
				// Provision clicked
				$("provision").click(function(event){provision(event);});
			});
		
		</script>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</head>

	<body>
		
		<jsp:include page="header.jsp" flush="true" />
 		
		<div id="body" class="tabs">
			<ul>
				<li><a href="#tab1">Table of Content</a></li>
				<li><a href="#tab2">Content</a></li>
				<li><a href="#tab3">Attachments</a></li>
				<li><a href="#tab4">Bylaws</a></li>
				<li><a href="#tab5">Metadata</a></li>
			</ul>
			
			<div id="tab1">
				<div id="toc">
					<p>test</p>
				</div>
			</div>	
						
			<div id="tab2">				
		 		<div id="norm">
		 			<div class="accordion" style="margin: 0 0 0 0; padding: 0 0 0 0; border: 0 0 0 0">
						<h3><a href="#">Search Results</a></h3>
    					<div><div id="searchResults"></div></div>
    					<h3><a href="#">Metadata</a></h3>
    					<div><div id="normMetadata"></div></div>
    					<h3><a href="#">Elements of Legal Norm</a></h3>
    					<div><div id="normElements">disposition</div></div>
    					<h3><a href="#">Case Law</a></h3>
    					<div><div id="caseLaw"></div></div>
    					<h3><a href="#">Expert Opinions</a></h3>
    					<div><div id="expertOpinions"></div></div>
					</div>
		 		</div>
		 		
		 		<div id="buttons">
		 			<button id="leftButton" type="button" style="position:absolute; top:50%; height:50px; margin-top:-50px;">&lt;</button>
		 			<button id="rightButton" type="button" style="position:absolute; top:50%; height:50px; margin-top:-0px;">&gt;</button>
		 		</div>
				 
	  			<c:if test="${legalAct != null}">
	  				<c:url value="Act" var="link">
	  					<c:param name="legalActUri" value="${legalAct.uri}" />
	  				</c:url>
	  				<jsp:include page="${link}" flush="true" />
	  			</c:if>
	  			 
			</div>
			
			<div id="tab3">
				<div id="attachments">
				</div>
			</div>	
			
			<div id="tab4">
				<div id="bylaws">
				</div>
			</div>
		 	
			<div id="tab5">
				<div id="actMetadata">
				</div>
			</div>
		</div>

		<jsp:include page="footer.jsp" flush="true" />
	</body>
</html>