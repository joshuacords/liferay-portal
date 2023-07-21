/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.http;

import com.liferay.osb.koroneiki.taproot.service.AccountNoteServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>AccountNoteServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.osb.koroneiki.taproot.model.AccountNoteSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.osb.koroneiki.taproot.model.AccountNote</code>, that is translated to a
 * <code>com.liferay.osb.koroneiki.taproot.model.AccountNoteSoap</code>. Methods that SOAP
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
 * @see AccountNoteServiceHttp
 * @generated
 */
public class AccountNoteServiceSoap {

	public static com.liferay.osb.koroneiki.taproot.model.AccountNoteSoap
			addAccountNote(
				String creatorUID, String creatorName, long accountId,
				String type, int priority, String content, String format,
				String status)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.AccountNote returnValue =
				AccountNoteServiceUtil.addAccountNote(
					creatorUID, creatorName, accountId, type, priority, content,
					format, status);

			return com.liferay.osb.koroneiki.taproot.model.AccountNoteSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.AccountNoteSoap
			deleteAccountNote(String accountNoteKey)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.AccountNote returnValue =
				AccountNoteServiceUtil.deleteAccountNote(accountNoteKey);

			return com.liferay.osb.koroneiki.taproot.model.AccountNoteSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.AccountNoteSoap
			getAccountNote(String accountNoteKey)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.AccountNote returnValue =
				AccountNoteServiceUtil.getAccountNote(accountNoteKey);

			return com.liferay.osb.koroneiki.taproot.model.AccountNoteSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.AccountNoteSoap[]
			getAccountNotes(
				long accountId, String[] types, int[] priorities,
				String[] statuses, int start, int end)
		throws RemoteException {

		try {
			java.util.List<com.liferay.osb.koroneiki.taproot.model.AccountNote>
				returnValue = AccountNoteServiceUtil.getAccountNotes(
					accountId, types, priorities, statuses, start, end);

			return com.liferay.osb.koroneiki.taproot.model.AccountNoteSoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getAccountNotesCount(
			long accountId, String[] types, int[] priorities, String[] statuses)
		throws RemoteException {

		try {
			int returnValue = AccountNoteServiceUtil.getAccountNotesCount(
				accountId, types, priorities, statuses);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.AccountNoteSoap
			updateAccountNote(
				long accountNoteId, String modifierUID, String modifierName,
				int priority, String content, String format, String status)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.taproot.model.AccountNote returnValue =
				AccountNoteServiceUtil.updateAccountNote(
					accountNoteId, modifierUID, modifierName, priority, content,
					format, status);

			return com.liferay.osb.koroneiki.taproot.model.AccountNoteSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		AccountNoteServiceSoap.class);

}