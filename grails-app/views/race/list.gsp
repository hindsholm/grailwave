
<%@ page import="dk.hindsholm.grailwave.Race" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'race.label', default: 'Race')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-race" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-race" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'race.name.label', default: 'Name')}" />
					
						<th><g:message code="race.series.label" default="Series" /></th>
					
						<g:sortableColumn property="startTime" title="${message(code: 'race.startTime.label', default: 'Start Time')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${raceInstanceList}" status="i" var="raceInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${raceInstance.id}">${fieldValue(bean: raceInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: raceInstance, field: "series")}</td>
					
						<td><g:formatDate date="${raceInstance.startTime}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${raceInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
