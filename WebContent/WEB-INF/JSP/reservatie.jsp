<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath}"></c:set>
<!doctype html>
<html lang="nl">
<head>
<title>Het cultuurhuis - Reserveren</title>
<link rel="stylesheet" href="${contextPath}/styles/default.css"/>
</head>
<body>
	<h1>
		Het cultuurhuis: reserveren
		<c:url value="/images/reserveren.png" var="reserverenURL"/>
		<img src="${reserverenURL}" alt="reserveren" />
	</h1>
	
	<c:url value="/welkom.htm" var="welkomURL"/>
	<a href="${welkomURL}">voorstellingen</a>
	<br><br>
	
	Voorstelling:
	<h4>${voorstelling.titel}</h4>
	Uitvoerders:
	<h4>${voorstelling.uitvoerders}</h4>
	Datum:
	<h4><fmt:formatDate value="${voorstelling.datum}" 
					type="both" pattern="dd/MM/yy HH:mm"/></h4>
	Prijs:
	<h4>&euro; <fmt:formatNumber value="${voorstelling.prijs}"
					minFractionDigits="2"/></h4>
	Vrije plaatsen:
	<h4>${voorstelling.vrijePlaatsen}</h4>
	
	<c:url value="/reservatie.htm" var="reservatieURL"/>
	<form method="post" action="${reservatieURL}">
		<label> Plaatsen: <span class="fout">${fout}</span>
			<c:choose>
				<c:when test='${empty bestaandeReservatie}'>
					<c:if test="${not empty param.plaatsen}">
						<c:set var="textPlaatsen" value="${param.plaatsen}" />
					</c:if>
				</c:when>
				<c:otherwise>
					<c:set var="textPlaatsen" value="${bestaandeReservatie}" />
				</c:otherwise>
			</c:choose><input type="number" name="plaatsen" value="${textPlaatsen}" autofocus/></label>

		<input class="knop" type="submit" value="Reserveren" />
	</form>
	
</body>
</html>


