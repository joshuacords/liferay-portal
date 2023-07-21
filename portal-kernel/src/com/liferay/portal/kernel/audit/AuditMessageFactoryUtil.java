/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.audit;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

import java.util.Date;

/**
 * @author Amos Fong
 */
public class AuditMessageFactoryUtil {

	public static AuditMessageFactory getAuditMessageFactory() {
		return _auditMessageFactory;
	}

	public AuditMessage getAuditMessage(String message) throws JSONException {
		return getAuditMessageFactory().getAuditMessage(message);
	}

	public AuditMessage getAuditMessage(
		String eventType, long companyId, long userId, String userName) {

		return getAuditMessageFactory().getAuditMessage(
			eventType, companyId, userId, userName);
	}

	public AuditMessage getAuditMessage(
		String eventType, long companyId, long userId, String userName,
		String className, String classPK) {

		return getAuditMessageFactory().getAuditMessage(
			eventType, companyId, userId, userName, className, classPK);
	}

	public AuditMessage getAuditMessage(
		String eventType, long companyId, long userId, String userName,
		String className, String classPK, String message) {

		return getAuditMessageFactory().getAuditMessage(
			eventType, companyId, userId, userName, className, classPK,
			message);
	}

	public AuditMessage getAuditMessage(
		String eventType, long companyId, long userId, String userName,
		String className, String classPK, String message, Date timestamp,
		JSONObject additionalInfo) {

		return getAuditMessageFactory().getAuditMessage(
			eventType, companyId, userId, userName, className, classPK, message,
			timestamp, additionalInfo);
	}

	public AuditMessage getAuditMessage(
		String eventType, long companyId, long userId, String userName,
		String className, String classPK, String message,
		JSONObject additionalInfo) {

		return getAuditMessageFactory().getAuditMessage(
			eventType, companyId, userId, userName, className, classPK, message,
			additionalInfo);
	}

	private static volatile AuditMessageFactory _auditMessageFactory =
		ServiceProxyFactory.newServiceTrackedInstance(
			AuditMessageFactory.class, AuditMessageFactoryUtil.class,
			"_auditMessageFactory", false);

}