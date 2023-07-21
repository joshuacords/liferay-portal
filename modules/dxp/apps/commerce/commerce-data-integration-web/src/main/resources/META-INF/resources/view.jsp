<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceDataIntegrationProcessDisplayContext commerceDataIntegrationProcessDisplayContext = (CommerceDataIntegrationProcessDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

PortletURL portletURL = commerceDataIntegrationProcessDisplayContext.getPortletURL();

portletURL.setParameter("searchContainerId", "commerceDataIntegrationProcesses");

request.setAttribute("view.jsp-portletURL", portletURL);
%>

<liferay-util:include page="/process_toolbar.jsp" servletContext="<%= application %>">
	<liferay-util:param name="searchContainerId" value="commerceDataIntegrationProcesses" />
</liferay-util:include>

<div id="<portlet:namespace />processesContainer">
	<div class="closed container-fluid-1280" id="<portlet:namespace />infoPanelId">
		<div class="container">
			<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
				<aui:input name="<%= Constants.CMD %>" type="hidden" />
				<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
				<aui:input name="deleteCDataIntegrationProcessIds" type="hidden" />

				<div class="process-lists-container" id="<portlet:namespace />entriesContainer">
					<liferay-ui:search-container
						id="commerceDataIntegrationProcesses"
						searchContainer="<%= commerceDataIntegrationProcessDisplayContext.getSearchContainer() %>"
					>
						<liferay-ui:search-container-row
							className="com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess"
							cssClass="entry-display-style"
							keyProperty="CDataIntegrationProcessId"
							modelVar="commerceDataIntegrationProcess"
						>

							<%
							PortletURL rowURL = renderResponse.createRenderURL();

							rowURL.setParameter("mvcRenderCommandName", "editCommerceDataIntegrationProcess");
							rowURL.setParameter("redirect", currentURL);
							rowURL.setParameter("commerceDataIntegrationProcessId", String.valueOf(commerceDataIntegrationProcess.getCommerceDataIntegrationProcessId()));
							%>

							<liferay-ui:search-container-column-text
								cssClass="important table-cell-content"
								href="<%= rowURL %>"
								property="name"
							/>

							<liferay-ui:search-container-column-text
								cssClass="table-cell-content"
								property="type"
							/>

							<liferay-ui:search-container-column-text
								cssClass="table-cell-content"
								name="system"
								value='<%= commerceDataIntegrationProcess.isSystem() ? LanguageUtil.get(request, "yes") : LanguageUtil.get(request, "no") %>'
							/>

							<liferay-ui:search-container-column-text
								cssClass="table-cell-content"
								name="next-fire-date"
								value="<%= commerceDataIntegrationProcessDisplayContext.getNextFireDate(commerceDataIntegrationProcess.getCommerceDataIntegrationProcessId()) %>"
							/>

							<liferay-ui:search-container-column-jsp
								cssClass="table-cell-content"
								path="/process/buttons.jsp"
							/>

							<liferay-ui:search-container-column-jsp
								cssClass="entry-action-column"
								path="/process_action.jsp"
							/>
						</liferay-ui:search-container-row>

						<liferay-ui:search-iterator
							displayStyle="list"
							markupView="lexicon"
						/>
					</liferay-ui:search-container>
				</div>
			</aui:form>
		</div>
	</div>
</div>