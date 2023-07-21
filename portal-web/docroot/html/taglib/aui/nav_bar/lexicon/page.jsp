<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/aui/nav_bar/init.jsp" %>

<c:if test="<%= Validator.isContent(bodyContentString) %>">
	<div class="navbar navbar-default <%= cssClass %>" id="<%= id %>" <%= AUIUtil.buildData(data) %> <%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %>>
		<div class="container-fluid-1280">
			<c:if test="<%= Validator.isNotNull(dataTarget) %>">
				<div class="navbar-header visible-xs">
					<button class="<%= (navItemCount.getValue() > 1) ? "collapsed" : StringPool.BLANK %> navbar-toggle navbar-toggle-left navbar-toggle-page-name" data-target="<%= (navItemCount.getValue() > 1) ? "#" + dataTarget + "NavbarCollapse" : StringPool.BLANK %>" data-toggle="<%= (navItemCount.getValue() > 1) ? "collapse" : StringPool.BLANK %>" id="<%= namespace %>NavbarBtn" type="button">
						<span class="sr-only"><liferay-ui:message key="toggle-navigation" /></span>

						<span class="page-name"><%= LanguageUtil.get(resourceBundle, selectedItemName) %></span>

						<c:if test="<%= navItemCount.getValue() > 1 %>">
							<span class="caret"></span>
						</c:if>
					</button>
				</div>
			</c:if>

			<%= bodyContentString %>
		</div>
	</div>
</c:if>