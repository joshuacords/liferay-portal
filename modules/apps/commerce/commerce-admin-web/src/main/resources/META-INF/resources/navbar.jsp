<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceAdminModuleRegistry commerceAdminModuleRegistry = (CommerceAdminModuleRegistry)request.getAttribute(CommerceAdminWebKeys.COMMERCE_ADMIN_MODULE_REGISTRY);

NavigableMap<String, CommerceAdminModule> commerceAdminModules = commerceAdminModuleRegistry.getCommerceAdminModules(company.getCompanyId(), themeDisplay.getScopeGroupId());

String selectedCommerceAdminModuleKey = ParamUtil.getString(request, "commerceAdminModuleKey", commerceAdminModules.firstKey());

List<NavigationItem> navigationItems = new ArrayList<>();

for (Map.Entry<String, CommerceAdminModule> entry : commerceAdminModules.entrySet()) {
	String commerceAdminModuleKey = entry.getKey();
	CommerceAdminModule commerceAdminModule = entry.getValue();

	PortletURL commerceAdminModuleURL = renderResponse.createRenderURL();

	commerceAdminModuleURL.setParameter("commerceAdminModuleKey", commerceAdminModuleKey);

	NavigationItem navigationItem = new NavigationItem();

	navigationItem.setActive(commerceAdminModuleKey.equals(selectedCommerceAdminModuleKey));
	navigationItem.setHref(commerceAdminModuleURL.toString());
	navigationItem.setLabel(commerceAdminModule.getLabel(locale));

	navigationItems.add(navigationItem);
}
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= navigationItems %>"
/>