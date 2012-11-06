<%@ page import="com.jpcf.backend.domain.Podcast" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
		</div>
		<div id="edit-event" class="content scaffold-edit" role="main">
			<h1>Enter Podcast Details</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

			<g:form method="post" >
				<g:hiddenField name="id" value="${podcast?.id}" />
				<g:hiddenField name="version" value="${podcast?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
