/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for Account. This utility wraps
 * <code>com.liferay.osb.koroneiki.taproot.service.impl.AccountServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AccountService
 * @generated
 */
public class AccountServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.taproot.service.impl.AccountServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static Account addAccount(
			long parentAccountId, String name, String code, String description,
			long logoId, String contactEmailAddress, String profileEmailAddress,
			String phoneNumber, String faxNumber, String website, String tier,
			String region, String dataRegion, String language, boolean internal,
			String status,
			List<com.liferay.osb.koroneiki.taproot.model.AccountField>
				accountFields)
		throws PortalException {

		return getService().addAccount(
			parentAccountId, name, code, description, logoId,
			contactEmailAddress, profileEmailAddress, phoneNumber, faxNumber,
			website, tier, region, dataRegion, language, internal, status,
			accountFields);
	}

	public static Account deleteAccount(long accountId) throws PortalException {
		return getService().deleteAccount(accountId);
	}

	public static Account deleteAccount(String accountKey)
		throws PortalException {

		return getService().deleteAccount(accountKey);
	}

	public static Account getAccount(long accountId) throws PortalException {
		return getService().getAccount(accountId);
	}

	public static Account getAccount(String accountKey) throws PortalException {
		return getService().getAccount(accountKey);
	}

	public static List<Account> getAccounts(
			long parentAccountId, int start, int end)
		throws PortalException {

		return getService().getAccounts(parentAccountId, start, end);
	}

	public static List<Account> getAccounts(
			String domain, String entityName, String entityId, int start,
			int end)
		throws PortalException {

		return getService().getAccounts(
			domain, entityName, entityId, start, end);
	}

	public static int getAccountsCount(long parentAccountId)
		throws PortalException {

		return getService().getAccountsCount(parentAccountId);
	}

	public static int getAccountsCount(
			String domain, String entityName, String entityId)
		throws PortalException {

		return getService().getAccountsCount(domain, entityName, entityId);
	}

	public static List<Account> getContactAccounts(
			long contactId, int start, int end)
		throws PortalException {

		return getService().getContactAccounts(contactId, start, end);
	}

	public static int getContactAccountsCount(long contactId)
		throws PortalException {

		return getService().getContactAccountsCount(contactId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static List<Account> getTeamAccounts(long teamId, int start, int end)
		throws PortalException {

		return getService().getTeamAccounts(teamId, start, end);
	}

	public static int getTeamAccountsCount(long teamId) throws PortalException {
		return getService().getTeamAccountsCount(teamId);
	}

	public static Account updateAccount(
			long accountId, long parentAccountId, String name, String code,
			String description, long logoId, String contactEmailAddress,
			String profileEmailAddress, String phoneNumber, String faxNumber,
			String website, String tier, String region, String dataRegion,
			String language, boolean internal, String status,
			List<com.liferay.osb.koroneiki.taproot.model.AccountField>
				accountFields)
		throws PortalException {

		return getService().updateAccount(
			accountId, parentAccountId, name, code, description, logoId,
			contactEmailAddress, profileEmailAddress, phoneNumber, faxNumber,
			website, tier, region, dataRegion, language, internal, status,
			accountFields);
	}

	public static Account updateAccount(
			String accountKey, long parentAccountId, String name, String code,
			String description, long logoId, String contactEmailAddress,
			String profileEmailAddress, String phoneNumber, String faxNumber,
			String website, String tier, String region, String dataRegion,
			String language, boolean internal, String status,
			List<com.liferay.osb.koroneiki.taproot.model.AccountField>
				accountFields)
		throws PortalException {

		return getService().updateAccount(
			accountKey, parentAccountId, name, code, description, logoId,
			contactEmailAddress, profileEmailAddress, phoneNumber, faxNumber,
			website, tier, region, dataRegion, language, internal, status,
			accountFields);
	}

	public static AccountService getService() {
		return _service;
	}

	public static void setService(AccountService service) {
		_service = service;
	}

	private static volatile AccountService _service;

}