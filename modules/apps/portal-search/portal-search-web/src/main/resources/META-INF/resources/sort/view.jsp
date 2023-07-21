<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.search.web.internal.sort.display.context.SortDisplayContext" %><%@
page import="com.liferay.portal.search.web.internal.sort.display.context.SortTermDisplayContext" %>

<liferay-theme:defineObjects />

<%
SortDisplayContext sortDisplayContext = (SortDisplayContext)java.util.Objects.requireNonNull(request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT));
%>

<c:choose>
	<c:when test="<%= sortDisplayContext.isRenderNothing() %>">
		<div class="alert alert-info text-center">
			<liferay-ui:message key="this-widget-is-not-visible-to-users-yet" />

			<aui:a href="javascript:;" onClick="<%= portletDisplay.getURLConfigurationJS() %>"><liferay-ui:message key="complete-its-configuration-to-make-it-visible" /></aui:a>
		</div>
	</c:when>
	<c:otherwise>
		<aui:form action="#" method="post" name="fm">
			<aui:fieldset>
				<aui:input cssClass="sort-parameter-name" name="sort-parameter-name" type="hidden" value="<%= sortDisplayContext.getParameterName() %>" />

				<aui:select class="sort-term" label="sort-by" name="sortSelection">
					<c:if test="<%= !sortDisplayContext.isAnySelected() %>">
						<aui:option disabled="<%= true %>" label="sort-default-order" selected="<%= true %>" />
					</c:if>

					<%
					for (SortTermDisplayContext sortTermDisplayContext : sortDisplayContext.getSortTermDisplayContexts()) {
					%>

						<aui:option label="<%= HtmlUtil.escapeAttribute(sortTermDisplayContext.getLabel()) %>" selected="<%= sortTermDisplayContext.isSelected() %>" value="<%= HtmlUtil.escapeAttribute(sortTermDisplayContext.getField()) %>" />

					<%
					}
					%>

				</aui:select>
			</aui:fieldset>
		</aui:form>
	</c:otherwise>
</c:choose>

<aui:script use="liferay-search-sort-util">
AUI().ready('aui-base', 'node', 'event', function(A) {
	A.one('#<portlet:namespace />sortSelection').on('change', function() {
		var selections = [];

		var sortSelect = A.one('#<portlet:namespace />sortSelection').get(
			'value'
		);

		selections.push(sortSelect);

		var key = A.one('#<portlet:namespace />sort-parameter-name').get(
			'value'
		);

		document.location.search = Liferay.Search.SortUtil.updateQueryString(
			key,
			selections,
			document.location.search
		);
	});
});
</aui:script>