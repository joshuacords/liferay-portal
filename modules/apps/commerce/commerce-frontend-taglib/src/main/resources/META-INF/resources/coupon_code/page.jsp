<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/coupon_code/init.jsp" %>

<%
CommerceOrder commerceOrder = (CommerceOrder)request.getAttribute("liferay-commerce:coupon-code:commerceOrder");

String couponCode = null;

if (commerceOrder != null) {
	couponCode = commerceOrder.getCouponCode();
}
%>

<c:choose>
	<c:when test="<%= commerceOrder == null %>">
	</c:when>
	<c:when test="<%= Validator.isNotNull(couponCode) %>">
		<div class="coupon-code-header">
			<h5><liferay-ui:message key="coupon-code" /></h5>
		</div>

		<div class="coupon-code-body">
			<h3 class="d-inline"><%= HtmlUtil.escape(couponCode) %></h3>

			<a class="d-inline" href="javascript:;" id="<portlet:namespace />couponCodeIconRemove">
				<liferay-ui:icon
					icon="times"
					markupView="lexicon"
					message="remove"
				/>
			</a>
		</div>

		<aui:script>
			var couponCodeIconRemove = window.document.querySelector(
				'#<portlet:namespace />couponCodeIconRemove'
			);

			couponCodeIconRemove.addEventListener(
				'click',
				function(event) {
					var actionURL =
						'<%= PortalUtil.getPortalURL(request) + "/o/commerce-ui/order/" + commerceOrder.getCommerceOrderId() + "/coupon-code" %>';

					fetch(actionURL, {
						credentials: 'include',
						headers: new Headers({'x-csrf-token': Liferay.authToken}),
						method: 'post'
					})
						.then(function(res) {
							return res.json();
						})
						.then(function(payload) {
							if (payload.success) {
								window.location.reload();
							}
							else {
								new Liferay.Notification({
									closeable: true,
									delay: {
										hide: 5000,
										show: 0
									},
									duration: 500,
									message:
										'<liferay-ui:message key="please-enter-a-valid-coupon-code" />',
									render: true,
									title: '<liferay-ui:message key="danger" />',
									type: 'danger'
								});
							}
						});
				},
				{
					once: true
				}
			);
		</aui:script>
	</c:when>
	<c:otherwise>
		<aui:input label="" name="couponCode" placeholder="enter-promo-code" type="text" />

		<aui:button name="applyCouponCodeButton" type="submit" value="apply" />

		<aui:script>
			var applyCouponCodeButton = window.document.querySelector(
				'#<portlet:namespace />applyCouponCodeButton'
			);

			applyCouponCodeButton.addEventListener(
				'click',
				function(event) {
					var actionURL =
						'<%= PortalUtil.getPortalURL(request) + "/o/commerce-ui/order/" + commerceOrder.getCommerceOrderId() + "/coupon-code/" %>';

					actionURL =
						actionURL +
						window.document.querySelector('#<portlet:namespace />couponCode')
							.value;

					fetch(actionURL, {
						credentials: 'include',
						headers: new Headers({'x-csrf-token': Liferay.authToken}),
						method: 'post'
					})
						.then(function(res) {
							return res.json();
						})
						.then(function(payload) {
							if (payload.success) {
								window.location.reload();
							}
							else {
								new Liferay.Notification({
									closeable: true,
									delay: {
										hide: 5000,
										show: 0
									},
									duration: 500,
									message:
										'<liferay-ui:message key="please-enter-a-valid-coupon-code" />',
									render: true,
									title: '<liferay-ui:message key="danger" />',
									type: 'danger'
								});
							}
						});
				},
				{
					once: true
				}
			);
		</aui:script>
	</c:otherwise>
</c:choose>