/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.service.resource.permission.test.util;

import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;

/**
 * @author Joshua Cords
 */
public class JournalProxyFactory {

	public JournalProxyFactory(
		PersistedModelLocalService journalArticlePersistedModelLocalService,
		ResourceActionLocalService resourceActionLocalService,
		RoleProxyFactory roleProxyFactory, long companyId) {

		_journalArticlePersistedModelLocalService =
			journalArticlePersistedModelLocalService;
		_resourceActionLocalService = resourceActionLocalService;
		_roleProxyFactory = roleProxyFactory;
		_companyId = companyId;
	}

	public JournalArticleProxy createJournalArticleProxy(
			JournalFolderProxy journalFolderProxy, String... roleNames)
		throws Exception {

		return new JournalArticleProxy(
			_journalArticlePersistedModelLocalService,
			_resourceActionLocalService, _roleProxyFactory, roleNames,
			journalFolderProxy);
	}

	public JournalArticleProxy createJournalArticleProxy(String... roleNames)
		throws Exception {

		return new JournalArticleProxy(
			_journalArticlePersistedModelLocalService,
			_resourceActionLocalService, _roleProxyFactory, roleNames, null);
	}

	public JournalFolderProxy createJournalFolderProxy(
			JournalFolderProxy journalFolderProxy, String... roleNames)
		throws Exception {

		return new JournalFolderProxy(
			_resourceActionLocalService, _roleProxyFactory, roleNames,
			journalFolderProxy);
	}

	public JournalFolderProxy createJournalFolderProxy(String... roleNames)
		throws Exception {

		return new JournalFolderProxy(
			_resourceActionLocalService, _roleProxyFactory, roleNames, null);
	}

	private final long _companyId;
	private final PersistedModelLocalService
		_journalArticlePersistedModelLocalService;
	private final ResourceActionLocalService _resourceActionLocalService;
	private final RoleProxyFactory _roleProxyFactory;

}