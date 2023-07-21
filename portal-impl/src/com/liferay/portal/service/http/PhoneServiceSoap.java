/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.PhoneServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>PhoneServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.portal.kernel.model.PhoneSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.portal.kernel.model.Phone</code>, that is translated to a
 * <code>com.liferay.portal.kernel.model.PhoneSoap</code>. Methods that SOAP
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
 * @see PhoneServiceHttp
 * @generated
 */
public class PhoneServiceSoap {

	public static com.liferay.portal.kernel.model.PhoneSoap addPhone(
			String className, long classPK, String number, String extension,
			long typeId, boolean primary,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.model.Phone returnValue =
				PhoneServiceUtil.addPhone(
					className, classPK, number, extension, typeId, primary,
					serviceContext);

			return com.liferay.portal.kernel.model.PhoneSoap.toSoapModel(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void deletePhone(long phoneId) throws RemoteException {
		try {
			PhoneServiceUtil.deletePhone(phoneId);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.PhoneSoap getPhone(
			long phoneId)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.model.Phone returnValue =
				PhoneServiceUtil.getPhone(phoneId);

			return com.liferay.portal.kernel.model.PhoneSoap.toSoapModel(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.PhoneSoap[] getPhones(
			String className, long classPK)
		throws RemoteException {

		try {
			java.util.List<com.liferay.portal.kernel.model.Phone> returnValue =
				PhoneServiceUtil.getPhones(className, classPK);

			return com.liferay.portal.kernel.model.PhoneSoap.toSoapModels(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.PhoneSoap updatePhone(
			long phoneId, String number, String extension, long typeId,
			boolean primary)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.model.Phone returnValue =
				PhoneServiceUtil.updatePhone(
					phoneId, number, extension, typeId, primary);

			return com.liferay.portal.kernel.model.PhoneSoap.toSoapModel(
				returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(PhoneServiceSoap.class);

}