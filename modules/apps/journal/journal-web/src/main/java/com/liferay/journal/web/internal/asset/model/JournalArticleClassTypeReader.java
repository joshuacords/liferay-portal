/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.asset.model;

import com.liferay.asset.kernel.model.BaseDDMStructureClassTypeReader;
import com.liferay.asset.kernel.model.ClassType;
import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.portal.kernel.model.Group;
import com.liferay.staging.StagingGroupHelper;
import com.liferay.staging.StagingGroupHelperUtil;

import java.util.List;
import java.util.Locale;

/**
 * @author Balázs Sáfrány-Kovalik
 */
public class JournalArticleClassTypeReader
	extends BaseDDMStructureClassTypeReader {

	public JournalArticleClassTypeReader(String className) {
		super(className);
	}

	@Override
	public List<ClassType> getAvailableClassTypes(
		long[] groupIds, Locale locale) {

		groupIds = _replaceGroupIds(groupIds);

		return super.getAvailableClassTypes(groupIds, locale);
	}

	private long[] _replaceGroupIds(long[] groupIds) {
		StagingGroupHelper stagingGroupHelper =
			StagingGroupHelperUtil.getStagingGroupHelper();

		groupIds = groupIds.clone();

		for (int i = 0; i < groupIds.length; i++) {
			if (stagingGroupHelper.isLocalStagingGroup(groupIds[i]) &&
				!stagingGroupHelper.isStagedPortlet(
					groupIds[i], JournalPortletKeys.JOURNAL)) {

				Group group = stagingGroupHelper.fetchLocalLiveGroup(
					groupIds[i]);

				groupIds[i] = group.getGroupId();
			}
		}

		return groupIds;
	}

}