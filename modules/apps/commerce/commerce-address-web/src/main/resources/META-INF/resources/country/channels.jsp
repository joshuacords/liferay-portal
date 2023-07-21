<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceCountriesDisplayContext commerceCountriesDisplayContext = (CommerceCountriesDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceCountry commerceCountry = commerceCountriesDisplayContext.getCommerceCountry();

List<CommerceChannel> commerceChannels = commerceCountriesDisplayContext.getCommerceChannels();
long[] commerceChannelIds = commerceCountriesDisplayContext.getCommerceChannelRelCommerceChannelIds();
%>

<portlet:actionURL name="editCommerceCountry" var="editCommerceCountryActionURL" />

<aui:form action="<%= editCommerceCountryActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="updateChannels" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="commerceCountryId" type="hidden" value="<%= commerceCountry.getCommerceCountryId() %>" />
	<aui:input name="commerceChannelIds" type="hidden" />

	<div class="lfr-form-content">
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:input checked="<%= commerceCountry.isChannelFilterEnabled() %>" label="enable-filter-channels" name="channelFilterEnabled" type="toggle-switch" value="<%= commerceCountry.isChannelFilterEnabled() %>" />

				<c:choose>
					<c:when test="<%= commerceChannels.isEmpty() %>">
						<div class="alert alert-info">
							<liferay-ui:message key="there-are-no-channels" />
						</div>
					</c:when>
					<c:otherwise>

						<%
						for (CommerceChannel commerceChannel : commerceChannels) {
						%>

							<aui:input checked="<%= ArrayUtil.contains(commerceChannelIds, commerceChannel.getCommerceChannelId()) %>" label="<%= commerceChannel.getName() %>" name='<%= "commerceChannelId_" + commerceChannel.getCommerceChannelId() %>' onChange='<%= renderResponse.getNamespace() + "fulfillCommerceChannelIds();" %>' type="checkbox" value="<%= commerceChannel.getCommerceChannelId() %>" />

						<%
						}
						%>

					</c:otherwise>
				</c:choose>

				<aui:button-row>
					<aui:button cssClass="btn-lg" type="submit" />

					<aui:button cssClass="btn-lg" href="<%= backURL %>" type="cancel" />
				</aui:button-row>
			</aui:fieldset>
		</aui:fieldset-group>
	</div>
</aui:form>

<aui:script>
	function <portlet:namespace />fulfillCommerceChannelIds(e) {
		var form = AUI.$(document.<portlet:namespace />fm);
		var values = Liferay.Util.listCheckedExcept(
			form,
			'<portlet:namespace />allRowIds'
		);
		form.fm('commerceChannelIds').val(values);
		return values;
	}

	<portlet:namespace />fulfillCommerceChannelIds();
</aui:script>