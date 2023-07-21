<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<c:if test="<%= !TensorFlowDownloadUtil.isDownloaded() %>">

	<%
	TensorFlowImageAssetAutoTagProviderCompanyConfiguration tensorFlowImageAssetAutoTagProviderCompanyConfiguration = (TensorFlowImageAssetAutoTagProviderCompanyConfiguration)request.getAttribute(TensorFlowImageAssetAutoTagProviderCompanyConfiguration.class.getName());

	boolean tensorFlowImageAssetAutoTagProviderEnabled = (tensorFlowImageAssetAutoTagProviderCompanyConfiguration != null) && tensorFlowImageAssetAutoTagProviderCompanyConfiguration.enabled();

	boolean downloadFailed = tensorFlowImageAssetAutoTagProviderEnabled && TensorFlowDownloadUtil.isDownloadFailed();
	%>

	<aui:alert closeable="<%= false %>" type='<%= downloadFailed ? "danger" : "info" %>'>
		<c:choose>
			<c:when test="<%= downloadFailed %>">
				<liferay-ui:message key="the-tensorflow-model-could-not-be-downloaded.-please-contact-your-administrator" />
			</c:when>
			<c:when test="<%= tensorFlowImageAssetAutoTagProviderEnabled %>">
				<liferay-ui:message key="the-tensorflow-model-is-being-downloaded-in-the-background.-no-tags-will-be-created-until-the-model-is-fully-downloaded" />
			</c:when>
			<c:otherwise>
				<liferay-ui:message key="the-tensorflow-model-will-be-downloaded-in-the-background.-no-tags-will-be-created-until-the-model-is-fully-downloaded" />
			</c:otherwise>
		</c:choose>
	</aui:alert>
</c:if>