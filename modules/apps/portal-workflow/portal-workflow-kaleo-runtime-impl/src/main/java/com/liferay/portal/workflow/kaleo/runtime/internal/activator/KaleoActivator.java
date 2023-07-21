/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.runtime.internal.activator;

import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;
import com.liferay.portal.workflow.kaleo.runtime.WorkflowEngine;
import com.liferay.portal.workflow.kaleo.runtime.internal.messaging.KaleoWorkflowMessagingConfigurator;
import com.liferay.portal.workflow.kaleo.runtime.manager.PortalKaleoManager;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 * @author Michael C. Han
 */
@Component(immediate = true, service = KaleoActivator.class)
public class KaleoActivator {

	@Activate
	protected void activate() throws Exception {
		_portalKaleoManager.deployKaleoDefaults();
	}

	@Reference
	private KaleoWorkflowMessagingConfigurator
		_kaleoWorkflowMessagingConfigurator;

	@Reference
	private PortalKaleoManager _portalKaleoManager;

	@Reference(target = "(proxy.bean=false)")
	private WorkflowDefinitionManager _workflowDefinitionManager;

	@Reference
	private WorkflowEngine _workflowEngine;

}