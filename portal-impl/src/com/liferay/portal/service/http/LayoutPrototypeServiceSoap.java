/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.LayoutPrototypeServiceUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;

import java.rmi.RemoteException;

import java.util.Locale;
import java.util.Map;

/**
 * Provides the SOAP utility for the
 * <code>LayoutPrototypeServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.portal.kernel.model.LayoutPrototypeSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.portal.kernel.model.LayoutPrototype</code>, that is translated to a
 * <code>com.liferay.portal.kernel.model.LayoutPrototypeSoap</code>. Methods that SOAP
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
 * @see LayoutPrototypeServiceHttp
 * @generated
 */
public class LayoutPrototypeServiceSoap {

	public static com.liferay.portal.kernel.model.LayoutPrototypeSoap
			addLayoutPrototype(
				String[] nameMapLanguageIds, String[] nameMapValues,
				String[] descriptionMapLanguageIds,
				String[] descriptionMapValues, boolean active,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
				nameMapLanguageIds, nameMapValues);
			Map<Locale, String> descriptionMap =
				LocalizationUtil.getLocalizationMap(
					descriptionMapLanguageIds, descriptionMapValues);

			com.liferay.portal.kernel.model.LayoutPrototype returnValue =
				LayoutPrototypeServiceUtil.addLayoutPrototype(
					nameMap, descriptionMap, active, serviceContext);

			return com.liferay.portal.kernel.model.LayoutPrototypeSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void deleteLayoutPrototype(long layoutPrototypeId)
		throws RemoteException {

		try {
			LayoutPrototypeServiceUtil.deleteLayoutPrototype(layoutPrototypeId);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.LayoutPrototypeSoap
			fetchLayoutPrototype(long layoutPrototypeId)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.model.LayoutPrototype returnValue =
				LayoutPrototypeServiceUtil.fetchLayoutPrototype(
					layoutPrototypeId);

			return com.liferay.portal.kernel.model.LayoutPrototypeSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.LayoutPrototypeSoap
			getLayoutPrototype(long layoutPrototypeId)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.model.LayoutPrototype returnValue =
				LayoutPrototypeServiceUtil.getLayoutPrototype(
					layoutPrototypeId);

			return com.liferay.portal.kernel.model.LayoutPrototypeSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.LayoutPrototypeSoap[] search(
			long companyId, Boolean active,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.portal.kernel.model.LayoutPrototype> obc)
		throws RemoteException {

		try {
			java.util.List<com.liferay.portal.kernel.model.LayoutPrototype>
				returnValue = LayoutPrototypeServiceUtil.search(
					companyId, active, obc);

			return com.liferay.portal.kernel.model.LayoutPrototypeSoap.
				toSoapModels(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.LayoutPrototypeSoap
			updateLayoutPrototype(
				long layoutPrototypeId, String[] nameMapLanguageIds,
				String[] nameMapValues, String[] descriptionMapLanguageIds,
				String[] descriptionMapValues, boolean active,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
				nameMapLanguageIds, nameMapValues);
			Map<Locale, String> descriptionMap =
				LocalizationUtil.getLocalizationMap(
					descriptionMapLanguageIds, descriptionMapValues);

			com.liferay.portal.kernel.model.LayoutPrototype returnValue =
				LayoutPrototypeServiceUtil.updateLayoutPrototype(
					layoutPrototypeId, nameMap, descriptionMap, active,
					serviceContext);

			return com.liferay.portal.kernel.model.LayoutPrototypeSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		LayoutPrototypeServiceSoap.class);

}