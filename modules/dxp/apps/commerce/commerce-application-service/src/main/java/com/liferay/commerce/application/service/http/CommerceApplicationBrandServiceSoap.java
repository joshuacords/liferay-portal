/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.application.service.http;

import com.liferay.commerce.application.service.CommerceApplicationBrandServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>CommerceApplicationBrandServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.commerce.application.model.CommerceApplicationBrandSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.commerce.application.model.CommerceApplicationBrand</code>, that is translated to a
 * <code>com.liferay.commerce.application.model.CommerceApplicationBrandSoap</code>. Methods that SOAP
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
 * @author Luca Pellizzon
 * @see CommerceApplicationBrandServiceHttp
 * @generated
 */
public class CommerceApplicationBrandServiceSoap {

	public static
		com.liferay.commerce.application.model.CommerceApplicationBrandSoap
				addCommerceApplicationBrand(
					long userId, String name, boolean logo, byte[] logoBytes)
			throws RemoteException {

		try {
			com.liferay.commerce.application.model.CommerceApplicationBrand
				returnValue =
					CommerceApplicationBrandServiceUtil.
						addCommerceApplicationBrand(
							userId, name, logo, logoBytes);

			return com.liferay.commerce.application.model.
				CommerceApplicationBrandSoap.toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void deleteCommerceApplicationBrand(
			long commerceApplicationBrandId)
		throws RemoteException {

		try {
			CommerceApplicationBrandServiceUtil.deleteCommerceApplicationBrand(
				commerceApplicationBrandId);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static
		com.liferay.commerce.application.model.CommerceApplicationBrandSoap
				getCommerceApplicationBrand(long commerceApplicationBrandId)
			throws RemoteException {

		try {
			com.liferay.commerce.application.model.CommerceApplicationBrand
				returnValue =
					CommerceApplicationBrandServiceUtil.
						getCommerceApplicationBrand(commerceApplicationBrandId);

			return com.liferay.commerce.application.model.
				CommerceApplicationBrandSoap.toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static
		com.liferay.commerce.application.model.CommerceApplicationBrandSoap[]
				getCommerceApplicationBrands(long companyId, int start, int end)
			throws RemoteException {

		try {
			java.util.List
				<com.liferay.commerce.application.model.
					CommerceApplicationBrand> returnValue =
						CommerceApplicationBrandServiceUtil.
							getCommerceApplicationBrands(companyId, start, end);

			return com.liferay.commerce.application.model.
				CommerceApplicationBrandSoap.toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getCommerceApplicationBrandsCount(long companyId)
		throws RemoteException {

		try {
			int returnValue =
				CommerceApplicationBrandServiceUtil.
					getCommerceApplicationBrandsCount(companyId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static
		com.liferay.commerce.application.model.CommerceApplicationBrandSoap
				updateCommerceApplicationBrand(
					long commerceApplicationBrandId, String name, boolean logo,
					byte[] logoBytes)
			throws RemoteException {

		try {
			com.liferay.commerce.application.model.CommerceApplicationBrand
				returnValue =
					CommerceApplicationBrandServiceUtil.
						updateCommerceApplicationBrand(
							commerceApplicationBrandId, name, logo, logoBytes);

			return com.liferay.commerce.application.model.
				CommerceApplicationBrandSoap.toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommerceApplicationBrandServiceSoap.class);

}