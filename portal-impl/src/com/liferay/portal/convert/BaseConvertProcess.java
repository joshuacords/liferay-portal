/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.convert;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.MaintenanceUtil;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Alexander Chow
 */
public abstract class BaseConvertProcess implements ConvertProcess {

	@Override
	public void convert() throws ConvertException {
		try {
			if (getPath() != null) {
				return;
			}

			StopWatch stopWatch = new StopWatch();

			stopWatch.start();

			if (_log.isInfoEnabled()) {
				Class<?> clazz = getClass();

				_log.info("Starting conversion for " + clazz.getName());
			}

			doConvert();

			if (_log.isInfoEnabled()) {
				Class<?> clazz = getClass();

				_log.info(
					StringBundler.concat(
						"Finished conversion for ", clazz.getName(), " in ",
						stopWatch.getTime(), " ms"));
			}
		}
		catch (Exception exception) {
			throw new ConvertException(exception);
		}
		finally {
			setParameterValues(null);

			if (MaintenanceUtil.isMaintaining()) {
				MaintenanceUtil.cancel();
			}
		}
	}

	@Override
	public String getConfigurationErrorMessage() {
		return null;
	}

	@Override
	public abstract String getDescription();

	@Override
	public String getParameterDescription() {
		return null;
	}

	@Override
	public String[] getParameterNames() {
		return null;
	}

	public String[] getParameterValues() {
		return _paramValues;
	}

	@Override
	public String getPath() {
		return null;
	}

	@Override
	public abstract boolean isEnabled();

	@Override
	public void setParameterValues(String[] values) {
		_paramValues = values;
	}

	/**
	 * @throws ConvertException
	 */
	@Override
	public void validate() throws ConvertException {
	}

	protected abstract void doConvert() throws Exception;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseConvertProcess.class);

	private String[] _paramValues;

}