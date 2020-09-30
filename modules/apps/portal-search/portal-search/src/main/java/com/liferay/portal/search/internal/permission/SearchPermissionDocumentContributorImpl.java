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

package com.liferay.portal.search.internal.permission;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchResourceException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.permission.SearchPermissionDocumentContributor;
import com.liferay.portal.search.spi.model.permission.SearchPermissionFieldContributor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.liferay.portlet.documentlibrary.service.impl.DLFileEntryServiceImpl;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true, service = SearchPermissionDocumentContributor.class
)
public class SearchPermissionDocumentContributorImpl
	implements SearchPermissionDocumentContributor {

	@Override
	public void addPermissionFields(long companyId, Document document) {
		long groupId = GetterUtil.getLong(document.get(Field.GROUP_ID));

		String className = document.get(Field.ENTRY_CLASS_NAME);
		String classPK = document.get(Field.ENTRY_CLASS_PK);

		if (Validator.isNull(className) && Validator.isNull(classPK)) {
			className = document.get(Field.ROOT_ENTRY_CLASS_NAME);
			classPK = document.get(Field.ROOT_ENTRY_CLASS_PK);
		}

		boolean relatedEntry = GetterUtil.getBoolean(
			document.get(Field.RELATED_ENTRY));

		if (relatedEntry) {
			long classNameId = GetterUtil.getLong(
				document.get(Field.CLASS_NAME_ID));

			if (classNameId > 0) {
				className = _portal.getClassName(classNameId);
				classPK = document.get(Field.CLASS_PK);
			}
		}

		addPermissionFields(
			companyId, groupId, className, GetterUtil.getLong(classPK),
			document);
	}

	@Override
	public void addPermissionFields(
		long companyId, long groupId, String className, long classPK,
		Document document) {

		Indexer<?> indexer = _indexerRegistry.nullSafeGetIndexer(className);

		if (!indexer.isPermissionAware()) {
			return;
		}

		String viewActionId = document.get(Field.VIEW_ACTION_ID);

		if (Validator.isNull(viewActionId)) {
			viewActionId = ActionKeys.VIEW;
		}

		_addPermissionFields(
			companyId, groupId, className, classPK, viewActionId, document);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addSearchPermissionFieldContributor(
		SearchPermissionFieldContributor searchPermissionFieldContributor) {

		_searchPermissionFieldContributors.add(
			searchPermissionFieldContributor);
	}

	protected void removeSearchPermissionFieldContributor(
		SearchPermissionFieldContributor searchPermissionFieldContributor) {

		_searchPermissionFieldContributors.remove(
			searchPermissionFieldContributor);
	}

	private List<Role> _getFolderRoles(
		long companyId, long groupId, String className, long classPK,
	   	String viewActionId, Document document) throws PortalException {

		//needs to be recursive??

		List<Role> folderViewRoles = _resourcePermissionLocalService.getRoles(
			companyId, "com.liferay.document.library.kernel.model.DLFolder", ResourceConstants.SCOPE_INDIVIDUAL,
			document.get("folderId"), viewActionId);

		List<Role> folderAccessRoles = _resourcePermissionLocalService.getRoles(
			companyId, "com.liferay.document.library.kernel.model.DLFolder", ResourceConstants.SCOPE_INDIVIDUAL,
			document.get("folderId"), "ACCESS");

		folderViewRoles.addAll(folderAccessRoles);

		return folderViewRoles;
	}

//	private <C extends GroupedModel, P extends GroupedModel> List<Role> _getFolderRoles2(
//		PermissionChecker permissionChecker, String name, C child,
//		String actionId)
//		throws PortalException {
//
//		if (!actionId.equals(ActionKeys.VIEW)) {
//			return null;
//		}
//
//		P parent = _fetchParentUnsafeFunction.apply(child);
//
//		if (parent == null) {
//			if (_portletResourcePermission.contains(
//				permissionChecker, child.getGroupId(), ActionKeys.VIEW)) {
//
//				return null;
//			}
//
//			return false;
//		}
//
//		if (_checkParentAccess &&
//			_parentModelResourcePermission.contains(
//				permissionChecker, parent, ActionKeys.ACCESS)) {
//
//			return null;
//		}
//
//		if (_parentModelResourcePermission.contains(
//			permissionChecker, parent, ActionKeys.VIEW)) {
//
//			return null;
//		}
//
//		return false;
//	}

	private void _addPermissionFields(
		long companyId, long groupId, String className, long classPK,
		String viewActionId, Document document) {

		for (SearchPermissionFieldContributor searchPermissionFieldContributor :
				_searchPermissionFieldContributors) {

			searchPermissionFieldContributor.contribute(
				document, className, classPK);
		}

		String permissionName = _getPermissionName(document, className);

		try {
			List<Role> roles = _resourcePermissionLocalService.getRoles(
				companyId, permissionName, ResourceConstants.SCOPE_INDIVIDUAL,
				String.valueOf(classPK), viewActionId);

			if (permissionName.contains("DL")) {
//				roles.retainAll(_getFolderRoles(companyId, groupId, className, classPK,
//				viewActionId, document));

				roles = _resourcePermissionLocalService.getCompositeRoles(companyId, permissionName, ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(classPK), viewActionId);
			}

			if (roles.isEmpty()) {
				return;
			}

			List<Long> roleIds = new ArrayList<>();
			List<String> groupRoleIds = new ArrayList<>();

			for (Role role : roles) {
				if ((role.getType() == RoleConstants.TYPE_ORGANIZATION) ||
					(role.getType() == RoleConstants.TYPE_SITE)) {

					groupRoleIds.add(
						groupId + StringPool.DASH + role.getRoleId());
				}
				else {
					roleIds.add(role.getRoleId());
				}
			}

			document.addKeyword(Field.ROLE_ID, roleIds.toArray(new Long[0]));
			document.addKeyword(
				Field.GROUP_ROLE_ID, groupRoleIds.toArray(new String[0]));

			_log.error("Indexing " + document.get("title") + " with groupRoleIds size = " + groupRoleIds.size());
		}
		catch (NoSuchResourceException noSuchResourceException) {
			if (_log.isDebugEnabled()) {
				_log.debug(noSuchResourceException, noSuchResourceException);
			}
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					StringBundler.concat(
						"Unable to get permission fields for class name ",
						permissionName, " and class PK ", classPK),
					exception);
			}
		}
	}

	private String _getPermissionName(Document document, String defaultValue) {
		String resourcePermissionName = document.get("resourcePermissionName");

		if (Validator.isNull(resourcePermissionName)) {
			return defaultValue;
		}

		return resourcePermissionName;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SearchPermissionDocumentContributorImpl.class);

	@Reference
	private IndexerRegistry _indexerRegistry;

	@Reference
	private Portal _portal;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	private final Collection<SearchPermissionFieldContributor>
		_searchPermissionFieldContributors = new CopyOnWriteArrayList<>();


	private static volatile ModelResourcePermission<DLFileEntry>
		_dlFileEntryModelResourcePermission =
		ModelResourcePermissionFactory.getInstance(
			DLFileEntryServiceImpl.class,
			"_dlFileEntryModelResourcePermission", DLFileEntry.class);
	private static volatile ModelResourcePermission<FileEntry>
		_fileEntryModelResourcePermission =
		ModelResourcePermissionFactory.getInstance(
			DLFileEntryServiceImpl.class,
			"_fileEntryModelResourcePermission", FileEntry.class);
	private static volatile ModelResourcePermission<Folder>
		_folderModelResourcePermission =
		ModelResourcePermissionFactory.getInstance(
			DLFileEntryServiceImpl.class, "_folderModelResourcePermission",
			Folder.class);


}