<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<?xml-stylesheet href="css/lawyer.css" type="text/css"?>
<?xml-stylesheet href="css/humanity/jquery-ui-1.8.9.custom.css" type="text/css"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<title>Lawyer</title>
		
		<link type="text/css" href="css/lawyer.css" rel="stylesheet" />
    	<link type="text/css" href="css/humanity/jquery-ui-1.8.9.custom.css" rel="stylesheet" />   
    	
    	<script type="text/javascript" src="js/jquery-1.4.4.min.js" ></script>
    	<script type="text/javascript" src="js/jquery-ui-1.8.9.custom.min.js" ></script>
		<script type="text/javascript" src="js/lawyer.js" ></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$( ".tabs" ).tabs({ 
					selected: 0 
				});
			});
		</script>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	
	<body>
		
		<jsp:include page="header.jsp" flush="true" />
		
		<div id="body" class="tabs">
			<ul>
				<li><a href="#tab1">Legal Acts</a></li>
				<li><a href="#tab2">Legal Norms</a></li>
			</ul>
		
			<div id="tab1">
				<form action="Acts" method="post" accept-charset="UTF-8">
					<table>
						<tr>
							<td>
								<table>
									<tr>
										<td>Legal System:</td>
									</tr>
									<tr>
										<td>
											<select id="legalSystem" name="legalSystem" tabindex="1">
												<option value="">All</option>
												<c:forEach items="${legalSystems}" var="legalSystem">
													<option value="${legalSystem.rdfId}">${legalSystem.label}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td>Legal Branch:</td>
									</tr>
									<tr>
										<td>
											<select id="legalBranch" name="legalBranch" tabindex="2">
												<option value="">All</option>
												<c:forEach items="${legalBranches}" var="legalBranch">
													<option value="${legalBranch.rdfId}">${legalBranch.label}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td>Legal Institution</td>
									</tr>
									<tr>
										<td>
											<select id="legalInstitution" name="legalInstitution" tabindex="3">
												<option value="">All</option>
												<c:forEach items="${legalInstitutions}" var="legalInstitution">
													<option value="${legalInstitution.rdfId}">${legalInstitution.label}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
								</table>
							</td>
							
							<td>
								<table>
									<tr>
										<td>Title: </td>
									</tr>
									<tr>
										<td>
											<input id="title" name="title" tabindex="4" />
										</td>
									</tr>
									<tr>
										<td>Gazette: </td>
									</tr>
									<tr>
										<td>
											<select id="gazette" name="gazette" tabindex="5">
												<option value="">All</option>
												<c:forEach items="${gazettes}" var="gazette">
													<option value="${gazette}">${gazette}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td>Content: </td>
									</tr>
									<tr>
										<td>
											<input id="content" name="content" tabindex="6" />
										</td>
									</tr>
								</table>
							</td>
							
							<td>
								<table>
									<tr>
										<td>Validity:</td>
									</tr>
									<tr>
										<td>
											<input id="validity" name="validity" value="all" type="radio" checked="checked" tabindex="7" />All<br>
											<input id="validity" name="validity" value="today" type="radio" tabindex="8" />Today<br>
											<input id="validity" name="validity" value="specific" type="radio" tabindex="9" /><input id="date" name="date" class="datepicker" tabindex="10" />
										</td>
									</tr>
									<tr>
										<td>Jurisdiction: </td>
									</tr>
									<tr>
										<td>
											<select id="jurisdiction" name="jurisdiction" tabindex="11">
												<option value="">All</option>
												<c:forEach items="${legalSubjects}" var="legalSubject">
												<option value="${legalSubject.rdfId}">${legalSubject.label}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
								</table>
							</td>
						</tr>
							
						<tr >
							<td class="button" colspan="3"><input type="reset" value="Reset" tabindex="12" /><input type="submit" value="Search" tabindex="13" /></td>
						</tr>
					</table>
				</form>
			</div>

			<div id="tab2">
				<form action="Norms">
					<table>
						<tr>
							<td>
								<table>
									<tr>
										<td>Legal System:</td>
									</tr>
									<tr>
										<td>
											<select id="legalSystem" name="legalSystem" tabindex="1">
												<option value="">All</option>
												<c:forEach items="${legalSystems}" var="legalSystem">
													<option value="${legalSystem.rdfId}">${legalSystem.label}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td>Legal Branch:</td>
									</tr>
									<tr>
										<td>
											<select id="legalBranch" name="legalBranch" tabindex="2">
												<option value="">All</option>
												<c:forEach items="${legalBranches}" var="legalBranch">
													<option value="${legalBranch.rdfId}">${legalBranch.label}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td>Legal Institution</td>
									</tr>
									<tr>
										<td>
											<select id="legalInstitution" name="legalInstitution" tabindex="3">
												<option value="">All</option>
												<c:forEach items="${legalInstitutions}" var="legalInstitution">
													<option value="${legalInstitution.rdfId}">${legalInstitution.label}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
								</table>
							</td>
							
							<td>
								<table>
									<tr>
										<td>Legal Relation:</td>
									</tr>
									<tr>
										<td>
											<select id="legalRelation" name="legalRelation" tabindex="4">
												<option value="">All</option>
												<c:forEach items="${legalRelations}" var="legalRelation">
													<option value="${legalRelation.rdfId}">${legalRelation.label}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td>Legal Subject:</td>
									</tr>
									<tr>
										<td>
											<select id="legalSubject" name="legalSubject" tabindex="5">
												<option value="">All</option>
												<c:forEach items="${legalSubjects}" var="legalSubject">
													<option value="${legalSubject.rdfId}">${legalSubject.label}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td><input id="rightSubject" name="rightSubject" type="checkbox" tabindex="6" checked="checked" value="true">Right Subject</td>
									</tr>
									<tr>
										<td><input id="dutySubject" name="dutySubject" type="checkbox" tabindex="7" value="true">Duty Subject</td>
									</tr>
									<tr>
										<td><input id="competenceSubject" name="competenceSubject" type="checkbox" tabindex="8" value="true">Competence Subject</td>
									</tr>
								</table>
							</td>
							
							<td>
								<table>
									<tr>
										<td>Validity:</td>
									</tr>
									<tr>
										<td>
											<input id="validity" name="validity" value="all" type="radio" checked="checked" tabindex="9" />All<br>
											<input id="validity" name="validity" value="today" type="radio" tabindex="10" />Today<br>
											<input id="validity" name="validity" value="specific" type="radio" tabindex="11" /><input id="date" name="date" class="datepicker" tabindex="10" />
										</td>
									</tr>
									<tr>
										<td>Jurisdiction: </td>
									</tr>
									<tr>
										<td>
											<select id="jurisdiction" name="jurisdiction" tabindex="12">
												<option value="">All</option>
												<c:forEach items="${legalSubjects}" var="legalSubject">
												<option value="${legalSubject.rdfId}">${legalSubject.label}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
								</table>
							</td>
						</tr>
							
						<tr>
							<td class="button" colspan="3"><input type="reset" value="Reset" tabindex="13" /><input type="submit" value="Search" tabindex="14" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>	
		
		<jsp:include page="footer.jsp" flush="true" />
	</body>

</html>