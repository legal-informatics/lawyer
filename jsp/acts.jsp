<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  

<?xml-stylesheet href="css/lawyer.css" type="text/css"?>
<?xml-stylesheet href="css/humanity/jquery-ui-1.8.9.custom.css" type="text/css"?>
<?xml-stylesheet href="css/pagination.css" type="text/css"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

	<head>
		<title>Lawyer</title>
		
		<link type="text/css" href="css/lawyer.css" rel="stylesheet" />
    	<link type="text/css" href="css/humanity/jquery-ui-1.8.9.custom.css" rel="stylesheet" />
		<link type="text/css" href="css/pagination.css" rel="stylesheet" />
		
		<script type="text/javascript" src="js/jquery-1.4.4.min.js" ></script>
    	<script type="text/javascript" src="js/jquery-ui-1.8.9.custom.min.js" ></script>
		<script type="text/javascript" src="js/pagination.js" ></script>
		<script type="text/javascript" src="js/lawyer.js" ></script>
		<script type="text/javascript">
			var items = [
			<c:forEach items="${legalActs}" var="legalAct">
				<c:url value="Content" var="link">
					<c:param name="legalActUri" value="${legalAct.rdfId}" />
				</c:url>
				["${link}", "${legalAct.label}",  "${legalAct.published}", "${legalAct.legalSubject.label}", "${legalAct.gazette}"],
			</c:forEach>
				["", "", "", "", ""]
			];
			
			function callback(page_index, jq) {
			    var items_per_page = 5;
			    var max_elem = Math.min((page_index + 1) * items_per_page, items.length - 1);
			    var newcontent = "";
			    
			    // Iterate through a selection of the content and build an HTML string
			    for (var i = page_index * items_per_page; i < max_elem; i++) {
			        newcontent += "<li>\n" +
					"<a href=\"" + items[i][0] + "\">" + items[i][1] + "</a><br />\n" +
					"Published: " + items[i][2] + "<br />" +
					"Issuer: " + items[i][3] + "<br />" +
					"Gazette: " + items[i][4] + "<br />" +
					"</li>";
			    }
			    
			    // Replace old content with new content
			    $("ol.results").html(newcontent);
			    
			    // Prevent click eventpropagation
			    return false;
			}
			
			$(document).ready(function() {
				$(".pagination").pagination(items.length - 1, {
		        	items_per_page: 5,
		        	callback: callback
				});
			});	
		</script>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>

	<body>
		<jsp:include page="header.jsp" flush="true" />
	
		<div id="body">
			<p>Search took <fmt:formatNumber value="${duration / 1000000000}" type="number" maxFractionDigits="6" minFractionDigits="6"/>  seconds.</p>
			
			<div class="pagination"></div><br />
			<ol class="results"></ol>
			<div class="pagination"></div>
		</div>
		
		<jsp:include page="footer.jsp" flush="true" />
	</body>
	
</html>