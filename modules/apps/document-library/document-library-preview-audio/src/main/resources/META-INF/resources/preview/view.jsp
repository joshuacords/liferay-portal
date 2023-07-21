<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/preview/init.jsp" %>

<%
String randomNamespace = PortalUtil.generateRandomKey(request, "portlet_document_library_view_file_entry_preview") + StringPool.UNDERLINE;

List<String> previewFileURLs = (List<String>)request.getAttribute(DLPreviewAudioWebKeys.PREVIEW_FILE_URLS);

List<Map<String, String>> audioSources = new ArrayList<>(previewFileURLs.size());

for (String previewFileURL : previewFileURLs) {
	if (Validator.isNotNull(previewFileURL)) {
		if (previewFileURL.endsWith("mp3")) {
			audioSources.add(MapUtil.fromArray("type", "audio/mp3", "url", previewFileURL));
		}
		else if (previewFileURL.endsWith("ogg")) {
			audioSources.add(MapUtil.fromArray("type", "audio/ogg", "url", previewFileURL));
		}
	}
}

Map<String, Object> context = new HashMap<>();

context.put("audioMaxWidth", PropsValues.DL_FILE_ENTRY_PREVIEW_VIDEO_WIDTH);
context.put("audioSources", audioSources);
%>

<liferay-util:html-top
	outputKey="document_library_preview_audio_css"
>
	<link href="<%= PortalUtil.getStaticResourceURL(request, PortalUtil.getPathProxy() + application.getContextPath() + "/preview/css/main.css") %>" rel="stylesheet" type="text/css" />
</liferay-util:html-top>

<soy:component-renderer
	componentId='<%= renderResponse.getNamespace() + randomNamespace + "previewAudio" %>'
	context="<%= context %>"
	module="preview/js/AudioPreviewer.es"
	templateNamespace="com.liferay.document.library.preview.AudioPreviewer.render"
/>