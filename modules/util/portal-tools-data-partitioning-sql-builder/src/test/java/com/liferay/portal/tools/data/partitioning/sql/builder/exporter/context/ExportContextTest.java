/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.exporter.context;

import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class ExportContextTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testConstructorWithCatalogName() {
		String catalogName = "catalogName";

		ExportContext exportProcess = new ExportContext(
			catalogName, null, null, null, "schemaName", true);

		Assert.assertNotEquals(
			exportProcess.getSchemaName(), exportProcess.getCatalogName());
		Assert.assertEquals(catalogName, exportProcess.getCatalogName());
	}

	@Test
	public void testConstructorWithoutCatalogName() {
		ExportContext exportProcess = new ExportContext(
			null, null, null, "schemaName", true);

		Assert.assertEquals(
			exportProcess.getSchemaName(), exportProcess.getCatalogName());
	}

}