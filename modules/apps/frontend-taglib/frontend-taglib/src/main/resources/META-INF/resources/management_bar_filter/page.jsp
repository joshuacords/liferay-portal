<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/management_bar_filter/init.jsp" %>

<%
boolean disabled = GetterUtil.getBoolean(request.getAttribute("liferay-frontend:management-bar-filter:disabled"));
String label = (String)request.getAttribute("liferay-frontend:management-bar-filter:label");
List<ManagementBarFilterItem> managementBarFilterItems = (List<ManagementBarFilterItem>)request.getAttribute("liferay-frontend:management-bar-filter:managementBarFilterItems");
String value = (String)request.getAttribute("liferay-frontend:management-bar-filter:value");
%>

<c:if test="<%= !managementBarFilterItems.isEmpty() %>">
	<li class="dropdown <%= disabled ? "disabled" : StringPool.BLANK %>">
		<a aria-expanded="true" class="dropdown-toggle" data-qa-id="filter<%= Validator.isNotNull(label) ? label : StringPool.BLANK %>" data-toggle="<%= disabled ? StringPool.BLANK : "dropdown" %>" href="javascript:;">
			<span class="management-bar-item-title">
				<c:if test="<%= Validator.isNotNull(label) %>">
					<liferay-ui:message key="<%= label %>" />:
				</c:if>

				<liferay-ui:message key="<%= HtmlUtil.escape(value) %>" />
			</span>

			<aui:icon image="caret-double-l" markupView="lexicon" />
		</a>

		<ul class="dropdown-menu" data-qa-id="filter<%= Validator.isNotNull(label) ? label : StringPool.BLANK %>Values">

			<%
			for (ManagementBarFilterItem managementBarFilterItem : managementBarFilterItems) {
			%>

				<li class="<%= managementBarFilterItem.isActive() ? "active" : StringPool.BLANK %>">
					<aui:a href="<%= managementBarFilterItem.getUrl() %>" id="<%= Validator.isNotNull(managementBarFilterItem.getId()) ? managementBarFilterItem.getId() : StringPool.BLANK %>" label="<%= HtmlUtil.escape(managementBarFilterItem.getLabel()) %>" />
				</li>

			<%
			}
			%>

		</ul>
	</li>
</c:if>