<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long organizationId = ParamUtil.getLong(request, "organizationId");

Organization organization = OrganizationServiceUtil.fetchOrganization(organizationId);

long logoId = organization.getLogoId();
%>

<h2><%= HtmlUtil.escape(organization.getName()) %></h2>

<div class="details">
	<img alt="<%= HtmlUtil.escapeAttribute(organization.getName()) %>" class="avatar" src="<%= themeDisplay.getPathImage() %>/organization_logo?img_id=<%= logoId %>&t=<%= WebServerServletTokenUtil.getToken(logoId) %>" />

	<dl class="property-list">
		<dt>
			<liferay-ui:message key="type" />
		</dt>
		<dd>
			<%= LanguageUtil.get(request, organization.getType()) %>
		</dd>

		<c:if test="<%= PropsValues.FIELD_ENABLE_COM_LIFERAY_PORTAL_KERNEL_MODEL_ORGANIZATION_STATUS %>">
			<dt>
				<liferay-ui:message key="status" />
			</dt>
			<dd>
				<%= LanguageUtil.get(request, ListTypeServiceUtil.getListType(organization.getStatusId()).getName()) %>
			</dd>
		</c:if>

		<c:if test="<%= organization.getCountryId() > 0 %>">
			<dt>
				<liferay-ui:message key="country" />
			</dt>
			<dd>
				<%= LanguageUtil.get(request, CountryServiceUtil.getCountry(organization.getCountryId()).getName()) %>
			</dd>
		</c:if>

		<c:if test="<%= organization.getRegionId() > 0 %>">
			<dt>
				<liferay-ui:message key="region" />
			</dt>
			<dd>
				<%= LanguageUtil.get(request, RegionServiceUtil.getRegion(organization.getRegionId()).getName()) %>
			</dd>
		</c:if>

		<c:if test="<%= organization.getParentOrganization() != null %>">
			<dt>
				<liferay-ui:message key="parent-organization" />
			</dt>
			<dd>
				<%= HtmlUtil.escape(organization.getParentOrganization().getName()) %>
			</dd>
		</c:if>
	</dl>
</div>