<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
int deltaDefault = GetterUtil.getInteger(SessionClicks.get(request, "com.liferay.product.navigation.control.menu.web_addPanelNumItems", "10"));

int delta = ParamUtil.getInteger(request, "delta", deltaDefault);

String displayStyleDefault = GetterUtil.getString(SessionClicks.get(request, "com.liferay.product.navigation.control.menu.web_addPanelDisplayStyle", "descriptive"));

String displayStyle = ParamUtil.getString(request, "displayStyle", displayStyleDefault);
%>

<portlet:resourceURL var="updateContentListURL">
	<portlet:param name="mvcPath" value="/view_resources.jsp" />
</portlet:resourceURL>

<aui:form action="<%= updateContentListURL %>" name="addContentForm" onSubmit="event.preventDefault();">
	<div class="input-group search-bar">
		<input aria-label="<%= LanguageUtil.get(request, "search") %>" class="form-control" id="<portlet:namespace />searchContent" name="<portlet:namespace />searchContent" placeholder="<%= LanguageUtil.get(request, "search") + StringPool.TRIPLE_PERIOD %>" type="text" />

		<span class="input-group-btn">
			<liferay-ui:icon
				icon="search"
				markupView="lexicon"
			/>
		</span>
	</div>

	<div id="<portlet:namespace />entriesContainer">
		<liferay-util:include page="/view_resources.jsp" servletContext="<%= application %>" />
	</div>
</aui:form>

<aui:script use="liferay-product-navigation-control-menu-add-content">
	var ControlMenu = Liferay.ControlMenu;

	var addContentCollapse = A.one('#<portlet:namespace />addContentCollapse');
	var searchContent = A.one('#<portlet:namespace />searchContent');

	if (addContentCollapse && searchContent) {
		var addContent = new ControlMenu.AddContent({
			delta: '<%= delta %>',
			displayStyle: '<%= HtmlUtil.escapeJS(displayStyle) %>',
			focusItem: searchContent,
			inputNode: searchContent,
			namespace: '<portlet:namespace />',
			panelBody: addContentCollapse
		});

		if (ControlMenu.PortletDragDrop) {
			addContent.plug(ControlMenu.PortletDragDrop, {
				on: {
					dragEnd: function(event) {
						addContent.addPortlet(event.portletNode, {
							item: event.appendNode
						});
					}
				},
				srcNode: '#<portlet:namespace />entriesContainer'
			});
		}

		Liferay.component('<portlet:namespace />addContent', addContent);
	}
</aui:script>