<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long oAuthApplicationId = ParamUtil.getLong(renderRequest, "oAuthApplicationId");

OAuthApplication oAuthApplication = OAuthApplicationLocalServiceUtil.fetchOAuthApplication(oAuthApplicationId);
%>

<liferay-ui:error exception="<%= ImageTypeException.class %>" message="please-enter-a-file-with-a-valid-file-type" />

<c:choose>
	<c:when test='<%= SessionMessages.contains(renderRequest, "requestProcessed") %>'>
		<aui:script>
			window.close();
			opener.<portlet:namespace />changeLogo(
				'<%= themeDisplay.getPathImage() + "/logo?img_id=" + oAuthApplication.getLogoId() + "&t=" + WebServerServletTokenUtil.getToken(oAuthApplication.getLogoId()) %>'
			);
		</aui:script>
	</c:when>
	<c:otherwise>
		<portlet:actionURL name="updateLogo" var="updateLogoURL">
			<portlet:param name="mvcPath" value="/admin/edit_application_logo.jsp" />
			<portlet:param name="oAuthApplicationId" value="<%= StringUtil.valueOf(oAuthApplicationId) %>" />
		</portlet:actionURL>

		<aui:form action="<%= updateLogoURL %>" enctype="multipart/form-data" method="post" name="fm">
			<liferay-ui:error exception="<%= UploadException.class %>" message="an-unexpected-error-occurred-while-uploading-your-file" />

			<aui:fieldset>
				<aui:input label="" name="fileName" size="50" type="file" />

				<aui:button-row>
					<aui:button type="submit" />

					<aui:button onClick="window.close();" type="cancel" value="close" />
				</aui:button-row>
			</aui:fieldset>
		</aui:form>

		<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
			<aui:script>
				Liferay.Util.focusFormField(
					document.<portlet:namespace />fm.<portlet:namespace />fileName
				);
			</aui:script>
		</c:if>
	</c:otherwise>
</c:choose>