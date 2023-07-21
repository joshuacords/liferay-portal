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

CommerceAdminModule selectedCommerceAdminModule = commerceAdminModules.get(selectedCommerceAdminModuleKey);
%>

<liferay-util:include page="/navbar.jsp" servletContext="<%= application %>" />

<%
selectedCommerceAdminModule.render(request, PipingServletResponse.createPipingServletResponse(pageContext));
%>