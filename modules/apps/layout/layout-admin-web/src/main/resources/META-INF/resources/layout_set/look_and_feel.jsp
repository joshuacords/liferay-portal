<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="look-and-feel"
/>

<aui:model-context bean="<%= layoutsAdminDisplayContext.getSelLayoutSet() %>" model="<%= Layout.class %>" />

<aui:input name="devices" type="hidden" value="regular" />

<liferay-util:include page="/look_and_feel_themes.jsp" servletContext="<%= application %>" />