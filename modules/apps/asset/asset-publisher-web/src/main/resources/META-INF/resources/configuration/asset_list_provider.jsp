<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
List<InfoListProvider> infoListProviders = assetPublisherDisplayContext.getAssetEntryInfoListProviders();
%>

<c:choose>
	<c:when test="<%= ListUtil.isNotEmpty(infoListProviders) %>">
		<aui:select label="" name="preferences--infoListProviderClassName--">
			<aui:option label="none" value="" />

			<%
			String infoListProviderClassName = PrefsParamUtil.getString(portletPreferences, request, "infoListProviderClassName", StringPool.BLANK);

			for (InfoListProvider infoListProvider : infoListProviders) {
				Class<?> clazz = infoListProvider.getClass();
			%>

				<aui:option label="<%= infoListProvider.getLabel(themeDisplay.getLocale()) %>" selected="<%= infoListProviderClassName.equals(clazz.getName()) %>" value="<%= clazz.getName() %>" />

			<%
			}
			%>

		</aui:select>
	</c:when>
	<c:otherwise>
		<liferay-ui:message key="you-do-not-have-any-content-set-providers" />
	</c:otherwise>
</c:choose>