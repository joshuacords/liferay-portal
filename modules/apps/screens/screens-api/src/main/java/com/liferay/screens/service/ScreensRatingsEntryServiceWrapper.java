/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.screens.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ScreensRatingsEntryService}.
 *
 * @author José Manuel Navarro
 * @see ScreensRatingsEntryService
 * @generated
 */
public class ScreensRatingsEntryServiceWrapper
	implements ScreensRatingsEntryService,
			   ServiceWrapper<ScreensRatingsEntryService> {

	public ScreensRatingsEntryServiceWrapper(
		ScreensRatingsEntryService screensRatingsEntryService) {

		_screensRatingsEntryService = screensRatingsEntryService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject deleteRatingsEntry(
			long classPK, String className, int ratingsLength)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _screensRatingsEntryService.deleteRatingsEntry(
			classPK, className, ratingsLength);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _screensRatingsEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getRatingsEntries(
			long assetEntryId, int ratingsLength)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _screensRatingsEntryService.getRatingsEntries(
			assetEntryId, ratingsLength);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getRatingsEntries(
			long classPK, String className, int ratingsLength)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _screensRatingsEntryService.getRatingsEntries(
			classPK, className, ratingsLength);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject updateRatingsEntry(
			long classPK, String className, double score, int ratingsLength)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _screensRatingsEntryService.updateRatingsEntry(
			classPK, className, score, ratingsLength);
	}

	@Override
	public ScreensRatingsEntryService getWrappedService() {
		return _screensRatingsEntryService;
	}

	@Override
	public void setWrappedService(
		ScreensRatingsEntryService screensRatingsEntryService) {

		_screensRatingsEntryService = screensRatingsEntryService;
	}

	private ScreensRatingsEntryService _screensRatingsEntryService;

}