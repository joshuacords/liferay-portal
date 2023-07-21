/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.internal.model.listener;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.petra.concurrent.NoticeableExecutorService;
import com.liferay.petra.executor.PortalExecutorManager;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.sync.constants.SyncDLObjectConstants;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.service.SyncDLObjectLocalService;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Shinn Lok
 */
public abstract class SyncBaseModelListener<T extends BaseModel<T>>
	extends BaseModelListener<T> {

	protected SyncDLObject getSyncDLObject(
		ResourcePermission resourcePermission) {

		String modelName = resourcePermission.getName();

		if (modelName.equals(DLFileEntry.class.getName())) {
			return syncDLObjectLocalService.fetchSyncDLObject(
				SyncDLObjectConstants.TYPE_FILE,
				GetterUtil.getLong(resourcePermission.getPrimKey()));
		}
		else if (modelName.equals(DLFolder.class.getName())) {
			return syncDLObjectLocalService.fetchSyncDLObject(
				SyncDLObjectConstants.TYPE_FOLDER,
				GetterUtil.getLong(resourcePermission.getPrimKey()));
		}

		return null;
	}

	protected void onAddRoleAssociation(Object roleId)
		throws ModelListenerException {

		ActionableDynamicQuery actionableDynamicQuery =
			_getActionableDynamicQuery(roleId);

		actionableDynamicQuery.setPerformActionMethod(
			(ResourcePermission resourcePermission) -> {
				SyncDLObject syncDLObject = getSyncDLObject(resourcePermission);

				if (syncDLObject == null) {
					return;
				}

				updateSyncDLObject(syncDLObject);
			});

		TransactionCommitCallbackUtil.registerCallback(
			() -> {
				NoticeableExecutorService noticeableExecutorService =
					portalExecutorManager.getPortalExecutor(
						UserModelListener.class.getName());

				noticeableExecutorService.submit(
					() -> {
						try {
							actionableDynamicQuery.performActions();
						}
						catch (Exception exception) {
							throw new ModelListenerException(exception);
						}

						return null;
					});

				return null;
			});
	}

	protected void onRemoveRoleAssociation(Object roleId) {
		ActionableDynamicQuery actionableDynamicQuery =
			_getActionableDynamicQuery(roleId);

		actionableDynamicQuery.setPerformActionMethod(
			(ResourcePermission resourcePermission) -> {
				SyncDLObject syncDLObject = getSyncDLObject(resourcePermission);

				if (syncDLObject == null) {
					return;
				}

				Date date = new Date();

				syncDLObject.setModifiedTime(date.getTime());
				syncDLObject.setLastPermissionChangeDate(date);

				syncDLObjectLocalService.updateSyncDLObject(syncDLObject);
			});

		TransactionCommitCallbackUtil.registerCallback(
			() -> {
				NoticeableExecutorService noticeableExecutorService =
					portalExecutorManager.getPortalExecutor(
						UserModelListener.class.getName());

				noticeableExecutorService.submit(
					() -> {
						try {
							actionableDynamicQuery.performActions();
						}
						catch (Exception exception) {
							throw new ModelListenerException(exception);
						}

						return null;
					});

				return null;
			});
	}

	protected void updateSyncDLObject(SyncDLObject syncDLObject) {
		syncDLObject.setModifiedTime(System.currentTimeMillis());

		syncDLObjectLocalService.updateSyncDLObject(syncDLObject);

		String type = syncDLObject.getType();

		if (!type.equals(SyncDLObjectConstants.TYPE_FOLDER)) {
			return;
		}

		List<SyncDLObject> childSyncDLObjects =
			syncDLObjectLocalService.getSyncDLObjects(
				syncDLObject.getRepositoryId(), syncDLObject.getTypePK());

		for (SyncDLObject childSyncDLObject : childSyncDLObjects) {
			updateSyncDLObject(childSyncDLObject);
		}
	}

	@Reference
	protected PortalExecutorManager portalExecutorManager;

	@Reference
	protected ResourcePermissionLocalService resourcePermissionLocalService;

	@Reference
	protected SyncDLObjectLocalService syncDLObjectLocalService;

	private ActionableDynamicQuery _getActionableDynamicQuery(
		final Object roleId) {

		ActionableDynamicQuery actionableDynamicQuery =
			resourcePermissionLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			dynamicQuery -> {
				Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

				Property nameProperty = PropertyFactoryUtil.forName("name");

				disjunction.add(nameProperty.eq(DLFileEntry.class.getName()));
				disjunction.add(nameProperty.eq(DLFolder.class.getName()));

				dynamicQuery.add(disjunction);

				Property roleIdProperty = PropertyFactoryUtil.forName("roleId");

				dynamicQuery.add(roleIdProperty.eq(roleId));

				Property viewActionIdProperty = PropertyFactoryUtil.forName(
					"viewActionId");

				dynamicQuery.add(viewActionIdProperty.eq(true));
			});

		return actionableDynamicQuery;
	}

}