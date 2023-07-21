<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String portletResourceNamespace = ParamUtil.getString(request, "portletResourceNamespace", renderResponse.getNamespace());

long structureVersionId = ParamUtil.getLong(request, "structureVersionId");

DDMStructureVersion structureVersion = DDMStructureVersionServiceUtil.getStructureVersion(structureVersionId);

DDMStructure structure = structureVersion.getStructure();

String script = BeanParamUtil.getString(structureVersion, request, "definition");

JSONArray fieldsJSONArray = DDMUtil.getDDMFormFieldsJSONArray(structureVersion, script);

String fieldsJSONArrayString = StringPool.BLANK;

if (fieldsJSONArray != null) {
	fieldsJSONArrayString = fieldsJSONArray.toString();
}

String title = LanguageUtil.format(request, "x-version-x", new Object[] {structureVersion.getName(locale), structureVersion.getVersion()});

PortletURL backURL = renderResponse.createRenderURL();

backURL.setParameter("mvcPath", "/view_structure_history.jsp");
backURL.setParameter("redirect", redirect);
backURL.setParameter("structureId", String.valueOf(structureVersion.getStructureId()));
%>

<div class="container-fluid-1280">
	<c:choose>
		<c:when test="<%= ddmDisplay.isShowBackURLInTitleBar() %>">

			<%
			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(backURL.toString());

			renderResponse.setTitle(title);
			%>

		</c:when>
		<c:otherwise>
			<liferay-ui:header
				backURL="<%= backURL.toString() %>"
				localizeTitle="<%= false %>"
				title="<%= title %>"
			/>
		</c:otherwise>
	</c:choose>

	<aui:model-context bean="<%= structureVersion %>" model="<%= DDMStructureVersion.class %>" />

	<aui:input disabled="<%= true %>" name="name" />

	<aui:input disabled="<%= true %>" name="description" />

	<%@ include file="/form_builder.jspf" %>

	<aui:button-row>
		<aui:button href="<%= backURL.toString() %>" type="cancel" />
	</aui:button-row>
</div>