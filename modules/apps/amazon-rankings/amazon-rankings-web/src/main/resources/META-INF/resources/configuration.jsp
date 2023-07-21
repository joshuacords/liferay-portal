<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String isbnsString = StringUtil.merge(isbns, StringPool.SPACE);

isbns = StringUtil.split(ParamUtil.getString(request, "isbns", isbnsString), CharPool.SPACE);

isbnsString = StringUtil.merge(isbns, StringPool.SPACE);
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<div class="portlet-configuration-body-content">
		<div class="container-fluid-1280">
			<liferay-ui:error exception="<%= ValidatorException.class %>">

				<%
				ValidatorException ve = (ValidatorException)errorException;
				%>

				<liferay-ui:message key="the-following-are-invalid-isbn-numbers" />

				<%
				Enumeration enu = ve.getFailedKeys();

				while (enu.hasMoreElements()) {
					String isbn = (String)enu.nextElement();
				%>

					<strong><%= HtmlUtil.escape(isbn) %></strong><%= enu.hasMoreElements() ? ", " : "." %>

				<%
				}
				%>

			</liferay-ui:error>

			<aui:fieldset>
				<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) || windowState.equals(LiferayWindowState.POP_UP) %>" cssClass="lfr-textarea-container" label="add-all-isbn-numbers-separated-by-spaces" name="preferences--isbns--" type="textarea" value="<%= isbnsString %>" wrap="soft" />
			</aui:fieldset>
		</div>
	</div>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>