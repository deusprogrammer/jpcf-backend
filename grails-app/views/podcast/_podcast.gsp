 <tr>
   <td>${podcast.name}</td>
   <td>${podcast.description}</td>
   <td>${podcast.dateCreated}</td>
   <td><g:link controller="podcast" action="edit" id="${podcast.id}"><button>Edit</button></g:link><g:link controller="podcast" action="delete" id="${podcast.id}"><button>Delete</button></g:link></td>
 </tr>