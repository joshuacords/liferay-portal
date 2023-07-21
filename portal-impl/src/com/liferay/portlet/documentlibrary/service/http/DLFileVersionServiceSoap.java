/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.service.http;

import com.liferay.document.library.kernel.service.DLFileVersionServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>DLFileVersionServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.document.library.kernel.model.DLFileVersionSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.document.library.kernel.model.DLFileVersion</code>, that is translated to a
 * <code>com.liferay.document.library.kernel.model.DLFileVersionSoap</code>. Methods that SOAP
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
 * @see DLFileVersionServiceHttp
 * @generated
 */
public class DLFileVersionServiceSoap {

	public static com.liferay.document.library.kernel.model.DLFileVersionSoap
			getFileVersion(long fileVersionId)
		throws RemoteException {

		try {
			com.liferay.document.library.kernel.model.DLFileVersion
				returnValue = DLFileVersionServiceUtil.getFileVersion(
					fileVersionId);

			return com.liferay.document.library.kernel.model.DLFileVersionSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.document.library.kernel.model.DLFileVersionSoap[]
			getFileVersions(long fileEntryId, int status)
		throws RemoteException {

		try {
			java.util.List
				<com.liferay.document.library.kernel.model.DLFileVersion>
					returnValue = DLFileVersionServiceUtil.getFileVersions(
						fileEntryId, status);

			return com.liferay.document.library.kernel.model.DLFileVersionSoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getFileVersionsCount(long fileEntryId, int status)
		throws RemoteException {

		try {
			int returnValue = DLFileVersionServiceUtil.getFileVersionsCount(
				fileEntryId, status);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.document.library.kernel.model.DLFileVersionSoap
			getLatestFileVersion(long fileEntryId)
		throws RemoteException {

		try {
			com.liferay.document.library.kernel.model.DLFileVersion
				returnValue = DLFileVersionServiceUtil.getLatestFileVersion(
					fileEntryId);

			return com.liferay.document.library.kernel.model.DLFileVersionSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.document.library.kernel.model.DLFileVersionSoap
			getLatestFileVersion(long fileEntryId, boolean excludeWorkingCopy)
		throws RemoteException {

		try {
			com.liferay.document.library.kernel.model.DLFileVersion
				returnValue = DLFileVersionServiceUtil.getLatestFileVersion(
					fileEntryId, excludeWorkingCopy);

			return com.liferay.document.library.kernel.model.DLFileVersionSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		DLFileVersionServiceSoap.class);

}