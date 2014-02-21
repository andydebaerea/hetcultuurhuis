<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}"></c:set>
<!doctype html>
<html lang="nl">
<head>
<title>Het Cultuurhuis - nieuwe klant</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css">
</head>
<body>

	<h1>
		Het Cultuurhuis: nieuwe klant
		<c:url value="/images/nieuweklant.png" var="imagesURL" />
		<img src="${imagesURL}" alt="nieuwe klant" />
	</h1>

	<c:url value="/welkom.htm" var="welkomurl" />
	<a href="${welkomurl}">Voorstellingen</a>
	<c:if test="${not empty session}">
		<c:url value="/reservatiemandje.htm" var="reservatiemandjeURL" />
		<a href="${reservatiemandjeURL}">Reservatiemandje</a>
		<c:url value="/bevestiging.htm" var="bevestigURL" />
		<a href="${bevestigURL}">Bevestiging reservatie</a>
	</c:if>

	<c:url value="/nieuweklant.htm" var="nieuweKlantURL"></c:url>
	<form class="klant" method="post" action="${nieuweKlantURL}">

		<div>
			<label>Voornaam: <input name="voornaam"
				value="${param.voornaam}"></label> <label>Familienaam: <input
				name="familienaam" value="${param.familienaam}"></label> <label>Straat:
				<input name="straat" value="${param.straat}">
			</label> <label>Huisnr: <input name="huisNr" value="${param.huisNr}"></label>
			<label>Postcode: <input name="postcode"
				value="${param.postcode}"></label> <label>Gemeente: <input
				name="gemeente" value="${param.gemeente}"></label> <label>Gebruikersnaam:
				<input name="gebruikersNaam" value="${param.gebruikersNaam}">
			</label> <label>Paswoord: <input type="password" name="paswoord"
				value="${param.paswoord}"></label> <label>Herhaal paswoord:
				<input type="password" name="herhaalPaswoord"
				value="${param.herhaalPaswoord}">
			</label>
		</div>

		<input class="knop" type="submit" value="OK">
	</form>

	<c:if test="${not empty fouten}">
		<ul>
			<c:forEach var="fout" items="${fouten}">
				<li class="fout">${fout}</li>
			</c:forEach>
		</ul>
	</c:if>

</body>
</html>