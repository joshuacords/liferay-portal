<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
AccountSearchDisplayContext accountSearchDisplayContext = ProvisioningWebComponentProvider.getAccountSearchDisplayContext(renderRequest, renderResponse, request);
%>

<div class="search-menu">
	<div class="container-fluid">
		<ul class="control-menu-nav search-menu-nav">
			<li class="logo">
				<svg class="company-logo">
					<use xlink:href="#liferay-waffle" />
				</svg>

				<h3>Raysource</h3>
			</li>
			<li class="custom-search" id="accountSearch">
				<react:component
					data="<%= accountSearchDisplayContext.getData() %>"
					module="js/apps/AccountSearchApp"
				/>
			</li>
		</ul>
	</div>
</div>

<aui:script>
	var accountSearchManagementToolbar = document.getElementById(
		'accountSearchManagementToolbar'
	);
	var titleBar = document.querySelector('.title-bar');

	if (accountSearchManagementToolbar && titleBar) {
		var resultsBar = accountSearchManagementToolbar.querySelector(
			'.subnav-tbar'
		);

		titleBar.classList.toggle('hide', resultsBar);
	}
</aui:script>