<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
JournalSelectDDMStructureDisplayContext journalSelectDDMStructureDisplayContext = new JournalSelectDDMStructureDisplayContext(renderRequest, renderResponse);

SearchContainer<DDMStructure> ddmStructureSearch = journalSelectDDMStructureDisplayContext.getDDMStructureSearch();
%>

<clay:management-toolbar
	displayContext="<%= new JournalSelectDDMStructureManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, journalSelectDDMStructureDisplayContext) %>"
/>

<aui:form cssClass="container-fluid-1280" method="post" name="selectDDMStructureFm">
	<liferay-ui:search-container
		searchContainer="<%= ddmStructureSearch %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.dynamic.data.mapping.model.DDMStructure"
			keyProperty="structureId"
			modelVar="ddmStructure"
		>
			<liferay-ui:search-container-column-text
				name="id"
				value="<%= String.valueOf(ddmStructure.getStructureId()) %>"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="name"
			>
				<c:choose>
					<c:when test="<%= ddmStructure.getStructureId() != journalSelectDDMStructureDisplayContext.getClassPK() %>">
						<aui:a
							cssClass="selector-button"
							data='<%=
								HashMapBuilder.<String, Object>put(
									"ddmstructureid", ddmStructure.getStructureId()
								).put(
									"ddmstructurekey", ddmStructure.getStructureKey()
								).put(
									"name", HtmlUtil.escape(ddmStructure.getName(locale))
								).build()
							%>'
							href="javascript:;"
						>
							<%= HtmlUtil.escape(ddmStructure.getUnambiguousName(ddmStructureSearch.getResults(), themeDisplay.getScopeGroupId(), locale)) %>
						</aui:a>
					</c:when>
					<c:otherwise>
						<%= HtmlUtil.escape(ddmStructure.getUnambiguousName(ddmStructureSearch.getResults(), themeDisplay.getScopeGroupId(), locale)) %>
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="description"
				truncate="<%= true %>"
				value="<%= HtmlUtil.escape(ddmStructure.getDescription(locale)) %>"
			/>

			<liferay-ui:search-container-column-date
				name="modified-date"
				value="<%= ddmStructure.getModifiedDate() %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="list"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	Liferay.Util.selectEntityHandler(
		'#<portlet:namespace />selectDDMStructureFm',
		'<%= HtmlUtil.escapeJS(journalSelectDDMStructureDisplayContext.getEventName()) %>'
	);
</aui:script>