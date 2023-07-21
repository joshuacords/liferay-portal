/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.koroneiki.web.service.internal;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.AccountResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.AccountSerDes;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.NoSuchContactException;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration",
	immediate = true, service = AccountWebService.class
)
public class AccountWebServiceImpl
	extends BaseWebService implements AccountWebService {

	public Account addAccount(
			String agentName, String agentUID, Account account)
		throws Exception {

		return _accountResource.postAccount(agentName, agentUID, account);
	}

	public void assignContactRolesByEmailAddress(
			String agentName, String agentUID, String accountKey,
			String contactEmailAddress, String[] contactRoleKeys)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			_accountResource.
				putAccountContactByEmailAddresContactEmailAddressRoleHttpResponse(
					agentName, agentUID, accountKey, contactEmailAddress,
					contactRoleKeys);

		if ((httpResponse.getStatusCode() ==
				HttpServletResponse.SC_BAD_REQUEST) ||
			(httpResponse.getStatusCode() ==
				HttpServletResponse.SC_NOT_FOUND)) {

			throw new NoSuchContactException();
		}

		validateResponse(httpResponse);
	}

	public void assignContactRolesByUuid(
			String agentName, String agentUID, String accountKey,
			String contactUuid, String[] contactRoleKeys)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			_accountResource.putAccountContactByUuidContactUuidRoleHttpResponse(
				agentName, agentUID, accountKey, contactUuid, contactRoleKeys);

		if ((httpResponse.getStatusCode() ==
				HttpServletResponse.SC_BAD_REQUEST) ||
			(httpResponse.getStatusCode() ==
				HttpServletResponse.SC_NOT_FOUND)) {

			throw new NoSuchContactException();
		}

		validateResponse(httpResponse);
	}

	public void assignTeamRoles(
			String agentName, String agentUID, String accountKey,
			String teamKey, String[] teamRoleKeys)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			_accountResource.putAccountAssignedTeamTeamKeyRoleHttpResponse(
				agentName, agentUID, accountKey, teamKey, teamRoleKeys);

		validateResponse(httpResponse);
	}

	public Account fetchAccount(String accountKey) throws Exception {
		HttpInvoker.HttpResponse httpResponse =
			_accountDetailsResource.getAccountHttpResponse(accountKey);

		if (httpResponse.getStatusCode() == HttpServletResponse.SC_NOT_FOUND) {
			return null;
		}

		return AccountSerDes.toDTO(httpResponse.getContent());
	}

	public Account getAccount(String accountKey) throws Exception {
		return _accountDetailsResource.getAccount(accountKey);
	}

	public List<Account> getAccounts(
			String domain, String entityName, String entityId, int page,
			int pageSize)
		throws Exception {

		Page<Account> accountsPage =
			_accountResource.getAccountByExternalLinkDomainEntityNameEntityPage(
				domain, entityName, entityId, Pagination.of(page, pageSize));

		if ((accountsPage != null) && (accountsPage.getItems() != null)) {
			return new ArrayList<>(accountsPage.getItems());
		}

		return Collections.emptyList();
	}

	public long getContactAccountsCount(String contactUuid) throws Exception {
		Page<Account> accountsPage =
			_accountResource.getContactByUuidContactUuidAccountsPage(
				contactUuid, Pagination.of(1, 1000));

		if (accountsPage != null) {
			return accountsPage.getTotalCount();
		}

		return 0;
	}

	public List<Account> search(
			String search, FilterQuery filterQuery, int page, int pageSize,
			String sortString)
		throws Exception {

		String filterString = null;

		if (filterQuery != null) {
			filterString = filterQuery.toString();
		}

		Page<Account> accountsPage = _accountResource.getAccountsPage(
			search, filterString, Pagination.of(page, pageSize), sortString);

		if ((accountsPage != null) && (accountsPage.getItems() != null)) {
			return new ArrayList<>(accountsPage.getItems());
		}

		return Collections.emptyList();
	}

	public long searchCount(String search, FilterQuery filterQuery)
		throws Exception {

		String filterString = null;

		if (filterQuery != null) {
			filterString = filterQuery.toString();
		}

		Page<Account> accountsPage = _accountResource.getAccountsPage(
			search, filterString, Pagination.of(1, 1), StringPool.BLANK);

		if (accountsPage != null) {
			return accountsPage.getTotalCount();
		}

		return 0;
	}

	public void unassignContactRolesByEmailAddress(
			String agentName, String agentUID, String accountKey,
			String contactEmailAddress, String[] contactRoleKeys)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			_accountResource.
				deleteAccountContactByEmailAddresContactEmailAddressRoleHttpResponse(
					agentName, agentUID, accountKey, contactEmailAddress,
					contactRoleKeys);

		validateResponse(httpResponse);
	}

	public void unassignContactRolesByUuid(
			String agentName, String agentUID, String accountKey,
			String contactUuid, String[] contactRoleKeys)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			_accountResource.
				deleteAccountContactByUuidContactUuidRoleHttpResponse(
					agentName, agentUID, accountKey, contactUuid,
					contactRoleKeys);

		validateResponse(httpResponse);
	}

	public void unassignCustomerContact(
			String agentName, String agentUID, String accountKey,
			String contactEmailAddress)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			_accountResource.
				deleteAccountCustomerContactByEmailAddresHttpResponse(
					agentName, agentUID, accountKey,
					new String[] {contactEmailAddress});

		validateResponse(httpResponse);
	}

	public void unassignTeamRoles(
			String agentName, String agentUID, String accountKey,
			String teamKey, String[] teamRoleKeys)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			_accountResource.deleteAccountAssignedTeamTeamKeyRoleHttpResponse(
				agentName, agentUID, accountKey, teamKey, teamRoleKeys);

		validateResponse(httpResponse);
	}

	public void unassignWorkerContact(
			String agentName, String agentUID, String accountKey,
			String contactEmailAddress)
		throws Exception {

		_accountResource.deleteAccountWorkerContactByEmailAddres(
			agentName, agentUID, accountKey,
			new String[] {contactEmailAddress});
	}

	public Account updateAccount(
			String agentName, String agentUID, String accountKey,
			Account account)
		throws Exception {

		return _accountResource.putAccount(
			agentName, agentUID, accountKey, account);
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		KoroneikiConfiguration koroneikiConfiguration =
			ConfigurableUtil.createConfigurable(
				KoroneikiConfiguration.class, properties);

		AccountResource.Builder builder = AccountResource.builder();

		_accountResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).parameter(
			"nestedFields", "assignedTeams,productPurchases"
		).build();

		AccountResource.Builder accountDetailsBuilder =
			AccountResource.builder();

		_accountDetailsResource = accountDetailsBuilder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).parameter(
			"nestedFields",
			"assignedTeams,assignedTeams.teamRoles,customerContacts," +
				"customerContacts.contactRoles,productPurchases"
		).build();
	}

	private AccountResource _accountDetailsResource;
	private AccountResource _accountResource;

}