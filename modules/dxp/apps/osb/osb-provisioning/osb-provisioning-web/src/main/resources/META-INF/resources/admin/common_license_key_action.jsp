<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CommonLicenseKey commonLicenseKey = (CommonLicenseKey)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<liferay-portlet:resourceURL id="/admin/download_common_license_key" var="downloadURL">
		<portlet:param name="commonLicenseKeyId" value="<%= String.valueOf(commonLicenseKey.getCommonLicenseKeyId()) %>" />
	</liferay-portlet:resourceURL>

	<liferay-ui:icon
		message="download"
		url="<%= downloadURL %>"
	/>

	<portlet:actionURL name="/admin/delete_common_license_key" var="deleteURL">
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="commonLicenseKeyId" value="<%= String.valueOf(commonLicenseKey.getCommonLicenseKeyId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete
		confirmation="are-you-sure-you-want-to-delete-this-common-license-key"
		message="delete"
		url="<%= deleteURL %>"
	/>
</liferay-ui:icon-menu>