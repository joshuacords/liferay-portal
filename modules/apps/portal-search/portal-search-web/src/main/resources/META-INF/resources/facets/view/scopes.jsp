<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/facets/init.jsp" %>

<%
long searchScopeGroupId = searchDisplayContext.getSearchScopeGroupId();

if (Validator.isNull(fieldParam)) {
	fieldParam = String.valueOf(searchScopeGroupId);
}

ScopeSearchFacetDisplayBuilder scopeSearchFacetDisplayBuilder = new ScopeSearchFacetDisplayBuilder();

scopeSearchFacetDisplayBuilder.setFacet(facet);

if (searchScopeGroupId != 0) {
	scopeSearchFacetDisplayBuilder.setFilteredGroupIds(new long[] {searchScopeGroupId});
}

scopeSearchFacetDisplayBuilder.setFrequenciesVisible(dataJSONObject.getBoolean("showAssetCount", true));
scopeSearchFacetDisplayBuilder.setFrequencyThreshold(dataJSONObject.getInt("frequencyThreshold"));
scopeSearchFacetDisplayBuilder.setGroupLocalService(GroupLocalServiceUtil.getService());
scopeSearchFacetDisplayBuilder.setLanguage(LanguageUtil.getLanguage());
scopeSearchFacetDisplayBuilder.setLocale(locale);
scopeSearchFacetDisplayBuilder.setMaxTerms(dataJSONObject.getInt("maxTerms"));
scopeSearchFacetDisplayBuilder.setParameterName(facet.getFieldId());
scopeSearchFacetDisplayBuilder.setParameterValue(fieldParam);

ScopeSearchFacetDisplayContext scopeSearchFacetDisplayContext = scopeSearchFacetDisplayBuilder.build();
%>

<c:choose>
	<c:when test="<%= scopeSearchFacetDisplayContext.isRenderNothing() %>">
		<aui:input autocomplete="off" name="<%= HtmlUtil.escapeAttribute(scopeSearchFacetDisplayContext.getParameterName()) %>" type="hidden" value="<%= scopeSearchFacetDisplayContext.getParameterValue() %>" />
	</c:when>
	<c:otherwise>
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">
					<liferay-ui:message key="sites" />
				</div>
			</div>

			<div class="panel-body">
				<div class="<%= cssClass %>" data-facetFieldName="<%= HtmlUtil.escapeAttribute(facet.getFieldId()) %>" id="<%= randomNamespace %>facet">
					<aui:input autocomplete="off" name="<%= HtmlUtil.escapeAttribute(scopeSearchFacetDisplayContext.getParameterName()) %>" type="hidden" value="<%= scopeSearchFacetDisplayContext.getParameterValue() %>" />

					<ul class="list-unstyled scopes">
						<li class="default facet-value">
							<a class="<%= scopeSearchFacetDisplayContext.isNothingSelected() ? "facet-term-selected" : "facet-term-unselected" %>" data-value="0" href="javascript:;"><liferay-ui:message key="<%= HtmlUtil.escape(facetConfiguration.getLabel()) %>" /></a>
						</li>

						<%
						List<ScopeSearchFacetTermDisplayContext> scopeSearchFacetTermDisplayContexts = scopeSearchFacetDisplayContext.getTermDisplayContexts();

						for (ScopeSearchFacetTermDisplayContext scopeSearchFacetTermDisplayContext : scopeSearchFacetTermDisplayContexts) {
						%>

							<li class="facet-value">
								<a class="<%= scopeSearchFacetTermDisplayContext.isSelected() ? "facet-term-selected" : "facet-term-unselected" %>" data-value="<%= scopeSearchFacetTermDisplayContext.getGroupId() %>" href="javascript:;">
									<%= HtmlUtil.escape(scopeSearchFacetTermDisplayContext.getDescriptiveName()) %>

									<c:if test="<%= scopeSearchFacetTermDisplayContext.isShowCount() %>">
										<span class="frequency">(<%= scopeSearchFacetTermDisplayContext.getCount() %>)</span>
									</c:if>
								</a>
							</li>

						<%
						}
						%>

					</ul>
				</div>
			</div>
		</div>
	</c:otherwise>
</c:choose>