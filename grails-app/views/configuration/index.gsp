<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page import com.jpcf.backend.domain.Event %>
<%@ page import com.jpcf.backend.domain.SlideShowImage %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main"/>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.2.min.js" ></script>
    <link type="text/css" rel="stylesheet" href="${resource(dir:'css', file:'config.css')}" />
    <title>Configuration Utility</title>
    <script type="text/javascript">
      $(function() {
        $("tr.tr-input td").addClass("td-input");
        
        $("button.move-up-button").click(function() {
          var id = $(this).attr("data-ImageId");
          jQuery.post(
            "${createLink(controller: 'slideShowImage', action: 'moveUp')}",
            {id: id},
            function() {
              console.log("Success");
              location.reload();
            },
            "json"
          );
        });
        $("button.move-down-button").click(function() {
          var id = $(this).attr("data-ImageId");
          jQuery.post(
            "${createLink(controller: 'slideShowImage', action: 'moveDown')}",
            {id: id},
            function() {
              console.log("Success");
              location.reload();
            },
            "json"
          );
        });
      });
    </script>
  </head>
  <body>
    <h1>Configuration Utility</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
    <hr />
    <h3>Slide Show</h3>
    <table>
      <thead>
        <tr>
          <th>Image</th>
          <th>Order</th>
          <th></th>
          <th></th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <g:if test="${images}">
          <g:each in="${images}" var="image" status="i">
            <tr>
              <td><img class="thumbnail" src="${createLink(controller: 'JS', action: 'getImageJs', id: image.id)}" /></td>
              <td>${image.orderIndex}</td>
              <td></td>
              <td></td>
              <td>
                <g:if test="${i != 0}">
                  <button class="move-down-button" data-ImageId="${image.id}">Move Up</button>
                </g:if>
                <g:else>
                  <button class="move-down-button" data-ImageId="${image.id}" disabled>Move Up</button>
                </g:else>
                <g:if test="${i != nImages - 1}">
                  <button class="move-up-button" data-ImageId="${image.id}">Move Down</button>
                </g:if>
                <g:else>
                  <button class="move-up-button" data-ImageId="${image.id}" disabled>Move Down</button>
                </g:else>
                <g:link controller="slideShowImage" action="delete" id="${image.id}"><button class="delete-img-button">Delete</button></g:link>
              </td>
            </tr>
          </g:each>
        </g:if>
        <g:form controller="fileUpload" action="uploadImage" method="post" enctype="multipart/form-data">
        <tr class="tr-input">
          <td><input type="file" id="fileUpload" name="imageUpload" /></td>
          <td></td>
          <td></td>
          <td></td>
          <td><g:actionSubmit class="upload" value="Upload Image" action="uploadImage" /></td>
        </tr>
        </g:form>
      </tbody>
    </table>
    <hr />
    <h3>Podcasts</h3>
    <table>
      <thead>
        <tr>
          <th>Podcast</th>
          <th>Description</th>
          <th>Date</th>
          <th></th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <g:if test="${podcasts}">
          <g:each in="${podcasts}" var="podcast" status="i">
			<g:render template="/podcast/podcast" model="${[podcast: podcast]}" />
          </g:each>
        </g:if>
		<g:render template="/podcast/inlineForm" />
      </tbody>
    </table>
    <hr />
    <h3>Events</h3>
    <table>
      <thead>
        <tr>
          <th>Event</th>
          <th>Description</th>
          <th>Start Date</th>
          <th>End Date</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <g:if test="${events}">
          <g:each in="${events}" var="event">
			<g:render template="/event/event" model="${[event: event]}" />
          </g:each>
        </g:if>
		<g:render template="/event/inlineForm" />
      </tbody>
    </table>
  </body>
</html>
