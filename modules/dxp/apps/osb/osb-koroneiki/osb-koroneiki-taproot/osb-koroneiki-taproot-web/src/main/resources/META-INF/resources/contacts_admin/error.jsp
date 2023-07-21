<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<c:if test="<%= !SessionErrors.isEmpty(renderRequest) %>">
	<div class="alert alert-danger">
		<liferay-ui:icon
			icon="exclamation-full"
			markupView="lexicon"
		/>

		<c:choose>
			<c:when test="<%= SessionErrors.contains(renderRequest, NoSuchContactException.class.getName()) %>">
				<liferay-ui:message key="the-contact-could-not-be-found" />
			</c:when>
		</c:choose>
	</div>
</c:if>