/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service.http;

import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>ProductPurchaseServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.osb.koroneiki.trunk.model.ProductPurchase</code>, that is translated to a
 * <code>com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap</code>. Methods that SOAP
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
 * @see ProductPurchaseServiceHttp
 * @generated
 */
public class ProductPurchaseServiceSoap {

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap
			addProductPurchase(
				long accountId, long productEntryId, java.util.Date startDate,
				java.util.Date endDate, java.util.Date originalEndDate,
				int quantity, int status,
				com.liferay.osb.koroneiki.trunk.model.ProductFieldSoap[]
					productFields)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductPurchase returnValue =
				ProductPurchaseServiceUtil.addProductPurchase(
					accountId, productEntryId, startDate, endDate,
					originalEndDate, quantity, status,
					com.liferay.osb.koroneiki.trunk.model.impl.
						ProductFieldModelImpl.toModels(productFields));

			return com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap
			addProductPurchase(
				String accountKey, String productEntryKey,
				java.util.Date startDate, java.util.Date endDate,
				java.util.Date originalEndDate, int quantity, int status,
				com.liferay.osb.koroneiki.trunk.model.ProductFieldSoap[]
					productFields)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductPurchase returnValue =
				ProductPurchaseServiceUtil.addProductPurchase(
					accountKey, productEntryKey, startDate, endDate,
					originalEndDate, quantity, status,
					com.liferay.osb.koroneiki.trunk.model.impl.
						ProductFieldModelImpl.toModels(productFields));

			return com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap
			deleteProductPurchase(long productPurchaseId)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductPurchase returnValue =
				ProductPurchaseServiceUtil.deleteProductPurchase(
					productPurchaseId);

			return com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap
			deleteProductPurchase(String productPurchaseKey)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductPurchase returnValue =
				ProductPurchaseServiceUtil.deleteProductPurchase(
					productPurchaseKey);

			return com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap[]
			getAccountProductEntryProductPurchases(
				long accountId, long productEntryId, int start, int end)
		throws RemoteException {

		try {
			java.util.List
				<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
					returnValue =
						ProductPurchaseServiceUtil.
							getAccountProductEntryProductPurchases(
								accountId, productEntryId, start, end);

			return com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap[]
			getAccountProductPurchases(long accountId, int start, int end)
		throws RemoteException {

		try {
			java.util.List
				<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
					returnValue =
						ProductPurchaseServiceUtil.getAccountProductPurchases(
							accountId, start, end);

			return com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap[]
			getAccountProductPurchases(String accountKey, int start, int end)
		throws RemoteException {

		try {
			java.util.List
				<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
					returnValue =
						ProductPurchaseServiceUtil.getAccountProductPurchases(
							accountKey, start, end);

			return com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getAccountProductPurchasesCount(long accountId)
		throws RemoteException {

		try {
			int returnValue =
				ProductPurchaseServiceUtil.getAccountProductPurchasesCount(
					accountId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getAccountProductPurchasesCount(String accountKey)
		throws RemoteException {

		try {
			int returnValue =
				ProductPurchaseServiceUtil.getAccountProductPurchasesCount(
					accountKey);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap[]
			getContactProductPurchases(long contactId, int start, int end)
		throws RemoteException {

		try {
			java.util.List
				<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
					returnValue =
						ProductPurchaseServiceUtil.getContactProductPurchases(
							contactId, start, end);

			return com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getContactProductPurchasesCount(long contactId)
		throws RemoteException {

		try {
			int returnValue =
				ProductPurchaseServiceUtil.getContactProductPurchasesCount(
					contactId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap
			getProductPurchase(long productPurchaseId)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductPurchase returnValue =
				ProductPurchaseServiceUtil.getProductPurchase(
					productPurchaseId);

			return com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap
			getProductPurchase(String productPurchaseKey)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductPurchase returnValue =
				ProductPurchaseServiceUtil.getProductPurchase(
					productPurchaseKey);

			return com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap[]
			getProductPurchases(
				String domain, String entityName, String entityId, int start,
				int end)
		throws RemoteException {

		try {
			java.util.List
				<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
					returnValue =
						ProductPurchaseServiceUtil.getProductPurchases(
							domain, entityName, entityId, start, end);

			return com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getProductPurchasesCount(
			String domain, String entityName, String entityId)
		throws RemoteException {

		try {
			int returnValue =
				ProductPurchaseServiceUtil.getProductPurchasesCount(
					domain, entityName, entityId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap
			updateProductPurchase(
				long productPurchaseId, java.util.Date startDate,
				java.util.Date endDate, java.util.Date originalEndDate,
				int quantity, int status,
				com.liferay.osb.koroneiki.trunk.model.ProductFieldSoap[]
					productFields)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductPurchase returnValue =
				ProductPurchaseServiceUtil.updateProductPurchase(
					productPurchaseId, startDate, endDate, originalEndDate,
					quantity, status,
					com.liferay.osb.koroneiki.trunk.model.impl.
						ProductFieldModelImpl.toModels(productFields));

			return com.liferay.osb.koroneiki.trunk.model.ProductPurchaseSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ProductPurchaseServiceSoap.class);

}