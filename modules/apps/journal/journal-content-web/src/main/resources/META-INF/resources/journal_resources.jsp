<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String refererPortletName = ParamUtil.getString(request, "refererPortletName");

JournalArticle article = journalContentDisplayContext.getArticle();
%>

<aui:input id='<%= refererPortletName + "ddmTemplateKey" %>' name='<%= refererPortletName + "preferences--ddmTemplateKey--" %>' type="hidden" useNamespace="<%= false %>" value="<%= journalContentDisplayContext.isDefaultTemplate() ? StringPool.BLANK : journalContentDisplayContext.getDDMTemplateKey() %>" />

<div class="sheet-section">
	<div class="sheet-subtitle">
		<liferay-ui:message key="layout.types.article" />
	</div>

	<div class="row">
		<div class="col-md-4">
			<c:if test="<%= article != null %>">
				<liferay-util:include page="/journal_article_resources.jsp" servletContext="<%= application %>" />
			</c:if>
		</div>
	</div>

	<div>
		<aui:button cssClass="web-content-selector" name="webContentSelector" value='<%= Validator.isNull(article) ? "select" : "change" %>' />

		<c:if test="<%= article != null %>">
			<aui:button cssClass="selector-button" name="removeWebContent" value="remove" />
		</c:if>
	</div>
</div>

<c:if test="<%= article != null %>">
	<liferay-util:include page="/journal_template.jsp" servletContext="<%= application %>" />

	<div class="sheet-section">
		<div class="sheet-subtitle">
			<liferay-ui:message key="user-tools" />
		</div>

		<liferay-asset:asset-addon-entry-selector
			assetAddonEntries="<%= (List<AssetAddonEntry>)(List<?>)journalContentDisplayContext.getEnabledUserToolAssetAddonEntries() %>"
			hiddenInput="preferences--userToolAssetAddonEntryKeys--"
			id="userToolsAssetAddonEntriesSelector"
			selectedAssetAddonEntries="<%= (List<AssetAddonEntry>)(List<?>)journalContentDisplayContext.getSelectedUserToolAssetAddonEntries() %>"
			title='<%= LanguageUtil.get(request, "select-user-tools") %>'
		/>
	</div>

	<div class="sheet-section">
		<div class="sheet-subtitle">
			<liferay-ui:message key="content-metadata" />
		</div>

		<liferay-asset:asset-addon-entry-selector
			assetAddonEntries="<%= (List<AssetAddonEntry>)(List<?>)journalContentDisplayContext.getEnabledContentMetadataAssetAddonEntries() %>"
			hiddenInput="preferences--contentMetadataAssetAddonEntryKeys--"
			id="contentMetadataAssetAddonEntriesSelector"
			selectedAssetAddonEntries="<%= (List<AssetAddonEntry>)(List<?>)journalContentDisplayContext.getSelectedContentMetadataAssetAddonEntries() %>"
			title='<%= LanguageUtil.get(request, "select-content-metadata") %>'
		/>
	</div>

	<div class="sheet-section">
		<div class="sheet-subtitle">
			<liferay-ui:message key="enable" />
		</div>

		<aui:input label="view-count-increment" name="preferences--enableViewCountIncrement--" type="toggle-switch" value="<%= journalContentDisplayContext.isEnableViewCountIncrement() %>" />
	</div>
</c:if>