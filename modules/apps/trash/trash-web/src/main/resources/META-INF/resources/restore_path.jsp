<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<c:if test="<%= SessionMessages.contains(renderRequest, portletDisplay.getId() + SessionMessages.KEY_SUFFIX_DELETE_SUCCESS_DATA) %>">
	<liferay-util:buffer
		var="alertMessage"
	>

		<%
		Map<String, List<String>> data = (HashMap<String, List<String>>)SessionMessages.get(renderRequest, portletDisplay.getId() + SessionMessages.KEY_SUFFIX_DELETE_SUCCESS_DATA);

		List<String> restoreClassNames = data.get("restoreClassNames");
		List<String> restoreEntryMessages = data.get("restoreEntryMessages");
		List<String> restoreEntryLinks = data.get("restoreEntryLinks");
		List<String> restoreLinks = data.get("restoreLinks");
		List<String> restoreMessages = data.get("restoreMessages");
		%>

		<c:choose>
			<c:when test="<%= (data != null) && (restoreLinks != null) && (restoreMessages != null) && (restoreLinks.size() > 0) && (restoreMessages.size() > 0) %>">

				<%
				for (int i = 0; i < restoreLinks.size(); i++) {
					String type = "selected-item";

					String restoreClassName = restoreClassNames.get(i);

					if (Validator.isNotNull(restoreClassName)) {
						type = ResourceActionsUtil.getModelResource(request, restoreClassName);
					}
				%>

					<liferay-util:buffer
						var="entityLink"
					>
						<em class="restore-entry-title"><aui:a cssClass="alert-link" href="<%= restoreEntryLinks.get(i) %>" label="<%= HtmlUtil.escape(restoreEntryMessages.get(i)) %>" /></em>
					</liferay-util:buffer>

					<liferay-util:buffer
						var="link"
					>
						<em class="restore-entry-title"><aui:a cssClass="alert-link" href="<%= restoreLinks.get(i) %>" label="<%= HtmlUtil.escape(restoreMessages.get(i)) %>" /></em>
					</liferay-util:buffer>

					<liferay-ui:message arguments="<%= new Object[] {type, entityLink.trim(), link.trim()} %>" key="the-x-x-was-restored-to-x" translateArguments="<%= false %>" />

				<%
				}
				%>

			</c:when>
			<c:otherwise>
				<liferay-ui:message key="the-item-was-restored" />
			</c:otherwise>
		</c:choose>
	</liferay-util:buffer>

	<liferay-ui:success key="<%= portletDisplay.getId() + SessionMessages.KEY_SUFFIX_DELETE_SUCCESS_DATA %>" message="<%= alertMessage %>" />
</c:if>

<portlet:actionURL name="moveEntry" var="selectContainerURL" />

<aui:form action="<%= selectContainerURL %>" method="post" name="selectContainerForm">
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="className" type="hidden" value="" />
	<aui:input name="classPK" type="hidden" value="" />
	<aui:input name="containerModelId" type="hidden" value="" />
</aui:form>