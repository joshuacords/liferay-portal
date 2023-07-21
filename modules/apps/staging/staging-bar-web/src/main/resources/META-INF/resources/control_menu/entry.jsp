<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/control_menu/init.jsp" %>

<li class="control-menu-nav-item staging-links">
	<liferay-portlet:runtime
		portletName="<%= StagingBarPortletKeys.STAGING_BAR %>"
	/>
</li>