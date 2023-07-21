<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/document_library/init.jsp" %>

<%
FileEntry fileEntry = (FileEntry)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_ENTRY);
FileVersion fileVersion = (FileVersion)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_VERSION);

String eventName = ParamUtil.getString(request, "eventName", renderResponse.getNamespace() + "selectFileVersionFm");

int status = WorkflowConstants.STATUS_APPROVED;

if ((user.getUserId() == fileEntry.getUserId()) || permissionChecker.isContentReviewer(user.getCompanyId(), scopeGroupId)) {
	status = WorkflowConstants.STATUS_ANY;
}
%>

<liferay-portlet:renderURL varImpl="portletURL">
	<portlet:param name="mvcRenderCommandName" value="/document_library/select_file_version" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
	<portlet:param name="fileEntryId" value="<%= String.valueOf(fileEntry.getFileEntryId()) %>" />
	<portlet:param name="version" value="<%= String.valueOf(fileVersion.getVersion()) %>" />
</liferay-portlet:renderURL>

<div class="container-fluid-1280">
	<aui:form action="<%= portletURL %>" method="post" name="selectFileVersionFm">
		<liferay-ui:search-container
			id="fileVersionSearchContainer"
			iteratorURL="<%= portletURL %>"
			total="<%= fileEntry.getFileVersionsCount(status) %>"
		>
			<liferay-ui:search-container-results
				results="<%= fileEntry.getFileVersions(status) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portal.kernel.repository.model.FileVersion"
				modelVar="curFileVersion"
			>
				<liferay-ui:search-container-column-text
					name="title"
					truncate="<%= true %>"
				>
					<c:choose>
						<c:when test="<%= fileVersion.getFileVersionId() != curFileVersion.getFileVersionId() %>">

							<%
							Map<String, Object> data = new HashMap<String, Object>();

							data.put("sourceversion", curFileVersion.getFileVersionId());
							data.put("targetversion", fileVersion.getFileVersionId());
							%>

							<aui:a cssClass="selector-button" data="<%= data %>" href="javascript:;">
								<%= HtmlUtil.escape(curFileVersion.getTitle()) %>
							</aui:a>
						</c:when>
						<c:otherwise>
							<%= HtmlUtil.escape(curFileVersion.getTitle()) %>
						</c:otherwise>
					</c:choose>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					name="version"
					property="version"
				/>

				<liferay-ui:search-container-column-date
					name="date"
					value="<%= curFileVersion.getModifiedDate() %>"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</aui:form>
</div>

<aui:script>
	Liferay.Util.selectEntityHandler(
		'#<portlet:namespace />selectFileVersionFm',
		'<%= HtmlUtil.escapeJS(eventName) %>'
	);
</aui:script>