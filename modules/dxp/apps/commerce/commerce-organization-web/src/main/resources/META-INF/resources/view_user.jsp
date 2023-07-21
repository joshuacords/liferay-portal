<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceOrganizationDisplayContext commerceOrganizationDisplayContext = (CommerceOrganizationDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

User selectedUser = commerceOrganizationDisplayContext.getSelectedUser();

PortletURL portletURL = commerceOrganizationDisplayContext.getPortletURL();

portletURL.setParameter("mvcRenderCommandName", "viewCommerceOrganizationUser");
%>

<div class="account-management">
	<section class="panel panel-secondary">
		<div class="panel-body">
			<div class="row">
				<div class="col-auto">
					<img alt="avatar" class="account-management__thumbnail img-fluid rounded-circle" src="<%= HtmlUtil.escapeAttribute(selectedUser.getPortraitURL(themeDisplay)) %>" />
				</div>

				<div class="col d-flex flex-col justify-content-center">
					<span class="account-management__name">
						<%= HtmlUtil.escape(selectedUser.getFullName()) %>
					</span>
					<span class="account-management__email">
						<%= HtmlUtil.escape(selectedUser.getEmailAddress()) %>
					</span>
				</div>
			</div>
		</div>
	</section>
</div>