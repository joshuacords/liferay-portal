<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/image_gallery_display/init.jsp" %>

<%
Folder folder = (Folder)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FOLDER);

long folderId = (folder == null) ? 0 : folder.getFolderId();

long repositoryId = scopeGroupId;

if (folder != null) {
	repositoryId = folder.getRepositoryId();
}

List fileEntries = DLAppServiceUtil.getFileEntries(repositoryId, folderId);

int defaultSpeed = 3000;
%>

<aui:form>
	<aui:fieldset column="<%= true %>">
		<aui:col width="<%= 50 %>">
			<aui:button onClick='<%= renderResponse.getNamespace() + "showPrevious();" %>' value="previous" />
			<aui:button onClick='<%= renderResponse.getNamespace() + "play();" %>' value="play" />
			<aui:button onClick='<%= renderResponse.getNamespace() + "pause();" %>' value="pause" />
			<aui:button onClick='<%= renderResponse.getNamespace() + "showNext();" %>' value="next" />
		</aui:col>

		<aui:col width="<%= 50 %>">
			<aui:select inlineLabel="left" name="speed" onChange='<%= renderResponse.getNamespace() + "changeSpeed(this[this.selectedIndex].value * 1000);" %>'>

				<%
				for (int i = 1; i <= 10; i++) {
				%>

					<aui:option label="<%= i %>" selected="<%= (defaultSpeed / 1000) == i %>" />

				<%
				}
				%>

			</aui:select>
		</aui:col>
	</aui:fieldset>
</aui:form>

<br />

<table class="lfr-table">
	<tr>
		<td>
			<c:if test="<%= !fileEntries.isEmpty() %>">

				<%
				FileEntry fileEntry = (FileEntry)fileEntries.get(0);
				%>

				<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="slide-show" />" name="<portlet:namespace />slideShow" src="<%= DLURLHelperUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), themeDisplay, StringPool.BLANK) %>" />
			</c:if>
		</td>
	</tr>
</table>

<aui:script>
	var <portlet:namespace />imgArray = [];

	<%
	for (int i = 0; i < fileEntries.size(); i++) {
		FileEntry fileEntry = (FileEntry)fileEntries.get(i);
	%>

		<portlet:namespace />imgArray[<%= i %>] =
			'<%= DLURLHelperUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), themeDisplay, StringPool.BLANK) %>';

	<%
	}
	%>

	var <portlet:namespace />imgArrayPos = 0;
	var <portlet:namespace />speed = <%= defaultSpeed %>;
	var <portlet:namespace />timeout = 0;

	function <portlet:namespace />changeSpeed(speed) {
		<portlet:namespace />pause();

		<portlet:namespace />speed = speed;

		<portlet:namespace />play();
	}

	function <portlet:namespace />pause() {
		clearInterval(<portlet:namespace />timeout);
		<portlet:namespace />timeout = 0;
	}

	function <portlet:namespace />play() {
		if (<portlet:namespace />timeout == 0) {
			<portlet:namespace />timeout = setInterval(
				<portlet:namespace />showNext,
				<portlet:namespace />speed
			);
		}
	}

	function <portlet:namespace />showNext() {
		<portlet:namespace />imgArrayPos++;

		if (
			<portlet:namespace />imgArrayPos == <portlet:namespace />imgArray.length
		) {
			<portlet:namespace />imgArrayPos = 0;
		}

		document.images.<portlet:namespace />slideShow.src =
			<portlet:namespace />imgArray[<portlet:namespace />imgArrayPos];
	}

	function <portlet:namespace />showPrevious() {
		<portlet:namespace />imgArrayPos--;

		if (<portlet:namespace />imgArrayPos < 0) {
			<portlet:namespace />imgArrayPos =
				<portlet:namespace />imgArray.length - 1;
		}

		document.images.<portlet:namespace />slideShow.src =
			<portlet:namespace />imgArray[<portlet:namespace />imgArrayPos];
	}

	<portlet:namespace />play();
</aui:script>