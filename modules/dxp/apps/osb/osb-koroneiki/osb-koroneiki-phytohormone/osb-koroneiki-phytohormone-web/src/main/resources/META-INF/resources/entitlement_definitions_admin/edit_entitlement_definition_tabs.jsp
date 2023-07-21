<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "details");

EntitlementDefinition entitlementDefinition = (EntitlementDefinition)request.getAttribute(PhytohormoneWebKeys.ENTITLEMENT_DEFINITION);

long entitlementDefinitionId = BeanParamUtil.getLong(entitlementDefinition, request, "entitlementDefinitionId");
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems='<%=
		new JSPNavigationItemList(pageContext) {
			{
				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("details"));
						navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/entitlement_definitions_admin/edit_entitlement_definition", "tabs1", "details", "entitlementDefinitionId", entitlementDefinitionId);
						navigationItem.setLabel(LanguageUtil.get(request, "details"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("external-links"));

						if (entitlementDefinition != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/entitlement_definitions_admin/edit_entitlement_definition", "tabs1", "external-links", "entitlementDefinitionId", entitlementDefinitionId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "external-links"));
					});
			}
		}
	%>'
/>