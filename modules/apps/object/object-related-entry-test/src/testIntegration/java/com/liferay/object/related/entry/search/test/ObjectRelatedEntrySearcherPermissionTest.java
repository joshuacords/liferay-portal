/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.related.entry.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.object.constants.ObjectDefinitionConstants;
import com.liferay.object.constants.ObjectEntryFolderConstants;
import com.liferay.object.constants.ObjectRelationshipConstants;
import com.liferay.object.field.builder.TextObjectFieldBuilder;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.related.entry.search.ObjectRelatedEntrySearchResponse;
import com.liferay.object.related.entry.search.ObjectRelatedEntrySearchResult;
import com.liferay.object.related.entry.search.ObjectRelatedEntrySearcher;
import com.liferay.object.relationship.util.ObjectRelationshipUtil;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectRelationshipLocalService;
import com.liferay.object.test.util.ObjectDefinitionTestUtil;
import com.liferay.object.test.util.ObjectRelationshipTestUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.context.ContextUserReplace;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.RoleTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.test.rule.SearchTestRule;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.io.Serializable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Permission posture of related object entries, the point PTR-9173 raises
 * about the mechanism inverting the ACL: a related entry document carries the
 * ACL of its parent as its pre filter, exactly like a comment or an
 * attachment, while DefaultSearchResultPermissionFilter still checks the child
 * object entry's own VIEW permission and the parent's visibility for every hit
 * individually, in the portal, before anything is folded. Every test searches
 * as a regular user with a dedicated role.
 *
 * @author Joshua Cords
 */
@RunWith(Arquillian.class)
public class ObjectRelatedEntrySearcherPermissionTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE,
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_examObjectDefinition = _publishObjectDefinition("title");
		_questionObjectDefinition = _publishObjectDefinition("questionText");

		_objectRelationship = ObjectRelationshipTestUtil.addObjectRelationship(
			_objectRelationshipLocalService, _examObjectDefinition,
			_questionObjectDefinition,
			ObjectRelationshipConstants.DELETION_TYPE_DISASSOCIATE,
			StringUtil.randomId());

		_role = RoleTestUtil.addRole(RoleConstants.TYPE_REGULAR);

		_user = UserTestUtil.addUser();

		_userLocalService.addRoleUser(_role.getRoleId(), _user.getUserId());

		_examObjectEntry = _addObjectEntry(
			_examObjectDefinition,
			HashMapBuilder.<String, Serializable>put(
				"title", RandomTestUtil.randomString()
			).build());

		_keyword = RandomTestUtil.randomString();

		_questionObjectEntry = _addObjectEntry(
			_questionObjectDefinition,
			HashMapBuilder.<String, Serializable>put(
				ObjectRelationshipUtil.getObjectRelationshipFieldName(
					_examObjectDefinition, _objectRelationship.getName()),
				_examObjectEntry.getObjectEntryId()
			).put(
				"questionText", _keyword
			).build());
	}

	@After
	public void tearDown() throws Exception {
		_objectDefinitionLocalService.deleteObjectDefinition(
			_questionObjectDefinition);
		_objectDefinitionLocalService.deleteObjectDefinition(
			_examObjectDefinition);
	}

	/**
	 * The parent is moved to the trash while the child stays approved and
	 * keeps pointing at it. The child document passes the pre filter and its
	 * own VIEW check, and is then vetoed by RelatedEntryIndexer#
	 * isVisibleRelatedEntry, one hit at a time, because its parent is no
	 * longer visible. Comments on a trashed entry disappear the same way.
	 */
	@Test
	public void testSearchWithParentInTrash() throws Exception {
		_grantView(_examObjectDefinition, _examObjectEntry);
		_grantView(_questionObjectDefinition, _questionObjectEntry);

		_reindex();

		_assertRelatedEntryFound();

		_objectEntryLocalService.moveObjectEntryToTrash(
			TestPropsValues.getUserId(),
			_objectEntryLocalService.getObjectEntry(
				_examObjectEntry.getObjectEntryId()),
			ServiceContextTestUtil.getServiceContext());

		_assertNothingFound();
	}

	/**
	 * The user can view the child object entry but not the parent. Because
	 * the child document is a related entry, its roleId pre filter was
	 * computed from the parent's ACL when it was indexed, so the search
	 * engine never returns it. The user's permission on the child alone is
	 * not enough, which is the posture comments and attachments have today.
	 */
	@Test
	public void testSearchWithViewOnChildOnly() throws Exception {
		_grantView(_questionObjectDefinition, _questionObjectEntry);

		_reindex();

		_assertNothingFound();
	}

	/**
	 * The user can view both the parent and the child, so the child hit
	 * survives the pre filter, the per hit VIEW check and the visibility veto,
	 * and is folded into its parent.
	 */
	@Test
	public void testSearchWithViewOnParentAndChild() throws Exception {
		_grantView(_examObjectDefinition, _examObjectEntry);
		_grantView(_questionObjectDefinition, _questionObjectEntry);

		_reindex();

		_assertRelatedEntryFound();
	}

	/**
	 * The user can view the parent but not the child. The child document
	 * passes the pre filter with the parent's ACL, and is then dropped by
	 * DefaultSearchResultPermissionFilter, which checks every hit against the
	 * child object entry's own VIEW permission before the fold runs. Nothing
	 * about the parent leaks through a child the user may not see.
	 */
	@Test
	public void testSearchWithViewOnParentOnly() throws Exception {
		_grantView(_examObjectDefinition, _examObjectEntry);

		_reindex();

		_assertNothingFound();
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	private ObjectEntry _addObjectEntry(
			ObjectDefinition objectDefinition, Map<String, Serializable> values)
		throws Exception {

		return _objectEntryLocalService.addObjectEntry(
			0, TestPropsValues.getUserId(),
			objectDefinition.getObjectDefinitionId(),
			ObjectEntryFolderConstants.PARENT_OBJECT_ENTRY_FOLDER_ID_DEFAULT,
			null, values, ServiceContextTestUtil.getServiceContext());
	}

	private void _assertNothingFound() throws Exception {
		ObjectRelatedEntrySearchResponse objectRelatedEntrySearchResponse =
			_search();

		List<ObjectRelatedEntrySearchResult> objectRelatedEntrySearchResults =
			objectRelatedEntrySearchResponse.
				getObjectRelatedEntrySearchResults();

		Assert.assertTrue(
			objectRelatedEntrySearchResults.toString(),
			objectRelatedEntrySearchResults.isEmpty());
	}

	private void _assertRelatedEntryFound() throws Exception {
		ObjectRelatedEntrySearchResponse objectRelatedEntrySearchResponse =
			_search();

		List<ObjectRelatedEntrySearchResult> objectRelatedEntrySearchResults =
			objectRelatedEntrySearchResponse.
				getObjectRelatedEntrySearchResults();

		Assert.assertEquals(
			objectRelatedEntrySearchResults.toString(), 1,
			objectRelatedEntrySearchResults.size());

		ObjectRelatedEntrySearchResult objectRelatedEntrySearchResult =
			objectRelatedEntrySearchResults.get(0);

		Assert.assertEquals(
			_examObjectDefinition.getClassName(),
			objectRelatedEntrySearchResult.getClassName());
		Assert.assertEquals(
			_examObjectEntry.getObjectEntryId(),
			objectRelatedEntrySearchResult.getClassPK());
		Assert.assertNull(objectRelatedEntrySearchResult.getSearchHit());

		List<SearchHit> relatedEntrySearchHits =
			objectRelatedEntrySearchResult.getRelatedEntrySearchHits();

		Assert.assertEquals(
			relatedEntrySearchHits.toString(), 1,
			relatedEntrySearchHits.size());
	}

	private void _grantView(
			ObjectDefinition objectDefinition, ObjectEntry objectEntry)
		throws Exception {

		_resourcePermissionLocalService.setResourcePermissions(
			TestPropsValues.getCompanyId(), objectDefinition.getClassName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			String.valueOf(objectEntry.getObjectEntryId()), _role.getRoleId(),
			new String[] {ActionKeys.VIEW});
	}

	private ObjectDefinition _publishObjectDefinition(String objectFieldName)
		throws Exception {

		return ObjectDefinitionTestUtil.publishObjectDefinition(
			Collections.singletonList(
				new TextObjectFieldBuilder(
				).indexed(
					true
				).indexedAsKeyword(
					false
				).labelMap(
					RandomTestUtil.randomLocaleStringMap()
				).name(
					objectFieldName
				).build()),
			ObjectDefinitionConstants.SCOPE_COMPANY);
	}

	private void _reindex() throws Exception {
		Indexer<ObjectEntry> examIndexer = IndexerRegistryUtil.getIndexer(
			_examObjectDefinition.getClassName());

		examIndexer.reindex(
			_objectEntryLocalService.getObjectEntry(
				_examObjectEntry.getObjectEntryId()));

		Indexer<ObjectEntry> questionIndexer = IndexerRegistryUtil.getIndexer(
			_questionObjectDefinition.getClassName());

		questionIndexer.reindex(
			_objectEntryLocalService.getObjectEntry(
				_questionObjectEntry.getObjectEntryId()));
	}

	private ObjectRelatedEntrySearchResponse _search() throws Exception {
		long userId = _user.getUserId();

		try (ContextUserReplace contextUserReplace = new ContextUserReplace(
				_user)) {

			return _objectRelatedEntrySearcher.search(
				_searchRequestBuilderFactory.builder(
				).companyId(
					TestPropsValues.getCompanyId()
				).entryClassNames(
					_examObjectDefinition.getClassName()
				).locale(
					LocaleUtil.US
				).queryString(
					_keyword
				).withSearchContext(
					searchContext -> searchContext.setUserId(userId)
				).build());
		}
	}

	private ObjectDefinition _examObjectDefinition;
	private ObjectEntry _examObjectEntry;
	private String _keyword;

	@Inject
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Inject
	private ObjectEntryLocalService _objectEntryLocalService;

	@Inject
	private ObjectRelatedEntrySearcher _objectRelatedEntrySearcher;

	private ObjectRelationship _objectRelationship;

	@Inject
	private ObjectRelationshipLocalService _objectRelationshipLocalService;

	private ObjectDefinition _questionObjectDefinition;
	private ObjectEntry _questionObjectEntry;

	@Inject
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@DeleteAfterTestRun
	private Role _role;

	@Inject
	private SearchRequestBuilderFactory _searchRequestBuilderFactory;

	@DeleteAfterTestRun
	private User _user;

	@Inject
	private UserLocalService _userLocalService;

}