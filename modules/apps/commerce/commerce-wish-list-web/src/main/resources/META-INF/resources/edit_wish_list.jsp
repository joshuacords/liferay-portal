<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceWishListDisplayContext commerceWishListDisplayContext = (CommerceWishListDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceWishList commerceWishList = commerceWishListDisplayContext.getCommerceWishList();
%>

<portlet:actionURL name="editCommerceWishList" var="editCommerceWishListActionURL" />

<aui:form action="<%= editCommerceWishListActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commerceWishList == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="commerceWishListId" type="hidden" value="<%= commerceWishListDisplayContext.getCommerceWishListId() %>" />

	<liferay-ui:error exception="<%= CommerceWishListNameException.class %>" message="please-enter-a-valid-name" />

	<aui:model-context bean="<%= commerceWishList %>" model="<%= CommerceWishList.class %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input name="name" />

			<aui:input checked='<%= BeanParamUtil.getBoolean(commerceWishList, request, "defaultWishList") %>' label="default" name="defaultWishList" type="toggle-switch" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>