<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/card/user_vertical_card/init.jsp" %>

<%@ include file="/card/vertical_card/start.jspf" %>

<div class="aspect-ratio-item-center-middle aspect-ratio-item-fluid card-type-asset-icon">
	<liferay-ui:user-portrait
		user="<%= user2 %>"
	/>
</div>

<%@ include file="/card/vertical_card/end.jspf" %>