<%@ page import="dk.hindsholm.grailwave.Race" %>



<div class="fieldcontain ${hasErrors(bean: raceInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="race.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${raceInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: raceInstance, field: 'series', 'error')} required">
	<label for="series">
		<g:message code="race.series.label" default="Series" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="series" name="series.id" from="${dk.hindsholm.grailwave.Series.list()}" optionKey="id" required="" value="${raceInstance?.series?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: raceInstance, field: 'startTime', 'error')} required">
	<label for="startTime">
		<g:message code="race.startTime.label" default="Start Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startTime" precision="day"  value="${raceInstance?.startTime}"  />
</div>

