<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Group group = (Group)row.getObject();

String groupId = String.valueOf(group.getGroupId());
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:choose>
		<c:when test='<%= GetterUtil.getBoolean(group.getTypeSettingsProperty("syncEnabled"), !group.isCompany()) %>'>
			<liferay-ui:icon
				label="<%= true %>"
				message="default-file-permissions"
				url='<%= "javascript:" + renderResponse.getNamespace() + "editDefaultFilePermissions(" + groupId + ");" %>'
			/>

			<portlet:actionURL name="updateSites" var="disableSiteURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="enabled" value="<%= Boolean.FALSE.toString() %>" />
				<portlet:param name="groupIds" value="<%= groupId %>" />
			</portlet:actionURL>

			<liferay-ui:icon-delete
				confirmation="disabling-a-sync-site-will-delete-all-associated-files-from-all-clients"
				label="<%= true %>"
				message="disable-sync-site"
				url="<%= disableSiteURL %>"
			/>
		</c:when>
		<c:otherwise>
			<portlet:actionURL name="updateSites" var="enableSiteURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="enabled" value="<%= Boolean.TRUE.toString() %>" />
				<portlet:param name="groupIds" value="<%= groupId %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				label="<%= true %>"
				message="enable-sync-site"
				url="<%= enableSiteURL %>"
			/>
		</c:otherwise>
	</c:choose>
</liferay-ui:icon-menu>

<aui:script>
	function <portlet:namespace />editDefaultFilePermissions(groupId) {
		var A = AUI();

		Liferay.Util.openWindow({
			dialog: {
				destroyOnHide: true,
				on: {
					destroy: function() {
						Liferay.Portlet.refresh('#p_p_id<portlet:namespace />');
					}
				}
			},
			id: '<portlet:namespace />editDefaultFilePermissionsDialog',
			title: '<liferay-ui:message key="default-file-permissions" />',

			<portlet:renderURL var="editDefaultFilePermissionsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="groupIds" value="{groupId}" />
				<portlet:param name="mvcPath" value="/edit_default_file_permissions.jsp" />
			</portlet:renderURL>

			uri: A.Lang.sub(
				decodeURIComponent('<%= editDefaultFilePermissionsURL %>'),
				{
					groupId: groupId
				}
			)
		});
	}
</aui:script>