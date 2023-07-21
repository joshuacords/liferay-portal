<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

ServletContext commerceAdminServletContext = (ServletContext)request.getAttribute(CommerceAdminWebKeys.COMMERCE_ADMIN_SERVLET_CONTEXT);

CommerceCurrenciesDisplayContext commerceCurrenciesDisplayContext = (CommerceCurrenciesDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceCurrency commerceCurrency = commerceCurrenciesDisplayContext.getCommerceCurrency();
CommerceCurrency primaryCommerceCurrency = commerceCurrenciesDisplayContext.getPrimaryCommerceCurrency();

String roundingMode = BeanParamUtil.getString(commerceCurrency, request, "roundingMode", commerceCurrenciesDisplayContext.getDefaultRoundingMode());

boolean primary = BeanParamUtil.getBoolean(commerceCurrency, request, "primary");

String title = LanguageUtil.get(request, "add-currency");

if (commerceCurrency != null) {
	title = LanguageUtil.format(request, "edit-x", commerceCurrency.getName(locale), false);
}

Map<String, Object> data = new HashMap<>();

data.put("direction-right", StringPool.TRUE);

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, commerceAdminModuleKey), redirect, data);
PortalUtil.addPortletBreadcrumbEntry(request, title, StringPool.BLANK, data);
%>

<liferay-util:include page="/navbar.jsp" servletContext="<%= commerceAdminServletContext %>">
	<liferay-util:param name="commerceAdminModuleKey" value="<%= commerceAdminModuleKey %>" />
</liferay-util:include>

<%@ include file="/breadcrumb.jspf" %>

<portlet:actionURL name="editCommerceCurrency" var="editCommerceCurrencyActionURL" />

<aui:form action="<%= editCommerceCurrencyActionURL %>" cssClass="container-fluid-1280" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveCommerceCurrency();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commerceCurrency == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="commerceCurrencyId" type="hidden" value="<%= (commerceCurrency == null) ? 0 : commerceCurrency.getCommerceCurrencyId() %>" />

	<div class="lfr-form-content">
		<liferay-ui:error exception="<%= CommerceCurrencyCodeException.class %>" message="please-enter-a-valid-code" />
		<liferay-ui:error exception="<%= CommerceCurrencyFractionDigitsException.class %>" message="the-decimal-place-bounds-are-invalid" />
		<liferay-ui:error exception="<%= CommerceCurrencyNameException.class %>" message="please-enter-a-valid-name" />

		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:input bean="<%= commerceCurrency %>" model="<%= CommerceCurrency.class %>" name="name" />

				<aui:input bean="<%= commerceCurrency %>" model="<%= CommerceCurrency.class %>" name="code" />

				<aui:input bean="<%= commerceCurrency %>" model="<%= CommerceCurrency.class %>" name="symbol" />

				<aui:input bean="<%= commerceCurrency %>" model="<%= CommerceCurrency.class %>" name="formatPattern" value="<%= commerceCurrenciesDisplayContext.getDefaultFormatPattern() %>" />

				<aui:input bean="<%= commerceCurrency %>" label="maximum-decimal-places" model="<%= CommerceCurrency.class %>" name="maxFractionDigits" value="<%= String.valueOf(commerceCurrenciesDisplayContext.getDefaultMaxFractionDigits()) %>">
					<aui:validator name="number" />
					<aui:validator name="min">0</aui:validator>
				</aui:input>

				<aui:input bean="<%= commerceCurrency %>" label="minimum-decimal-places" model="<%= CommerceCurrency.class %>" name="minFractionDigits" value="<%= String.valueOf(commerceCurrenciesDisplayContext.getDefaultMinFractionDigits()) %>">
					<aui:validator name="number" />
					<aui:validator name="min">0</aui:validator>
				</aui:input>

				<aui:select bean="<%= commerceCurrency %>" model="<%= CommerceCurrency.class %>" name="roundingMode">

					<%
					for (RoundingMode curRoundingMode : RoundingMode.values()) {
					%>

						<aui:option label="<%= commerceCurrenciesDisplayContext.getRoundingModeLabel(curRoundingMode.name()) %>" selected="<%= roundingMode.equals(curRoundingMode.name()) %>" value="<%= curRoundingMode.name() %>" />

					<%
					}
					%>

				</aui:select>

				<aui:input bean="<%= commerceCurrency %>" model="<%= CommerceCurrency.class %>" name="primary" type="toggle-switch" value="<%= primary %>" />

				<%
				String taglibLabel = "exchange-rate";

				if (primaryCommerceCurrency != null) {
					taglibLabel = LanguageUtil.format(request, "exchange-rate-with-x", primaryCommerceCurrency.getName(locale), false);
				}
				%>

				<div class="<%= primary ? "hide" : StringPool.BLANK %>" id="<portlet:namespace />rateOptions">
					<aui:input label="<%= taglibLabel %>" name="rate" type="text" value="<%= (commerceCurrency == null) ? BigDecimal.ZERO : commerceCurrency.round(commerceCurrency.getRate()) %>">
						<aui:validator name="number" />
					</aui:input>
				</div>

				<aui:input bean="<%= commerceCurrency %>" model="<%= CommerceCurrency.class %>" name="priority" />

				<aui:input bean="<%= commerceCurrency %>" checked="<%= (commerceCurrency == null) ? false : commerceCurrency.isActive() %>" model="<%= CommerceCurrency.class %>" name="active" type="toggle-switch" />
			</aui:fieldset>
		</aui:fieldset-group>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />saveCommerceCurrency() {
		submitForm(document.<portlet:namespace />fm);
	}

	Liferay.Util.toggleBoxes(
		'<portlet:namespace />primary',
		'<portlet:namespace />rateOptions',
		true
	);
</aui:script>