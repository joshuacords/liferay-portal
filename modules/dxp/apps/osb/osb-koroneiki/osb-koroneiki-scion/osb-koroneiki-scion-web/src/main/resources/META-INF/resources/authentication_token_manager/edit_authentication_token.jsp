<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

AuthenticationToken authenticationToken = (AuthenticationToken)request.getAttribute(ScionWebKeys.AUTHENTICATION_TOKEN);
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title="authentication-token"
/>

<portlet:actionURL name="/authentication_token_manager/edit_authentication_token" var="editAuthenticationTokenURL" />

<aui:form action="<%= editAuthenticationTokenURL %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="authenticationTokenId" type="hidden" value='<%= BeanParamUtil.getLong(authenticationToken, request, "authenticationTokenId") %>' />

	<aui:model-context bean="<%= authenticationToken %>" model="<%= AuthenticationToken.class %>" />

	<aui:fieldset-group>
		<aui:fieldset>
			<aui:input name="name" />

			<c:choose>
				<c:when test="<%= authenticationToken != null %>">
					<aui:input name="token" type="resource" value="<%= authenticationToken.getMaskedToken() %>" />
				</c:when>
				<c:otherwise>

					<%
					String token = (String)request.getAttribute(ScionWebKeys.TOKEN);
					%>

					<aui:input name="token" type="hidden" value="<%= token %>" />

					<aui:input label="token" name="tokenDisplay" type="resource" value="<%= token %>" />

					<div class="alert alert-warning">
						<liferay-ui:message key="make-sure-to-copy-this-authentication-token-now.-you-won't-be-able-to-see-it-again-after-you-save" />
					</div>
				</c:otherwise>
			</c:choose>
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>