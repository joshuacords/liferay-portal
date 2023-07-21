<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/document_library/init.jsp" %>

<%
Folder folder = (Folder)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FOLDER);

long folderId = BeanParamUtil.getLong(folder, request, "folderId", DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);

long groupId = BeanParamUtil.getLong(folder, request, "groupId");

String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectFileEntry_" + groupId);

if (folder != null) {
	DLBreadcrumbUtil.addPortletBreadcrumbEntries(folder, request, renderResponse);
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/document_library/select_file_entry");
portletURL.setParameter("groupId", String.valueOf(groupId));
portletURL.setParameter("folderId", String.valueOf(folderId));

SearchContainer dlSearchContainer = new SearchContainer(liferayPortletRequest, null, null, "curEntry", SearchContainer.DEFAULT_DELTA, portletURL, null, null);

dlSearchContainer.setTotal(DLAppServiceUtil.getFoldersAndFileEntriesAndFileShortcutsCount(groupId, folderId, WorkflowConstants.STATUS_APPROVED, true));

List<Object> foldersAndFileEntriesAndFileShortcuts = DLAppServiceUtil.getFoldersAndFileEntriesAndFileShortcuts(groupId, folderId, WorkflowConstants.STATUS_APPROVED, true, dlSearchContainer.getStart(), dlSearchContainer.getEnd(), dlSearchContainer.getOrderByComparator());

dlSearchContainer.setResults(foldersAndFileEntriesAndFileShortcuts);
%>

<div class="container-fluid-1280">
	<aui:form method="post" name="selectFileEntryFm">

		<%
		Group group = GroupLocalServiceUtil.getGroup(groupId);
		%>

		<liferay-ui:breadcrumb
			showGuestGroup="<%= group.isGuest() %>"
			showLayout="<%= false %>"
			showParentGroups="<%= false %>"
		/>

		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-documents-or-media-files-in-this-folder"
			searchContainer="<%= dlSearchContainer %>"
		>
			<liferay-ui:search-container-row
				className="Object"
				cssClass="app-view-entry-taglib entry-display-style"
				modelVar="result"
			>
				<%@ include file="/document_library/cast_result.jspf" %>

				<c:choose>
					<c:when test="<%= curFolder != null %>">
						<liferay-portlet:renderURL varImpl="rowURL">
							<portlet:param name="mvcRenderCommandName" value="/document_library/select_file_entry" />
							<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
							<portlet:param name="folderId" value="<%= String.valueOf(curFolder.getFolderId()) %>" />
						</liferay-portlet:renderURL>

						<%
						if (curFolder.isMountPoint()) {
							try {
								DLAppServiceUtil.getFoldersCount(curFolder.getRepositoryId(), curFolder.getFolderId());
							}
							catch (PortalException pe) {
								rowURL = null;
							}
						}
						%>

						<liferay-ui:search-container-column-text
							name="title"
						>
							<aui:a href="<%= rowURL.toString() %>">
								<%= curFolder.getName() %>
							</aui:a>
						</liferay-ui:search-container-column-text>
					</c:when>
					<c:when test="<%= (fileEntry != null) && (fileShortcut == null) %>">

						<%
						FileVersion fileVersion = fileEntry.getFileVersion();
						%>

						<c:if test="<%= fileVersion.isApproved() %>">
							<liferay-ui:search-container-column-text
								name="title"
							>

								<%
								Map<String, Object> data = new HashMap<String, Object>();

								data.put("entryid", fileEntry.getFileEntryId());
								data.put("entryname", HtmlUtil.unescape(fileEntry.getTitle()));
								%>

								<aui:a cssClass="selector-button" data="<%= data %>" href="javascript:;">
									<%= fileEntry.getTitle() %>
								</aui:a>

								<c:if test="<%= Validator.isNotNull(fileEntry.getDescription()) %>">
									<br />
									<%= fileEntry.getDescription() %>
								</c:if>
							</liferay-ui:search-container-column-text>
						</c:if>
					</c:when>
				</c:choose>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
				resultRowSplitter="<%= new DLResultRowSplitter() %>"
				searchContainer="<%= dlSearchContainer %>"
			/>
		</liferay-ui:search-container>
	</aui:form>
</div>

<aui:script>
	Liferay.Util.selectEntityHandler(
		'#<portlet:namespace />selectFileEntryFm',
		'<%= HtmlUtil.escapeJS(eventName) %>'
	);
</aui:script>