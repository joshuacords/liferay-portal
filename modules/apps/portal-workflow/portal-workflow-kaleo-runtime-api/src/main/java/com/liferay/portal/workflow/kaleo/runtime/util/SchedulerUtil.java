/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.runtime.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.workflow.kaleo.runtime.constants.KaleoRuntimeDestinationNames;

/**
 * @author Michael C. Han
 */
public class SchedulerUtil {

	public static final String getGroupName(long kaleoTimerInstanceTokenId) {
		return KaleoRuntimeDestinationNames.WORKFLOW_TIMER.concat(
			StringPool.SLASH
		).concat(
			String.valueOf(kaleoTimerInstanceTokenId)
		);
	}

}