<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String carPartsFinderRootElementId = renderResponse.getNamespace() + "-car-parts-finder";

NPMResolver npmResolver = NPMResolverProvider.getNPMResolver();
%>

<div class="car-parts-finder-module" id="<%= carPartsFinderRootElementId %>">
	<div class="inline-item my-5 p-5 w-100">
		<span aria-hidden="true" class="loading-animation"></span>
	</div>
</div>

<aui:script require='<%= npmResolver.resolveModuleName("commerce-bom-web/js/index.es") + " as CarPartsFinder" %>'>
	CarPartsFinder.default('partFinder', '<%= carPartsFinderRootElementId %>', {
		basename: window.location.pathname,
		basePathUrl:
			'<%= PortalUtil.getGroupFriendlyURL(layout.getLayoutSet(), themeDisplay, locale) %>',
		areasEndpoint:
			'<%= PortalUtil.getPortalURL(request) + "/o/commerce-bom/1.0/areas" %>',
		foldersEndpoint:
			'<%= PortalUtil.getPortalURL(request) + "/o/commerce-bom/1.0/folders" %>',
		spritemap: '<%= themeDisplay.getPathThemeImages() + "/lexicon/icons.svg" %>'
	});
</aui:script>