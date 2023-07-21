<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/card/vertical_card/init.jsp" %>

<%@ include file="/card/vertical_card/start.jspf" %>

<img alt="" class="aspect-ratio-item-center-middle aspect-ratio-item-fluid" src="<%= imageUrl %>" />

<c:if test="<%= Validator.isNotNull(stickerBottom) %>">
	<%= stickerBottom %>
</c:if>

<%@ include file="/card/vertical_card/end.jspf" %>