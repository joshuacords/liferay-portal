<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

SyncDevice syncDevice = (SyncDevice)row.getObject();

String syncDeviceId = String.valueOf(syncDevice.getSyncDeviceId());
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:choose>
		<c:when test="<%= syncDevice.getStatus() == SyncDeviceConstants.STATUS_ACTIVE %>">
			<portlet:actionURL name="updateDevice" var="disableDeviceURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="status" value="<%= String.valueOf(SyncDeviceConstants.STATUS_INACTIVE) %>" />
				<portlet:param name="syncDeviceId" value="<%= syncDeviceId %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				label="<%= true %>"
				message="disable-sync-device"
				url="<%= disableDeviceURL %>"
			/>
		</c:when>
		<c:otherwise>
			<c:if test="<%= syncDevice.getStatus() != SyncDeviceConstants.STATUS_WIPED %>">
				<portlet:actionURL name="updateDevice" var="enableDeviceURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="status" value="<%= String.valueOf(SyncDeviceConstants.STATUS_ACTIVE) %>" />
					<portlet:param name="syncDeviceId" value="<%= syncDeviceId %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					label="<%= true %>"
					message="enable-sync-device"
					url="<%= enableDeviceURL %>"
				/>
			</c:if>

			<c:if test="<%= syncDevice.getStatus() == SyncDeviceConstants.STATUS_INACTIVE %>">
				<portlet:actionURL name="updateDevice" var="wipeDeviceURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="status" value="<%= String.valueOf(SyncDeviceConstants.STATUS_PENDING_WIPE) %>" />
					<portlet:param name="syncDeviceId" value="<%= syncDeviceId %>" />
				</portlet:actionURL>

				<liferay-ui:icon-delete
					confirmation="wiping-a-sync-device-will-delete-all-associated-files-from-the-client"
					label="<%= true %>"
					message="wipe-sync-device"
					url="<%= wipeDeviceURL %>"
				/>
			</c:if>

			<portlet:actionURL name="deleteDevice" var="deleteDeviceURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="syncDeviceId" value="<%= syncDeviceId %>" />
			</portlet:actionURL>

			<liferay-ui:icon-delete
				label="<%= true %>"
				message="delete-sync-device"
				url="<%= deleteDeviceURL %>"
			/>
		</c:otherwise>
	</c:choose>
</liferay-ui:icon-menu>