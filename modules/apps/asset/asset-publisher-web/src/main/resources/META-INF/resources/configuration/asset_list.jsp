<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
AssetListEntry assetListEntry = assetPublisherDisplayContext.fetchAssetListEntry();
%>

<aui:input id="assetListEntryId" name="preferences--assetListEntryId--" type="hidden" value="<%= (assetListEntry != null) ? assetListEntry.getAssetListEntryId() : StringPool.BLANK %>" />

<div class="form-group input-text-wrapper text-default" id="<portlet:namespace />assetListTitle">
	<c:choose>
		<c:when test="<%= assetListEntry != null %>">
			<%= HtmlUtil.escape(assetListEntry.getTitle()) %>
		</c:when>
		<c:otherwise>
			<span class="text-muted"><liferay-ui:message key="none" /></span>
		</c:otherwise>
	</c:choose>
</div>

<div class="button-row">
	<aui:button cssClass="mr-2" name="selectAssetListButton" value="select" />

	<aui:button name="clearAssetListButton" value="clear" />
</div>

<aui:script sandbox="<%= true %>">
	var assetListEntryId = document.getElementById(
		'<portlet:namespace />assetListEntryId'
	);
	var assetListTitle = document.getElementById(
		'<portlet:namespace />assetListTitle'
	);

	var selectAssetListButton = document.getElementById(
		'<portlet:namespace />selectAssetListButton'
	);

	if (selectAssetListButton) {
		selectAssetListButton.addEventListener('click', function(event) {
			var uri =
				'<%= assetPublisherDisplayContext.getAssetListSelectorURL() %>';

			uri = Liferay.Util.addParams(
				'<%= assetPublisherDisplayContext.getAssetListPortletNamespace() %>assetListEntryId=' +
					assetListEntryId.value,
				uri
			);

			Liferay.Util.selectEntity(
				{
					dialog: {
						constrain: true,
						destroyOnHide: true
					},
					eventName:
						'<%= assetPublisherDisplayContext.getSelectAssetListEventName() %>',
					id: '<portlet:namespace />selectAssetList',
					title: '<liferay-ui:message key="select-content-set" />',
					uri: uri
				},
				function(event) {
					assetListEntryId.value = event.assetlistentryid;

					assetListTitle.innerHTML = event.assetlistentrytitle;
				}
			);
		});
	}

	var clearAssetListButton = document.getElementById(
		'<portlet:namespace />clearAssetListButton'
	);

	if (clearAssetListButton) {
		clearAssetListButton.addEventListener('click', function(event) {
			assetListTitle.innerHTML = '<liferay-ui:message key="none" />';

			assetListEntryId.value = '';
		});
	}
</aui:script>