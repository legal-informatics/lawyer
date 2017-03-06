<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

			<div id="searchResults">	
				<!-- 
				<p>Search took <fmt:formatNumber value="${duration / 1000000000}" type="number" maxFractionDigits="6" minFractionDigits="6"/>  seconds.</p>
				 -->
				<table>
				<c:forEach items="${legalNorms}" var="legalNorm">
					<c:url value="Content" var="url">
						<c:param name="legalNormUri" value="${legalNorm.rdfId}" />
					</c:url>
					<tr><td>Synopsis:</td><td><a href="${url}#${legalNorm.fragment}">${legalNorm.synopsis}</a></td></tr>
					<tr><td>Contained in:</td><td>${legalNorm.legalAct.label}</td></tr>
					<tr><td>Published in:</td><td>${legalNorm.legalAct.gazette}</td></tr>
					<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
				</c:forEach>
				</table>
				
				 
				 <!-- 
				<ul>
				<c:forEach items="${legalNorms}" var="legalNorm">
					<c:url value="Content" var="url">
						<c:param name="legalNormId" value="${legalNorm.rdfId}" />
					</c:url>
					<li>
						Synopsis: <a href="${url}">${legalNorm.synopsis}</a> <br />
						Published in: ${legalNorm.legalAct.gazette} 
					</li>
				</c:forEach>
				</ul>
				-->
			</div>