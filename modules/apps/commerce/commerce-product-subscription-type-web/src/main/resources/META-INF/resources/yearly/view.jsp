<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
YearlyCPSubscriptionTypeDisplayContext yearlyCPSubscriptionTypeDisplayContext = (YearlyCPSubscriptionTypeDisplayContext)request.getAttribute("view.jsp-yearlyCPSubscriptionTypeDisplayContext");

int selectedMonth = yearlyCPSubscriptionTypeDisplayContext.getSelectedMonth();
int selectedYearlyMode = yearlyCPSubscriptionTypeDisplayContext.getSelectedYearlyMode();
%>

<c:choose>
	<c:when test="<%= yearlyCPSubscriptionTypeDisplayContext.isPayment() %>">
		<aui:select label="mode" name="subscriptionTypeSettings--yearlyMode--" onChange='<%= "event.preventDefault(); changeYearlyCPSubscriptionTypeSettingsMode();" %>'>

			<%
			for (int mode : CPSubscriptionTypeConstants.YEARLY_MODES) {
			%>

				<aui:option label="<%= CPSubscriptionTypeConstants.getYearlyCPSubscriptionTypeModeLabel(mode) %>" selected="<%= selectedYearlyMode == mode %>" value="<%= mode %>" />

			<%
			}
			%>

		</aui:select>

		<div class="<%= (selectedYearlyMode == CPSubscriptionTypeConstants.MODE_EXACT_DAY_OF_YEAR) ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />exactDayOfYearInputContainer">
			<aui:select label="month" name="subscriptionTypeSettings--month--">

				<%
				for (int month : yearlyCPSubscriptionTypeDisplayContext.getCalendarMonths()) {
				%>

					<aui:option label="<%= yearlyCPSubscriptionTypeDisplayContext.getMonthDisplayName(month) %>" selected="<%= selectedMonth == month %>" value="<%= month %>" />

				<%
				}
				%>

			</aui:select>

			<aui:input label="day" name="subscriptionTypeSettings--monthDay--" value="<%= yearlyCPSubscriptionTypeDisplayContext.getMonthDay() %>">
				<aui:validator name="digits" />
				<aui:validator name="max">31</aui:validator>
				<aui:validator name="min">1</aui:validator>
			</aui:input>
		</div>

		<aui:script>
			function changeYearlyCPSubscriptionTypeSettingsMode() {
				var A = AUI();

				if (
					A.one('#<portlet:namespace />yearlyMode').val() ==
					'<%= CPSubscriptionTypeConstants.MODE_EXACT_DAY_OF_YEAR %>'
				) {
					A.one('#<portlet:namespace />exactDayOfYearInputContainer').removeClass(
						'hide'
					);
				}
				else {
					if (
						!A.one(
							'#<portlet:namespace />exactDayOfYearInputContainer'
						).hasClass('hide')
					) {
						A.one(
							'#<portlet:namespace />exactDayOfYearInputContainer'
						).addClass('hide');
					}
				}
			}
		</aui:script>
	</c:when>
	<c:otherwise>
		<aui:select label="mode" name="deliverySubscriptionTypeSettings--deliveryYearlyMode--" onChange='<%= "event.preventDefault(); changeYearlyDeliveryCPSubscriptionTypeSettingsMode();" %>'>

			<%
			for (int mode : CPSubscriptionTypeConstants.YEARLY_MODES) {
			%>

				<aui:option label="<%= CPSubscriptionTypeConstants.getYearlyCPSubscriptionTypeModeLabel(mode) %>" selected="<%= selectedYearlyMode == mode %>" value="<%= mode %>" />

			<%
			}
			%>

		</aui:select>

		<div class="<%= (selectedYearlyMode == CPSubscriptionTypeConstants.MODE_EXACT_DAY_OF_YEAR) ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />deliveryExactDayOfYearInputContainer">
			<aui:select label="month" name="deliverySubscriptionTypeSettings--deliveryMonth--">

				<%
				for (int month : yearlyCPSubscriptionTypeDisplayContext.getCalendarMonths()) {
				%>

					<aui:option label="<%= yearlyCPSubscriptionTypeDisplayContext.getMonthDisplayName(month) %>" selected="<%= selectedMonth == month %>" value="<%= month %>" />

				<%
				}
				%>

			</aui:select>

			<aui:input label="day" name="deliverySubscriptionTypeSettings--deliveryMonthDay--" value="<%= yearlyCPSubscriptionTypeDisplayContext.getMonthDay() %>">
				<aui:validator name="digits" />
				<aui:validator name="max">31</aui:validator>
				<aui:validator name="min">1</aui:validator>
			</aui:input>
		</div>

		<aui:script>
			function changeYearlyDeliveryCPSubscriptionTypeSettingsMode() {
				var A = AUI();

				if (
					A.one('#<portlet:namespace />deliveryYearlyMode').val() ==
					'<%= CPSubscriptionTypeConstants.MODE_EXACT_DAY_OF_YEAR %>'
				) {
					A.one(
						'#<portlet:namespace />deliveryExactDayOfYearInputContainer'
					).removeClass('hide');
				}
				else {
					if (
						!A.one(
							'#<portlet:namespace />deliveryExactDayOfYearInputContainer'
						).hasClass('hide')
					) {
						A.one(
							'#<portlet:namespace />deliveryExactDayOfYearInputContainer'
						).addClass('hide');
					}
				}
			}
		</aui:script>
	</c:otherwise>
</c:choose>