<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/data_layout_builder/init.jsp" %>

<liferay-editor:resources
	editorName="alloyeditor"
/>

<liferay-util:html-top>
	<link href="<%= PortalUtil.getStaticResourceURL(request, PortalUtil.getPathModule() + "/dynamic-data-mapping-form-renderer/css/main.css") %>" rel="stylesheet" />
</liferay-util:html-top>

<liferay-util:dynamic-include key="com.liferay.data.engine.taglib#/data_layout_builder/start.jsp#pre" />

<c:if test="<%= localizable %>">
	<div class="container-fluid-1280 ddm-translation-manager">
		<liferay-frontend:translation-manager
			availableLocales="<%= availableLocales %>"
			changeableDefaultLanguage="<%= false %>"
			defaultLanguageId="<%= themeDisplay.getLanguageId() %>"
			id="translationManager"
		/>
	</div>
</c:if>

<liferay-util:dynamic-include key="com.liferay.data.engine.taglib#/data_layout_builder/start.jsp#post" />