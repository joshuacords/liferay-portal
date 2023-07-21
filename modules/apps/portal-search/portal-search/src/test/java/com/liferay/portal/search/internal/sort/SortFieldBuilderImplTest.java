/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.sort;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.test.util.PropsTestUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.contributor.ContributorConstants;
import com.liferay.portal.search.contributor.sort.SortFieldNameTranslator;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author Michael C. Han
 */
public class SortFieldBuilderImplTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		_sortFieldBuilderImpl = new SortFieldBuilderImpl();

		Mockito.when(
			_indexerRegistry.getIndexer(Mockito.anyString())
		).thenAnswer(
			invocation -> _indexer
		);

		_sortFieldBuilderImpl.indexerRegistry = _indexerRegistry;
		_sortFieldBuilderImpl.props = PropsTestUtil.setProps(
			"index.sortable.text.fields",
			new String[] {
				"firstName", "jobTitle", "lastName", "name", "screenName",
				"title"
			});

		_sortFieldBuilderImpl.activate();
	}

	@Test
	public void testGetSortFieldWithNoSortFieldTranslator() {
		Mockito.when(
			_indexer.getSortField(Mockito.anyString())
		).thenAnswer(
			invocation -> invocation.getArgumentAt(0, String.class)
		);

		String sortFieldName = _sortFieldBuilderImpl.getSortField(
			"modelClassName", "testField");

		Assert.assertEquals("testField", sortFieldName);

		sortFieldName = _sortFieldBuilderImpl.getSortField(
			"modelClassName", "firstName");

		Assert.assertEquals(
			Field.getSortableFieldName("firstName"), sortFieldName);

		sortFieldName = _sortFieldBuilderImpl.getSortField(
			"modelClassName", "testField", Sort.DOUBLE_TYPE);

		Assert.assertEquals(
			Field.getSortableFieldName("testField"), sortFieldName);

		sortFieldName = _sortFieldBuilderImpl.getSortField(
			"modelClassName", "testField", Sort.FLOAT_TYPE);

		Assert.assertEquals(
			Field.getSortableFieldName("testField"), sortFieldName);

		sortFieldName = _sortFieldBuilderImpl.getSortField(
			"modelClassName", "testField", Sort.INT_TYPE);

		Assert.assertEquals(
			Field.getSortableFieldName("testField"), sortFieldName);

		sortFieldName = _sortFieldBuilderImpl.getSortField(
			"modelClassName", "testField", Sort.LONG_TYPE);

		Assert.assertEquals(
			Field.getSortableFieldName("testField"), sortFieldName);
	}

	@Test
	public void testGetSortFieldWithSortFieldTranslator() {
		SortFieldNameTranslator sortFieldNameTranslator = Mockito.mock(
			SortFieldNameTranslator.class);

		Mockito.when(
			sortFieldNameTranslator.getSortFieldName(Mockito.anyString())
		).then(
			invocation -> {
				String orderByCol = invocation.getArgumentAt(0, String.class);

				return StringUtil.upperCaseFirstLetter(orderByCol);
			}
		);

		Map<String, Object> properties = HashMapBuilder.<String, Object>put(
			ContributorConstants.ENTRY_CLASS_NAME_PROPERTY_KEY, "modelClassName"
		).build();

		_sortFieldBuilderImpl.addSortFieldNameTranslator(
			sortFieldNameTranslator, properties);

		Mockito.when(
			_indexer.getSortField(Mockito.anyString())
		).thenAnswer(
			invocation -> invocation.getArgumentAt(0, String.class)
		);

		String sortFieldName = _sortFieldBuilderImpl.getSortField(
			"modelClassName", "testField");

		Assert.assertEquals("TestField", sortFieldName);

		sortFieldName = _sortFieldBuilderImpl.getSortField(
			"modelClassName", "firstName");

		Assert.assertEquals("FirstName", sortFieldName);

		sortFieldName = _sortFieldBuilderImpl.getSortField(
			"modelClassName", "testField", Sort.DOUBLE_TYPE);

		Assert.assertEquals(
			Field.getSortableFieldName("testField"), sortFieldName);

		sortFieldName = _sortFieldBuilderImpl.getSortField(
			"modelClassName", "testField", Sort.FLOAT_TYPE);

		Assert.assertEquals(
			Field.getSortableFieldName("testField"), sortFieldName);

		sortFieldName = _sortFieldBuilderImpl.getSortField(
			"modelClassName", "testField", Sort.INT_TYPE);

		Assert.assertEquals(
			Field.getSortableFieldName("testField"), sortFieldName);

		sortFieldName = _sortFieldBuilderImpl.getSortField(
			"modelClassName", "testField", Sort.LONG_TYPE);

		Assert.assertEquals(
			Field.getSortableFieldName("testField"), sortFieldName);
	}

	@Mock
	private Indexer _indexer;

	@Mock
	private IndexerRegistry _indexerRegistry;

	private SortFieldBuilderImpl _sortFieldBuilderImpl;

}