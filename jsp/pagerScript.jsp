<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

			<script id="pagerScript" type="text/javascript">
			var items = [
			<c:forEach items="${legalNorms}" var="legalNorm">
				<c:url value="Content" var="link">
					<c:param name="legalNormId" value="${legalNorm.rdfId}" />
				</c:url>
				["${legalNorm.label}", "${link}", "${legalNorm.rdfId}", "${legalNorm.synopsis}", "${legalNorm.legalAct.gazette}"],
			</c:forEach>
				["", "", "", ""]
			];
			
			function callback(page_index, jq) {
			    var items_per_page = 5;
			    var max_elem = Math.min((page_index + 1) * items_per_page, items.length - 1);
			    var newcontent = "";
			    
			    // Iterate through a selection of the content and build an HTML string
			    for (var i = page_index * items_per_page; i < max_elem; i++) {
			        newcontent += "<li>\n" +
					"Synopsis: <a href=\"" + items[i][1] + "\">" + items[i][3] + "</a><br />\n" +
					"Published in: " + items[i][4] + "\n" +
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