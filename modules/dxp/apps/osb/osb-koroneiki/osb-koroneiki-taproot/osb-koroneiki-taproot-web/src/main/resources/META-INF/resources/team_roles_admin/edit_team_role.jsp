<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

TeamRole teamRole = (TeamRole)request.getAttribute(TaprootWebKeys.TEAM_ROLE);
%>

<portlet:actionURL name="/team_roles_admin/edit_team_role" var="editTeamRoleURL" />

<aui:form action="<%= editTeamRoleURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="teamRoleId" type="hidden" value='<%= BeanParamUtil.getLong(teamRole, request, "teamRoleId") %>' />

	<liferay-ui:error exception="<%= TeamRoleNameException.class %>" message="please-enter-a-valid-name" />
	<liferay-ui:error exception="<%= TeamRoleTypeException.class %>" message="please-enter-a-valid-type" />

	<aui:model-context bean="<%= teamRole %>" model="<%= TeamRole.class %>" />

	<aui:fieldset-group>
		<aui:fieldset>
			<c:choose>
				<c:when test="<%= teamRole != null %>">
					<aui:input label="key" name="key" type="resource" value="<%= teamRole.getTeamRoleKey() %>" />

					<h5><liferay-ui:message key="type" /></h5>

					<p>
						<%= teamRole.getType() %>
					</p>
				</c:when>
				<c:otherwise>
					<aui:select name="type">

						<%
						for (com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRole.Type teamRoleType : com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRole.Type.values()) {
						%>

							<aui:option label="<%= teamRoleType.toString() %>" value="<%= teamRoleType.toString() %>" />

						<%
						}
						%>

					</aui:select>
				</c:otherwise>
			</c:choose>

			<aui:input name="name" />

			<aui:input name="description" type="textarea" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>