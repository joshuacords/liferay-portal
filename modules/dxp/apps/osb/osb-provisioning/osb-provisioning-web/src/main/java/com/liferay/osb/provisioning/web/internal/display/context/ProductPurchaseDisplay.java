/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.provisioning.constants.ProvisioningActionKeys;
import com.liferay.osb.provisioning.web.internal.permission.AccountPermissionChecker;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.Format;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class ProductPurchaseDisplay {

	public ProductPurchaseDisplay(
		HttpServletRequest httpServletRequest, ProductPurchase productPurchase,
		long productConsumptionsCount) {

		_httpServletRequest = httpServletRequest;
		_productPurchase = productPurchase;

		_dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM dd, yyyy");

		_provisionedCount = productConsumptionsCount;

		ExternalLink externalLink = _getSalesforceopportunityExternalLink(
			productPurchase);

		if (externalLink != null) {
			_externalLinkKey = externalLink.getKey();
			_salesforceOpportunityKey = externalLink.getEntityId();
			_salesforceOpportunityURL = externalLink.getUrl();
		}
		else {
			_externalLinkKey = StringPool.BLANK;
			_salesforceOpportunityKey = StringPool.BLANK;
			_salesforceOpportunityURL = StringPool.BLANK;
		}

		Map<String, String> properties = productPurchase.getProperties();

		if (properties != null) {
			_sizing = GetterUtil.getInteger(properties.get("sizing"));
		}
		else {
			_sizing = 0;
		}

		_initState();
	}

	public String getAccountKey() {
		return _productPurchase.getAccountKey();
	}

	public Date getEndDate() {
		return _productPurchase.getEndDate();
	}

	public String getExternalLinkKey() {
		return _externalLinkKey;
	}

	public String getGracePeriod() {
		Date originalEndDate = _productPurchase.getOriginalEndDate();
		Date endDate = _productPurchase.getEndDate();

		if ((originalEndDate == null) || (endDate == null) ||
			_productPurchase.getPerpetual() || originalEndDate.after(endDate)) {

			return StringPool.DASH;
		}

		StringBundler sb = new StringBundler(3);

		sb.append(_dateFormat.format(originalEndDate));
		sb.append(" - ");
		sb.append(_dateFormat.format(endDate));

		return sb.toString();
	}

	public String getKey() {
		return _productPurchase.getKey();
	}

	public Date getOriginalEndDate() {
		return _productPurchase.getOriginalEndDate();
	}

	public String getProductName() {
		Product product = _productPurchase.getProduct();

		return product.getName();
	}

	public String getProvisionedCount() {
		return String.valueOf(_provisionedCount);
	}

	public String getQuantity() {
		return String.valueOf(_productPurchase.getQuantity());
	}

	public String getSalesforceOpportunityKey() {
		return _salesforceOpportunityKey;
	}

	public String getSalesforceOpportunityURL() {
		return _salesforceOpportunityURL;
	}

	public String getSizing() {
		if (_sizing > 0) {
			return String.valueOf(_sizing);
		}

		return StringPool.DASH;
	}

	public Date getStartDate() {
		return _productPurchase.getStartDate();
	}

	public String getState() {
		return _state;
	}

	public String getStateStyle() {
		if (_state.equals("active")) {
			return "label-success";
		}
		else if (_state.equals("cancelled")) {
			return "label-danger";
		}
		else if (_state.equals("expired")) {
			return "label-secondary";
		}
		else if (_state.equals("future")) {
			return "label-warning";
		}

		return StringPool.BLANK;
	}

	public String getSupportLife() {
		if (_productPurchase.getStartDate() == null) {
			return LanguageUtil.get(_httpServletRequest, "perpetual");
		}

		StringBundler sb = new StringBundler(3);

		sb.append(_dateFormat.format(_productPurchase.getStartDate()));
		sb.append(" - ");

		if (_productPurchase.getOriginalEndDate() != null) {
			sb.append(
				_dateFormat.format(_productPurchase.getOriginalEndDate()));
		}
		else {
			sb.append(LanguageUtil.get(_httpServletRequest, "perpetual"));
		}

		return sb.toString();
	}

	public boolean hasEditPermission() throws Exception {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return AccountPermissionChecker.contains(
			themeDisplay.getPermissionChecker(),
			ProvisioningActionKeys.MANAGE_ACCOUNTS);
	}

	public boolean isPerpetual() {
		if (_productPurchase.getStartDate() == null) {
			return true;
		}

		return false;
	}

	private ExternalLink _getSalesforceopportunityExternalLink(
		ProductPurchase productPurchase) {

		ExternalLink[] externalLinks = productPurchase.getExternalLinks();

		if (externalLinks != null) {
			for (ExternalLink externalLink : externalLinks) {
				String domain = externalLink.getDomain();
				String entityName = externalLink.getEntityName();

				if (domain.equals(ExternalLinkDomain.SALESFORCE) &&
					entityName.equals(
						ExternalLinkEntityName.SALESFORCE_OPPORTUNITY)) {

					return externalLink;
				}
			}
		}

		return null;
	}

	private void _initState() {
		if (_productPurchase.getStatus() == ProductPurchase.Status.APPROVED) {
			Date now = new Date();

			if (_productPurchase.getPerpetual() ||
				(now.after(_productPurchase.getStartDate()) &&
				 now.before(_productPurchase.getEndDate()))) {

				_state = "active";
			}
			else if (now.after(_productPurchase.getEndDate())) {
				_state = "expired";
			}
			else {
				_state = "future";
			}
		}
		else {
			_state = "cancelled";
		}
	}

	private final Format _dateFormat;
	private final String _externalLinkKey;
	private final HttpServletRequest _httpServletRequest;
	private final ProductPurchase _productPurchase;
	private final long _provisionedCount;
	private final String _salesforceOpportunityKey;
	private final String _salesforceOpportunityURL;
	private final int _sizing;
	private String _state;

}