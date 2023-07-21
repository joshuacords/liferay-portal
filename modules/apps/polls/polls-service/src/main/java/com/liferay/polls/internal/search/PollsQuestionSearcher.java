/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.internal.search;

import com.liferay.polls.model.PollsQuestion;
import com.liferay.portal.kernel.search.BaseSearcher;
import com.liferay.portal.kernel.search.Field;

import org.osgi.service.component.annotations.Component;

/**
 * @author Luan Maoski
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.polls.model.PollsQuestion",
	service = BaseSearcher.class
)
public class PollsQuestionSearcher extends BaseSearcher {

	public static final String CLASS_NAME = PollsQuestion.class.getName();

	public PollsQuestionSearcher() {
		setDefaultSelectedFieldNames(
			Field.ASSET_TAG_NAMES, Field.CREATE_DATE, Field.COMPANY_ID,
			Field.DESCRIPTION, Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
			Field.GROUP_ID, Field.SCOPE_GROUP_ID, Field.TITLE, Field.UID);
		setFilterSearch(true);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

}