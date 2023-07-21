<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Layout selLayout = (Layout)request.getAttribute(WebKeys.SEL_LAYOUT);
boolean setCustomName = GetterUtil.getBoolean(request.getAttribute(SiteNavigationMenuItemTypeLayoutWebKeys.SET_CUSTOM_NAME));
SiteNavigationMenuItem siteNavigationMenuItem = (SiteNavigationMenuItem)request.getAttribute(SiteNavigationWebKeys.SITE_NAVIGATION_MENU_ITEM);

String taglibOnChange = "Liferay.Util.toggleDisabled('#" + renderResponse.getNamespace() + "nameBoundingBox input, [for=" + renderResponse.getNamespace() + "name]', !event.target.checked)";
%>

<aui:fieldset>
	<aui:input checked="<%= setCustomName %>" helpMessage="set-custom-name-help" label="set-custom-name" name="TypeSettingsProperties--setCustomName--" onChange="<%= taglibOnChange %>" type="checkbox" />
</aui:fieldset>

<aui:input autoFocus="<%= true %>" disabled="<%= !setCustomName %>" label="name" localized="<%= true %>" maxlength='<%= ModelHintsUtil.getMaxLength(SiteNavigationMenuItem.class.getName(), "name") %>' name="name" placeholder="name" type="text" value='<%= SiteNavigationMenuItemUtil.getSiteNavigationMenuItemXML(siteNavigationMenuItem, "name") %>'>
	<aui:validator name="required" />
</aui:input>

<aui:input id="groupId" name="TypeSettingsProperties--groupId--" type="hidden" value="<%= (selLayout != null) ? selLayout.getGroupId() : StringPool.BLANK %>">
	<aui:validator name="required" />
</aui:input>

<aui:input id="privateLayout" name="TypeSettingsProperties--privateLayout--" type="hidden" value="<%= (selLayout != null) ? selLayout.isPrivateLayout() : StringPool.BLANK %>">
	<aui:validator name="required" />
</aui:input>

<div class="form-group input-text-wrapper text-default">
	<div class="d-inline-block" id="<portlet:namespace />layoutItemRemove" role="button">
		<aui:icon cssClass="icon-monospaced" image="times-circle" markupView="lexicon" />
	</div>

	<div class="d-inline-block">
		<span id="<portlet:namespace />layoutNameInput">
			<c:choose>
				<c:when test="<%= selLayout != null %>">
					<%= HtmlUtil.escape(selLayout.getName(locale)) %>
				</c:when>
				<c:otherwise>
					<span class="text-muted"><liferay-ui:message key="none" /></span>
				</c:otherwise>
			</c:choose>
		</span>
	</div>

	<aui:input id="layoutUuid" name="TypeSettingsProperties--layoutUuid--" type="hidden" value="<%= (selLayout != null) ? selLayout.getUuid() : StringPool.BLANK %>">
		<aui:validator name="required" />
	</aui:input>
</div>

<aui:button name="chooseLayout" value="choose" />

<%
String eventName = renderResponse.getNamespace() + "selectLayout";

ItemSelector itemSelector = (ItemSelector)request.getAttribute(SiteNavigationMenuItemTypeLayoutWebKeys.ITEM_SELECTOR);

ItemSelectorCriterion itemSelectorCriterion = new LayoutItemSelectorCriterion();

itemSelectorCriterion.setDesiredItemSelectorReturnTypes(new UUIDItemSelectorReturnType());

PortletURL itemSelectorURL = itemSelector.getItemSelectorURL(RequestBackedPortletURLFactoryUtil.create(renderRequest), eventName, itemSelectorCriterion);

if (selLayout != null) {
	itemSelectorURL.setParameter("layoutUuid", selLayout.getUuid());
}
%>

<aui:script use="aui-base,liferay-item-selector-dialog,node-event-simulate">
	var groupId = A.one('#<portlet:namespace />groupId');
	var layoutItemRemove = A.one('#<portlet:namespace />layoutItemRemove');
	var layoutNameInput = A.one('#<portlet:namespace />layoutNameInput');
	var layoutUuid = A.one('#<portlet:namespace />layoutUuid');
	var privateLayout = A.one('#<portlet:namespace />privateLayout');

	A.one('#<portlet:namespace />chooseLayout').on('click', function(event) {
		var itemSelectorDialog = new A.LiferayItemSelectorDialog({
			eventName: '<%= eventName %>',
			on: {
				selectedItemChange: function(event) {
					var selectedItem = event.newVal;

					if (selectedItem) {
						groupId.val(selectedItem.groupId);

						layoutUuid.val(selectedItem.id);

						privateLayout.val(selectedItem.privateLayout);

						layoutNameInput.html(selectedItem.name);
						layoutNameInput.simulate('change');

						layoutItemRemove.removeClass('hide');
					}
				}
			},
			'strings.add': '<liferay-ui:message key="done" />',
			title: '<liferay-ui:message key="select-layout" />',
			url: '<%= itemSelectorURL.toString() %>'
		});

		itemSelectorDialog.open();
	});

	layoutItemRemove.on('click', function(event) {
		layoutNameInput.html('<liferay-ui:message key="none" />');

		layoutUuid.val('');

		layoutItemRemove.addClass('hide');
	});
</aui:script>