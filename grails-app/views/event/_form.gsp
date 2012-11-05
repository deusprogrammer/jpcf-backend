<%@ page import="com.jpcf.backend.domain.Event" %>



<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="event.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${eventInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'endDate', 'error')} required">
	<label for="endDate">
		<g:message code="event.endDate.label" default="End Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="endDate" precision="day"  value="${eventInstance?.endDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="event.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${eventInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'startDate', 'error')} required">
	<label for="startDate">
		<g:message code="event.startDate.label" default="Start Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startDate" precision="day"  value="${eventInstance?.startDate}"  />
</div>

