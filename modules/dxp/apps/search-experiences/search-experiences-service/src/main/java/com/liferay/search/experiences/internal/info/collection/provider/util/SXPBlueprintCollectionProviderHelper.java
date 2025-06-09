/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.search.experiences.internal.info.collection.provider.util;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.object.constants.ObjectDefinitionConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.asset.AssetSubtypeIdentifier;
import com.liferay.portal.search.asset.AssetSubtypeIdentifierBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Joshua Cords
 */
public class SXPBlueprintCollectionProviderHelper {

	public SXPBlueprintCollectionProviderHelper(
		AssetSubtypeIdentifierBuilder assetSubtypeIdentifierBuilder) {

		_assetSubtypeIdentifierBuilder = assetSubtypeIdentifierBuilder;
	}

	public String enhanceConfiguration(String configuration)
		throws PortalException {

		try {
			JSONObject configurationJSONObject =
				JSONFactoryUtil.createJSONObject(configuration);

			JSONObject generalConfigurationJSONObject =
				configurationJSONObject.getJSONObject("generalConfiguration");

			if (generalConfigurationJSONObject == null) {
				return configuration;
			}

			JSONArray searchableAssetTypesJSONArray =
				(JSONArray)generalConfigurationJSONObject.get(
					"searchableAssetTypes");

			if (searchableAssetTypesJSONArray == null) {
				return _setCollectionProviderType(
					configurationJSONObject, generalConfigurationJSONObject,
					AssetEntry.class.getName());
			}

			String[] searchableAssetTypesArray = JSONUtil.toStringArray(
				searchableAssetTypesJSONArray);

			if (searchableAssetTypesArray.length == 0) {
				return _setCollectionProviderType(
					configurationJSONObject, generalConfigurationJSONObject,
					AssetEntry.class.getName());
			}

			if (searchableAssetTypesArray.length == 1) {
				return _setCollectionProviderType(
					configurationJSONObject, generalConfigurationJSONObject,
					searchableAssetTypesArray[0]);
			}

			AssetSubtypeIdentifier assetSubtypeIdentifier1 =
				_assetSubtypeIdentifierBuilder.searchableAssetType(
					searchableAssetTypesArray[0]
				).build();

			for (int i = 1; i < searchableAssetTypesArray.length; i++) {
				AssetSubtypeIdentifier assetSubtypeIdentifier2 =
					_assetSubtypeIdentifierBuilder.searchableAssetType(
						searchableAssetTypesArray[i]
					).build();

				if (!StringUtil.equals(
						assetSubtypeIdentifier1.getClassName(),
						assetSubtypeIdentifier2.getClassName())) {

					return _setCollectionProviderType(
						configurationJSONObject, generalConfigurationJSONObject,
						AssetEntry.class.getName());
				}
			}

			return _setCollectionProviderType(
				configurationJSONObject, generalConfigurationJSONObject,
				assetSubtypeIdentifier1.getClassName());
		}
		catch (Exception exception) {
			throw new PortalException(exception);
		}
	}

	private String _setCollectionProviderType(
		JSONObject configurationJSONObject,
		JSONObject generalConfigurationJSONObject, String type) {

		AssetSubtypeIdentifier assetSubtypeIdentifier =
			_assetSubtypeIdentifierBuilder.searchableAssetType(
				type
			).build();

		String className = assetSubtypeIdentifier.getClassName();

		if (!_collectionProviderTypes.contains(className) &&
			!className.startsWith(
				ObjectDefinitionConstants.
					CLASS_NAME_PREFIX_CUSTOM_OBJECT_DEFINITION)) {

			type = AssetEntry.class.getName();
		}

		generalConfigurationJSONObject.put("collectionProviderType", type);

		return configurationJSONObject.toString();
	}

	private final AssetSubtypeIdentifierBuilder _assetSubtypeIdentifierBuilder;
	private final List<String> _collectionProviderTypes = new ArrayList<>(
		Arrays.asList(
			BlogsEntry.class.getName(), DLFileEntry.class.getName(),
			JournalArticle.class.getName(), KBArticle.class.getName()));

}