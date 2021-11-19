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

package com.liferay.journal.internal.search;

import com.liferay.journal.constants.JournalFolderConstants;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalFolderLocalService;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.search.internal.permission.DynamicInheritanceRoleSetContributorFactory;
import com.liferay.portal.search.spi.model.permission.DynamicInheritanceRoleSetContributor;
import com.liferay.portal.search.spi.model.permission.SearchPermissionDefinition;

import java.util.Arrays;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(service = SearchPermissionDefinition.class)
public class JournalArticleSearchPermissionDefinition
	implements SearchPermissionDefinition<JournalArticle> {

	@Override
	public String getClassName() {
		return JournalArticle.class.getName();
	}

	@Override
	public JournalArticle getModel(long classPK) {
		return _journalArticleLocalService.fetchArticle(classPK);
	}

	@Override
	public List<RoleSetContributor<JournalArticle>> getRoleSetContributors() {
		DynamicInheritanceRoleSetContributor journalFolderContributor =
			_dynamicInheritanceRoleSetContributorFactory.create(
				_journalFolderModelResourcePermission,
				_getFetchJournalFolderParentFunction(), false);

		return Arrays.asList(
			_dynamicInheritanceRoleSetContributorFactory.create(
				_journalFolderModelResourcePermission,
				_getFetchJournalArticleParentFunction(), true,
				journalFolderContributor));
		//			new WorkflowedModelRoleSetContributor());
	}

	private UnsafeFunction<JournalArticle, JournalFolder, PortalException>
		_getFetchJournalArticleParentFunction() {

		return article -> {
			long folderId = article.getFolderId();

			if (JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID == folderId) {
				return null;
			}

			if (article.isInTrash()) {
				return _journalFolderLocalService.fetchFolder(folderId);
			}

			return _journalFolderLocalService.getFolder(folderId);
		};
	}

	private UnsafeFunction<JournalFolder, JournalFolder, PortalException>
		_getFetchJournalFolderParentFunction() {

		return folder -> {
			long folderId = folder.getParentFolderId();

			if (JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID == folderId) {
				return null;
			}

			if (folder.isInTrash()) {
				return _journalFolderLocalService.fetchJournalFolder(folderId);
			}

			return _journalFolderLocalService.getFolder(folderId);
		};
	}

	@Reference
	private DynamicInheritanceRoleSetContributorFactory
		_dynamicInheritanceRoleSetContributorFactory;

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

	@Reference
	private JournalFolderLocalService _journalFolderLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.journal.model.JournalFolder)"
	)
	private ModelResourcePermission<JournalFolder>
		_journalFolderModelResourcePermission;

}