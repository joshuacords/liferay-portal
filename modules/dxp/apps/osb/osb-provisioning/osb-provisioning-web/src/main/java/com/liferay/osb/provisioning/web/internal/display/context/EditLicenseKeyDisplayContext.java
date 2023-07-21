/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.provisioning.constants.ProvisioningActionKeys;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.provisioning.license.helper.constants.LicenseLifetime;
import com.liferay.osb.provisioning.license.helper.constants.LicenseType;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.permission.LicenseKeyPermission;
import com.liferay.osb.provisioning.license.service.LicenseKeyLocalService;
import com.liferay.osb.provisioning.license.util.LicenseUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuanyuan Huang
 */
public class EditLicenseKeyDisplayContext {

	public EditLicenseKeyDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest,
			LicenseKeyLocalService licenseKeyLocalService,
			LicenseKeyPermission licenseKeyPermission,
			ProductPurchaseViewWebService productPurchaseViewWebService)
		throws Exception {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
		_licenseKeyLocalService = licenseKeyLocalService;
		_licenseKeyPermission = licenseKeyPermission;
		_productPurchaseViewWebService = productPurchaseViewWebService;

		_licenseKey = (LicenseKey)renderRequest.getAttribute(
			ProvisioningWebKeys.LICENSE_KEY);
		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_licenseKeyDisplay = new LicenseKeyDisplay(
			renderRequest, renderResponse, _licenseKey);

		_licenseType = _licenseKey.getLicenseEntryType();
		_licenseVersion = _licenseKey.getLicenseVersion();
	}

	public String getAccountProductPurchasesURL() throws Exception {
		PortletURL portletURL = PortletURLFactoryUtil.create(
			_renderRequest, ProvisioningPortletKeys.ACCOUNTS,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/view_subscription");
		portletURL.setParameter("accountKey", _licenseKey.getAccountKey());
		portletURL.setParameter("productKey", _licenseKey.getProductKey());

		return portletURL.toString();
	}

	public String getAccountURL() throws Exception {
		PortletURL portletURL = PortletURLFactoryUtil.create(
			_renderRequest, ProvisioningPortletKeys.ACCOUNTS,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/view_account");
		portletURL.setParameter("accountKey", _licenseKey.getAccountKey());

		return portletURL.toString();
	}

	public List<LicenseKey> getClusterLicenseKeys() {
		String productPurchaseKey = null;

		if (Validator.isNotNull(_licenseKey.getProductPurchaseKey())) {
			productPurchaseKey = _licenseKey.getProductPurchaseKey();
		}

		return _licenseKeyLocalService.search(
			null, null, null, null, null, null, _licenseKey.getAccountKey(),
			productPurchaseKey, null, null, null, new long[0],
			new String[] {_licenseKey.getProductKey()}, null, null,
			new String[] {_licenseKey.getProductVersion()},
			new long[] {_licenseKey.getClusterId()}, _licenseKey.getOwner(),
			null, null, null, null, null, null, null, null,
			new LinkedHashMap<>(), true, 0, 1000,
			LicenseUtil.getLicenseKeyOrderByComparator(
				"expiration-date", "desc"));
	}

	public String getClusterLicenseKeysDisplay() throws Exception {
		List<LicenseKey> licenseKeys = getClusterLicenseKeys();

		if (licenseKeys.isEmpty()) {
			return StringPool.DASH;
		}

		StringBundler sb = new StringBundler((licenseKeys.size() * 2) - 1);

		for (int i = 0; i < licenseKeys.size(); i++) {
			LicenseKey licenseKey = licenseKeys.get(i);

			LicenseKeyDisplay licenseKeyDisplay = new LicenseKeyDisplay(
				_renderRequest, _renderResponse, licenseKey);

			sb.append(licenseKeyDisplay.getServerId());

			if ((i + 1) < licenseKeys.size()) {
				sb.append(StringPool.SPACE);
			}
		}

		return sb.toString();
	}

	public String getDownloadLicenseKeyURL() throws Exception {
		ResourceURL resourceURL = _renderResponse.createResourceURL();

		resourceURL.setParameter(
			"licenseKeyId", String.valueOf(_licenseKey.getLicenseKeyId()));
		resourceURL.setResourceID("/licenses/download_license_key");

		return resourceURL.toString();
	}

	public String getEditLicenseKeyURL() throws Exception {
		PortletURL portletURL = _renderResponse.createActionURL();

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "/licenses/edit_license_key");

		return portletURL.toString();
	}

	public String getExpirationDateLabel() {
		return LanguageUtil.get(_httpServletRequest, "expiration-date");
	}

	public String getExtendLicenseKeysURL() throws Exception {
		String redirect = ParamUtil.getString(_renderRequest, "redirect");

		PortletURL portletURL = PortletURLFactoryUtil.create(
			_renderRequest, ProvisioningPortletKeys.LICENSES,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"mvcRenderCommandName", "/licenses/extend_license_key");
		portletURL.setParameter("redirect", redirect);
		portletURL.setParameter(
			"licenseKeyId", String.valueOf(_licenseKey.getLicenseKeyId()));

		return portletURL.toString();
	}

	public String getLastModifiedUserNameDate() {
		return _licenseKey.getModifiedUserName() + " On " +
			_licenseKeyDisplay.getModifiedDate();
	}

	public LicenseKey getLicenseKey() {
		return _licenseKey;
	}

	public LicenseKeyDisplay getLicenseKeyDisplay() {
		return _licenseKeyDisplay;
	}

	public String getMoveLicenseKeyURL() throws Exception {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setWindowState(LiferayWindowState.POP_UP);
		portletURL.setParameter(
			"mvcRenderCommandName", "/licenses/move_license_key");
		portletURL.setParameter(
			"licenseKeyId", String.valueOf(_licenseKey.getLicenseKeyId()));

		return portletURL.toString();
	}

	public Map<String, Object> getReplaceLicenseKeyData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		PortletURL portletURL = _renderResponse.createActionURL();

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "/licenses/replace_license_key");

		data.put("replacementURL", portletURL.toString());

		return data;
	}

	public boolean hasManageLicenseKeysPermission() throws Exception {
		return _licenseKeyPermission.contains(
			_themeDisplay.getPermissionChecker(),
			ProvisioningActionKeys.MANAGE_LICENSE_KEYS);
	}

	public boolean showClusterLicenseKey() {
		if ((_licenseVersion >= 3) &&
			_licenseType.equals(LicenseType.CLUSTER)) {

			return true;
		}

		return false;
	}

	public boolean showComplimentary() {
		if ((_licenseVersion >= 3) &&
			!_licenseType.equals(LicenseType.CLUSTER)) {

			return true;
		}

		return false;
	}

	public boolean showDownload() {
		if (!showClusterLicenseKey() && _licenseKey.isActive() &&
			((_licenseVersion >= 2) ||
			 _licenseType.equals(LicenseType.CLUSTER) ||
			 _licenseType.equals(LicenseType.DEVELOPER_CLUSTER))) {

			return true;
		}

		return false;
	}

	public boolean showExtend() {
		Date expirationDate = _licenseKey.getExpirationDate();
		Date startDate = _licenseKey.getStartDate();

		long time = expirationDate.getTime() - startDate.getTime();

		if (time < LicenseLifetime.INDEFINITE) {
			return true;
		}

		return false;
	}

	public boolean showHostName() {
		return _licenseKeyDisplay.showHostName();
	}

	public boolean showIpAddresses() {
		return _licenseKeyDisplay.showIpAddresses();
	}

	public boolean showKey() {
		if (_licenseVersion == 1) {
			return true;
		}

		return false;
	}

	public boolean showMacAddresses() {
		return _licenseKeyDisplay.showMacAddresses();
	}

	public boolean showMaxClusterNodes() {
		return _licenseKeyDisplay.showMaxClusterNodes();
	}

	public boolean showMaximumConcurrentUsers() {
		if ((_licenseVersion >= 3) &&
			_licenseType.equals(LicenseType.PER_USER)) {

			return true;
		}

		return false;
	}

	public boolean showMaximumConnections() {
		if ((_licenseVersion >= 3) &&
			(_licenseType.equals(LicenseType.DEVELOPER) ||
			 _licenseType.equals(LicenseType.DEVELOPER_CLUSTER))) {

			return true;
		}

		return false;
	}

	public boolean showMaximumServers() {
		return _licenseKeyDisplay.showMaximumServers();
	}

	public boolean showMaximumUsers() {
		if ((_licenseVersion >= 3) &&
			_licenseType.equals(LicenseType.PER_USER)) {

			return true;
		}

		return false;
	}

	public boolean showServerId() {
		if (((_licenseVersion >= 3) &&
			 (_licenseType.equals(LicenseType.LIMITED) ||
			  _licenseType.equals(LicenseType.PER_USER) ||
			  _licenseType.equals(LicenseType.PRODUCTION)) &&
			 Validator.isNotNull(_licenseKey.getServerId())) ||
			((_licenseVersion == 1) &&
			 _licenseType.equals(LicenseType.PRODUCTION))) {

			return true;
		}

		return false;
	}

	private final HttpServletRequest _httpServletRequest;
	private final LicenseKey _licenseKey;
	private final LicenseKeyDisplay _licenseKeyDisplay;
	private final LicenseKeyLocalService _licenseKeyLocalService;
	private final LicenseKeyPermission _licenseKeyPermission;
	private final String _licenseType;
	private final int _licenseVersion;
	private final ProductPurchaseViewWebService _productPurchaseViewWebService;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final ThemeDisplay _themeDisplay;

}