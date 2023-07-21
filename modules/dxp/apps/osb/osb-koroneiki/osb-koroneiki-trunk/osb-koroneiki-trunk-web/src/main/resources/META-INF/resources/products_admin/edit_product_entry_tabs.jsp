<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "details");

ProductEntry productEntry = (ProductEntry)request.getAttribute(TrunkWebKeys.PRODUCT_ENTRY);

long productEntryId = BeanParamUtil.getLong(productEntry, request, "productEntryId");
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems='<%=
		new JSPNavigationItemList(pageContext) {
			{
				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("details"));
						navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/products_admin/edit_product_entry", "tabs1", "details", "productEntryId", productEntryId);
						navigationItem.setLabel(LanguageUtil.get(request, "details"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("external-links"));

						if (productEntry != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/products_admin/edit_product_entry", "tabs1", "external-links", "productEntryId", productEntryId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "external-links"));
					});
			}
		}
	%>'
/>