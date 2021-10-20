<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Layout curLayout = (Layout)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<liferay-ui:icon
		message="view"
		url="<%= layoutsAdminDisplayContext.getViewLayoutURL(curLayout) %>"
	/>

	<%
	String editLayoutURL = layoutsAdminDisplayContext.getEditLayoutURL(curLayout);
	%>

	<c:if test="<%= Validator.isNotNull(editLayoutURL) %>">
		<liferay-ui:icon
			message="edit"
			url="<%= editLayoutURL %>"
		/>
	</c:if>

	<c:if test="<%= layoutsAdminDisplayContext.isShowConfigureAction(curLayout) %>">
		<liferay-ui:icon
			message="configure"
			url="<%= layoutsAdminDisplayContext.getConfigureLayoutURL(curLayout) %>"
		/>
	</c:if>

	<c:if test="<%= layoutsAdminDisplayContext.isShowAddChildPageAction(curLayout) %>">
		<liferay-ui:icon
			message="add-child-page"
			url="<%= layoutsAdminDisplayContext.getSelectLayoutPageTemplateEntryURL(layoutsAdminDisplayContext.getFirstLayoutPageTemplateCollectionId(), curLayout.getPlid(), curLayout.isPrivateLayout()) %>"
		/>
	</c:if>

	<c:if test="<%= layoutsAdminDisplayContext.isShowPreviewDraftAction(curLayout) %>">
		<liferay-ui:icon
			linkCssClass="align-items-center d-flex justify-content-between"
			markupView="lexicon"
			message="preview-draft"
			target="_blank"
			url="<%= layoutsAdminDisplayContext.getPreviewDraftURL(curLayout) %>"
		/>
	</c:if>

	<c:if test="<%= layoutsAdminDisplayContext.isShowApproveDraftAction(curLayout) %>">
		<liferay-ui:icon
			message="approve-draft"
			url="<%= layoutsAdminDisplayContext.getApproveDraftURL(curLayout) %>"
		/>
	</c:if>

	<c:if test="<%= layoutsAdminDisplayContext.isShowCopyLayoutAction(curLayout) %>">
		<liferay-ui:icon
			cssClass='<%= liferayPortletResponse.getNamespace() + "copy-layout-action-option" %>'
			message="copy-page"
			url="javascript:;"
		/>
	</c:if>

	<c:if test="<%= layoutsAdminDisplayContext.isShowPermissionsAction(curLayout) %>">
		<liferay-ui:icon
			message="permissions"
			method="get"
			url="<%= layoutsAdminDisplayContext.getPermissionsURL(curLayout) %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= layoutsAdminDisplayContext.isShowOrphanPortletsAction(curLayout) %>">
		<liferay-ui:icon
			message="orphan-widgets"
			url="<%= layoutsAdminDisplayContext.getOrphanPortletsURL(curLayout) %>"
		/>
	</c:if>

	<c:if test="<%= layoutsAdminDisplayContext.isShowConvertLayoutAction(curLayout) %>">
		<liferay-ui:icon
			message="convert-to-content-page"
			url="<%= layoutsAdminDisplayContext.getConvertLayoutURL(curLayout) %>"
		/>
	</c:if>

	<c:if test="<%= layoutsAdminDisplayContext.isShowDeleteAction(curLayout) %>">

		<%
		String messageKey = "are-you-sure-you-want-to-delete-this-page";

		if (curLayout.hasChildren() && _hasScopeGroup(curLayout)) {
			messageKey = "this-page-is-being-used-as-a-scope-for-content-and-also-has-child-pages";
		}
		else if (curLayout.hasChildren()) {
			messageKey = "this-page-has-child-pages-that-will-also-be-removed";
		}
		else if (_hasScopeGroup(curLayout)) {
			messageKey = "this-page-is-being-used-as-a-scope-for-content";
		}
		%>

		<liferay-ui:icon-delete
			confirmation="<%= messageKey %>"
			url="<%= layoutsAdminDisplayContext.getDeleteLayoutURL(curLayout) %>"
		/>
	</c:if>
</liferay-ui:icon-menu>

<aui:script require="metal-dom/src/all/dom as dom,frontend-js-web/liferay/modal/commands/OpenSimpleInputModal.es as modalCommands">
	var copyLayoutActionOptionQueryClickHandler = dom.delegate(
		document.body,
		'click',
		'.<portlet:namespace />copy-layout-action-option',
		function(event) {
			Liferay.Util.openWindow({
				dialog: {
					destroyOnHide: true,
					height: 480,
					resizable: false,
					width: 640
				},
				dialogIframe: {
					bodyCssClass: 'dialog-with-footer'
				},
				id: '<portlet:namespace />addLayoutDialog',
				title: '<liferay-ui:message key="copy-page" />',
				uri:
					'<%= layoutsAdminDisplayContext.getCopyLayoutRenderURL(curLayout) %>'
			});
		}
	);

	function handleDestroyPortlet() {
		copyLayoutActionOptionQueryClickHandler.removeListener();

		Liferay.detach('destroyPortlet', handleDestroyPortlet);
	}

	Liferay.on('destroyPortlet', handleDestroyPortlet);
</aui:script>

<%!
private boolean _hasScopeGroup(Layout layout) throws Exception {
	if (layout.hasScopeGroup()) {
		return true;
	}

	Layout draftLayout = LayoutLocalServiceUtil.fetchLayout(
		PortalUtil.getClassNameId(Layout.class), layout.getPlid());

	if (draftLayout == null) {
		return false;
	}

	return draftLayout.hasScopeGroup();
}
%>