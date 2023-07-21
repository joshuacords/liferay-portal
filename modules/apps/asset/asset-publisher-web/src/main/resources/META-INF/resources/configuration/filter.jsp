<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<aui:fieldset label="displayed-assets-must-match-these-rules" markupView="lexicon">
	<liferay-asset:asset-tags-error />

	<%
	DuplicateQueryRuleException dqre = null;
	%>

	<liferay-ui:error exception="<%= DuplicateQueryRuleException.class %>">

		<%
		dqre = (DuplicateQueryRuleException)errorException;

		String name = dqre.getName();
		%>

		<liferay-util:buffer
			var="messageArgument"
		>
			<em>(<liferay-ui:message key='<%= dqre.isContains() ? "contains" : "does-not-contain" %>' /> - <liferay-ui:message key='<%= dqre.isAndOperator() ? "all" : "any" %>' /> - <liferay-ui:message key='<%= name.equals("assetTags") ? "tags" : "categories" %>' />)</em>
		</liferay-util:buffer>

		<liferay-ui:message arguments="<%= messageArgument %>" key="only-one-rule-with-the-combination-x-is-supported" translateArguments="<%= false %>" />
	</liferay-ui:error>
</aui:fieldset>

<div id="<portlet:namespace />ConditionForm"></div>

<%
Map<String, Object> context = new HashMap<>();

context.put("categorySelectorURL", assetPublisherDisplayContext.getCategorySelectorURL());
context.put("id", "autofield");
context.put("groupIds", ListUtil.toList(assetPublisherDisplayContext.getReferencedModelsGroupIds()));
context.put("namespace", liferayPortletResponse.getNamespace());
context.put("pathThemeImages", themeDisplay.getPathThemeImages());
context.put("rules", assetPublisherDisplayContext.getAutoFieldRulesJSONArray());
context.put("tagSelectorURL", assetPublisherDisplayContext.getTagSelectorURL());
context.put("vocabularyIds", assetPublisherDisplayContext.getVocabularyIds());
%>

<soy:component-renderer
	context="<%= context %>"
	module="js/AutoField.es"
	templateNamespace="com.liferay.asset.publisher.web.AutoField.render"
/>