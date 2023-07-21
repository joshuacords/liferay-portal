/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.impl;

import com.liferay.osb.koroneiki.root.util.ModelKeyGenerator;
import com.liferay.osb.koroneiki.taproot.constants.ContactRoleSystem;
import com.liferay.osb.koroneiki.taproot.exception.ContactRoleNameException;
import com.liferay.osb.koroneiki.taproot.exception.ContactRoleSystemException;
import com.liferay.osb.koroneiki.taproot.exception.ContactRoleTypeException;
import com.liferay.osb.koroneiki.taproot.exception.RequiredContactRoleException;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.service.base.ContactRoleLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.instances.service.PortalInstancesLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.taproot.model.ContactRole",
	service = AopService.class
)
public class ContactRoleLocalServiceImpl
	extends ContactRoleLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	public ContactRole addContactRole(
			long userId, String name, String description, String type)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		validate(0, name, type);

		long contactRoleId = counterLocalService.increment();

		ContactRole contactRole = contactRolePersistence.create(contactRoleId);

		contactRole.setCompanyId(user.getCompanyId());
		contactRole.setUserId(userId);
		contactRole.setContactRoleKey(
			ModelKeyGenerator.generate(contactRoleId));
		contactRole.setName(name);
		contactRole.setDescription(description);
		contactRole.setType(type);

		contactRole = contactRolePersistence.update(contactRole);

		// Resources

		resourceLocalService.addResources(
			contactRole.getCompanyId(), 0, userId, ContactRole.class.getName(),
			contactRole.getContactRoleId(), false, false, false);

		return contactRole;
	}

	public void checkMemberRoles() throws PortalException {
		long companyId = _portalInstancesLocalService.getDefaultCompanyId();

		User user = userLocalService.getDefaultUser(companyId);

		for (com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type
				type :
					com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.
						Type.values()) {

			ContactRole contactRole = contactRolePersistence.fetchByN_T(
				ContactRoleSystem.NAME_MEMBER, type.toString());

			if (contactRole == null) {
				contactRole = addContactRole(
					user.getUserId(), ContactRoleSystem.NAME_MEMBER,
					StringPool.BLANK, type.toString());

				contactRole.setSystem(true);

				contactRole = contactRolePersistence.update(contactRole);

				contactRoleLocalService.reindex(contactRole.getContactRoleId());
			}

			_memberContactRoles.put(type.toString(), contactRole);
		}
	}

	@Override
	public ContactRole deleteContactRole(ContactRole contactRole)
		throws PortalException {

		if (contactRole.isSystem()) {
			throw new ContactRoleSystemException();
		}

		int contactAccountRolesCount =
			contactAccountRolePersistence.countByContactRoleId(
				contactRole.getContactRoleId());

		int contactTeamRolesCount =
			contactTeamRolePersistence.countByContactRoleId(
				contactRole.getContactRoleId());

		if ((contactAccountRolesCount != 0) || (contactTeamRolesCount != 0)) {
			throw new RequiredContactRoleException.
				MustNotDeleteContactRoleReferencedByContact(
					contactRole.getContactRoleId());
		}

		// Resources

		resourceLocalService.deleteResource(
			contactRole.getCompanyId(), ContactRole.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, contactRole.getContactRoleId());

		return contactRolePersistence.remove(contactRole);
	}

	@Override
	public ContactRole deleteContactRole(long contactRoleId)
		throws PortalException {

		return deleteContactRole(
			contactRoleLocalService.getContactRole(contactRoleId));
	}

	public ContactRole fetchContactRole(String name, String type) {
		return contactRolePersistence.fetchByN_T(name, type);
	}

	public List<ContactRole> getContactAccountContactRoles(
		long accountId, long contactId, String[] types, int start, int end) {

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("accountContact", new Long[] {accountId, contactId});

		return contactRoleFinder.findByN_T(null, types, params, start, end);
	}

	public int getContactAccountContactRolesCount(
		long accountId, long contactId, String[] types) {

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("accountContact", new Long[] {accountId, contactId});

		return contactRoleFinder.countByN_T(null, types, params);
	}

	public List<ContactRole> getContactContactRoles(
		long contactId, int start, int end) {

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("contact", new Long[] {contactId, contactId});

		return contactRoleFinder.findByN_T(
			null, new String[] {null}, params, start, end);
	}

	public ContactRole getContactRole(String contactRoleKey)
		throws PortalException {

		return contactRolePersistence.findByContactRoleKey(contactRoleKey);
	}

	public ContactRole getContactRole(String name, String type)
		throws PortalException {

		return contactRolePersistence.findByN_T(name, type);
	}

	public List<ContactRole> getContactRoles(String type, int start, int end) {
		return contactRolePersistence.findByType(type, start, end);
	}

	public int getContactRolesCount(String type) {
		return contactRolePersistence.countByType(type);
	}

	public List<ContactRole> getContactTeamContactRoles(
		long teamId, long contactId, String[] types, int start, int end) {

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("teamContact", new Long[] {teamId, contactId});

		return contactRoleFinder.findByN_T(null, types, params, start, end);
	}

	public int getContactTeamContactRolesCount(
		long teamId, long contactId, String[] types) {

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("teamContact", new Long[] {teamId, contactId});

		return contactRoleFinder.countByN_T(null, types, params);
	}

	public ContactRole getMemberContactRole(String type) {
		return _memberContactRoles.get(type);
	}

	@Indexable(type = IndexableType.REINDEX)
	public ContactRole reindex(long contactRoleId) throws PortalException {
		return contactRolePersistence.findByPrimaryKey(contactRoleId);
	}

	public Hits search(
			long companyId, String type, String keywords, int start, int end,
			Sort sort)
		throws PortalException {

		try {
			Indexer<ContactRole> indexer =
				IndexerRegistryUtil.nullSafeGetIndexer(ContactRole.class);

			SearchContext searchContext = new SearchContext();

			searchContext.setAndSearch(false);

			Map<String, Serializable> attributes = new HashMap<>();

			attributes.put("description", keywords);
			attributes.put("name", keywords);

			searchContext.setAttributes(attributes);

			BooleanQuery booleanQuery = new BooleanQueryImpl();

			booleanQuery.addExactTerm("type", StringUtil.toLowerCase(type));

			BooleanClause booleanClause = BooleanClauseFactoryUtil.create(
				booleanQuery, BooleanClauseOccur.MUST.getName());

			searchContext.setBooleanClauses(
				new BooleanClause[] {booleanClause});

			searchContext.setCompanyId(companyId);
			searchContext.setEnd(end);

			if (sort != null) {
				searchContext.setSorts(sort);
			}

			searchContext.setStart(start);

			QueryConfig queryConfig = searchContext.getQueryConfig();

			queryConfig.setHighlightEnabled(false);
			queryConfig.setScoreEnabled(false);

			return indexer.search(searchContext);
		}
		catch (Exception exception) {
			throw new PortalException(exception);
		}
	}

	@Indexable(type = IndexableType.REINDEX)
	public ContactRole updateContactRole(
			long contactRoleId, String name, String description)
		throws PortalException {

		ContactRole contactRole = contactRolePersistence.findByPrimaryKey(
			contactRoleId);

		validate(contactRoleId, name, contactRole.getType());

		contactRole.setName(name);
		contactRole.setDescription(description);

		return contactRolePersistence.update(contactRole);
	}

	protected void validate(long contactRoleId, String name, String type)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new ContactRoleNameException();
		}

		com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type
			contactRoleType =
				com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type.
					create(type);

		if (contactRoleType == null) {
			throw new ContactRoleTypeException();
		}

		ContactRole contactRole = contactRolePersistence.fetchByN_T(name, type);

		if ((contactRole != null) &&
			(contactRole.getContactRoleId() != contactRoleId)) {

			throw new ContactRoleNameException.MustNotBeDuplicate(name, type);
		}
	}

	private final Map<String, ContactRole> _memberContactRoles =
		new HashMap<>();

	@Reference
	private PortalInstancesLocalService _portalInstancesLocalService;

}