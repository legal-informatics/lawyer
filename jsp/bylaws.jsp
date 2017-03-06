<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

			<div id="bylaws">
				<table>
				<c:forEach items="${bylaws}" var="bylaw">
					<c:url value="Content" var="link">
	  					<c:param name="legalActUri" value="${bylaw.rdfId}" />
	  				</c:url>
					<tr>
						<td>Title:</td>
						<td><a href="${link}">${bylaw.label}</a></td>
					</tr>
					<tr>
						<td>Published:</td>
						<td>${bylaw.published}</td>
					</tr>
					<tr>
						<td>Issuer:</td>
						<td>${bylaw.legalSubject.label}</td>
					</tr>
					<tr>
						<td>Gazette:</td>
						<td>${bylaw.gazette}</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</c:forEach>
				</table>
			</div>