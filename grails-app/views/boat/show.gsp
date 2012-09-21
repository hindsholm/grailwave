
<%@ page import="dk.hindsholm.grailwave.Boat" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'boat.label', default: 'Boat')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-boat" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-boat" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list boat">
			
				<g:if test="${boatInstance?.skipper}">
				<li class="fieldcontain">
					<span id="skipper-label" class="property-label"><g:message code="boat.skipper.label" default="Skipper" /></span>
					
						<span class="property-value" aria-labelledby="skipper-label"><g:link controller="person" action="show" id="${boatInstance?.skipper?.id}">${boatInstance?.skipper?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${boatInstance?.type}">
				<li class="fieldcontain">
					<span id="type-label" class="property-label"><g:message code="boat.type.label" default="Type" /></span>
					
						<span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${boatInstance}" field="type"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${boatInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="boat.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${boatInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${boatInstance?.sailNumber}">
				<li class="fieldcontain">
					<span id="sailNumber-label" class="property-label"><g:message code="boat.sailNumber.label" default="Sail Number" /></span>
					
						<span class="property-value" aria-labelledby="sailNumber-label"><g:fieldValue bean="${boatInstance}" field="sailNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${boatInstance?.rating}">
				<li class="fieldcontain">
					<span id="rating-label" class="property-label"><g:message code="boat.rating.label" default="Rating" /></span>
					
						<span class="property-value" aria-labelledby="rating-label"><g:fieldValue bean="${boatInstance}" field="rating"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${boatInstance?.fleet}">
				<li class="fieldcontain">
					<span id="fleet-label" class="property-label"><g:message code="boat.fleet.label" default="Fleet" /></span>
					
						<span class="property-value" aria-labelledby="fleet-label"><g:link controller="fleet" action="show" id="${boatInstance?.fleet?.id}">${boatInstance?.fleet?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${boatInstance?.id}" />
					<g:link class="edit" action="edit" id="${boatInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
