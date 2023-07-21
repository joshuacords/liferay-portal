<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-frontend:edit-form
	action="<%= configurationActionURL %>"
	method="post"
	name="fm"
>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset-group>
			<liferay-frontend:fieldset>
				<aui:input name="preferences--mapAddress--" type="text" value="<%= mapAddress %>" wrapperCssClass="lfr-input-text-container" />

				<aui:input label="allow-map-address-to-be-edited" name="preferences--mapInputEnabled--" type="toggle-switch" value="<%= mapInputEnabled %>" />

				<aui:input name="preferences--directionsAddress--" type="text" value="<%= directionsAddress %>" wrapperCssClass="lfr-input-text-container" />

				<aui:input label="allow-directions-address-to-be-edited" name="preferences--directionsInputEnabled--" type="toggle-switch" value="<%= directionsInputEnabled %>" />

				<aui:input name="preferences--showDirectionSteps--" type="toggle-switch" value="<%= showDirectionSteps %>" />

				<aui:input name="preferences--enableChangingTravelingMode--" type="toggle-switch" value="<%= enableChangingTravelingMode %>" />

				<aui:input name="preferences--height--" size="4" suffix="px" type="text" value="<%= height %>" />

				<aui:input name="preferences--showGoogleMapsLink--" type="toggle-switch" value="<%= showGoogleMapsLink %>" />
			</liferay-frontend:fieldset>
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<aui:button type="submit" />

		<aui:button href='<%= ParamUtil.getString(request, "redirect") %>' type="cancel" />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>