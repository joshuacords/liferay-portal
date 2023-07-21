/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.page.template.model.impl;

import com.liferay.layout.page.template.model.LayoutPageTemplateStructureRel;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureRelLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.segments.constants.SegmentsExperienceConstants;

import java.util.Arrays;
import java.util.stream.LongStream;

/**
 * @author Eduardo García
 */
public class LayoutPageTemplateStructureImpl
	extends LayoutPageTemplateStructureBaseImpl {

	public LayoutPageTemplateStructureImpl() {
	}

	public String getData(long segmentsExperienceId) {
		LayoutPageTemplateStructureRel layoutPageTemplateStructureRel =
			LayoutPageTemplateStructureRelLocalServiceUtil.
				fetchLayoutPageTemplateStructureRel(
					getLayoutPageTemplateStructureId(), segmentsExperienceId);

		if (layoutPageTemplateStructureRel != null) {
			return layoutPageTemplateStructureRel.getData();
		}

		return StringPool.BLANK;
	}

	public String getData(long[] segmentsExperienceIds) throws PortalException {
		long segmentsExperienceId = _getFirstSegmentsExperienceId(
			segmentsExperienceIds);

		return getData(segmentsExperienceId);
	}

	private long _getFirstSegmentsExperienceId(long[] segmentsExperienceIds) {
		LongStream stream = Arrays.stream(segmentsExperienceIds);

		return stream.filter(
			segmentsExperienceId -> {
				LayoutPageTemplateStructureRel layoutPageTemplateStructureRel =
					LayoutPageTemplateStructureRelLocalServiceUtil.
						fetchLayoutPageTemplateStructureRel(
							getLayoutPageTemplateStructureId(),
							segmentsExperienceId);

				return layoutPageTemplateStructureRel != null;
			}
		).findFirst(
		).orElse(
			SegmentsExperienceConstants.ID_DEFAULT
		);
	}

}