/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.impl;

import com.liferay.osb.koroneiki.phytohormone.service.EntitlementLocalService;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.root.util.ModelKeyGenerator;
import com.liferay.osb.koroneiki.taproot.exception.AccountCodeException;
import com.liferay.osb.koroneiki.taproot.exception.AccountNameException;
import com.liferay.osb.koroneiki.taproot.exception.AccountParentException;
import com.liferay.osb.koroneiki.taproot.exception.RequiredAccountException;
import com.liferay.osb.koroneiki.taproot.internal.util.DefaultTeamManager;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.AccountField;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.AccountFieldLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.osb.koroneiki.taproot.service.base.AccountLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.taproot.model.Account",
	service = AopService.class
)
public class AccountLocalServiceImpl extends AccountLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Account addAccount(Account account) {
		account = super.addAccount(account);

		try {
			addResources(
				account.getCompanyId(), account.getUserId(),
				account.getAccountId());

			_defaultTeamManager.sync(account);

			if (account.getParentAccountId() > 0) {
				accountLocalService.reindex(account.getParentAccountId());
			}
		}
		catch (PortalException portalException) {
			throw new SystemException(portalException);
		}

		return account;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Account addAccount(
			long userId, long parentAccountId, String name, String code,
			String description, long logoId, String contactEmailAddress,
			String profileEmailAddress, String phoneNumber, String faxNumber,
			String website, String tier, String region, String dataRegion,
			String language, boolean internal, String status,
			List<AccountField> accountFields)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		code = StringUtil.toUpperCase(code);

		validate(0, 0, name, code);

		if (Validator.isNull(status)) {
			status =
				com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account.Status.
					ACTIVE.toString();
		}

		long accountId = counterLocalService.increment();

		Account account = accountPersistence.create(accountId);

		account.setCompanyId(user.getCompanyId());
		account.setUserId(userId);
		account.setAccountKey(ModelKeyGenerator.generate(accountId));
		account.setParentAccountId(parentAccountId);
		account.setName(name);
		account.setCode(code);
		account.setDescription(description);
		account.setLogoId(logoId);
		account.setContactEmailAddress(contactEmailAddress);
		account.setProfileEmailAddress(profileEmailAddress);
		account.setPhoneNumber(phoneNumber);
		account.setFaxNumber(faxNumber);
		account.setWebsite(website);
		account.setTier(tier);
		account.setRegion(region);
		account.setDataRegion(dataRegion);
		account.setLanguage(language);
		account.setInternal(internal);
		account.setStatus(status);

		account = accountPersistence.update(account);

		addResources(account.getCompanyId(), userId, account.getAccountId());

		for (AccountField accountField : accountFields) {
			_accountFieldLocalService.addAccountField(
				userId, accountId, accountField.getName(),
				accountField.getValue());
		}

		_defaultTeamManager.sync(account);

		if (parentAccountId > 0) {
			accountLocalService.reindex(parentAccountId);
		}

		return account;
	}

	@Override
	public Account deleteAccount(Account account) throws PortalException {
		if (accountPersistence.countByParentAccountId(account.getAccountId()) >
				0) {

			throw new RequiredAccountException();
		}

		// Account notes

		accountNotePersistence.removeByAccountId(account.getAccountId());

		// Contact account roles

		contactAccountRolePersistence.removeByAccountId(account.getAccountId());

		// Entitlements

		long classNameId = classNameLocalService.getClassNameId(Account.class);

		_entitlementLocalService.deleteEntitlements(
			classNameId, account.getAccountId());

		// External links

		_externalLinkLocalService.deleteExternalLinks(
			classNameId, account.getAccountId());

		// Resources

		resourceLocalService.deleteResource(
			account.getCompanyId(), Account.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, account.getAccountId());

		// Teams

		List<Team> teams = teamPersistence.findByAccountId(
			account.getAccountId());

		for (Team team : teams) {
			_teamLocalService.deleteTeam(team);
		}

		// Team account roles

		teamAccountRolePersistence.removeByAccountId(account.getAccountId());

		return accountPersistence.remove(account);
	}

	@Override
	public Account deleteAccount(long accountId) throws PortalException {
		return deleteAccount(accountLocalService.getAccount(accountId));
	}

	public Account fetchAccount(String accountKey) {
		return accountPersistence.fetchByAccountKey(accountKey);
	}

	public Account fetchAccountByCode(String code) {
		return accountPersistence.fetchByCode(code);
	}

	public Account getAccount(String accountKey) throws PortalException {
		return accountPersistence.findByAccountKey(accountKey);
	}

	public List<Account> getAccounts(long parentAccountId, int start, int end) {
		return accountPersistence.findByParentAccountId(
			parentAccountId, start, end);
	}

	public int getAccountsCount(long parentAccountId) {
		return accountPersistence.countByParentAccountId(parentAccountId);
	}

	public List<Account> getContactAccounts(
		long contactId, int start, int end) {

		return accountFinder.findByContact(contactId, start, end);
	}

	public int getContactAccountsCount(long contactId) {
		return accountFinder.countByContact(contactId);
	}

	public List<Account> getTeamAccounts(long teamId, int start, int end) {
		return accountFinder.findByTeam(teamId, start, end);
	}

	public int getTeamAccountsCount(long teamId) {
		return accountFinder.countByTeam(teamId);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Account reindex(long accountId) throws PortalException {
		return accountPersistence.findByPrimaryKey(accountId);
	}

	public Hits search(
			long companyId, String keywords, int start, int end, Sort sort)
		throws PortalException {

		try {
			Indexer<Account> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				Account.class);

			SearchContext searchContext = new SearchContext();

			searchContext.setAndSearch(false);

			Map<String, Serializable> attributes = new HashMap<>();

			attributes.put("code", keywords);
			attributes.put("contactUuids", keywords);
			attributes.put("description", keywords);
			attributes.put("externalLinkDomains", keywords);
			attributes.put("externalLinkEntityIds", keywords);
			attributes.put("externalLinkEntityNames", keywords);
			attributes.put("name", keywords);
			attributes.put("productEntryKeys", keywords);

			searchContext.setAttributes(attributes);

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
	public Account updateAccount(
			long userId, long accountId, long parentAccountId, String name,
			String code, String description, long logoId,
			String contactEmailAddress, String profileEmailAddress,
			String phoneNumber, String faxNumber, String website, String tier,
			String region, String dataRegion, String language, boolean internal,
			String status, List<AccountField> accountFields)
		throws PortalException {

		code = StringUtil.toUpperCase(code);

		validate(accountId, parentAccountId, name, code);

		Account account = accountPersistence.findByPrimaryKey(accountId);

		long oldParentAccountId = account.getParentAccountId();

		account.setParentAccountId(parentAccountId);
		account.setName(name);
		account.setCode(code);
		account.setDescription(description);
		account.setLogoId(logoId);
		account.setContactEmailAddress(contactEmailAddress);
		account.setProfileEmailAddress(profileEmailAddress);
		account.setPhoneNumber(phoneNumber);
		account.setFaxNumber(faxNumber);
		account.setWebsite(website);
		account.setTier(tier);
		account.setRegion(region);
		account.setDataRegion(dataRegion);
		account.setLanguage(language);
		account.setInternal(internal);
		account.setStatus(status);

		account = accountPersistence.update(account);

		Map<String, AccountField> accountFieldsMap = getAccountFieldsMap(
			accountId);

		for (AccountField accountField : accountFields) {
			AccountField curAccountField = accountFieldsMap.remove(
				accountField.getName());

			if (curAccountField == null) {
				_accountFieldLocalService.addAccountField(
					userId, accountId, accountField.getName(),
					accountField.getValue());
			}
			else {
				_accountFieldLocalService.updateAccountField(
					curAccountField.getAccountFieldId(),
					accountField.getValue());
			}
		}

		for (AccountField accountField : accountFieldsMap.values()) {
			_accountFieldLocalService.deleteAccountField(
				accountField.getAccountFieldId());
		}

		_defaultTeamManager.sync(account);

		if (oldParentAccountId != parentAccountId) {
			if (oldParentAccountId > 0) {
				accountLocalService.reindex(oldParentAccountId);
			}

			if (parentAccountId > 0) {
				accountLocalService.reindex(parentAccountId);
			}
		}

		return account;
	}

	protected void addResources(long companyId, long userId, long accountId)
		throws PortalException {

		resourceLocalService.addResources(
			companyId, 0, userId, Account.class.getName(), accountId, false,
			false, false);
	}

	protected Map<String, AccountField> getAccountFieldsMap(long accountId) {
		Map<String, AccountField> accountFieldsMap = new HashMap<>();

		List<AccountField> accountFields =
			_accountFieldLocalService.getAccountFields(accountId);

		for (AccountField accountField : accountFields) {
			accountFieldsMap.put(accountField.getName(), accountField);
		}

		return accountFieldsMap;
	}

	protected void validate(
			long accountId, long parentAccountId, String name, String code)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new AccountNameException();
		}

		if (Validator.isNotNull(code)) {
			Account account = accountPersistence.fetchByCode(code);

			if ((account != null) && (account.getAccountId() != accountId)) {
				throw new AccountCodeException.MustNotBeDuplicate(code);
			}
		}

		if (parentAccountId != 0) {
			if (accountId == parentAccountId) {
				throw new AccountParentException.MustNotBeSelf(
					String.valueOf(accountId));
			}

			Account parentAccount = getAccount(parentAccountId);

			while (parentAccount.getParentAccountId() != 0) {
				if (parentAccount.getParentAccountId() == accountId) {
					throw new AccountParentException.MustNotBeDescendant(
						String.valueOf(accountId));
				}

				parentAccount = getAccount(parentAccount.getParentAccountId());
			}
		}
	}

	@Reference
	private AccountFieldLocalService _accountFieldLocalService;

	@Reference
	private DefaultTeamManager _defaultTeamManager;

	@Reference
	private EntitlementLocalService _entitlementLocalService;

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

	@Reference
	private TeamLocalService _teamLocalService;

}