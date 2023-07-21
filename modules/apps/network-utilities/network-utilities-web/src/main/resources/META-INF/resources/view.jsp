<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String cmd = ParamUtil.getString(request, Constants.CMD);

String tabs1 = ParamUtil.getString(request, "tabs1", "dns-lookup");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);
%>

<aui:form action="<%= portletURL %>">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.SEARCH %>" />
	<aui:input name="tabs1" type="hidden" value="<%= HtmlUtil.escapeAttribute(tabs1) %>" />

	<liferay-ui:tabs
		names="dns-lookup,whois"
		url="<%= portletURL.toString() %>"
	/>

	<c:choose>
		<c:when test='<%= tabs1.equals("dns-lookup") %>'>

			<%
			String domain = ParamUtil.getString(request, "domain");

			DNSLookup dnsLookup = null;

			if (cmd.equals(Constants.SEARCH)) {
				dnsLookup = NetworkUtil.getDNSLookup(domain);

				if (dnsLookup == null) {
					SessionErrors.add(renderRequest, DNSLookup.class.getName());
				}
			}
			%>

			<liferay-ui:error exception="<%= DNSLookup.class %>" message="please-enter-a-valid-host-name-or-ip" />

			<liferay-ui:input-search
				autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>"
				name="domain"
			/>

			<c:if test="<%= dnsLookup != null %>">
				<liferay-ui:panel
					collapsible="<%= false %>"
					title="<%= HtmlUtil.escape(domain) %>"
				>
					<pre>
<%= dnsLookup.getResults() %>
					</pre>
				</liferay-ui:panel>
			</c:if>
		</c:when>
		<c:when test='<%= tabs1.equals("whois") %>'>

			<%
			String domain = ParamUtil.getString(request, "domain");

			Whois whois = null;

			if (cmd.equals(Constants.SEARCH)) {
				whois = NetworkUtil.getWhois(domain);

				if (whois == null) {
					SessionErrors.add(renderRequest, Whois.class.getName());
				}
			}
			%>

			<liferay-ui:error exception="<%= Whois.class %>" message="an-unexpected-error-occurred" />

			<liferay-ui:input-search
				autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>"
				name="domain"
			/>

			<c:if test="<%= whois != null %>">
				<liferay-ui:panel
					collapsible="<%= false %>"
					title="<%= HtmlUtil.escape(domain) %>"
				>
					<pre>
<%= whois.getResults() %>
					</pre>
				</liferay-ui:panel>
			</c:if>
		</c:when>
	</c:choose>
</aui:form>