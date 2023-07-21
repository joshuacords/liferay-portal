<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/preview/init.jsp" %>

<%
String randomNamespace = PortalUtil.generateRandomKey(request, "portlet_document_library_view_file_entry_preview") + StringPool.UNDERLINE;

Map<String, Object> context = new HashMap<>();

List<String> previewFileURLs = (List<String>)request.getAttribute(DLPreviewVideoWebKeys.PREVIEW_FILE_URLS);

context.put(
	"videoSources",
	new ArrayList<Map<String, String>>() {
		{
			for (String previewFileURL : previewFileURLs) {
				if (Validator.isNotNull(previewFileURL)) {
					if (previewFileURL.endsWith("mp4")) {
						add(MapUtil.fromArray("type", "video/mp4", "url", previewFileURL));
					}
					else if (previewFileURL.endsWith("ogv")) {
						add(MapUtil.fromArray("type", "video/ogv", "url", previewFileURL));
					}
				}
			}
		}
	});

context.put("videoPosterURL", (String)request.getAttribute(DLPreviewVideoWebKeys.VIDEO_POSTER_URL));
%>

<liferay-util:html-top
	outputKey="document_library_preview_video_css"
>
	<link href="<%= PortalUtil.getStaticResourceURL(request, PortalUtil.getPathProxy() + application.getContextPath() + "/preview/css/main.css") %>" rel="stylesheet" type="text/css" />
</liferay-util:html-top>

<soy:component-renderer
	componentId='<%= renderResponse.getNamespace() + randomNamespace + "previewVideo" %>'
	context="<%= context %>"
	module="preview/js/VideoPreviewer.es"
	templateNamespace="com.liferay.document.library.preview.VideoPreviewer.render"
/>