<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<style>
	.facet-checkbox-label {
		display: block;
	}
</style>

<%
CPSpecificationOptionFacetsDisplayContext cpSpecificationOptionFacetsDisplayContext = (CPSpecificationOptionFacetsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<c:choose>
	<c:when test="<%= !cpSpecificationOptionFacetsDisplayContext.hasCommerceChannel() %>">
		<div class="alert alert-info mx-auto">
			<liferay-ui:message key="this-site-does-not-have-a-channel" />
		</div>
	</c:when>
	<c:otherwise>

		<%
		List<Facet> facets = cpSpecificationOptionFacetsDisplayContext.getFacets();
		%>

		<c:choose>
			<c:when test="<%= !facets.isEmpty() %>">

				<%
				for (Facet facet : cpSpecificationOptionFacetsDisplayContext.getFacets()) {
					FacetCollector facetCollector = facet.getFacetCollector();

					List<TermCollector> termCollectors = facetCollector.getTermCollectors();
				%>

					<c:if test="<%= !termCollectors.isEmpty() %>">

					<liferay-ui:panel-container
						extended="<%= true %>"
						markupView="lexicon"
						persistState="<%= true %>"
					>
						<liferay-ui:panel
							collapsible="<%= true %>"
							cssClass="search-facet"
							markupView="lexicon"
							persistState="<%= true %>"
							title="<%= HtmlUtil.escape(cpSpecificationOptionFacetsDisplayContext.getCPSpecificationOptionTitle(facet.getFieldName())) %>"
						>
							<aui:form method="post" name='<%= "assetEntriesFacetForm_" + facet.getFieldName() %>'>
								<aui:input cssClass="facet-parameter-name" name="facet-parameter-name" type="hidden" value="<%= cpSpecificationOptionFacetsDisplayContext.getCPSpecificationOptionKey(facet.getFieldName()) %>" />

								<aui:fieldset>
								<ul class="asset-type list-unstyled">

									<%
									int i = 0;

									for (TermCollector termCollector : termCollectors) {
										i++;
									%>

										<li class="facet-value">
											<label class="facet-checkbox-label" for="<portlet:namespace />term_<%= facet.getFieldName() + i %>">
												<input
													class="facet-term"
													data-term-id="<%= HtmlUtil.escape(termCollector.getTerm()) %>"
													id="<portlet:namespace />term_<%= facet.getFieldName() + i %>"
													name="<portlet:namespace />term_<%= facet.getFieldName() + i %>"
													onChange="Liferay.Search.FacetUtil.changeSelection(event);"
													type="checkbox"
													<%= cpSpecificationOptionFacetsDisplayContext.isCPDefinitionSpecificationOptionValueSelected(facet.getFieldName(), termCollector.getTerm()) ? "checked" : "" %>
												/>

												<span class="term-name">
													<%= HtmlUtil.escape(termCollector.getTerm()) %>
												</span>

												<small class="term-count">
													(<%= termCollector.getFrequency() %>)
												</small>
											</label>
										</li>

									<%
									}
									%>

								</aui:fieldset>
							</aui:form>
						</liferay-ui:panel>
					</liferay-ui:panel-container>

					</c:if>

				<%
				}
				%>

			</c:when>
			<c:otherwise>
				<div class="alert alert-info">
					<liferay-ui:message key="no-facets-were-found" />
				</div>
			</c:otherwise>
		</c:choose>

		<aui:script use="liferay-search-facet-util"></aui:script>
	</c:otherwise>
</c:choose>