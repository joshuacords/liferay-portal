<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String action = GetterUtil.getString(request.getAttribute("liferay-staging:select-pages:action"));
boolean disableInputs = GetterUtil.getBoolean(request.getAttribute("liferay-staging:select-pages:disableInputs"));
long exportImportConfigurationId = GetterUtil.getLong(request.getAttribute("liferay-staging:select-pages:exportImportConfigurationId"));
long selectPagesGroupId = GetterUtil.getLong(request.getAttribute("liferay-staging:select-pages:groupId"));
boolean selectPagesPrivateLayout = GetterUtil.getBoolean(request.getAttribute("liferay-staging:select-pages:privateLayout"));
String treeId = GetterUtil.getString(request.getAttribute("liferay-staging:select-pages:treeId"));

Group selectPagesGroup = group;

if (groupId != selectPagesGroupId) {
	selectPagesGroup = GroupLocalServiceUtil.fetchGroup(groupId);

	if (selectPagesGroup == null) {
		selectPagesGroup = group;
	}
}

Map<String, Serializable> settingsMap = Collections.emptyMap();
Map<String, String[]> parameterMap = Collections.emptyMap();

ExportImportConfiguration exportImportConfiguration = ExportImportConfigurationLocalServiceUtil.fetchExportImportConfiguration(exportImportConfigurationId);

long[] selectedLayoutIdsArray = null;

if (exportImportConfiguration != null) {
	settingsMap = exportImportConfiguration.getSettingsMap();

	parameterMap = (Map<String, String[]>)settingsMap.get("parameterMap");

	int type = exportImportConfiguration.getType();

	if (type == ExportImportConfigurationConstants.TYPE_PUBLISH_LAYOUT_REMOTE) {
		Map<Long, Boolean> layoutIdMap = (Map<Long, Boolean>)settingsMap.get("layoutIdMap");

		selectedLayoutIdsArray = ExportImportHelperUtil.getLayoutIds(layoutIdMap);
	}
	else {
		selectedLayoutIdsArray = GetterUtil.getLongValues(settingsMap.get("layoutIds"));
	}
}
else {
	String openNodes = SessionTreeJSClicks.getOpenNodes(request, treeId + "SelectedNode");

	if (openNodes == null) {
		selectedLayoutIdsArray = ExportImportHelperUtil.getAllLayoutIds(selectPagesGroupId, selectPagesPrivateLayout);

		for (long selectedLayoutId : selectedLayoutIdsArray) {
			SessionTreeJSClicks.openLayoutNodes(request, treeId + "SelectedNode", selectPagesPrivateLayout, selectedLayoutId, true);
		}
	}
	else {
		selectedLayoutIdsArray = GetterUtil.getLongValues(StringUtil.split(openNodes, ','));
	}
}

String selectedLayoutIds = StringUtil.merge(selectedLayoutIdsArray);

String range = ParamUtil.getString(portletRequest, ExportImportDateUtil.RANGE, null);

boolean useRequestValues = false;

if ((range != null) || (exportImportConfiguration == null)) {
	useRequestValues = true;
}
%>