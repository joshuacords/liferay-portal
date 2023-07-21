<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
MoveLicenseKeyDisplayContext moveLicenseKeyDisplayContext = ProvisioningWebComponentProvider.getMoveLicenseKeyDisplayContext(renderRequest, renderResponse, request);

LicenseKey licenseKey = moveLicenseKeyDisplayContext.getLicenseKey();

String detachedLicenseKeysCount = moveLicenseKeyDisplayContext.getDetachedLicenseKeysCount();

String productPurchaseKey = StringPool.BLANK;

if (Validator.isNotNull(licenseKey.getProductPurchaseKey())) {
	productPurchaseKey = licenseKey.getProductPurchaseKey();
}

Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat("MMMM dd, yyyy");
%>

<aui:form cssClass="container-fluid-1280" name="fm">
	<liferay-ui:search-container
		searchContainer="<%= moveLicenseKeyDisplayContext.getSearchContainer() %>"
		var="productPurchasesSearchContainer"
	>
		<liferay-ui:search-container-row
			className="Object"
			modelVar="resultRow"
		>

			<%
			ProductPurchaseDisplay productPurchaseDisplay = null;

			if (resultRow instanceof ProductPurchaseDisplay) {
				productPurchaseDisplay = (ProductPurchaseDisplay)resultRow;
			}

			String curProductPurchaseKey = StringPool.BLANK;
			String licenseKeysGenerated = StringPool.DASH;
			String sizing = StringPool.DASH;
			String startDate = StringPool.DASH;
			String endDate = StringPool.DASH;

			if (productPurchaseDisplay != null) {
				curProductPurchaseKey = productPurchaseDisplay.getKey();
				sizing = productPurchaseDisplay.getSizing();

				licenseKeysGenerated = productPurchaseDisplay.getProvisionedCount() + " / " + productPurchaseDisplay.getQuantity();

				if (productPurchaseDisplay.getStartDate() != null) {
					startDate = dateFormat.format(productPurchaseDisplay.getStartDate());
				}

				if (productPurchaseDisplay.getEndDate() != null) {
					endDate = dateFormat.format(productPurchaseDisplay.getEndDate());
				}
			}
			else {
				licenseKeysGenerated = detachedLicenseKeysCount;
			}
			%>

			<liferay-ui:search-container-column-text
				name="subscription-start-date"
				value="<%= startDate %>"
			/>

			<liferay-ui:search-container-column-text
				name="subscription-end-date"
				value="<%= endDate %>"
			/>

			<liferay-ui:search-container-column-text
				name="instance-size"
				value="<%= sizing %>"
			/>

			<liferay-ui:search-container-column-text
				name="licenses-generated"
				value="<%= licenseKeysGenerated %>"
			>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text>
				<c:choose>
					<c:when test="<%= !curProductPurchaseKey.equals(productPurchaseKey) %>">

						<%
						Map<String, Object> data = new HashMap<String, Object>();

						data.put("productPurchaseKey", curProductPurchaseKey);
						%>

						<aui:button cssClass="selector-button" data="<%= data %>" value="choose" />
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="current" />
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
			paginate="<%= false %>"
			resultRowSplitter="<%= new ProductPurchaseResultRowSplitter() %>"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	Liferay.Util.selectEntityHandler('#<portlet:namespace />fm', 'moveLicenseKey');
</aui:script>