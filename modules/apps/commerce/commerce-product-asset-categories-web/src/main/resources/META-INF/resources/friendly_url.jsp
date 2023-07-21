<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
AssetCategory category = (AssetCategory)request.getAttribute("assetCategory");
String assetCategoryURLSeparator = (String)request.getAttribute("assetCategoryURLSeparator");
String titleMapAsXML = (String)request.getAttribute("titleMapAsXML");
long vocabularyId = ParamUtil.getLong(request, "vocabularyId");

String friendlyURLBase = themeDisplay.getPortalURL() + assetCategoryURLSeparator;

PortletURL categoryRedirectURL = renderResponse.createRenderURL();

long parentCategoryId = BeanParamUtil.getLong(category, request, "parentCategoryId");

categoryRedirectURL.setParameter("mvcPath", "/view_categories.jsp");

if (parentCategoryId > 0) {
	categoryRedirectURL.setParameter("categoryId", String.valueOf(parentCategoryId));
}

if (vocabularyId > 0) {
	categoryRedirectURL.setParameter("vocabularyId", String.valueOf(vocabularyId));
}

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(categoryRedirectURL.toString());

renderResponse.setTitle(category.getTitle(locale));
%>

<portlet:actionURL name="editAssetCategoryFriendlyURL" var="editCategoryURL">
</portlet:actionURL>

<aui:form action="<%= editCategoryURL %>" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="categoryId" type="hidden" value="<%= category.getCategoryId() %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<div class="form-group">
				<label for="<portlet:namespace />urlTitleMapAsXML"><liferay-ui:message key="friendly-url" /><liferay-ui:icon-help message='<%= LanguageUtil.format(request, "for-example-x", "<em>news</em>", false) %>' /></label>

				<liferay-ui:input-localized
					defaultLanguageId="<%= LocaleUtil.toLanguageId(themeDisplay.getSiteDefaultLocale()) %>"
					inputAddon="<%= StringUtil.shorten(friendlyURLBase.toString(), 40) %>"
					name="urlTitleMapAsXML"
					xml="<%= HttpUtil.decodeURL(titleMapAsXML) %>"
				/>
			</div>
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= categoryRedirectURL.toString() %>" type="cancel" />
	</aui:button-row>
</aui:form>