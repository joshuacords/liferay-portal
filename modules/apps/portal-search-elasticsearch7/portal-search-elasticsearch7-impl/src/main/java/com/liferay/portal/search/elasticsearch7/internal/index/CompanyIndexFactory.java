/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.index;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalRunMode;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration;
import com.liferay.portal.search.elasticsearch7.internal.index.contributor.IndexContributorReceiver;
import com.liferay.portal.search.elasticsearch7.internal.settings.SettingsBuilder;
import com.liferay.portal.search.elasticsearch7.internal.util.LogUtil;
import com.liferay.portal.search.elasticsearch7.internal.util.ResourceUtil;
import com.liferay.portal.search.index.IndexNameBuilder;
import com.liferay.portal.search.spi.model.index.contributor.IndexContributor;
import com.liferay.portal.search.spi.settings.IndexSettingsContributor;
import com.liferay.portal.search.spi.settings.IndexSettingsHelper;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;

import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 */
@Component(
	configurationPid = "com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration",
	immediate = true,
	service = {IndexContributorReceiver.class, IndexFactory.class}
)
public class CompanyIndexFactory
	implements IndexContributorReceiver, IndexFactory {

	@Override
	public void addIndexContributor(IndexContributor indexContributor) {
		_indexContributors.add(indexContributor);
	}

	@Override
	public void createIndices(AdminClient adminClient, long companyId) {
		IndicesAdminClient indicesAdminClient = adminClient.indices();

		_indicesAdminClient = indicesAdminClient;

		String indexName = getIndexName(companyId);

		if (hasIndex(indicesAdminClient, indexName)) {
			return;
		}

		createIndex(indexName, indicesAdminClient);

		_companyIds.add(companyId);
	}

	@Override
	public void deleteIndices(AdminClient adminClient, long companyId) {
		IndicesAdminClient indicesAdminClient = adminClient.indices();

		String indexName = getIndexName(companyId);

		if (!hasIndex(indicesAdminClient, indexName)) {
			return;
		}

		executeIndexContributorsBeforeRemove(indexName);

		DeleteIndexRequestBuilder deleteIndexRequestBuilder =
			indicesAdminClient.prepareDelete(indexName);

		ActionResponse actionResponse = deleteIndexRequestBuilder.get();

		LogUtil.logActionResponse(_log, actionResponse);

		_companyIds.remove(companyId);
	}

	@Override
	public void removeIndexContributor(IndexContributor indexContributor) {
		_indexContributors.remove(indexContributor);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		ElasticsearchConfiguration elasticsearchConfiguration =
			ConfigurableUtil.createConfigurable(
				ElasticsearchConfiguration.class, properties);

		setAdditionalIndexConfigurations(
			elasticsearchConfiguration.additionalIndexConfigurations());
		setAdditionalTypeMappings(
			elasticsearchConfiguration.additionalTypeMappings());
		setIndexNumberOfReplicas(
			elasticsearchConfiguration.indexNumberOfReplicas());
		setIndexNumberOfShards(
			elasticsearchConfiguration.indexNumberOfShards());
		setOverrideTypeMappings(
			elasticsearchConfiguration.overrideTypeMappings());
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addElasticsearchIndexSettingsContributor(
		com.liferay.portal.search.elasticsearch7.settings.
			IndexSettingsContributor indexSettingsContributor) {

		_elasticsearchIndexSettingsContributors.add(indexSettingsContributor);

		processContributions(
			(indexName, liferayDocumentTypeFactory) ->
				indexSettingsContributor.contribute(
					indexName, liferayDocumentTypeFactory));
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addIndexSettingsContributor(
		IndexSettingsContributor indexSettingsContributor) {

		_indexSettingsContributors.add(indexSettingsContributor);

		processContributions(
			(indexName, liferayDocumentTypeFactory) ->
				indexSettingsContributor.contribute(
					indexName, liferayDocumentTypeFactory));
	}

	protected void addLiferayDocumentTypeMappings(
		CreateIndexRequestBuilder createIndexRequestBuilder,
		LiferayDocumentTypeFactory liferayDocumentTypeFactory) {

		if (Validator.isNotNull(_overrideTypeMappings)) {
			liferayDocumentTypeFactory.createLiferayDocumentTypeMappings(
				createIndexRequestBuilder, _overrideTypeMappings);
		}
		else {
			liferayDocumentTypeFactory.createRequiredDefaultTypeMappings(
				createIndexRequestBuilder);
		}
	}

	protected void createIndex(
		String indexName, IndicesAdminClient indicesAdminClient) {

		CreateIndexRequestBuilder createIndexRequestBuilder =
			indicesAdminClient.prepareCreate(indexName);

		LiferayDocumentTypeFactory liferayDocumentTypeFactory =
			new LiferayDocumentTypeFactory(indicesAdminClient, jsonFactory);

		setSettings(createIndexRequestBuilder, liferayDocumentTypeFactory);

		addLiferayDocumentTypeMappings(
			createIndexRequestBuilder, liferayDocumentTypeFactory);

		CreateIndexResponse createIndexResponse =
			createIndexRequestBuilder.get();

		LogUtil.logActionResponse(_log, createIndexResponse);

		updateLiferayDocumentType(indexName, liferayDocumentTypeFactory);

		executeIndexContributorsAfterCreate(indexName);
	}

	protected void executeIndexContributorAfterCreate(
		IndexContributor indexContributor, String indexName) {

		try {
			indexContributor.onAfterCreate(indexName);
		}
		catch (Throwable t) {
			_log.error(
				StringBundler.concat(
					"Unable to apply contributor ", indexContributor,
					"to index ", indexName),
				t);
		}
	}

	protected void executeIndexContributorBeforeRemove(
		IndexContributor indexContributor, String indexName) {

		try {
			indexContributor.onBeforeRemove(indexName);
		}
		catch (Throwable t) {
			_log.error(
				StringBundler.concat(
					"Unable to apply contributor ", indexContributor,
					" when removing index ", indexName),
				t);
		}
	}

	protected void executeIndexContributorsAfterCreate(String indexName) {
		for (IndexContributor indexContributor : _indexContributors) {
			executeIndexContributorAfterCreate(indexContributor, indexName);
		}
	}

	protected void executeIndexContributorsBeforeRemove(String indexName) {
		for (IndexContributor indexContributor : _indexContributors) {
			executeIndexContributorBeforeRemove(indexContributor, indexName);
		}
	}

	protected String getIndexName(long companyId) {
		return indexNameBuilder.getIndexName(companyId);
	}

	protected boolean hasIndex(
		IndicesAdminClient indicesAdminClient, String indexName) {

		IndicesExistsRequestBuilder indicesExistsRequestBuilder =
			indicesAdminClient.prepareExists(indexName);

		IndicesExistsResponse indicesExistsResponse =
			indicesExistsRequestBuilder.get();

		return indicesExistsResponse.isExists();
	}

	protected void loadAdditionalIndexConfigurations(
		SettingsBuilder settingsBuilder) {

		settingsBuilder.loadFromSource(_additionalIndexConfigurations);
	}

	protected void loadAdditionalTypeMappings(
		String indexName,
		LiferayDocumentTypeFactory liferayDocumentTypeFactory) {

		if (Validator.isNull(_additionalTypeMappings)) {
			return;
		}

		liferayDocumentTypeFactory.addTypeMappings(
			indexName, _additionalTypeMappings);
	}

	protected void loadDefaultIndexSettings(SettingsBuilder settingsBuilder) {
		Settings.Builder builder = settingsBuilder.getBuilder();

		String defaultIndexSettings = ResourceUtil.getResourceAsString(
			getClass(), "/META-INF/index-settings-defaults.json");

		builder.loadFromSource(defaultIndexSettings, XContentType.JSON);
	}

	protected void loadIndexConfigurations(SettingsBuilder settingsBuilder) {
		settingsBuilder.put("index.number_of_replicas", _indexNumberOfReplicas);
		settingsBuilder.put("index.number_of_shards", _indexNumberOfShards);
	}

	protected void loadIndexSettingsContributors(
		final Settings.Builder builder) {

		com.liferay.portal.search.elasticsearch7.settings.IndexSettingsHelper
			elasticsearchIndexSettingsHelper = (setting, value) -> builder.put(
				setting, value);

		for (com.liferay.portal.search.elasticsearch7.settings.
				IndexSettingsContributor indexSettingsContributor :
					_elasticsearchIndexSettingsContributors) {

			indexSettingsContributor.populate(elasticsearchIndexSettingsHelper);
		}

		IndexSettingsHelper indexSettingsHelper =
			(String setting, String value) -> builder.put(setting, value);

		for (IndexSettingsContributor indexSettingsContributor1 :
				_indexSettingsContributors) {

			indexSettingsContributor1.populate(indexSettingsHelper);
		}
	}

	protected void loadTestModeIndexSettings(SettingsBuilder settingsBuilder) {
		if (!PortalRunMode.isTestMode()) {
			return;
		}

		settingsBuilder.put("index.refresh_interval", "1ms");
		settingsBuilder.put("index.search.slowlog.threshold.fetch.warn", "-1");
		settingsBuilder.put("index.search.slowlog.threshold.query.warn", "-1");
		settingsBuilder.put("index.translog.sync_interval", "100ms");
	}

	protected void loadTypeMappingsContributors(
		String indexName,
		LiferayDocumentTypeFactory liferayDocumentTypeFactory) {

		for (com.liferay.portal.search.elasticsearch7.settings.
				IndexSettingsContributor elasticsearchIndexSettingsContributor :
					_elasticsearchIndexSettingsContributors) {

			elasticsearchIndexSettingsContributor.contribute(
				indexName, liferayDocumentTypeFactory);
		}

		for (IndexSettingsContributor indexSettingsContributor :
				_indexSettingsContributors) {

			indexSettingsContributor.contribute(
				indexName, liferayDocumentTypeFactory);
		}
	}

	protected void processContributions(
		BiConsumer<String, LiferayDocumentTypeFactory> biConsumer) {

		if (Validator.isNotNull(_overrideTypeMappings) ||
			(_indicesAdminClient == null)) {

			if (_log.isInfoEnabled()) {
				_log.info("Skipping index settings contributor");
			}

			return;
		}

		LiferayDocumentTypeFactory liferayDocumentTypeFactory =
			new LiferayDocumentTypeFactory(_indicesAdminClient, jsonFactory);

		for (Long companyId : _companyIds) {
			biConsumer.accept(
				getIndexName(companyId), liferayDocumentTypeFactory);
		}
	}

	protected void removeElasticsearchIndexSettingsContributor(
		com.liferay.portal.search.elasticsearch7.settings.
			IndexSettingsContributor indexSettingsContributor) {

		_elasticsearchIndexSettingsContributors.remove(
			indexSettingsContributor);
	}

	protected void removeIndexSettingsContributor(
		IndexSettingsContributor indexSettingsContributor) {

		_indexSettingsContributors.remove(indexSettingsContributor);
	}

	protected void setAdditionalIndexConfigurations(
		String additionalIndexConfigurations) {

		_additionalIndexConfigurations = additionalIndexConfigurations;
	}

	protected void setAdditionalTypeMappings(String additionalTypeMappings) {
		_additionalTypeMappings = additionalTypeMappings;
	}

	protected void setIndexNumberOfReplicas(String indexNumberOfReplicas) {
		_indexNumberOfReplicas = indexNumberOfReplicas;
	}

	protected void setIndexNumberOfShards(String indexNumberOfShards) {
		_indexNumberOfShards = indexNumberOfShards;
	}

	protected void setOverrideTypeMappings(String overrideTypeMappings) {
		_overrideTypeMappings = overrideTypeMappings;
	}

	protected void setSettings(
		CreateIndexRequestBuilder createIndexRequestBuilder,
		LiferayDocumentTypeFactory liferayDocumentTypeFactory) {

		Settings.Builder builder = Settings.builder();

		liferayDocumentTypeFactory.createRequiredDefaultAnalyzers(builder);

		SettingsBuilder settingsBuilder = new SettingsBuilder(builder);

		loadDefaultIndexSettings(settingsBuilder);

		loadTestModeIndexSettings(settingsBuilder);

		loadIndexConfigurations(settingsBuilder);

		loadAdditionalIndexConfigurations(settingsBuilder);

		loadIndexSettingsContributors(builder);

		if (Validator.isNotNull(builder.get("index.number_of_replicas"))) {
			builder.put("index.auto_expand_replicas", false);
		}

		createIndexRequestBuilder.setSettings(builder);
	}

	protected void updateLiferayDocumentType(
		String indexName,
		LiferayDocumentTypeFactory liferayDocumentTypeFactory) {

		if (Validator.isNotNull(_overrideTypeMappings)) {
			return;
		}

		loadAdditionalTypeMappings(indexName, liferayDocumentTypeFactory);

		loadTypeMappingsContributors(indexName, liferayDocumentTypeFactory);

		liferayDocumentTypeFactory.createOptionalDefaultTypeMappings(indexName);
	}

	@Reference
	protected IndexNameBuilder indexNameBuilder;

	@Reference
	protected JSONFactory jsonFactory;

	private static final Log _log = LogFactoryUtil.getLog(
		CompanyIndexFactory.class);

	private volatile String _additionalIndexConfigurations;
	private String _additionalTypeMappings;
	private final Set<Long> _companyIds = new HashSet<>();
	private final Set
		<com.liferay.portal.search.elasticsearch7.settings.
			IndexSettingsContributor> _elasticsearchIndexSettingsContributors =
				new ConcurrentSkipListSet<>();
	private final List<IndexContributor> _indexContributors =
		new CopyOnWriteArrayList<>();
	private String _indexNumberOfReplicas;
	private String _indexNumberOfShards;
	private final Set<IndexSettingsContributor> _indexSettingsContributors =
		ConcurrentHashMap.newKeySet();
	private IndicesAdminClient _indicesAdminClient;
	private String _overrideTypeMappings;

}