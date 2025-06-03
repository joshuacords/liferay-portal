/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.search.experiences.internal.upgrade.v3_2_0;

import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.asset.AssetSubtypeIdentifierBuilder;
import com.liferay.search.experiences.internal.info.collection.provider.util.SXPBlueprintCollectionProviderHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Joshua Cords
 */
public class SXPBlueprintCollectionProviderUpgradeProcess
	extends UpgradeProcess {

	public SXPBlueprintCollectionProviderUpgradeProcess(
		AssetSubtypeIdentifierBuilder assetSubtypeIdentifierBuilder) {

		_assetSubtypeIdentifierBuilder = assetSubtypeIdentifierBuilder;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!FeatureFlagManagerUtil.isEnabled("LPS-129412")) {
			return;
		}

		_upgradeSXPBlueprints();
	}

	private String _setAsCollectionProvider(String configurationJSON)
		throws Exception {

		if (Validator.isBlank(configurationJSON)) {
			return configurationJSON;
		}

		JSONObject configurationJSONObject = JSONFactoryUtil.createJSONObject(
			configurationJSON);

		JSONObject generalConfigurationJSONObject =
			configurationJSONObject.getJSONObject("generalConfiguration");

		if (generalConfigurationJSONObject == null) {
			return configurationJSON;
		}

		generalConfigurationJSONObject.put("collectionProvider", true);

		return configurationJSONObject.toString();
	}

	private void _upgradeSXPBlueprints() throws Exception {
		SXPBlueprintCollectionProviderHelper
			sxpBlueprintCollectionProviderHelper =
				new SXPBlueprintCollectionProviderHelper(
					_assetSubtypeIdentifierBuilder);

		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
				"select configurationJSON,sxpBlueprintId from SXPBlueprint");
			PreparedStatement preparedStatement2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update SXPBlueprint set configurationJSON = ? where " +
						"sxpBlueprintId = ?")) {

			try (ResultSet resultSet1 = preparedStatement1.executeQuery()) {
				while (resultSet1.next()) {
					String configuration = _setAsCollectionProvider(
						resultSet1.getString("configurationJSON"));

					preparedStatement2.setString(
						1,
						sxpBlueprintCollectionProviderHelper.
							enhanceConfiguration(configuration));

					preparedStatement2.setLong(
						2, resultSet1.getLong("sxpBlueprintId"));

					preparedStatement2.addBatch();
				}

				preparedStatement2.executeBatch();
			}
		}
	}

	private final AssetSubtypeIdentifierBuilder _assetSubtypeIdentifierBuilder;

}