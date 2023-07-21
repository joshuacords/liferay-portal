<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<portlet:actionURL name="edit" var="editCurrency" />

<aui:form action="<%= editCurrency %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="symbols" type="hidden" value="" />

	<%
	List leftList = new ArrayList();

	for (int i = 0; i < symbols.length; i++) {
		leftList.add(new KeyValuePair(symbols[i], LanguageUtil.get(request, "currency." + symbols[i])));
	}

	List rightList = new ArrayList();

	Arrays.sort(symbols);

	for (Map.Entry<String, String> entry : allSymbols.entrySet()) {
		String symbol = entry.getValue();
		String currencyValue = entry.getKey();

		if (Arrays.binarySearch(symbols, symbol) < 0) {
			rightList.add(new KeyValuePair(symbol, LanguageUtil.get(request, "currency." + currencyValue)));
		}
	}

	rightList = ListUtil.sort(rightList, new KeyValuePairComparator(false, true));
	%>

	<liferay-ui:input-move-boxes
		leftBoxName="current_actions"
		leftList="<%= leftList %>"
		leftReorder="<%= Boolean.TRUE.toString() %>"
		leftTitle="current"
		rightBoxName="available_actions"
		rightList="<%= rightList %>"
		rightTitle="available"
	/>

	<aui:button onClick='<%= renderResponse.getNamespace() + "saveCurrency();" %>' primary="<%= true %>" value="save" />
</aui:form>

<aui:script>
	function <portlet:namespace />saveCurrency() {
		var form = document.getElementById('<portlet:namespace />fm');

		if (form) {
			var currentActions = form.querySelector(
				'#<portlet:namespace />current_actions'
			);
			var symbols = form.querySelector('#<portlet:namespace />symbols');

			if (currentActions && symbols) {
				symbols.setAttribute(
					'value',
					Liferay.Util.listSelect(currentActions)
				);

				submitForm(form);
			}
		}
	}
</aui:script>