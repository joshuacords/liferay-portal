<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "details");

ProductPurchase productPurchase = (ProductPurchase)request.getAttribute(TrunkWebKeys.PRODUCT_PURCHASE);

long productPurchaseId = BeanParamUtil.getLong(productPurchase, request, "productPurchaseId");
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems='<%=
		new JSPNavigationItemList(pageContext) {
			{
				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("details"));
						navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/products_admin/edit_product_purchase", "tabs1", "details", "productPurchaseId", productPurchaseId);
						navigationItem.setLabel(LanguageUtil.get(request, "details"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("external-links"));

						if (productPurchase != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/products_admin/edit_product_purchase", "tabs1", "external-links", "productPurchaseId", productPurchaseId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "external-links"));
					});
			}
		}
	%>'
/>