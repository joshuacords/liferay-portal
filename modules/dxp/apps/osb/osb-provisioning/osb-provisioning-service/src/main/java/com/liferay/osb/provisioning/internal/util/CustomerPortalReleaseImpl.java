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

package com.liferay.osb.provisioning.internal.util;

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Entitlement;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.provisioning.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.provisioning.koroneiki.constants.EntitlementConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.osb.provisioning.util.CustomerPortalRelease;
import com.liferay.osgi.util.StringPlus;
import com.liferay.petra.content.ContentUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SubscriptionSender;
import com.liferay.portal.kernel.util.Validator;

import java.net.URL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"accountAccessEUOktaId=", "accountAccessUSOktaId=", "accountKeys=",
		"partner=", "provisioningEmailAddressAustralia=",
		"provisioningEmailAddressBrazil=", "provisioningEmailAddressChina=",
		"provisioningEmailAddressGlobal=", "provisioningEmailAddressHungary=",
		"provisioningEmailAddressIndia=", "provisioningEmailAddressJapan=",
		"provisioningEmailAddressSpain=", "provisioningEmailAddressUS=",
		"provisioningOktaId=", "regions="
	},
	service = CustomerPortalRelease.class
)
public class CustomerPortalReleaseImpl implements CustomerPortalRelease {

	public boolean hasAccountAccessPermission(Account account, Contact contact)
		throws Exception {

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addLambdaEquals(true, "contactUuids", contact.getUuid());

		FilterQuery nestedFilterQuery = new FilterQuery();

		nestedFilterQuery.addLambdaEquals(
			false, "externalLinkEntityIds",
			StringBundler.concat(
				ExternalLinkDomain.OKTA, "_", ExternalLinkEntityName.OKTA_GROUP,
				"_", _accountAccessEUOktaId));
		nestedFilterQuery.addLambdaEquals(
			false, "externalLinkEntityIds",
			StringBundler.concat(
				ExternalLinkDomain.OKTA, "_", ExternalLinkEntityName.OKTA_GROUP,
				"_", _provisioningOktaId));

		if (account.getRegion() == Account.Region.UNITED_STATES) {
			nestedFilterQuery.addLambdaEquals(
				false, "externalLinkEntityIds",
				StringBundler.concat(
					ExternalLinkDomain.OKTA, "_",
					ExternalLinkEntityName.OKTA_GROUP, "_",
					_accountAccessUSOktaId));
		}

		filterQuery.addFilterQuery(true, nestedFilterQuery);

		List<Team> teams = _teamWebService.search(
			StringPool.BLANK, filterQuery, 1, 1000, StringPool.BLANK);

		if (!teams.isEmpty()) {
			return true;
		}

		return false;
	}

	public boolean hasAccountManageLicenseKeysPermission(
			String accountKey, Contact contact)
		throws Exception {

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addLambdaEquals(true, "contactUuids", contact.getUuid());

		FilterQuery nestedFilterQuery = new FilterQuery();

		nestedFilterQuery.addLambdaEquals(
			false, "externalLinkEntityIds",
			StringBundler.concat(
				ExternalLinkDomain.OKTA, "_", ExternalLinkEntityName.OKTA_GROUP,
				"_", _provisioningOktaId));

		filterQuery.addFilterQuery(true, nestedFilterQuery);

		List<Team> teams = _teamWebService.search(
			StringPool.BLANK, filterQuery, 1, 1000, StringPool.BLANK);

		if (!teams.isEmpty()) {
			return true;
		}

		List<ContactRole> contactRoles =
			_contactRoleWebService.getAccountContactRoles(
				accountKey, contact.getEmailAddress(), 1, 1000);

		for (ContactRole contactRole : contactRoles) {
			String name = contactRole.getName();

			if (name.equals(
					ContactRoleConstants.NAME_LIFERAY_CUSTOMER_SUCCESS) ||
				name.equals(ContactRoleConstants.NAME_LIFERAY_SALES) ||
				name.equals(ContactRoleConstants.NAME_SUPPORT_ADMINISTRATOR)) {

				return true;
			}
		}

		return false;
	}

	public boolean isEnabled(
		String accountKey, Set<ProductPurchase> productPurchases,
		Account.Region accountRegion) {

		if (Validator.isNotNull(accountKey) &&
			_accountKeys.contains(accountKey)) {

			return true;
		}

		if (_partner) {
			for (ProductPurchase productPurchase : productPurchases) {
				Product product = productPurchase.getProduct();

				String name = StringUtil.toLowerCase(product.getName());

				if (name.contains("partnership")) {
					return true;
				}
			}
		}

		if (_regions.contains(accountRegion.toString())) {
			return true;
		}

		return false;
	}

	public void sendAutoProvisionedWelcomeEmail(Account account)
		throws Exception {

		FilterQuery filterQuery = new FilterQuery();

		for (ContactRole contactRole : _getCustomerContactRoles()) {
			filterQuery.addLambdaEquals(
				false, "accountKeysContactRoleKeys",
				account.getKey() + "_" + contactRole.getKey());
		}

		List<Contact> contacts = _contactWebService.search(
			StringPool.BLANK, filterQuery, 1, 1000, StringPool.BLANK);

		for (Contact contact : contacts) {
			if (!GetterUtil.getBoolean(contact.getEmailAddressVerified())) {
				continue;
			}

			_sendContactWelcomeEmail(contact, new Account[] {account});
		}
	}

	public void sendAutoProvisionedWelcomeEmail(
			String emailAddress, Account account,
			List<ContactRole> currentContactRoles,
			List<ContactRole> addContactRoles)
		throws Exception {

		Contact contact = _contactWebService.fetchContactByEmailAddress(
			emailAddress);

		if ((contact == null) ||
			!GetterUtil.getBoolean(contact.getEmailAddressVerified())) {

			return;
		}

		if (!_isAssignedNewCustomerPortalRole(
				currentContactRoles, addContactRoles)) {

			return;
		}

		_sendContactWelcomeEmail(contact, new Account[] {account});
	}

	public void sendContactAssignedWelcomeEmail(
			Contact contact, Account account,
			List<ContactRole> currentContactRoles, String[] addContactRoleKeys)
		throws Exception {

		if ((contact == null) ||
			!GetterUtil.getBoolean(contact.getEmailAddressVerified())) {

			return;
		}

		if (!_isEnabled(account)) {
			return;
		}

		List<ContactRole> addContactRoles = new ArrayList<>();

		for (String contactRoleKey : addContactRoleKeys) {
			ContactRole contactRole = _contactRoleWebService.getContactRole(
				contactRoleKey);

			addContactRoles.add(contactRole);
		}

		if (!_isAssignedNewCustomerPortalRole(
				currentContactRoles, addContactRoles)) {

			return;
		}

		_sendContactWelcomeEmail(contact, new Account[] {account});
	}

	public void sendContactVerifiedWelcomeEmail(Contact contact)
		throws Exception {

		if (!_partner && _regions.isEmpty()) {
			return;
		}

		FilterQuery filterQuery = new FilterQuery();

		if (!_regions.isEmpty()) {
			FilterQuery customerFilterQuery = new FilterQuery();

			FilterQuery nestedFilterQuery1 = new FilterQuery();

			for (ContactRole contactRole : _getCustomerContactRoles()) {
				nestedFilterQuery1.addLambdaEquals(
					false, "contactUuidContactRoleKeys",
					contact.getUuid() + "_" + contactRole.getKey());
			}

			customerFilterQuery.addFilterQuery(true, nestedFilterQuery1);

			FilterQuery nestedFilterQuery2 = new FilterQuery();

			for (String entitlement : EntitlementConstants.SLAS) {
				nestedFilterQuery2.addLambdaEquals(
					false, "entitlements", entitlement);
			}

			customerFilterQuery.addFilterQuery(true, nestedFilterQuery2);

			FilterQuery nestedFilterQuery3 = new FilterQuery();

			for (String region : _regions) {
				nestedFilterQuery3.addEquals(false, "region", region);
			}

			customerFilterQuery.addFilterQuery(true, nestedFilterQuery3);

			filterQuery.addFilterQuery(false, customerFilterQuery);
		}

		if (_partner) {
			FilterQuery partnerFilterQuery = new FilterQuery();

			partnerFilterQuery.addLambdaEquals(
				true, "entitlements", EntitlementConstants.PARTNER);

			FilterQuery nestedFilterQuery = new FilterQuery();

			for (ContactRole contactRole : _getPartnerContactRoles()) {
				nestedFilterQuery.addLambdaEquals(
					false, "contactUuidContactRoleKeys",
					contact.getUuid() + "_" + contactRole.getKey());
			}

			partnerFilterQuery.addFilterQuery(true, nestedFilterQuery);

			filterQuery.addFilterQuery(false, partnerFilterQuery);
		}

		List<Account> accounts = _accountWebService.search(
			StringPool.BLANK, filterQuery, 1, 1000, StringPool.BLANK);

		_sendContactWelcomeEmail(contact, accounts.toArray(new Account[0]));
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) throws Exception {
		_accountAccessEUOktaId = GetterUtil.getString(
			properties.get("accountAccessEUOktaId"));
		_accountAccessUSOktaId = GetterUtil.getString(
			properties.get("accountAccessUSOktaId"));
		_accountKeys = StringPlus.asList(properties.get("accountKeys"));
		_partner = GetterUtil.getBoolean(properties.get("partner"));
		_provisioningEmailAddressAustralia = GetterUtil.getString(
			properties.get("provisioningEmailAddressAustralia"));
		_provisioningEmailAddressBrazil = GetterUtil.getString(
			properties.get("provisioningEmailAddressBrazil"));
		_provisioningEmailAddressChina = GetterUtil.getString(
			properties.get("provisioningEmailAddressChina"));
		_provisioningEmailAddressGlobal = GetterUtil.getString(
			properties.get("provisioningEmailAddressGlobal"));
		_provisioningEmailAddressHungary = GetterUtil.getString(
			properties.get("provisioningEmailAddressHungary"));
		_provisioningEmailAddressIndia = GetterUtil.getString(
			properties.get("provisioningEmailAddressIndia"));
		_provisioningEmailAddressJapan = GetterUtil.getString(
			properties.get("provisioningEmailAddressJapan"));
		_provisioningEmailAddressSpain = GetterUtil.getString(
			properties.get("provisioningEmailAddressSpain"));
		_provisioningEmailAddressUS = GetterUtil.getString(
			properties.get("provisioningEmailAddressUS"));
		_provisioningOktaId = GetterUtil.getString(
			properties.get("provisioningOktaId"));
		_regions = StringPlus.asList(properties.get("regions"));
	}

	private String _getAccountInvitationMessage(
		Account[] accounts, ResourceBundle resourceBundle) {

		if (accounts.length == 1) {
			Account account = accounts[0];

			return LanguageUtil.format(
				resourceBundle,
				"you-have-been-invited-to-the-liferay-project-x",
				HtmlUtil.escape(account.getName()));
		}

		StringBundler sb = new StringBundler();

		sb.append(
			LanguageUtil.get(
				resourceBundle,
				"you-have-been-invited-to-the-following-liferay-projects"));
		sb.append("<br /><ul>");

		for (Account account : accounts) {
			sb.append("<li>");
			sb.append(HtmlUtil.escape(account.getName()));
			sb.append("</li>");
		}

		sb.append("</ul>");

		return sb.toString();
	}

	private String _getContactFullName(Contact contact) {
		StringBundler sb = new StringBundler(5);

		if (Validator.isNotNull(contact.getFirstName())) {
			sb.append(contact.getFirstName());
		}

		if (Validator.isNotNull(contact.getMiddleName())) {
			if (sb.length() > 0) {
				sb.append(StringPool.SPACE);
			}

			sb.append(contact.getMiddleName());
		}

		if (Validator.isNotNull(contact.getLastName())) {
			if (sb.length() > 0) {
				sb.append(StringPool.SPACE);
			}

			sb.append(contact.getLastName());
		}

		return sb.toString();
	}

	private Set<String> _getContactRoleNames(
			Contact contact, Account[] accounts)
		throws Exception {

		Set<String> contactRoleNames = new HashSet<>();

		for (Account account : accounts) {
			List<ContactRole> contactRoles =
				_contactRoleWebService.getAccountCustomerContactRoles(
					account.getKey(), contact.getEmailAddress(), 1, 1000);

			for (ContactRole contactRole : contactRoles) {
				contactRoleNames.add(contactRole.getName());
			}
		}

		return contactRoleNames;
	}

	private ContactRole[] _getCustomerContactRoles() throws Exception {
		if (_customerContactRoles != null) {
			return _customerContactRoles;
		}

		ContactRole[] customerContactRoles =
			new ContactRole[_CUSTOMER_CONTACT_ROLE_NAMES.length];

		for (int i = 0; i < _CUSTOMER_CONTACT_ROLE_NAMES.length; i++) {
			customerContactRoles[i] = _contactRoleWebService.getContactRole(
				ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
				_CUSTOMER_CONTACT_ROLE_NAMES[i]);
		}

		_customerContactRoles = customerContactRoles;

		return _customerContactRoles;
	}

	private String _getEmailTemplate(
		String templateName, String defaultTemplateName) {

		ClassLoader classLoader =
			CustomerPortalReleaseImpl.class.getClassLoader();

		String templateDirName =
			"com/liferay/osb/provisioning/internal/dependencies/";

		URL url = classLoader.getResource(templateDirName + templateName);

		if (url != null) {
			return ContentUtil.get(classLoader, templateDirName + templateName);
		}

		return ContentUtil.get(
			classLoader, templateDirName + defaultTemplateName);
	}

	private ContactRole[] _getPartnerContactRoles() throws Exception {
		if (_partnerContactRoles != null) {
			return _partnerContactRoles;
		}

		ContactRole[] partnerContactRoles =
			new ContactRole[_PARTNER_CONTACT_ROLE_NAMES.length];

		for (int i = 0; i < _PARTNER_CONTACT_ROLE_NAMES.length; i++) {
			partnerContactRoles[i] = _contactRoleWebService.getContactRole(
				ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
				_PARTNER_CONTACT_ROLE_NAMES[i]);
		}

		_partnerContactRoles = partnerContactRoles;

		return _partnerContactRoles;
	}

	private String _getProvisioningEmailAddress(Account[] accounts) {
		String provisioningEmailAddress = null;

		for (Account account : accounts) {
			String curProvisioningEmailAddress = null;

			Account.Region accountRegion = account.getRegion();

			if (accountRegion == Account.Region.AUSTRALIA) {
				curProvisioningEmailAddress =
					_provisioningEmailAddressAustralia;
			}
			else if (accountRegion == Account.Region.BRAZIL) {
				curProvisioningEmailAddress = _provisioningEmailAddressBrazil;
			}
			else if (accountRegion == Account.Region.CHINA) {
				curProvisioningEmailAddress = _provisioningEmailAddressChina;
			}
			else if (accountRegion == Account.Region.HUNGARY) {
				curProvisioningEmailAddress = _provisioningEmailAddressHungary;
			}
			else if (accountRegion == Account.Region.INDIA) {
				curProvisioningEmailAddress = _provisioningEmailAddressIndia;
			}
			else if (accountRegion == Account.Region.JAPAN) {
				curProvisioningEmailAddress = _provisioningEmailAddressJapan;
			}
			else if (accountRegion == Account.Region.SPAIN) {
				curProvisioningEmailAddress = _provisioningEmailAddressSpain;
			}
			else if (accountRegion == Account.Region.UNITED_STATES) {
				curProvisioningEmailAddress = _provisioningEmailAddressUS;
			}
			else {
				curProvisioningEmailAddress = _provisioningEmailAddressGlobal;
			}

			if ((provisioningEmailAddress != null) &&
				!provisioningEmailAddress.equals(curProvisioningEmailAddress)) {

				return _provisioningEmailAddressGlobal;
			}

			provisioningEmailAddress = curProvisioningEmailAddress;
		}

		return provisioningEmailAddress;
	}

	private String _getRoleActionsList(
			Contact contact, Account[] accounts, ResourceBundle resourceBundle)
		throws Exception {

		Set<String> contactRoleNames = _getContactRoleNames(contact, accounts);

		StringBundler sb = new StringBundler(21);

		sb.append("<ul><li>");
		sb.append(
			LanguageUtil.get(
				resourceBundle, "view-your-project's-subscriptions"));
		sb.append("</li>");

		if (contactRoleNames.contains(
				ContactRoleConstants.NAME_PARTNER_MANAGER) ||
			contactRoleNames.contains(
				ContactRoleConstants.NAME_SUPPORT_ADMINISTRATOR)) {

			sb.append("<li>");
			sb.append(
				LanguageUtil.get(
					resourceBundle, "manage-team-members-and-roles"));
			sb.append("</li>");
		}

		if (contactRoleNames.contains(
				ContactRoleConstants.NAME_SUPPORT_ADMINISTRATOR)) {

			sb.append("<li>");
			sb.append(
				LanguageUtil.get(
					resourceBundle, "activate-your-liferay-products"));
			sb.append("</li>");
		}

		if (contactRoleNames.contains(
				ContactRoleConstants.NAME_PARTNER_MEMBER) ||
			contactRoleNames.contains(
				ContactRoleConstants.NAME_SUPPORT_REQUESTER) ||
			contactRoleNames.contains(ContactRoleConstants.NAME_SUPPORT_USER)) {

			sb.append("<li>");
			sb.append(
				LanguageUtil.get(
					resourceBundle,
					"view-the-activation-status-of-your-liferay-products"));
			sb.append("</li>");
		}

		sb.append("<li>");
		sb.append(
			LanguageUtil.get(
				resourceBundle,
				"learn-how-to-succeed-with-each-of-our-products"));
		sb.append("</li><li>");
		sb.append(
			LanguageUtil.get(
				resourceBundle, "search-our-extensive-knowledge-base"));
		sb.append("</li>");

		if (contactRoleNames.contains(
				ContactRoleConstants.NAME_SUPPORT_ADMINISTRATOR) ||
			contactRoleNames.contains(
				ContactRoleConstants.NAME_SUPPORT_REQUESTER)) {

			sb.append("<li>");
			sb.append(
				LanguageUtil.get(
					resourceBundle, "request-help-from-our-support-team"));
			sb.append("</li>");
		}

		sb.append("</ul>");

		return sb.toString();
	}

	private boolean _isAssignedNewCustomerPortalRole(
		List<ContactRole> currentContactRoles,
		List<ContactRole> addContactRoles) {

		for (ContactRole contactRole : currentContactRoles) {
			if (ArrayUtil.contains(
					_CUSTOMER_CONTACT_ROLE_NAMES, contactRole.getName()) ||
				ArrayUtil.contains(
					_PARTNER_CONTACT_ROLE_NAMES, contactRole.getName())) {

				return false;
			}
		}

		for (ContactRole contactRole : addContactRoles) {
			if (ArrayUtil.contains(
					_CUSTOMER_CONTACT_ROLE_NAMES, contactRole.getName()) ||
				ArrayUtil.contains(
					_PARTNER_CONTACT_ROLE_NAMES, contactRole.getName())) {

				return true;
			}
		}

		return false;
	}

	private boolean _isEnabled(Account account) {
		if (_accountKeys.contains(account.getKey())) {
			return true;
		}

		Entitlement[] entitlements = account.getEntitlements();

		for (Entitlement entitlement : entitlements) {
			String name = entitlement.getName();

			if (_partner && name.equals(EntitlementConstants.PARTNER)) {
				return true;
			}

			if (ArrayUtil.contains(EntitlementConstants.SLAS, name) &&
				_regions.contains(account.getRegionAsString())) {

				return true;
			}
		}

		return false;
	}

	private void _sendContactWelcomeEmail(Contact contact, Account[] accounts)
		throws Exception {

		if (ArrayUtil.isEmpty(accounts)) {
			return;
		}

		String languageId = contact.getLanguageId();

		if (Validator.isNull(languageId)) {
			languageId = LocaleUtil.toLanguageId(LocaleUtil.US);
		}

		String body = _getEmailTemplate(
			"email_provisioning_welcome_body_" + languageId + ".tmpl",
			"email_provisioning_welcome_body.tmpl");

		Locale locale = LocaleUtil.fromLanguageId(languageId);

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		String subject = LanguageUtil.get(
			resourceBundle, "new-liferay-project-invitation");

		if (accounts.length == 1) {
			subject += " - [$ACCOUNT_NAME$]";
		}

		String provisioningEmailAddress = _getProvisioningEmailAddress(
			accounts);

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setBody(body);
		subscriptionSender.setCompanyId(_portal.getDefaultCompanyId());

		if (accounts.length == 1) {
			Account account = accounts[0];

			subscriptionSender.setContextAttribute(
				"[$ACCOUNT_NAME$]", account.getName());
		}

		subscriptionSender.setContextAttribute(
			"[$ACCOUNT_INVITATION_MESSAGE$]",
			_getAccountInvitationMessage(accounts, resourceBundle), false);
		subscriptionSender.setContextAttribute(
			"[$CONTACT_ROLE_ACTIONS_LIST$]",
			_getRoleActionsList(contact, accounts, resourceBundle), false);
		subscriptionSender.setContextAttributes(
			"[$TO_NAME$]", _getContactFullName(contact));
		subscriptionSender.setFrom(
			provisioningEmailAddress, "Liferay Provisioning");
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setMailId("provisioning");
		subscriptionSender.setReplyToAddress(provisioningEmailAddress);
		subscriptionSender.setSubject(subject);

		subscriptionSender.addRuntimeSubscribers(
			contact.getEmailAddress(), _getContactFullName(contact));

		subscriptionSender.flushNotificationsAsync();
	}

	private static final String[] _CUSTOMER_CONTACT_ROLE_NAMES = {
		ContactRoleConstants.NAME_SUPPORT_ADMINISTRATOR,
		ContactRoleConstants.NAME_SUPPORT_REQUESTER,
		ContactRoleConstants.NAME_SUPPORT_USER
	};

	private static final String[] _PARTNER_CONTACT_ROLE_NAMES = {
		ContactRoleConstants.NAME_PARTNER_MANAGER,
		ContactRoleConstants.NAME_PARTNER_MEMBER
	};

	private String _accountAccessEUOktaId;
	private String _accountAccessUSOktaId;
	private List<String> _accountKeys;

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private ContactWebService _contactWebService;

	private ContactRole[] _customerContactRoles;
	private boolean _partner;
	private ContactRole[] _partnerContactRoles;

	@Reference
	private Portal _portal;

	private String _provisioningEmailAddressAustralia;
	private String _provisioningEmailAddressBrazil;
	private String _provisioningEmailAddressChina;
	private String _provisioningEmailAddressGlobal;
	private String _provisioningEmailAddressHungary;
	private String _provisioningEmailAddressIndia;
	private String _provisioningEmailAddressJapan;
	private String _provisioningEmailAddressSpain;
	private String _provisioningEmailAddressUS;
	private String _provisioningOktaId;
	private List<String> _regions;

	@Reference
	private TeamWebService _teamWebService;

}