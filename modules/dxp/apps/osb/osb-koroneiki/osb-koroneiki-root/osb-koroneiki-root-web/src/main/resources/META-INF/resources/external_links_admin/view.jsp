<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<portlet:actionURL name="/external_links_admin/edit_external_link_mappings" var="editExternalLinkMappingsURL" />

<aui:form action="<%= editExternalLinkMappingsURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value='<%= ParamUtil.getString(request, "redirect") %>' />

	<aui:fieldset-group>
		<h3 class="sheet-subtitle"><liferay-ui:message key="external-link-mappings" /></h3>

		<aui:fieldset id='<%= renderResponse.getNamespace() + "externalLinks" %>'>

			<%
			Map<String, String[]> map = new HashMap<>(portletPreferences.getMap());

			if (map.isEmpty()) {
				map.put(StringPool.BLANK, new String[0]);
			}

			int[] externalLinkIndexes = new int[map.size()];

			int i = 0;

			String domain = StringPool.BLANK;
			String entityName = StringPool.BLANK;
			String url = StringPool.BLANK;

			for (Map.Entry<String, String[]> entry : map.entrySet()) {
				String key = entry.getKey();

				if (Validator.isNotNull(key)) {
					domain = key.split(StringPool.UNDERLINE)[0];
					entityName = key.split(StringPool.UNDERLINE)[1];
					url = entry.getValue()[0];
				}

				externalLinkIndexes[i] = i;
			%>

				<div class="lfr-form-row lfr-form-row-inline">
					<div class="row-fields">
						<aui:row>
							<aui:col md="4">
								<aui:input label="domain" name='<%= "domain_" + i %>' type="text" value="<%= domain %>" />
							</aui:col>

							<aui:col md="4">
								<aui:input label="entity-name" name='<%= "entityName_" + i %>' type="text" value="<%= entityName %>" />
							</aui:col>

							<aui:col md="4">
								<aui:input label="url" name='<%= "url_" + i %>' type="text" value="<%= url %>" />
							</aui:col>
						</aui:row>
					</div>
				</div>

			<%
				i++;
			}
			%>

			<aui:input name="externalLinkIndexes" type="hidden" value="<%= StringUtil.merge(externalLinkIndexes) %>" />
		</aui:fieldset>
	</aui:fieldset-group>

	<liferay-frontend:fieldset
		collapsed="<%= true %>"
		collapsible="<%= true %>"
		label="definition-of-terms"
	>
		<dt>
			[$ENTITY_ID$]
		</dt>
		<dd>
			The unique identifier of the external object.
		</dd>
	</liferay-frontend:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-base,liferay-auto-fields">
	var autoFields = new Liferay.AutoFields(
		{
			contentBox: 'fieldset#<portlet:namespace />externalLinks',
			fieldIndexes: '<portlet:namespace />externalLinkIndexes',
			namespace: '<portlet:namespace />'
		}
	).render();
</aui:script>