<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
EditSegmentsEntryDisplayContext editSegmentsEntryDisplayContext = (EditSegmentsEntryDisplayContext)request.getAttribute(SegmentsWebKeys.EDIT_SEGMENTS_ENTRY_DISPLAY_CONTEXT);

String backURL = editSegmentsEntryDisplayContext.getBackURL();

if (Validator.isNotNull(backURL)) {
	portletDisplay.setShowBackIcon(true);
	portletDisplay.setURLBack(backURL);
}

renderResponse.setTitle(editSegmentsEntryDisplayContext.getTitle(locale));
%>

<liferay-ui:error embed="<%= false %>" exception="<%= SegmentsEntryCriteriaException.class %>" message="invalid-criteria" />
<liferay-ui:error embed="<%= false %>" exception="<%= SegmentsEntryKeyException.class %>" message="key-is-already-used" />
<liferay-ui:error embed="<%= false %>" exception="<%= SegmentsEntryNameException.class %>" message="please-enter-a-valid-name" />

<portlet:actionURL name="updateSegmentsEntry" var="updateSegmentsEntryActionURL" />

<aui:form action="<%= updateSegmentsEntryActionURL %>" method="post" name="editSegmentFm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveSegmentsEntry();" %>'>
	<aui:input name="redirect" type="hidden" value="<%= editSegmentsEntryDisplayContext.getRedirect() %>" />
	<aui:input name="segmentsEntryId" type="hidden" value="<%= editSegmentsEntryDisplayContext.getSegmentsEntryId() %>" />
	<aui:input name="groupId" type="hidden" value="<%= editSegmentsEntryDisplayContext.getGroupId() %>" />
	<aui:input name="segmentsEntryKey" type="hidden" value="<%= editSegmentsEntryDisplayContext.getSegmentsEntryKey() %>" />
	<aui:input name="type" type="hidden" value="<%= editSegmentsEntryDisplayContext.getType() %>" />
	<aui:input name="dynamic" type="hidden" value="<%= true %>" />

	<div id="<%= renderResponse.getNamespace() %>-segment-edit-root">
		<div class="inline-item my-5 p-5 w-100">
			<span aria-hidden="true" class="loading-animation"></span>
		</div>

		<react:component
			data="<%= editSegmentsEntryDisplayContext.getData() %>"
			module="js/SegmentsApp.es"
		/>
	</div>
</aui:form>

<aui:script>
	function <portlet:namespace />saveSegmentsEntry() {
		submitForm(document.<portlet:namespace />editSegmentFm);
	}
</aui:script>