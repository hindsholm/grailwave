<%@ page import="dk.hindsholm.grailwave.Boat" %>



<div class="fieldcontain ${hasErrors(bean: boatInstance, field: 'skipper', 'error')} required">
	<label for="skipper">
		<g:message code="boat.skipper.label" default="Skipper" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="skipper" name="skipper.id" from="${dk.hindsholm.grailwave.Person.list()}" optionKey="id" required="" value="${boatInstance?.skipper?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: boatInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="boat.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="type" required="" value="${boatInstance?.type}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: boatInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="boat.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${boatInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: boatInstance, field: 'sailNumber', 'error')} required">
	<label for="sailNumber">
		<g:message code="boat.sailNumber.label" default="Sail Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="sailNumber" required="" value="${boatInstance?.sailNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: boatInstance, field: 'rating', 'error')} required">
	<label for="rating">
		<g:message code="boat.rating.label" default="Rating" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="rating" required="" value="${fieldValue(bean: boatInstance, field: 'rating')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: boatInstance, field: 'fleet', 'error')} required">
	<label for="fleet">
		<g:message code="boat.fleet.label" default="Fleet" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="fleet" name="fleet.id" from="${dk.hindsholm.grailwave.Fleet.list()}" optionKey="id" required="" value="${boatInstance?.fleet?.id}" class="many-to-one"/>
</div>

