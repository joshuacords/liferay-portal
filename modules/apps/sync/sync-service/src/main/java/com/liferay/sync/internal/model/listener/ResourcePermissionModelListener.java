/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.internal.model.listener;

import com.liferay.petra.concurrent.NoticeableExecutorService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.sync.model.SyncDLObject;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

/**
 * @author Shinn Lok
 */
@Component(immediate = true, service = ModelListener.class)
public class ResourcePermissionModelListener
	extends SyncBaseModelListener<ResourcePermission> {

	@Override
	public void onBeforeCreate(ResourcePermission resourcePermission)
		throws ModelListenerException {

		SyncDLObject syncDLObject = getSyncDLObject(resourcePermission);

		if (syncDLObject == null) {
			return;
		}

		if (resourcePermission.hasActionId(ActionKeys.VIEW)) {
			updateSyncDLObject(syncDLObject);
		}
	}

	@Override
	public void onBeforeRemove(ResourcePermission resourcePermission)
		throws ModelListenerException {

		SyncDLObject syncDLObject = getSyncDLObject(resourcePermission);

		if (syncDLObject == null) {
			return;
		}

		if (resourcePermission.hasActionId(ActionKeys.VIEW)) {
			Date date = new Date();

			syncDLObject.setModifiedTime(date.getTime());
			syncDLObject.setLastPermissionChangeDate(date);

			syncDLObjectLocalService.updateSyncDLObject(syncDLObject);
		}
	}

	@Override
	public void onBeforeUpdate(ResourcePermission resourcePermission)
		throws ModelListenerException {

		SyncDLObject syncDLObject = getSyncDLObject(resourcePermission);

		if (syncDLObject == null) {
			return;
		}

		ResourcePermission originalResourcePermission =
			resourcePermissionLocalService.fetchResourcePermission(
				resourcePermission.getResourcePermissionId());

		if (originalResourcePermission.hasActionId(ActionKeys.VIEW) &&
			!resourcePermission.hasActionId(ActionKeys.VIEW)) {

			Date date = new Date();

			syncDLObject.setModifiedTime(date.getTime());
			syncDLObject.setLastPermissionChangeDate(date);

			syncDLObjectLocalService.updateSyncDLObject(syncDLObject);
		}
		else if (!originalResourcePermission.hasActionId(ActionKeys.VIEW) &&
				 resourcePermission.hasActionId(ActionKeys.VIEW)) {

			TransactionCommitCallbackUtil.registerCallback(
				() -> {
					NoticeableExecutorService noticeableExecutorService =
						portalExecutorManager.getPortalExecutor(
							ResourcePermissionModelListener.class.getName());

					noticeableExecutorService.submit(
						() -> {
							try {
								updateSyncDLObject(syncDLObject);
							}
							catch (Exception exception) {
								throw new ModelListenerException(exception);
							}

							return null;
						});

					return null;
				});
		}
	}

}