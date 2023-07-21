<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

MDRRuleGroupInstance mdrRuleGroupInstance = (MDRRuleGroupInstance)row.getObject();

MDRRuleGroup mdrRuleGroup = MDRRuleGroupLocalServiceUtil.getMDRRuleGroup(mdrRuleGroupInstance.getRuleGroupId());
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= MDRRuleGroupInstancePermission.contains(permissionChecker, mdrRuleGroupInstance.getRuleGroupInstanceId(), ActionKeys.UPDATE) %>">
		<liferay-ui:icon
			id='<%= row.getRowId() + "manageActions" %>'
			message="manage-actions"
			url="javascript:;"
		/>
	</c:if>

	<c:if test="<%= MDRRuleGroupInstancePermission.contains(permissionChecker, mdrRuleGroupInstance.getRuleGroupInstanceId(), ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= MDRRuleGroupInstance.class.getName() %>"
			modelResourceDescription="<%= mdrRuleGroup.getName(locale) %>"
			resourcePrimKey="<%= String.valueOf(mdrRuleGroupInstance.getRuleGroupInstanceId()) %>"
			var="permissionsURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			message="permissions"
			url="<%= permissionsURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= MDRRuleGroupInstancePermission.contains(permissionChecker, mdrRuleGroupInstance.getRuleGroupInstanceId(), ActionKeys.DELETE) %>">
		<liferay-ui:icon-delete
			url='<%= "javascript:" + liferayPortletResponse.getNamespace() + "deleteRuleGroupInstance(" + mdrRuleGroupInstance.getRuleGroupInstanceId() + ");" %>'
		/>
	</c:if>
</liferay-ui:icon-menu>

<c:if test="<%= MDRRuleGroupInstancePermission.contains(permissionChecker, mdrRuleGroupInstance.getRuleGroupInstanceId(), ActionKeys.UPDATE) %>">
	<aui:script use="aui-base">
		<liferay-portlet:renderURL portletName="<%= MDRPortletKeys.MOBILE_DEVICE_RULES %>" varImpl="viewRuleGroupInstanceActionsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcRenderCommandName" value="/mobile_device_rules/view_actions" />
			<portlet:param name="showBackURL" value="<%= Boolean.FALSE.toString() %>" />
			<portlet:param name="redirect" value='<%= HttpUtil.setParameter(currentURL, liferayPortletResponse.getNamespace() + "historyKey", "mobileDeviceRules") %>' />
			<portlet:param name="ruleGroupInstanceId" value="<%= String.valueOf(mdrRuleGroupInstance.getRuleGroupInstanceId()) %>" />
		</liferay-portlet:renderURL>

		A.one('#<portlet:namespace /><%= row.getRowId() %>manageActions').on(
			'click',
			function(event) {
				var currentTarget = event.currentTarget;

				Liferay.Util.openWindow({
					dialog: {
						on: {
							visibleChange: function(event) {
								<portlet:namespace />updateRuleGroupInstances();
							}
						}
					},
					dialogIframe: {
						bodyCssClass: 'dialog-with-footer'
					},
					title:
						'<liferay-ui:message arguments="<%= HtmlUtil.escape(mdrRuleGroup.getName(locale)) %>" key="actions-for-x" translateArguments="<%= false %>" />',
					uri: '<%= viewRuleGroupInstanceActionsURL.toString() %>'
				});
			}
		);
	</aui:script>
</c:if>