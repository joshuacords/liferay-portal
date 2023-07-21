/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.internal.security.permission.resource;

import com.liferay.data.engine.rest.internal.constants.DataDefinitionConstants;
import com.liferay.data.engine.rest.internal.constants.DataRecordCollectionConstants;
import com.liferay.data.engine.rest.internal.model.InternalDataRecordCollection;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Leonardo Barros
 */
@Component(
	immediate = true,
	property = "model.class.name=" + DataRecordCollectionConstants.RESOURCE_NAME,
	service = ModelResourcePermission.class
)
public class InternalDataRecordCollectionModelResourcePermission
	implements ModelResourcePermission<InternalDataRecordCollection> {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			InternalDataRecordCollection internalDataRecordCollection,
			String actionId)
		throws PortalException {

		if (!contains(
				permissionChecker, internalDataRecordCollection, actionId)) {

			throw new PrincipalException.MustHavePermission(
				permissionChecker, DataDefinitionConstants.RESOURCE_NAME,
				(long)internalDataRecordCollection.getPrimaryKeyObj(),
				actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long primaryKey,
			String actionId)
		throws PortalException {

		InternalDataRecordCollection internalDataRecordCollection =
			new InternalDataRecordCollection();

		internalDataRecordCollection.setPrimaryKeyObj(primaryKey);

		check(permissionChecker, internalDataRecordCollection, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			InternalDataRecordCollection internalDataRecordCollection,
			String actionId)
		throws PortalException {

		DDLRecordSet recordSet = ddlRecordSetLocalService.getRecordSet(
			(long)internalDataRecordCollection.getPrimaryKeyObj());

		if (permissionChecker.hasOwnerPermission(
				recordSet.getCompanyId(),
				InternalDataRecordCollection.class.getName(),
				(long)internalDataRecordCollection.getPrimaryKeyObj(),
				recordSet.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			recordSet.getGroupId(),
			InternalDataRecordCollection.class.getName(),
			(long)internalDataRecordCollection.getPrimaryKeyObj(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long primaryKey,
			String actionId)
		throws PortalException {

		InternalDataRecordCollection internalDataRecordCollection =
			new InternalDataRecordCollection();

		internalDataRecordCollection.setPrimaryKeyObj(primaryKey);

		return contains(
			permissionChecker, internalDataRecordCollection, actionId);
	}

	@Override
	public String getModelName() {
		return InternalDataRecordCollection.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return null;
	}

	@Reference
	protected DDLRecordSetLocalService ddlRecordSetLocalService;

}