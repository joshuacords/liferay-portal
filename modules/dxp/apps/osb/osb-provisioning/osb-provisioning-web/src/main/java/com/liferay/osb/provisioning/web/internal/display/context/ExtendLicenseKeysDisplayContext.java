/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.provisioning.constants.ProvisioningActionKeys;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.provisioning.license.helper.constants.LicenseLifetime;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.permission.LicenseKeyPermission;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.Format;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuanyuan Huang
 */
public class ExtendLicenseKeysDisplayContext {

	public ExtendLicenseKeysDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest,
			LicenseKeyPermission licenseKeyPermission,
			ProductPurchaseViewWebService productPurchaseViewWebService)
		throws Exception {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
		_licenseKeyPermission = licenseKeyPermission;
		_productPurchaseViewWebService = productPurchaseViewWebService;

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_account = (Account)renderRequest.getAttribute(
			ProvisioningWebKeys.ACCOUNT);
		_licenseKey = (LicenseKey)renderRequest.getAttribute(
			ProvisioningWebKeys.LICENSE_KEY);
		_licenseKeys = (List<LicenseKey>)renderRequest.getAttribute(
			ProvisioningWebKeys.LICENSE_KEYS);

		if (_account != null) {
			_renderResponse.setTitle(
				StringBundler.concat(
					_account.getCode(), StringPool.SPACE,
					LanguageUtil.get(_httpServletRequest, "extend-licenses")));
		}
	}

	public Map<String, Object> getData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		JSONArray licenseKeyDetailsJSONArray =
			JSONFactoryUtil.createJSONArray();

		PortletURL portletURL = _renderResponse.createActionURL();

		if (_licenseKey != null) {
			portletURL.setParameter(
				ActionRequest.ACTION_NAME, "/licenses/extend_license_key");

			PortletURL redirect = PortletURLFactoryUtil.create(
				_renderRequest, ProvisioningPortletKeys.LICENSES,
				PortletRequest.RENDER_PHASE);

			portletURL.setParameter("redirect", redirect.toString());

			licenseKeyDetailsJSONArray.put(_getLicenseKeyDetails(_licenseKey));
		}
		else {
			String redirect = ParamUtil.getString(_renderRequest, "redirect");

			portletURL.setParameter(
				ActionRequest.ACTION_NAME, "/accounts/extend_license_keys");
			portletURL.setParameter("redirect", redirect);

			for (LicenseKey licenseKey : _licenseKeys) {
				licenseKeyDetailsJSONArray.put(
					_getLicenseKeyDetails(licenseKey));
			}
		}

		data.put("details", licenseKeyDetailsJSONArray);
		data.put("extensionURL", portletURL.toString());
		data.put(
			"hasUpdateLicenseDatePermission",
			_licenseKeyPermission.contains(
				_themeDisplay.getPermissionChecker(),
				ProvisioningActionKeys.UPDATE_LICENSE_DATE));

		return data;
	}

	public String getTitle() throws Exception {
		if (_licenseKey != null) {
			return LanguageUtil.get(_httpServletRequest, "extend-license");
		}

		return LanguageUtil.get(_httpServletRequest, "extend-licenses");
	}

	private String _formatDate(Date date) {
		if (date != null) {
			Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
				"yyyy-MM-dd");

			return dateFormat.format(date);
		}

		return StringPool.BLANK;
	}

	private JSONObject _getLicenseKeyDetails(LicenseKey licenseKey)
		throws Exception {

		ProductPurchaseView productPurchaseView =
			_productPurchaseViewWebService.getProductPurchaseView(
				licenseKey.getAccountKey(), licenseKey.getProductKey());

		return JSONUtil.put(
			"accountName", licenseKey.getAccountName()
		).put(
			"expirationDate", _formatDate(licenseKey.getExpirationDate())
		).put(
			"indefinite",
			_isIndefinite(
				licenseKey.getStartDate(), licenseKey.getExpirationDate())
		).put(
			"licenseKeyId", licenseKey.getLicenseKeyId()
		).put(
			"licenseKeysGenerated",
			_getLicenseKeysGenerated(productPurchaseView)
		).put(
			"licenseType", licenseKey.getLicenseEntryType()
		).put(
			"productName", licenseKey.getProductName()
		).put(
			"startDate", _formatDate(licenseKey.getStartDate())
		).put(
			"terms", _getTerms(licenseKey, productPurchaseView)
		);
	}

	private int _getLicenseKeysGenerated(
			ProductPurchaseView productPurchaseView)
		throws Exception {

		int provisionedCount = 0;

		if (productPurchaseView.getProductConsumptions() != null) {
			ProductConsumption[] productConsumptions =
				productPurchaseView.getProductConsumptions();

			if (productConsumptions != null) {
				provisionedCount = productConsumptions.length;
			}
		}

		return provisionedCount;
	}

	private JSONArray _getTerms(
			LicenseKey licenseKey, ProductPurchaseView productPurchaseView)
		throws Exception {

		if (Validator.isNull(licenseKey.getProductPurchaseKey())) {
			return null;
		}

		if (ArrayUtil.isNotEmpty(productPurchaseView.getProductPurchases())) {
			JSONArray productPurchasesJSONArray =
				JSONFactoryUtil.createJSONArray();

			for (ProductPurchase productPurchase :
					productPurchaseView.getProductPurchases()) {

				ProductPurchase.Status status = productPurchase.getStatus();

				productPurchasesJSONArray.put(
					JSONUtil.put(
						"endDate", _formatDate(productPurchase.getEndDate())
					).put(
						"licenseKeysAllowed", productPurchase.getQuantity()
					).put(
						"licenseKeysGenerated",
						_getLicenseKeysGenerated(productPurchaseView)
					).put(
						"perpetual", productPurchase.getPerpetual()
					).put(
						"productPurchaseKey", productPurchase.getKey()
					).put(
						"startDate", _formatDate(productPurchase.getStartDate())
					).put(
						"status", status.toString()
					));
			}

			return productPurchasesJSONArray;
		}

		return null;
	}

	private boolean _isIndefinite(Date startDate, Date expirationDate) {
		long time = expirationDate.getTime() - startDate.getTime();

		if (time < LicenseLifetime.INDEFINITE) {
			return false;
		}

		return true;
	}

	private final Account _account;
	private final HttpServletRequest _httpServletRequest;
	private final LicenseKey _licenseKey;
	private final LicenseKeyPermission _licenseKeyPermission;
	private final List<LicenseKey> _licenseKeys;
	private final ProductPurchaseViewWebService _productPurchaseViewWebService;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final ThemeDisplay _themeDisplay;

}