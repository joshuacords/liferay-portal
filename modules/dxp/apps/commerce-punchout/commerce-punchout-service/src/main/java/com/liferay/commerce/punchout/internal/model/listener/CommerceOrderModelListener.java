/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.punchout.internal.model.listener;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.punchout.service.PunchOutAccountRoleHelper;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Collections;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jaclyn Ong
 */
@Component(immediate = true, service = ModelListener.class)
public class CommerceOrderModelListener
	extends BaseModelListener<CommerceOrder> {

	@Override
	public void onAfterUpdate(CommerceOrder commerceOrder) {
		try {
			if ((commerceOrder.getStatus() != WorkflowConstants.STATUS_DRAFT) ||
				!_punchOutAccountRoleHelper.hasPunchOutRole(
					commerceOrder.getUserId(),
					commerceOrder.getCommerceAccountId())) {

				return;
			}

			ServiceContext serviceContext = new ServiceContext();

			_commerceOrderLocalService.updateStatus(
				commerceOrder.getUserId(), commerceOrder.getCommerceOrderId(),
				WorkflowConstants.STATUS_APPROVED, serviceContext,
				Collections.emptyMap());
		}
		catch (PortalException e) {
			_log.error(
				"Failed to update workflow status to Approved on punch out " +
					"order (" + commerceOrder.getCommerceOrderId() + ")",
				e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceOrderModelListener.class);

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Reference
	private PunchOutAccountRoleHelper _punchOutAccountRoleHelper;

}