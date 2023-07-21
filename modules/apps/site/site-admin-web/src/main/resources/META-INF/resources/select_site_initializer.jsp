<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
SelectSiteInitializerDisplayContext selectSiteInitializerDisplayContext = new SelectSiteInitializerDisplayContext(request, renderRequest, renderResponse);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(selectSiteInitializerDisplayContext.getBackURL());

renderResponse.setTitle(LanguageUtil.get(request, "select-site-template"));
%>

<aui:form cssClass="container-fluid-1280" name="fm">
	<liferay-ui:search-container
		searchContainer="<%= selectSiteInitializerDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.site.admin.web.internal.util.SiteInitializerItem"
			keyProperty="key"
			modelVar="siteInitializerItem"
		>

			<%
			row.setCssClass("entry-card lfr-asset-item " + row.getCssClass());
			%>

			<liferay-ui:search-container-column-text>
				<button class="add-site-action-button btn btn-unstyled mb-4 w-100" type="button">
					<clay:vertical-card
						elementClasses="add-site-action-card mb-0"
						verticalCard="<%= new SelectSiteInitializerVerticalCard(siteInitializerItem, renderRequest, renderResponse) %>"
					/>
				</button>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="icon"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>

	<portlet:actionURL name="addGroup" var="addSiteURL">
		<portlet:param name="mvcPath" value="/select_layout_set_prototype_entry.jsp" />
		<portlet:param name="parentGroupId" value="<%= String.valueOf(selectSiteInitializerDisplayContext.getParentGroupId()) %>" />
	</portlet:actionURL>

	<aui:script require="metal-dom/src/all/dom as dom,frontend-js-web/liferay/modal/commands/OpenSimpleInputModal.es as modalCommands">
		var addSiteActionOptionQueryClickHandler = dom.delegate(
			document.body,
			'click',
			'.add-site-action-button',
			function(event) {
				var data = event.delegateTarget.querySelector('.add-site-action-card')
					.dataset;

				modalCommands.openSimpleInputModal({
					checkboxFieldLabel:
						'<liferay-ui:message key="create-default-pages-as-private-available-only-to-members-if-unchecked-they-will-be-public-available-to-anyone" />',
					checkboxFieldName: data.checkboxFieldName,
					checkboxFieldValue: false,
					dialogTitle: '<liferay-ui:message key="add-site" />',
					formSubmitURL: data.addSiteUrl,
					idFieldName: 'layoutSetPrototypeId',
					idFieldValue: data.layoutSetPrototypeId,
					mainFieldName: 'name',
					mainFieldLabel: '<liferay-ui:message key="name" />',
					namespace: '<portlet:namespace />',
					spritemap:
						'<%= themeDisplay.getPathThemeImages() %>/lexicon/icons.svg'
				});
			}
		);

		function handleDestroyPortlet() {
			addSiteActionOptionQueryClickHandler.removeListener();

			Liferay.detach('destroyPortlet', handleDestroyPortlet);
		}

		Liferay.on('destroyPortlet', handleDestroyPortlet);
	</aui:script>
</aui:form>