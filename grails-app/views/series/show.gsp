
<%@ page import="dk.hindsholm.grailwave.Series" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'series.label', default: 'Series')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-series" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-series" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list series">
			
				<g:if test="${seriesInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="series.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${seriesInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${seriesInstance?.organizer}">
				<li class="fieldcontain">
					<span id="organizer-label" class="property-label"><g:message code="series.organizer.label" default="Organizer" /></span>
					
						<span class="property-value" aria-labelledby="organizer-label"><g:fieldValue bean="${seriesInstance}" field="organizer"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${seriesInstance?.fleets}">
				<li class="fieldcontain">
					<span id="fleets-label" class="property-label"><g:message code="series.fleets.label" default="Fleets" /></span>
					
						<g:each in="${seriesInstance.fleets}" var="f">
						<span class="property-value" aria-labelledby="fleets-label"><g:link controller="fleet" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${seriesInstance?.races}">
				<li class="fieldcontain">
					<span id="races-label" class="property-label"><g:message code="series.races.label" default="Races" /></span>
					
						<g:each in="${seriesInstance.races}" var="r">
						<span class="property-value" aria-labelledby="races-label"><g:link controller="race" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${seriesInstance?.id}" />
					<g:link class="edit" action="edit" id="${seriesInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
