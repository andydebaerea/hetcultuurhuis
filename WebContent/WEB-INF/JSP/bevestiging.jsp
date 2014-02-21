<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}"></c:set>
<!doctype html>
<html lang="nl">
<head>
<title>Het cultuurhuis : bevestiging reservaties</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css" />
</head>
<body>
	<h1>
		Het cultuurhuis: Bevestiging reservaties
		<c:url value="/images/bevestig.png" var="bevestigingURL" />
		<img src="${bevestigingURL}" alt="bevestiging" />
	</h1>

	<c:url value="/welkom.htm" var="welkomURL"/>
	<a href="${welkomURL}">Voorstellingen</a>
	<c:url value="/reservatiemandje.htm" var="mandjeURL"/>
	<a href="${mandjeURL}">Reservatiemandje</a>

	<h2>Stap 1: Wie ben je?</h2>
	<c:url value="/bevestiging.htm" var="bevestigingURL"/>
	<form method="post" action="${bevestigingURL}">
		<label>Gebruikersnaam:
		<input name="Gebruikersnaam" value="${param.Gebruikersnaam}" autofocus/>
		</label> 
		<label>Paswoord:<input type="password" name="Paswoord" value="${param.Paswoord}" />
		</label>
		<input class="knop" type="submit" value="Zoek me op"
			<c:if test="${not empty klant}">disabled</c:if> />
	</form>
	<c:url value="/nieuweklant.htm" var="nieuweklantURL"/>
	<form method="get" action="${nieuweklantURL}">
		<input type="submit" value="Ik ben nieuw"
			<c:if test="${not empty klant}">disabled</c:if> />
	</form>
	<c:choose>
		<c:when test="${not empty klant}">
			${klant.voornaam} ${klant.familienaam}, ${klant.straat} ${klant.huisNr}, ${klant.gemeente}
		</c:when>
		<c:otherwise>
			<span class="fout">${fout}</span>
		</c:otherwise>
	</c:choose>
	<h2>Stap 2: Bevestigen</h2>
	<c:url value="/gedanereserveringen.htm" var="gedanereserveringenURL"/>
	<form method="get" action="${gedanereserveringenURL}">
		<input type="submit" value="Bevestigen"
			<c:if test="${empty klant}">disabled</c:if> />
	</form>
</body>
</html>
