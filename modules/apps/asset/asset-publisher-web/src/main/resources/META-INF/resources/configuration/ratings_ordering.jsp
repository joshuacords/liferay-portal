<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<aui:row id="ordering">
	<aui:col width="<%= 50 %>">
		<aui:select label="order-by" name="preferences--orderByColumn1--" value="<%= assetPublisherDisplayContext.getOrderByColumn1() %>" wrapperCssClass="field-inline w80">
			<aui:option label="average-score" value="ratings" />
			<aui:option label="total-score" value="ratingsTotalScore" />
		</aui:select>
	</aui:col>
</aui:row>

<aui:script use="aui-base">
	A.one('#<portlet:namespace />ordering').delegate(
		'click',
		function(event) {
			var currentTarget = event.currentTarget;

			var orderByTypeContainer = currentTarget.ancestor(
				'.order-by-type-container'
			);

			orderByTypeContainer.all('.icon').toggleClass('hide');

			var orderByTypeField = orderByTypeContainer.one('.order-by-type-field');

			var newVal = orderByTypeField.val() === 'ASC' ? 'DESC' : 'ASC';

			orderByTypeField.val(newVal);
		},
		'.icon'
	);
</aui:script>