/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.audit.model.listener;

import com.liferay.osb.koroneiki.phytohormone.model.Entitlement;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementLocalService;
import com.liferay.osb.koroneiki.root.audit.model.BaseAuditModelListener;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class EntitlementModelListener
	extends BaseAuditModelListener<Entitlement> {

	@Override
	protected long getClassNameId(Entitlement entitlement) {
		return entitlement.getClassNameId();
	}

	@Override
	protected long getClassPK(Entitlement entitlement) {
		return entitlement.getClassPK();
	}

	@Override
	protected Entitlement getModel(long classPK) throws PortalException {
		return _entitlementLocalService.getEntitlement(classPK);
	}

	@Override
	protected ServiceContext getServiceContext(long classNameId, long classPK) {
		ServiceContext serviceContext = super.getServiceContext(
			classNameId, classPK);

		serviceContext.setAttribute("agentName", StringPool.BLANK);
		serviceContext.setAttribute("agentUID", StringPool.BLANK);

		return serviceContext;
	}

	@Override
	protected long getUserId() throws PortalException {
		long companyId = portalInstancesLocalService.getDefaultCompanyId();

		return userLocalService.getDefaultUserId(companyId);
	}

	@Reference
	private EntitlementLocalService _entitlementLocalService;

}