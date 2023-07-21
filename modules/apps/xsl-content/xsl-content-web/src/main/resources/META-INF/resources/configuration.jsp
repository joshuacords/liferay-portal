<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form
	action="<%= configurationActionURL %>"
	method="post"
	name="fm"
>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<liferay-frontend:edit-form-body>
		<liferay-ui:error key="xmlUrl" message="please-enter-a-valid-xml-url" />
		<liferay-ui:error key="xslUrl" message="please-enter-a-valid-xsl-url" />
		<liferay-ui:error key="transformation" message="an-error-occurred-while-processing-your-xml-and-xsl" />

		<%
		String validUrlPrefixes = xslContentConfiguration.validUrlPrefixes();
		%>

		<c:if test="<%= Validator.isNotNull(validUrlPrefixes) %>">
			<div class="alert alert-info">
				<liferay-ui:message arguments="<%= validUrlPrefixes %>" key="urls-must-begin-with-one-of-the-following" />
			</div>
		</c:if>

		<liferay-frontend:fieldset-group>
			<liferay-frontend:fieldset>
				<aui:input cssClass="lfr-input-text-container" name="preferences--xmlUrl--" type="text" value="<%= xslContentPortletInstanceConfiguration.xmlUrl() %>" />

				<aui:input cssClass="lfr-input-text-container" name="preferences--xslUrl--" type="text" value="<%= xslContentPortletInstanceConfiguration.xslUrl() %>" />
			</liferay-frontend:fieldset>
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<aui:button type="submit" />

		<aui:button type="cancel" />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>