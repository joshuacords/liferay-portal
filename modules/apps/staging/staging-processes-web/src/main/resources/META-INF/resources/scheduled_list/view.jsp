<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<div class="container-fluid-1280" id="<portlet:namespace />scheduledProcessesContainer">
	<liferay-util:include page="/scheduled_list/scheduled_publish_processes.jsp" servletContext="<%= application %>" />
</div>