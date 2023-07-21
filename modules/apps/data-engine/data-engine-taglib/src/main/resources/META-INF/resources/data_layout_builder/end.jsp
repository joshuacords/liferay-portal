<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/data_layout_builder/init.jsp" %>

<div id="<%= componentId %>container"></div>

<aui:script require='<%= fieldTypesModules + ", " + dataLayoutBuilderModule + " as DataLayoutBuilder" %>'>
	Liferay.component(
		'<%= componentId %>',
		new DataLayoutBuilder.default(
			{
				componentId: '<%= componentId %>',
				context: <%= dataLayoutJSONObject %>,
				dataDefinitionInputId: '<%= namespace + dataDefinitionInputId %>',
				dataLayoutInputId: '<%= namespace + dataLayoutInputId %>',
				editingLanguageId: '<%= themeDisplay.getLanguageId() %>',
				fieldTypes: <%= fieldTypesJSONArray %>,
				localizable: <%= localizable %>,
				portletNamespace: '<%= namespace %>',
				spritemap:
					'<%= themeDisplay.getPathThemeImages() %>/lexicon/icons.svg'
			},
			'#<%= componentId %>container'
		)
	);

	var clearPortletHandlers = function(event) {
		if (event.portletId === '<%= portletDisplay.getRootPortletId() %>') {
			Liferay.destroyComponent('<%= componentId %>');

			Liferay.detach('destroyPortlet', clearPortletHandlers);
		}
	};

	Liferay.on('destroyPortlet', clearPortletHandlers);
</aui:script>