<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/form_navigator/init.jsp" %>

<%
String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_form_navigator_init") + StringPool.UNDERLINE;

String tabs1Param = randomNamespace + "tabs1";
%>

<c:choose>
	<c:when test="<%= categoryKeys.length > 1 %>">
		<liferay-ui:tabs
			names="<%= StringUtil.merge(categoryKeys, StringPool.COMMA) %>"
			param="<%= tabs1Param %>"
			refresh="<%= false %>"
			value="<%= GetterUtil.getString(SessionClicks.get(request, namespace + id, null)) %>"
		>

			<%
			for (String categoryKey : categoryKeys) {
				request.setAttribute(FormNavigatorWebKeys.CURRENT_TAB, categoryKey);
				request.setAttribute(FormNavigatorWebKeys.FORM_NAVIGATOR_ENTRIES, FormNavigatorEntryUtil.getFormNavigatorEntries(id, categoryKey, user, formModelBean));
			%>

				<liferay-ui:section>
					<liferay-util:include page="/form_navigator/sections.jsp" servletContext="<%= application %>" />
				</liferay-ui:section>

			<%
			}

			String errorTab = (String)request.getAttribute(FormNavigatorWebKeys.ERROR_TAB);

			if (Validator.isNotNull(errorTab)) {
				request.setAttribute(WebKeys.ERROR_SECTION, errorTab);
			}
			%>

		</liferay-ui:tabs>
	</c:when>
	<c:otherwise>

		<%
		request.setAttribute(FormNavigatorWebKeys.FORM_NAVIGATOR_ENTRIES, FormNavigatorEntryUtil.getFormNavigatorEntries(id, user, formModelBean));
		%>

		<liferay-util:include page="/form_navigator/sections.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>

<c:if test="<%= showButtons %>">
	<div>
		<aui:button primary="<%= true %>" type="submit" />

		<aui:button href="<%= backURL %>" type="cancel" />
	</div>
</c:if>

<aui:script require="metal-dom/src/dom as dom">
	var redirectField = dom.toElement(
		'input[name="<portlet:namespace />redirect"]'
	);
	var tabs1Param = '<portlet:namespace /><%= tabs1Param %>';

	var updateRedirectField = function(event) {
		var redirectURL = new URL(redirectField.value, window.location.origin);

		redirectURL.searchParams.set(tabs1Param, event.id);

		redirectField.value = redirectURL.toString();

		Liferay.Util.Session.set('<portlet:namespace /><%= id %>', event.id);
	};

	var clearFormNavigatorHandles = function(event) {
		if (event.portletId === '<%= portletDisplay.getRootPortletId() %>') {
			Liferay.detach('showTab', updateRedirectField);
			Liferay.detach('destroyPortlet', clearFormNavigatorHandles);
		}
	};

	if (redirectField) {
		var currentURL = new URL(document.location.href);

		var tabs1Value = currentURL.searchParams.get(tabs1Param);

		if (tabs1Value) {
			updateRedirectField({
				id: tabs1Value
			});
		}

		Liferay.on('showTab', updateRedirectField);
		Liferay.on('destroyPortlet', clearFormNavigatorHandles);
	}
</aui:script>