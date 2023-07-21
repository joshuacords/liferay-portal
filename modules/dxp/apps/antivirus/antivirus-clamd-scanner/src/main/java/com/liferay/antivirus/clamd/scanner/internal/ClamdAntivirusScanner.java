/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.antivirus.clamd.scanner.internal;

import com.liferay.antivirus.clamd.scanner.internal.configuration.ClamdAntivirusScannerConfiguration;
import com.liferay.document.library.kernel.antivirus.AntivirusScanner;
import com.liferay.document.library.kernel.antivirus.AntivirusScannerException;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import fi.solita.clamav.ClamAVClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

/**
 * @author Shuyang Zhou
 */
@Component(
	configurationPid = "com.liferay.antivirus.clamd.scanner.internal.configuration.ClamdAntivirusScannerConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE,
	service = AntivirusScanner.class
)
public class ClamdAntivirusScanner implements AntivirusScanner {

	@Override
	public boolean isActive() {
		return true;
	}

	@Override
	public void scan(byte[] bytes) throws AntivirusScannerException {
		try {
			if (!ClamAVClient.isCleanReply(_clamdClient.scan(bytes))) {
				throw new AntivirusScannerException(
					"Virus detected in byte array",
					AntivirusScannerException.VIRUS_DETECTED);
			}
		}
		catch (IOException ioException) {
			throw new AntivirusScannerException(
				AntivirusScannerException.PROCESS_FAILURE, ioException);
		}
	}

	@Override
	public void scan(File file) throws AntivirusScannerException {
		try (InputStream inputStream = new FileInputStream(file)) {
			if (!ClamAVClient.isCleanReply(_clamdClient.scan(inputStream))) {
				throw new AntivirusScannerException(
					"Virus detected in " + file.getAbsolutePath(),
					AntivirusScannerException.VIRUS_DETECTED);
			}
		}
		catch (IOException ioException) {
			throw new AntivirusScannerException(
				AntivirusScannerException.PROCESS_FAILURE, ioException);
		}
	}

	@Override
	public void scan(InputStream inputStream) throws AntivirusScannerException {
		try {
			if (!ClamAVClient.isCleanReply(_clamdClient.scan(inputStream))) {
				throw new AntivirusScannerException(
					"Virus detected in input stream",
					AntivirusScannerException.VIRUS_DETECTED);
			}
		}
		catch (IOException ioException) {
			throw new AntivirusScannerException(
				AntivirusScannerException.PROCESS_FAILURE, ioException);
		}
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		ClamdAntivirusScannerConfiguration clamdAntivirusScannerConfiguration =
			ConfigurableUtil.createConfigurable(
				ClamdAntivirusScannerConfiguration.class, properties);

		_clamdClient = new ClamAVClient(
			clamdAntivirusScannerConfiguration.hostname(),
			clamdAntivirusScannerConfiguration.port(),
			clamdAntivirusScannerConfiguration.timeout());
	}

	private ClamAVClient _clamdClient;

}