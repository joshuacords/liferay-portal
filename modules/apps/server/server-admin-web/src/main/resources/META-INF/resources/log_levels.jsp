<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
int delta = ParamUtil.getInteger(request, SearchContainer.DEFAULT_DELTA_PARAM, SearchContainer.DEFAULT_DELTA);
String keywords = ParamUtil.getString(request, "keywords");

PortletURL searchURL = renderResponse.createRenderURL();

searchURL.setParameter("mvcRenderCommandName", "/server_admin/view");
searchURL.setParameter("tabs1", tabs1);
searchURL.setParameter("delta", String.valueOf(delta));

PortletURL clearResultsURL = PortletURLUtil.clone(searchURL, liferayPortletResponse);

clearResultsURL.setParameter("navigation", (String)null);
clearResultsURL.setParameter("keywords", StringPool.BLANK);

SearchContainer loggerSearchContainer = new SearchContainer(liferayPortletRequest, searchURL, null, null);

Map currentLoggerNames = new TreeMap();

Enumeration enu = LogManager.getCurrentLoggers();

while (enu.hasMoreElements()) {
	Logger logger = (Logger)enu.nextElement();

	String loggerName = logger.getName();

	if (Validator.isNull(keywords) || loggerName.contains(keywords)) {
		currentLoggerNames.put(loggerName, logger);
	}
}

List currentLoggerNamesList = ListUtil.fromCollection(currentLoggerNames.entrySet());

Iterator itr = currentLoggerNamesList.iterator();

while (itr.hasNext()) {
	Map.Entry entry = (Map.Entry)itr.next();

	Logger logger = (Logger)entry.getValue();

	Level level = logger.getLevel();

	if (level == null) {
		itr.remove();
	}
}

loggerSearchContainer.setResults(ListUtil.subList(currentLoggerNamesList, loggerSearchContainer.getStart(), loggerSearchContainer.getEnd()));
loggerSearchContainer.setTotal(currentLoggerNamesList.size());

PortletURL addLogCategoryURL = renderResponse.createRenderURL();

addLogCategoryURL.setParameter("mvcRenderCommandName", "/server_admin/add_log_category");
addLogCategoryURL.setParameter("redirect", currentURL);

CreationMenu creationMenu =
	new CreationMenu() {
		{
			addPrimaryDropdownItem(
				dropdownItem -> {
					dropdownItem.setHref(addLogCategoryURL);
					dropdownItem.setLabel(LanguageUtil.get(request, "add-category"));
				});
		}
	};
%>

<clay:management-toolbar
	clearResultsURL="<%= String.valueOf(clearResultsURL) %>"
	creationMenu="<%= creationMenu %>"
	itemsTotal="<%= loggerSearchContainer.getTotal() %>"
	searchActionURL="<%= String.valueOf(searchURL) %>"
	searchFormName="searchFm"
	selectable="<%= false %>"
	showCreationMenu="<%= true %>"
	showSearch="<%= true %>"
/>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		searchContainer="<%= loggerSearchContainer %>"
	>
		<liferay-ui:search-container-row
			className="java.util.Map.Entry"
			modelVar="entry"
		>

			<%
			String name = (String)entry.getKey();
			%>

			<liferay-ui:search-container-column-text
				name="category"
				value="<%= HtmlUtil.escape(name) %>"
			/>

			<liferay-ui:search-container-column-text
				name="level"
			>

				<%
				Logger logger = (Logger)entry.getValue();

				Level level = logger.getLevel();
				%>

				<select name="<%= renderResponse.getNamespace() %>logLevel<%= HtmlUtil.escapeAttribute(name) %>">

					<%
					for (int j = 0; j < Levels.ALL_LEVELS.length; j++) {
					%>

						<option <%= level.equals(Levels.ALL_LEVELS[j]) ? "selected" : StringPool.BLANK %> value="<%= Levels.ALL_LEVELS[j] %>"><%= Levels.ALL_LEVELS[j] %></option>

					<%
					}
					%>

				</select>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>

	<aui:button-row>
		<aui:button cssClass="save-server-button" data-cmd="updateLogLevels" value="save" />
	</aui:button-row>
</div>