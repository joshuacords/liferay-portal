/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.display.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.info.display.contributor.field.InfoDisplayContributorField;
import com.liferay.info.display.contributor.field.InfoDisplayContributorFieldTracker;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Jürgen Kappler
 */
@RunWith(Arquillian.class)
public class AssetDisplayContributorTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testAssetDisplayContributorField() {
		List<InfoDisplayContributorField> infoDisplayContributorFields =
			_infoDisplayContributorFieldTracker.getInfoDisplayContributorFields(
				AssetEntry.class.getName());

		Stream<InfoDisplayContributorField> stream =
			infoDisplayContributorFields.stream();

		List<String> infoDisplayContributorFieldKeys = stream.map(
			infoDisplayContributorField -> infoDisplayContributorField.getKey()
		).collect(
			Collectors.toList()
		);

		Assert.assertTrue(
			infoDisplayContributorFieldKeys.contains("assetEntryAdapterKey"));
	}

	@Inject
	private InfoDisplayContributorFieldTracker
		_infoDisplayContributorFieldTracker;

}