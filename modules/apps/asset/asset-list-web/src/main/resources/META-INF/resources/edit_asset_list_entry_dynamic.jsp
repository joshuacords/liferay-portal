<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNull(redirect)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	redirect = portletURL.toString();
}

AssetListEntry assetListEntry = assetListDisplayContext.getAssetListEntry();
%>

<portlet:actionURL name="/asset_list/update_asset_list_entry_dynamic" var="updateAssetListEntryDynamicURL" />

<liferay-frontend:edit-form
	action="<%= updateAssetListEntryDynamicURL %>"
	cssClass="pt-0"
	fluid="<%= true %>"
	method="post"
	name="fm"
>
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="assetListEntryId" type="hidden" value="<%= assetListDisplayContext.getAssetListEntryId() %>" />
	<aui:input name="segmentsEntryId" type="hidden" value="<%= assetListDisplayContext.getSegmentsEntryId() %>" />
	<aui:input name="type" type="hidden" value="<%= assetListDisplayContext.getAssetListEntryType() %>" />

	<aui:model-context bean="<%= assetListEntry %>" model="<%= AssetListEntry.class %>" />

	<liferay-frontend:edit-form-body>
		<h3 class="sheet-title">
			<div class="autofit-row autofit-row-center">
				<div class="autofit-col">
					<%= HtmlUtil.escape(editAssetListDisplayContext.getSegmentsEntryName(editAssetListDisplayContext.getSegmentsEntryId(), locale)) %>
				</div>

				<div class="autofit-col autofit-col-end inline-item-after">
					<liferay-util:include page="/asset_list_entry_variation_action.jsp" servletContext="<%= application %>" />
				</div>
			</div>
		</h3>

		<liferay-frontend:form-navigator
			formModelBean="<%= assetListEntry %>"
			id="<%= AssetListFormConstants.FORM_NAVIGATOR_ID %>"
			showButtons="<%= false %>"
		/>
	</liferay-frontend:edit-form-body>

	<c:if test="<%= !editAssetListDisplayContext.isLiveGroup() %>">
		<liferay-frontend:edit-form-footer>
			<aui:button onClick='<%= renderResponse.getNamespace() + "saveSelectBoxes();" %>' type="submit" />

			<aui:button href="<%= redirect %>" type="cancel" />
		</liferay-frontend:edit-form-footer>
	</c:if>
</liferay-frontend:edit-form>

<script>
	function <portlet:namespace />saveSelectBoxes() {
		var form = document.<portlet:namespace />fm;

		<%
		List<AssetRendererFactory<?>> assetRendererFactories = ListUtil.sort(AssetRendererFactoryRegistryUtil.getAssetRendererFactories(company.getCompanyId()), new AssetRendererFactoryTypeNameComparator(locale));

		for (AssetRendererFactory<?> assetRendererFactory : assetRendererFactories) {
			ClassTypeReader classTypeReader = assetRendererFactory.getClassTypeReader();

			List<ClassType> classTypes = classTypeReader.getAvailableClassTypes(editAssetListDisplayContext.getReferencedModelsGroupIds(), locale);

			if (classTypes.isEmpty()) {
				continue;
			}

			String className = assetListDisplayContext.getClassName(assetRendererFactory);
		%>

			Liferay.Util.setFormValues(form, {
				classTypeIds<%= className %>: Liferay.Util.listSelect(
					Liferay.Util.getFormElement(
						form,
						'<%= className %>currentClassTypeIds'
					)
				)
			});

		<%
		}
		%>

		var currentClassNameIdsSelect = Liferay.Util.getFormElement(
			form,
			'currentClassNameIds'
		);

		if (currentClassNameIdsSelect) {
			Liferay.Util.postForm(form, {
				data: {
					classNameIds: Liferay.Util.listSelect(currentClassNameIdsSelect)
				}
			});
		}
		else {
			submitForm(form);
		}
	}
</script>