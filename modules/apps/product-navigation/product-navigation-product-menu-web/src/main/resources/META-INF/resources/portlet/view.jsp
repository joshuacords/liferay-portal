<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/portlet/init.jsp" %>

<%
String productMenuState = SessionClicks.get(request, "com.liferay.product.navigation.product.menu.web_productMenuState", "closed");
%>

<div class="lfr-product-menu-sidebar" id="productMenuSidebar">
	<div class="sidebar-header">
		<h1 class="sr-only"><liferay-ui:message key="product-admin-menu" /></h1>

		<div class="autofit-row">
			<div class="autofit-col autofit-col-expand">
				<a href="<%= PortalUtil.addPreservedParameters(themeDisplay, themeDisplay.getURLPortal(), false, true) %>">
					<span class="company-details truncate-text">
						<img alt="" class="company-logo" src="<%= themeDisplay.getPathImage() %>/company_logo?img_id=<%= company.getLogoId() %>&t=<%= WebServerServletTokenUtil.getToken(company.getLogoId()) %>" />

						<span class="company-name"><%= HtmlUtil.escape(company.getName()) %></span>
					</span>
				</a>
			</div>

			<div class="autofit-col">
				<aui:icon cssClass="d-inline-block d-md-none icon-monospaced sidenav-close" image="times" markupView="lexicon" url="javascript:;" />
			</div>
		</div>
	</div>

	<div class="sidebar-body">
		<c:if test='<%= Objects.equals(productMenuState, "open") %>'>
			<liferay-util:include page="/portlet/product_menu.jsp" servletContext="<%= application %>" />
		</c:if>
	</div>
</div>

<aui:script use="aui-base">
	var sidenavToggle = document.getElementById(
		'<portlet:namespace />sidenavToggleId'
	);

	var sidenavInstance = Liferay.SideNavigation.initialize(sidenavToggle);

	Liferay.once('screenLoad', function() {
		Liferay.SideNavigation.destroy(sidenavToggle);
	});

	sidenavInstance.on('closed.lexicon.sidenav', function(event) {
		Liferay.Util.Session.set(
			'com.liferay.product.navigation.product.menu.web_productMenuState',
			'closed'
		);
	});

	sidenavInstance.on('open.lexicon.sidenav', function(event) {
		Liferay.Util.Session.set(
			'com.liferay.product.navigation.product.menu.web_productMenuState',
			'open'
		);
	});

	if (Liferay.Util.isPhone() && document.body.classList.contains('open')) {
		Liferay.SideNavigation.hide(sidenavToggle);
	}
</aui:script>