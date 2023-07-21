<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/card/horizontal_card_icon/init.jsp" %>

<%
Object bodyContent = request.getAttribute("liferay-frontend:horizontal-card-icon:bodyContent");

String bodyContentString = StringPool.BLANK;

if (bodyContent != null) {
	bodyContentString = bodyContent.toString();
}
%>

<c:choose>
	<c:when test="<%= Validator.isNotNull(bodyContentString) %>">
		<%= bodyContentString %>
	</c:when>
	<c:otherwise>
		<div class="sticker sticker-secondary sticker-static">
			<aui:icon image="<%= icon %>" markupView="lexicon" />
		</div>
	</c:otherwise>
</c:choose>