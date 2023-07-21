<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/repository_entry_browser/init.jsp" %>

<div class="search-info">

	<%
	long folderId = ParamUtil.getLong(request, "folderId");
	String keywords = ParamUtil.getString(request, "keywords");

	boolean searchEverywhere = true;

	long searchFolderId = ParamUtil.getLong(request, "searchFolderId");

	boolean showRerunSearchButton = true;

	if (folderId > DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
		searchEverywhere = false;
	}
	else {
		folderId = searchFolderId;
	}

	if ((folderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) && (searchFolderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID)) {
		showRerunSearchButton = false;
	}
	%>

	<c:if test="<%= showRerunSearchButton %>">

		<%
		PortletURL portletURL = (PortletURL)request.getAttribute("liferay-item-selector:repository-entry-browser:portletURL");

		PortletURL searchEverywhereURL = PortletURLUtil.clone(portletURL, liferayPortletResponse);

		searchEverywhereURL.setParameter("folderId", String.valueOf(DLFolderConstants.DEFAULT_PARENT_FOLDER_ID));
		searchEverywhereURL.setParameter("searchFolderId", String.valueOf(folderId));
		searchEverywhereURL.setParameter("keywords", keywords);

		PortletURL searchFolderURL = PortletURLUtil.clone(searchEverywhereURL, liferayPortletResponse);

		searchFolderURL.setParameter("folderId", String.valueOf(folderId));
		%>

		<liferay-util:whitespace-remover>
			<liferay-ui:message key="search" />

			<clay:link
				buttonStyle="secondary"
				elementClasses='<%= "btn-sm" + (searchEverywhere ? " active" : "") %>'
				href="<%= searchEverywhereURL.toString() %>"
				label='<%= LanguageUtil.get(resourceBundle, "everywhere") %>'
			/>

			<%
			Folder folder = DLAppServiceUtil.getFolder(folderId);
			%>

			<clay:link
				buttonStyle="secondary"
				elementClasses='<%= "btn-sm" + (!searchEverywhere ? " active" : "") %>'
				href="<%= searchFolderURL.toString() %>"
				icon="folder"
				label="<%= folder.getName() %>"
			/>
		</liferay-util:whitespace-remover>
	</c:if>
</div>