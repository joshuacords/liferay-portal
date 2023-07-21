<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceVirtualOrderItemContentDisplayContext commerceVirtualOrderItemContentDisplayContext = (CommerceVirtualOrderItemContentDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

JournalArticleDisplay articleDisplay = commerceVirtualOrderItemContentDisplayContext.getArticleDisplay();

long commerceVirtualOrderItemId = ParamUtil.getLong(request, "commerceVirtualOrderItemId");
String termsOfUseContent = ParamUtil.getString(request, "termsOfUseContent");
%>

<div class="journal-article-preview p-3">
	<c:choose>
		<c:when test="<%= articleDisplay != null %>">
			<%= articleDisplay.getContent() %>
		</c:when>
		<c:otherwise>
			<%= termsOfUseContent %>
		</c:otherwise>
	</c:choose>

	<aui:button-row>
		<aui:button name="agreeButton" onClick='<%= renderResponse.getNamespace() + "agreeTermsOfUse();" %>' primary="<%= true %>" value="i-agree" />

		<aui:button name="disagreeButton" onClick='<%= renderResponse.getNamespace() + "closeDialog();" %>' value="i-disagree" />
	</aui:button-row>
</div>

<aui:script>
	function <portlet:namespace />agreeTermsOfUse() {
		Liferay.Util.getOpener().<portlet:namespace />downloadCommerceVirtualOrderItem(
			'<portlet:namespace />viewTermsOfUseDialog',
			'<%= commerceVirtualOrderItemId %>'
		);
	}

	function <portlet:namespace />closeDialog() {
		Liferay.Util.getOpener().<portlet:namespace />closePopup(
			'<portlet:namespace />viewTermsOfUseDialog'
		);
	}
</aui:script>