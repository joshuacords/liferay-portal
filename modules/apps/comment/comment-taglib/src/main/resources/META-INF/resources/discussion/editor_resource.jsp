<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/discussion/init.jsp" %>

<liferay-ui:input-editor
	autoCreate="<%= true %>"
	configKey="commentEditor"
	contents='<%= GetterUtil.getString(request.getAttribute("liferay-comment:editor:contents")) %>'
	cssClass="form-control"
	editorName='<%= PropsUtil.get("editor.wysiwyg.portal-web.docroot.html.taglib.ui.discussion.jsp") %>'
	name='<%= GetterUtil.getString(request.getAttribute("liferay-comment:editor:name")) %>'
	onChangeMethod='<%= GetterUtil.getString(request.getAttribute("liferay-comment:editor:onChangeMethod")) %>'
	placeholder='<%= GetterUtil.getString(request.getAttribute("liferay-comment:editor:placeholder")) %>'
	showSource="<%= false %>"
	skipEditorLoading="<%= true %>"
/>