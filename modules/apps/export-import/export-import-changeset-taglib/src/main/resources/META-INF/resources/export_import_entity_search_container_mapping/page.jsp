<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/export_import_entity_search_container_mapping/init.jsp" %>

<div id="<portlet:namespace /><%= searchContainerMappingId %>">

	<%
	List<ResultRow> resultRows = searchContainer.getResultRows();

	for (ResultRow resultRow : resultRows) {
		Map<String, Object> data = new HashMap<>();

		data.put("rowPK", resultRow.getPrimaryKey());

		StagedModel stagedModel = (StagedModel)resultRow.getObject();

		data.put("classNameId", ExportImportClassedModelUtil.getClassNameId(stagedModel));
		data.put("groupId", BeanPropertiesUtil.getLong(stagedModel, "groupId"));
		data.put("uuid", stagedModel.getUuid());
	%>

		<div <%= HtmlUtil.buildData(data) %>></div>

	<%
	}
	%>

</div>