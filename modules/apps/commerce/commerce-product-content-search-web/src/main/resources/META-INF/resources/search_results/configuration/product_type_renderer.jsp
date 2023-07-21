<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPSearchResultsDisplayContext cpSearchResultsDisplayContext = (CPSearchResultsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<div id="<portlet:namespace />configuration-tabs">
	<ul class="nav nav-tabs">

		<%
		for (CPType cpType : cpSearchResultsDisplayContext.getCPTypes()) {
		%>

			<li>
				<a href="#<%= cpType.getName() %>"><%= HtmlUtil.escape(cpType.getLabel(locale)) %></a>
			</li>

		<%
		}
		%>

	</ul>

	<div class="tab-content">

		<%
		for (CPType cpType : cpSearchResultsDisplayContext.getCPTypes()) {
		%>

			<div id="<%= cpType.getName() %>">
				<aui:fieldset markupView="lexicon">
					<aui:select label='<%= HtmlUtil.escape(cpType.getLabel(locale)) + StringPool.SPACE + LanguageUtil.get(request, "cp-type-list-entry-renderer-key") %>' name='<%= "preferences--" + cpType.getName() + "--cpTypeListEntryRendererKey--" %>'>

						<%
						List<CPContentListEntryRenderer> cpContentListEntryRenderers = cpSearchResultsDisplayContext.getCPContentListEntryRenderers(cpType.getName());

						for (CPContentListEntryRenderer cpContentListEntryRenderer : cpContentListEntryRenderers) {
							String key = cpContentListEntryRenderer.getKey();
						%>

							<aui:option label="<%= HtmlUtil.escape(cpContentListEntryRenderer.getLabel(locale)) %>" selected="<%= key.equals(cpSearchResultsDisplayContext.getCPTypeListEntryRendererKey(cpType.getName())) %>" value="<%= key %>" />

						<%
						}
						%>

					</aui:select>
				</aui:fieldset>
			</div>

		<%
		}
		%>

	</div>
</div>

<aui:script use="aui-tabview">
	new A.TabView({
		srcNode: '#<portlet:namespace />configuration-tabs'
	}).render();
</aui:script>