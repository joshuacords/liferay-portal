<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Group liveGroup = (Group)request.getAttribute("site.liveGroup");

UnicodeProperties groupTypeSettings = null;

if (liveGroup != null) {
	groupTypeSettings = liveGroup.getTypeSettingsProperties();
}
else {
	groupTypeSettings = new UnicodeProperties();
}

int companyContentSharingWithChildrenEnabled = PrefsPropsUtil.getInteger(company.getCompanyId(), PropsKeys.SITES_CONTENT_SHARING_WITH_CHILDREN_ENABLED);
%>

<aui:select label="allow-subsites-to-display-content-from-this-site" name="contentSharingWithChildrenEnabled" value='<%= PropertiesParamUtil.getInteger(groupTypeSettings, request, "contentSharingWithChildrenEnabled", Sites.CONTENT_SHARING_WITH_CHILDREN_DEFAULT_VALUE) %>'>
	<aui:option label='<%= (companyContentSharingWithChildrenEnabled == Sites.CONTENT_SHARING_WITH_CHILDREN_ENABLED_BY_DEFAULT) ? "default-value-enabled" : "default-value-disabled" %>' value="<%= Sites.CONTENT_SHARING_WITH_CHILDREN_DEFAULT_VALUE %>" />
	<aui:option label="enabled" value="<%= Sites.CONTENT_SHARING_WITH_CHILDREN_ENABLED %>" />
	<aui:option label="disabled" value="<%= Sites.CONTENT_SHARING_WITH_CHILDREN_DISABLED %>" />
</aui:select>