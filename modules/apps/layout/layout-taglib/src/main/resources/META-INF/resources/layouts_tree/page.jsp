<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/layouts_tree/init.jsp" %>

<%
String namespace = AUIUtil.getNamespace(liferayPortletRequest, liferayPortletResponse);

boolean draggableTree = GetterUtil.getBoolean((String)request.getAttribute("liferay-layout:layouts-tree:draggableTree"));
long groupId = GetterUtil.getLong((String)request.getAttribute("liferay-layout:layouts-tree:groupId"));
LayoutSetBranch layoutSetBranch = (LayoutSetBranch)request.getAttribute("liferay-layout:layouts-tree:layoutSetBranch");
String linkTemplate = (String)request.getAttribute("liferay-layout:layouts-tree:linkTemplate");
JSONArray portletURLsJSONArray = (JSONArray)request.getAttribute("liferay-layout:layouts-tree:portletURLsJSONArray");
boolean privateLayout = GetterUtil.getBoolean((String)request.getAttribute("liferay-layout:layouts-tree:privateLayout"));
String rootLinkTemplate = (String)request.getAttribute("liferay-layout:layouts-tree:rootLinkTemplate");
String rootNodeName = (String)request.getAttribute("liferay-layout:layouts-tree:rootNodeName");
Long selPlid = (Long)request.getAttribute("liferay-layout:layouts-tree:selPlid");
String treeId = (String)request.getAttribute("liferay-layout:layouts-tree:treeId");
%>

<aui:script position='<%= (String)request.getAttribute("liferay-layout:layouts-tree:scriptPosition") %>' use='<%= (String)request.getAttribute("liferay-layout:layouts-tree:modules") %>'>
	var plugins = [];

	<c:if test='<%= GetterUtil.getBoolean((String)request.getAttribute("liferay-layout:layouts-tree:selectableTree")) %>'>
		plugins.push({
			cfg: {
				default: <%= GetterUtil.getBoolean((String)request.getAttribute("liferay-layout:layouts-tree:defaultStateChecked")) %>
			},
			fn: A.Plugin.LayoutsTreeSelectable
		});
	</c:if>

	<c:if test='<%= GetterUtil.getBoolean((String)request.getAttribute("liferay-layout:layouts-tree:checkContentDisplayPage")) %>'>
		plugins.push(A.Plugin.LayoutsTreeCheckContentDisplayPage);
	</c:if>

	<c:if test='<%= GetterUtil.getBoolean((String)request.getAttribute("liferay-layout:layouts-tree:saveState")) %>'>
		plugins.push({
			cfg: {
				checkedNodes: <%= (String)request.getAttribute("liferay-layout:layouts-tree:checkedNodes") %>,
				rootNodeExpanded: <%= GetterUtil.getBoolean(SessionClicks.get(request, "com.liferay.frontend.js.web_" + treeId + "RootNode", null), true) %>
			},
			fn: A.Plugin.LayoutsTreeState
		});
	</c:if>

	var TreeViewType = Liferay.LayoutsTree;

	<c:if test="<%= draggableTree %>">
		TreeViewType = Liferay.LayoutsTreeDD;
	</c:if>

	var treeview = new TreeViewType({
		boundingBox: '#<portlet:namespace /><%= HtmlUtil.escape(treeId) %>Output',
		incomplete: <%= GetterUtil.getBoolean((String)request.getAttribute("liferay-layout:layouts-tree:incomplete")) %>,

		<%
		long[] openNodes = StringUtil.split(SessionTreeJSClicks.getOpenNodes(request, treeId), 0L);
		%>

		layouts: <%= LayoutsTreeUtil.getLayoutsJSON(request, groupId, privateLayout, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID, openNodes, true, treeId, layoutSetBranch) %>,

		<c:if test="<%= Validator.isNotNull(linkTemplate) %>">
			linkTemplate: '<%= HtmlUtil.escapeJS(linkTemplate) %>',
		</c:if>

		<c:if test="<%= draggableTree %>">
			lazyLoad: false,
		</c:if>

		maxChildren: <%= PropsValues.LAYOUT_MANAGE_PAGES_INITIAL_CHILDREN %>,
		on: {
			'*:select': function(event) {
				Liferay.fire('<%= namespace + treeId %>:selectedNode', {
					selectedNode: event.target
				});
			}
		},
		plugins: plugins,
		root: {
			defaultParentLayoutId: <%= LayoutConstants.DEFAULT_PARENT_LAYOUT_ID %>,
			expand: <%= GetterUtil.getBoolean((String)request.getAttribute("liferay-layout:layouts-tree:expandFirstNode")) %>,
			groupId: <%= groupId %>,
			label: '<%= HtmlUtil.escapeJS(rootNodeName) %>',

			<c:if test="<%= Validator.isNotNull(rootLinkTemplate) %>">
				linkTemplate: '<%= HtmlUtil.escapeJS(rootLinkTemplate) %>',
			</c:if>

			privateLayout: <%= privateLayout %>
		},

		<c:if test="<%= selPlid != null %>">
			selPlid: '<%= selPlid %>',
		</c:if>

		urls: <%= portletURLsJSONArray.toString() %>
	}).render();

	Liferay.once('screenLoad', function() {
		treeview.destroy();
	});
</aui:script>

<div class="lfr-tree" data-treeid="<%= HtmlUtil.escapeAttribute(treeId) %>" id="<portlet:namespace /><%= HtmlUtil.escape(treeId) %>Output"></div>