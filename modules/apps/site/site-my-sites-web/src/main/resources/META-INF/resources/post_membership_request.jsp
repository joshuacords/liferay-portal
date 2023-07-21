<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNull(redirect)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("tabs1", "available-sites");

	redirect = portletURL.toString();
}

long groupId = ParamUtil.getLong(request, "groupId");

Group group = GroupLocalServiceUtil.fetchGroup(groupId);
%>

<portlet:actionURL name="postMembershipRequest" var="postMembershipRequestURL">
	<portlet:param name="mvcPath" value="/post_membership_request.jsp" />
	<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
</portlet:actionURL>

<aui:form action="<%= postMembershipRequestURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<c:if test="<%= !layout.isTypeControlPanel() %>">
		<liferay-ui:header
			backURL="<%= redirect %>"
			escapeXml="<%= false %>"
			localizeTitle="<%= false %>"
			title='<%= LanguageUtil.format(request, "request-membership-for-x", HtmlUtil.escape(group.getDescriptiveName(locale)), false) %>'
		/>
	</c:if>

	<liferay-ui:error exception="<%= MembershipRequestCommentsException.class %>" message="please-enter-valid-comments" />

	<aui:model-context bean="<%= (MembershipRequest)request.getAttribute(WebKeys.MEMBERSHIP_REQUEST) %>" model="<%= MembershipRequest.class %>" />

	<c:if test="<%= Validator.isNotNull(group.getDescription()) %>">
		<div class="alert alert-info">
			<%= HtmlUtil.escape(group.getDescription(locale)) %>
		</div>
	</c:if>

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" name="comments" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>