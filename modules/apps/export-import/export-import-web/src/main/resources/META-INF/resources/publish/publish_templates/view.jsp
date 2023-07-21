<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long groupId = ParamUtil.getLong(request, "groupId");
long layoutSetBranchId = ParamUtil.getLong(request, "layoutSetBranchId");
boolean localPublishing = ParamUtil.getBoolean(request, "localPublishing");
boolean privateLayout = ParamUtil.getBoolean(request, "privateLayout");
%>

<liferay-portlet:renderURL varImpl="portletURL">
	<portlet:param name="mvcRenderCommandName" value="publishLayouts" />
	<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.PUBLISH %>" />
	<portlet:param name="publishConfigurationButtons" value="saved" />
	<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
	<portlet:param name="layoutSetBranchId" value="<%= String.valueOf(layoutSetBranchId) %>" />
	<portlet:param name="layoutSetBranchName" value='<%= ParamUtil.getString(request, "layoutSetBranchName") %>' />
	<portlet:param name="privateLayout" value="<%= String.valueOf(privateLayout) %>" />
</liferay-portlet:renderURL>

<%
int exportImportConfigurationType = localPublishing ? ExportImportConfigurationConstants.TYPE_PUBLISH_LAYOUT_LOCAL : ExportImportConfigurationConstants.TYPE_PUBLISH_LAYOUT_REMOTE;
%>

<div class="export-dialog-tree">
	<div class="container-fluid-1280">
		<div class="alert alert-info">
			<liferay-ui:message key="publish-templates-can-be-administered-in-the-control-menu" />
		</div>

		<aui:form action="<%= portletURL %>">
			<liferay-ui:search-container
				displayTerms="<%= new ExportImportConfigurationSearchTerms(renderRequest) %>"
				emptyResultsMessage="there-are-no-saved-publish-templates"
				iteratorURL="<%= portletURL %>"
				orderByCol="name"
				orderByComparator="<%= new ExportImportConfigurationNameComparator(true) %>"
				orderByType="asc"
				searchTerms="<%= new ExportImportConfigurationSearchTerms(renderRequest) %>"
			>
				<liferay-ui:search-container-results>
					<%@ include file="/export_import_configuration_search_results.jspf" %>
				</liferay-ui:search-container-results>

				<liferay-ui:search-container-row
					className="com.liferay.exportimport.kernel.model.ExportImportConfiguration"
					keyProperty="exportImportConfigurationId"
					modelVar="exportImportConfiguration"
				>
					<liferay-ui:search-container-column-text
						cssClass="background-task-user-column"
						name="user"
					>
						<liferay-ui:user-display
							displayStyle="3"
							showUserDetails="<%= false %>"
							showUserName="<%= false %>"
							userId="<%= exportImportConfiguration.getUserId() %>"
						/>
					</liferay-ui:search-container-column-text>

					<liferay-portlet:renderURL varImpl="rowURL">
						<portlet:param name="mvcRenderCommandName" value="publishLayouts" />
						<portlet:param name="<%= Constants.CMD %>" value="<%= localPublishing ? Constants.PUBLISH_TO_LIVE : Constants.PUBLISH_TO_REMOTE %>" />
						<portlet:param name="redirect" value="<%= searchContainer.getIteratorURL().toString() %>" />
						<portlet:param name="exportImportConfigurationId" value="<%= String.valueOf(exportImportConfiguration.getExportImportConfigurationId()) %>" />
						<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
						<portlet:param name="privateLayout" value="<%= String.valueOf(privateLayout) %>" />
					</liferay-portlet:renderURL>

					<liferay-ui:search-container-column-text
						href="<%= rowURL %>"
						name="name"
						value="<%= HtmlUtil.escape(exportImportConfiguration.getName()) %>"
					/>

					<liferay-ui:search-container-column-text
						name="description"
						value="<%= HtmlUtil.escape(exportImportConfiguration.getDescription()) %>"
					/>

					<liferay-ui:search-container-column-date
						name="create-date"
						value="<%= exportImportConfiguration.getCreateDate() %>"
					/>

					<liferay-ui:search-container-column-jsp
						align="right"
						cssClass="entry-action"
						path="/publish/publish_templates/actions.jsp"
					/>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
				/>
			</liferay-ui:search-container>
		</aui:form>
	</div>
</div>