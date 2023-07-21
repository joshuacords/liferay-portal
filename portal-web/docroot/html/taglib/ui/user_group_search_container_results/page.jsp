<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%
UserGroupDisplayTerms searchTerms = (UserGroupDisplayTerms)request.getAttribute("liferay-ui:user-group-search-container-results:searchTerms");
LinkedHashMap<String, Object> userGroupParams = (LinkedHashMap<String, Object>)request.getAttribute("liferay-ui:user-group-search-container-results:userGroupParams");
SearchContainer userGroupSearchContainer = (SearchContainer)request.getAttribute("liferay-ui:user-group-search-container-results:searchContainer");
%>

<liferay-ui:search-container
	id="<%= userGroupSearchContainer.getId(request, namespace) %>"
	searchContainer="<%= userGroupSearchContainer %>"
>
	<liferay-ui:search-container-results>

		<%
		String keywords = searchTerms.getKeywords();

		if (Validator.isNotNull(keywords)) {
			userGroupParams.put("expandoAttributes", keywords);
		}

		total = UserGroupServiceUtil.searchCount(company.getCompanyId(), keywords, userGroupParams);

		searchContainer.setTotal(total);

		results = UserGroupServiceUtil.search(company.getCompanyId(), keywords, userGroupParams, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());

		searchContainer.setResults(results);
		%>

	</liferay-ui:search-container-results>
</liferay-ui:search-container>