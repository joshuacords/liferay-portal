/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.web.internal.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.workflow.metrics.web.internal.constants.WorkflowMetricsPortletKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rafael Praxedes
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=150",
		"panel.category.key=" + PanelCategoryKeys.CONTROL_PANEL_WORKFLOW
	},
	service = PanelApp.class
)
public class ControlPanelWorkflowMetricsPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return WorkflowMetricsPortletKeys.WORKFLOW_METRICS;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + WorkflowMetricsPortletKeys.WORKFLOW_METRICS + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}