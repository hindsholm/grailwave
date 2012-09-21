<%@ page import="dk.hindsholm.grailwave.Series" %>



<div class="fieldcontain ${hasErrors(bean: seriesInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="series.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${seriesInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: seriesInstance, field: 'organizer', 'error')} required">
	<label for="organizer">
		<g:message code="series.organizer.label" default="Organizer" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="organizer" required="" value="${seriesInstance?.organizer}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: seriesInstance, field: 'fleets', 'error')} ">
	<label for="fleets">
		<g:message code="series.fleets.label" default="Fleets" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${seriesInstance?.fleets?}" var="f">
    <li><g:link controller="fleet" action="show" id="${f.id}">${f?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="fleet" action="create" params="['series.id': seriesInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'fleet.label', default: 'Fleet')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: seriesInstance, field: 'races', 'error')} ">
	<label for="races">
		<g:message code="series.races.label" default="Races" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${seriesInstance?.races?}" var="r">
    <li><g:link controller="race" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="race" action="create" params="['series.id': seriesInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'race.label', default: 'Race')])}</g:link>
</li>
</ul>

</div>

