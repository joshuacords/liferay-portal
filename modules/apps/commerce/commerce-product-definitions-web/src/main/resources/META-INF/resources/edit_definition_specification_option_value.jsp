<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPDefinitionSpecificationOptionValueDisplayContext cpDefinitionSpecificationOptionValueDisplayContext = (CPDefinitionSpecificationOptionValueDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CPDefinitionSpecificationOptionValue cpDefinitionSpecificationOptionValue = cpDefinitionSpecificationOptionValueDisplayContext.getCPDefinitionSpecificationOptionValue();
List<CPOptionCategory> cpOptionCategories = cpDefinitionSpecificationOptionValueDisplayContext.getCPOptionCategories();

CPSpecificationOption cpSpecificationOption = cpDefinitionSpecificationOptionValue.getCPSpecificationOption();

long cpOptionCategoryId = BeanParamUtil.getLong(cpDefinitionSpecificationOptionValue, request, "CPOptionCategoryId");
%>

<portlet:actionURL name="editProductDefinitionSpecificationOptionValue" var="editProductDefinitionSpecificationOptionValueActionURL" />

<commerce-ui:side-panel-content
	title="<%= cpSpecificationOption.getTitle(locale) %>"
>
	<commerce-ui:panel
		title='<%= LanguageUtil.get(request, "detail") %>'
	>
		<aui:form action="<%= editProductDefinitionSpecificationOptionValueActionURL %>" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
			<aui:input name="cpDefinitionSpecificationOptionValueId" type="hidden" value="<%= String.valueOf(cpDefinitionSpecificationOptionValue.getCPDefinitionSpecificationOptionValueId()) %>" />

			<aui:model-context bean="<%= cpDefinitionSpecificationOptionValue %>" model="<%= CPDefinitionSpecificationOptionValue.class %>" />

			<aui:input defaultLanguageId="<%= cpDefinitionSpecificationOptionValueDisplayContext.getCatalogDefaultLanguageId() %>" name="value" />

			<aui:select label="group" name="CPOptionCategoryId" showEmptyOption="<%= true %>">

				<%
				for (CPOptionCategory cpOptionCategory : cpOptionCategories) {
				%>

					<aui:option label="<%= cpOptionCategory.getTitle(locale) %>" selected="<%= cpOptionCategoryId == cpOptionCategory.getCPOptionCategoryId() %>" value="<%= cpOptionCategory.getCPOptionCategoryId() %>" />

				<%
				}
				%>

			</aui:select>

			<aui:input name="priority" />

			<c:if test="<%= cpDefinitionSpecificationOptionValueDisplayContext.hasCustomAttributesAvailable() %>">
				<liferay-expando:custom-attribute-list
					className="<%= CPDefinitionSpecificationOptionValue.class.getName() %>"
					classPK="<%= (cpDefinitionSpecificationOptionValue != null) ? cpDefinitionSpecificationOptionValue.getCPDefinitionSpecificationOptionValueId() : 0 %>"
					editable="<%= true %>"
					label="<%= true %>"
				/>
			</c:if>

			<aui:button-row>
				<aui:button cssClass="btn-lg" type="submit" value="save" />

				<aui:button cssClass="btn-lg" type="cancel" />
			</aui:button-row>
		</aui:form>
	</commerce-ui:panel>
</commerce-ui:side-panel-content>