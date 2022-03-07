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

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.provisioning.koroneiki.constants.ProductPurchaseConstants;
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.text.Format;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class AccountDisplay {

	public AccountDisplay(
			PortletRequest portletRequest, PortletResponse portletResponse,
			AccountReader accountReader, Account account)
		throws Exception {

		_portletRequest = portletRequest;
		_portletResponse = portletResponse;

		_accountReader = accountReader;
		_account = account;

		_dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM dd, yyyy");
		_dateTimeFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM dd, yyyy hh:mm a z");
		_firstLineSupportTeam = _accountReader.getFirstLineSupportTeam(
			_account);
		_httpServletRequest = PortalUtil.getHttpServletRequest(portletRequest);
		_liferayPortletResponse = PortalUtil.getLiferayPortletResponse(
			portletResponse);
		_partnerTeam = _accountReader.getPartnerTeam(_account);
	}

	public String getAddPostalAddressURL() {
		PortletURL addPostalAddressURL =
			_liferayPortletResponse.createActionURL();

		addPostalAddressURL.setParameter(
			ActionRequest.ACTION_NAME, "/accounts/edit_postal_address");

		PortletURL portletURL = _getPortletURL(
			"/accounts/view_account", "details");

		addPostalAddressURL.setParameter("redirect", portletURL.toString());

		addPostalAddressURL.setParameter("accountKey", _account.getKey());

		return addPostalAddressURL.toString();
	}

	public String getCode() {
		if (Validator.isNotNull(_account.getCode())) {
			return _account.getCode();
		}

		return StringPool.DASH;
	}

	public String getDataRegion() {
		Account.DataRegion dataRegion = _account.getDataRegion();

		if (dataRegion != null) {
			return dataRegion.toString();
		}

		return StringPool.DASH;
	}

	public String getDateCreated() {
		return _dateTimeFormat.format(_account.getDateCreated());
	}

	public String getDateModified() {
		return _dateTimeFormat.format(_account.getDateModified());
	}

	public String getDossieraAccountKey() {
		return _getExternalLinkEntityId(
			ExternalLinkDomain.DOSSIERA,
			ExternalLinkEntityName.DOSSIERA_ACCOUNT);
	}

	public String getDossieraProjectKey() {
		return _getExternalLinkEntityId(
			ExternalLinkDomain.DOSSIERA,
			ExternalLinkEntityName.DOSSIERA_PROJECT);
	}

	public String getEditAccountHierarchyURL() {
		PortletURL editAccountHierarchyURL =
			_liferayPortletResponse.createActionURL();

		editAccountHierarchyURL.setParameter(
			ActionRequest.ACTION_NAME, "/accounts/edit_account_hierarchy");

		PortletURL portletURL = _getPortletURL(
			"/accounts/view_account", "details");

		editAccountHierarchyURL.setParameter("redirect", portletURL.toString());

		editAccountHierarchyURL.setParameter("accountKey", _account.getKey());

		return editAccountHierarchyURL.toString();
	}

	public String getEditAccountURL() {
		PortletURL editAccountURL = _liferayPortletResponse.createActionURL();

		editAccountURL.setParameter(
			ActionRequest.ACTION_NAME, "/accounts/edit_account");

		PortletURL portletURL = _getPortletURL(
			"/accounts/view_account", "details");

		editAccountURL.setParameter("redirect", portletURL.toString());

		editAccountURL.setParameter("accountKey", _account.getKey());

		return editAccountURL.toString();
	}

	public String getEWSA() throws Exception {
		if (_accountReader.isEWSA(_account)) {
			return LanguageUtil.get(_httpServletRequest, "yes");
		}

		return LanguageUtil.get(_httpServletRequest, "no");
	}

	public String getFirstLineSupportTeamKey() {
		if (_firstLineSupportTeam != null) {
			return _firstLineSupportTeam.getKey();
		}

		return StringPool.BLANK;
	}

	public String getFirstLineSupportTeamName() throws Exception {
		if (_firstLineSupportTeam != null) {
			return _firstLineSupportTeam.getName();
		}

		return StringPool.DASH;
	}

	public String getKey() {
		return _account.getKey();
	}

	public String getLiferayVersion() {
		Map<String, String> properties = _account.getProperties();

		if (properties != null) {
			String liferayVersion = properties.get("liferayVersion");

			if (Validator.isNotNull(liferayVersion)) {
				return liferayVersion;
			}
		}

		return StringPool.DASH;
	}

	public String getName() {
		return _account.getName();
	}

	public String getParentAccountKey() {
		return _account.getParentAccountKey();
	}

	public String getPartnerTeamKey() {
		if (_partnerTeam != null) {
			return _partnerTeam.getKey();
		}

		return StringPool.BLANK;
	}

	public String getPartnerTeamName() throws Exception {
		if (_partnerTeam != null) {
			return _partnerTeam.getName();
		}

		return StringPool.DASH;
	}

	public List<PostalAddressDisplay> getPostalAddressDisplays() {
		if (_account.getPostalAddresses() == null) {
			return Collections.emptyList();
		}

		return TransformUtil.transformToList(
			_account.getPostalAddresses(),
			postalAddress -> new PostalAddressDisplay(
				_portletRequest, _portletResponse, _account, postalAddress));
	}

	public String getPrimaryCountry() {
		PostalAddress[] postalAddresses = _account.getPostalAddresses();

		if (postalAddresses != null) {
			for (PostalAddress postalAddress : postalAddresses) {
				if ((postalAddress.getPrimary() != null) &&
					postalAddress.getPrimary()) {

					return postalAddress.getAddressCountry();
				}
			}
		}

		return StringPool.BLANK;
	}

	public String getRegion() {
		Account.Region region = _account.getRegion();

		if (region != null) {
			return region.toString();
		}

		return StringPool.DASH;
	}

	public String getSalesforceProjectKey() {
		return _getExternalLinkEntityId(
			ExternalLinkDomain.SALESFORCE,
			ExternalLinkEntityName.SALESFORCE_PROJECT);
	}

	public String getSLAName() {
		ProductPurchase slaProductPurchase = _getSLAProductPurchase();

		if (slaProductPurchase != null) {
			Product product = slaProductPurchase.getProduct();

			return StringUtil.removeSubstring(
				product.getName(), " Subscription");
		}

		return StringPool.DASH;
	}

	public String getStatus() {
		Account.Status status = _account.getStatus();

		if (status != null) {
			return status.toString();
		}

		return StringPool.DASH;
	}

	public String getStatusStyle() {
		Account.Status status = _account.getStatus();

		if (status == Account.Status.ACTIVE) {
			return "label-success";
		}

		return "label-secondary";
	}

	public String getSubscriptionState() {
		String state = _accountReader.getSubscriptionState(_account);

		if (Validator.isNotNull(state)) {
			return LanguageUtil.get(_httpServletRequest, state);
		}

		return StringPool.DASH;
	}

	public String getSubscriptionStateStyle() {
		String state = _accountReader.getSubscriptionState(_account);

		if (Validator.isNull(state)) {
			return StringPool.BLANK;
		}

		if (state.equals(ProductPurchaseConstants.STATE_ACTIVE)) {
			return "label-success";
		}
		else if (state.equals(ProductPurchaseConstants.STATE_UNACTIVATED)) {
			return "label-secondary";
		}

		return "label-danger";
	}

	public String getSupportEndDate() {
		ProductPurchase slaProductPurchase = _getSLAProductPurchase();

		if (slaProductPurchase != null) {
			if (slaProductPurchase.getPerpetual()) {
				return LanguageUtil.get(_httpServletRequest, "perpetual");
			}

			return _dateFormat.format(slaProductPurchase.getOriginalEndDate());
		}

		return StringPool.BLANK;
	}

	public String getSupportSeatContactUsage() {
		StringBundler sb = new StringBundler(5);

		sb.append(_accountReader.getSupportSeatCount(_account));
		sb.append(" / ");

		int maxSupportSeatCount = _accountReader.getMaxSupportSeatCount(
			_account);

		if (maxSupportSeatCount == -1) {
			sb.append("∞");
		}
		else {
			sb.append(maxSupportSeatCount);
		}

		sb.append(" ");
		sb.append(LanguageUtil.get(_httpServletRequest, "filled"));

		return sb.toString();
	}

	public String getTier() {
		Account.Tier tier = _account.getTier();

		if (tier != null) {
			return tier.toString();
		}

		return StringPool.DASH;
	}

	public String getUpdateDossieraAccountURL() {
		return _getUpdateExternalLinkURL(
			_getExternalLinkKey(
				ExternalLinkDomain.DOSSIERA,
				ExternalLinkEntityName.DOSSIERA_ACCOUNT));
	}

	public String getUpdateDossieraProjectURL() {
		return _getUpdateExternalLinkURL(
			_getExternalLinkKey(
				ExternalLinkDomain.DOSSIERA,
				ExternalLinkEntityName.DOSSIERA_PROJECT));
	}

	public String getUpdateSalesforceProjectURL() {
		return _getUpdateExternalLinkURL(
			_getExternalLinkKey(
				ExternalLinkDomain.SALESFORCE,
				ExternalLinkEntityName.SALESFORCE_PROJECT));
	}

	public boolean isAllowPermanentLicenses() {
		Map<String, String> properties = _account.getProperties();

		if (properties != null) {
			return GetterUtil.getBoolean(
				properties.get("allowPermanentLicenses"), true);
		}

		return true;
	}

	public boolean isAllowSelfProvisioning() {
		Map<String, String> properties = _account.getProperties();

		if (properties != null) {
			return GetterUtil.getBoolean(
				properties.get("allowSelfProvisioning"), true);
		}

		return true;
	}

	private String _getAddExternalLinkURL() {
		PortletURL addExternalLinkURL =
			_liferayPortletResponse.createActionURL();

		addExternalLinkURL.setParameter(
			ActionRequest.ACTION_NAME, "/edit_external_link");

		PortletURL portletURL = _getPortletURL(
			"/accounts/view_account", "details");

		addExternalLinkURL.setParameter("redirect", portletURL.toString());

		addExternalLinkURL.setParameter("accountKey", _account.getKey());

		return addExternalLinkURL.toString();
	}

	private String _getEditExternalLinkURL(String externalLinkKey) {
		PortletURL editExternalLinkURL =
			_liferayPortletResponse.createActionURL();

		editExternalLinkURL.setParameter(
			ActionRequest.ACTION_NAME, "/edit_external_link");

		PortletURL portletURL = _getPortletURL(
			"/accounts/view_account", "details");

		editExternalLinkURL.setParameter("redirect", portletURL.toString());

		editExternalLinkURL.setParameter("accountKey", _account.getKey());
		editExternalLinkURL.setParameter("externalLinkKey", externalLinkKey);

		return editExternalLinkURL.toString();
	}

	private String _getExternalLinkEntityId(String domain, String entityName) {
		ExternalLink[] externalLinks = _account.getExternalLinks();

		if (externalLinks != null) {
			for (ExternalLink externalLink : externalLinks) {
				if (domain.equals(externalLink.getDomain()) &&
					entityName.equals(externalLink.getEntityName())) {

					return externalLink.getEntityId();
				}
			}
		}

		return StringPool.DASH;
	}

	private String _getExternalLinkKey(String domain, String entityName) {
		ExternalLink[] externalLinks = _account.getExternalLinks();

		if (externalLinks != null) {
			for (ExternalLink externalLink : externalLinks) {
				if (domain.equals(externalLink.getDomain()) &&
					entityName.equals(externalLink.getEntityName())) {

					return externalLink.getKey();
				}
			}
		}

		return StringPool.BLANK;
	}

	private PortletURL _getPortletURL(String mvcRenderCommandName, String tab) {
		PortletURL portletURL = _liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", mvcRenderCommandName);
		portletURL.setParameter("tabs1", tab);
		portletURL.setParameter("accountKey", _account.getKey());

		return portletURL;
	}

	private ProductPurchase _getSLAProductPurchase() {
		if (_slaProductPurchase != null) {
			return _slaProductPurchase;
		}

		return _accountReader.getSLAProductPurchase(_account);
	}

	private String _getUpdateExternalLinkURL(String externalLinkKey) {
		if (Validator.isNotNull(externalLinkKey)) {
			return _getEditExternalLinkURL(externalLinkKey);
		}

		return _getAddExternalLinkURL();
	}

	private final Account _account;
	private final AccountReader _accountReader;
	private final Format _dateFormat;
	private final Format _dateTimeFormat;
	private final Team _firstLineSupportTeam;
	private final HttpServletRequest _httpServletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private final Team _partnerTeam;
	private final PortletRequest _portletRequest;
	private final PortletResponse _portletResponse;
	private ProductPurchase _slaProductPurchase;

}