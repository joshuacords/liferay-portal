/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.connection;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.lucene.util.SetOnce;

import org.elasticsearch.analysis.common.CommonAnalysisPlugin;
import org.elasticsearch.common.logging.LogConfigurator;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.mapper.MapperExtrasPlugin;
import org.elasticsearch.index.reindex.ReindexPlugin;
import org.elasticsearch.node.InternalSettingsPreparer;
import org.elasticsearch.node.Node;
import org.elasticsearch.painless.PainlessPlugin;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.transport.Netty4Plugin;

/**
 * @author André de Oliveira
 */
public class EmbeddedElasticsearchNode extends Node {

	public static Node newInstance(Settings settings) {
		Environment environment = InternalSettingsPreparer.prepareEnvironment(
			settings, Collections.emptyMap(), null, () -> "liferay");

		List<Class<? extends Plugin>> classpathPlugins = Arrays.asList(
			CommonAnalysisPlugin.class, MapperExtrasPlugin.class,
			Netty4Plugin.class, PainlessPlugin.class, ReindexPlugin.class);

		try {
			LogConfigurator.registerErrorListener();

			LogConfigurator.configure(environment);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to find log4j2.properties", exception);
			}
		}

		return PluginJarConflictCheckSuppression.execute(
			() -> new EmbeddedElasticsearchNode(environment, classpathPlugins));
	}

	public EmbeddedElasticsearchNode(
		Environment environment,
		Collection<Class<? extends Plugin>> classpathPlugins) {

		super(environment, classpathPlugins, false);
	}

	protected void registerDerivedNodeNameWithLogger(String nodeName) {
		try {
			LogConfigurator.setNodeName(nodeName);
		}
		catch (SetOnce.AlreadySetException alreadySetException) {
			if (_log.isDebugEnabled()) {
				_log.debug("Node name has already been set");
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EmbeddedElasticsearchNode.class);

}