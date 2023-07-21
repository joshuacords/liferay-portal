<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/ui/toggle_area/init.jsp" %>

<div style="float: <%= align %>">
	<liferay-ui:toggle
		defaultShowContent="<%= defaultShowContent %>"
		hideImage="<%= hideImage %>"
		hideMessage="<%= hideMessage %>"
		id="<%= id %>"
		showImage="<%= showImage %>"
		showMessage="<%= showMessage %>"
		stateVar="<%= stateVar %>"
	/>
</div>

<%
String clickValue = SessionClicks.get(request, id, null);

if (clickValue == null) {
	if (defaultShowContent) {
		clickValue = "block";
	}
	else {
		clickValue = "none";
	}
}
else if (clickValue.equals(StringPool.BLANK)) {
	clickValue = "block";
}
%>

<div id="<%= id %>" style="display: <%= clickValue %>;">