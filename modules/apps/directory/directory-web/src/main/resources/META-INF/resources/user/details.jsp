<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
User selUser = (User)request.getAttribute("user.selUser");
Contact selContact = (Contact)request.getAttribute("user.selContact");
List<Organization> organizations = (List<Organization>)request.getAttribute("user.organizations");

String organizationsHTML = StringPool.BLANK;

for (int i = 0; i < organizations.size(); i++) {
	Organization organization = organizations.get(i);

	if (i == 0) {
		organizationsHTML = organization.getName();
	}
	else {
		organizationsHTML += ", " + organization.getName();
	}
}
%>

<h2><%= selUser.getFullName() %></h2>

<div class="details">
	<liferay-ui:user-portrait
		user="<%= selUser %>"
	/>

	<dl class="property-list">
		<c:if test="<%= Validator.isNotNull(selUser.getDisplayEmailAddress()) %>">
			<dt>
				<liferay-ui:message key="email-address" />
			</dt>
			<dd>
				<%= selUser.getDisplayEmailAddress() %>
			</dd>
		</c:if>

		<c:if test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.FIELD_ENABLE_COM_LIFERAY_PORTAL_KERNEL_MODEL_CONTACT_BIRTHDAY) %>">
			<dt>
				<liferay-ui:message key="birthday" />
			</dt>
			<dd>
				<%= dateFormatDate.format(selUser.getBirthday()) %>
			</dd>
		</c:if>

		<c:if test="<%= Validator.isNotNull(selContact.getJobTitle()) %>">
			<dt>
				<liferay-ui:message key="job-title" />
			</dt>
			<dd>
				<%= HtmlUtil.escape(selContact.getJobTitle()) %>
			</dd>
		</c:if>

		<c:if test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.FIELD_ENABLE_COM_LIFERAY_PORTAL_KERNEL_MODEL_CONTACT_MALE) %>">
			<dt>
				<liferay-ui:message key="gender" />
			</dt>
			<dd>
				<%= LanguageUtil.get(request, selUser.isMale() ? "male" : "female") %>
			</dd>
		</c:if>

		<c:if test="<%= !organizations.isEmpty() %>">
			<dt>
				<c:choose>
					<c:when test="<%= organizations.size() > 1 %>">
						<liferay-ui:message key="organizations" />
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="organization" />
					</c:otherwise>
				</c:choose>
			</dt>
			<dd>
				<%= HtmlUtil.escape(organizationsHTML) %>
			</dd>
		</c:if>
	</dl>
</div>