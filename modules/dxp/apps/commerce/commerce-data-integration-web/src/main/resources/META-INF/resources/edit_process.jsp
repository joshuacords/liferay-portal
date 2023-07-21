<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceDataIntegrationProcessDisplayContext commerceDataIntegrationProcessDisplayContext = (CommerceDataIntegrationProcessDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceDataIntegrationProcess commerceDataIntegrationProcess = commerceDataIntegrationProcessDisplayContext.getCommerceDataIntegrationProcess();

String title = LanguageUtil.get(request, "add-process");

if (commerceDataIntegrationProcess != null) {
	title = commerceDataIntegrationProcess.getName();
}

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(backURL);
portletDisplay.setTitle(title);
%>

<div id="<portlet:namespace />editProcessContainer">
	<liferay-frontend:screen-navigation
		containerCssClass="col-md-10"
		key="<%= CommerceDataIntegrationConstants.SCREEN_NAVIGATION_KEY_COMMERCE_DATA_INTEGRATION_GENERAL %>"
		modelBean="<%= commerceDataIntegrationProcess %>"
		navCssClass="col-md-2"
		portletURL="<%= currentURLObj %>"
	/>
</div>