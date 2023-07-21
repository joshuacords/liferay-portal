<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<div class="container-fluid-1280">
	<div class="card main-content-card">
		<div class="card-row card-row-padded">
			<liferay-ui:search-container
				emptyResultsMessage="there-are-no-gadgets"
				headerNames="name"
				iteratorURL="<%= renderResponse.createRenderURL() %>"
				total="<%= GadgetLocalServiceUtil.getGadgetsCount(company.getCompanyId()) %>"
			>
				<liferay-ui:search-container-results
					results="<%= GadgetLocalServiceUtil.getGadgets(company.getCompanyId(), searchContainer.getStart(), searchContainer.getEnd()) %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.opensocial.model.Gadget"
					keyProperty="gadgetId"
					modelVar="gadget"
				>
					<liferay-ui:search-container-column-text
						name="gadget"
						property="name"
					/>

					<%
					String gadgetURL = gadget.getUrl();
					%>

					<liferay-ui:search-container-column-text
						href="<%= gadgetURL %>"
						name="url"
						value="<%= gadgetURL %>"
					/>

					<liferay-ui:search-container-column-jsp
						align="right"
						cssClass="entry-action"
						path="/admin/gadget_action.jsp"
						valign="top"
					/>
				</liferay-ui:search-container-row>

				<aui:button-row>
					<c:if test="<%= GadgetPermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), ActionKeys.PUBLISH_GADGET) %>">
						<portlet:renderURL var="publishGadgetURL">
							<portlet:param name="mvcPath" value="/admin/edit_gadget.jsp" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
						</portlet:renderURL>

						<aui:button onClick="<%= publishGadgetURL %>" value="publish-gadget" />
					</c:if>

					<portlet:actionURL name="refreshGadgets" var="refreshGadgetsURL">
						<portlet:param name="redirect" value="<%= currentURL %>" />
					</portlet:actionURL>

					<aui:button onClick="<%= refreshGadgetsURL %>" value="refresh-gadgets" />
				</aui:button-row>

				<liferay-ui:search-iterator />
			</liferay-ui:search-container>
		</div>
	</div>
</div>