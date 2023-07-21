<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CommerceCurrenciesDisplayContext commerceCurrenciesDisplayContext = (CommerceCurrenciesDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceCurrencyConfiguration commerceCurrencyConfiguration = commerceCurrenciesDisplayContext.getCommerceCurrencyConfiguration();

boolean enableAutoUpdate = commerceCurrencyConfiguration.enableAutoUpdate();
%>

<c:if test="<%= commerceCurrenciesDisplayContext.hasManageCommerceCurrencyPermission() %>">
	<div class="container-fluid-1280" id="<portlet:namespace />exchangeRateContainer">
		<portlet:actionURL name="editExchangeRate" var="editExchangeRateActionURL" />

		<aui:form action="<%= editExchangeRateActionURL %>" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
			<aui:input name="exchangeRateConfiguration--groupId--" type="hidden" value="<%= scopeGroupId %>" />

			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset>
					<aui:select id="exchangeRateConfiguration--defaultExchangeRateProviderKey--" label="exchange-rate-provider" name="exchangeRateConfiguration--defaultExchangeRateProviderKey--" showEmptyOption="<%= true %>">

						<%
						for (String exchangeRateProviderKey : commerceCurrenciesDisplayContext.getExchangeRateProviderKeys()) {
						%>

							<aui:option label="<%= LanguageUtil.get(request, exchangeRateProviderKey) %>" selected="<%= exchangeRateProviderKey.equals(commerceCurrencyConfiguration.defaultExchangeRateProviderKey()) %>" value="<%= exchangeRateProviderKey %>" />

						<%
						}
						%>

					</aui:select>

					<aui:input id="exchangeRateConfiguration--enableAutoUpdate--" name="exchangeRateConfiguration--enableAutoUpdate--" type="toggle-switch" value="<%= enableAutoUpdate %>" />
				</aui:fieldset>
			</aui:fieldset-group>

			<aui:button name="saveButton" type="submit" value="save" />
		</aui:form>
	</div>
</c:if>