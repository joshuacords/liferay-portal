/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.reports.engine.console.jasper.internal.fill.manager;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.reports.engine.ReportDataSourceType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Gavin Wan
 * @author Brian Wing Shun Chan
 * @author Brian Greenwald
 */
@Component(immediate = true, service = ReportFillManagerRegistry.class)
public class ReportFillManagerRegistry {

	public ReportFillManager getReportFillManager(
		ReportDataSourceType reportDataSourceType) {

		ReportFillManager reportFillManager = _reportFillManagers.get(
			reportDataSourceType);

		if (_reportFillManagers == null) {
			throw new IllegalArgumentException(
				"No report fill manager found for " + reportDataSourceType);
		}

		return reportFillManager;
	}

	public void unsetReportFillManager(
		ReportFillManager reportFillManager, Map<String, Object> properties) {

		String reportDataSourceTypeString = GetterUtil.getString(
			properties.get("reportDataSourceType"));

		ReportDataSourceType reportDataSourceType = ReportDataSourceType.parse(
			reportDataSourceTypeString);

		if (reportDataSourceType == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"No report data source type specified for " +
						reportFillManager);
			}

			return;
		}

		_reportFillManagers.remove(reportDataSourceType);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void setReportFillManager(
		ReportFillManager reportFillManager, Map<String, Object> properties) {

		String reportDataSourceTypeString = GetterUtil.getString(
			properties.get("reportDataSourceType"));

		ReportDataSourceType reportDataSourceType = ReportDataSourceType.parse(
			reportDataSourceTypeString);

		_reportFillManagers.put(reportDataSourceType, reportFillManager);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ReportFillManagerRegistry.class);

	private Map<ReportDataSourceType, ReportFillManager> _reportFillManagers =
		new ConcurrentHashMap<>();

}