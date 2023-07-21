<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
if (movie.equals(StringPool.BLANK)) {
	movie = "http://www.youtube.com/v/WqjxI3kFPH0&hl=en";
}

if (flashAttributes.equals(StringPool.BLANK)) {
	StringBundler sb = new StringBundler(15);

	sb.append("align=left\n");
	sb.append("allowScriptAccess=sameDomain\n");
	sb.append("base=.\n");
	sb.append("bgcolor=#FFFFFF\n");
	sb.append("devicefont=true\n");
	sb.append("height=200\n");
	sb.append("loop=true\n");
	sb.append("menu=false\n");
	sb.append("play=false\n");
	sb.append("quality=best\n");
	sb.append("salign=\n");
	sb.append("scale=showall\n");
	sb.append("swliveconnect=false\n");
	sb.append("width=100%\n");
	sb.append("wmode=opaque");

	flashAttributes = sb.toString();
}

if (flashVariables.equals(StringPool.BLANK)) {
	flashVariables = "var1=hello\nvar2=world";
}

movie = ParamUtil.getString(request, "movie", movie);
flashAttributes = ParamUtil.getString(request, "flashAttributes", flashAttributes);
flashVariables = ParamUtil.getString(request, "flashVariables", flashVariables);
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input name="preferences--movie--" type="text" value="<%= movie %>" wrapperCssClass="lfr-input-text-container" />

			<aui:input name="preferences--flashAttributes--" onKeyDown="Liferay.Util.checkTab(this); disableEsc();" type="textarea" value="<%= flashAttributes %>" wrap="soft" wrapperCssClass="lfr-textarea-container" />

			<aui:input name="preferences--flashVariables--" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" type="textarea" value="<%= flashVariables %>" wrap="soft" wrapperCssClass="lfr-textarea-container" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>