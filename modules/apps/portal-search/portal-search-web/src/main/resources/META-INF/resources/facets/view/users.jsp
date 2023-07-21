<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/facets/init.jsp" %>

<%
UserSearchFacetDisplayBuilder userSearchFacetDisplayBuilder = new UserSearchFacetDisplayBuilder();

userSearchFacetDisplayBuilder.setFacet(facet);
userSearchFacetDisplayBuilder.setFrequenciesVisible(dataJSONObject.getBoolean("showAssetCount", true));
userSearchFacetDisplayBuilder.setFrequencyThreshold(dataJSONObject.getInt("frequencyThreshold"));
userSearchFacetDisplayBuilder.setMaxTerms(dataJSONObject.getInt("maxTerms", 10));
userSearchFacetDisplayBuilder.setParamName(facet.getFieldId());
userSearchFacetDisplayBuilder.setParamValue(fieldParam);

UserSearchFacetDisplayContext userSearchFacetDisplayContext = userSearchFacetDisplayBuilder.build();
%>

<c:choose>
	<c:when test="<%= userSearchFacetDisplayContext.isRenderNothing() %>">
		<aui:input autocomplete="off" name="<%= HtmlUtil.escapeAttribute(userSearchFacetDisplayContext.getParamName()) %>" type="hidden" value="<%= userSearchFacetDisplayContext.getParamValue() %>" />
	</c:when>
	<c:otherwise>
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">
					<liferay-ui:message key="users" />
				</div>
			</div>

			<div class="panel-body">
				<div class="<%= cssClass %>" data-facetFieldName="<%= HtmlUtil.escapeAttribute(userSearchFacetDisplayContext.getParamName()) %>" id="<%= randomNamespace %>facet">
					<aui:input autocomplete="off" name="<%= HtmlUtil.escapeAttribute(userSearchFacetDisplayContext.getParamName()) %>" type="hidden" value="<%= userSearchFacetDisplayContext.getParamValue() %>" />

					<ul class="list-unstyled users">
						<li class="default facet-value">
							<a class="<%= userSearchFacetDisplayContext.isNothingSelected() ? "facet-term-selected" : "facet-term-unselected" %>" data-value="" href="javascript:;"><liferay-ui:message key="<%= HtmlUtil.escape(facetConfiguration.getLabel()) %>" /></a>
						</li>

						<%
						java.util.List<UserSearchFacetTermDisplayContext> userSearchFacetTermDisplayContexts = userSearchFacetDisplayContext.getTermDisplayContexts();

						for (UserSearchFacetTermDisplayContext userSearchFacetTermDisplayContext : userSearchFacetTermDisplayContexts) {
						%>

							<li class="facet-value">
								<a class="<%= userSearchFacetTermDisplayContext.isSelected() ? "facet-term-selected" : "facet-term-unselected" %>" data-value="<%= HtmlUtil.escapeAttribute(userSearchFacetTermDisplayContext.getUserName()) %>" href="javascript:;">
									<%= HtmlUtil.escape(userSearchFacetTermDisplayContext.getUserName()) %>

									<c:if test="<%= userSearchFacetTermDisplayContext.isFrequencyVisible() %>">
										<span class="frequency">(<%= userSearchFacetTermDisplayContext.getFrequency() %>)</span>
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