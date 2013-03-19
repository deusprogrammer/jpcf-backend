  <table>
 	<thead>
 	  <th>Image</th>
 	  <th>Text</th>
 	</thead>
 	<tbody>
	<tr class="tr-input">
	  <td><img src="${createLink(controller: 'JS', action: 'getImageJs', id: slideShowImage?.id)}" /></td>
	  <td><g:textField name="text" id="ss-text" value="${slideShowImage?.text}" /></td>
	</tr>
	</tbody>
</table>