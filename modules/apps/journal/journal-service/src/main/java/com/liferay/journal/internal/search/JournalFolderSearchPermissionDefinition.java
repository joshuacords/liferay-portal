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
import com.liferay.portal.kernel.security.permission.resource.DynamicInheritancePermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.search.spi.model.permission.SearchPermissionDefinition;
import com.liferay.portal.search.spi.model.permission.DynamicInheritanceRoleSetContributor;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Arrays;
import java.util.List;

/**
 * @author Preston Crary
 */
@Component(service = SearchPermissionDefinition.class)
public class JournalFolderSearchPermissionDefinition
	implements SearchPermissionDefinition<JournalFolder> {

	@Override
	public JournalFolder getModel(long classPK) {
		return _journalFolderLocalService.fetchFolder(classPK);
	}

	@Override
	public String getClassName() {
		return JournalFolder.class.getName();
	}

	@Override
	public List<RoleSetContributor<JournalFolder>> getRoleSetContributors() {
		return Arrays.asList(
			_dynamicInheritanceRoleSetContributor);
//			new WorkflowedModelRoleSetContributor());
	}

	protected DynamicInheritanceRoleSetContributor getDynamicInheritanceRoleSetContributor() {
		return _dynamicInheritanceRoleSetContributor;
	}

	private UnsafeFunction<JournalFolder, JournalFolder, PortalException>
	_getFetchParentFunction() {

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
	private JournalFolderLocalService _journalFolderLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.journal.model.JournalFolder)"
	)
	private ModelResourcePermission<JournalFolder>
		_journalFolderModelResourcePermission;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	private DynamicInheritanceRoleSetContributor<JournalFolder, JournalFolder>
		_dynamicInheritanceRoleSetContributor =
		new DynamicInheritanceRoleSetContributor<>(
			_journalFolderModelResourcePermission, _getFetchParentFunction(),
			false, _resourcePermissionLocalService, _roleLocalService);

}