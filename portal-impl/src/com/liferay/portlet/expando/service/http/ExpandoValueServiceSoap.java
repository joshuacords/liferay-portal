/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.expando.service.http;

import com.liferay.expando.kernel.service.ExpandoValueServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>ExpandoValueServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.expando.kernel.model.ExpandoValueSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.expando.kernel.model.ExpandoValue</code>, that is translated to a
 * <code>com.liferay.expando.kernel.model.ExpandoValueSoap</code>. Methods that SOAP
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
 * @see ExpandoValueServiceHttp
 * @generated
 */
public class ExpandoValueServiceSoap {

	public static com.liferay.expando.kernel.model.ExpandoValueSoap addValue(
			long companyId, String className, String tableName,
			String columnName, long classPK, Object data)
		throws RemoteException {

		try {
			com.liferay.expando.kernel.model.ExpandoValue returnValue =
				ExpandoValueServiceUtil.addValue(
					companyId, className, tableName, columnName, classPK, data);

			return com.liferay.expando.kernel.model.ExpandoValueSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.expando.kernel.model.ExpandoValueSoap addValue(
			long companyId, String className, String tableName,
			String columnName, long classPK, String data)
		throws RemoteException {

		try {
			com.liferay.expando.kernel.model.ExpandoValue returnValue =
				ExpandoValueServiceUtil.addValue(
					companyId, className, tableName, columnName, classPK, data);

			return com.liferay.expando.kernel.model.ExpandoValueSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static String getJSONData(
			long companyId, String className, String tableName,
			String columnName, long classPK)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				ExpandoValueServiceUtil.getJSONData(
					companyId, className, tableName, columnName, classPK);

			return returnValue.toString();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ExpandoValueServiceSoap.class);

}