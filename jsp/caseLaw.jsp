<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

			<div id="caseLaw">
				<table>
					<c:forEach items="${legalNorm.caseLaw}" var="caseLaw">
					<c:url value="Content" var="link">
	  					<c:param name="legalActUri" value="${caseLaw.uri}" />
	  				</c:url>
					<tr>
						<td><a href="${link}">${caseLaw.label}</a></td>
					</tr>
					</c:forEach>
				</table>
			</div>