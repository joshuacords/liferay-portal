/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.reports.engine;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;

import java.io.OutputStream;
import java.io.Serializable;

/**
 * @author Michael C. Han
 */
public class ByteArrayReportResultContainer
	implements ReportResultContainer, Serializable {

	public static final int DEFAULT_INITIAL_CAPCITY = 15360;

	public ByteArrayReportResultContainer() {
		this(null, DEFAULT_INITIAL_CAPCITY);
	}

	public ByteArrayReportResultContainer(String reportName) {
		this(reportName, DEFAULT_INITIAL_CAPCITY);
	}

	public ByteArrayReportResultContainer(
		String reportName, int initialCapacity) {

		_reportName = reportName;
		_initialCapacity = initialCapacity;
	}

	@Override
	public ReportResultContainer clone(String reportName) {
		return new ByteArrayReportResultContainer(reportName, _initialCapacity);
	}

	@Override
	public OutputStream getOutputStream() {
		if (_unsyncByteArrayOutputStream == null) {
			_unsyncByteArrayOutputStream = new UnsyncByteArrayOutputStream(
				_initialCapacity);
		}

		return _unsyncByteArrayOutputStream;
	}

	@Override
	public ReportGenerationException getReportGenerationException() {
		return _reportGenerationException;
	}

	@Override
	public String getReportName() {
		return _reportName;
	}

	@Override
	public byte[] getResults() {
		return _unsyncByteArrayOutputStream.toByteArray();
	}

	@Override
	public boolean hasError() {
		if (_reportGenerationException != null) {
			return true;
		}

		return false;
	}

	@Override
	public void setReportGenerationException(
		ReportGenerationException reportGenerationException) {

		_reportGenerationException = reportGenerationException;
	}

	private final int _initialCapacity;
	private ReportGenerationException _reportGenerationException;
	private final String _reportName;
	private UnsyncByteArrayOutputStream _unsyncByteArrayOutputStream;

}