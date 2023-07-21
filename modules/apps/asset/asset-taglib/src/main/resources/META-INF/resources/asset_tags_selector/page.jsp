<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/asset_tags_selector/init.jsp" %>

<%
String addCallback = GetterUtil.getString((String)request.getAttribute("liferay-asset:asset-tags-selector:addCallback"));
boolean allowAddEntry = GetterUtil.getBoolean((String)request.getAttribute("liferay-asset:asset-tags-selector:allowAddEntry"));
String eventName = (String)request.getAttribute("liferay-asset:asset-tags-selector:eventName");
long[] groupIds = (long[])request.getAttribute("liferay-asset:asset-tags-selector:groupIds");
String hiddenInput = (String)request.getAttribute("liferay-asset:asset-tags-selector:hiddenInput");
String id = GetterUtil.getString((String)request.getAttribute("liferay-asset:asset-tags-selector:id"));
PortletURL portletURL = (PortletURL)request.getAttribute("liferay-asset:asset-tags-selector:portletURL");
String removeCallback = GetterUtil.getString((String)request.getAttribute("liferay-asset:asset-tags-selector:removeCallback"));
String tagNames = GetterUtil.getString((String)request.getAttribute("liferay-asset:asset-tags-selector:tagNames"));
%>

<h4>
	<liferay-ui:message key="tags" />
</h4>

<div class="lfr-tags-selector-content" id="<portlet:namespace /><%= id %>assetTagsSelector">
	<aui:input name="<%= hiddenInput %>" type="hidden" />

	<c:if test="<%= allowAddEntry %>">
		<input class="form-control lfr-tag-selector-input" id="<%= id %>assetTagNames" size="15" title="<liferay-ui:message key="add-tags" />" type="text" />
	</c:if>
</div>

<aui:script use="liferay-asset-taglib-tags-selector">
	var assetTaglibTagsSelector = new Liferay.AssetTaglibTagsSelector({
		allowAddEntry: <%= allowAddEntry %>,
		contentBox: '#<portlet:namespace /><%= id %>assetTagsSelector',

		<c:if test="<%= groupIds != null %>">
			groupIds: '<%= StringUtil.merge(groupIds) %>',
		</c:if>

		hiddenInput: '#<portlet:namespace /><%= hiddenInput %>',

		<c:if test="<%= allowAddEntry %>">
			input: '#<%= id %>assetTagNames',
		</c:if>

		<c:if test="<%= Validator.isNotNull(eventName) %>">
			eventName: '<%= eventName %>',
		</c:if>

		maxLength: <%= ModelHintsConstants.TEXT_MAX_LENGTH %>,

		<c:if test="<%= portletURL != null %>">
			portletURL: '<%= portletURL.toString() %>',
		</c:if>

		tagNames: '<%= HtmlUtil.escapeJS(tagNames) %>'
	}).render();

	Liferay.component('<portlet:namespace />tagsSelector', assetTaglibTagsSelector);

	<c:if test="<%= Validator.isNotNull(addCallback) %>">
		assetTaglibTagsSelector.entries.on('add', function(event) {
			window['<portlet:namespace /><%= addCallback %>'](event.item);
		});
	</c:if>

	<c:if test="<%= Validator.isNotNull(removeCallback) %>">
		assetTaglibTagsSelector.entries.on('remove', function(event) {
			window['<portlet:namespace /><%= removeCallback %>'](event.item);
		});
	</c:if>

	<c:if test='<%= GetterUtil.getBoolean((String)request.getAttribute("liferay-asset:asset-tags-selector:autoFocus")) %>'>
		Liferay.Util.focusFormField('#<%= id %>assetTagNames');
	</c:if>
</aui:script>