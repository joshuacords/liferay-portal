<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "details");

Contact koroneikiContact = (Contact)request.getAttribute(TaprootWebKeys.CONTACT);

long contactId = BeanParamUtil.getLong(koroneikiContact, request, "contactId");
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems='<%=
		new JSPNavigationItemList(pageContext) {
			{
				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("details"));
						navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/contacts_admin/edit_contact", "tabs1", "details", "contactId", contactId);
						navigationItem.setLabel(LanguageUtil.get(request, "details"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("accounts"));
						navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/contacts_admin/edit_contact", "tabs1", "accounts", "contactId", contactId);
						navigationItem.setLabel(LanguageUtil.get(request, "accounts"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("entitlements"));

						if (koroneikiContact != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/contacts_admin/edit_contact", "tabs1", "entitlements", "contactId", contactId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "entitlements"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("external-links"));

						if (koroneikiContact != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/contacts_admin/edit_contact", "tabs1", "external-links", "contactId", contactId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "external-links"));
					});
			}
		}
	%>'
/>