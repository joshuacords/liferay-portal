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
page import="com.liferay.portal.search.web.internal.facet.display.context.AssetEntriesSearchFacetDisplayContext" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.context.AssetEntriesSearchFacetTermDisplayContext" %>

<portlet:defineObjects />

<%
AssetEntriesSearchFacetDisplayContext assetEntriesSearchFacetDisplayContext = (AssetEntriesSearchFacetDisplayContext)java.util.Objects.requireNonNull(request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT));
%>

<c:choose>
	<c:when test="<%= assetEntriesSearchFacetDisplayContext.isRenderNothing() %>">
		<aui:input autocomplete="off" name="<%= HtmlUtil.escapeAttribute(assetEntriesSearchFacetDisplayContext.getParameterName()) %>" type="hidden" value="<%= assetEntriesSearchFacetDisplayContext.getParameterValue() %>" />
	</c:when>
	<c:otherwise>
		<aui:form action="#" method="post" name="assetEntriesFacetForm">
			<aui:input autocomplete="off" name="<%= HtmlUtil.escapeAttribute(assetEntriesSearchFacetDisplayContext.getParameterName()) %>" type="hidden" value="<%= assetEntriesSearchFacetDisplayContext.getParameterValue() %>" />
			<aui:input cssClass="facet-parameter-name" name="facet-parameter-name" type="hidden" value="<%= assetEntriesSearchFacetDisplayContext.getParameterName() %>" />
			<aui:input cssClass="start-parameter-name" name="start-parameter-name" type="hidden" value="<%= assetEntriesSearchFacetDisplayContext.getPaginationStartParameterName() %>" />

			<liferay-ui:panel-container
				extended="<%= true %>"
				id='<%= renderResponse.getNamespace() + "facetAssetEntriesPanelContainer" %>'
				markupView="lexicon"
				persistState="<%= true %>"
			>
				<liferay-ui:panel
					collapsible="<%= true %>"
					cssClass="search-facet"
					id='<%= renderResponse.getNamespace() + "facetAssetEntriesPanel" %>'
					markupView="lexicon"
					persistState="<%= true %>"
					title="type"
				>
					<aui:fieldset>
						<ul class="asset-type list-unstyled">

							<%
							int i = 0;

							for (AssetEntriesSearchFacetTermDisplayContext assetEntriesSearchFacetTermDisplayContext : assetEntriesSearchFacetDisplayContext.getTermDisplayContexts()) {
								i++;
							%>

								<li class="facet-value">
									<div class="custom-checkbox custom-control">
										<label class="facet-checkbox-label" for="<portlet:namespace />term_<%= i %>">
											<input
												autocomplete="off"
												class="custom-control-input facet-term"
												data-term-id="<%= assetEntriesSearchFacetTermDisplayContext.getAssetType() %>"
												disabled
												id="<portlet:namespace />term_<%= i %>"
												name="<portlet:namespace />term_<%= i %>"
												onChange="Liferay.Search.FacetUtil.changeSelection(event);"
												type="checkbox"
												<%= assetEntriesSearchFacetTermDisplayContext.isSelected() ? "checked" : StringPool.BLANK %>
											/>

											<span class="custom-control-label term-name <%= assetEntriesSearchFacetTermDisplayContext.isSelected() ? "facet-term-selected" : "facet-term-unselected" %>">
												<span class="custom-control-label-text"><%= HtmlUtil.escape(assetEntriesSearchFacetTermDisplayContext.getTypeName()) %></span>
											</span>

											<c:if test="<%= assetEntriesSearchFacetTermDisplayContext.isFrequencyVisible() %>">
												<small class="term-count">
													(<%= assetEntriesSearchFacetTermDisplayContext.getFrequency() %>)
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

					<c:if test="<%= !assetEntriesSearchFacetDisplayContext.isNothingSelected() %>">
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
			'#<portlet:namespace />assetEntriesFacetForm .facet-term'
		)
	);
</aui:script>