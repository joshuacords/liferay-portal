<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String className = (String)request.getAttribute("contact_information.jsp-className");
long classPK = (long)request.getAttribute("contact_information.jsp-classPK");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

EmailAddress emailAddress = (EmailAddress)row.getObject();

long emailAddressId = emailAddress.getEmailAddressId();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>

	<%
	PortletURL editURL = liferayPortletResponse.createRenderURL();

	editURL.setParameter("mvcPath", "/common/edit_email_address.jsp");
	editURL.setParameter("redirect", currentURL);
	editURL.setParameter("className", className);
	editURL.setParameter("classPK", String.valueOf(classPK));
	editURL.setParameter("primaryKey", String.valueOf(emailAddressId));
	%>

	<liferay-ui:icon
		message="edit"
		url="<%= editURL.toString() %>"
	/>

	<%
	PortletURL portletURL = renderResponse.createActionURL();

	portletURL.setParameter(ActionRequest.ACTION_NAME, "/users_admin/update_contact_information");
	portletURL.setParameter("redirect", currentURL);
	portletURL.setParameter("className", className);
	portletURL.setParameter("classPK", String.valueOf(classPK));
	portletURL.setParameter("listType", ListTypeConstants.EMAIL_ADDRESS);
	portletURL.setParameter("primaryKey", String.valueOf(emailAddressId));

	PortletURL makePrimaryURL = PortletURLUtil.clone(portletURL, renderResponse);

	makePrimaryURL.setParameter(Constants.CMD, "makePrimary");
	%>

	<liferay-ui:icon
		message="make-primary"
		url="<%= makePrimaryURL.toString() %>"
	/>

	<%
	PortletURL removeEmailAddressURL = PortletURLUtil.clone(portletURL, renderResponse);

	removeEmailAddressURL.setParameter(Constants.CMD, Constants.DELETE);
	%>

	<liferay-ui:icon
		message="remove"
		url="<%= removeEmailAddressURL.toString() %>"
	/>
</liferay-ui:icon-menu>