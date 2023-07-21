<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long previewAssetEntryId = ParamUtil.getLong(request, "previewAssetEntryId");
int previewAssetEntryType = ParamUtil.getInteger(request, "previewAssetEntryType");

AssetEntryResult assetEntryResult = (AssetEntryResult)request.getAttribute("view.jsp-assetEntryResult");

for (AssetEntry assetEntry : assetEntryResult.getAssetEntries()) {
	AssetRendererFactory<?> assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassNameId(assetEntry.getClassNameId());

	if (assetRendererFactory == null) {
		continue;
	}

	AssetRenderer<?> assetRenderer = null;

	try {
		if (previewAssetEntryId == assetEntry.getEntryId()) {
			assetRenderer = assetRendererFactory.getAssetRenderer(assetEntry.getClassPK(), previewAssetEntryType);
		}
		else {
			assetRenderer = assetRendererFactory.getAssetRenderer(assetEntry.getClassPK());
		}
	}
	catch (Exception e) {
		if (_log.isWarnEnabled()) {
			_log.warn(e, e);
		}
	}

	if ((assetRenderer == null) || (!assetRenderer.isDisplayable() && (previewAssetEntryId <= 0))) {
		continue;
	}

	request.setAttribute("view.jsp-assetEntry", assetEntry);
	request.setAttribute("view.jsp-assetRenderer", assetRenderer);
	request.setAttribute("view.jsp-assetRendererFactory", assetRendererFactory);
%>

	<liferay-util:include page="/view_asset_entry_full_content.jsp" servletContext="<%= application %>">
		<liferay-util:param name="viewSingleAsset" value="false" />
	</liferay-util:include>

<%
}
%>

<%!
private static Log _log = LogFactoryUtil.getLog("com_liferay_asset_publisher_web.view_asset_entries_full_content_jsp");
%>