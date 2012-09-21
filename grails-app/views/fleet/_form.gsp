<%@ page import="dk.hindsholm.grailwave.Fleet" %>



<div class="fieldcontain ${hasErrors(bean: fleetInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="fleet.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${fleetInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fleetInstance, field: 'boats', 'error')} ">
	<label for="boats">
		<g:message code="fleet.boats.label" default="Boats" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${fleetInstance?.boats?}" var="b">
    <li><g:link controller="boat" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="boat" action="create" params="['fleet.id': fleetInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'boat.label', default: 'Boat')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: fleetInstance, field: 'series', 'error')} required">
	<label for="series">
		<g:message code="fleet.series.label" default="Series" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="series" name="series.id" from="${dk.hindsholm.grailwave.Series.list()}" optionKey="id" required="" value="${fleetInstance?.series?.id}" class="many-to-one"/>
</div>

