/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.segments.util;

import com.liferay.portal.kernel.portlet.PortletIdCodec;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.segments.constants.SegmentsExperienceConstants;

/**
 * @author Pavel Savinov
 */
public class SegmentsExperiencePortletUtil {

	public static String decodePortletName(String portletId) {
		int index = portletId.indexOf(_SEGMENTS_EXPERIENCE_SEPARATOR);

		if (index == -1) {
			return PortletIdCodec.decodePortletName(portletId);
		}

		return PortletIdCodec.decodePortletName(portletId.substring(0, index));
	}

	public static long getSegmentsExperienceId(String portletId) {
		int index = portletId.indexOf(_SEGMENTS_EXPERIENCE_SEPARATOR);

		if (index == -1) {
			return 0L;
		}

		return GetterUtil.getLong(
			portletId.substring(
				index + _SEGMENTS_EXPERIENCE_SEPARATOR.length()),
			SegmentsExperienceConstants.ID_DEFAULT);
	}

	public static String setSegmentsExperienceId(
		String instanceId, long segmentsExperienceId) {

		if (segmentsExperienceId == SegmentsExperienceConstants.ID_DEFAULT) {
			return instanceId;
		}

		int index = instanceId.indexOf(_SEGMENTS_EXPERIENCE_SEPARATOR);

		if (index == -1) {
			return instanceId + _SEGMENTS_EXPERIENCE_SEPARATOR +
				segmentsExperienceId;
		}

		return instanceId.substring(0, index) + _SEGMENTS_EXPERIENCE_SEPARATOR +
			segmentsExperienceId;
	}

	private static final String _SEGMENTS_EXPERIENCE_SEPARATOR =
		"SEGMENTSEXPERIENCE";

}