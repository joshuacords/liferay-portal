/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.provisioning.constants.ProvisioningActionKeys;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductConsumptionWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.license.helper.constants.ProductVersion;
import com.liferay.osb.provisioning.license.model.LicenseEntry;
import com.liferay.osb.provisioning.license.permission.LicenseKeyPermission;
import com.liferay.osb.provisioning.license.service.LicenseEntryLocalService;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.osb.provisioning.web.internal.configuration.ProvisioningWebConfiguration;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.Format;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuanyuan Huang
 */
public class AddLicenseKeyDisplayContext {

	public AddLicenseKeyDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest,
		LicenseEntryLocalService licenseEntryLocalService,
		LicenseKeyPermission licenseKeyPermission,
		ProductConsumptionWebService productConsumptionWebService,
		ProductWebService productWebService,
		ProductPurchaseViewWebService productPurchaseViewWebService,
		ProvisioningWebConfiguration provisioningWebConfiguration) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
		_licenseEntryLocalService = licenseEntryLocalService;
		_licenseKeyPermission = licenseKeyPermission;
		_productConsumptionWebService = productConsumptionWebService;
		_productWebService = productWebService;
		_productPurchaseViewWebService = productPurchaseViewWebService;
		_provisioningWebConfiguration = provisioningWebConfiguration;

		_currentURLObj = PortletURLUtil.getCurrent(
			_renderRequest, _renderResponse);

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_account = (Account)renderRequest.getAttribute(
			ProvisioningWebKeys.ACCOUNT);
	}

	public Map<String, Object> getAddLicenseKeyData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		data.put(
			"hasUpdateLicenseDatePermission",
			_licenseKeyPermission.contains(
				_themeDisplay.getPermissionChecker(),
				ProvisioningActionKeys.UPDATE_LICENSE_DATE));

		String redirect = ParamUtil.getString(_httpServletRequest, "redirect");

		data.put("redirect", redirect);

		PortletURL selectAccountActionURL = _renderResponse.createActionURL();

		selectAccountActionURL.setParameter(
			ActionRequest.ACTION_NAME, "/licenses/select_account");
		selectAccountActionURL.setParameter("redirect", redirect);

		data.put("selectAccountActionURL", selectAccountActionURL.toString());

		PortletURL selectAccountRenderURL = _renderResponse.createRenderURL();

		selectAccountRenderURL.setParameter(
			"mvcRenderCommandName", "/licenses/select_account");
		selectAccountRenderURL.setWindowState(LiferayWindowState.POP_UP);

		data.put("selectAccountRenderURL", selectAccountRenderURL.toString());

		if (_account == null) {
			return data;
		}

		data.put("accountKey", _account.getKey());
		data.put("accountName", _account.getName());

		PortletURL addLicenseKeyURL = _renderResponse.createActionURL();

		addLicenseKeyURL.setParameter(
			ActionRequest.ACTION_NAME, "/licenses/edit_license_key");
		addLicenseKeyURL.setParameter("redirect", redirect);

		data.put("addLicenseKeyURL", addLicenseKeyURL.toString());

		data.put("allowPermanentLicenses", _isAllowPermanentLicenses());

		String productKey = ParamUtil.getString(_renderRequest, "productKey");

		if (Validator.isNotNull(productKey)) {
			data.put("currentProduct", productKey);
		}

		data.put("description", _account.getName());
		data.put("licensableProducts", _getLicensableProductsJSONArray());
		data.put("owner", _account.getName());
		data.put("purchasedProducts", _getPurchasedProductsJSONObject());

		return data;
	}

	private String _formatDate(Date date) {
		if (date != null) {
			Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
				"yyyy-MM-dd");

			return dateFormat.format(date);
		}

		return StringPool.BLANK;
	}

	private JSONObject _getDetachedDetails(String productKey) throws Exception {
		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addEquals(true, "accountKey", _account.getKey());
		filterQuery.addEquals(true, "productKey", productKey);
		filterQuery.addEquals(true, "productPurchaseKey", (String)null);

		long productConsumptionsCount =
			_productConsumptionWebService.searchCount(filterQuery);

		List<Integer> sizing = new ArrayList<>();

		for (int i = 0; i <= 4; i++) {
			sizing.add(i);
		}

		return JSONUtil.put(
			"instanceSizes", sizing
		).put(
			"licenseKeysGenerated", productConsumptionsCount
		);
	}

	private JSONArray _getLicensableProductsJSONArray() throws Exception {
		JSONArray licensableProductsJSONArray =
			JSONFactoryUtil.createJSONArray();

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addEquals(true, "property_licenses", "true");

		List<Product> products = _productWebService.search(
			StringPool.BLANK, filterQuery, 1, 1000, StringPool.BLANK);

		for (Product product : products) {
			String[] versions = ProductVersion.getProductVersions(
				product.getName(), false);

			if (ArrayUtil.isEmpty(versions)) {
				continue;
			}

			licensableProductsJSONArray.put(
				JSONUtil.put(
					"detached", _getDetachedDetails(product.getKey())
				).put(
					"productKey", product.getKey()
				).put(
					"productName", product.getName()
				).put(
					"productVersions",
					_getProductVersionsJSONObject(versions, product.getKey())
				));
		}

		return licensableProductsJSONArray;
	}

	private JSONObject _getProductVersionsJSONObject(
			String[] versions, String productKey)
		throws Exception {

		JSONObject productVersionsJSONObject =
			JSONFactoryUtil.createJSONObject();

		for (String version : versions) {
			if (ArrayUtil.contains(
					_provisioningWebConfiguration.addLicenseHiddenVersions(),
					version)) {

				continue;
			}

			List<LicenseEntry> licenseEntries =
				_licenseEntryLocalService.getLicenseEntriesByVersion(
					productKey, version, false);

			JSONArray licenseEntriesJSONArray =
				JSONFactoryUtil.createJSONArray();

			for (LicenseEntry licenseEntry : licenseEntries) {
				licenseEntriesJSONArray.put(
					JSONUtil.put(
						"licenseEntryDisplayName", licenseEntry.getDisplayName()
					).put(
						"licenseEntryId", licenseEntry.getLicenseEntryId()
					).put(
						"licenseEntryName", licenseEntry.getName()
					).put(
						"licenseEntryType", licenseEntry.getType()
					));
			}

			productVersionsJSONObject.put(version, licenseEntriesJSONArray);
		}

		return productVersionsJSONObject;
	}

	private JSONObject _getPurchasedProductsJSONObject() throws Exception {
		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addEquals(true, "accountKey", _account.getKey());
		filterQuery.addEquals(true, "property_licenses", "true");

		List<ProductPurchaseView> productPurchaseViews =
			_productPurchaseViewWebService.search(
				StringPool.BLANK, filterQuery, 1, 1000, StringPool.BLANK);

		JSONObject purchasedProductsJSONObject =
			JSONFactoryUtil.createJSONObject();

		if (productPurchaseViews.isEmpty()) {
			return purchasedProductsJSONObject;
		}

		for (ProductPurchaseView productPurchaseView : productPurchaseViews) {
			if (productPurchaseView.getProductConsumptions() != null) {
				Map<String, List<ProductConsumption>> productConsumptionsMap =
					new HashMap<>();

				for (ProductConsumption productConsumption :
						productPurchaseView.getProductConsumptions()) {

					List<ProductConsumption> productConsumptions =
						productConsumptionsMap.get(
							productConsumption.getProductPurchaseKey());

					if (productConsumptions == null) {
						productConsumptions = new ArrayList<>();

						productConsumptionsMap.put(
							productConsumption.getProductPurchaseKey(),
							productConsumptions);
					}

					productConsumptions.add(productConsumption);
				}

				if (ArrayUtil.isNotEmpty(
						productPurchaseView.getProductPurchases())) {

					Product product = productPurchaseView.getProduct();

					JSONArray productPurchasesJSONArray =
						JSONFactoryUtil.createJSONArray();

					for (ProductPurchase productPurchase :
							productPurchaseView.getProductPurchases()) {

						Map<String, String> properties =
							productPurchase.getProperties();

						int sizing = 0;

						if (properties != null) {
							sizing = GetterUtil.getInteger(
								properties.get("sizing"));
						}

						int provisionedCount = 0;

						List<ProductConsumption> productConsumptions =
							productConsumptionsMap.get(
								productPurchase.getKey());

						if (productConsumptions != null) {
							provisionedCount = productConsumptions.size();
						}

						String licenseKeysGenerated =
							provisionedCount + " / " +
								productPurchase.getQuantity();

						productPurchasesJSONArray.put(
							JSONUtil.put(
								"endDate",
								_formatDate(productPurchase.getEndDate())
							).put(
								"instanceSize", sizing
							).put(
								"licenseKeysGenerated", licenseKeysGenerated
							).put(
								"perpetual", productPurchase.getPerpetual()
							).put(
								"productPurchaseKey", productPurchase.getKey()
							).put(
								"startDate",
								_formatDate(productPurchase.getStartDate())
							));
					}

					purchasedProductsJSONObject.put(
						product.getKey(), productPurchasesJSONArray);
				}
			}
		}

		return purchasedProductsJSONObject;
	}

	private boolean _isAllowPermanentLicenses() {
		Map<String, String> properties = _account.getProperties();

		if (properties != null) {
			return GetterUtil.getBoolean(
				properties.get("allowPermanentLicenses"), true);
		}

		return true;
	}

	private final Account _account;
	private final PortletURL _currentURLObj;
	private final HttpServletRequest _httpServletRequest;
	private final LicenseEntryLocalService _licenseEntryLocalService;
	private final LicenseKeyPermission _licenseKeyPermission;
	private final ProductConsumptionWebService _productConsumptionWebService;
	private final ProductPurchaseViewWebService _productPurchaseViewWebService;
	private final ProductWebService _productWebService;
	private final ProvisioningWebConfiguration _provisioningWebConfiguration;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final ThemeDisplay _themeDisplay;

}