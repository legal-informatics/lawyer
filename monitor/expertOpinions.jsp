<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

			<div id="expertOpinions">
				<table>
					<c:forEach items="${legalNorm.expertOpinions}" var="expertOpinion">
					<c:url value="Content" var="link">
	  					<c:param name="legalActUri" value="${expertOpinion.uri}" />
	  				</c:url>
					<tr>
						<td><a href="${link}">${expertOpinion.label}</a></td>
					</tr>
					</c:forEach>
				</table>
			</div>