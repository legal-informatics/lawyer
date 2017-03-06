<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

			<div id="normMetadata">
					<table>
						<!-- 
						<tr>
							<td>Label:</td>
							<td>${legalNorm.label}</td>
						</tr>
						
						<tr>
							<td>IRI:</td>
							<td>${legalNorm.rdfId}</td>
						</tr>
						 -->
						 
						<tr>
							<td>Synopsis:</td>
							<td>${legalNorm.synopsis}</td>
						</tr>
						
						<tr>
							<td>Legal Relation:</td>
							<td>${legalNorm.legalRelation.label}</td>
						</tr>
						
						<tr>
							<td>Policy:</td>
							<td>${legalNorm.policy.label}</td>
						</tr>
						
						<tr>
							<td>Legal Institution:</td>
							<td> ${legalNorm.legalInstitution.label}</td>
						</tr>
						
						<tr>
							<td>Entered into Force:</td>
							<td>${legalNorm.enteredIntoForce}</td>
						</tr>
						
						<tr>
							<td>Repealed:</td>
							<td> ${legalNorm.repealed}</td>
						</tr>
						
						<tr>
							<td>Efficacy:</td>
							<td>${legalNorm.efficacy}</td>
						</tr>
					</table>
				</div>