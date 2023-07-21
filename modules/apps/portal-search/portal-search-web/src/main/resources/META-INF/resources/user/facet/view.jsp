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
page import="com.liferay.portal.search.web.internal.facet.display.context.UserSearchFacetDisplayContext" %><%@
page import="com.liferay.portal.search.web.internal.facet.display.context.UserSearchFacetTermDisplayContext" %>

<portlet:defineObjects />

<%
UserSearchFacetDisplayContext userSearchFacetDisplayContext = (UserSearchFacetDisplayContext)java.util.Objects.requireNonNull(request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT));
%>

<c:choose>
	<c:when test="<%= userSearchFacetDisplayContext.isRenderNothing() %>">
		<aui:input autocomplete="off" name="<%= HtmlUtil.escapeAttribute(userSearchFacetDisplayContext.getParamName()) %>" type="hidden" value="<%= userSearchFacetDisplayContext.getParamValue() %>" />
	</c:when>
	<c:otherwise>
		<aui:form action="#" method="post" name="userFacetForm">
			<aui:input autocomplete="off" name="<%= HtmlUtil.escapeAttribute(userSearchFacetDisplayContext.getParamName()) %>" type="hidden" value="<%= userSearchFacetDisplayContext.getParamValue() %>" />
			<aui:input cssClass="facet-parameter-name" name="facet-parameter-name" type="hidden" value="<%= userSearchFacetDisplayContext.getParamName() %>" />
			<aui:input cssClass="start-parameter-name" name="start-parameter-name" type="hidden" value="<%= userSearchFacetDisplayContext.getPaginationStartParameterName() %>" />

			<liferay-ui:panel-container
				extended="<%= true %>"
				id='<%= renderResponse.getNamespace() + "facetUserPanelContainer" %>'
				markupView="lexicon"
				persistState="<%= true %>"
			>
				<liferay-ui:panel
					collapsible="<%= true %>"
					cssClass="search-facet"
					id='<%= renderResponse.getNamespace() + "facetUserPanel" %>'
					markupView="lexicon"
					persistState="<%= true %>"
					title="user"
				>
					<aui:fieldset>
						<ul class="list-unstyled">

							<%
							int i = 0;

							for (UserSearchFacetTermDisplayContext userSearchFacetTermDisplayContext : userSearchFacetDisplayContext.getTermDisplayContexts()) {
								i++;
							%>

								<li class="facet-value">
									<div class="custom-checkbox custom-control">
										<label class="facet-checkbox-label" for="<portlet:namespace />term_<%= i %>">
											<input
												autocomplete="off"
												<%= userSearchFacetTermDisplayContext.isSelected() ? "checked" : StringPool.BLANK %>
												class="custom-control-input facet-term"
												data-term-id="<%= HtmlUtil.escapeAttribute(userSearchFacetTermDisplayContext.getUserName()) %>"
												disabled
												id="<portlet:namespace />term_<%= i %>"
												name="<portlet:namespace />term_<%= i %>"
												onChange="Liferay.Search.FacetUtil.changeSelection(event);"
												type="checkbox"
											/>

											<span class="custom-control-label term-name <%= userSearchFacetTermDisplayContext.isSelected() ? "facet-term-selected" : "facet-term-unselected" %>">
												<span class="custom-control-label-text">
													<%= HtmlUtil.escape(userSearchFacetTermDisplayContext.getUserName()) %>
												</span>
											</span>

											<c:if test="<%= userSearchFacetTermDisplayContext.isFrequencyVisible() %>">
												<small class="term-count">
													(<%= userSearchFacetTermDisplayContext.getFrequency() %>)
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

					<c:if test="<%= !userSearchFacetDisplayContext.isNothingSelected() %>">
						<aui:button cssClass="btn-link btn-unstyled facet-clear-btn" onClick="Liferay.Search.FacetUtil.clearSelections(event);" value="clear" />
					</c:if>
				</liferay-ui:panel>
			</liferay-ui:panel-container>
		</aui:form>
	</c:otherwise>
</c:choose>

<aui:script use="liferay-search-facet-util">
	Liferay.Search.FacetUtil.enableInputs(
		document.querySelectorAll('#<portlet:namespace />userFacetForm .facet-term')
	);
</aui:script>