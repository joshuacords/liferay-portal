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

long vocabularyId = ParamUtil.getLong(request, "vocabularyId");

AssetVocabulary vocabulary = null;

if (vocabularyId > 0) {
	vocabulary = AssetVocabularyServiceUtil.fetchVocabulary(vocabularyId);
}

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle((vocabulary == null) ? LanguageUtil.get(request, "add-vocabulary") : vocabulary.getTitle(locale));
%>

<portlet:actionURL name="editVocabulary" var="editVocabularyURL">
	<portlet:param name="mvcPath" value="/edit_vocabulary.jsp" />
</portlet:actionURL>

<liferay-frontend:edit-form
	action="<%= editVocabularyURL %>"
	name="fm"
>
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="vocabularyId" type="hidden" value="<%= vocabularyId %>" />

	<liferay-frontend:edit-form-body>
		<liferay-ui:error exception="<%= DuplicateVocabularyException.class %>" message="please-enter-a-unique-name" />
		<liferay-ui:error exception="<%= VocabularyNameException.class %>" message="please-enter-a-valid-name" />

		<aui:model-context bean="<%= vocabulary %>" model="<%= AssetVocabulary.class %>" />

		<liferay-frontend:fieldset-group>
			<liferay-frontend:fieldset
				collapsed="<%= false %>"
				collapsible="<%= true %>"
				label="details"
			>
				<aui:input autoFocus="<%= true %>" label="name" name="title" placeholder="name" />

				<aui:input name="description" placeholder="description" />

				<aui:input helpMessage="multi-valued-help" label="allow-multiple-categories" name="multiValued" type="toggle-switch" value="<%= (vocabulary != null) ? vocabulary.isMultiValued() : true %>" />
			</liferay-frontend:fieldset>

			<%@ include file="/edit_vocabulary_settings.jspf" %>

			<c:if test="<%= vocabulary == null %>">
				<liferay-frontend:fieldset
					collapsed="<%= true %>"
					collapsible="<%= true %>"
					label="permissions"
				>
					<liferay-ui:input-permissions
						modelName="<%= AssetVocabulary.class.getName() %>"
					/>
				</liferay-frontend:fieldset>
			</c:if>
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>