<%@ page import="com.jpcf.backend.domain.User" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
		<g:layoutHead/>
		<r:layoutResources />
	</head>
	<body>
        <% 
        def user = null
        if (session["userId"]) {
          user = User.get(session["userId"])
        }
        %>
        <g:if test="${user}">
          <div style="background-color: #00ccff; color: white;">Logged in as <g:link controller="user" action="show" id="${session["userId"]}" style="text-decoration: none; color: red">${user.username}</g:link> | <g:link controller="user" action ="logout" style="text-decoration: none; color: red;">Logout</g:link>?</div>
        </g:if>
        <g:else>
          <div style="background-color: #00ccff; color: white">Need to <g:link controller="user" action="promptLogin" style="text-decoration: none; color: red">login</g:link>?  If you don't have a login, you need to request one!</div>
        </g:else>
		<div id="grailsLogo" role="banner"><a href="http://grails.org"><img src="${resource(dir: 'images', file: 'grails_logo.png')}" alt="Grails"/></a></div>
		<g:layoutBody/>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<g:javascript library="application"/>
		<r:layoutResources />
	</body>
</html>
