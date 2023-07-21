<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPPublisherConfigurationDisplayContext cpPublisherConfigurationDisplayContext = (CPPublisherConfigurationDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<aui:fieldset markupView="lexicon">
	<aui:input checked="<%= cpPublisherConfigurationDisplayContext.isSelectionStyleDynamic() %>" id="selectionStyleDynamic" label="dynamic" name="preferences--selectionStyle--" onChange='<%= renderResponse.getNamespace() + "chooseSelectionStyle();" %>' type="radio" value="dynamic" />

	<aui:input checked="<%= cpPublisherConfigurationDisplayContext.isSelectionStyleManual() %>" id="selectionStyleManual" label="manual" name="preferences--selectionStyle--" onChange='<%= renderResponse.getNamespace() + "chooseSelectionStyle();" %>' type="radio" value="manual" />

	<aui:input checked="<%= cpPublisherConfigurationDisplayContext.isSelectionStyleDataSource() %>" id="selectionStyleDataSource" label="data-source" name="preferences--selectionStyle--" onChange='<%= renderResponse.getNamespace() + "chooseSelectionStyle();" %>' type="radio" value="dataSource" />
</aui:fieldset>

<aui:script>
	function <portlet:namespace />chooseSelectionStyle() {
		var form = AUI.$(document.<portlet:namespace />fm);

		form.fm('<%= Constants.CMD %>').val('selection-style');

		submitForm(form);
	}
</aui:script>