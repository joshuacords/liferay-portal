/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.screens.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ScreensDDLRecordService}.
 *
 * @author José Manuel Navarro
 * @see ScreensDDLRecordService
 * @generated
 */
public class ScreensDDLRecordServiceWrapper
	implements ScreensDDLRecordService,
			   ServiceWrapper<ScreensDDLRecordService> {

	public ScreensDDLRecordServiceWrapper(
		ScreensDDLRecordService screensDDLRecordService) {

		_screensDDLRecordService = screensDDLRecordService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getDDLRecord(
			long ddlRecordId, java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _screensDDLRecordService.getDDLRecord(ddlRecordId, locale);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getDDLRecords(
			long ddlRecordSetId, java.util.Locale locale, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.dynamic.data.lists.model.DDLRecord> obc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _screensDDLRecordService.getDDLRecords(
			ddlRecordSetId, locale, start, end, obc);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getDDLRecords(
			long ddlRecordSetId, long userId, java.util.Locale locale,
			int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.dynamic.data.lists.model.DDLRecord> obc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _screensDDLRecordService.getDDLRecords(
			ddlRecordSetId, userId, locale, start, end, obc);
	}

	@Override
	public int getDDLRecordsCount(long ddlRecordSetId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _screensDDLRecordService.getDDLRecordsCount(ddlRecordSetId);
	}

	@Override
	public int getDDLRecordsCount(long ddlRecordSetId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _screensDDLRecordService.getDDLRecordsCount(
			ddlRecordSetId, userId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _screensDDLRecordService.getOSGiServiceIdentifier();
	}

	@Override
	public ScreensDDLRecordService getWrappedService() {
		return _screensDDLRecordService;
	}

	@Override
	public void setWrappedService(
		ScreensDDLRecordService screensDDLRecordService) {

		_screensDDLRecordService = screensDDLRecordService;
	}

	private ScreensDDLRecordService _screensDDLRecordService;

}