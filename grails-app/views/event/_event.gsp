 <tr>
   <td>${event.name}</td>
   <td>${event.description}</td>
   <td><g:formatDate format="MM/dd/yyyy hh:mm a" date="${event.startDate}" /></td>
   <td><g:formatDate format="MM/dd/yyyy hh:mm a" date="${event.endDate}" /></td>
   <td><g:link controller="event" action="edit" id="${event.id}"><button class="jpcf-edit-button" data-eventId="${event.id}">Edit</button></g:link><g:link controller="event" action="delete" id="${event.id}"><button class="jpcf-delete-button">Delete</button></g:link></td>
 </tr>