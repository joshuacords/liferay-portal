<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<aui:script use="aui-base">
	Liferay.Util.getOpener().Liferay.fire(
		'publishGadgetSuccess',
		{
			gadgetId: <%= ParamUtil.getLong(request, "gadgetId") %>,
			unpublishPermission: <%= ParamUtil.getBoolean(request, "unpublishPermission") %>
		}
	);
</aui:script>