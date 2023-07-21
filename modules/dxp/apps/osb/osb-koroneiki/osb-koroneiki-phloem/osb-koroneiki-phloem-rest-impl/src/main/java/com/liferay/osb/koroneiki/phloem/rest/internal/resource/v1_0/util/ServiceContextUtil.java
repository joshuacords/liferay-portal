/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util;

import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Amos Fong
 */
public class ServiceContextUtil {

	public static ServiceContext getServiceContext() {
		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if (serviceContext == null) {
			serviceContext = new ServiceContext();
		}

		return serviceContext;
	}

	public static void setAgentFields(String agentName, String agentUID) {
		if (Validator.isNull(agentName) && Validator.isNull(agentUID)) {
			return;
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.popServiceContext();

		if (serviceContext == null) {
			serviceContext = new ServiceContext();
		}

		if (Validator.isNotNull(agentName)) {
			serviceContext.setAttribute("agentName", agentName);
		}

		if (Validator.isNotNull(agentUID)) {
			serviceContext.setAttribute("agentUID", agentUID);
		}

		ServiceContextThreadLocal.pushServiceContext(serviceContext);
	}

	public static void setAuditSetId(Long auditSetId) {
		if ((auditSetId == null) || (auditSetId == 0)) {
			return;
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.popServiceContext();

		if (serviceContext == null) {
			serviceContext = new ServiceContext();
		}

		serviceContext.setAttribute("auditSetId", auditSetId);

		ServiceContextThreadLocal.pushServiceContext(serviceContext);
	}

}