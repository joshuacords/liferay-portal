/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.exporter;

import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.exception.DBProviderNotAvailableException;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class DataPartitioningExporterFactoryTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test(expected = DBProviderNotAvailableException.class)
	public void testGetDataPartitioningExporter() throws Exception {
		DataPartitioningExporterFactory.getDataPartitioningExporter();
	}

}