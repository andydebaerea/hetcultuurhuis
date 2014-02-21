<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}"></c:set>
<!doctype html>
<html lang="nl">
<head>
<title>Het Cultuurhuis</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css">
</head>
<body>

	<h1>
		Voorstellingen Cultuurhuis
		<c:url value="/images/voorstellingen.png" var="imagesURL" />
		<img src="${imagesURL}" alt="voorstellingen" />
	</h1>
	
	<c:if test="${not empty session}">
		<c:url value="/reservatiemandje.htm" var="mandjeurl" />
		<a href="${mandjeurl}">Reservatiemandje</a>
		<c:url value="/bevestiging.htm" var="bevestigURL" />
		<a href="${bevestigURL}">Bevestiging reservatie</a>
	</c:if>
	
	<h2>Genres</h2>
	
	<c:forEach var="genre" items="${genres}">
		<c:url value="/welkom.htm" var="url">
			<c:param name="genreNr" value="${genre.genreNr}" />
		</c:url>
		<a href="${url}">${genre.naam}</a>
	</c:forEach>

	<c:if test="${not empty voorstellingen}">
		<h2>${genreNaam}voorstellingen</h2>
		<table>

			<tr>
				<th>Datum</th>
				<th>Titel</th>
				<th>Uitvoerders</th>
				<th>Prijs</th>
				<th>Vrije Plaatsen</th>
				<th>Reserveren</th>
			</tr>

			<c:forEach var="voorstelling" items="${voorstellingen}"
				varStatus="status">

				<tr>
					<td><fmt:formatDate value="${voorstelling.datum}" 
					type="both" pattern="dd/MM/yy HH:mm"/></td>
					<td>${voorstelling.titel}</td>
					<td>${voorstelling.uitvoerders}</td>
					<td class="getal">&euro; 
					<fmt:formatNumber value="${voorstelling.prijs}"
					minFractionDigits="2"/></td>
					<td class="getal">${voorstelling.vrijePlaatsen}</td>
					<td><c:url value="/reservatie.htm" var="ReservatieURL">
							<c:param name="voorstellingsNr"
								value="${voorstelling.voorstellingsNr}" />
						</c:url> <c:if test="${voorstelling.vrijePlaatsen >0}">
							<a href="${ReservatieURL}">Reserveren</a>
						</c:if></td>
				</tr>

			</c:forEach>

		</table>

	</c:if>

</body>
</html>
