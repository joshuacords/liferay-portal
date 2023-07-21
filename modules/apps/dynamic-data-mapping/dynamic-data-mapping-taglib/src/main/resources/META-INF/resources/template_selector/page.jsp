<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/template_selector/init.jsp" %>

<%
long classNameId = GetterUtil.getLong((String)request.getAttribute("liferay-ddm:template-selector:classNameId"));
DDMTemplate portletDisplayDDMTemplate = (DDMTemplate)request.getAttribute("liferay-ddm:template-selector:portletDisplayDDMTemplate");

long ddmTemplateGroupId = PortletDisplayTemplateUtil.getDDMTemplateGroupId(themeDisplay.getScopeGroupId());

Group ddmTemplateGroup = GroupLocalServiceUtil.getGroup(ddmTemplateGroupId);
%>

<div class="autofit-row autofit-row-center">
	<div class="autofit-col inline-item-before">
		<aui:input id="displayStyleGroupId" name="preferences--displayStyleGroupId--" type="hidden" value="<%= String.valueOf(displayStyleGroupId) %>" />

		<aui:select id="displayStyle" inlineField="<%= true %>" label="<%= HtmlUtil.escape(label) %>" name="preferences--displayStyle--">
			<c:if test="<%= showEmptyOption %>">
				<aui:option label="default" selected="<%= Validator.isNull(displayStyle) %>" />
			</c:if>

			<c:if test="<%= (displayStyles != null) && !displayStyles.isEmpty() %>">
				<optgroup label="<liferay-ui:message key="default" />">

					<%
					for (String curDisplayStyle : displayStyles) {
					%>

						<aui:option label="<%= HtmlUtil.escape(curDisplayStyle) %>" selected="<%= displayStyle.equals(curDisplayStyle) %>" />

					<%
					}
					%>

				</optgroup>
			</c:if>

			<%
			for (com.liferay.dynamic.data.mapping.model.DDMTemplate curDDMTemplate : DDMTemplateLocalServiceUtil.getTemplates(PortalUtil.getCurrentAndAncestorSiteGroupIds(ddmTemplateGroupId), classNameId, 0L)) {
				if (!DDMTemplatePermission.contains(permissionChecker, curDDMTemplate.getTemplateId(), ActionKeys.VIEW) || !DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY.equals(curDDMTemplate.getType())) {
					continue;
				}

				Map<String, Object> data = new HashMap<String, Object>();

				data.put("displaystylegroupid", curDDMTemplate.getGroupId());
			%>

				<aui:option data="<%= data %>" label="<%= HtmlUtil.escape(curDDMTemplate.getName(locale)) %>" selected="<%= (portletDisplayDDMTemplate != null) && (curDDMTemplate.getTemplateId() == portletDisplayDDMTemplate.getTemplateId()) %>" value="<%= PortletDisplayTemplate.DISPLAY_STYLE_PREFIX + HtmlUtil.escape(curDDMTemplate.getTemplateKey()) %>" />

			<%
			}
			%>

		</aui:select>
	</div>

	<c:if test="<%= !ddmTemplateGroup.isLayoutPrototype() %>">
		<div class="autofit-col">
			<liferay-ui:icon
				icon="<%= HtmlUtil.escapeCSS(icon) %>"
				id="selectDDMTemplate"
				label="<%= true %>"
				markupView="lexicon"
				message='<%= LanguageUtil.get(request, "manage-templates") %>'
				url="javascript:;"
			/>
		</div>
	</c:if>
</div>

<liferay-portlet:renderURL plid="<%= themeDisplay.getPlid() %>" portletName="<%= PortletProviderUtil.getPortletId(DDMTemplate.class.getName(), PortletProvider.Action.VIEW) %>" var="basePortletURL">
	<portlet:param name="showHeader" value="<%= Boolean.FALSE.toString() %>" />
</liferay-portlet:renderURL>

<aui:script sandbox="<%= true %>">
	var selectDDMTemplateLink = document.getElementById(
		'<portlet:namespace />selectDDMTemplate'
	);

	if (selectDDMTemplateLink) {
		selectDDMTemplateLink.addEventListener('click', function(event) {
			Liferay.Util.openDDMPortlet(
				{
					basePortletURL: '<%= basePortletURL %>',
					classNameId: '<%= classNameId %>',
					dialog: {
						width: 1024
					},
					eventName: '<portlet:namespace />saveTemplate',
					groupId: <%= ddmTemplateGroupId %>,
					mvcPath: '/view_template.jsp',
					navigationStartsOn: '<%= DDMNavigationHelper.VIEW_TEMPLATES %>',
					refererPortletName:
						'<%= PortletKeys.PORTLET_DISPLAY_TEMPLATE %>',
					title:
						'<%= UnicodeLanguageUtil.get(request, "widget-templates") %>'
				},
				function(event) {
					if (!event.newVal) {
						submitForm(
							document.<portlet:namespace />fm,
							'<%= HtmlUtil.escapeJS(refreshURL) %>'
						);
					}
				}
			);
		});
	}

	var displayStyle = document.getElementById('<portlet:namespace />displayStyle');
	var displayStyleGroupIdInput = document.getElementById(
		'<portlet:namespace />displayStyleGroupId'
	);

	if (displayStyle && displayStyleGroupIdInput) {
		displayStyle.addEventListener('change', function(event) {
			var selectedDisplayStyle = displayStyle.querySelector('option:checked');

			if (selectedDisplayStyle) {
				var displayStyleGroupId =
					selectedDisplayStyle.dataset.displaystylegroupid;

				if (displayStyleGroupId) {
					displayStyleGroupIdInput.value = displayStyleGroupId;
				}
			}
		});
	}
</aui:script>