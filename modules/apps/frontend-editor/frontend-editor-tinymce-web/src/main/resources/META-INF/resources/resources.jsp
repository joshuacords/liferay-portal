<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String editorName = (String)request.getAttribute(TinyMCEEditorConstants.ATTRIBUTE_NAMESPACE + ":editorName");
%>

<liferay-util:html-top
	outputKey="js_editor_tinymce"
>

	<%
	long javaScriptLastModified = PortalWebResourcesUtil.getLastModified(PortalWebResourceConstants.RESOURCE_TYPE_EDITOR_TINYMCEEDITOR);
	%>

	<script data-senna-track="temporary" src="<%= HtmlUtil.escape(PortalUtil.getStaticResourceURL(request, themeDisplay.getCDNHost() + PortalWebResourcesUtil.getContextPath(PortalWebResourceConstants.RESOURCE_TYPE_EDITOR_TINYMCEEDITOR) + "/tiny_mce/tinymce.min.js", javaScriptLastModified)) %>" type="text/javascript"></script>

	<liferay-util:dynamic-include key='<%= "com.liferay.frontend.editor.tinymce.web#" + editorName + "#additionalResources" %>' />

	<script data-senna-track="temporary" type="text/javascript">
		var tinymceInstances = 0;
		var disposeResources = false;

		var cleanupGlobals = function() {
			if (!tinymceInstances && disposeResources) {
				window.tinyMCE = undefined;

				tinymceInstances = 0;
				disposeResources = false;
			}
		};

		Liferay.namespace('EDITORS').tinymce = {
			addInstance: function() {
				tinymceInstances++;
			},
			removeInstance: function() {
				tinymceInstances--;

				cleanupGlobals();
			}
		};

		var destroyGlobalEditors = function() {
			disposeResources = true;

			cleanupGlobals();

			Liferay.detach('beforeScreenFlip', destroyGlobalEditors);
		};

		Liferay.on('beforeScreenFlip', destroyGlobalEditors);
	</script>
</liferay-util:html-top>