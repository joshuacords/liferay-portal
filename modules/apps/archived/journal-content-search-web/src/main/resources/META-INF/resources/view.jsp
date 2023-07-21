<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<portlet:renderURL var="backURL">
	<portlet:param name="mvcPath" value="/view.jsp" />
</portlet:renderURL>

<portlet:renderURL var="searchURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="mvcPath" value="/search.jsp" />
	<portlet:param name="backURL" value="<%= backURL %>" />

	<c:if test="<%= Validator.isNotNull(journalContentSearchPortletInstanceConfiguration.targetPortletId()) %>">
		<portlet:param name="targetPortletId" value="<%= journalContentSearchPortletInstanceConfiguration.targetPortletId() %>" />
	</c:if>
</portlet:renderURL>

<aui:form action="<%= searchURL %>" method="post" name="fm" onSubmit='<%= renderResponse.getNamespace() + "search(); event.preventDefault();" %>'>
	<div class="form-search">
		<liferay-ui:input-search
			name="keywords"
			placeholder='<%= LanguageUtil.get(request, "keywords") %>'
		/>
	</div>
</aui:form>

<aui:script>
	function <portlet:namespace />search() {
		var keywords =
			document.<portlet:namespace />fm.<portlet:namespace />keywords.value;

		keywords = keywords.replace(/^\s+|\s+$/, '');

		if (keywords != '') {
			submitForm(document.<portlet:namespace />fm);
		}
	}
</aui:script>