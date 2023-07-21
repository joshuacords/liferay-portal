/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.provisioning.license.helper.constants.LicenseType;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.Format;

import java.util.Date;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyle Bischof
 */
public class LicenseKeyDisplay {

	public LicenseKeyDisplay(
			PortletRequest portletRequest, PortletResponse portletResponse,
			LicenseKey licenseKey)
		throws Exception {

		_portletRequest = portletRequest;
		_portletResponse = portletResponse;
		_licenseKey = licenseKey;

		_httpServletRequest = PortalUtil.getHttpServletRequest(portletRequest);
		_liferayPortletResponse = PortalUtil.getLiferayPortletResponse(
			portletResponse);

		_dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMMM dd, yyyy");
		_dateTimeFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMMM dd, yyyy hh:mm:ss a 'UTC'");

		_licenseType = _licenseKey.getLicenseEntryType();
		_licenseVersion = _licenseKey.getLicenseVersion();

		_initStatus();
	}

	public String getAccountName() {
		return _licenseKey.getAccountName();
	}

	public String getCreateDate() {
		return _dateTimeFormat.format(_licenseKey.getCreateDate());
	}

	public String getDescription() {
		return _licenseKey.getDescription();
	}

	public String getEndDate() {
		return _dateTimeFormat.format(_licenseKey.getExpirationDate());
	}

	public String getExpirationDate() {
		return _dateFormat.format(_licenseKey.getExpirationDate());
	}

	public String getHostName() {
		if (Validator.isNotNull(_licenseKey.getHostName())) {
			return _licenseKey.getHostName();
		}

		return StringPool.DASH;
	}

	public String getIpAddresses() {
		return getSplitFieldValue(_licenseKey.getIpAddresses());
	}

	public String getLicenseKeyId() {
		return String.valueOf(_licenseKey.getLicenseKeyId());
	}

	public String getMacAddresses() {
		return getSplitFieldValue(_licenseKey.getMacAddresses());
	}

	public String getMaxClusterNodes() {
		return String.valueOf(_licenseKey.getMaxClusterNodes());
	}

	public String getMaxConcurrentUsersLabel() {
		if (_licenseKey.getMaxConcurrentUsers() <= 0) {
			return LanguageUtil.get(_httpServletRequest, "unlimited");
		}

		return String.valueOf(_licenseKey.getMaxConcurrentUsers());
	}

	public String getMaximumServers() {
		return String.valueOf(_licenseKey.getMaxServers());
	}

	public String getMaxUsersLabel() {
		if (_licenseKey.getMaxUsers() <= 0) {
			return LanguageUtil.get(_httpServletRequest, "unlimited");
		}

		return String.valueOf(_licenseKey.getMaxUsers());
	}

	public String getModifiedDate() {
		return _dateTimeFormat.format(_licenseKey.getModifiedDate());
	}

	public String getName() {
		return _licenseKey.getName();
	}

	public String getOwner() {
		return _licenseKey.getOwner();
	}

	public String getProductName() {
		return _licenseKey.getProductName();
	}

	public String getProductVersion() {
		return _licenseKey.getProductVersion();
	}

	public String getServerId() {
		if (Validator.isNotNull(_licenseKey.getServerId())) {
			return _licenseKey.getServerId();
		}

		return StringPool.DASH;
	}

	public String getStartDate() {
		return _dateFormat.format(_licenseKey.getStartDate());
	}

	public String getStatus() {
		return LanguageUtil.get(_httpServletRequest, _status);
	}

	public String getStatusStyle() {
		if (_status.equals("active")) {
			return "label-success";
		}
		else if (_status.equals("expired")) {
			return "label-warning";
		}

		return "label-danger";
	}

	public String getType() {
		return LanguageUtil.get(
			_httpServletRequest, _licenseKey.getLicenseEntryType());
	}

	public String getUpdateActiveConfirmMessage() {
		if (_licenseKey.isActive()) {
			return LanguageUtil.get(
				_httpServletRequest,
				"are-you-sure-you-want-to-deactivate-the-license-keys");
		}

		return LanguageUtil.get(
			_httpServletRequest,
			"are-you-sure-you-want-to-activate-the-license-keys");
	}

	public String getUpdateActiveLabel() {
		if (_licenseKey.isActive()) {
			return LanguageUtil.get(_httpServletRequest, "deactivate");
		}

		return LanguageUtil.get(_httpServletRequest, "activate");
	}

	public String getUpdateComplimentaryConfirmMessage() {
		if (_licenseKey.isComplimentary()) {
			return LanguageUtil.get(
				_httpServletRequest,
				"are-you-sure-you-want-to-proceed-the-license-keys-will-no-" +
					"longer-be-complimentary");
		}

		return LanguageUtil.get(
			_httpServletRequest,
			"are-you-sure-you-want-to-make-the-license-keys-complimentary");
	}

	public String getUpdateComplimentaryLabel() {
		if (_licenseKey.isComplimentary()) {
			return LanguageUtil.get(
				_httpServletRequest, "remove-complimentary");
		}

		return LanguageUtil.get(_httpServletRequest, "make-complimentary");
	}

	public String getUserName() {
		if (Validator.isNotNull(_licenseKey.getUserName())) {
			return _licenseKey.getUserName();
		}

		return StringPool.DASH;
	}

	public boolean isComplimentary() {
		return _licenseKey.isComplimentary();
	}

	public String isComplimentaryLabel() {
		if (_licenseKey.isComplimentary()) {
			return LanguageUtil.get(_httpServletRequest, "yes");
		}

		return LanguageUtil.get(_httpServletRequest, "no");
	}

	public boolean showHostName() {
		if ((_licenseVersion >= 3) &&
			(_licenseType.equals(LicenseType.LIMITED) ||
			 _licenseType.equals(LicenseType.PER_USER) ||
			 _licenseType.equals(LicenseType.PRODUCTION))) {

			return true;
		}

		return false;
	}

	public boolean showIpAddresses() {
		if ((_licenseVersion >= 3) &&
			(_licenseType.equals(LicenseType.LIMITED) ||
			 _licenseType.equals(LicenseType.PER_USER) ||
			 _licenseType.equals(LicenseType.PRODUCTION))) {

			return true;
		}

		return false;
	}

	public boolean showMacAddresses() {
		if (((_licenseVersion >= 3) &&
			 (_licenseType.equals(LicenseType.LIMITED) ||
			  _licenseType.equals(LicenseType.PER_USER) ||
			  _licenseType.equals(LicenseType.PRODUCTION))) ||
			((_licenseVersion == 2) &&
			 _licenseType.equals(LicenseType.PRODUCTION)) ||
			((_licenseVersion == 1) &&
			 (_licenseType.equals(LicenseType.CLUSTER) ||
			  _licenseType.equals(LicenseType.DEVELOPER_CLUSTER)))) {

			return true;
		}

		return false;
	}

	public boolean showMaxClusterNodes() {
		if (_licenseType.equals(LicenseType.VIRTUAL_CLUSTER)) {
			return true;
		}

		return false;
	}

	public boolean showMaximumServers() {
		if (((_licenseVersion >= 3) &&
			 _licenseType.equals(LicenseType.CLUSTER)) ||
			((_licenseVersion == 2) &&
			 (_licenseType.equals(LicenseType.CLUSTER) ||
			  _licenseType.equals(LicenseType.DEVELOPER_CLUSTER)))) {

			return true;
		}

		return false;
	}

	protected String getSplitFieldValue(String value) {
		String[] splitValue = StringUtil.split(value);

		if (splitValue.length > 0) {
			StringBundler sb = new StringBundler((splitValue.length * 2) - 1);

			for (int i = 0; i < splitValue.length; i++) {
				sb.append(HtmlUtil.escape(splitValue[i]));

				if ((i + 1) < splitValue.length) {
					sb.append("<br />");
				}
			}

			return sb.toString();
		}

		return StringPool.DASH;
	}

	private void _initStatus() {
		if (_licenseKey.isActive()) {
			Date expirationDate = _licenseKey.getExpirationDate();

			Date now = new Date();

			if (expirationDate.after(now)) {
				_status = "active";
			}
			else {
				_status = "expired";
			}
		}
		else {
			_status = "deactivated";
		}
	}

	private final Format _dateFormat;
	private final Format _dateTimeFormat;
	private final HttpServletRequest _httpServletRequest;
	private final LicenseKey _licenseKey;
	private final String _licenseType;
	private final int _licenseVersion;
	private final LiferayPortletResponse _liferayPortletResponse;
	private final PortletRequest _portletRequest;
	private final PortletResponse _portletResponse;
	private String _status;

}