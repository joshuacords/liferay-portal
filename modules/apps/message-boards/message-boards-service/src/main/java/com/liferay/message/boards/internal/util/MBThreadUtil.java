/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.internal.util;

import com.liferay.message.boards.constants.MBCategoryConstants;
import com.liferay.message.boards.model.MBThread;
import com.liferay.message.boards.service.persistence.MBThreadPersistence;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;

import java.util.List;

/**
 * @author Preston Crary
 */
public class MBThreadUtil {

	public static List<MBThread> getGroupThreads(
		MBThreadPersistence mbThreadPersistence, long groupId,
		QueryDefinition<MBThread> queryDefinition) {

		if (queryDefinition.isExcludeStatus()) {
			return mbThreadPersistence.findByG_NotC_NotS(
				groupId, MBCategoryConstants.DISCUSSION_CATEGORY_ID,
				queryDefinition.getStatus(), queryDefinition.getStart(),
				queryDefinition.getEnd());
		}

		return mbThreadPersistence.findByG_NotC_S(
			groupId, MBCategoryConstants.DISCUSSION_CATEGORY_ID,
			queryDefinition.getStatus(), queryDefinition.getStart(),
			queryDefinition.getEnd());
	}

	private MBThreadUtil() {
	}

}