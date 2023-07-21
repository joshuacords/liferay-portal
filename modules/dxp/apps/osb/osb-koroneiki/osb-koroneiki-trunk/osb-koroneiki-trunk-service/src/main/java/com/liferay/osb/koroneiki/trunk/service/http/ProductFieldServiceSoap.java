/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service.http;

import com.liferay.osb.koroneiki.trunk.service.ProductFieldServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>ProductFieldServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.osb.koroneiki.trunk.model.ProductFieldSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.osb.koroneiki.trunk.model.ProductField</code>, that is translated to a
 * <code>com.liferay.osb.koroneiki.trunk.model.ProductFieldSoap</code>. Methods that SOAP
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
 * @see ProductFieldServiceHttp
 * @generated
 */
public class ProductFieldServiceSoap {

	public static com.liferay.osb.koroneiki.trunk.model.ProductFieldSoap
			addProductField(
				long classNameId, long classPK, String name, String value)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductField returnValue =
				ProductFieldServiceUtil.addProductField(
					classNameId, classPK, name, value);

			return com.liferay.osb.koroneiki.trunk.model.ProductFieldSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductFieldSoap
			deleteProductField(long productFieldId)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductField returnValue =
				ProductFieldServiceUtil.deleteProductField(productFieldId);

			return com.liferay.osb.koroneiki.trunk.model.ProductFieldSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductFieldSoap
			updateProductField(long productFieldId, String value)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductField returnValue =
				ProductFieldServiceUtil.updateProductField(
					productFieldId, value);

			return com.liferay.osb.koroneiki.trunk.model.ProductFieldSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ProductFieldServiceSoap.class);

}