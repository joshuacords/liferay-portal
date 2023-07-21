<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "details");

ProductConsumption productConsumption = (ProductConsumption)request.getAttribute(TrunkWebKeys.PRODUCT_CONSUMPTION);

long productConsumptionId = BeanParamUtil.getLong(productConsumption, request, "productConsumptionId");
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems='<%=
		new JSPNavigationItemList(pageContext) {
			{
				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("details"));
						navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/products_admin/edit_product_consumption", "tabs1", "details", "productConsumptionId", productConsumptionId);
						navigationItem.setLabel(LanguageUtil.get(request, "details"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("external-links"));

						if (productConsumption != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/products_admin/edit_product_consumption", "tabs1", "external-links", "productConsumptionId", productConsumptionId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "external-links"));
					});
			}
		}
	%>'
/>