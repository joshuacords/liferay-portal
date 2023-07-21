<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.servlet.taglib.ui.AssetAddonEntry" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.JavaConstants" %><%@
page import="com.liferay.portal.kernel.util.ListUtil" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.taglib.aui.AUIUtil" %>

<%@ page import="java.util.List" %>

<%@ page import="javax.portlet.PortletRequest" %><%@
page import="javax.portlet.PortletResponse" %>

<liferay-theme:defineObjects />

<%
List<AssetAddonEntry> assetAddonEntries = (List<AssetAddonEntry>)request.getAttribute(WebKeys.ASSET_ADDON_ENTRIES);
String hiddenInput = (String)request.getAttribute("liferay-asset:asset-addon-entry-selector:hiddenInput");
String id = GetterUtil.getString((String)request.getAttribute("liferay-asset:asset-addon-entry-selector:id"));
List<AssetAddonEntry> selectedAssetAddonEntries = (List<AssetAddonEntry>)request.getAttribute("liferay-asset:asset-addon-entry-selector:selectedAssetAddonEntries");
String title = GetterUtil.getString((String)request.getAttribute("liferay-asset:asset-addon-entry-selector:title"));

PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);

PortletResponse portletResponse = (PortletResponse)request.getAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE);

String namespace = AUIUtil.getNamespace(portletRequest, portletResponse);
%>