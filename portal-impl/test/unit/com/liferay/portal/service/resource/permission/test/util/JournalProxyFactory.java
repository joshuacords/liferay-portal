package com.liferay.portal.service.resource.permission.test.util;

import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;

public class JournalProxyFactory {
	public JournalProxyFactory(
		PersistedModelLocalService journalArticlePersistedModelLocalService,
//		PersistedModelLocalService journalFolderPersistedModelLocalService,
		RoleProxyFactory roleProxyFactory,
		ResourceActionLocalService resourceActionLocalService, long companyId) {
		_journalArticlePersistedModelLocalService =
			journalArticlePersistedModelLocalService;
//		_journalFolderPersistedModelLocalService =
//			journalFolderPersistedModelLocalService;
		_roleProxyFactory = roleProxyFactory;
		_resourceActionLocalService =
			resourceActionLocalService;
		_companyId = companyId;
	}

	public JournalArticleProxy createJournalArticleProxy(String ... roleNames) throws Exception {
		return new JournalArticleProxy(
			_roleProxyFactory,
			_journalArticlePersistedModelLocalService,
			_resourceActionLocalService, roleNames);
	}

	public JournalArticleProxy createJournalArticleProxy(
		JournalFolderProxy journalFolderProxy, String ... roleNames) throws Exception {
		return new JournalArticleProxy(
			_roleProxyFactory,
			_journalArticlePersistedModelLocalService,
			_resourceActionLocalService, journalFolderProxy, roleNames);
	}

	public JournalFolderProxy createJournalFolderProxy(String ... roleNames) throws Exception {
		return new JournalFolderProxy(
			_resourceActionLocalService, _roleProxyFactory, roleNames);
	}

	public JournalFolderProxy createJournalFolderProxy(
		JournalFolderProxy journalFolderProxy, String ... roleNames) throws Exception {
		return new JournalFolderProxy(
			_resourceActionLocalService, _roleProxyFactory, journalFolderProxy,
			roleNames);
	}

	RoleProxyFactory _roleProxyFactory;
	PersistedModelLocalService _journalArticlePersistedModelLocalService;
	PersistedModelLocalService _journalFolderPersistedModelLocalService;
	ResourceActionLocalService _resourceActionLocalService;
	private long _companyId;
}

