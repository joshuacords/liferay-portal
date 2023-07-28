<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%
String randomNamespace = StringUtil.randomId() + StringPool.UNDERLINE;

String height = (String)request.getAttribute("liferay-ui:flash:height");
String width = (String)request.getAttribute("liferay-ui:flash:width");
%>

<div id="<%= randomNamespace %>flashcontent" style="height: <%= height %>; width: <%= width %>;"></div>

<aui:script use="aui-swf-deprecated">
	new A.SWF(
		{
			boundingBox: '#<%= randomNamespace %>flashcontent',
			fixedAttributes: {
				allowFullScreen: '<%= (String)request.getAttribute("liferay-ui:flash:allowFullScreen") %>',
				allowScriptAccess: '<%= (String)request.getAttribute("liferay-ui:flash:allowScriptAccess") %>',
				base: '<%= (String)request.getAttribute("liferay-ui:flash:base") %>',
				bgcolor: '<%= (String)request.getAttribute("liferay-ui:flash:bgcolor") %>',
				devicefont: '<%= (String)request.getAttribute("liferay-ui:flash:devicefont") %>',
				loop: '<%= (String)request.getAttribute("liferay-ui:flash:loop") %>',
				menu: '<%= (String)request.getAttribute("liferay-ui:flash:menu") %>',
				play: '<%= (String)request.getAttribute("liferay-ui:flash:play") %>',
				quality: '<%= (String)request.getAttribute("liferay-ui:flash:quality") %>',
				salign: '<%= (String)request.getAttribute("liferay-ui:flash:salign") %>',
				scale: '<%= (String)request.getAttribute("liferay-ui:flash:scale") %>',
				swliveconnect: '<%= (String)request.getAttribute("liferay-ui:flash:swliveconnect") %>',
				wmode: '<%= (String)request.getAttribute("liferay-ui:flash:wmode") %>'
			},
			flashVars: '<%= (String)request.getAttribute("liferay-ui:flash:flashvars") %>',
			height: '<%= height %>',
			id: '<%= (String)request.getAttribute("liferay-ui:flash:id") %>',
			url: '<%= (String)request.getAttribute("liferay-ui:flash:movie") %>',
			version: <%= (String)request.getAttribute("liferay-ui:flash:version") %>,
			width: '<%= width %>'
		}
	).render();
</aui:script>