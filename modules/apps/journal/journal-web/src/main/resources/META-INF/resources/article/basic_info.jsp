<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
JournalArticle article = journalDisplayContext.getArticle();

JournalEditArticleDisplayContext journalEditArticleDisplayContext = new JournalEditArticleDisplayContext(request, liferayPortletResponse, article);

DDMStructure ddmStructure = journalEditArticleDisplayContext.getDDMStructure();
%>

<aui:input name="ddmStructureKey" type="hidden" value="<%= ddmStructure.getStructureKey() %>" />

<c:if test="<%= journalWebConfiguration.changeableDefaultLanguage() %>">
	<soy:component-renderer
		context="<%= journalEditArticleDisplayContext.getChangeDefaultLanguageSoyContext() %>"
		module="js/ChangeDefaultLanguage.es"
		servletContext="<%= application %>"
		templateNamespace="com.liferay.journal.web.ChangeDefaultLanguage.render"
	/>
</c:if>

<p class="article-structure">
	<b><liferay-ui:message key="structure" /></b>: <%= HtmlUtil.escape(ddmStructure.getName(locale)) %>
</p>

<c:if test="<%= (article != null) && !article.isNew() && (journalEditArticleDisplayContext.getClassNameId() == JournalArticleConstants.CLASSNAME_ID_DEFAULT) %>">
	<p class="article-version-status">
		<b><liferay-ui:message key="version" /></b>: <%= article.getVersion() %>

		<span class="label label-<%= LabelItem.getStyleFromWorkflowStatus(article.getStatus()) %> ml-2 text-uppercase">
			<liferay-ui:message key="<%= WorkflowConstants.getStatusLabel(article.getStatus()) %>" />
		</span>
	</p>
</c:if>

<c:choose>
	<c:when test="<%= !journalWebConfiguration.journalArticleForceAutogenerateId() && (journalEditArticleDisplayContext.getClassNameId() == JournalArticleConstants.CLASSNAME_ID_DEFAULT) %>">
		<div class="article-id">
			<label for="<portlet:namespace />newArticleId"><liferay-ui:message key="id" /></label>

			<aui:input label="" name="newArticleId" type="text" value="<%= (article != null) ? article.getArticleId() : StringPool.BLANK %>" wrapperCssClass="mb-1" />

			<%
			String taglibOnChange = "Liferay.Util.toggleDisabled('#" + renderResponse.getNamespace() + "newArticleId', event.target.checked);";
			%>

			<aui:input checked="<%= false %>" label="autogenerate-id" name="autoArticleId" onChange="<%= taglibOnChange %>" type="checkbox" value="<%= false %>" wrapperCssClass="mb-3" />
		</div>

		<aui:script>
			Liferay.Util.disableToggleBoxes(
				'<portlet:namespace />autoArticleId',
				'<portlet:namespace />newArticleId',
				true
			);
		</aui:script>
	</c:when>
	<c:otherwise>
		<aui:input name="newArticleId" type="hidden" />
		<aui:input name="autoArticleId" type="hidden" value="<%= true %>" />

		<c:if test="<%= (article != null) && !article.isNew() && (journalEditArticleDisplayContext.getClassNameId() == JournalArticleConstants.CLASSNAME_ID_DEFAULT) %>">
			<p class="article-id">
				<b><liferay-ui:message key="id" /></b>: <%= article.getArticleId() %>
			</p>
		</c:if>
	</c:otherwise>
</c:choose>

<div class="article-content-description">
	<label for="<portlet:namespace />descriptionMapAsXML"><liferay-ui:message key="summary" /></label>

	<liferay-ui:input-localized
		availableLocales="<%= journalEditArticleDisplayContext.getAvailableLocales() %>"
		cssClass="form-control"
		defaultLanguageId="<%= journalEditArticleDisplayContext.getDefaultArticleLanguageId() %>"
		editorName="alloyeditor"
		formName="fm"
		ignoreRequestValue="<%= journalEditArticleDisplayContext.isChangeStructure() %>"
		name="descriptionMapAsXML"
		placeholder="description"
		selectedLanguageId="<%= journalEditArticleDisplayContext.getSelectedLanguageId() %>"
		type="editor"
		xml="<%= (article != null) ? article.getDescriptionMapAsXML() : StringPool.BLANK %>"
	/>
</div>