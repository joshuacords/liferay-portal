/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.model.impl;

import com.liferay.segments.constants.SegmentsExperimentConstants;
import com.liferay.segments.service.SegmentsExperimentLocalServiceUtil;

/**
 * @author Eduardo García
 */
public class SegmentsExperienceImpl extends SegmentsExperienceBaseImpl {

	public SegmentsExperienceImpl() {
	}

	public boolean hasSegmentsExperiment() {
		return SegmentsExperimentLocalServiceUtil.hasSegmentsExperiment(
			getSegmentsExperienceId(), getClassNameId(), getClassPK(),
			SegmentsExperimentConstants.Status.getLockedStatusValues());
	}

}