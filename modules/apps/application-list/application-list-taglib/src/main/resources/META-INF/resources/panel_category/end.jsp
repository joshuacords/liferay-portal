<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/panel_category/init.jsp" %>

<c:if test="<%= showBody %>">
	<liferay-application-list:panel-category-body
		panelApps="<%= panelApps %>"
		panelCategory="<%= panelCategory %>"
	/>
</c:if>

<c:if test="<%= !panelApps.isEmpty() && showHeader %>">
		</div>
	</div>

	<c:if test="<%= persistState %>">
		<aui:script position="auto">
			var collapse = $('#<%= id %>');

			collapse.on('hidden.bs.collapse', function(event) {
				Liferay.Util.Session.set(
					'<%= PanelCategory.class.getName() %><%= id %>',
					'closed'
				);
			});

			collapse.on('shown.bs.collapse', function(event) {
				Liferay.Util.Session.set(
					'<%= PanelCategory.class.getName() %><%= id %>',
					'open'
				);
			});
		</aui:script>
	</c:if>
</c:if>