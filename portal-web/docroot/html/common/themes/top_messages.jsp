<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/common/themes/init.jsp" %>

<%
if (!user.isDefaultUser() && !locale.equals(user.getLocale())) {
	PortalUtil.addUserLocaleOptionsMessage(request);
}
%>

<c:if test="<%= ShutdownUtil.isInProcess() %>">
	<div class="alert alert-danger lfr-shutdown-message popup-alert-warning" id="lfrShutdownMessage">
		<span class="notice-label"><liferay-ui:message key="maintenance-alert" /></span> <span class="notice-date"><%= FastDateFormatFactoryUtil.getTime(locale).format(Time.getDate(CalendarFactoryUtil.getCalendar(timeZone))) %> <%= timeZone.getDisplayName(false, TimeZone.SHORT, locale) %></span>
		<span class="notice-message"><liferay-ui:message arguments="<%= String.valueOf((ShutdownUtil.getInProcess() / Time.MINUTE) + 1) %>" key="the-portal-will-shutdown-for-maintenance-in-x-minutes" translateArguments="<%= false %>" /></span>

		<c:if test="<%= Validator.isNotNull(ShutdownUtil.getMessage()) %>">
			<span class="custom-shutdown-message"><%= HtmlUtil.escape(ShutdownUtil.getMessage()) %></span>
		</c:if>
	</div>
</c:if>

<%
String jspPath = (String)PortalMessages.get(request, PortalMessages.KEY_JSP_PATH);
String message = (String)PortalMessages.get(request, PortalMessages.KEY_MESSAGE);
%>

<c:if test="<%= Validator.isNotNull(jspPath) || Validator.isNotNull(message) %>">

	<%
	String cssClass = GetterUtil.getString(PortalMessages.get(request, PortalMessages.KEY_CSS_CLASS), "alert-info");
	String portletId = (String)PortalMessages.get(request, PortalMessages.KEY_PORTLET_ID);
	int timeout = GetterUtil.getInteger(PortalMessages.get(request, PortalMessages.KEY_TIMEOUT), 10000);
	boolean useAnimation = GetterUtil.getBoolean(PortalMessages.get(request, PortalMessages.KEY_ANIMATION), true);

	if (useAnimation) {
		cssClass = cssClass + " hide";
	}
	%>

	<div class="alert-notifications alert-notifications-fixed" id="portalMessageContainer">
		<div class="alert alert-dismissible <%= cssClass %>">
			<c:choose>
				<c:when test="<%= Validator.isNotNull(jspPath) %>">
					<liferay-util:include page="<%= jspPath %>" portletId="<%= portletId %>" />
				</c:when>
				<c:otherwise>
					<liferay-ui:message key="<%= message %>" /><button aria-label="<%= LanguageUtil.get(request, "close") %>" class="close" type="button">&times;</button>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<aui:script use="liferay-notice">
		var banner = new Liferay.Notice(
			{
				animationConfig:
					{
						duration: 2,
						top: '0px'
					},
				closeText: false,
				node: '#portalMessageContainer',
				noticeClass: 'hide <%= cssClass %>',
				timeout: <%= timeout %>,
				toggleText: false,
				useAnimation: <%= useAnimation %>
			}
		);

		banner.show();
	</aui:script>
</c:if>