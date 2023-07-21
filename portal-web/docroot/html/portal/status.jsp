<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/portal/init.jsp" %>

<%
int status = ParamUtil.getInteger(request, "status");

if (status > 0) {
	response.setStatus(status);
}

String exception = ParamUtil.getString(request, "exception");

String url = ParamUtil.getString(request, "previousURL");

if (Validator.isNull(url)) {
	url = PortalUtil.getCurrentURL(request);
}

url = HttpUtil.decodeURL(themeDisplay.getPortalURL() + url);

boolean noSuchResourceException = false;

for (String key : SessionErrors.keySet(request)) {
	key = key.substring(key.lastIndexOf(StringPool.PERIOD) + 1);

	if (key.startsWith("NoSuch") && key.endsWith("Exception")) {
		noSuchResourceException = true;
	}
}

if (Validator.isNotNull(exception)) {
	exception = exception.substring(exception.lastIndexOf(StringPool.PERIOD) + 1);

	if (exception.startsWith("NoSuch") && exception.endsWith("Exception")) {
		noSuchResourceException = true;
	}
}
%>

<c:choose>
	<c:when test="<%= SessionErrors.contains(request, PrincipalException.getNestedClasses()) %>">
		<h3 class="alert alert-danger">
			<liferay-ui:message key="forbidden" />
		</h3>

		<liferay-ui:message key="you-do-not-have-permission-to-access-the-requested-resource" />

		<br /><br />

		<code class="lfr-url-error"><%= HtmlUtil.escape(url) %></code>
	</c:when>
	<c:when test="<%= SessionErrors.contains(request, PortalException.class.getName()) || SessionErrors.contains(request, SystemException.class.getName()) %>">
		<h3 class="alert alert-danger">
			<liferay-ui:message key="internal-server-error" />
		</h3>

		<liferay-ui:message key="an-error-occurred-while-accessing-the-requested-resource" />

		<br /><br />

		<code class="lfr-url-error"><%= HtmlUtil.escape(url) %></code>
	</c:when>
	<c:when test="<%= SessionErrors.contains(request, TransformException.class.getName()) %>">
		<h3 class="alert alert-danger">
			<liferay-ui:message key="internal-server-error" />
		</h3>

		<liferay-ui:message key="an-error-occurred-while-processing-the-requested-resource" />

		<br /><br />

		<code class="lfr-url-error"><%= HtmlUtil.escape(url) %></code>

		<br /><br />

		<%
		TransformException te = (TransformException)SessionErrors.get(request, TransformException.class.getName());
		%>

		<div>
			<%= StringUtil.replace(HtmlUtil.escape(te.getMessage()), '\n', "<br />\n") %>
		</div>
	</c:when>
	<c:when test="<%= noSuchResourceException %>">
		<h3 class="alert alert-danger">
			<liferay-ui:message key="not-found" />
		</h3>

		<liferay-ui:message key="the-requested-resource-could-not-be-found" />

		<br /><br />

		<code class="lfr-url-error"><%= HtmlUtil.escape(url) %></code>
	</c:when>
	<c:otherwise>
		<h3 class="alert alert-danger">
			<liferay-ui:message key="internal-server-error" />
		</h3>

		<liferay-ui:message key="an-error-occurred-while-accessing-the-requested-resource" />

		<br /><br />

		<code class="lfr-url-error"><%= HtmlUtil.escape(url) %></code>

		<%
		for (String key : SessionErrors.keySet(request)) {
			Object value = SessionErrors.get(request, key);

			if (value instanceof Exception) {
				Exception e = (Exception)value;

				_log.error(e.getMessage());

				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}
			}
		}
		%>

	</c:otherwise>
</c:choose>

<div class="separator"><!-- --></div>

<a href="javascript:history.go(-1);">&laquo; <liferay-ui:message key="back" /></a>

<%!
private static Log _log = LogFactoryUtil.getLog("portal_web.docroot.html.portal.status_jsp");
%>