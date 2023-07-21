<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/dynamic_include/init.jsp" %>

<%
BlogsGroupServiceOverriddenConfiguration blogsGroupServiceOverriddenConfiguration = ConfigurationProviderUtil.getConfiguration(BlogsGroupServiceOverriddenConfiguration.class, new GroupServiceSettingsLocator(themeDisplay.getSiteGroupId(), BlogsConstants.SERVICE_NAME));
%>

<div class="btn-group">
	<c:if test="<%= blogsGroupServiceOverriddenConfiguration.enableRss() && ((themeDisplay.getScopeGroupId() == 0) || GroupPermissionUtil.contains(themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(), ActionKeys.VIEW)) %>">

		<%
		String rssURL = RSSUtil.getURL(StringBundler.concat(themeDisplay.getPathMain(), "/blogs/rss?plid=", String.valueOf(themeDisplay.getPlid()), "&groupId=", String.valueOf(themeDisplay.getScopeGroupId())), GetterUtil.getInteger(blogsGroupServiceOverriddenConfiguration.rssDelta()), blogsGroupServiceOverriddenConfiguration.rssDisplayStyle(), blogsGroupServiceOverriddenConfiguration.rssFeedType(), null);
		%>

		<div class="btn-group-item">
			<clay:link
				elementClasses="btn btn-outline-borderless btn-outline-secondary btn-sm"
				href="<%= rssURL %>"
				icon="rss-full"
				label='<%= LanguageUtil.get(request, "rss") %>'
			/>
		</div>

		<liferay-util:html-top>
			<link href="<%= HtmlUtil.escapeAttribute(rssURL) %>" rel="alternate" title="RSS" type="application/rss+xml" />
		</liferay-util:html-top>
	</c:if>

	<%
	BlogsGroupServiceSettings blogsGroupServiceSettings = BlogsGroupServiceSettings.getInstance(scopeGroupId);
	%>

	<c:if test="<%= (blogsGroupServiceSettings.isEmailEntryAddedEnabled() || blogsGroupServiceSettings.isEmailEntryUpdatedEnabled()) && BlogsPermission.contains(permissionChecker, scopeGroupId, ActionKeys.SUBSCRIBE) %>">
		<div class="btn-group-item">
			<c:choose>
				<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(company.getCompanyId(), user.getUserId(), BlogsEntry.class.getName(), scopeGroupId) %>">
					<portlet:actionURL name="/blogs/edit_entry" var="unsubscribeURL">
						<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UNSUBSCRIBE %>" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
					</portlet:actionURL>

					<clay:link
						buttonStyle="secondary"
						elementClasses="btn-sm"
						href="<%= unsubscribeURL %>"
						label='<%= LanguageUtil.get(request, "unsubscribe") %>'
					/>
				</c:when>
				<c:otherwise>
					<portlet:actionURL name="/blogs/edit_entry" var="subscribeURL">
						<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.SUBSCRIBE %>" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
					</portlet:actionURL>

					<clay:link
						buttonStyle="secondary"
						elementClasses="btn-sm"
						href="<%= subscribeURL %>"
						label='<%= LanguageUtil.get(request, "subscribe") %>'
					/>
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>

	<c:if test="<%= BlogsPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ENTRY) %>">
		<portlet:renderURL var="editEntryURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcRenderCommandName" value="/blogs/edit_entry" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
		</portlet:renderURL>

		<div class="btn-group-item">
			<clay:link
				buttonStyle="primary"
				elementClasses="btn-sm"
				href="<%= editEntryURL %>"
				label='<%= LanguageUtil.get(request, "new-entry") %>'
			/>
		</div>
	</c:if>
</div>