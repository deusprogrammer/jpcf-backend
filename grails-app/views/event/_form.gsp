 <% def formatter = new java.text.SimpleDateFormat("MM/dd/yyyy hh:mm a") %>
 <table>
 	<thead>
 	  <th>Name</th>
 	  <th>Description</th>
 	  <th>Start Date</th>
 	  <th>End Date</th>
 	</thead>
 	<tbody>
	<tr class="tr-input">
	  <td><g:textField name="name" id="event-name" value="${event?.name}" /></td>
	  <td><g:textField name="description" id="event-desc" value="${event?.description}" /></td>
	  <td><g:textField name="startDate" id="event-start" value="${formatter.format(event?.startDate ?: new Date())}" /></td>
	  <td><g:textField name="endDate" id="event-end" value="${formatter.format(event?.endDate ?: new Date())}" /></td>
	</tr>
	</tbody>
</table>