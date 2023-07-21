<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
DLItemSelectorViewDisplayContext dlItemSelectorViewDisplayContext = (DLItemSelectorViewDisplayContext)request.getAttribute(DLItemSelectorWebKeys.DL_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT);

int cur = ParamUtil.getInteger(request, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_CUR);
int delta = ParamUtil.getInteger(request, SearchContainer.DEFAULT_DELTA_PARAM, SearchContainer.DEFAULT_DELTA);

int[] startAndEnd = SearchPaginationUtil.calculateStartAndEnd(cur, delta);

int start = startAndEnd[0];
int end = startAndEnd[1];

List repositoryEntries = null;
int repositoryEntriesCount = 0;

long groupId = dlItemSelectorViewDisplayContext.getStagingAwareGroupId(themeDisplay.getScopeGroupId());
long folderId = dlItemSelectorViewDisplayContext.getFolderId(request);
String[] mimeTypes = dlItemSelectorViewDisplayContext.getMimeTypes();

if (dlItemSelectorViewDisplayContext.isSearch()) {
	SearchContext searchContext = SearchContextFactory.getInstance(request);

	searchContext.setAttribute("mimeTypes", mimeTypes);
	searchContext.setEnd(end);
	searchContext.setFolderIds(new long[] {dlItemSelectorViewDisplayContext.getFolderId(request)});
	searchContext.setGroupIds(new long[] {groupId});
	searchContext.setStart(start);

	Hits hits = DLAppServiceUtil.search(groupId, searchContext);

	repositoryEntriesCount = hits.getLength();

	Document[] docs = hits.getDocs();

	repositoryEntries = new ArrayList(docs.length);

	List<SearchResult> searchResults = SearchResultUtil.getSearchResults(hits, locale);

	for (int i = 0; i < searchResults.size(); i++) {
		SearchResult searchResult = searchResults.get(i);

		String className = searchResult.getClassName();

		if (className.equals(DLFileEntryConstants.getClassName()) || FileEntry.class.isAssignableFrom(Class.forName(className))) {
			repositoryEntries.add(DLAppServiceUtil.getFileEntry(searchResult.getClassPK()));
		}
		else if (className.equals(DLFileShortcutConstants.getClassName())) {
			repositoryEntries.add(DLAppServiceUtil.getFileShortcut(searchResult.getClassPK()));
		}
		else if (className.equals(DLFolderConstants.getClassName())) {
			repositoryEntries.add(DLAppServiceUtil.getFolder(searchResult.getClassPK()));
		}
		else {
			continue;
		}
	}
}
else {
	repositoryEntries = DLAppServiceUtil.getFoldersAndFileEntriesAndFileShortcuts(groupId, folderId, WorkflowConstants.STATUS_APPROVED, mimeTypes, false, false, start, end, dlItemSelectorViewDisplayContext.getOrderByComparator());

	repositoryEntriesCount = DLAppServiceUtil.getFoldersAndFileEntriesAndFileShortcutsCount(groupId, folderId, WorkflowConstants.STATUS_APPROVED, mimeTypes, false, false);
}
%>

<liferay-item-selector:repository-entry-browser
	allowedCreationMenuUIItemKeys="<%= dlItemSelectorViewDisplayContext.getAllowedCreationMenuUIItemKeys() %>"
	emptyResultsMessage='<%= LanguageUtil.get(request, "there-are-no-documents-or-media-files-in-this-folder") %>'
	extensions="<%= ListUtil.toList(dlItemSelectorViewDisplayContext.getExtensions()) %>"
	itemSelectedEventName="<%= dlItemSelectorViewDisplayContext.getItemSelectedEventName() %>"
	itemSelectorReturnTypeResolver="<%= dlItemSelectorViewDisplayContext.getItemSelectorReturnTypeResolver() %>"
	maxFileSize="<%= DLValidatorUtil.getMaxAllowableSize() %>"
	portletURL="<%= dlItemSelectorViewDisplayContext.getPortletURL(request, liferayPortletResponse) %>"
	repositoryEntries="<%= repositoryEntries %>"
	repositoryEntriesCount="<%= repositoryEntriesCount %>"
	showBreadcrumb="<%= true %>"
	showDragAndDropZone="<%= dlItemSelectorViewDisplayContext.isShowDragAndDropZone(request) %>"
	tabName="<%= dlItemSelectorViewDisplayContext.getTitle(locale) %>"
	uploadURL="<%= dlItemSelectorViewDisplayContext.getUploadURL(request, liferayPortletResponse) %>"
/>