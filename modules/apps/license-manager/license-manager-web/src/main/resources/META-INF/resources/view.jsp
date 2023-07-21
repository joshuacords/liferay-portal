<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<aui:nav-bar markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item label="licenses" selected="<%= true %>" />
	</aui:nav>
</aui:nav-bar>

<div class="container-fluid-1280">
	<iframe allowTransparency="true" frameborder="0" id="<portlet:namespace />iframe" scrolling="no" src="<%= themeDisplay.getPathMain() %>/portal/license?p_l_id=<%= themeDisplay.getPlid() %>&p_p_state=pop_up" style="border: none; width: 100%;"></iframe>
</div>

<aui:script use="aui-autosize-iframe">
	var iframe = A.one('#<portlet:namespace />iframe');

	if (iframe) {
		iframe.plug(A.Plugin.AutosizeIframe);
	}
</aui:script>