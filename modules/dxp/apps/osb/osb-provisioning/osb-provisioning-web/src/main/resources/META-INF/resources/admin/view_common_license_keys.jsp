<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);
%>

<liferay-ui:tabs
	names="commerce,elasticsearch"
	param="tabs1"
	portletURL="<%= portletURL %>"
/>

<c:choose>
	<c:when test='<%= tabs1.equals("elasticsearch") %>'>
		<portlet:actionURL name="/admin/upload_elasticsearch_license" var="uploadElasticsearchLicenseURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
		</portlet:actionURL>

		<aui:form action="<%= uploadElasticsearchLicenseURL %>" cssClass="container-fluid container-fluid-max-xl" enctype="multipart/form-data" method="post">
			<liferay-ui:error exception="<%= DuplicateCommonLicenseKeyException.class %>" message="the-file-has-already-been-uploaded" />

			<aui:fieldset-group>
				<aui:fieldset>
					<aui:input multiple="<%= true %>" name="elasticsearchLicenseFiles" type="file" />

					<aui:button type="submit" value="submit" />
				</aui:fieldset>
			</aui:fieldset-group>
		</aui:form>

		<liferay-ui:search-container
			iteratorURL="<%= portletURL %>"
			total="<%= CommonLicenseKeyLocalServiceUtil.getCommonLicenseKeysCount(ProductGroup.Name.ENTERPRISE_SEARCH.toString()) %>"
		>
			<liferay-ui:search-container-results
				results="<%= CommonLicenseKeyLocalServiceUtil.getCommonLicenseKeys(ProductGroup.Name.ENTERPRISE_SEARCH.toString(), searchContainer.getStart(), searchContainer.getEnd()) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.provisioning.license.model.CommonLicenseKey"
				escapedModel="<%= true %>"
				keyProperty="commonLicenseKeyId"
				modelVar="commonLicenseKey"
			>
				<portlet:renderURL var="rowURL">
					<portlet:param name="mvcRenderCommandName" value="/admin/edit_common_license_key" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="commonLicenseKeyId" value="<%= String.valueOf(commonLicenseKey.getCommonLicenseKeyId()) %>" />
				</portlet:renderURL>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="name"
					value="<%= commonLicenseKey.getFileName() %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="product-environment"
					value="<%= commonLicenseKey.getProductEnvironment() %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="start-date"
					value="<%= mediumDateFormatDate.format(commonLicenseKey.getStartDate()) %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="end-date"
					value="<%= mediumDateFormatDate.format(commonLicenseKey.getEndDate()) %>"
				/>

				<liferay-ui:search-container-column-jsp
					align="right"
					path="/admin/common_license_key_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</c:when>
	<c:otherwise>
		<portlet:actionURL name="/admin/upload_commerce_license" var="uploadCommerceLicenseURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
		</portlet:actionURL>

		<aui:form action="<%= uploadCommerceLicenseURL %>" cssClass="container-fluid container-fluid-max-xl" enctype="multipart/form-data" method="post">
			<liferay-ui:error exception="<%= DuplicateCommonLicenseKeyException.class %>" message="the-file-has-already-been-uploaded" />

			<aui:fieldset-group>
				<aui:fieldset>
					<aui:input multiple="<%= true %>" name="commerceLicenseFiles" type="file" />

					<aui:button type="submit" value="submit" />
				</aui:fieldset>
			</aui:fieldset-group>
		</aui:form>

		<liferay-ui:search-container
			iteratorURL="<%= portletURL %>"
			total="<%= CommonLicenseKeyLocalServiceUtil.getCommonLicenseKeysCount(ProductGroup.Name.COMMERCE.toString()) %>"
		>
			<liferay-ui:search-container-results
				results="<%= CommonLicenseKeyLocalServiceUtil.getCommonLicenseKeys(ProductGroup.Name.COMMERCE.toString(), searchContainer.getStart(), searchContainer.getEnd()) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.provisioning.license.model.CommonLicenseKey"
				escapedModel="<%= true %>"
				keyProperty="commonLicenseKeyId"
				modelVar="commonLicenseKey"
			>
				<portlet:renderURL var="rowURL">
					<portlet:param name="mvcRenderCommandName" value="/admin/edit_common_license_key" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="commonLicenseKeyId" value="<%= String.valueOf(commonLicenseKey.getCommonLicenseKeyId()) %>" />
				</portlet:renderURL>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="name"
					value="<%= commonLicenseKey.getFileName() %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="product-environment"
					value="<%= commonLicenseKey.getProductEnvironment() %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="start-date"
					value="<%= mediumDateFormatDate.format(commonLicenseKey.getStartDate()) %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="end-date"
					value="<%= mediumDateFormatDate.format(commonLicenseKey.getEndDate()) %>"
				/>

				<liferay-ui:search-container-column-jsp
					align="right"
					path="/admin/common_license_key_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</c:otherwise>
</c:choose>