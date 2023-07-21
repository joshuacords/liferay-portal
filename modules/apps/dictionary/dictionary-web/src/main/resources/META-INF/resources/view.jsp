<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<aui:form cssClass="form-inline" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "lookUp();" %>'>
	<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" inlineField="<%= true %>" label="" name="word" />

	<aui:select inlineField="<%= true %>" label="" name="type">
		<aui:option label="dictionary" value="http://dictionary.reference.com/browse/" />
		<aui:option label="thesaurus" value="http://thesaurus.reference.com/browse/" />
	</aui:select>

	<aui:button type="submit" value="find" />
</aui:form>

<aui:script>
	function <portlet:namespace />lookUp() {
		var form = document.<portlet:namespace />fm;

		var type = form.<portlet:namespace />type.selectedIndex;
		var word = form.<portlet:namespace />word.value;

		window.open(
			form.<portlet:namespace />type[type].value + encodeURIComponent(word)
		);
	}
</aui:script>