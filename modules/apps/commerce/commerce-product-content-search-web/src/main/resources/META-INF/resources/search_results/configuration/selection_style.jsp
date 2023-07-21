<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPSearchResultsDisplayContext cpSearchResultsDisplayContext = (CPSearchResultsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<aui:fieldset markupView="lexicon">
	<aui:input checked="<%= cpSearchResultsDisplayContext.isSelectionStyleADT() %>" id="selectionStyleADT" label="use-adt" name="preferences--selectionStyle--" onChange='<%= renderResponse.getNamespace() + "chooseSelectionStyle();" %>' type="radio" value="adt" />

	<aui:input checked="<%= cpSearchResultsDisplayContext.isSelectionStyleCustomRenderer() %>" id="selectionStyleCustomRenderer" label="use-custom-renderer" name="preferences--selectionStyle--" onChange='<%= renderResponse.getNamespace() + "chooseSelectionStyle();" %>' type="radio" value="custom" />
</aui:fieldset>

<aui:script>
	function <portlet:namespace />chooseSelectionStyle() {
		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>