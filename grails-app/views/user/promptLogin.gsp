<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main">
    <title>Login</title>
  </head>
  <body>
    <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
    </g:if>
    <h1>Login</h1>
    <g:form controller="user" action="login">
      <label>Username</label><g:textField name="username"></g:textField><br/>
      <label>Password</label><g:passwordField name="password"></g:passwordField><br/>
      <g:submitButton name="submit" value="Login" />
    </g:form>
  </body>
</html>