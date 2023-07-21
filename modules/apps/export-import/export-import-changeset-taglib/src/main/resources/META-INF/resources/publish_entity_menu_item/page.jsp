<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/publish_entity_menu_item/init.jsp" %>

<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, themeDisplay.getScopeGroup(), ActionKeys.EXPORT_IMPORT_PORTLET_INFO) && showMenuItem %>">

	<%
	PortletURL portletURL = PortletURLFactoryUtil.create(request, ChangesetPortletKeys.CHANGESET, PortletRequest.ACTION_PHASE);

	portletURL.setParameter(ActionRequest.ACTION_NAME, "exportImportChangeset");
	portletURL.setParameter("mvcRenderCommandName", "exportImportChangeset");
	portletURL.setParameter("cmd", Constants.PUBLISH);
	portletURL.setParameter("backURL", currentURL);
	portletURL.setParameter("groupId", String.valueOf(entityGroupId));
	portletURL.setParameter("changesetUuid", changesetUuid);
	portletURL.setParameter("portletId", portletDisplay.getId());
	%>

	<liferay-ui:icon-delete
		confirmation="are-you-sure-you-want-to-publish-to-live"
		message="publish-to-live"
		url="<%= portletURL.toString() %>"
	/>
</c:if>