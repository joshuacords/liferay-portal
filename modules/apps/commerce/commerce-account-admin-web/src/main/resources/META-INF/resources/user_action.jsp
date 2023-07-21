<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceAccountUserRelAdminDisplayContext commerceAccountUserRelAdminDisplayContext = (CommerceAccountUserRelAdminDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CommerceAccountUserRel commerceAccountUserRel = (CommerceAccountUserRel)row.getObject();

User commerceAccountUser = commerceAccountUserRel.getUser();

String editUserRoleId = "editUserRoles" + commerceAccountUser.getUserId();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= commerceAccountUserRelAdminDisplayContext.hasPermission(commerceAccountUserRel.getCommerceAccountId(), ActionKeys.UPDATE) %>">
		<liferay-ui:icon
			id="<%= editUserRoleId %>"
			message="edit-roles"
			url="javascript:;"
		/>
	</c:if>

	<c:if test="<%= commerceAccountUserRelAdminDisplayContext.hasPermission(commerceAccountUserRel.getCommerceAccountId(), ActionKeys.DELETE) %>">
		<portlet:actionURL name="editCommerceAccountUserRel" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commerceAccountId" value="<%= String.valueOf(commerceAccountUserRel.getCommerceAccountId()) %>" />
			<portlet:param name="commerceAccountUserId" value="<%= String.valueOf(commerceAccountUserRel.getCommerceAccountUserId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			message="remove"
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>

<aui:script use="liferay-item-selector-dialog">
	$('#<portlet:namespace /><%= editUserRoleId %>').on('click', function(event) {
		event.preventDefault();

		var form = AUI.$(document.<portlet:namespace />fm);

		form.fm('originalRoleIds').val(
			'<%= commerceAccountUserRelAdminDisplayContext.getUserRoleIds(commerceAccountUserRel) %>'
		);

		var itemSelectorDialog = new A.LiferayItemSelectorDialog({
			eventName: 'userRoleItemSelector',
			on: {
				selectedItemChange: function(event) {
					var <portlet:namespace />addUserRolesIds = [];

					var selectedItems = event.newVal;

					if (selectedItems) {
						A.Array.each(selectedItems, function(
							item,
							index,
							selectedItems
						) {
							<portlet:namespace />addUserRolesIds.push(item.id);
						});

						form.fm('<%= Constants.CMD %>').val(
							'<%= Constants.UPDATE %>'
						);
						form.fm('commerceAccountUserId').val(
							<%= String.valueOf(commerceAccountUser.getUserId()) %>
						);
						form.fm('roleIds').val(
							<portlet:namespace />addUserRolesIds.join(',')
						);

						submitForm(
							form,
							'<portlet:actionURL name="editCommerceAccountUserRel" />'
						);
					}
				}
			},
			title: '<liferay-ui:message key="edit-roles" />',
			url:
				'<%= commerceAccountUserRelAdminDisplayContext.getUserRoleItemSelectorUrl(commerceAccountUserRel) %>'
		});

		itemSelectorDialog.open();
	});
</aui:script>