/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.dao.orm;

import com.liferay.petra.executor.PortalExecutorManager;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.registry.BasicRegistryImpl;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author André de Oliveira
 */
public class IndexableActionableDynamicQueryTest {

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		RegistryUtil.setRegistry(createRegistry());

		indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setIndexWriterHelper(indexWriterHelper);
	}

	@Test
	public void testAddDocuments() throws Exception {
		indexableActionableDynamicQuery.setInterval(1);

		indexableActionableDynamicQuery.addDocuments(document1, document2);

		verifyDocumentsUpdated(document1, document2);
	}

	@Test
	public void testAddDocumentsWithinInterval() throws Exception {
		indexableActionableDynamicQuery.setInterval(3);

		indexableActionableDynamicQuery.addDocuments(document1, document2);

		verifyNoDocumentsUpdated();

		indexableActionableDynamicQuery.addDocuments(document3);

		verifyDocumentsUpdated(document1, document2, document3);
	}

	protected Registry createRegistry() {
		Registry registry = new BasicRegistryImpl();

		registry.registerService(
			PortalExecutorManager.class,
			Mockito.mock(PortalExecutorManager.class));

		return registry;
	}

	protected void verifyDocumentsUpdated(Document... documents)
		throws Exception {

		Mockito.verify(
			indexWriterHelper
		).updateDocuments(
			null, 0, Arrays.asList(documents), false
		);
	}

	protected void verifyNoDocumentsUpdated() {
		Mockito.verifyZeroInteractions(indexWriterHelper);
	}

	@Mock
	protected Document document1;

	@Mock
	protected Document document2;

	@Mock
	protected Document document3;

	protected IndexableActionableDynamicQuery indexableActionableDynamicQuery;

	@Mock
	protected IndexWriterHelper indexWriterHelper;

}