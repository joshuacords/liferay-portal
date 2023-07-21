<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/aui/fieldset/init.jsp" %>

<%
if (Validator.isNull(label)) {
	collapsible = false;
	collapsed = false;
}
else if (collapsible) {
	boolean defaultState = collapsed;

	collapsed = GetterUtil.getBoolean(SessionClicks.get(request, id, null), defaultState);
}
%>

<fieldset class="<%= collapsible ? "panel panel-default" : StringPool.BLANK %> <%= cssClass %>" <%= Validator.isNotNull(id) ? "id=\"" + id + "\"" : StringPool.BLANK %> <%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %>>
	<c:choose>
		<c:when test="<%= Validator.isNotNull(label) %>">
			<liferay-util:buffer
				var="header"
			>
				<liferay-ui:message key="<%= label %>" localizeKey="<%= localizeLabel %>" />

				<c:if test="<%= Validator.isNotNull(helpMessage) %>">
					<liferay-ui:icon-help message="<%= helpMessage %>" />
				</c:if>
			</liferay-util:buffer>

			<c:choose>
				<c:when test="<%= collapsible %>">
					<legend class="sr-only">
						<%= header %>
					</legend>

					<div class="panel-heading" id="<%= id %>Header" role="presentation">
						<div class="panel-title" id="<%= id %>Title">
							<a aria-controls="<%= id %>Content" aria-expanded="<%= !collapsed %>" class="collapse-icon collapse-icon-middle <%= collapsed ? "collapsed" : StringPool.BLANK %>" data-toggle="collapse" href="#<%= id %>Content" role="button">
								<span aria-hidden="true">
									<%= header %>
								</span>

								<aui:icon cssClass="collapse-icon-closed" image="angle-right" markupView="lexicon" />

								<aui:icon cssClass="collapse-icon-open" image="angle-down" markupView="lexicon" />
							</a>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<legend class="fieldset-legend">
						<div class="panel-heading" id="<%= id %>Header" role="presentation">
							<div class="panel-title" id="<%= id %>Title">
								<span class="legend">
									<%= header %>
								</span>
							</div>
						</div>
					</legend>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<legend class="sr-only">
				<%= HtmlUtil.escape(portletDisplay.getTitle()) %>
			</legend>
		</c:otherwise>
	</c:choose>

	<div aria-labelledby="<%= id %>Header" class="<%= !collapsed ? "in" : StringPool.BLANK %> <%= collapsible ? "panel-collapse collapse" : StringPool.BLANK %> <%= column ? "row" : StringPool.BLANK %>" id="<%= id %>Content" role="presentation">
		<div class="panel-body">