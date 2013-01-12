 <g:form controller="fileUpload" action="uploadPodcast" method="post" enctype="multipart/form-data">
 <tr class="tr-input">
   <td><g:textField name="name" /></td>
   <td><g:textField name="description" /></td>
   <td><input type="file" id="fileUpload" name="podcastUpload" /></td>
   <td><g:actionSubmit class="upload" value="Upload Podcast" action="uploadPodcast" /></td>
 </tr>
 </g:form>