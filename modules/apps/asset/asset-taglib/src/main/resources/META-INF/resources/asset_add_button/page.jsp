<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/asset_add_button/init.jsp" %>

<%
boolean addDisplayPageParameter = GetterUtil.getBoolean(request.getAttribute("liferay-asset:asset-add-button:addDisplayPageParameter"));
long[] allAssetCategoryIds = GetterUtil.getLongValues(request.getAttribute("liferay-asset:asset-add-button:allAssetCategoryIds"), null);
String[] allAssetTagNames = GetterUtil.getStringValues(request.getAttribute("liferay-asset:asset-add-button:allAssetTagNames"), (String[])null);
long[] classNameIds = GetterUtil.getLongValues(request.getAttribute("liferay-asset:asset-add-button:classNameIds"));
long[] classTypeIds = GetterUtil.getLongValues(request.getAttribute("liferay-asset:asset-add-button:classTypeIds"));
long[] groupIds = GetterUtil.getLongValues(request.getAttribute("liferay-asset:asset-add-button:groupIds"));
String redirect = (String)request.getAttribute("liferay-asset:asset-add-button:redirect");
boolean useDialog = GetterUtil.getBoolean(request.getAttribute("liferay-asset:asset-add-button:useDialog"), true);

boolean hasAddPortletURLs = false;

for (long groupId : groupIds) {
	List<AssetPublisherAddItemHolder> assetPublisherAddItemHolders = assetHelper.getAssetPublisherAddItemHolders((LiferayPortletRequest)portletRequest, (LiferayPortletResponse)portletResponse, groupId, classNameIds, classTypeIds, allAssetCategoryIds, allAssetTagNames, redirect);

	if ((assetPublisherAddItemHolders != null) && !assetPublisherAddItemHolders.isEmpty()) {
		hasAddPortletURLs = true;
	}
%>

	<c:if test="<%= hasAddPortletURLs %>">
		<aui:nav>
			<c:choose>
				<c:when test="<%= assetPublisherAddItemHolders.size() == 1 %>">

					<%
					AssetPublisherAddItemHolder assetPublisherAddItemHolder = assetPublisherAddItemHolders.get(0);

					String message = assetPublisherAddItemHolder.getModelResource();

					long curGroupId = groupId;

					Group group = GroupLocalServiceUtil.fetchGroup(groupId);

					if (!group.isStagedPortlet(assetPublisherAddItemHolder.getPortletId()) && !group.isStagedRemotely()) {
						curGroupId = group.getLiveGroupId();
					}
					%>

					<aui:nav-item href="<%= _getURL(curGroupId, plid, assetPublisherAddItemHolder.getPortletURL(), message, addDisplayPageParameter, layout, pageContext, portletResponse, useDialog, assetHelper) %>" label='<%= LanguageUtil.format(request, (groupIds.length == 1) ? "add-x" : "add-x-in-x", new Object[] {HtmlUtil.escape(message), HtmlUtil.escape((GroupLocalServiceUtil.getGroup(groupId)).getDescriptiveName(locale))}, false) %>' />
				</c:when>
				<c:otherwise>
					<aui:nav-item dropdown="<%= true %>" label='<%= LanguageUtil.format(request, (groupIds.length == 1) ? "add-new" : "add-new-in-x", HtmlUtil.escape((GroupLocalServiceUtil.getGroup(groupId)).getDescriptiveName(locale)), false) %>'>

						<%
						for (AssetPublisherAddItemHolder assetPublisherAddItemHolder : assetPublisherAddItemHolders) {
							String message = assetPublisherAddItemHolder.getModelResource();

							long curGroupId = groupId;

							Group group = GroupLocalServiceUtil.fetchGroup(groupId);

							if (!group.isStagedPortlet(assetPublisherAddItemHolder.getPortletId()) && !group.isStagedRemotely()) {
								curGroupId = group.getLiveGroupId();
							}
						%>

							<aui:nav-item href="<%= _getURL(curGroupId, plid, assetPublisherAddItemHolder.getPortletURL(), message, addDisplayPageParameter, layout, pageContext, portletResponse, useDialog, assetHelper) %>" label="<%= HtmlUtil.escape(message) %>" />

						<%
						}
						%>

					</aui:nav-item>
				</c:otherwise>
			</c:choose>
		</aui:nav>
	</c:if>

<%
}

request.setAttribute("liferay-asset:asset-add-button:hasAddPortletURLs", hasAddPortletURLs);
%>

<%!
private String _getURL(long groupId, long plid, PortletURL addPortletURL, String message, boolean addDisplayPageParameter, Layout layout, PageContext pageContext, PortletResponse portletResponse, boolean useDialog, AssetHelper assetHelper) {
	String addPortletURLString = assetHelper.getAddURLPopUp(groupId, plid, addPortletURL, addDisplayPageParameter, layout);

	if (useDialog) {
		return "javascript:Liferay.Util.openWindow({dialog: {destroyOnHide: true}, dialogIframe: {bodyCssClass: 'dialog-with-footer'}, id: '" + portletResponse.getNamespace() + "editAsset', title: '" + HtmlUtil.escapeJS(LanguageUtil.format((HttpServletRequest) pageContext.getRequest(), "new-x", HtmlUtil.escape(message), false)) + "', uri: '" + HtmlUtil.escapeJS(addPortletURLString) + "'});";
	}

	return addPortletURLString;
}
%>