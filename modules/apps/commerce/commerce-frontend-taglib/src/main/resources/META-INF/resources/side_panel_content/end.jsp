<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/side_panel_content/init.jsp" %>

	<c:if test="<%= Validator.isNull(screenNavigatorKey) %>">
		</div>
	</c:if>

	<c:if test="<%= Validator.isNotNull(screenNavigatorKey) %>">
		<liferay-frontend:screen-navigation
			containerWrapperCssClass="side-panel-iframe-wrapper"
			fullContainerCssClass="col-12"
			headerContainerCssClass="side-panel-iframe-menu-wrapper"
			key="<%= screenNavigatorKey %>"
			modelBean="<%= screenNavigatorModelBean %>"
			portletURL="<%= screenNavigatorPortletURL %>"
		/>
	</c:if>
</div>

<aui:script require="commerce-frontend-js/utilities/eventsDefinitions as events">
	document.querySelector('body').classList.remove('open');

	document
		.querySelectorAll('.side-panel-iframe-close, .btn-cancel')
		.forEach(function(trigger) {
			trigger.addEventListener('click', function(e) {
				e.preventDefault();
				window.parent.Liferay.fire(events.CLOSE_SIDE_PANEL);
			});
		});
</aui:script>