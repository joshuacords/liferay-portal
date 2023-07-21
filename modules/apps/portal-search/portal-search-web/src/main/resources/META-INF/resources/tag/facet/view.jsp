<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.context.AssetTagsSearchFacetDisplayContext" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.context.AssetTagsSearchFacetTermDisplayContext" %>

<portlet:defineObjects />

<%
AssetTagsSearchFacetDisplayContext assetTagsSearchFacetDisplayContext = (AssetTagsSearchFacetDisplayContext)java.util.Objects.requireNonNull(request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT));
%>

<c:choose>
	<c:when test="<%= assetTagsSearchFacetDisplayContext.isRenderNothing() %>">
		<aui:input autocomplete="off" name="<%= HtmlUtil.escapeAttribute(assetTagsSearchFacetDisplayContext.getParameterName()) %>" type="hidden" value="<%= assetTagsSearchFacetDisplayContext.getParameterValue() %>" />
	</c:when>
	<c:otherwise>
		<aui:form action="#" method="post" name="assetTagsFacetForm">
			<aui:input autocomplete="off" name="<%= HtmlUtil.escapeAttribute(assetTagsSearchFacetDisplayContext.getParameterName()) %>" type="hidden" value="<%= assetTagsSearchFacetDisplayContext.getParameterValue() %>" />
			<aui:input cssClass="facet-parameter-name" name="facet-parameter-name" type="hidden" value="<%= assetTagsSearchFacetDisplayContext.getParameterName() %>" />
			<aui:input cssClass="start-parameter-name" name="start-parameter-name" type="hidden" value="<%= assetTagsSearchFacetDisplayContext.getPaginationStartParameterName() %>" />

			<liferay-ui:panel-container
				extended="<%= true %>"
				id='<%= renderResponse.getNamespace() + "facetAssetTagsPanelContainer" %>'
				markupView="lexicon"
				persistState="<%= true %>"
			>
				<liferay-ui:panel
					collapsible="<%= true %>"
					cssClass="search-facet"
					id='<%= renderResponse.getNamespace() + "facetAssetTagsPanel" %>'
					markupView="lexicon"
					persistState="<%= true %>"
					title="tag"
				>
					<aui:fieldset>
						<ul class="<%= assetTagsSearchFacetDisplayContext.isCloudWithCount() ? "tag-cloud" : "tag-list" %> list-unstyled">

							<%
							int i = 0;

							for (AssetTagsSearchFacetTermDisplayContext assetTagsSearchFacetTermDisplayContext : assetTagsSearchFacetDisplayContext.getTermDisplayContexts()) {
								i++;
							%>

								<li class="facet-value tag-popularity-<%= assetTagsSearchFacetTermDisplayContext.getPopularity() %>">
									<div class="custom-checkbox custom-control">
										<label class="facet-checkbox-label" for="<portlet:namespace />term_<%= i %>">
											<input
												autocomplete="off"
												<%= assetTagsSearchFacetTermDisplayContext.isSelected() ? "checked" : StringPool.BLANK %>
												class="custom-control-input facet-term"
												data-term-id="<%= HtmlUtil.escapeAttribute(assetTagsSearchFacetTermDisplayContext.getValue()) %>"
												disabled
												id="<portlet:namespace />term_<%= i %>"
												name="<portlet:namespace />term_<%= i %>"
												onChange="Liferay.Search.FacetUtil.changeSelection(event);"
												type="checkbox"
											/>

											<span class="custom-control-label term-name <%= assetTagsSearchFacetTermDisplayContext.isSelected() ? "facet-term-selected" : "facet-term-unselected" %>">
												<span class="custom-control-label-text"><%= HtmlUtil.escape(assetTagsSearchFacetTermDisplayContext.getDisplayName()) %></span>
											</span>

											<c:if test="<%= assetTagsSearchFacetTermDisplayContext.isFrequencyVisible() %>">
												<small class="term-count">
													(<%= assetTagsSearchFacetTermDisplayContext.getFrequency() %>)
												</small>
											</c:if>
										</label>
									</div>
								</li>

							<%
							}
							%>

						</ul>
					</aui:fieldset>

					<c:if test="<%= !assetTagsSearchFacetDisplayContext.isNothingSelected() %>">
						<aui:button cssClass="btn-link btn-unstyled facet-clear-btn" onClick="Liferay.Search.FacetUtil.clearSelections(event);" value="clear" />
					</c:if>
				</liferay-ui:panel>
			</liferay-ui:panel-container>
		</aui:form>
	</c:otherwise>
</c:choose>

<aui:script use="liferay-search-facet-util">
	Liferay.Search.FacetUtil.enableInputs(
		document.querySelectorAll(
			'#<portlet:namespace />assetTagsFacetForm .facet-term'
		)
	);
</aui:script>