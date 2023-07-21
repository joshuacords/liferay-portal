/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.taglib.internal.info.display.contributor;

import com.liferay.info.display.contributor.InfoDisplayContributorTracker;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(immediate = true, service = {})
public class InfoDisplayContributorTrackerUtil {

	public static final InfoDisplayContributorTracker
		getInfoDisplayContributorTracker() {

		return _infoDisplayContributorTracker;
	}

	@Reference(unbind = "-")
	protected void setServletContext(
		InfoDisplayContributorTracker infoDisplayContributorTracker) {

		_infoDisplayContributorTracker = infoDisplayContributorTracker;
	}

	private static InfoDisplayContributorTracker _infoDisplayContributorTracker;

}