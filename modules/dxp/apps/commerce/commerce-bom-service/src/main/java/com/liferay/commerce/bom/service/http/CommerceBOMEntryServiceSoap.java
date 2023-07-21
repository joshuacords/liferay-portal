/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.bom.service.http;

import com.liferay.commerce.bom.service.CommerceBOMEntryServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>CommerceBOMEntryServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.commerce.bom.model.CommerceBOMEntrySoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.commerce.bom.model.CommerceBOMEntry</code>, that is translated to a
 * <code>com.liferay.commerce.bom.model.CommerceBOMEntrySoap</code>. Methods that SOAP
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
 * @see CommerceBOMEntryServiceHttp
 * @generated
 */
public class CommerceBOMEntryServiceSoap {

	public static com.liferay.commerce.bom.model.CommerceBOMEntrySoap
			addCommerceBOMEntry(
				long userId, int number, String cpInstanceUuid, long cProductId,
				long commerceBOMDefinitionId, double positionX,
				double positionY, double radius)
		throws RemoteException {

		try {
			com.liferay.commerce.bom.model.CommerceBOMEntry returnValue =
				CommerceBOMEntryServiceUtil.addCommerceBOMEntry(
					userId, number, cpInstanceUuid, cProductId,
					commerceBOMDefinitionId, positionX, positionY, radius);

			return com.liferay.commerce.bom.model.CommerceBOMEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void deleteCommerceBOMEntry(long commerceBOMEntryId)
		throws RemoteException {

		try {
			CommerceBOMEntryServiceUtil.deleteCommerceBOMEntry(
				commerceBOMEntryId);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.commerce.bom.model.CommerceBOMEntrySoap[]
			getCommerceBOMEntries(
				long commerceBOMDefinitionId, int start, int end)
		throws RemoteException {

		try {
			java.util.List<com.liferay.commerce.bom.model.CommerceBOMEntry>
				returnValue = CommerceBOMEntryServiceUtil.getCommerceBOMEntries(
					commerceBOMDefinitionId, start, end);

			return com.liferay.commerce.bom.model.CommerceBOMEntrySoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static int getCommerceBOMEntriesCount(long commerceBOMDefinitionId)
		throws RemoteException {

		try {
			int returnValue =
				CommerceBOMEntryServiceUtil.getCommerceBOMEntriesCount(
					commerceBOMDefinitionId);

			return returnValue;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.commerce.bom.model.CommerceBOMEntrySoap
			getCommerceBOMEntry(long commerceBOMEntryId)
		throws RemoteException {

		try {
			com.liferay.commerce.bom.model.CommerceBOMEntry returnValue =
				CommerceBOMEntryServiceUtil.getCommerceBOMEntry(
					commerceBOMEntryId);

			return com.liferay.commerce.bom.model.CommerceBOMEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.commerce.bom.model.CommerceBOMEntrySoap
			updateCommerceBOMEntry(
				long commerceBOMEntryId, int number, String cpInstanceUuid,
				long cProductId, double positionX, double positionY,
				double radius)
		throws RemoteException {

		try {
			com.liferay.commerce.bom.model.CommerceBOMEntry returnValue =
				CommerceBOMEntryServiceUtil.updateCommerceBOMEntry(
					commerceBOMEntryId, number, cpInstanceUuid, cProductId,
					positionX, positionY, radius);

			return com.liferay.commerce.bom.model.CommerceBOMEntrySoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommerceBOMEntryServiceSoap.class);

}