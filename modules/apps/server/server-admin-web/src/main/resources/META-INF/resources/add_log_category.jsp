<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(LanguageUtil.get(request, "add-category"));
%>

<portlet:actionURL name="/server_admin/edit_server" var="addLogCategoryURL">
	<portlet:param name="cmd" value="addLogLevel" />
	<portlet:param name="redirect" value="<%= String.valueOf(redirect) %>" />
</portlet:actionURL>

<aui:form action="<%= addLogCategoryURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input cssClass="lfr-input-text-container" label="logger-name" name="loggerName" type="text" />

			<aui:select label="log-level" name="priority">

				<%
				for (int i = 0; i < Levels.ALL_LEVELS.length; i++) {
				%>

					<aui:option label="<%= Levels.ALL_LEVELS[i] %>" selected="<%= Level.INFO.equals(Levels.ALL_LEVELS[i]) %>" />

				<%
				}
				%>

			</aui:select>
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>