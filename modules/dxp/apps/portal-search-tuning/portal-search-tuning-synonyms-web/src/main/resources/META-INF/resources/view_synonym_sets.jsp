<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.search.tuning.synonyms.web.internal.constants.SynonymsPortletKeys" %><%@
page import="com.liferay.portal.search.tuning.synonyms.web.internal.display.context.SynonymsDisplayContext" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<%
SynonymsDisplayContext synonymsDisplayContext = (SynonymsDisplayContext)request.getAttribute(SynonymsPortletKeys.SYNONYMS_DISPLAY_CONTEXT);
%>

<clay:management-toolbar
	actionDropdownItems="<%= synonymsDisplayContext.getActionDropdownMultipleItems() %>"
	componentId="synonymSetsEntriesManagementToolbar"
	creationMenu="<%= synonymsDisplayContext.getCreationMenu() %>"
	disabled="<%= synonymsDisplayContext.isDisabledManagementBar() %>"
	itemsTotal="<%= synonymsDisplayContext.getItemsTotal() %>"
	searchContainerId="synonymSetsEntries"
	selectable="<%= true %>"
	showCreationMenu="<%= true %>"
	showSearch="<%= false %>"
/>

<portlet:actionURL name="deleteSynonymSet" var="deleteSynonymSetActionURL">
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:actionURL>

<aui:form action="<%= deleteSynonymSetActionURL %>" cssClass="container-fluid-1280" method="post" name="SynonymSetsEntriesFm">
	<aui:input name="deletedSynonymSetsString" type="hidden" value="" />

	<liferay-ui:search-container
		id="synonymSetsEntries"
		searchContainer="<%= synonymsDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.portal.search.tuning.synonyms.web.internal.display.context.SynonymSetDisplayContext"
			keyProperty="synonymSetId"
			modelVar="synonymSetDisplayContext"
		>
			<liferay-ui:search-container-column-text
				colspan="<%= 2 %>"
				cssClass="table-cell-expand table-title"
			>
				<aui:a href="<%= synonymSetDisplayContext.getEditRenderURL() %>">
					<%= HtmlUtil.escape(synonymSetDisplayContext.getDisplayedSynonymSet()) %>
				</aui:a>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text>
				<clay:dropdown-actions
					defaultEventHandler="SynonymSetsDropdownDefaultEventHandler"
					dropdownItems="<%= synonymSetDisplayContext.getDropdownItems() %>"
				/>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script require='<%= npmResolvedPackageName + "/js/MultipleCheckboxAction.es as MultipleCheckboxAction" %>'>
	new MultipleCheckboxAction.default('<portlet:namespace />');
</aui:script>

<liferay-frontend:component
	componentId="SynonymSetsDropdownDefaultEventHandler"
	module="js/SynonymSetsDropdownDefaultEventHandler.es"
/>