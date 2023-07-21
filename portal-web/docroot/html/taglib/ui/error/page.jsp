<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%
String alertIcon = (String)request.getAttribute("liferay-ui:error:alertIcon");
String alertMessage = (String)request.getAttribute("liferay-ui:error:alertMessage");
String alertStyle = (String)request.getAttribute("liferay-ui:error:alertStyle");
String alertTitle = (String)request.getAttribute("liferay-ui:error:alertTitle");
%>

<c:choose>
	<c:when test='<%= GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:error:embed")) %>'>
		<div class="alert alert-dismissible alert-<%= alertStyle %>" role="alert">
			<button aria-label="<%= LanguageUtil.get(request, "close") %>" class="close" data-dismiss="alert" type="button">
				<aui:icon image="times" markupView="lexicon" />

				<span class="sr-only"><%= LanguageUtil.get(request, "close") %></span>
			</button>

			<span class="alert-indicator">
				<svg aria-hidden="true" class="lexicon-icon lexicon-icon-<%= alertIcon %>">
					<use xlink:href="<%= themeDisplay.getPathThemeImages() %>/lexicon/icons.svg#<%= alertIcon %>"></use>
				</svg>
			</span>

			<strong class="lead"><%= alertTitle %></strong><%= alertMessage %>
		</div>

		<%= (String)request.getAttribute("liferay-ui:error:rowBreak") %>
	</c:when>
	<c:otherwise>
		<aui:script require="metal-dom/src/all/dom as dom,clay-alert/src/ClayToast as ClayToast">
			let alertContainer = document.getElementById('alertContainer');

			if (!alertContainer) {
				alertContainer = document.createElement('div');
				alertContainer.id = 'alertContainer';

				dom.addClasses(alertContainer, 'alert-notifications alert-notifications-fixed');
				dom.enterDocument(alertContainer);
			}
			else {
				dom.removeChildren(alertContainer);
			}

			const clayToast = new ClayToast.default(
				{
					autoClose: true,
					destroyOnHide: true,
					events: {
						'disposed': function(event) {
							if (!alertContainer.hasChildNodes()) {
								dom.exitDocument(alertContainer);
							}
						}
					},
					message: '<%= HtmlUtil.escapeJS(alertMessage) %>',
					spritemap: '<%= themeDisplay.getPathThemeImages() %>/lexicon/icons.svg',
					style: '<%= alertStyle %>',
					title: '<%= alertTitle %>'
				},
				alertContainer
			);

			dom.removeClasses(clayToast.element, 'show');

			requestAnimationFrame(
				function() {
					dom.addClasses(clayToast.element, 'show');
				}
			);
		</aui:script>
	</c:otherwise>
</c:choose>