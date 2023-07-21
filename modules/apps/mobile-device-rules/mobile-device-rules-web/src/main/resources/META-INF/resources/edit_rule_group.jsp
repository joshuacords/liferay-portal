<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL");

MDRRuleGroup ruleGroup = (MDRRuleGroup)renderRequest.getAttribute(MDRWebKeys.MOBILE_DEVICE_RULES_RULE_GROUP);

long ruleGroupId = BeanParamUtil.getLong(ruleGroup, request, "ruleGroupId");

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle((ruleGroup == null) ? LanguageUtil.get(resourceBundle, "new-device-family") : ruleGroup.getName(locale));
%>

<portlet:actionURL name="/mobile_device_rules/edit_rule_group" var="editRuleGroupURL">
	<portlet:param name="mvcRenderCommandName" value="/mobile_device_rules/edit_rule_group" />
</portlet:actionURL>

<aui:form action="<%= editRuleGroupURL %>" cssClass="container-fluid-1280" enctype="multipart/form-data" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (ruleGroup == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="ruleGroupId" type="hidden" value="<%= ruleGroupId %>" />
	<aui:input name="groupId" type="hidden" value="<%= groupId %>" />

	<liferay-ui:error exception="<%= NoSuchRuleGroupException.class %>" message="device-family-does-not-exist" />

	<aui:model-context bean="<%= ruleGroup %>" model="<%= MDRRuleGroup.class %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<c:if test="<%= ruleGroup == null %>">
				<p class="text-default">
					<liferay-ui:message key="device-family-help" />
				</p>
			</c:if>

			<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) || windowState.equals(LiferayWindowState.POP_UP) %>" name="name" placeholder="name" />

			<aui:input name="description" placeholder="description" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" value="cancel" />
	</aui:button-row>
</aui:form>