<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "details");

ContactRole contactRole = (ContactRole)request.getAttribute(TaprootWebKeys.CONTACT_ROLE);

long contactRoleId = BeanParamUtil.getLong(contactRole, request, "contactRoleId");
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems='<%=
		new JSPNavigationItemList(pageContext) {
			{
				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("details"));
						navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/contact_roles_admin/edit_contact_role", "tabs1", "details", "contactRoleId", contactRoleId);
						navigationItem.setLabel(LanguageUtil.get(request, "details"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("external-links"));

						if (contactRole != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/contact_roles_admin/edit_contact_role", "tabs1", "external-links", "contactRoleId", contactRoleId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "external-links"));
					});
			}
		}
	%>'
/>