/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.internal.model.listener;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.sync.constants.SyncDeviceConstants;
import com.liferay.sync.model.SyncDevice;
import com.liferay.sync.service.SyncDeviceLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jonathan McCann
 */
@Component(immediate = true, service = ModelListener.class)
public class UserModelListener extends SyncBaseModelListener<User> {

	@Override
	public void onAfterRemove(User user) throws ModelListenerException {
		try {
			if (user != null) {
				List<SyncDevice> syncDevices =
					_syncDeviceLocalService.getSyncDevices(
						user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS,
						null);

				for (SyncDevice syncDevice : syncDevices) {
					_syncDeviceLocalService.deleteSyncDevice(syncDevice);
				}
			}
		}
		catch (Exception exception) {
			throw new ModelListenerException(exception);
		}
	}

	@Override
	public void onBeforeAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		if (associationClassName.equals(Role.class.getName())) {
			User user = _userLocalService.fetchUser((Long)classPK);

			if ((user == null) || !user.isActive()) {
				return;
			}

			onAddRoleAssociation(associationClassPK);
		}
	}

	@Override
	public void onBeforeRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		if (associationClassName.equals(Role.class.getName())) {
			User user = _userLocalService.fetchUser((Long)classPK);

			if ((user == null) || !user.isActive()) {
				return;
			}

			onRemoveRoleAssociation(associationClassPK);
		}
	}

	@Override
	public void onBeforeUpdate(User user) throws ModelListenerException {
		try {
			User originalUser = _userLocalService.getUser(user.getUserId());

			if (originalUser.isActive() && !user.isActive()) {
				List<SyncDevice> syncDevices =
					_syncDeviceLocalService.getSyncDevices(
						user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS,
						null);

				for (SyncDevice syncDevice : syncDevices) {
					_syncDeviceLocalService.updateStatus(
						syncDevice.getSyncDeviceId(),
						SyncDeviceConstants.STATUS_INACTIVE);
				}
			}
			else if (!originalUser.isActive() && user.isActive()) {
				List<SyncDevice> syncDevices =
					_syncDeviceLocalService.getSyncDevices(
						user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS,
						null);

				for (SyncDevice syncDevice : syncDevices) {
					_syncDeviceLocalService.updateStatus(
						syncDevice.getSyncDeviceId(),
						SyncDeviceConstants.STATUS_ACTIVE);
				}
			}
		}
		catch (Exception exception) {
			throw new ModelListenerException(exception);
		}
	}

	@Reference
	private SyncDeviceLocalService _syncDeviceLocalService;

	@Reference
	private UserLocalService _userLocalService;

}