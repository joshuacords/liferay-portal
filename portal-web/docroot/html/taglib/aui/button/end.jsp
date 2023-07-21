<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/aui/button/init.jsp" %>

<c:if test="<%= dropdown %>">
	<div class="btn-group dropdown" id="<%= id %>BtnGroup">
</c:if>

<c:choose>
	<c:when test="<%= Validator.isNotNull(escapedHREF) %>">
		<a
			class="<%= AUIUtil.buildCss(AUIUtil.BUTTON_PREFIX, disabled, false, false, cssClass) %> <%= type.equals("cancel") ? "btn-link" : "btn-default" %>"
			href="<%= escapedHREF %>"
			id="<%= id %>"

			<c:if test="<%= Validator.isNotNull(onClick) %>">
				onClick="<%= onClick %>"
			</c:if>

			<%= AUIUtil.buildData(data) %>
			<%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %>
		>
	</c:when>
	<c:otherwise>
		<button
			class="<%= AUIUtil.buildCss(AUIUtil.BUTTON_PREFIX, disabled, false, false, cssClass) %> <%= type.equals("cancel") ? "btn-link" : "btn-default" %>"

			<c:if test="<%= disabled %>">
				disabled
			</c:if>

			id="<%= id %>"

			<c:if test="<%= Validator.isNotNull(name) %>">
				name="<%= namespace %><%= name %>"
			</c:if>

			<c:if test="<%= Validator.isNotNull(onClick) %>">
				onClick="<%= onClick %>"
			</c:if>

			type="<%= type.equals("cancel") ? "button" : type %>"

			<%= AUIUtil.buildData(data) %>
			<%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %>
		>
	</c:otherwise>
</c:choose>

<c:if test='<%= Validator.isNotNull(icon) && iconAlign.equals("left") %>'>
	<i class="<%= icon %>"></i>
</c:if>

<c:if test="<%= Validator.isNotNull(value) %>">
	<span class="lfr-btn-label"><%= LanguageUtil.get(resourceBundle, value) %></span>
</c:if>

<c:if test='<%= Validator.isNotNull(icon) && iconAlign.equals("right") %>'>
	<i class="<%= icon %>"></i>
</c:if>

<c:choose>
	<c:when test="<%= Validator.isNotNull(escapedHREF) %>">
		</a>
	</c:when>
	<c:otherwise>
		</button>
	</c:otherwise>
</c:choose>

<c:if test="<%= dropdown %>">
	<button aria-expanded="false" class="btn btn-primary dropdown-toggle <%= cssClass %>" data-toggle="dropdown" <%= disabled ? "disabled" : StringPool.BLANK %> id="<%= id %>Toggle" type="button">
		<span class="caret"></span>

		<span class="sr-only"><liferay-ui:message key="toggle-dropdown" /></span>
	</button>
</c:if>

<c:if test="<%= dropdown %>">
		<ul class="dropdown-menu" role="menu">
			<%= bodyContentString %>
		</ul>
	</div>
</c:if>

<c:if test="<%= useDialog %>">
	<aui:script>
		Liferay.delegateClick(
			'<%= namespace + name %>',
			function(event) {
				Liferay.Util.openInDialog(
					event,
					{
						dialogIframe: {
							bodyCssClass: 'dialog-with-footer'
						}
					}
				);
			}
		);
	</aui:script>
</c:if>