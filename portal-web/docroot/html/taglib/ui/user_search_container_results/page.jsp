<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%
UserSearchTerms searchTerms = (UserSearchTerms)request.getAttribute("liferay-ui:user-search-container-results:searchTerms");
LinkedHashMap<String, Object> userParams = (LinkedHashMap<String, Object>)request.getAttribute("liferay-ui:user-search-container-results:userParams");
SearchContainer userSearchContainer = (SearchContainer)request.getAttribute("liferay-ui:user-search-container-results:searchContainer");
%>

<liferay-ui:search-container
	id="<%= userSearchContainer.getId(request, namespace) %>"
	searchContainer="<%= userSearchContainer %>"
>
	<liferay-ui:search-container-results>

		<%
		if (searchTerms.isAdvancedSearch()) {
			total = UserLocalServiceUtil.searchCount(company.getCompanyId(), searchTerms.getFirstName(), searchTerms.getMiddleName(), searchTerms.getLastName(), searchTerms.getScreenName(), searchTerms.getEmailAddress(), searchTerms.getStatus(), userParams, searchTerms.isAndOperator());

			results = UserLocalServiceUtil.search(company.getCompanyId(), searchTerms.getFirstName(), searchTerms.getMiddleName(), searchTerms.getLastName(), searchTerms.getScreenName(), searchTerms.getEmailAddress(), searchTerms.getStatus(), userParams, searchTerms.isAndOperator(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());
		}
		else {
			total = UserLocalServiceUtil.searchCount(company.getCompanyId(), searchTerms.getKeywords(), searchTerms.getStatus(), userParams);

			searchContainer.setTotal(total);

			results = UserLocalServiceUtil.search(company.getCompanyId(), searchTerms.getKeywords(), searchTerms.getStatus(), userParams, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());
		}

		searchContainer.setTotal(total);
		searchContainer.setResults(results);
		%>

	</liferay-ui:search-container-results>
</liferay-ui:search-container>