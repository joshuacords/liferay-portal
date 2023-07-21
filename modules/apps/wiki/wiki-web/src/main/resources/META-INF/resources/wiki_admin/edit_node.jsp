<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/wiki/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

WikiNode node = (WikiNode)request.getAttribute(WikiWebKeys.WIKI_NODE);

long nodeId = BeanParamUtil.getLong(node, request, "nodeId");

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle((node == null) ? LanguageUtil.get(request, "new-wiki-node") : node.getName());
%>

<portlet:actionURL name="/wiki/edit_node" var="editNodeURL" />

<div class="container-fluid-1280">
	<aui:form action="<%= editNodeURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveNode();" %>'>
		<aui:input name="<%= Constants.CMD %>" type="hidden" />
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="nodeId" type="hidden" value="<%= nodeId %>" />

		<liferay-ui:error exception="<%= DuplicateNodeNameException.class %>" message="please-enter-a-unique-node-name" />
		<liferay-ui:error exception="<%= NodeNameException.class %>" message="please-enter-a-valid-name" />

		<aui:model-context bean="<%= node %>" model="<%= WikiNode.class %>" />

		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" name="name">
					<aui:validator errorMessage="please-enter-a-nonnumeric-name" name="custom">
						function(val) {
							return !/^\d+$/.test(val);
						}
					</aui:validator>
				</aui:input>

				<aui:input name="description" />
			</aui:fieldset>

			<c:if test="<%= node == null %>">
				<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="permissions">
					<liferay-ui:input-permissions
						modelName="<%= WikiNode.class.getName() %>"
					/>
				</aui:fieldset>
			</c:if>
		</aui:fieldset-group>

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<aui:script>
	function <portlet:namespace />saveNode() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value =
			'<%= (node == null) ? Constants.ADD : Constants.UPDATE %>';

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>

<%
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, (node == null) ? "add-wiki" : "edit"), currentURL);
%>