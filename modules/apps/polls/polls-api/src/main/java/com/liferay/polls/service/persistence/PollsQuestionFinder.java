/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface PollsQuestionFinder {

	public int countByKeywords(
		long companyId, long[] groupIds, String keywords);

	public int countByC_G_T_D(
		long companyId, long[] groupIds, String title, String description,
		boolean andOperator);

	public java.util.List<com.liferay.polls.model.PollsQuestion> findByKeywords(
		long companyId, long[] groupIds, String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.liferay.polls.model.PollsQuestion> orderByComparator);

	public java.util.List<com.liferay.polls.model.PollsQuestion> findByC_G_T_D(
		long companyId, long[] groupIds, String title, String description,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<com.liferay.polls.model.PollsQuestion> orderByComparator);

}