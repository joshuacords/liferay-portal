<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-ui:error-header />

<liferay-ui:error exception="<%= NoSuchBOMDefinitionException.class %>" message="the-definition-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchBOMFolderApplicationRelException.class %>" message="the-model-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchBOMFolderException.class %>" message="the-folder-could-not-be-found" />

<liferay-ui:error-principal />