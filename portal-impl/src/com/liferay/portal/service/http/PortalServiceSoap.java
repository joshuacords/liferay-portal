/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.PortalServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>PortalServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
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
 * @see PortalServiceHttp
 * @generated
 */
public class PortalServiceSoap {

	public static String getAutoDeployDirectory() throws RemoteException {
		try {
			String returnValue = PortalServiceUtil.getAutoDeployDirectory();

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getBuildNumber() throws RemoteException {
		try {
			int returnValue = PortalServiceUtil.getBuildNumber();

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String getVersion() throws RemoteException {
		try {
			String returnValue = PortalServiceUtil.getVersion();

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void testAddClassName_Rollback(String classNameValue)
		throws RemoteException {

		try {
			PortalServiceUtil.testAddClassName_Rollback(classNameValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void testAddClassName_Success(String classNameValue)
		throws RemoteException {

		try {
			PortalServiceUtil.testAddClassName_Success(classNameValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void
			testAddClassNameAndTestTransactionPortletBar_PortalRollback(
				String transactionPortletBarText)
		throws RemoteException {

		try {
			PortalServiceUtil.
				testAddClassNameAndTestTransactionPortletBar_PortalRollback(
					transactionPortletBarText);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void
			testAddClassNameAndTestTransactionPortletBar_PortletRollback(
				String transactionPortletBarText)
		throws RemoteException {

		try {
			PortalServiceUtil.
				testAddClassNameAndTestTransactionPortletBar_PortletRollback(
					transactionPortletBarText);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void testAddClassNameAndTestTransactionPortletBar_Success(
			String transactionPortletBarText)
		throws RemoteException {

		try {
			PortalServiceUtil.
				testAddClassNameAndTestTransactionPortletBar_Success(
					transactionPortletBarText);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void testAutoSyncHibernateSessionStateOnTxCreation()
		throws RemoteException {

		try {
			PortalServiceUtil.testAutoSyncHibernateSessionStateOnTxCreation();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void testDeleteClassName() throws RemoteException {
		try {
			PortalServiceUtil.testDeleteClassName();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int testGetBuildNumber() throws RemoteException {
		try {
			int returnValue = PortalServiceUtil.testGetBuildNumber();

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void testGetUserId() throws RemoteException {
		try {
			PortalServiceUtil.testGetUserId();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static boolean testHasClassName() throws RemoteException {
		try {
			boolean returnValue = PortalServiceUtil.testHasClassName();

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(PortalServiceSoap.class);

}