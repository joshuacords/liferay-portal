/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.connection;

import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.io.IOException;

import java.nio.file.Path;

import org.elasticsearch.Version;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author André de Oliveira
 */
public class EmbeddedElasticsearchPluginManagerTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		setUpPluginManagerFactory();
		setUpPluginZipFactory();
	}

	@Test
	public void testInstallForTheFirstTime() throws Exception {
		install("analysis-kuromoji");

		Mockito.verify(
			_pluginManager
		).install(
			Mockito.eq("analysis-kuromoji")
		);

		Mockito.verify(
			_pluginZipFactory
		).createPluginZip(
			"/plugins/org.elasticsearch.plugin.analysis.kuromoji-" +
				Version.CURRENT + ".zip"
		);

		Mockito.verify(
			_pluginZip
		).delete();
	}

	@Test
	public void testInstallForTheSecondTime() throws Exception {
		setUpAlreadyInstalled(true);

		install();

		Mockito.verify(
			_pluginManager, Mockito.never()
		).install(
			Mockito.anyString()
		);

		Mockito.verify(
			_pluginZipFactory, Mockito.never()
		).createPluginZip(
			Mockito.anyString()
		);

		Mockito.verifyZeroInteractions(_pluginZip);
	}

	@Test
	public void testInstallOverObsoleteVersion() throws Exception {
		setUpAlreadyInstalled(false);

		install();

		Mockito.verify(
			_pluginManager
		).remove(
			Mockito.eq(_PLUGIN_NAME)
		);
	}

	@Test
	public void testPluginZipIsDeletedOnError() throws Exception {
		IOException ioException1 = new IOException();

		setUpBrokenDownloadAndExtract(ioException1);

		try {
			install();

			Assert.fail();
		}
		catch (IOException ioException2) {
			Assert.assertSame(ioException1, ioException2);
		}

		Mockito.verify(
			_pluginZip
		).delete();
	}

	@Test
	public void testSurviveConcurrentInstallInSameDir() throws Exception {
		setUpBrokenDownloadAndExtract(
			new IOException(
				"already exists. To update the plugin, uninstall it first"));

		install();

		Mockito.verify(
			_pluginZip
		).delete();
	}

	protected Path createPath(String pluginName) {
		Path path = Mockito.mock(Path.class);

		Mockito.doReturn(
			true
		).when(
			path
		).endsWith(
			pluginName
		);

		return path;
	}

	protected void install() throws Exception {
		install(_PLUGIN_NAME);
	}

	protected void install(String pluginName) throws Exception {
		EmbeddedElasticsearchPluginManager embeddedElasticsearchPluginManager =
			new EmbeddedElasticsearchPluginManager(
				pluginName, _PLUGINS_PATH_STRING, _pluginManagerFactory,
				_pluginZipFactory);

		embeddedElasticsearchPluginManager.removeObsoletePlugin();

		embeddedElasticsearchPluginManager.install();
	}

	protected void setUpAlreadyInstalled(boolean currentVersion)
		throws Exception {

		Path path = createPath(_PLUGIN_NAME);

		Mockito.doReturn(
			new Path[] {path}
		).when(
			_pluginManager
		).getInstalledPluginsPaths();

		Mockito.doReturn(
			currentVersion
		).when(
			_pluginManager
		).isCurrentVersion(
			path
		);
	}

	protected void setUpBrokenDownloadAndExtract(IOException ioException)
		throws Exception {

		Mockito.doThrow(
			ioException
		).when(
			_pluginManager
		).install(
			Mockito.anyString()
		);
	}

	protected void setUpPluginManagerFactory() throws Exception {
		Mockito.doReturn(
			new Path[0]
		).when(
			_pluginManager
		).getInstalledPluginsPaths();

		Mockito.doReturn(
			_pluginManager
		).when(
			_pluginManagerFactory
		).createPluginManager();

		Mockito.doReturn(
			_pluginManager
		).when(
			_pluginManagerFactory
		).createPluginManager(
			Mockito.<PluginZip>any()
		);
	}

	protected void setUpPluginZipFactory() throws Exception {
		Mockito.doReturn(
			_pluginZip
		).when(
			_pluginZipFactory
		).createPluginZip(
			Mockito.anyString()
		);
	}

	private static final String _PLUGIN_NAME = RandomTestUtil.randomString();

	private static final String _PLUGINS_PATH_STRING = ".";

	@Mock
	private PluginManager _pluginManager;

	@Mock
	private PluginManagerFactory _pluginManagerFactory;

	@Mock
	private PluginZip _pluginZip;

	@Mock
	private PluginZipFactory _pluginZipFactory;

}