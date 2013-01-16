 <% def formatter = new java.text.SimpleDateFormat("MM/dd/yyyy hh:mm a") %>
 <g:form controller="event" action="save">
 	<tr class="tr-input">
	   <td><g:textField name="name" id="event-name" value="${event?.name}" /></td>
	   <td><g:textField name="description" id="event-desc" value="${event?.description}" /></td>
	   <td><g:textField name="startDate" id="event-start" value="${formatter.format(event?.startDate ?: new Date())}" /></td>
	   <td><g:textField name="endDate" id="event-end" value="${formatter.format(event?.endDate ?: new Date())}" /></td>
	   <td><g:submitButton name="create-event-button" value="Create Event" /></td>
	</tr>
</g:form>