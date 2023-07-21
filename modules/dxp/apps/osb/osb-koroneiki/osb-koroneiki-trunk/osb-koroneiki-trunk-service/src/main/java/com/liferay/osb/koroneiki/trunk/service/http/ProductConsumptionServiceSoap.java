/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service.http;

import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>ProductConsumptionServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.osb.koroneiki.trunk.model.ProductConsumption</code>, that is translated to a
 * <code>com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap</code>. Methods that SOAP
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
 * @see ProductConsumptionServiceHttp
 * @generated
 */
public class ProductConsumptionServiceSoap {

	public static com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap
			addProductConsumption(
				long accountId, long productEntryId, long productPurchaseId,
				java.util.Date startDate, java.util.Date endDate,
				com.liferay.osb.koroneiki.trunk.model.ProductFieldSoap[]
					productFields)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductConsumption
				returnValue =
					ProductConsumptionServiceUtil.addProductConsumption(
						accountId, productEntryId, productPurchaseId, startDate,
						endDate,
						com.liferay.osb.koroneiki.trunk.model.impl.
							ProductFieldModelImpl.toModels(productFields));

			return com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap
			addProductConsumption(
				String accountKey, String productEntryKey,
				String productPurchaseKey, java.util.Date startDate,
				java.util.Date endDate,
				com.liferay.osb.koroneiki.trunk.model.ProductFieldSoap[]
					productFields)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductConsumption
				returnValue =
					ProductConsumptionServiceUtil.addProductConsumption(
						accountKey, productEntryKey, productPurchaseKey,
						startDate, endDate,
						com.liferay.osb.koroneiki.trunk.model.impl.
							ProductFieldModelImpl.toModels(productFields));

			return com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap
			deleteProductConsumption(long productConsumptionId)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductConsumption
				returnValue =
					ProductConsumptionServiceUtil.deleteProductConsumption(
						productConsumptionId);

			return com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap
			deleteProductConsumption(long accountId, long productEntryId)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductConsumption
				returnValue =
					ProductConsumptionServiceUtil.deleteProductConsumption(
						accountId, productEntryId);

			return com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap
			deleteProductConsumption(String productConsumptionKey)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductConsumption
				returnValue =
					ProductConsumptionServiceUtil.deleteProductConsumption(
						productConsumptionKey);

			return com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap[]
			getAccountProductConsumptions(long accountId, int start, int end)
		throws RemoteException {

		try {
			java.util.List
				<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>
					returnValue =
						ProductConsumptionServiceUtil.
							getAccountProductConsumptions(
								accountId, start, end);

			return com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap[]
			getAccountProductConsumptions(String accountKey, int start, int end)
		throws RemoteException {

		try {
			java.util.List
				<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>
					returnValue =
						ProductConsumptionServiceUtil.
							getAccountProductConsumptions(
								accountKey, start, end);

			return com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getAccountProductConsumptionsCount(long accountId)
		throws RemoteException {

		try {
			int returnValue =
				ProductConsumptionServiceUtil.
					getAccountProductConsumptionsCount(accountId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getAccountProductConsumptionsCount(String accountKey)
		throws RemoteException {

		try {
			int returnValue =
				ProductConsumptionServiceUtil.
					getAccountProductConsumptionsCount(accountKey);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap[]
			getAccountProductEntryProductConsumptions(
				long accountId, long productEntryId)
		throws RemoteException {

		try {
			java.util.List
				<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>
					returnValue =
						ProductConsumptionServiceUtil.
							getAccountProductEntryProductConsumptions(
								accountId, productEntryId);

			return com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap[]
			getContactProductConsumptions(long contactId, int start, int end)
		throws RemoteException {

		try {
			java.util.List
				<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>
					returnValue =
						ProductConsumptionServiceUtil.
							getContactProductConsumptions(
								contactId, start, end);

			return com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getContactProductConsumptionsCount(long contactId)
		throws RemoteException {

		try {
			int returnValue =
				ProductConsumptionServiceUtil.
					getContactProductConsumptionsCount(contactId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap
			getProductConsumption(long productConsumptionId)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductConsumption
				returnValue =
					ProductConsumptionServiceUtil.getProductConsumption(
						productConsumptionId);

			return com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap
			getProductConsumption(String productConsumptionKey)
		throws RemoteException {

		try {
			com.liferay.osb.koroneiki.trunk.model.ProductConsumption
				returnValue =
					ProductConsumptionServiceUtil.getProductConsumption(
						productConsumptionKey);

			return com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap[]
			getProductConsumptions(
				String domain, String entityName, String entityId, int start,
				int end)
		throws RemoteException {

		try {
			java.util.List
				<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>
					returnValue =
						ProductConsumptionServiceUtil.getProductConsumptions(
							domain, entityName, entityId, start, end);

			return com.liferay.osb.koroneiki.trunk.model.ProductConsumptionSoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getProductConsumptionsCount(
			String domain, String entityName, String entityId)
		throws RemoteException {

		try {
			int returnValue =
				ProductConsumptionServiceUtil.getProductConsumptionsCount(
					domain, entityName, entityId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ProductConsumptionServiceSoap.class);

}