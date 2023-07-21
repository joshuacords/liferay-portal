<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceInventoryWarehousesDisplayContext commerceInventoryWarehousesDisplayContext = (CommerceInventoryWarehousesDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

List<CommerceChannel> commerceChannels = commerceInventoryWarehousesDisplayContext.getCommerceChannels();
long[] commerceChannelIds = commerceInventoryWarehousesDisplayContext.getCommerceChannelRelCommerceChannelIds();
%>

<c:choose>
	<c:when test="<%= commerceChannels.isEmpty() %>">
		<div class="alert alert-info">
			<liferay-ui:message key="there-are-no-channels" />
		</div>
	</c:when>
	<c:otherwise>
		<liferay-ui:error-marker
			key="<%= WebKeys.ERROR_SECTION %>"
			value="channels"
		/>

		<aui:fieldset>

			<%
			for (CommerceChannel commerceChannel : commerceChannels) {
			%>

				<aui:input checked="<%= ArrayUtil.contains(commerceChannelIds, commerceChannel.getCommerceChannelId()) %>" label="<%= HtmlUtil.escape(commerceChannel.getName()) %>" name='<%= "commerceChannelId_" + commerceChannel.getCommerceChannelId() %>' onChange='<%= renderResponse.getNamespace() + "fulfillCommerceChannelIds();" %>' type="checkbox" value="<%= commerceChannel.getCommerceChannelId() %>" />

			<%
			}
			%>

		</aui:fieldset>
	</c:otherwise>
</c:choose>

<aui:script>
	function <portlet:namespace />fulfillCommerceChannelIds(e) {
		var form = AUI.$(document.<portlet:namespace />fm);
		var values = Liferay.Util.listCheckedExcept(
			form,
			'<portlet:namespace />allRowIds'
		);
		form.fm('commerceChannelIds').val(values);
		return values;
	}

	<portlet:namespace />fulfillCommerceChannelIds();
</aui:script>