<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main">
    <title>Password Change</title>
  </head>
  <body>
    <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
    </g:if>
    <h1>Password Change</h1>
    <g:form controller="user" action="changePassword">
      <g:textField name = "password" /><g:submitButton name="submit" value="Change Password"/>
    </g:form>
  </body>
</html>