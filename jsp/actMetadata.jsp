<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

			<div id="actMetadata">
				<table>
					<!-- 
					<tr>
						<td>IRI:</td>
						<td>${legalAct.rdfId}</td>
					</tr>
					 -->
					<tr>
						<td>Label:</td>
						<td>${legalAct.label}</td>
					</tr>
					<tr>
						<td>Issued:</td>
						<td>${legalAct.legalSubject.label}</td>
					</tr>
					<tr>
						<td>Enacted:</td>
						<td>${legalAct.enacted}</td>
					</tr>
					<tr>
						<td>Promulgated:</td>
						<td>${legalAct.promulgated}</td>
					</tr>
					<tr>
						<td>Published:</td>
						<td>${legalAct.published}</td>
					</tr>
				</table>
			</div>