<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-ui:icon
	message="install-from-url"
	onClick='<%= renderResponse.getNamespace() + "openInstallFromURLView()" %>'
	url="javascript:;"
/>

<aui:script>
	function <portlet:namespace />openInstallFromURLView() {
		Liferay.Util.openWindow({
			dialog: {
				destroyOnHide: true
			},
			id: '<portlet:namespace />openInstallFromURLView',
			title: '<liferay-ui:message key="install-from-url" />',
			uri:
				'<liferay-portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/install_remote_app.jsp" /><portlet:param name="redirect" value="<%= String.valueOf(renderResponse.createRenderURL()) %>" /></liferay-portlet:renderURL>'
		});
	}
</aui:script>