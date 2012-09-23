
<%@ page import="dk.hindsholm.grailwave.Fleet" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fleet.label', default: 'Fleet')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-fleet" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-fleet" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'fleet.name.label', default: 'Name')}" />
					
						<th><g:message code="fleet.series.label" default="Series" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${fleetInstanceList}" status="i" var="fleetInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${fleetInstance.id}">${fieldValue(bean: fleetInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: fleetInstance, field: "series")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${fleetInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
