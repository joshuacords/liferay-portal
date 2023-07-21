/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.mobile.device.detection.fiftyonedegrees.internal;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mobile.device.Device;
import com.liferay.portal.kernel.mobile.device.UnknownDevice;
import com.liferay.portal.mobile.device.detection.fiftyonedegrees.configuration.FiftyOneDegreesConfiguration;
import com.liferay.portal.mobile.device.detection.fiftyonedegrees.data.DataFileProvider;

import fiftyone.mobile.detection.Dataset;
import fiftyone.mobile.detection.Match;
import fiftyone.mobile.detection.Provider;
import fiftyone.mobile.detection.factories.StreamFactory;

import java.io.IOException;
import java.io.InputStream;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Greenwald
 * @author Prathima Shreenath
 */
@Component(
	configurationPid = "com.liferay.portal.mobile.device.detection.fiftyonedegrees.configuration.FiftyOneDegreesConfiguration",
	service = FiftyOneDegreesEngineProxy.class
)
public class FiftyOneDegreesEngineProxy {

	public Dataset getDataset() {
		return _provider.dataSet;
	}

	public Device getDevice(HttpServletRequest httpServletRequest) {
		String userAgent = httpServletRequest.getHeader("User-Agent");

		try {
			Match match = _provider.match(userAgent);

			return new FiftyOneDegreesDevice(match);
		}
		catch (IOException ioException) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to get match for user agent: " + userAgent,
					ioException);
			}

			return UnknownDevice.getInstance();
		}
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_fiftyOneDegreesConfiguration = ConfigurableUtil.createConfigurable(
			FiftyOneDegreesConfiguration.class, properties);

		try (InputStream inputStream =
				_dataFileProvider.getDataFileInputStream()) {

			_dataset = StreamFactory.create(IOUtils.toByteArray(inputStream));

			_provider = new Provider(
				_dataset, _fiftyOneDegreesConfiguration.cacheSize());
		}
		catch (IOException ioException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to load 51Degrees provider data", ioException);
			}

			throw new IllegalStateException(ioException);
		}
	}

	@Deactivate
	protected void deactivate() throws IOException {
		_fiftyOneDegreesConfiguration = null;
		_provider = null;

		if (_dataset != null) {
			_dataset.close();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FiftyOneDegreesEngineProxy.class);

	@Reference
	private DataFileProvider _dataFileProvider;

	private Dataset _dataset;
	private volatile FiftyOneDegreesConfiguration _fiftyOneDegreesConfiguration;
	private Provider _provider;

}