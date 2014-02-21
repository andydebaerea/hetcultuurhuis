<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}"></c:set>
<!doctype html>
<html lang="nl">
<head>
<title>Gemaakte reserveringen</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
</head>
<body>
	<h1>
		Het cultuurhuis: Overzicht
		<c:url value="/images/bevestig.png" var="imagesURL" />
		<img src="${imagesURL}" alt="bevestiging reservaties" />
	</h1>

	<c:url value="/welkom.htm" var="welkomURL" />
	<a href="${welkomURL}">voorstellingen</a>

<c:if test="${not empty gelukteReservaties}">
	<h2>Gelukte reserveringen</h2>
	<table>
		<tr>
			<th>Datum</th>
			<th>Titel</th>
			<th>Uitvoerders</th>
			<th>Prijs</th>
			<th>Plaatsen</th>
		</tr>
		<c:forEach var="gelukt" items="${gelukteReservaties}"
			varStatus="status">
			<tr>
				<td><fmt:formatDate value="${gelukt.voorstelling.datum}" 
					type="both" pattern="dd/MM/yy HH:mm"/></td>
				<td>${gelukt.voorstelling.titel}</td>
				<td>${gelukt.voorstelling.uitvoerders}</td>
				<td class="getal">&euro; 
					<fmt:formatNumber value="${gelukt.voorstelling.prijs}" 
						minFractionDigits="2" /></td>
				<td class="getal">${gelukt.plaatsen}</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
<c:if test="${not empty mislukteReservaties}">	
	<h2>Mislukte reserveringen</h2>
	<table>
		<tr>
			<th>Datum</th>
			<th>Titel</th>
			<th>Uitvoerders</th>
			<th>Prijs</th>
			<th>Vrije Plaatsen</th>
			<th>Plaatsen</th>
		</tr>
		<c:forEach var="mislukt" items="${mislukteReservaties}"
			varStatus="status">
			<tr>
				<td><fmt:formatDate value="${mislukt.voorstelling.datum}" 
					type="both" pattern="dd/MM/yy HH:mm"/></td>
				<td>${mislukt.voorstelling.titel}</td>
				<td>${mislukt.voorstelling.uitvoerders}</td>
				<td class="getal">&euro; <fmt:formatNumber 
				value="${mislukt.voorstelling.prijs}"
				minFractionDigits="2"/></td>
				<td class="getal">${mislukt.voorstelling.vrijePlaatsen}</td>
				<td class="getal">${mislukt.plaatsen}</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
</body>
</html>
