<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long[] groupIds = ParamUtil.getLongValues(request, "groupIds");

Group group = null;
int currentPermissions = 0;

if (groupIds.length == 1) {
	group = GroupLocalServiceUtil.fetchGroup(groupIds[0]);

	currentPermissions = GetterUtil.getInteger(group.getTypeSettingsProperty("syncSiteMemberFilePermissions"));
}
%>

<liferay-ui:header
	localizeTitle="<%= false %>"
	title="<%= (group == null) ? StringPool.BLANK : group.getDescriptiveName() %>"
/>

<table class="table table-bordered table-hover table-striped">
	<thead class="table-columns">
		<tr>
			<th>
				<liferay-ui:message key="name" />
			</th>
			<th />
		</tr>
	</thead>

	<tbody>

		<%
		List<Integer> permissionsOptions = new ArrayList<Integer>(4);

		permissionsOptions.add(SyncPermissionsConstants.PERMISSIONS_VIEW_ONLY);
		permissionsOptions.add(SyncPermissionsConstants.PERMISSIONS_VIEW_AND_ADD_DISCUSSION);
		permissionsOptions.add(SyncPermissionsConstants.PERMISSIONS_VIEW_UPDATE_AND_ADD_DISCUSSION);
		permissionsOptions.add(SyncPermissionsConstants.PERMISSIONS_FULL_ACCESS);

		for (Integer permissions : permissionsOptions) {
		%>

			<tr class="record-row">
				<td>
					<c:choose>
						<c:when test="<%= permissions == SyncPermissionsConstants.PERMISSIONS_FULL_ACCESS %>">

							<%
							List<String> resourceActions = ListUtil.toList(SyncPermissionsConstants.getFileResourceActions(permissions));

							List<String> localizedResourceActions = new ArrayList<String>(resourceActions.size());

							for (String resourceAction : resourceActions) {
								localizedResourceActions.add(LanguageUtil.get(request, ResourceActionsUtil.getActionNamePrefix() + resourceAction));
							}
							%>

							<liferay-ui:message arguments="<%= StringUtil.merge(localizedResourceActions, StringPool.COMMA_AND_SPACE) %>" key="full-access-x" />

							<liferay-ui:icon-help message="full-access-help" />
						</c:when>
						<c:when test="<%= permissions == SyncPermissionsConstants.PERMISSIONS_VIEW_AND_ADD_DISCUSSION %>">
							<liferay-ui:message key="view-and-add-discussion" />

							<liferay-ui:icon-help message="view-and-add-discussion-help" />
						</c:when>
						<c:when test="<%= permissions == SyncPermissionsConstants.PERMISSIONS_VIEW_ONLY %>">
							<liferay-ui:message key="view-only" />

							<liferay-ui:icon-help message="view-only-help" />
						</c:when>
						<c:when test="<%= permissions == SyncPermissionsConstants.PERMISSIONS_VIEW_UPDATE_AND_ADD_DISCUSSION %>">
							<liferay-ui:message key="view-update-and-add-discussion" />

							<liferay-ui:icon-help message="view-update-and-add-discussion-help" />
						</c:when>
					</c:choose>
				</td>
				<td>
					<portlet:actionURL name="updateSites" var="setPermissionsURL">
						<portlet:param name="groupIds" value="<%= StringUtil.merge(groupIds) %>" />
						<portlet:param name="permissions" value="<%= String.valueOf(permissions) %>" />
					</portlet:actionURL>

					<%
					String taglibSetPermissions = renderResponse.getNamespace() + "setPermissions('" + setPermissionsURL + "');";
					%>

					<aui:button disabled="<%= currentPermissions == permissions %>" onClick="<%= taglibSetPermissions %>" value="choose" />
				</td>
			</tr>

		<%
		}
		%>

	</tbody>
</table>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />setPermissions',
		function(uri) {
			Liferay.Util.fetch(uri, {method: 'POST'})
				.then(function(response) {
					return response.text();
				})
				.then(function() {
					Liferay.Util.getWindow(
						'<portlet:namespace />editDefaultFilePermissionsDialog'
					).destroy();
				});
		},
		['liferay-util-window']
	);
</aui:script>