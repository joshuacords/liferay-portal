/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service.http;

import com.liferay.osb.koroneiki.trunk.service.ProductEntryServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>ProductEntryServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.osb.koroneiki.trunk.model.ProductEntry</code>, that is translated to a
 * <code>com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap</code>. Methods that SOAP
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
 * @see ProductEntryServiceHttp
 * @generated
 */
public class ProductEntryServiceSoap {

	public static com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap
			addProductEntry(
				String name,
				com.liferay.osb.koroneiki.trunk.model.ProductFieldSoap[]
					productFields)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductEntry returnValue =
				ProductEntryServiceUtil.addProductEntry(
					name,
					com.liferay.osb.koroneiki.trunk.model.impl.
						ProductFieldModelImpl.toModels(productFields));

			return com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap
			deleteProductEntry(long productEntryId)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductEntry returnValue =
				ProductEntryServiceUtil.deleteProductEntry(productEntryId);

			return com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap
			deleteProductEntry(String productEntryKey)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductEntry returnValue =
				ProductEntryServiceUtil.deleteProductEntry(productEntryKey);

			return com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap[]
			getProductEntries(int start, int end)
		throws RemoteException {

		try {
			java.util.List<com.liferay.osb.koroneiki.trunk.model.ProductEntry>
				returnValue = ProductEntryServiceUtil.getProductEntries(
					start, end);

			return com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap[]
			getProductEntries(
				String domain, String entityName, String entityId, int start,
				int end)
		throws RemoteException {

		try {
			java.util.List<com.liferay.osb.koroneiki.trunk.model.ProductEntry>
				returnValue = ProductEntryServiceUtil.getProductEntries(
					domain, entityName, entityId, start, end);

			return com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getProductEntriesCount() throws RemoteException {
		try {
			int returnValue = ProductEntryServiceUtil.getProductEntriesCount();

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getProductEntriesCount(
			String domain, String entityName, String entityId)
		throws RemoteException {

		try {
			int returnValue = ProductEntryServiceUtil.getProductEntriesCount(
				domain, entityName, entityId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap
			getProductEntry(long productEntryId)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductEntry returnValue =
				ProductEntryServiceUtil.getProductEntry(productEntryId);

			return com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap
			getProductEntry(String productEntryKey)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductEntry returnValue =
				ProductEntryServiceUtil.getProductEntry(productEntryKey);

			return com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap
			getProductEntryByName(String name)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductEntry returnValue =
				ProductEntryServiceUtil.getProductEntryByName(name);

			return com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap
			updateProductEntry(
				long productEntryId, String name,
				com.liferay.osb.koroneiki.trunk.model.ProductFieldSoap[]
					productFields)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductEntry returnValue =
				ProductEntryServiceUtil.updateProductEntry(
					productEntryId, name,
					com.liferay.osb.koroneiki.trunk.model.impl.
						ProductFieldModelImpl.toModels(productFields));

			return com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap
			updateProductEntry(
				String productEntryKey, String name,
				com.liferay.osb.koroneiki.trunk.model.ProductFieldSoap[]
					productFields)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductEntry returnValue =
				ProductEntryServiceUtil.updateProductEntry(
					productEntryKey, name,
					com.liferay.osb.koroneiki.trunk.model.impl.
						ProductFieldModelImpl.toModels(productFields));

			return com.liferay.osb.koroneiki.trunk.model.ProductEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ProductEntryServiceSoap.class);

}