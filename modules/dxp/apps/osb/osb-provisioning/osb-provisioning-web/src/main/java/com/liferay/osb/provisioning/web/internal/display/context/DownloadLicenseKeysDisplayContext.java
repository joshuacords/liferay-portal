/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.license.model.LicenseEntry;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.text.Format;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuanyuan Huang
 */
public class DownloadLicenseKeysDisplayContext {

	public DownloadLicenseKeysDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;

		_account = (Account)renderRequest.getAttribute(
			ProvisioningWebKeys.ACCOUNT);
		_dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM dd, yyyy");
		_licenseKeys = (List<LicenseKey>)renderRequest.getAttribute(
			ProvisioningWebKeys.LICENSE_KEYS);

		_renderResponse.setTitle(
			StringBundler.concat(
				_account.getCode(), StringPool.SPACE,
				LanguageUtil.get(_httpServletRequest, "download-licenses")));
	}

	public Map<String, Object> getDownloadLicenseKeysData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		LiferayPortletURL liferayPortletURL =
			(LiferayPortletURL)_renderResponse.createResourceURL();

		liferayPortletURL.setCopyCurrentRenderParameters(false);
		liferayPortletURL.setResourceID("/accounts/download_license_keys");

		data.put("downloadLicenseKeysURL", liferayPortletURL.toString());

		JSONArray licenseKeysJSONArray = JSONFactoryUtil.createJSONArray();

		for (LicenseKey licenseKey : _licenseKeys) {
			LicenseEntry licenseEntry = licenseKey.getLicenseEntry();

			licenseKeysJSONArray.put(
				JSONUtil.put(
					"active", licenseKey.isActive()
				).put(
					"description", licenseKey.getDescription()
				).put(
					"expirationDate",
					_dateFormat.format(licenseKey.getExpirationDate())
				).put(
					"hostName", licenseKey.getHostName()
				).put(
					"ipAddresses", licenseKey.getIpAddresses()
				).put(
					"licenseEntryDisplayName", licenseEntry.getDisplayName()
				).put(
					"licenseEntryName", licenseKey.getLicenseEntryName()
				).put(
					"licenseEntryType", licenseKey.getLicenseEntryType()
				).put(
					"licenseKeyId", licenseKey.getLicenseKeyId()
				).put(
					"licenseVersion", licenseKey.getLicenseVersion()
				).put(
					"macAddresses", licenseKey.getMacAddresses()
				).put(
					"name", licenseKey.getName()
				).put(
					"productId", licenseKey.getProductId()
				).put(
					"productName", licenseKey.getProductName()
				).put(
					"productVersion", licenseKey.getProductVersion()
				).put(
					"sizing", licenseKey.getSizing()
				).put(
					"startDate", _dateFormat.format(licenseKey.getStartDate())
				));
		}

		data.put("licenseKeys", licenseKeysJSONArray);

		return data;
	}

	private final Account _account;
	private final Format _dateFormat;
	private final HttpServletRequest _httpServletRequest;
	private final List<LicenseKey> _licenseKeys;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;

}