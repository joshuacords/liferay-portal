/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.provisioning.auth.ProvisioningContactThreadLocal;
import com.liferay.osb.provisioning.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.provisioning.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.provisioning.koroneiki.constants.ProductPurchaseConstants;
import com.liferay.osb.provisioning.koroneiki.exception.ContactAccountRoleAlreadyExistsException;
import com.liferay.osb.provisioning.koroneiki.exception.NoSuchContactException;
import com.liferay.osb.provisioning.koroneiki.exception.UnexpectedErrorException;
import com.liferay.osb.provisioning.koroneiki.exception.ValidationException;
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.osb.provisioning.koroneiki.validator.ContactRoleValidator;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.rest.resource.v1_0.AccountResource;
import com.liferay.osb.provisioning.util.CustomerPortalRelease;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/account.properties",
	scope = ServiceScope.PROTOTYPE, service = AccountResource.class
)
public class AccountResourceImpl extends BaseAccountResourceImpl {

	@Override
	public void deleteAccountContactByEmailAddresContactEmailAddressRole(
			String accountKey, String contactEmailAddress,
			String[] contactRoleNames)
		throws Exception {

		_checkAccountAdminContactRole(accountKey);
		_validate(accountKey, contactEmailAddress, contactRoleNames);

		String[] contactRoleKeys = new String[contactRoleNames.length];

		for (int i = 0; i < contactRoleNames.length; i++) {
			String contactRoleName = contactRoleNames[i];

			ContactRole contactRole = _contactRoleWebService.getContactRole(
				ContactRole.Type.ACCOUNT_CUSTOMER.toString(), contactRoleName);

			contactRoleKeys[i] = contactRole.getKey();
		}

		_accountWebService.unassignContactRolesByEmailAddress(
			_getAgentName(), _getAgentUID(), accountKey, contactEmailAddress,
			contactRoleKeys);
	}

	@Override
	public void putAccountContactByEmailAddresContactEmailAddressRole(
			String accountKey, String contactEmailAddress,
			String[] contactRoleNames, String firstName, String lastName)
		throws Exception {

		_checkAccountAdminContactRole(accountKey);

		try {
			_assignAccountContactRole(
				accountKey, contactEmailAddress, contactRoleNames, firstName,
				lastName);
		}
		catch (Exception exception) {
			if (exception instanceof ContactAccountRoleAlreadyExistsException ||
				exception instanceof NoSuchModelException ||
				exception instanceof ValidationException) {

				throw exception;
			}

			_log.error(exception, exception);

			throw new UnexpectedErrorException(exception);
		}
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		try {
			StringUtil.readLines(
				AccountResourceImpl.class.getResourceAsStream(
					"/dependencies/bad_domains.txt"),
				_badDomains);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private void _assignAccountContactRole(
			String accountKey, String contactEmailAddress,
			String[] contactRoleNames, String firstName, String lastName)
		throws Exception {

		String domain = contactEmailAddress.substring(
			contactEmailAddress.indexOf(StringPool.AT) + 1);

		if (_badDomains.contains(domain)) {
			throw new ValidationException(
				"Domain " + domain + " is not allowed");
		}

		String[] contactRoleKeys = new String[contactRoleNames.length];
		boolean checkSupportSeatCount = false;

		for (int i = 0; i < contactRoleNames.length; i++) {
			ContactRole contactRole = _contactRoleWebService.getContactRole(
				ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
				contactRoleNames[i]);

			contactRoleKeys[i] = contactRole.getKey();

			if (ArrayUtil.contains(
					ContactRoleConstants.SUPPORT_SEAT_CONTACT_ROLES,
					contactRoleNames[i])) {

				checkSupportSeatCount = true;
			}
		}

		Account account = _accountWebService.getAccount(accountKey);

		Contact contact = _contactIdentityProvider.fetchContactByEmailAddress(
			contactEmailAddress, true);

		List<ContactRole> contactRoles = new ArrayList<>();

		if (contact != null) {
			contactRoles =
				_contactRoleWebService.getAccountCustomerContactRoles(
					accountKey, contactEmailAddress, 1, 1000);

			for (ContactRole contactRole : contactRoles) {
				if (ArrayUtil.contains(contactRoleKeys, contactRole.getKey())) {
					throw new ContactAccountRoleAlreadyExistsException();
				}

				if (checkSupportSeatCount &&
					ArrayUtil.contains(
						ContactRoleConstants.SUPPORT_SEAT_CONTACT_ROLES,
						contactRole.getName())) {

					checkSupportSeatCount = false;
				}
			}
		}
		else {
			if (Validator.isNull(firstName) || Validator.isNull(lastName)) {
				throw new ValidationException(
					"New contact creation needs first and last name");
			}

			String subscriptionState = _accountReader.getSubscriptionState(
				account);

			if (subscriptionState.equals(
					ProductPurchaseConstants.STATE_ACTIVE)) {

				_contactIdentityProvider.createContact(
					contactEmailAddress, firstName, StringPool.BLANK, lastName);
			}
			else {
				throw new NoSuchContactException(
					"No contact exists with email address " +
						contactEmailAddress);
			}
		}

		if (checkSupportSeatCount) {
			int supportSeatCount = _accountReader.getSupportSeatCount(account);
			int maxSupportSeatCount = _accountReader.getMaxSupportSeatCount(
				account);

			if ((supportSeatCount + 1) > maxSupportSeatCount) {
				throw new ValidationException(
					"Account has reached the maximum allowed ticket " +
						"requesters");
			}
		}

		_accountWebService.assignContactRolesByEmailAddress(
			_getAgentName(), _getAgentUID(), accountKey, contactEmailAddress,
			contactRoleKeys);

		if (contact != null) {
			_customerPortalRelease.sendContactAssignedWelcomeEmail(
				contact, account, contactRoles, contactRoleKeys);
		}
	}

	private void _checkAccountAdminContactRole(String accountKey)
		throws Exception {

		Contact contact = ProvisioningContactThreadLocal.getContact();

		if (contact != null) {
			List<ContactRole> contactRoles =
				_contactRoleWebService.getAccountCustomerContactRoles(
					accountKey, contact.getEmailAddress(), 1, 1000);

			for (ContactRole contactRole : contactRoles) {
				String name = contactRole.getName();

				if (name.equals(ContactRoleConstants.NAME_PARTNER_MANAGER) ||
					name.equals(
						ContactRoleConstants.NAME_SUPPORT_ADMINISTRATOR)) {

					return;
				}
			}
		}
		else if (_isOmniAdmin()) {
			return;
		}

		StringBundler sb = new StringBundler(9);

		if (contact != null) {
			sb.append(contact.getEmailAddress());
			sb.append(StringPool.SPACE);
		}

		sb.append(contextHttpServletRequest.getRemoteAddr());
		sb.append(" does not have permissions to ");
		sb.append(contextHttpServletRequest.getMethod());
		sb.append(StringPool.SPACE);
		sb.append(contextHttpServletRequest.getRequestURI());

		if (Validator.isNotNull(contextHttpServletRequest.getQueryString())) {
			sb.append(StringPool.QUESTION);
			sb.append(contextHttpServletRequest.getQueryString());
		}

		_log.error(sb.toString());

		throw new PrincipalException();
	}

	private String _getAgentName() {
		Contact contact = ProvisioningContactThreadLocal.getContact();

		if (contact != null) {
			return StringBundler.concat(
				contact.getFirstName(), StringPool.SPACE,
				contact.getLastName());
		}

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		User user = permissionChecker.getUser();

		return user.getFullName();
	}

	private String _getAgentUID() {
		Contact contact = ProvisioningContactThreadLocal.getContact();

		if (contact != null) {
			return contact.getUuid();
		}

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		User user = permissionChecker.getUser();

		return user.getUuid();
	}

	private boolean _isOmniAdmin() {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker.isOmniadmin()) {
			return true;
		}

		return false;
	}

	private void _validate(
			String accountKey, String emailAddress, String[] contactRoleNames)
		throws Exception {

		if (!ArrayUtil.contains(
				contactRoleNames, ContactRoleConstants.NAME_PARTNER_MANAGER) &&
			!ArrayUtil.contains(
				contactRoleNames,
				ContactRoleConstants.NAME_SUPPORT_ADMINISTRATOR)) {

			return;
		}

		boolean hasPartnerManager = false;
		boolean hasSupportAdministrator = false;

		List<ContactRole> curContactRoles =
			_contactRoleWebService.getAccountCustomerContactRoles(
				accountKey, emailAddress, 1, 1000);

		for (ContactRole curContactRole : curContactRoles) {
			String name = curContactRole.getName();

			if (name.equals(ContactRoleConstants.NAME_PARTNER_MANAGER)) {
				hasPartnerManager = true;
			}
			else if (name.equals(
						ContactRoleConstants.NAME_SUPPORT_ADMINISTRATOR)) {

				hasSupportAdministrator = true;
			}
		}

		if ((ArrayUtil.contains(
				contactRoleNames, ContactRoleConstants.NAME_PARTNER_MANAGER) &&
			 ArrayUtil.contains(
				 contactRoleNames,
				 ContactRoleConstants.NAME_SUPPORT_ADMINISTRATOR) &&
			 hasPartnerManager && hasSupportAdministrator) ||
			(ArrayUtil.contains(
				contactRoleNames, ContactRoleConstants.NAME_PARTNER_MANAGER) &&
			 !hasSupportAdministrator) ||
			(ArrayUtil.contains(
				contactRoleNames,
				ContactRoleConstants.NAME_SUPPORT_ADMINISTRATOR) &&
			 !hasPartnerManager)) {

			_contactRoleValidator.validateAdminContactRoleUnassignment(
				accountKey, emailAddress);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AccountResourceImpl.class);

	@Reference
	private AccountReader _accountReader;

	@Reference
	private AccountWebService _accountWebService;

	private final Set<String> _badDomains = new HashSet<>();

	@Reference(target = "(provider=okta)")
	private ContactIdentityProvider _contactIdentityProvider;

	@Reference
	private ContactRoleValidator _contactRoleValidator;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private CustomerPortalRelease _customerPortalRelease;

}