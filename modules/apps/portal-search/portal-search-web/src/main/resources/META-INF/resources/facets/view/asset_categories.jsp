<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/facets/init.jsp" %>

<%
AssetCategoriesSearchFacetDisplayBuilder assetCategoriesSearchFacetDisplayBuilder = new AssetCategoriesSearchFacetDisplayBuilder();

assetCategoriesSearchFacetDisplayBuilder.setAssetCategoryLocalService(AssetCategoryLocalServiceUtil.getService());
assetCategoriesSearchFacetDisplayBuilder.setAssetCategoryPermissionChecker(new AssetCategoryPermissionCheckerImpl(themeDisplay.getPermissionChecker()));
assetCategoriesSearchFacetDisplayBuilder.setDisplayStyle(dataJSONObject.getString("displayStyle", "cloud"));
assetCategoriesSearchFacetDisplayBuilder.setFacet(facet);
assetCategoriesSearchFacetDisplayBuilder.setFrequenciesVisible(dataJSONObject.getBoolean("showAssetCount", true));
assetCategoriesSearchFacetDisplayBuilder.setFrequencyThreshold(dataJSONObject.getInt("frequencyThreshold"));
assetCategoriesSearchFacetDisplayBuilder.setLocale(locale);
assetCategoriesSearchFacetDisplayBuilder.setMaxTerms(dataJSONObject.getInt("maxTerms", 10));
assetCategoriesSearchFacetDisplayBuilder.setParameterName(facet.getFieldId());
assetCategoriesSearchFacetDisplayBuilder.setParameterValue(fieldParam);

AssetCategoriesSearchFacetDisplayContext assetCategoriesSearchFacetDisplayContext = assetCategoriesSearchFacetDisplayBuilder.build();
%>

<c:choose>
	<c:when test="<%= assetCategoriesSearchFacetDisplayContext.isRenderNothing() %>">
		<aui:input autocomplete="off" name="<%= HtmlUtil.escapeAttribute(assetCategoriesSearchFacetDisplayContext.getParameterName()) %>" type="hidden" value="<%= assetCategoriesSearchFacetDisplayContext.getParameterValue() %>" />
	</c:when>
	<c:otherwise>
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">
					<liferay-ui:message key="categories" />
				</div>
			</div>

			<div class="panel-body">
				<div class="asset-tags <%= cssClass %>" data-facetFieldName="<%= HtmlUtil.escapeAttribute(assetCategoriesSearchFacetDisplayContext.getParameterName()) %>" id="<%= randomNamespace %>facet">
					<aui:input autocomplete="off" name="<%= HtmlUtil.escapeAttribute(assetCategoriesSearchFacetDisplayContext.getParameterName()) %>" type="hidden" value="<%= assetCategoriesSearchFacetDisplayContext.getParameterValue() %>" />

					<ul class="<%= assetCategoriesSearchFacetDisplayContext.isCloud() ? "tag-cloud" : "tag-list" %> list-unstyled">
						<li class="default facet-value">
							<a class="<%= assetCategoriesSearchFacetDisplayContext.isNothingSelected() ? "facet-term-selected" : "facet-term-unselected" %>" data-value="" href="javascript:;"><liferay-ui:message key="<%= HtmlUtil.escape(facetConfiguration.getLabel()) %>" /></a>
						</li>

						<%
						for (AssetCategoriesSearchFacetTermDisplayContext assetCategoriesSearchFacetTermDisplayContext : assetCategoriesSearchFacetDisplayContext.getTermDisplayContexts()) {
						%>

							<li class="facet-value tag-popularity-<%= assetCategoriesSearchFacetTermDisplayContext.getPopularity() %>">
								<a class="<%= assetCategoriesSearchFacetTermDisplayContext.isSelected() ? "facet-term-selected" : "facet-term-unselected" %>" data-value="<%= HtmlUtil.escapeAttribute(String.valueOf(assetCategoriesSearchFacetTermDisplayContext.getAssetCategoryId())) %>" href="javascript:;">
									<%= HtmlUtil.escape(assetCategoriesSearchFacetTermDisplayContext.getDisplayName()) %>

									<c:if test="<%= assetCategoriesSearchFacetTermDisplayContext.isFrequencyVisible() %>">
										<span class="frequency">(<%= assetCategoriesSearchFacetTermDisplayContext.getFrequency() %>)</span>
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