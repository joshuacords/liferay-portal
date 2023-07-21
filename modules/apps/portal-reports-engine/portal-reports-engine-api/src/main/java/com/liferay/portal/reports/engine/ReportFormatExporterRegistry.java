/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.reports.engine;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Michael C. Han
 */
public class ReportFormatExporterRegistry {

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

	protected void setReportFormatExporters(
		Map<String, ReportFormatExporter> reportFormatExporters) {

		for (Map.Entry<String, ReportFormatExporter> entry :
				reportFormatExporters.entrySet()) {

			ReportFormat reportFormat = ReportFormat.parse(entry.getKey());
			ReportFormatExporter reportFormatExporter = entry.getValue();

			_reportFormatExporters.put(reportFormat, reportFormatExporter);
		}
	}

	private final Map<ReportFormat, ReportFormatExporter>
		_reportFormatExporters = new ConcurrentHashMap<>();

}