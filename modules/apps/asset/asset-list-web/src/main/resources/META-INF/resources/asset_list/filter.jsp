<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-frontend:fieldset-group>
	<liferay-frontend:fieldset
		disabled="<%= editAssetListDisplayContext.isLiveGroup() %>"
	>
		<liferay-asset:asset-tags-error />

		<liferay-ui:error exception="<%= DuplicateQueryRuleException.class %>">

			<%
			DuplicateQueryRuleException dqre = (DuplicateQueryRuleException)errorException;
			%>

			<liferay-util:buffer
				var="messageArgument"
			>
				<em>(<liferay-ui:message key='<%= dqre.isContains() ? "contains" : "does-not-contain" %>' /> - <liferay-ui:message key='<%= dqre.isAndOperator() ? "all" : "any" %>' /> - <liferay-ui:message key='<%= Objects.equals(dqre.getName(), "assetTags") ? "tags" : "categories" %>' />)</em>
			</liferay-util:buffer>

			<liferay-ui:message arguments="<%= messageArgument %>" key="only-one-rule-with-the-combination-x-is-supported" translateArguments="<%= false %>" />
		</liferay-ui:error>

		<p><liferay-ui:message key="displayed-assets-must-match-these-rules" /></p>

		<div id="<portlet:namespace />ConditionForm"></div>

		<soy:component-renderer
			context='<%=
				HashMapBuilder.<String, Object>put(
					"categorySelectorURL", editAssetListDisplayContext.getCategorySelectorURL()
				).put(
					"disabled", editAssetListDisplayContext.isLiveGroup()
				).put(
					"groupIds", ListUtil.toList(editAssetListDisplayContext.getReferencedModelsGroupIds())
				).put(
					"id", "autofield"
				).put(
					"namespace", liferayPortletResponse.getNamespace()
				).put(
					"pathThemeImages", themeDisplay.getPathThemeImages()
				).put(
					"rules", editAssetListDisplayContext.getAutoFieldRulesJSONArray()
				).put(
					"tagSelectorURL", editAssetListDisplayContext.getTagSelectorURL()
				).put(
					"vocabularyIds", editAssetListDisplayContext.getVocabularyIds()
				).build()
			%>'
			module="js/AutoField.es"
			templateNamespace="com.liferay.asset.list.web.AutoField.render"
		/>
	</liferay-frontend:fieldset>
</liferay-frontend:fieldset-group>