<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

			<div id="normElements">
					<table>
						<tr>
							<td>Disposition:</td>
							<td>
								<c:forEach items="${dispositionUrls}" var="dispositionUrl">
									<a href="#${dispositionUrl}" style="background-color: yellow"><c:out value="${dispositionUrl}" /></a>, 
								</c:forEach>
							</td>
						</tr>
						
						<tr>
							<td>Disposition Hypothesis:</td>
							<td>
								<c:forEach items="${dispositionHypothesisUrls}" var="dispositionHypothesisUrl">
									<a href="#${dispositionHypothesisUrl}" style="background-color: lime"><c:out value="${dispositionHypothesisUrl}"></c:out></a>, 
								</c:forEach> 
							</td>
						</tr>
						
						<tr>
							<td>Sanction:</td>
							<td>
								<c:forEach items="${sanctionUrls}" var="sanctionUrl">
									<a href="#${sanctionUrl}" style="background-color: aqua"><c:out value="${sanctionUrl}"></c:out></a>
								</c:forEach>
							</td>
						</tr>
						
						<tr>
							<td>Sanction Hypothesis:</td>
							<td>
								<c:forEach items="${sanctionHypothesisUrls}" var="sanctionHypothesisUrl">
									<a href="#${sanctionHypothesisUrl}" style="background-color: fuchsia"><c:out value="${sanctionHypothesisUrl}"></c:out></a>
								</c:forEach>
							</td>
						</tr> 
						
						<tr>
							<td>Exception:</td>
							<td>
								<c:forEach items="${exceptionUrls}" var="exceptionUrl">
									<a href="#${exceptionUrl}" style="background-color: silver"><c:out value="${exceptionUrl}"></c:out></a>
								</c:forEach>
							</td>
						</tr>
					</table>
				</div>