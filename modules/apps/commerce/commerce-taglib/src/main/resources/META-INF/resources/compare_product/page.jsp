<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/compare_product/init.jsp" %>

<%
boolean checked = (boolean)request.getAttribute("liferay-commerce:compare-product:checked");
long cpDefinitionId = (long)request.getAttribute("liferay-commerce:compare-product:cpDefinitionId");

String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_commerce_compare_product_page") + StringPool.UNDERLINE;
%>

<liferay-portlet:actionURL name="editCompareProduct" portletName="<%= CPPortletKeys.CP_COMPARE_CONTENT_WEB %>" var="editCompareProductActionURL" />

<div class="commerce-compare-product-container">
	<aui:form action="<%= editCompareProductActionURL %>" name='<%= randomNamespace + "Fm" %>' portletNamespace="<%= PortalUtil.getPortletNamespace(CPPortletKeys.CP_COMPARE_CONTENT_WEB) %>">
		<aui:input name="redirect" type="hidden" value="<%= PortalUtil.getCurrentURL(request) %>" />
		<aui:input name="cpDefinitionId" type="hidden" value="<%= cpDefinitionId %>" />

		<aui:input checked="<%= checked %>" ignoreRequestValue="<%= true %>" label="compare" name='<%= cpDefinitionId + "Compare" %>' onClick="this.form.submit();" type="checkbox" />
	</aui:form>
</div>