<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}"></c:set>
<!doctype html>
<html lang="nl">
<head>
<title>Het cultuurhuis - Reservatiemandje</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
</head>
<body>
	<h1>
		Het cultuurhuis: reservatiemandje
		<c:url value="/images/mandje.png" var="mandjeURL"/>
		<img src="${mandjeURL}" alt="reservatie mandje"/>
	</h1>
	<c:url value="/welkom.htm" var="welkomURL"/>
	<a href="${welkomURL}">voorstellingen</a>
	<c:url value="/bevestiging.htm" var="bevestigURL" />
	<a href="${bevestigURL}">Bevestiging reservatie</a>
	<c:url value="/reservatiemandje.htm" var="reservatieURL"/>
	<form method ="post" action="${reservatieURL}">
	<table>
		<tr>
			<th>Datum</th>
			<th>Titel</th>
			<th>Uitvoerders</th>
			<th>Prijs</th>
			<th>Plaatsen</th>
			<th><input type="submit" value="Verwijderen"/></th>
		</tr>
		<c:forEach var="reservatie" items="${teReserveren}" varStatus="status">
			<tr>
				<td><fmt:formatDate value="${reservatie.voorstelling.datum}" 
					type="both" pattern="dd/MM/yy HH:mm"/></td> 
				<td>${reservatie.voorstelling.titel}</td>
				<td>${reservatie.voorstelling.uitvoerders}</td>
				<td class="getal">&euro; <fmt:formatNumber 
				value="${reservatie.voorstelling.prijs}"
					minFractionDigits="2"/></td>
				<td class="getal">${reservatie.plaatsen}</td>
				<td><label><input type="checkbox" name="geselecteerdeNrs" 
				value="${reservatie.voorstelling.voorstellingsNr}">
				</label>
				</td>				
			</tr>
		</c:forEach>
		</table>
		</form>
		Te betalen: &euro; ${totaal}
</body>
</html>
