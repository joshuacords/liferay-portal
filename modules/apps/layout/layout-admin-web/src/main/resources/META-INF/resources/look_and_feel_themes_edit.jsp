<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Layout selLayout = layoutsAdminDisplayContext.getSelLayout();
LayoutSet selLayoutSet = layoutsAdminDisplayContext.getSelLayoutSet();

Theme selTheme = null;

if (selLayout != null) {
	selTheme = selLayout.getTheme();
}
else {
	selTheme = selLayoutSet.getTheme();
}
%>

<h1 class="h4 text-default"><liferay-ui:message key="current-theme" /></h1>

<div class="card-horizontal main-content-card">
	<div class="card-row card-row-padded">
		<div id="<portlet:namespace />themeContainer">
			<liferay-util:include page="/look_and_feel_theme_details.jsp" servletContext="<%= application %>" />
		</div>

		<aui:input label="insert-custom-css-that-is-loaded-after-the-theme" name="regularCss" placeholder="css" type="textarea" value="<%= (selLayout != null) ? selLayout.getCssText() : selLayoutSet.getCss() %>" />
	</div>
</div>

<aui:button cssClass="btn btn-default" id="changeTheme" value="change-current-theme" />

<aui:script use="aui-parse-content">
	var Util = Liferay.Util;

	var selThemeId = '<%= selTheme.getThemeId() %>';

	var themeContainer = A.one('#<portlet:namespace />themeContainer');

	<portlet:renderURL var="selectThemeURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="mvcPath" value="/select_theme.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
	</portlet:renderURL>

	A.one('#<portlet:namespace />changeTheme').on('click', function(event) {
		event.preventDefault();

		Util.selectEntity(
			{
				dialog: {
					constrain: true,
					destroyOnHide: true,
					modal: true
				},
				eventName: '<portlet:namespace />selectTheme',
				title: '<liferay-ui:message key="available-themes" />',
				uri: Util.addParams(
					'<portlet:namespace />themeId=' + selThemeId,
					'<%= selectThemeURL %>'
				)
			},
			function(event) {
				var selectedItem = event.themeid;

				if (selectedItem && selThemeId != selectedItem) {
					themeContainer.html('<div class="loading-animation"></div>');

					var data = Util.ns('<portlet:namespace />', {
						themeId: selectedItem
					});

					Liferay.Util.fetch(
						'<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/look_and_feel_theme_details.jsp" /></portlet:renderURL>',
						{
							body: Liferay.Util.objectToFormData(data),
							method: 'POST'
						}
					)
						.then(function(response) {
							return response.text();
						})
						.then(function(responseData) {
							themeContainer.plug(A.Plugin.ParseContent);

							themeContainer.setContent(responseData);

							var newCheckboxNames = [];

							var checkboxInputs = themeContainer.all(
								'input[type=checkbox]'
							);

							checkboxInputs.each(function(item, index) {
								var checkboxName = item.attr('name');
								newCheckboxNames.push(
									checkboxName.substring(
										'<portlet:namespace />'.length
									)
								);
							});

							document.querySelector(
								'#<portlet:namespace />checkboxNames'
							).value = newCheckboxNames.join(',');

							selThemeId = selectedItem;
						});
				}
			}
		);
	});
</aui:script>