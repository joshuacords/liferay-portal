/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.data.provider.web.internal.portlet.action;

import com.liferay.dynamic.data.mapping.data.provider.web.internal.constants.DDMDataProviderPortletKeys;
import com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseTransactionalMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Leonardo Barros
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + DDMDataProviderPortletKeys.DYNAMIC_DATA_MAPPING_DATA_PROVIDER,
		"mvc.command.name=deleteDataProvider"
	},
	service = MVCActionCommand.class
)
public class DeleteDataProviderMVCActionCommand
	extends BaseTransactionalMVCActionCommand {

	protected void doDeleteDataProviderInstance(long dataProviderInstanceId)
		throws PortalException {

		_ddmDataProviderInstanceService.deleteDataProviderInstance(
			dataProviderInstanceId);
	}

	@Override
	protected void doTransactionalCommand(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] deleteDataProviderInstanceIds = null;

		long dataProviderInstanceId = ParamUtil.getLong(
			actionRequest, "dataProviderInstanceId");

		if (dataProviderInstanceId > 0) {
			deleteDataProviderInstanceIds = new long[] {dataProviderInstanceId};
		}
		else {
			deleteDataProviderInstanceIds = StringUtil.split(
				ParamUtil.getString(
					actionRequest, "deleteDataProviderInstanceIds"),
				0L);
		}

		for (long deleteDataProviderInstanceId :
				deleteDataProviderInstanceIds) {

			doDeleteDataProviderInstance(deleteDataProviderInstanceId);
		}
	}

	@Reference
	private DDMDataProviderInstanceService _ddmDataProviderInstanceService;

}