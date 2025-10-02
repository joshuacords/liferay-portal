/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.test.rule;

import com.liferay.portal.configuration.module.configuration.ConfigurationProvider;
import com.liferay.portal.configuration.test.util.ConfigurationTestUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.test.rule.ClassTestRule;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.search.configuration.SemanticSearchConfiguration;
import com.liferay.portal.search.configuration.SemanticSearchConfigurationProvider;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.InjectTestBag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.runner.Description;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

/**
 * @author Joshua Cords
 */
public class SemanticSearchTestRule extends ClassTestRule<Void> {

	public SemanticSearchTestRule() {
		_injectTestBag = _createInjectTestBag();
	}

	@Override
	public void afterClass(Description description, Void unused)
		throws Throwable {

		try {
			_restoreOriginalConfiguration();
		}
		finally {
			try {
				_stopDockerContainer();
			}
			finally {
				_injectTestBag.resetFields();
			}
		}
	}

	@Override
	public Void beforeClass(Description description) throws Throwable {
		_injectTestBag.injectFields();

		_companyId = TestPropsValues.getCompanyId();

		_backupOriginalConfiguration();

		_ensureDockerImageBuilt();

		_startDockerContainer();

		resetProviderConfiguration();

		return null;
	}

	public void configureProvider(
			List<String> languageIds, List<String> modelClassNames)
		throws Exception {

		_currentLanguageIds = new ArrayList<>(languageIds);
		_currentModelClassNames = new ArrayList<>(modelClassNames);

		_updateConfiguration();
	}

	public void resetProviderConfiguration() throws Exception {
		_currentLanguageIds = new ArrayList<>();
		_currentModelClassNames = new ArrayList<>();

		_updateConfiguration();
	}

	private void _backupOriginalConfiguration() throws Exception {
		Configuration configuration = _configurationAdmin.getConfiguration(
			_SEMANTIC_SEARCH_CONFIGURATION_PID, "?");

		Dictionary<String, Object> properties = configuration.getProperties();

		if (properties == null) {
			_originalProperties = null;

			return;
		}

		_originalProperties = new LinkedHashMap<>();

		Enumeration<String> keysEnumeration = properties.keys();

		while (keysEnumeration.hasMoreElements()) {
			String key = keysEnumeration.nextElement();

			Object value = properties.get(key);

			if (value instanceof String[]) {
				_originalProperties.put(key, ((String[])value).clone());
			}
			else {
				_originalProperties.put(key, value);
			}
		}
	}

	private void _buildDockerImage(Path tempDirectory) throws Exception {
		Files.write(
			tempDirectory.resolve("config.yml"),
			_TXT_AI_CONFIG.getBytes(StandardCharsets.UTF_8));

		Files.write(
			tempDirectory.resolve("Dockerfile"),
			_DOCKERFILE_CONTENT.getBytes(StandardCharsets.UTF_8));

		_runCommand(
			new String[] {"docker", "build", "-t", _DOCKER_IMAGE_TAG, "."},
			tempDirectory);
	}

	private InjectTestBag _createInjectTestBag() {
		try {
			return new InjectTestBag(SemanticSearchTestRule.class, this);
		}
		catch (RuntimeException runtimeException) {
			throw runtimeException;
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	private void _deleteDirectory(Path directoryPath) throws IOException {
		if (!Files.exists(directoryPath)) {
			return;
		}

		Files.walk(
			directoryPath
		).sorted(
			(path1, path2) -> path2.compareTo(path1)
		).forEach(
			path -> {
				try {
					Files.deleteIfExists(path);
				}
				catch (IOException ioException) {
					throw new RuntimeException(ioException);
				}
			}
		);
	}

	private void _ensureDockerImageBuilt() throws Exception {
		synchronized (_dockerImageLock) {
			if (_dockerImageBuilt) {
				return;
			}

			Path tempDirectory = Files.createTempDirectory("txtai");

			try {
				_buildDockerImage(tempDirectory);
			}
			finally {
				_deleteDirectory(tempDirectory);
			}

			_dockerImageBuilt = true;
		}
	}

	private boolean _equals(String[] array1, String[] array2) {
		if (array1 == array2) {
			return true;
		}

		if ((array1 == null) || (array2 == null) ||
			(array1.length != array2.length)) {

			return false;
		}

		for (int i = 0; i < array1.length; i++) {
			if (!array1[i].equals(array2[i])) {
				return false;
			}
		}

		return true;
	}

	private int _findAvailablePort() throws IOException {
		try (java.net.ServerSocket serverSocket = new java.net.ServerSocket(
				0)) {

			serverSocket.setReuseAddress(true);

			return serverSocket.getLocalPort();
		}
	}

	private String _getProviderConfigurationJSON() {
		return JSONUtil.put(
			"attributes",
			JSONUtil.put(
				"hostAddress", "http://localhost:" + _hostPort
			).put(
				"maxCharacterCount", 500
			)
		).put(
			"embeddingVectorDimensions", _EMBEDDING_VECTOR_DIMENSIONS
		).put(
			"languageIds",
			JSONUtil.putAll(_currentLanguageIds.toArray(new String[0]))
		).put(
			"modelClassNames",
			JSONUtil.putAll(_currentModelClassNames.toArray(new String[0]))
		).put(
			"providerName", "txtai"
		).toString();
	}

	private void _restoreOriginalConfiguration() throws Exception {
		if (_originalProperties == null) {
			_configurationProvider.deleteCompanyConfiguration(
				SemanticSearchConfiguration.class, _companyId);

			return;
		}

		Dictionary<String, Object> dictionary =
			HashMapDictionaryBuilder.<String, Object>putAll(
				_originalProperties
			).build();

		ConfigurationTestUtil.updateConfiguration(
			_SEMANTIC_SEARCH_CONFIGURATION_PID,
			() -> _configurationProvider.saveCompanyConfiguration(
				SemanticSearchConfiguration.class, _companyId, dictionary));
	}

	private String _runCommand(String[] command, Path workingDirectory)
		throws Exception {

		ProcessBuilder processBuilder = new ProcessBuilder(command);

		if (workingDirectory != null) {
			processBuilder.directory(workingDirectory.toFile());
		}

		processBuilder.redirectErrorStream(true);

		Process process = processBuilder.start();

		String output;

		try (InputStream inputStream = process.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
				inputStream, StandardCharsets.UTF_8);
			BufferedReader bufferedReader = new BufferedReader(
				inputStreamReader)) {

			StringBuilder sb = new StringBuilder();

			String line;

			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
			}

			output = sb.toString();
		}

		int exitCode = process.waitFor();

		if (exitCode != 0) {
			throw new RuntimeException(
				"Command failed (exit " + exitCode + "): " +
					String.join(" ", command) + System.lineSeparator() +
						output);
		}

		return output;
	}

	private void _startDockerContainer() throws Exception {
		_hostPort = _findAvailablePort();

		_containerName = _CONTAINER_NAME_PREFIX + UUID.randomUUID();

		_runCommand(
			new String[] {
				"docker", "run", "-d", "--name", _containerName, "-p",
				_hostPort + ":8000", _DOCKER_IMAGE_TAG
			},
			null);

		_waitForTxtai();
	}

	private void _stopDockerContainer() throws Exception {
		if (_containerName == null) {
			return;
		}

		try {
			_runCommand(
				new String[] {"docker", "rm", "-f", _containerName}, null);
		}
		catch (Exception exception) {
			throw new RuntimeException(
				"Unable to stop txtai container", exception);
		}
	}

	private void _updateConfiguration() throws Exception {
		String[] providerConfigurationJSONs = new String[0];
		boolean textEmbeddingsEnabled = false;

		if (!_currentLanguageIds.isEmpty() &&
			!_currentModelClassNames.isEmpty()) {

			providerConfigurationJSONs = new String[] {
				_getProviderConfigurationJSON()
			};

			textEmbeddingsEnabled = true;
		}

		Dictionary<String, Object> dictionary =
			HashMapDictionaryBuilder.<String, Object>put(
				"textEmbeddingCacheTimeout",
				String.valueOf(_CACHE_TIMEOUT_SECONDS)
			).put(
				"textEmbeddingProviderConfigurationJSONs",
				providerConfigurationJSONs
			).put(
				"textEmbeddingsEnabled", textEmbeddingsEnabled
			).build();

		ConfigurationTestUtil.updateConfiguration(
			_SEMANTIC_SEARCH_CONFIGURATION_PID,
			() -> _configurationProvider.saveCompanyConfiguration(
				SemanticSearchConfiguration.class, _companyId, dictionary));

		_waitForConfiguration(
			providerConfigurationJSONs, textEmbeddingsEnabled);
	}

	private void _waitForConfiguration(
			String[] providerConfigurationJSONs, boolean textEmbeddingsEnabled)
		throws Exception {

		long timeout =
			System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(60);

		while (true) {
			SemanticSearchConfiguration semanticSearchConfiguration =
				_semanticSearchConfigurationProvider.getCompanyConfiguration(
					_companyId);

			if ((semanticSearchConfiguration.textEmbeddingsEnabled() ==
					textEmbeddingsEnabled) &&
				_equals(
					semanticSearchConfiguration.
						textEmbeddingProviderConfigurationJSONs(),
					providerConfigurationJSONs)) {

				return;
			}

			if (System.currentTimeMillis() > timeout) {
				throw new IllegalStateException(
					"Timed out waiting for SemanticSearch configuration update");
			}

			Thread.sleep(1000);
		}
	}

	private void _waitForTxtai() throws Exception {
		long timeout =
			System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(60);

		while (true) {
			try (java.net.Socket socket = new java.net.Socket()) {
				socket.connect(
					new java.net.InetSocketAddress("localhost", _hostPort),
					2000);

				return;
			}
			catch (IOException ioException) {
			}

			if (System.currentTimeMillis() > timeout) {
				throw new IllegalStateException(
					"Timed out waiting for txtai service to start");
			}

			Thread.sleep(1000);
		}
	}

	private static final int _CACHE_TIMEOUT_SECONDS = 604800;

	private static final String _CONTAINER_NAME_PREFIX = "liferay_txtai_";

	private static final String _DOCKER_IMAGE_TAG = "liferay-txtai-test";

	private static final String _DOCKERFILE_CONTENT =
		"FROM neuml/txtai-cpu:5.1.0\n\n" + "COPY config.yml .\n\n" +
			"ENV CONFIG \"config.yml\"\n\n" +
				"ENTRYPOINT [\"uvicorn\", \"--host\", \"0.0.0.0\", " +
					"\"txtai.api:app\"]\n\n" +
						"RUN python -c \"from txtai.api import API; API('config.yml', " +
							"False)\"\n";

	private static final int _EMBEDDING_VECTOR_DIMENSIONS = 768;

	private static final String _SEMANTIC_SEARCH_CONFIGURATION_PID =
		"com.liferay.portal.search.configuration.SemanticSearchConfiguration";

	private static final String _TXT_AI_CONFIG =
		"embeddings:\n" +
			"    path: sentence-transformers/msmarco-distilbert-base-dot-prod-v3\n" +
				"path: /tmp/index\n" + "writable: False\n";

	private static volatile boolean _dockerImageBuilt;
	private static final Object _dockerImageLock = new Object();

	private long _companyId;

	@Inject
	private ConfigurationAdmin _configurationAdmin;

	@Inject
	private ConfigurationProvider _configurationProvider;

	private String _containerName;
	private List<String> _currentLanguageIds = new ArrayList<>();
	private List<String> _currentModelClassNames = new ArrayList<>();
	private int _hostPort;
	private final InjectTestBag _injectTestBag;
	private Map<String, Object> _originalProperties;

	@Inject
	private SemanticSearchConfigurationProvider
		_semanticSearchConfigurationProvider;

}