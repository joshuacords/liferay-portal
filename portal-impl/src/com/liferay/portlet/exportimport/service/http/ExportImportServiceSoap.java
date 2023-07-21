/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.exportimport.service.http;

import com.liferay.exportimport.kernel.service.ExportImportServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>ExportImportServiceUtil</code> service
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
 * @see ExportImportServiceHttp
 * @generated
 */
public class ExportImportServiceSoap {

	public static long exportLayoutsAsFileInBackground(
			com.liferay.exportimport.kernel.model.ExportImportConfigurationSoap
				exportImportConfiguration)
		throws RemoteException {

		try {
			long returnValue =
				ExportImportServiceUtil.exportLayoutsAsFileInBackground(
					com.liferay.portlet.exportimport.model.impl.
						ExportImportConfigurationModelImpl.toModel(
							exportImportConfiguration));

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static long exportLayoutsAsFileInBackground(
			long exportImportConfigurationId)
		throws RemoteException {

		try {
			long returnValue =
				ExportImportServiceUtil.exportLayoutsAsFileInBackground(
					exportImportConfigurationId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static long exportPortletInfoAsFileInBackground(
			com.liferay.exportimport.kernel.model.ExportImportConfigurationSoap
				exportImportConfiguration)
		throws RemoteException {

		try {
			long returnValue =
				ExportImportServiceUtil.exportPortletInfoAsFileInBackground(
					com.liferay.portlet.exportimport.model.impl.
						ExportImportConfigurationModelImpl.toModel(
							exportImportConfiguration));

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ExportImportServiceSoap.class);

}