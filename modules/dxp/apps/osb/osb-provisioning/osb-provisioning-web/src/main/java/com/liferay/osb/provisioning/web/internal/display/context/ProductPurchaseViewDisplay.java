/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.Format;

import java.util.Date;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class ProductPurchaseViewDisplay {

	public ProductPurchaseViewDisplay(
		HttpServletRequest httpServletRequest, Account account,
		ProductPurchaseView productPurchaseView) {

		_httpServletRequest = httpServletRequest;
		_account = account;

		_dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM dd, yyyy");
		_product = productPurchaseView.getProduct();

		Date now = new Date();

		_initProductConsumptions(
			productPurchaseView.getProductConsumptions(), now);
		_initProductPurchases(productPurchaseView.getProductPurchases(), now);

		if (StringUtil.equalsIgnoreCase(_status, "approved")) {
			if (!_inSupportGap && (_perpetual || _startDate.before(now)) &&
				(_perpetual || _endDate.after(now))) {

				_state = "active";
			}
			else if (_endDate.before(now)) {
				_state = "expired";
			}
			else {
				_state = "future";
			}
		}
		else if (_totalPurchasedCount == 0) {
			_state = StringPool.BLANK;
		}
		else {
			_state = "cancelled";
		}
	}

	public String getAccountKey() {
		return _account.getKey();
	}

	public String getApprovedPurchasedCount() {
		return String.valueOf(_approvedPurchasedCount);
	}

	public String getCurrentProvisionedCount() {
		return String.valueOf(_currentProvisionedCount);
	}

	public String getCurrentPurchasedCount() {
		return String.valueOf(_currentPurchasedCount);
	}

	public String getEndDate() {
		if (_endDate != null) {
			return _dateFormat.format(_endDate);
		}

		return StringPool.DASH;
	}

	public String getGracePeriod() {
		if (_perpetual || (_originalEndDate == null) ||
			_originalEndDate.after(_endDate)) {

			return StringPool.DASH;
		}

		StringBundler sb = new StringBundler(3);

		sb.append(_dateFormat.format(_originalEndDate));
		sb.append(" - ");

		if (_endDate != null) {
			sb.append(_dateFormat.format(_endDate));
		}
		else {
			sb.append(LanguageUtil.get(_httpServletRequest, "perpetual"));
		}

		return sb.toString();
	}

	public Date getLatestEndDate() {
		return _latestEndDate;
	}

	public String getLatestPurchasedCount() {
		return String.valueOf(_latestPurchasedCount);
	}

	public String getName() {
		return _product.getName();
	}

	public String getNextTermStartDate() {
		if (_nextTermStartDate != null) {
			return _dateFormat.format(_nextTermStartDate);
		}

		return StringPool.BLANK;
	}

	public String getProductKey() {
		return _product.getKey();
	}

	public String getProvisionedCount() {
		return String.valueOf(_provisionedCount);
	}

	public String getProvisionedCountURL() {
		PortletURL portletURL = PortletURLFactoryUtil.create(
			_httpServletRequest, ProvisioningPortletKeys.ACCOUNTS,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/view_subscription");
		portletURL.setParameter("tabs1", "licenses");
		portletURL.setParameter("accountKey", _account.getKey());
		portletURL.setParameter("productKey", _product.getKey());

		return portletURL.toString();
	}

	public String getSizing() {
		if (_sizing > 0) {
			return String.valueOf(_sizing);
		}

		return StringPool.DASH;
	}

	public String getSizingWithLabel() {
		if (_sizing > 0) {
			return LanguageUtil.get(_httpServletRequest, "instance-size") +
				": " + _sizing;
		}

		return StringPool.BLANK;
	}

	public String getState() {
		if (Validator.isNotNull(_state)) {
			return LanguageUtil.get(_httpServletRequest, _state);
		}

		return StringPool.DASH;
	}

	public String getStateStyle() {
		if (Validator.isNull(_state)) {
			return StringPool.BLANK;
		}
		else if (_state.equals("active")) {
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
		if (_perpetual) {
			return LanguageUtil.get(_httpServletRequest, "perpetual");
		}

		if (_startDate == null) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(3);

		sb.append(_dateFormat.format(_startDate));
		sb.append(" - ");

		if (_originalEndDate != null) {
			sb.append(_dateFormat.format(_originalEndDate));
		}
		else {
			sb.append(LanguageUtil.get(_httpServletRequest, "perpetual"));
		}

		return sb.toString();
	}

	public String getType() {
		Map<String, String> properties = _product.getProperties();

		if (properties != null) {
			String type = properties.get("type");

			if (Validator.isNotNull(type)) {
				return type;
			}
		}

		return StringPool.BLANK;
	}

	public boolean isInSupportGap() {
		if (_inSupportGap) {
			return true;
		}

		return false;
	}

	public boolean isPerpetual() {
		return _perpetual;
	}

	private void _initProductConsumptions(
		ProductConsumption[] productConsumptions, Date now) {

		if (productConsumptions != null) {
			_provisionedCount = productConsumptions.length;

			for (ProductConsumption productConsumption : productConsumptions) {
				if ((productConsumption.getEndDate() == null) ||
					now.before(productConsumption.getEndDate())) {

					_currentProvisionedCount += 1;
				}
			}
		}
		else {
			_provisionedCount = 0;
		}
	}

	private void _initProductPurchases(
		ProductPurchase[] productPurchases, Date now) {

		_inSupportGap = true;

		for (ProductPurchase productPurchase : productPurchases) {
			Date startDate = productPurchase.getStartDate();
			Date originalEndDate = productPurchase.getOriginalEndDate();
			Date endDate = productPurchase.getEndDate();

			boolean approved = false;

			if (productPurchase.getStatus() ==
					ProductPurchase.Status.APPROVED) {

				approved = true;
			}

			if (approved && (startDate == null)) {
				_inSupportGap = false;
				_perpetual = true;
			}

			if (approved && !_perpetual &&
				((_startDate == null) || startDate.before(_startDate))) {

				_startDate = startDate;
			}

			if (approved && !_perpetual &&
				((_originalEndDate == null) ||
				 originalEndDate.after(_originalEndDate))) {

				_originalEndDate = originalEndDate;
			}

			if (approved && !_perpetual &&
				((_endDate == null) || endDate.after(_endDate))) {

				_endDate = endDate;
			}

			if (approved && (startDate != null) && startDate.before(now) &&
				(endDate != null) && endDate.after(now)) {

				_inSupportGap = false;
			}

			if (approved && !_perpetual && _inSupportGap &&
				((_nextTermStartDate == null) ||
				 ((_nextTermStartDate != null) &&
				  startDate.before(_nextTermStartDate))) &&
				startDate.after(now)) {

				_nextTermStartDate = startDate;
			}

			Map<String, String> properties = productPurchase.getProperties();

			if (approved && (properties != null)) {
				int sizing = GetterUtil.getInteger(properties.get("sizing"));

				if ((sizing > _sizing) &&
					((startDate == null) || startDate.before(now)) &&
					((endDate == null) || endDate.after(now))) {

					_sizing = sizing;
				}
			}

			_totalPurchasedCount += productPurchase.getQuantity();

			if (approved) {
				_approvedPurchasedCount += productPurchase.getQuantity();

				if (((startDate == null) || startDate.before(now)) &&
					((endDate == null) || endDate.after(now))) {

					_currentPurchasedCount += productPurchase.getQuantity();
				}
			}

			if ((endDate == null) ||
				((_latestEndDate == null) && (_latestPurchasedCount == 0)) ||
				((_latestEndDate != null) && endDate.after(_latestEndDate))) {

				_latestEndDate = endDate;

				_latestPurchasedCount = productPurchase.getQuantity();
			}
			else if (endDate.equals(_latestEndDate)) {
				_latestPurchasedCount += productPurchase.getQuantity();
			}

			if (!StringUtil.equalsIgnoreCase(_status, "approved")) {
				_status = productPurchase.getStatusAsString();
			}
		}

		if (_inSupportGap && (_startDate != null) && _startDate.after(now)) {
			_inSupportGap = false;
		}
	}

	private final Account _account;
	private int _approvedPurchasedCount;
	private int _currentProvisionedCount;
	private int _currentPurchasedCount;
	private final Format _dateFormat;
	private Date _endDate;
	private final HttpServletRequest _httpServletRequest;
	private boolean _inSupportGap;
	private Date _latestEndDate;
	private int _latestPurchasedCount;
	private Date _nextTermStartDate;
	private Date _originalEndDate;
	private boolean _perpetual;
	private final Product _product;
	private int _provisionedCount;
	private int _sizing;
	private Date _startDate;
	private final String _state;
	private String _status;
	private int _totalPurchasedCount;

}