
<%@ page import="dk.hindsholm.grailwave.Boat" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'boat.label', default: 'Boat')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-boat" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-boat" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="boat.skipper.label" default="Skipper" /></th>
					
						<g:sortableColumn property="type" title="${message(code: 'boat.type.label', default: 'Type')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'boat.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="sailNumber" title="${message(code: 'boat.sailNumber.label', default: 'Sail Number')}" />
					
						<g:sortableColumn property="rating" title="${message(code: 'boat.rating.label', default: 'Rating')}" />
					
						<th><g:message code="boat.fleet.label" default="Fleet" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${boatInstanceList}" status="i" var="boatInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${boatInstance.id}">${fieldValue(bean: boatInstance, field: "skipper")}</g:link></td>
					
						<td>${fieldValue(bean: boatInstance, field: "type")}</td>
					
						<td>${fieldValue(bean: boatInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: boatInstance, field: "sailNumber")}</td>
					
						<td>${fieldValue(bean: boatInstance, field: "rating")}</td>
					
						<td>${fieldValue(bean: boatInstance, field: "fleet")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${boatInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
