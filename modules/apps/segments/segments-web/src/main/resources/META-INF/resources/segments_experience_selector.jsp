<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
SegmentsExperienceSelectorDisplayContext segmentsExperienceSelectorDisplayContext = new SegmentsExperienceSelectorDisplayContext(request);
%>

<li class="border-left border-secondary control-menu-nav-item ml-3 pl-3">
	<div class="dropdown">
		<button aria-expanded="false" aria-haspopup="true" class="btn btn-unstyled dropdown-toggle" id="<portlet:namespace />dropdownToggle" type="button">
			<span class="align-items-center c-inner d-flex" tabindex="-1">
				<span class="lfr-portal-tooltip mr-2 text-truncate" title="<%= HtmlUtil.escapeAttribute(segmentsExperienceSelectorDisplayContext.getSelectedSegmentsExperienceName()) %>">
					<%= segmentsExperienceSelectorDisplayContext.getSelectedSegmentsExperienceName() %>
				</span>

				<clay:icon
					elementClasses="flex-shrink-0"
					symbol="caret-double"
				/>
			</span>
		</button>

		<ul aria-labelledby="<portlet:namespace />dropdownToggle" class="dropdown-menu" id="<portlet:namespace />dropdown">

			<%
			JSONArray segmentsExperiencesJSONArray = segmentsExperienceSelectorDisplayContext.getSegmentsExperiencesJSONArray();

			for (int i = 0; i < segmentsExperiencesJSONArray.length(); i++) {
				JSONObject segmentsExperiencesJSONObject = segmentsExperiencesJSONArray.getJSONObject(i);
			%>

				<li>
					<a class="border-0 d-flex dropdown-item list-group-item list-group-item-flex rounded-0" href="<%= segmentsExperiencesJSONObject.getString("url") %>">
						<div class="autofit-col autofit-col-expand">
							<p class="lfr-portal-tooltip list-group-title text-truncate" title="<%= HtmlUtil.escapeAttribute(segmentsExperiencesJSONObject.getString("segmentsExperienceName")) %>">
								<%= segmentsExperiencesJSONObject.getString("segmentsExperienceName") %>
							</p>

							<p class="lfr-portal-tooltip list-group-text text-secondary text-truncate" title="<%= HtmlUtil.escapeAttribute(segmentsExperiencesJSONObject.getString("segmentsEntryName")) %>">
								<liferay-ui:message arguments='<%= segmentsExperiencesJSONObject.getString("segmentsEntryName") %>' key="segment-x" />
							</p>
						</div>

						<c:if test='<%= segmentsExperiencesJSONObject.getBoolean("active") %>'>
							<div class="autofit-col">
								<span class="label label-success m-0">
									<span class="label-item label-item-expand">
										<liferay-ui:message key="active" />
									</span>
								</span>
							</div>
						</c:if>
					</a>
				</li>

			<%
			}
			%>

		</ul>
	</div>
</li>

<liferay-frontend:component
	module="js/ExperienceSelector"
/>