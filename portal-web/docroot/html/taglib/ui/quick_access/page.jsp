<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/ui/quick_access/init.jsp" %>

<%
String randomNamespace = StringUtil.randomId() + StringPool.UNDERLINE;
%>

<c:if test="<%= ((quickAccessEntries != null) && !quickAccessEntries.isEmpty()) || Validator.isNotNull(contentId) %>">
	<nav aria-label="<liferay-ui:message key="quick-links" />" class="quick-access-nav" id="<%= randomNamespace %>quickAccessNav">
		<h1 class="hide-accessible"><liferay-ui:message key="navigation" /></h1>

		<ul>
			<c:if test="<%= Validator.isNotNull(contentId) %>">
				<li><a href="<%= contentId %>"><liferay-ui:message key="skip-to-content" /></a></li>
			</c:if>

			<c:if test="<%= (quickAccessEntries != null) && !quickAccessEntries.isEmpty() %>">

				<%
				for (QuickAccessEntry quickAccessEntry : quickAccessEntries) {
				%>

					<li>
						<a href="<%= quickAccessEntry.getURL() %>" id="<%= randomNamespace + quickAccessEntry.getId() %>" onclick="<%= quickAccessEntry.getOnClick() %>">
							<%= quickAccessEntry.getContent() %>
						</a>
					</li>

				<%
				}
				%>

			</c:if>
		</ul>
	</nav>
</c:if>