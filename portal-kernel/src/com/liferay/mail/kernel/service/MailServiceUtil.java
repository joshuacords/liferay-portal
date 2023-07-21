/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.kernel.service;

import com.liferay.mail.kernel.model.Filter;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

import javax.mail.Session;

/**
 * @author Brian Wing Shun Chan
 */
public class MailServiceUtil {

	public static void addForward(
		long companyId, long userId, List<Filter> filters,
		List<String> emailAddresses, boolean leaveCopy) {

		getService().addForward(
			companyId, userId, filters, emailAddresses, leaveCopy);
	}

	public static void addUser(
		long companyId, long userId, String password, String firstName,
		String middleName, String lastName, String emailAddress) {

		getService().addUser(
			companyId, userId, password, firstName, middleName, lastName,
			emailAddress);
	}

	public static void addVacationMessage(
		long companyId, long userId, String emailAddress,
		String vacationMessage) {

		getService().addVacationMessage(
			companyId, userId, emailAddress, vacationMessage);
	}

	public static void clearSession() {
		getService().clearSession();
	}

	public static void deleteEmailAddress(long companyId, long userId) {
		getService().deleteEmailAddress(companyId, userId);
	}

	public static void deleteUser(long companyId, long userId) {
		getService().deleteUser(companyId, userId);
	}

	public static MailService getService() {
		if (_service == null) {
			_service = (MailService)PortalBeanLocatorUtil.locate(
				MailService.class.getName());

			ReferenceRegistry.registerReference(
				MailServiceUtil.class, "_service");
		}

		return _service;
	}

	public static Session getSession() {
		return getService().getSession();
	}

	public static void sendEmail(MailMessage mailMessage) {
		getService().sendEmail(mailMessage);
	}

	public static void updateBlocked(
		long companyId, long userId, List<String> blocked) {

		getService().updateBlocked(companyId, userId, blocked);
	}

	public static void updateEmailAddress(
		long companyId, long userId, String emailAddress) {

		getService().updateEmailAddress(companyId, userId, emailAddress);
	}

	public static void updatePassword(
		long companyId, long userId, String password) {

		getService().updatePassword(companyId, userId, password);
	}

	public void setService(MailService service) {
		_service = service;

		ReferenceRegistry.registerReference(MailServiceUtil.class, "_service");
	}

	private static MailService _service;

}