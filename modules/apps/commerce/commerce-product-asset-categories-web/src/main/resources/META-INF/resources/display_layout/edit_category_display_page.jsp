<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CategoryCPDisplayLayoutDisplayContext categoryCPDisplayLayoutDisplayContext = (CategoryCPDisplayLayoutDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceChannel commerceChannel = categoryCPDisplayLayoutDisplayContext.getCommerceChannel();
CPDisplayLayout cpDisplayLayout = categoryCPDisplayLayoutDisplayContext.getCPDisplayLayout();

long[] assetCategoryIds = new long[0];

if (cpDisplayLayout != null) {
	assetCategoryIds = ArrayUtil.append(assetCategoryIds, cpDisplayLayout.getClassPK());
}

String layoutBreadcrumb = StringPool.BLANK;

if (cpDisplayLayout != null) {
	Layout selLayout = LayoutLocalServiceUtil.fetchLayoutByUuidAndGroupId(cpDisplayLayout.getLayoutUuid(), commerceChannel.getSiteGroupId(), false);

	if (selLayout == null) {
		selLayout = LayoutLocalServiceUtil.fetchLayoutByUuidAndGroupId(cpDisplayLayout.getLayoutUuid(), commerceChannel.getSiteGroupId(), true);
	}

	if (selLayout != null) {
		layoutBreadcrumb = categoryCPDisplayLayoutDisplayContext.getLayoutBreadcrumb(selLayout);
	}
}
%>

<commerce-ui:side-panel-content
	title='<%= (cpDisplayLayout == null) ? LanguageUtil.get(request, "add-display-layout") : LanguageUtil.get(request, "edit-display-layout") %>'
>
	<portlet:actionURL name="editCategoryDisplayLayout" var="editCategoryDisplayPageActionURL" />

	<aui:form action="<%= editCategoryDisplayPageActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (cpDisplayLayout == null) ? Constants.ADD : Constants.UPDATE %>" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="classPK" type="hidden" value="<%= (cpDisplayLayout == null) ? 0 : cpDisplayLayout.getClassPK() %>" />
		<aui:input name="commerceChannelId" type="hidden" value="<%= categoryCPDisplayLayoutDisplayContext.getCommerceChannelId() %>" />

		<liferay-ui:error exception="<%= CPDisplayLayoutEntryException.class %>" message="please-select-a-valid-category" />
		<liferay-ui:error exception="<%= CPDisplayLayoutLayoutUuidException.class %>" message="please-select-a-valid-layout" />

		<aui:model-context bean="<%= cpDisplayLayout %>" model="<%= CPDisplayLayout.class %>" />

		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<liferay-asset:asset-categories-error />

				<h4><liferay-ui:message key="select-categories" /></h4>

				<liferay-asset:asset-categories-selector
					categoryIds="<%= StringUtil.merge(assetCategoryIds, StringPool.COMMA) %>"
					hiddenInput="classPK"
					singleSelect="<%= true %>"
				/>

				<aui:input id="pagesContainerInput" ignoreRequestValue="<%= true %>" name="layoutUuid" type="hidden" value="<%= (cpDisplayLayout == null) ? StringPool.BLANK : cpDisplayLayout.getLayoutUuid() %>" />

				<aui:field-wrapper helpMessage="category-display-page-help" label="category-display-page">
					<p class="text-default">
						<span class="<%= Validator.isNull(layoutBreadcrumb) ? "hide" : StringPool.BLANK %>" id="<portlet:namespace />displayPageItemRemove" role="button">
							<aui:icon cssClass="icon-monospaced" image="times" markupView="lexicon" />
						</span>
						<span id="<portlet:namespace />displayPageNameInput">
							<c:choose>
								<c:when test="<%= Validator.isNull(layoutBreadcrumb) %>">
									<span class="text-muted"><liferay-ui:message key="none" /></span>
								</c:when>
								<c:otherwise>
									<%= layoutBreadcrumb %>
								</c:otherwise>
							</c:choose>
						</span>
					</p>
				</aui:field-wrapper>

				<aui:button-row>
					<aui:button name="chooseDisplayPage" value="choose" />
				</aui:button-row>
			</aui:fieldset>
		</aui:fieldset-group>

		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" />
		</aui:button-row>
	</aui:form>
</commerce-ui:side-panel-content>

<aui:script use="liferay-item-selector-dialog">
	var displayPageItemContainer = $(
		'#<portlet:namespace />displayPageItemContainer'
	);
	var displayPageItemRemove = $('#<portlet:namespace />displayPageItemRemove');
	var displayPageNameInput = $('#<portlet:namespace />displayPageNameInput');
	var pagesContainerInput = $('#<portlet:namespace />pagesContainerInput');

	$('#<portlet:namespace />chooseDisplayPage').on('click', function(event) {
		var itemSelectorDialog = new A.LiferayItemSelectorDialog({
			eventName: 'selectDisplayPage',
			on: {
				selectedItemChange: function(event) {
					var selectedItem = event.newVal;

					if (selectedItem) {
						pagesContainerInput.val(selectedItem.id);

						displayPageNameInput.innerText = selectedItem.name;

						displayPageItemRemove.removeClass('hide');
					}
				}
			},
			'strings.add': '<liferay-ui:message key="done" />',
			title: '<liferay-ui:message key="select-category-display-page" />',
			url:
				'<%= categoryCPDisplayLayoutDisplayContext.getItemSelectorUrl(renderRequest) %>'
		});

		itemSelectorDialog.open();
	});

	displayPageItemRemove.on('click', function(event) {
		displayPageNameInput.innerText = '<liferay-ui:message key="none" />';

		pagesContainerInput.val('');

		displayPageItemRemove.addClass('hide');
	});
</aui:script>