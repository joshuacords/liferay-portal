/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.license.helper.constants.LicenseSizing;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseKeyService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yuanyuan Huang
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"javax.portlet.name=" + ProvisioningPortletKeys.LICENSES,
		"mvc.command.name=/accounts/edit_license_keys",
		"mvc.command.name=/licenses/edit_license_key"
	},
	service = MVCActionCommand.class
)
public class EditLicenseKeysMVCActionCommand extends BaseMVCActionCommand {

	protected void addLicenseKey(
			ActionRequest actionRequest, ActionResponse actionResponse,
			ThemeDisplay themeDisplay)
		throws Exception {

		long licenseEntryId = ParamUtil.getLong(
			actionRequest, "licenseEntryId");
		String productKey = ParamUtil.getString(actionRequest, "productKey");
		String accountKey = ParamUtil.getString(actionRequest, "accountKey");
		String productPurchaseKey = ParamUtil.getString(
			actionRequest, "productPurchaseKey");
		String accountName = ParamUtil.getString(actionRequest, "accountName");
		String productVersion = ParamUtil.getString(
			actionRequest, "productVersion");
		String name = ParamUtil.getString(actionRequest, "name");
		String owner = ParamUtil.getString(actionRequest, "owner");
		int maxClusterNodes = ParamUtil.getInteger(
			actionRequest, "maxClusterNodes");
		int maxServers = ParamUtil.getInteger(actionRequest, "maxServers");
		int maxHttpSessions = ParamUtil.getInteger(
			actionRequest, "maxHttpSessions");
		int sizing = ParamUtil.getInteger(actionRequest, "sizing");
		String description = ParamUtil.getString(actionRequest, "description");
		boolean complimentary = ParamUtil.getBoolean(
			actionRequest, "complimentary");

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date startDate = ParamUtil.getDate(
			actionRequest, "startDate", dateFormat);
		Date expirationDate = ParamUtil.getDate(
			actionRequest, "expirationDate", dateFormat);

		List<String> hostNames = new ArrayList<>();
		List<String> ipAddresses = new ArrayList<>();
		List<String> macAddresses = new ArrayList<>();

		JSONArray serverIdsJSONArray = JSONFactoryUtil.createJSONArray(
			ParamUtil.getString(actionRequest, "serverIds"));

		for (int i = 0; i < serverIdsJSONArray.length(); i++) {
			JSONObject serverIdJSONObject = serverIdsJSONArray.getJSONObject(i);

			String hostName = StringUtil.trim(
				serverIdJSONObject.getString("hostName"));

			String[] curIpAddresses = _separatorPattern.split(
				serverIdJSONObject.getString("ipAddresses"));

			Set<String> distinctIpAddresses = new HashSet<>();

			for (String ipAddress : curIpAddresses) {
				ipAddress = StringUtil.trim(ipAddress);

				if (Validator.isNotNull(ipAddress)) {
					distinctIpAddresses.add(ipAddress);
				}
			}

			Set<String> distinctMacAddresses = new HashSet<>();

			String[] curMacAddresses = _separatorPattern.split(
				serverIdJSONObject.getString("macAddresses"));

			for (String macAddress : curMacAddresses) {
				macAddress = StringUtil.trim(macAddress);

				if (Validator.isNotNull(macAddress)) {
					distinctMacAddresses.add(macAddress);
				}
			}

			if (Validator.isNull(hostName) && distinctIpAddresses.isEmpty() &&
				distinctMacAddresses.isEmpty()) {

				continue;
			}

			hostNames.add(hostName);
			ipAddresses.add(StringUtil.merge(distinctIpAddresses));
			macAddresses.add(StringUtil.merge(distinctMacAddresses));
		}

		User user = themeDisplay.getUser();

		LicenseKey licenseKey = _licenseKeyService.addLicenseKey(
			user.getFullName(), user.getUserUuid(), licenseEntryId, productKey,
			accountKey, productPurchaseKey, accountName, productVersion, 0,
			name, owner, maxClusterNodes, maxServers, maxHttpSessions, 0, 0,
			LicenseSizing.getLabel(sizing), description,
			hostNames.toArray(new String[0]),
			ipAddresses.toArray(new String[0]),
			macAddresses.toArray(new String[0]), startDate, expirationDate,
			complimentary, true);

		PortletURL redirectURL = PortletURLFactoryUtil.create(
			actionRequest, ProvisioningPortletKeys.LICENSES,
			PortletRequest.RENDER_PHASE);

		PortletURL portletURL = null;

		if (serverIdsJSONArray.length() > 1) {
			portletURL = PortletURLFactoryUtil.create(
				actionRequest, ProvisioningPortletKeys.ACCOUNTS,
				PortletRequest.RENDER_PHASE);

			portletURL.setParameter(
				"mvcRenderCommandName", "/accounts/view_account");
			portletURL.setParameter("redirect", redirectURL.toString());
			portletURL.setParameter("tabs1", "licenses");
			portletURL.setParameter("accountKey", accountKey);
		}
		else {
			portletURL = PortletURLFactoryUtil.create(
				actionRequest, ProvisioningPortletKeys.LICENSES,
				PortletRequest.RENDER_PHASE);

			portletURL.setParameter(
				"mvcRenderCommandName", "/licenses/edit_license_key");
			portletURL.setParameter("redirect", redirectURL.toString());
			portletURL.setParameter(
				"licenseKeyId", String.valueOf(licenseKey.getLicenseKeyId()));
		}

		hideDefaultSuccessMessage(actionRequest);

		JSONObject jsonObject = JSONUtil.put("redirectURL", portletURL);

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse, jsonObject);
	}

	protected void bulkUpdateLicenseKeys(
			ActionRequest actionRequest, ActionResponse actionResponse,
			ThemeDisplay themeDisplay, long[] licenseKeyIds)
		throws Exception {

		String active = ParamUtil.getString(actionRequest, "active");
		String complimentary = ParamUtil.getString(
			actionRequest, "complimentary");

		for (long licenseKeyId : licenseKeyIds) {
			LicenseKey licenseKey = _licenseKeyService.getLicenseKey(
				licenseKeyId);

			if (Validator.isNotNull(active)) {
				_licenseKeyService.updateLicenseKey(
					licenseKeyId, licenseKey.getProductPurchaseKey(),
					licenseKey.isComplimentary(),
					GetterUtil.getBoolean(active));
			}
			else if (Validator.isNotNull(complimentary)) {
				_licenseKeyService.updateLicenseKey(
					licenseKeyId, licenseKey.getProductPurchaseKey(),
					GetterUtil.getBoolean(complimentary),
					licenseKey.isActive());
			}
		}

		sendRedirect(actionRequest, actionResponse);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long licenseKeyId = ParamUtil.getLong(
				actionRequest, "licenseKeyId");

			long[] licenseKeyIds = ParamUtil.getLongValues(
				actionRequest, "licenseKeyIds");

			if (ArrayUtil.isNotEmpty(licenseKeyIds)) {
				bulkUpdateLicenseKeys(
					actionRequest, actionResponse, themeDisplay, licenseKeyIds);
			}
			else if (licenseKeyId > 0) {
				updateLicenseKey(
					actionRequest, actionResponse, themeDisplay, licenseKeyId);
			}
			else {
				addLicenseKey(actionRequest, actionResponse, themeDisplay);
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	protected String getRedirect(
			ActionResponse actionResponse, ActionRequest actionRequest,
			long licenseKeyId)
		throws Exception {

		LiferayPortletResponse liferayPortletResponse =
			_portal.getLiferayPortletResponse(actionResponse);

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		portletURL.setParameter(
			"mvcRenderCommandName", "/licenses/edit_license_key");
		portletURL.setParameter("redirect", redirect);
		portletURL.setParameter("licenseKeyId", String.valueOf(licenseKeyId));

		return portletURL.toString();
	}

	protected void updateLicenseKey(
			ActionRequest actionRequest, ActionResponse actionResponse,
			ThemeDisplay themeDisplay, long licenseKeyId)
		throws Exception {

		long clusterLicenseKeyId = ParamUtil.getLong(
			actionRequest, "clusterLicenseKeyId");
		String productPurchaseKey = ParamUtil.getString(
			actionRequest, "productPurchaseKey");
		boolean active = ParamUtil.getBoolean(actionRequest, "active");
		boolean complimentary = ParamUtil.getBoolean(
			actionRequest, "complimentary");

		long curLicenseKeyId = licenseKeyId;

		if (clusterLicenseKeyId > 0) {
			curLicenseKeyId = clusterLicenseKeyId;
		}

		_licenseKeyService.updateLicenseKey(
			curLicenseKeyId, productPurchaseKey, complimentary, active);

		sendRedirect(
			actionRequest, actionResponse,
			getRedirect(actionResponse, actionRequest, licenseKeyId));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditLicenseKeysMVCActionCommand.class);

	private static final Pattern _separatorPattern = Pattern.compile(
		"\\s*,\\s*|\\s+");

	@Reference
	private LicenseKeyService _licenseKeyService;

	@Reference
	private Portal _portal;

}