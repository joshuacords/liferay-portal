<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/card/html_vertical_card/init.jsp" %>

<%@ include file="/card/vertical_card/start.jspf" %>

<div class="aspect-ratio aspect-ratio-bg-center aspect-ratio-bg-cover vertical-card-container">
	<%= html %>

	<c:if test="<%= Validator.isNotNull(stickerBottom) %>">
		<%= stickerBottom %>
	</c:if>
</div>

<%@ include file="/card/vertical_card/end.jspf" %>