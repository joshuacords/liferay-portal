/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.internal.exporter;

import com.liferay.dynamic.data.lists.exporter.DDLExporter;
import com.liferay.dynamic.data.lists.exporter.DDLExporterFactory;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * Provides a factory to fetch implementations of the DDL Exporter service. By
 * default, implementations for XML and CSV formats are available, but others
 * can be added as OSGi modules.
 *
 * @author Marcellus Tavares
 * @see    DDLExporter
 */
@Component(immediate = true, service = DDLExporterFactory.class)
public class DDLExporterFactoryImpl implements DDLExporterFactory {

	/**
	 * Returns the available formats that can be used to export record set
	 * records.
	 *
	 * @return the available formats registered in the system
	 */
	@Override
	public Set<String> getAvailableFormats() {
		return Collections.unmodifiableSet(_ddlExporters.keySet());
	}

	/**
	 * Returns the DDL Exporter service instance for the format.
	 *
	 * @param  format the format that will be used to export
	 * @return the DDL Exporter instance
	 */
	@Override
	public DDLExporter getDDLExporter(String format) {
		DDLExporter ddlExporter = _ddlExporters.get(format);

		if (ddlExporter == null) {
			throw new IllegalArgumentException(
				"No DDL exporter exists for the format " + format);
		}

		return ddlExporter;
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addDDLExporter(DDLExporter ddlExporter) {
		_ddlExporters.put(ddlExporter.getFormat(), ddlExporter);
	}

	protected void removeDDLExporter(DDLExporter ddlExporter) {
		_ddlExporters.remove(ddlExporter.getFormat());
	}

	private final Map<String, DDLExporter> _ddlExporters =
		new ConcurrentHashMap<>();

}