/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.spi.model.index.contributor.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Joshua Cords
 */
@RunWith(Arquillian.class)
public class ExternalReferenceCodeModelDocumentContributorTest
	extends BaseModelDocumentContributorTest {

	@Test
	public void testContributeExternalReferenceCode() throws Exception {
		assertERCSearch(blogsEntry, _EXTERNAL_REFERENCE_CODE_FIELD_NAME);
		assertERCSearch(journalArticle, _EXTERNAL_REFERENCE_CODE_FIELD_NAME);
		assertERCSearch(journalFolder, _EXTERNAL_REFERENCE_CODE_FIELD_NAME);
		assertERCSearch(user, _EXTERNAL_REFERENCE_CODE_FIELD_NAME);
	}

	private static final String _EXTERNAL_REFERENCE_CODE_FIELD_NAME =
		"externalReferenceCode";

}