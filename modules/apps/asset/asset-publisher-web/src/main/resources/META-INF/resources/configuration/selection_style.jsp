<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<aui:fieldset markupView="lexicon">
	<aui:input checked="<%= assetPublisherDisplayContext.isSelectionStyleDynamic() %>" id="selectionStyleDynamic" label="dynamic" name="preferences--selectionStyle--" onChange='<%= renderResponse.getNamespace() + "chooseSelectionStyle();" %>' type="radio" value="dynamic" />

	<aui:input checked="<%= assetPublisherDisplayContext.isSelectionStyleManual() %>" id="selectionStyleManual" label="manual" name="preferences--selectionStyle--" onChange='<%= renderResponse.getNamespace() + "chooseSelectionStyle();" %>' type="radio" value="manual" />

	<aui:input checked="<%= assetPublisherDisplayContext.isSelectionStyleAssetList() %>" id="selectionStyleAssetList" label="content-set" name="preferences--selectionStyle--" onChange='<%= renderResponse.getNamespace() + "chooseSelectionStyle();" %>' type="radio" value="asset-list" />

	<aui:input checked="<%= assetPublisherDisplayContext.isSelectionStyleAssetListProvider() %>" id="selectionStyleInfoListProvider" label="content-set-provider" name="preferences--selectionStyle--" onChange='<%= renderResponse.getNamespace() + "chooseSelectionStyle();" %>' type="radio" value="asset-list-provider" />
</aui:fieldset>

<script>
	function <portlet:namespace />chooseSelectionStyle() {
		Liferay.Util.postForm(document.<portlet:namespace />fm, {
			data: {
				cmd: 'selection-style'
			}
		});
	}
</script>