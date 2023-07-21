/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.screens.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.screens.service.ScreensAssetEntryServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>ScreensAssetEntryServiceUtil</code> service
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
 * @author José Manuel Navarro
 * @see ScreensAssetEntryServiceHttp
 * @generated
 */
public class ScreensAssetEntryServiceSoap {

	public static String getAssetEntries(
			com.liferay.asset.kernel.service.persistence.AssetEntryQuery
				assetEntryQuery,
			String locale)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONArray returnValue =
				ScreensAssetEntryServiceUtil.getAssetEntries(
					assetEntryQuery, LocaleUtil.fromLanguageId(locale));

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String getAssetEntries(
			long companyId, long groupId, String portletItemName, String locale,
			int max)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONArray returnValue =
				ScreensAssetEntryServiceUtil.getAssetEntries(
					companyId, groupId, portletItemName,
					LocaleUtil.fromLanguageId(locale), max);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String getAssetEntry(long entryId, String locale)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				ScreensAssetEntryServiceUtil.getAssetEntry(
					entryId, LocaleUtil.fromLanguageId(locale));

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String getAssetEntry(
			String className, long classPK, String locale)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				ScreensAssetEntryServiceUtil.getAssetEntry(
					className, classPK, LocaleUtil.fromLanguageId(locale));

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ScreensAssetEntryServiceSoap.class);

}