<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/aui/fieldset/init.jsp" %>

		</div>
	</div>
</fieldset>

<c:if test="<%= collapsible %>">
	<aui:script sandbox="<%= true %>" use="aui-base,liferay-store">
		var storeTask = A.debounce(Liferay.Store, 100);

		$('#<%= id %>Content').on(
			'hide.bs.collapse show.bs.collapse',
			function(event) {
				if (event.target.id === '<%= id %>Content') {
					var task = {};

					task['<%= id %>'] = event.type === 'hide';

					storeTask(task);
				}
			}
		);
	</aui:script>
</c:if>