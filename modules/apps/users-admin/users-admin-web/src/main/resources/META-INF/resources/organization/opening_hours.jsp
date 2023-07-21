<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
OrganizationScreenNavigationDisplayContext organizationScreenNavigationDisplayContext = (OrganizationScreenNavigationDisplayContext)request.getAttribute(UsersAdminWebKeys.ORGANIZATION_SCREEN_NAVIGATION_DISPLAY_CONTEXT);

long organizationId = organizationScreenNavigationDisplayContext.getOrganizationId();

List<OrgLabor> orgLabors = OrgLaborServiceUtil.getOrgLabors(organizationId);
%>

<div class="sheet-header">
	<h2 class="autofit-row sheet-title">
		<span class="autofit-col autofit-col-expand">
			<span class="heading-text"><%= organizationScreenNavigationDisplayContext.getFormLabel() %></span>
		</span>
		<span class="autofit-col">
			<span class="heading-end">

				<%
				PortletURL editURL = liferayPortletResponse.createRenderURL();

				editURL.setParameter("mvcPath", "/organization/edit_opening_hours.jsp");
				editURL.setParameter("redirect", currentURL);
				editURL.setParameter("className", Organization.class.getName());
				editURL.setParameter("classPK", String.valueOf(organizationId));
				%>

				<liferay-ui:icon
					label="<%= true %>"
					linkCssClass="add-opening-hours-link btn btn-secondary btn-sm"
					message="add"
					url="<%= editURL.toString() %>"
				/>
			</span>
		</span>
	</h2>
</div>

<c:if test="<%= orgLabors.isEmpty() %>">
	<div class="contact-information-empty-results-message-wrapper">
		<liferay-ui:empty-result-message
			message="this-organization-does-not-have-any-opening-hours"
		/>
	</div>
</c:if>

<div
	class="<%=
		CSSClassNames.builder(
			"opening-hours-wrapper"
		).add(
			"hide", orgLabors.isEmpty()
		).build()
	%>"
>

	<%
	for (OrgLabor orgLabor : orgLabors) {
		OrgLaborDisplay orgLaborDisplay = new OrgLaborDisplay(locale, orgLabor);
	%>

		<div class="opening-hours-entry">
			<div class="autofit-row opening-hours-header">
				<span class="autofit-col">
					<h5><%= orgLaborDisplay.getTitle() %></h5>
				</span>
				<span class="autofit-col lfr-search-container-wrapper">
					<liferay-util:include page="/organization/opening_hours_action.jsp" servletContext="<%= application %>">
						<liferay-util:param name="orgLaborId" value="<%= String.valueOf(orgLabor.getOrgLaborId()) %>" />
					</liferay-util:include>
				</span>
			</div>

			<div class="table-responsive">
				<table class="table table-autofit">
					<tbody>

						<%
						for (KeyValuePair dayKeyValuePair : orgLaborDisplay.getDayKeyValuePairs()) {
						%>

							<tr>
								<td class="table-cell-expand">
									<span class="table-title"><%= dayKeyValuePair.getKey() %></span>
								</td>
								<td class="table-cell-expand">
									<span><%= dayKeyValuePair.getValue() %></span>
								</td>
							</tr>

						<%
						}
						%>

					</tbody>
				</table>
			</div>
		</div>

	<%
	}
	%>

</div>