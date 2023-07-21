<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.search.web.internal.search.bar.portlet.SearchBarPortletDisplayContext" %><%@
page import="com.liferay.portal.search.web.internal.search.bar.portlet.SearchBarPortletPreferences" %><%@
page import="com.liferay.portal.search.web.internal.util.PortletPreferencesJspUtil" %>

<portlet:defineObjects />

<%
SearchBarPortletDisplayContext searchBarPortletDisplayContext = (SearchBarPortletDisplayContext)java.util.Objects.requireNonNull(request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT));

/*
*/
SearchBarPortletPreferences searchBarPortletPreferences = new com.liferay.portal.search.web.internal.search.bar.portlet.SearchBarPortletPreferencesImpl(java.util.Optional.ofNullable(portletPreferences));
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form
	action="<%= configurationActionURL %>"
	method="post"
	name="fm"
>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset-group>
			<aui:fieldset>
				<aui:input helpMessage="invisible-help" label="invisible" name="<%= PortletPreferencesJspUtil.getInputName(SearchBarPortletPreferences.PREFERENCE_KEY_INVISIBLE) %>" type="checkbox" value="<%= searchBarPortletPreferences.isInvisible() %>" />

				<c:if test="<%= searchBarPortletDisplayContext.isDisplayWarningIgnoredConfiguration() %>">
					<div class="alert alert-info text-center">
						<liferay-ui:message key="the-header-search-bar-configuration-takes-precedence" />
					</div>
				</c:if>

				<aui:input disabled="<%= searchBarPortletDisplayContext.isDisplayWarningIgnoredConfiguration() %>" label="keywords-parameter-name" name="<%= PortletPreferencesJspUtil.getInputName(SearchBarPortletPreferences.PREFERENCE_KEY_KEYWORDS_PARAMETER_NAME) %>" value="<%= searchBarPortletPreferences.getKeywordsParameterName() %>" />

				<aui:select disabled="<%= searchBarPortletDisplayContext.isDisplayWarningIgnoredConfiguration() %>" label="scope" name="<%= PortletPreferencesJspUtil.getInputName(SearchBarPortletPreferences.PREFERENCE_KEY_SEARCH_SCOPE) %>" value="<%= searchBarPortletPreferences.getSearchScopePreferenceString() %>">
					<aui:option label="this-site" value="this-site" />
					<aui:option label="everything" value="everything" />
					<aui:option label="let-the-user-choose" value="let-the-user-choose" />
				</aui:select>

				<aui:input disabled="<%= searchBarPortletDisplayContext.isDisplayWarningIgnoredConfiguration() %>" label="scope-parameter-name" name="<%= PortletPreferencesJspUtil.getInputName(SearchBarPortletPreferences.PREFERENCE_KEY_SCOPE_PARAMETER_NAME) %>" value="<%= searchBarPortletPreferences.getScopeParameterName() %>" />

				<aui:input helpMessage="destination-page-help" label="destination-page" name="<%= PortletPreferencesJspUtil.getInputName(SearchBarPortletPreferences.PREFERENCE_KEY_DESTINATION) %>" value="<%= searchBarPortletPreferences.getDestinationString() %>" />

				<aui:input disabled="<%= searchBarPortletDisplayContext.isDisplayWarningIgnoredConfiguration() %>" helpMessage="use-advanced-search-syntax-help" name="<%= PortletPreferencesJspUtil.getInputName(SearchBarPortletPreferences.PREFERENCE_KEY_USE_ADVANCED_SEARCH_SYNTAX) %>" type="checkbox" value="<%= searchBarPortletPreferences.isUseAdvancedSearchSyntax() %>" />

				<aui:input disabled="<%= searchBarPortletDisplayContext.isDisplayWarningIgnoredConfiguration() %>" helpMessage="show-results-from-staged-sites-help" label="show-results-from-staged-sites" name="<%= PortletPreferencesJspUtil.getInputName(SearchBarPortletPreferences.PREFERENCE_KEY_SHOW_STAGED_RESULTS) %>" type="checkbox" value="<%= searchBarPortletPreferences.isShowStagedResults() %>" />

				<aui:input helpMessage="federated-search-key-help" label="federated-search-key" name="<%= PortletPreferencesJspUtil.getInputName(SearchBarPortletPreferences.PREFERENCE_KEY_FEDERATED_SEARCH_KEY) %>" type="text" value="<%= searchBarPortletPreferences.getFederatedSearchKeyString() %>" />
			</aui:fieldset>
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<aui:button type="submit" />

		<aui:button type="cancel" />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>