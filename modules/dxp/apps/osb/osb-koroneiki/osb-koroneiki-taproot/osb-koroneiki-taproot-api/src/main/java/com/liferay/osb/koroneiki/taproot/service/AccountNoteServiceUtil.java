/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.osb.koroneiki.taproot.model.AccountNote;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for AccountNote. This utility wraps
 * <code>com.liferay.osb.koroneiki.taproot.service.impl.AccountNoteServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AccountNoteService
 * @generated
 */
public class AccountNoteServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.taproot.service.impl.AccountNoteServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static AccountNote addAccountNote(
			String creatorUID, String creatorName, long accountId, String type,
			int priority, String content, String format, String status)
		throws PortalException {

		return getService().addAccountNote(
			creatorUID, creatorName, accountId, type, priority, content, format,
			status);
	}

	public static AccountNote deleteAccountNote(String accountNoteKey)
		throws PortalException {

		return getService().deleteAccountNote(accountNoteKey);
	}

	public static AccountNote getAccountNote(String accountNoteKey)
		throws PortalException {

		return getService().getAccountNote(accountNoteKey);
	}

	public static List<AccountNote> getAccountNotes(
			long accountId, String[] types, int[] priorities, String[] statuses,
			int start, int end)
		throws PortalException {

		return getService().getAccountNotes(
			accountId, types, priorities, statuses, start, end);
	}

	public static int getAccountNotesCount(
			long accountId, String[] types, int[] priorities, String[] statuses)
		throws PortalException {

		return getService().getAccountNotesCount(
			accountId, types, priorities, statuses);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static AccountNote updateAccountNote(
			long accountNoteId, String modifierUID, String modifierName,
			int priority, String content, String format, String status)
		throws PortalException {

		return getService().updateAccountNote(
			accountNoteId, modifierUID, modifierName, priority, content, format,
			status);
	}

	public static AccountNoteService getService() {
		return _service;
	}

	public static void setService(AccountNoteService service) {
		_service = service;
	}

	private static volatile AccountNoteService _service;

}