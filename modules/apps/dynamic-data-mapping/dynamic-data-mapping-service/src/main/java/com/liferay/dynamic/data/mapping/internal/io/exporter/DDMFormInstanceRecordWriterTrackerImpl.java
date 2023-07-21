/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.io.exporter;

import com.liferay.dynamic.data.mapping.io.exporter.DDMFormInstanceRecordWriter;
import com.liferay.dynamic.data.mapping.io.exporter.DDMFormInstanceRecordWriterTracker;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Leonardo Barros
 */
@Component(immediate = true, service = DDMFormInstanceRecordWriterTracker.class)
public class DDMFormInstanceRecordWriterTrackerImpl
	implements DDMFormInstanceRecordWriterTracker {

	@Override
	public DDMFormInstanceRecordWriter getDDMFormInstanceRecordWriter(
		String type) {

		return _ddmFormInstanceRecordWriters.get(type);
	}

	@Override
	public Map<String, String> getDDMFormInstanceRecordWriterExtensions() {
		return Collections.unmodifiableMap(
			_ddmFormInstanceRecordWriterExtensions);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addDDMFormInstanceRecordWriter(
		DDMFormInstanceRecordWriter ddmFormInstanceRecordWriter,
		Map<String, Object> properties) {

		String type = MapUtil.getString(
			properties, "ddm.form.instance.record.writer.type");

		String extension = MapUtil.getString(
			properties, "ddm.form.instance.record.writer.extension");

		if (Validator.isNull(extension)) {
			extension = StringUtil.toUpperCase(type);
		}

		_ddmFormInstanceRecordWriterExtensions.put(type, extension);

		_ddmFormInstanceRecordWriters.put(type, ddmFormInstanceRecordWriter);
	}

	@Deactivate
	protected void deactivate() {
		_ddmFormInstanceRecordWriterExtensions.clear();
		_ddmFormInstanceRecordWriters.clear();
	}

	protected void removeDDMFormInstanceRecordWriter(
		DDMFormInstanceRecordWriter ddmFormInstanceRecordWriter,
		Map<String, Object> properties) {

		String type = MapUtil.getString(
			properties, "ddm.form.instance.record.writer.type");

		_ddmFormInstanceRecordWriterExtensions.remove(type);
		_ddmFormInstanceRecordWriters.remove(type);
	}

	private final Map<String, String> _ddmFormInstanceRecordWriterExtensions =
		new TreeMap<>();
	private final Map<String, DDMFormInstanceRecordWriter>
		_ddmFormInstanceRecordWriters = new TreeMap<>();

}