<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<div id="<portlet:namespace />root">
	<span aria-hidden="true" class="loading-animation"></span>
</div>

<aui:script require='<%= npmResolvedPackageName + " as bootstrapRequire" %>'>
	bootstrapRequire.default(
		<%= PropsValues.SEARCH_CONTAINER_PAGE_DEFAULT_DELTA %>,
		<%= Arrays.toString(PropsValues.SEARCH_CONTAINER_PAGE_DELTA_VALUES) %>,
		<%= DateUtil.isFormatAmPm(locale) %>,
		<%= PropsValues.SEARCH_CONTAINER_PAGE_ITERATOR_MAX_PAGES %>,
		'<portlet:namespace />'
	);
</aui:script>