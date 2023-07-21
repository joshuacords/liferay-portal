<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/portal/api/jsonws/init.jsp" %>

<c:if test="<%= PropsValues.JSONWS_WEB_SERVICE_API_DISCOVERABLE %>">
	<style>
		<%@ include file="/html/portal/api/jsonws/css.jspf" %>
	</style>

	<div id="wrapper">
		<header id="banner" role="banner">
			<div id="heading">
				<h1 class="site-title">
					<a class="logo" href="<%= HtmlUtil.escapeAttribute(jsonWSContextPath) %>" title="JSONWS API">
						<img alt="<%= HtmlUtil.escapeAttribute("JSONWS API") %>" height="<%= themeDisplay.getCompanyLogoHeight() %>" src="<%= HtmlUtil.escape(themeDisplay.getCompanyLogo()) %>" width="<%= themeDisplay.getCompanyLogoWidth() %>" />
					</a>

					<span class="site-name">
						JSONWS API
					</span>
				</h1>
			</div>
		</header>

		<div id="content">
			<div id="main-content">
				<aui:row>
					<aui:col cssClass="lfr-api-navigation" width="<%= 25 %>">
						<liferay-util:include page="/html/portal/api/jsonws/actions.jsp" />
					</aui:col>

					<aui:col cssClass="lfr-api-details" width="<%= 75 %>">
						<liferay-util:include page="/html/portal/api/jsonws/action.jsp" />
					</aui:col>
				</aui:row>
			</div>
		</div>

		<footer id="footer" role="contentinfo">
			<p class="powered-by">
				<liferay-ui:message key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a>
			</p>
		</footer>
	</div>
</c:if>