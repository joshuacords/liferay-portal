/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.reports.engine.console.jasper.internal.exporter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.reports.engine.ReportFormat;
import com.liferay.portal.reports.engine.ReportFormatExporter;
import com.liferay.portal.reports.engine.ReportFormatExporterRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Brian Greenwald
 */
@Component(immediate = true, service = ReportFormatExporterRegistry.class)
public class JasperReportFormatExporterRegistry
	extends ReportFormatExporterRegistry {

	@Override
	public ReportFormatExporter getReportFormatExporter(
		ReportFormat reportFormat) {

		ReportFormatExporter reportFormatExporter = _reportFormatExporters.get(
			reportFormat);

		if (reportFormatExporter == null) {
			throw new IllegalArgumentException(
				"No report format exporter found for " + reportFormat);
		}

		return reportFormatExporter;
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void setReportFormatExporter(
		ReportFormatExporter reportFormatExporter,
		Map<String, Object> properties) {

		String reportFormatString = GetterUtil.getString(
			properties.get("reportFormat"));

		ReportFormat reportFormat = ReportFormat.parse(reportFormatString);

		_reportFormatExporters.put(reportFormat, reportFormatExporter);
	}

	protected void unsetReportFormatExporter(
		ReportFormatExporter reportFormatExporter,
		Map<String, Object> properties) {

		String reportFormatString = GetterUtil.getString(
			properties.get("reportFormat"));

		ReportFormat reportFormat = ReportFormat.parse(reportFormatString);

		if (reportFormat == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"No report format specified for " + reportFormatExporter);
			}

			return;
		}

		_reportFormatExporters.remove(reportFormat);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JasperReportFormatExporterRegistry.class);

	private final Map<ReportFormat, ReportFormatExporter>
		_reportFormatExporters = new ConcurrentHashMap<>();

}