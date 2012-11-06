<%@ page import="com.jpcf.backend.domain.Podcast" %>

<div class="fieldcontain ${hasErrors(bean: podcast, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="podcast.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${podcast?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: podcast, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="podcast.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${podcast?.description}"/>
</div>