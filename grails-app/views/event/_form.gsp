 <g:form controller="event" action="save">
 <tr class="tr-input">
   <td><g:textField name="name" id="event-name" /></td>
   <td><g:textField name="description" id="event-desc" /></td>
   <td><g:textField name="startDate" id="event-start" /></td>
   <td><g:textField name="endDate" id="event-end" /></td>
   <td><g:actionSubmit value="Add Event" action="save"/></td>
 </tr>
 </g:form>