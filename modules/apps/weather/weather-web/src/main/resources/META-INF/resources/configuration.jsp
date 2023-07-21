<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String zipsString = StringUtil.merge(zips, StringPool.NEW_LINE);

zips = StringUtil.split(ParamUtil.getString(request, "zips", zipsString), StringPool.NEW_LINE);

zipsString = StringUtil.merge(zips, StringPool.NEW_LINE);
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-frontend:edit-form
	action="<%= configurationActionURL %>"
	cssClass="container-fluid-1280"
	method="post"
>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<liferay-frontend:edit-form-body>
		<liferay-ui:error exception="<%= ValidatorException.class %>">

			<%
			ValidatorException ve = (ValidatorException)errorException;
			%>

			<liferay-ui:message key="the-following-are-invalid-cities-or-zip-codes" />

			<%
			Enumeration<String> enu = ve.getFailedKeys();

			while (enu.hasMoreElements()) {
				String zip = enu.nextElement();
			%>

				<strong><%= HtmlUtil.escape(zip) %></strong><%= enu.hasMoreElements() ? ", " : "." %>

			<%
			}
			%>

		</liferay-ui:error>

		<liferay-frontend:fieldset-group>
			<liferay-frontend:fieldset>
				<aui:input label="openweathermap-api-key" name="preferences--apiKey--" type="text" value="<%= apiKey %>" />

				<aui:input label="enter-one-city-or-zip-code-per-line" name="preferences--zips--" type="textarea" value="<%= zipsString %>" wrapperCssClass="lfr-textarea-container" />

				<aui:select label="temperature-format" name="preferences--fahrenheit--">
					<aui:option label="fahrenheit" selected="<%= fahrenheit %>" value="1" />
					<aui:option label="celsius" selected="<%= !fahrenheit %>" value="0" />
				</aui:select>
			</liferay-frontend:fieldset>
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<aui:button type="submit" />

		<aui:button type="cancel" />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>