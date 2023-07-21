<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%
boolean forceDatabase = GetterUtil.getBoolean(request.getAttribute("liferay-ui:organization-search-container-results:forceDatabase"));
LinkedHashMap<String, Object> organizationParams = (LinkedHashMap<String, Object>)request.getAttribute("liferay-ui:organization-search-container-results:organizationParams");
long parentOrganizationId = GetterUtil.getLong(request.getAttribute("liferay-ui:organization-search-container-results:parentOrganizationId"));
SearchContainer organizationSearchContainer = (SearchContainer)request.getAttribute("liferay-ui:organization-search-container-results:searchContainer");
OrganizationSearchTerms searchTerms = (OrganizationSearchTerms)request.getAttribute("liferay-ui:organization-search-container-results:searchTerms");

Indexer<?> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Organization.class);
%>

<liferay-ui:search-container
	id="<%= organizationSearchContainer.getId(request, namespace) %>"
	searchContainer="<%= organizationSearchContainer %>"
>
	<liferay-ui:search-container-results>
		<c:choose>
			<c:when test="<%= !forceDatabase && indexer.isIndexerEnabled() && PropsValues.ORGANIZATIONS_SEARCH_WITH_INDEX %>">

				<%
				organizationParams.put("expandoAttributes", searchTerms.getKeywords());

				Sort sort = SortFactoryUtil.getSort(Organization.class, searchContainer.getOrderByCol(), searchContainer.getOrderByType());

				BaseModelSearchResult<Organization> baseModelSearchResult = null;

				if (searchTerms.isAdvancedSearch()) {
					baseModelSearchResult = OrganizationLocalServiceUtil.searchOrganizations(company.getCompanyId(), parentOrganizationId, searchTerms.getName(), searchTerms.getType(), searchTerms.getStreet(), searchTerms.getCity(), searchTerms.getZip(), searchTerms.getRegionName(), searchTerms.getCountryName(), organizationParams, searchTerms.isAndOperator(), searchContainer.getStart(), searchContainer.getEnd(), sort);
				}
				else {
					baseModelSearchResult = OrganizationLocalServiceUtil.searchOrganizations(company.getCompanyId(), parentOrganizationId, searchTerms.getKeywords(), organizationParams, searchContainer.getStart(), searchContainer.getEnd(), sort);
				}

				searchContainer.setResults(baseModelSearchResult.getBaseModels());
				searchContainer.setTotal(baseModelSearchResult.getLength());
				%>

			</c:when>
			<c:otherwise>

				<%
				if (searchTerms.isAdvancedSearch()) {
					total = OrganizationLocalServiceUtil.searchCount(company.getCompanyId(), parentOrganizationId, searchTerms.getName(), searchTerms.getType(), searchTerms.getStreet(), searchTerms.getCity(), searchTerms.getZip(), searchTerms.getRegionIdObj(), searchTerms.getCountryIdObj(), organizationParams, searchTerms.isAndOperator());

					results = OrganizationLocalServiceUtil.search(company.getCompanyId(), parentOrganizationId, searchTerms.getName(), searchTerms.getType(), searchTerms.getStreet(), searchTerms.getCity(), searchTerms.getZip(), searchTerms.getRegionIdObj(), searchTerms.getCountryIdObj(), organizationParams, searchTerms.isAndOperator(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());
				}
				else {
					total = OrganizationLocalServiceUtil.searchCount(company.getCompanyId(), parentOrganizationId, searchTerms.getKeywords(), searchTerms.getType(), searchTerms.getRegionIdObj(), searchTerms.getCountryIdObj(), organizationParams);

					results = OrganizationLocalServiceUtil.search(company.getCompanyId(), parentOrganizationId, searchTerms.getKeywords(), searchTerms.getType(), searchTerms.getRegionIdObj(), searchTerms.getCountryIdObj(), organizationParams, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());
				}

				searchContainer.setTotal(total);
				searchContainer.setResults(results);
				%>

			</c:otherwise>
		</c:choose>
	</liferay-ui:search-container-results>
</liferay-ui:search-container>