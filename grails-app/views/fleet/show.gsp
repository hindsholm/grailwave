
<%@ page import="dk.hindsholm.grailwave.Fleet" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fleet.label', default: 'Fleet')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-fleet" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-fleet" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list fleet">
			
				<g:if test="${fleetInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="fleet.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${fleetInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fleetInstance?.boats}">
				<li class="fieldcontain">
					<span id="boats-label" class="property-label"><g:message code="fleet.boats.label" default="Boats" /></span>
					
						<g:each in="${fleetInstance.boats}" var="b">
						<span class="property-value" aria-labelledby="boats-label"><g:link controller="boat" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${fleetInstance?.series}">
				<li class="fieldcontain">
					<span id="series-label" class="property-label"><g:message code="fleet.series.label" default="Series" /></span>
					
						<span class="property-value" aria-labelledby="series-label"><g:link controller="series" action="show" id="${fleetInstance?.series?.id}">${fleetInstance?.series?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${fleetInstance?.id}" />
					<g:link class="edit" action="edit" id="${fleetInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
