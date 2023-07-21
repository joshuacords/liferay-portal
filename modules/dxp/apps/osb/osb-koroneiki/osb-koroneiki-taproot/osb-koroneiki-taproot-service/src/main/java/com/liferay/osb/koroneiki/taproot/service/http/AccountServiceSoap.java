/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.http;

import com.liferay.osb.koroneiki.taproot.service.AccountServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>AccountServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.osb.koroneiki.taproot.model.AccountSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.osb.koroneiki.taproot.model.Account</code>, that is translated to a
 * <code>com.liferay.osb.koroneiki.taproot.model.AccountSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountServiceHttp
 * @generated
 */
public class AccountServiceSoap {

	public static com.liferay.osb.koroneiki.taproot.model.AccountSoap
			addAccount(
				long parentAccountId, String name, String code,
				String description, long logoId, String contactEmailAddress,
				String profileEmailAddress, String phoneNumber,
				String faxNumber, String website, String tier, String region,
				String dataRegion, String language, boolean internal,
				String status,
				com.liferay.osb.koroneiki.taproot.model.AccountFieldSoap[]
					accountFields)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.Account returnValue =
				AccountServiceUtil.addAccount(
					parentAccountId, name, code, description, logoId,
					contactEmailAddress, profileEmailAddress, phoneNumber,
					faxNumber, website, tier, region, dataRegion, language,
					internal, status,
					com.liferay.osb.koroneiki.taproot.model.impl.
						AccountFieldModelImpl.toModels(accountFields));

			return com.liferay.osb.koroneiki.taproot.model.AccountSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.AccountSoap
			deleteAccount(long accountId)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.Account returnValue =
				AccountServiceUtil.deleteAccount(accountId);

			return com.liferay.osb.koroneiki.taproot.model.AccountSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.AccountSoap
			deleteAccount(String accountKey)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.Account returnValue =
				AccountServiceUtil.deleteAccount(accountKey);

			return com.liferay.osb.koroneiki.taproot.model.AccountSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.AccountSoap
			getAccount(long accountId)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.Account returnValue =
				AccountServiceUtil.getAccount(accountId);

			return com.liferay.osb.koroneiki.taproot.model.AccountSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.AccountSoap
			getAccount(String accountKey)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.Account returnValue =
				AccountServiceUtil.getAccount(accountKey);

			return com.liferay.osb.koroneiki.taproot.model.AccountSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.AccountSoap[]
			getAccounts(long parentAccountId, int start, int end)
		throws RemoteException {

		try {
			java.util.List<com.liferay.osb.koroneiki.taproot.model.Account>
				returnValue = AccountServiceUtil.getAccounts(
					parentAccountId, start, end);

			return com.liferay.osb.koroneiki.taproot.model.AccountSoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.AccountSoap[]
			getAccounts(
				String domain, String entityName, String entityId, int start,
				int end)
		throws RemoteException {

		try {
			java.util.List<com.liferay.osb.koroneiki.taproot.model.Account>
				returnValue = AccountServiceUtil.getAccounts(
					domain, entityName, entityId, start, end);

			return com.liferay.osb.koroneiki.taproot.model.AccountSoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getAccountsCount(long parentAccountId)
		throws RemoteException {

		try {
			int returnValue = AccountServiceUtil.getAccountsCount(
				parentAccountId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getAccountsCount(
			String domain, String entityName, String entityId)
		throws RemoteException {

		try {
			int returnValue = AccountServiceUtil.getAccountsCount(
				domain, entityName, entityId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.AccountSoap[]
			getContactAccounts(long contactId, int start, int end)
		throws RemoteException {

		try {
			java.util.List<com.liferay.osb.koroneiki.taproot.model.Account>
				returnValue = AccountServiceUtil.getContactAccounts(
					contactId, start, end);

			return com.liferay.osb.koroneiki.taproot.model.AccountSoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getContactAccountsCount(long contactId)
		throws RemoteException {

		try {
			int returnValue = AccountServiceUtil.getContactAccountsCount(
				contactId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.AccountSoap[]
			getTeamAccounts(long teamId, int start, int end)
		throws RemoteException {

		try {
			java.util.List<com.liferay.osb.koroneiki.taproot.model.Account>
				returnValue = AccountServiceUtil.getTeamAccounts(
					teamId, start, end);

			return com.liferay.osb.koroneiki.taproot.model.AccountSoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getTeamAccountsCount(long teamId) throws RemoteException {
		try {
			int returnValue = AccountServiceUtil.getTeamAccountsCount(teamId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.AccountSoap
			updateAccount(
				long accountId, long parentAccountId, String name, String code,
				String description, long logoId, String contactEmailAddress,
				String profileEmailAddress, String phoneNumber,
				String faxNumber, String website, String tier, String region,
				String dataRegion, String language, boolean internal,
				String status,
				com.liferay.osb.koroneiki.taproot.model.AccountFieldSoap[]
					accountFields)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.Account returnValue =
				AccountServiceUtil.updateAccount(
					accountId, parentAccountId, name, code, description, logoId,
					contactEmailAddress, profileEmailAddress, phoneNumber,
					faxNumber, website, tier, region, dataRegion, language,
					internal, status,
					com.liferay.osb.koroneiki.taproot.model.impl.
						AccountFieldModelImpl.toModels(accountFields));

			return com.liferay.osb.koroneiki.taproot.model.AccountSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.AccountSoap
			updateAccount(
				String accountKey, long parentAccountId, String name,
				String code, String description, long logoId,
				String contactEmailAddress, String profileEmailAddress,
				String phoneNumber, String faxNumber, String website,
				String tier, String region, String dataRegion, String language,
				boolean internal, String status,
				com.liferay.osb.koroneiki.taproot.model.AccountFieldSoap[]
					accountFields)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.Account returnValue =
				AccountServiceUtil.updateAccount(
					accountKey, parentAccountId, name, code, description,
					logoId, contactEmailAddress, profileEmailAddress,
					phoneNumber, faxNumber, website, tier, region, dataRegion,
					language, internal, status,
					com.liferay.osb.koroneiki.taproot.model.impl.
						AccountFieldModelImpl.toModels(accountFields));

			return com.liferay.osb.koroneiki.taproot.model.AccountSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AccountServiceSoap.class);

}