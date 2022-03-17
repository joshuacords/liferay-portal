/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.license.service.http;

import com.liferay.osb.provisioning.license.service.LicenseKeyServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>LicenseKeyServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.osb.provisioning.license.model.LicenseKeySoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.osb.provisioning.license.model.LicenseKey</code>, that is translated to a
 * <code>com.liferay.osb.provisioning.license.model.LicenseKeySoap</code>. Methods that SOAP
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
 * @see LicenseKeyServiceHttp
 * @generated
 */
public class LicenseKeyServiceSoap {

	public static com.liferay.osb.provisioning.license.model.LicenseKeySoap
			addLicenseKey(
				String userName, String userUuid, long licenseEntryId,
				String productKey, String accountKey, String productPurchaseKey,
				String accountName, String productVersion, long clusterId,
				String name, String owner, int maxClusterNodes, int maxServers,
				int maxHttpSessions, int maxConcurrentUsers, int maxUsers,
				String sizing, String description, String[] hostNames,
				String[] ipAddresses, String[] macAddresses,
				java.util.Date startDate, java.util.Date expirationDate,
				boolean complimentary, boolean active)
		throws RemoteException {

		try {
			com.liferay.osb.provisioning.license.model.LicenseKey returnValue =
				LicenseKeyServiceUtil.addLicenseKey(
					userName, userUuid, licenseEntryId, productKey, accountKey,
					productPurchaseKey, accountName, productVersion, clusterId,
					name, owner, maxClusterNodes, maxServers, maxHttpSessions,
					maxConcurrentUsers, maxUsers, sizing, description,
					hostNames, ipAddresses, macAddresses, startDate,
					expirationDate, complimentary, active);

			return com.liferay.osb.provisioning.license.model.LicenseKeySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.provisioning.license.model.LicenseKeySoap
			addLicenseKey(
				String userUuid, String assetReceiptLicenseUuid,
				String licenseEntryType, String productEntryName,
				String productId, int productVersion, String owner,
				long maxUsers, String description, String hostName,
				String ipAddresses, String macAddresses, String serverId,
				java.util.Date startDate, java.util.Date expirationDate)
		throws RemoteException {

		try {
			com.liferay.osb.provisioning.license.model.LicenseKey returnValue =
				LicenseKeyServiceUtil.addLicenseKey(
					userUuid, assetReceiptLicenseUuid, licenseEntryType,
					productEntryName, productId, productVersion, owner,
					maxUsers, description, hostName, ipAddresses, macAddresses,
					serverId, startDate, expirationDate);

			return com.liferay.osb.provisioning.license.model.LicenseKeySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.provisioning.license.model.LicenseKeySoap
			extendLicenseKey(
				long licenseKeyId, String productPurchaseKey,
				java.util.Date startDate, java.util.Date expirationDate)
		throws RemoteException {

		try {
			com.liferay.osb.provisioning.license.model.LicenseKey returnValue =
				LicenseKeyServiceUtil.extendLicenseKey(
					licenseKeyId, productPurchaseKey, startDate,
					expirationDate);

			return com.liferay.osb.provisioning.license.model.LicenseKeySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.provisioning.license.model.LicenseKeySoap[]
			getAssetReceiptLicenseLicenseKeys(
				String assetReceiptLicenseUuid, boolean complimentary,
				boolean active)
		throws RemoteException {

		try {
			java.util.List
				<com.liferay.osb.provisioning.license.model.LicenseKey>
					returnValue =
						LicenseKeyServiceUtil.getAssetReceiptLicenseLicenseKeys(
							assetReceiptLicenseUuid, complimentary, active);

			return com.liferay.osb.provisioning.license.model.LicenseKeySoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getAssetReceiptLicenseLicenseKeysCount(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active)
		throws RemoteException {

		try {
			int returnValue =
				LicenseKeyServiceUtil.getAssetReceiptLicenseLicenseKeysCount(
					assetReceiptLicenseUuid, complimentary, active);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.provisioning.license.model.LicenseKeySoap
			getLicenseKey(long licenseKeyId)
		throws RemoteException {

		try {
			com.liferay.osb.provisioning.license.model.LicenseKey returnValue =
				LicenseKeyServiceUtil.getLicenseKey(licenseKeyId);

			return com.liferay.osb.provisioning.license.model.LicenseKeySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.provisioning.license.model.LicenseKeySoap
			getLicenseKey(String uuid)
		throws RemoteException {

		try {
			com.liferay.osb.provisioning.license.model.LicenseKey returnValue =
				LicenseKeyServiceUtil.getLicenseKey(uuid);

			return com.liferay.osb.provisioning.license.model.LicenseKeySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.provisioning.license.model.LicenseKeySoap[]
			getLicenseKeys(String productId, String serverId)
		throws RemoteException {

		try {
			java.util.List
				<com.liferay.osb.provisioning.license.model.LicenseKey>
					returnValue = LicenseKeyServiceUtil.getLicenseKeys(
						productId, serverId);

			return com.liferay.osb.provisioning.license.model.LicenseKeySoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.provisioning.license.model.LicenseKeySoap[]
			getLicenseKeys(
				String assetReceiptLicenseUuid, String productId,
				String serverId, boolean active, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {

		try {
			java.util.List
				<com.liferay.osb.provisioning.license.model.LicenseKey>
					returnValue = LicenseKeyServiceUtil.getLicenseKeys(
						assetReceiptLicenseUuid, productId, serverId, active,
						start, end, obc);

			return com.liferay.osb.provisioning.license.model.LicenseKeySoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.provisioning.license.model.LicenseKeySoap[]
			getLicenseKeysByName(
				String productName, String serverId, boolean active, int start,
				int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {

		try {
			java.util.List
				<com.liferay.osb.provisioning.license.model.LicenseKey>
					returnValue = LicenseKeyServiceUtil.getLicenseKeysByName(
						productName, serverId, active, start, end, obc);

			return com.liferay.osb.provisioning.license.model.LicenseKeySoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static boolean isActive(
			String serverId, String productId, String key)
		throws RemoteException {

		try {
			boolean returnValue = LicenseKeyServiceUtil.isActive(
				serverId, productId, key);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.provisioning.license.model.LicenseKeySoap
			replaceLicenseKey(
				long licenseKeyId, java.util.Date startDate,
				java.util.Date expirationDate)
		throws RemoteException {

		try {
			com.liferay.osb.provisioning.license.model.LicenseKey returnValue =
				LicenseKeyServiceUtil.replaceLicenseKey(
					licenseKeyId, startDate, expirationDate);

			return com.liferay.osb.provisioning.license.model.LicenseKeySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.provisioning.license.model.LicenseKeySoap
			replaceLicenseKey(
				String uuid, java.util.Date startDate,
				java.util.Date expirationDate)
		throws RemoteException {

		try {
			com.liferay.osb.provisioning.license.model.LicenseKey returnValue =
				LicenseKeyServiceUtil.replaceLicenseKey(
					uuid, startDate, expirationDate);

			return com.liferay.osb.provisioning.license.model.LicenseKeySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.osb.provisioning.license.model.LicenseKeySoap
			updateLicenseKey(
				long licenseKeyId, String productPurchaseKey,
				boolean complimentary, boolean active)
		throws RemoteException {

		try {
			com.liferay.osb.provisioning.license.model.LicenseKey returnValue =
				LicenseKeyServiceUtil.updateLicenseKey(
					licenseKeyId, productPurchaseKey, complimentary, active);

			return com.liferay.osb.provisioning.license.model.LicenseKeySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void updateLicenseKey(
			String userUuid, String uuid, boolean active)
		throws RemoteException {

		try {
			LicenseKeyServiceUtil.updateLicenseKey(userUuid, uuid, active);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void updateLicenseKeys(
			String assetReceiptLicenseUuid, boolean active)
		throws RemoteException {

		try {
			LicenseKeyServiceUtil.updateLicenseKeys(
				assetReceiptLicenseUuid, active);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		LicenseKeyServiceSoap.class);

}