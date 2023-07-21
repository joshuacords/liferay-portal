<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
UADInfoPanelDisplay uadInfoPanelDisplay = (UADInfoPanelDisplay)request.getAttribute(UADWebKeys.UAD_INFO_PANEL_DISPLAY);
%>

<div class="sidebar sidebar-light">
	<c:choose>
		<c:when test="<%= uadInfoPanelDisplay.getUADEntitiesCount() == 0 %>">
			<div class="sidebar-header">
				<c:if test="<%= uadInfoPanelDisplay.getTitle(locale) != null %>">
					<h3 class="sidebar-title"><%= uadInfoPanelDisplay.getTitle(locale) %></h3>
				</c:if>

				<h5 class="sidebar-subtitle"><%= uadInfoPanelDisplay.getSubtitle(locale) %></h5>
			</div>
		</c:when>
		<c:when test="<%= uadInfoPanelDisplay.getUADEntitiesCount() == 1 %>">

			<%
			UADDisplay uadDisplay = uadInfoPanelDisplay.getUADDisplay();

			UADEntity uadEntity = uadInfoPanelDisplay.getFirstUADEntity();

			Serializable primaryKey = uadEntity.getPrimaryKey();

			Map<String, Object> displayValues = uadDisplay.getFieldValues(uadEntity.getEntity(), uadDisplay.getDisplayFieldNames(), locale);

			String identifierFieldName = uadDisplay.getDisplayFieldNames()[0];
			%>

			<div class="sidebar-header">
				<ul class="sidebar-header-actions">
					<li>
						<%@ include file="/single_entity_action_menu.jspf" %>
					</li>
				</ul>

				<h3 class="sidebar-title"><%= uadInfoPanelDisplay.getTitle(locale) %></h3>

				<h5 class="sidebar-subtitle"><%= uadInfoPanelDisplay.getSubtitle(locale) %></h5>
			</div>

			<div class="sidebar-body">
				<dl class="sidebar-block sidebar-dl sidebar-section">
					<dt class="sidebar-dt"><%= LanguageUtil.get(request, "primary-key") %></dt>
					<dd class="sidebar-dd"><%= primaryKey %></dd>

					<%
					displayValues = new TreeMap<>(displayValues);

					for (Map.Entry<String, Object> entry : displayValues.entrySet()) {
						if (identifierFieldName.equals(entry.getKey())) {
							continue;
						}
					%>

						<dt class="sidebar-dt"><%= entry.getKey() %></dt>
						<dd class="sidebar-dd"><%= SafeDisplayValueUtil.get(entry.getValue()) %></dd>

					<%
					}
					%>

				</dl>
			</div>
		</c:when>
		<c:when test="<%= uadInfoPanelDisplay.getUADEntitiesCount() > 1 %>">
			<div class="sidebar-header">
				<c:if test="<%= uadInfoPanelDisplay.getTitle(locale) != null %>">
					<h3 class="sidebar-title"><%= uadInfoPanelDisplay.getTitle(locale) %></h3>
				</c:if>

				<h5 class="sidebar-subtitle"><%= uadInfoPanelDisplay.getSubtitle(locale) %></h5>
			</div>
		</c:when>
	</c:choose>
</div>