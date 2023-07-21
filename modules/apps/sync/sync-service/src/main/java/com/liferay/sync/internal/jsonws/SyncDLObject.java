/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.internal.jsonws;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.sync.service.SyncDLObjectService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Shinn Lok
 */
@Component(
	immediate = true,
	property = {
		"json.web.service.context.name=sync-web",
		"json.web.service.context.path=SyncDLObject"
	},
	service = SyncDLObject.class
)
@JSONWebService
public class SyncDLObject {

	public Object getSyncContext() throws PortalException {
		return _syncDLObjectService.getSyncContext();
	}

	@Reference(unbind = "-")
	protected void setGroupLocalService(
		SyncDLObjectService syncDLObjectService) {

		_syncDLObjectService = syncDLObjectService;
	}

	private SyncDLObjectService _syncDLObjectService;

}