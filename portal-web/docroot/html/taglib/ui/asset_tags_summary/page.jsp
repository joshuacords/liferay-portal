<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%
String[] assetTagNames = StringUtil.split((String)request.getAttribute("liferay-ui:asset-tags-summary:assetTagNames"));
String className = (String)request.getAttribute("liferay-ui:asset-tags-summary:className");
long classPK = GetterUtil.getLong((String)request.getAttribute("liferay-ui:asset-tags-summary:classPK"));
String message = GetterUtil.getString((String)request.getAttribute("liferay-ui:asset-tags-summary:message"));
String paramName = GetterUtil.getString((String)request.getAttribute("liferay-ui:asset-tags-summary:paramName"), "tag");
PortletURL portletURL = (PortletURL)request.getAttribute("liferay-ui:asset-tags-summary:portletURL");

if (assetTagNames.length == 0) {
	List<AssetTag> tags = (List<AssetTag>)request.getAttribute("liferay-ui:asset-tags-summary:assetTags");

	if (ListUtil.isEmpty(tags)) {
		tags = AssetTagServiceUtil.getTags(className, classPK);
	}

	assetTagNames = ListUtil.toArray(tags, AssetTag.NAME_ACCESSOR);
}
%>

<c:if test="<%= assetTagNames.length > 0 %>">
	<span class="taglib-asset-tags-summary">
		<%= Validator.isNotNull(message) ? (LanguageUtil.get(resourceBundle, message) + ": ") : "" %>

		<c:choose>
			<c:when test="<%= portletURL != null %>">

				<%
				for (int i = 0; i < assetTagNames.length; i++) {
					portletURL.setParameter(paramName, assetTagNames[i]);
				%>

					<a class="badge badge-default badge-sm" href="<%= HtmlUtil.escape(portletURL.toString()) %>"><%= assetTagNames[i] %></a>

				<%
				}
				%>

			</c:when>
			<c:otherwise>

				<%
				for (int i = 0; i < assetTagNames.length; i++) {
				%>

					<span class="badge badge-default badge-sm"><%= assetTagNames[i] %></span>

				<%
				}
				%>

			</c:otherwise>
		</c:choose>
	</span>
</c:if>