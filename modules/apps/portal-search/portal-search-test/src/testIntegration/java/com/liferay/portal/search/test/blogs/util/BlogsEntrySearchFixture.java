/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.test.blogs.util;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalService;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author André de Oliveira
 */
public class BlogsEntrySearchFixture {

	public BlogsEntrySearchFixture(
		BlogsEntryLocalService blogsEntryLocalService) {

		_blogsEntryLocalService = blogsEntryLocalService;
	}

	public BlogsEntry addBlogsEntry(Group group, User user, String keyword)
		throws Exception {

		BlogsEntry blogsEntry = _blogsEntryLocalService.addEntry(
			user.getUserId(), keyword, RandomTestUtil.randomString(),
			ServiceContextTestUtil.getServiceContext(group.getGroupId()));

		_blogsEntries.add(blogsEntry);

		return blogsEntry;
	}

	public List<BlogsEntry> getBlogsEntries() {
		return _blogsEntries;
	}

	public void setUp() {
	}

	public void tearDown() {
	}

	private final List<BlogsEntry> _blogsEntries = new ArrayList<>();
	private final BlogsEntryLocalService _blogsEntryLocalService;

}