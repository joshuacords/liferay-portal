<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
LayoutSet layoutSet = layoutsAdminDisplayContext.getSelLayoutSet();

String virtualHostName = PortalUtil.getVirtualHostname(layoutSet);

Group scopeGroup = themeDisplay.getScopeGroup();

if (Validator.isNull(virtualHostName) && scopeGroup.isStagingGroup()) {
	Group liveGroup = scopeGroup.getLiveGroup();

	virtualHostName = PortalUtil.getVirtualHostname(layoutSet.isPrivateLayout() ? liveGroup.getPrivateLayoutSet() : liveGroup.getPublicLayoutSet());
}
%>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="robots"
/>

<c:choose>
	<c:when test="<%= Validator.isNotNull(virtualHostName) %>">
		<aui:input helpMessage="robots-txt-help" label="set-the-robots-txt" name='<%= "TypeSettingsProperties--" + layoutSet.isPrivateLayout() + "-robots.txt--" %>' placeholder="robots" type="textarea" value="<%= layoutsAdminDisplayContext.getRobots() %>" />
	</c:when>
	<c:otherwise>
		<div class="alert alert-info">
			<liferay-ui:message key="please-set-the-virtual-host-before-you-set-the-robots-txt" />
		</div>
	</c:otherwise>
</c:choose>