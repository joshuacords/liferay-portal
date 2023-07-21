/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.io.exporter;

import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Locale;

/**
 * @author     Marcellus Tavares
 * @author     Manuel de la Peña
 * @deprecated As of Judson (7.1.x), replaced by {@link
 *             DDMFormInstanceRecordExporter}
 */
@Deprecated
public interface DDMFormExporter {

	public byte[] export(long formInstanceId) throws Exception;

	public byte[] export(long formInstanceId, int status) throws Exception;

	public byte[] export(long formInstanceId, int status, int start, int end)
		throws Exception;

	public byte[] export(
			long formInstanceId, int status, int start, int end,
			OrderByComparator<DDMFormInstanceRecord> orderByComparator)
		throws Exception;

	public String getFormat();

	public default String getLabel() {
		return StringUtil.toUpperCase(getFormat());
	}

	public Locale getLocale();

	public void setLocale(Locale locale);

}