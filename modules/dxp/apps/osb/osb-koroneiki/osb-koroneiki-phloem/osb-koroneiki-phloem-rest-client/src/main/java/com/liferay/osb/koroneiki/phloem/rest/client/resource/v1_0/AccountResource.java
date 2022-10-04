/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.AccountPermission;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.problem.Problem;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.AccountSerDes;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public interface AccountResource {

	public static Builder builder() {
		return new Builder();
	}

	public Page<Account> getAccountsPage(
			String search, String filterString, Pagination pagination,
			String sortString)
		throws Exception;

	public HttpInvoker.HttpResponse getAccountsPageHttpResponse(
			String search, String filterString, Pagination pagination,
			String sortString)
		throws Exception;

	public Account postAccount(
			String agentName, String agentUID, Account account)
		throws Exception;

	public HttpInvoker.HttpResponse postAccountHttpResponse(
			String agentName, String agentUID, Account account)
		throws Exception;

	public Page<Account> getAccountByExternalLinkDomainEntityNameEntityPage(
			String domain, String entityName, String entityId,
			Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getAccountByExternalLinkDomainEntityNameEntityPageHttpResponse(
				String domain, String entityName, String entityId,
				Pagination pagination)
		throws Exception;

	public void deleteAccount(
			String agentName, String agentUID, String accountKey)
		throws Exception;

	public HttpInvoker.HttpResponse deleteAccountHttpResponse(
			String agentName, String agentUID, String accountKey)
		throws Exception;

	public Account getAccount(String accountKey) throws Exception;

	public HttpInvoker.HttpResponse getAccountHttpResponse(String accountKey)
		throws Exception;

	public Account putAccount(
			String agentName, String agentUID, String accountKey,
			Account account)
		throws Exception;

	public HttpInvoker.HttpResponse putAccountHttpResponse(
			String agentName, String agentUID, String accountKey,
			Account account)
		throws Exception;

	public void deleteAccountAccountPermission(
			String agentName, String agentUID, String accountKey,
			AccountPermission accountPermission)
		throws Exception;

	public HttpInvoker.HttpResponse deleteAccountAccountPermissionHttpResponse(
			String agentName, String agentUID, String accountKey,
			AccountPermission accountPermission)
		throws Exception;

	public void putAccountAccountPermission(
			String agentName, String agentUID, String accountKey,
			AccountPermission accountPermission)
		throws Exception;

	public HttpInvoker.HttpResponse putAccountAccountPermissionHttpResponse(
			String agentName, String agentUID, String accountKey,
			AccountPermission accountPermission)
		throws Exception;

	public void deleteAccountAssignedTeamTeamKeyRole(
			String agentName, String agentUID, String accountKey,
			String teamKey, String[] teamRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse
			deleteAccountAssignedTeamTeamKeyRoleHttpResponse(
				String agentName, String agentUID, String accountKey,
				String teamKey, String[] teamRoleKeys)
		throws Exception;

	public void putAccountAssignedTeamTeamKeyRole(
			String agentName, String agentUID, String accountKey,
			String teamKey, String[] teamRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse
			putAccountAssignedTeamTeamKeyRoleHttpResponse(
				String agentName, String agentUID, String accountKey,
				String teamKey, String[] teamRoleKeys)
		throws Exception;

	public Page<Account> getAccountChildAccountsPage(
			String accountKey, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse getAccountChildAccountsPageHttpResponse(
			String accountKey, Pagination pagination)
		throws Exception;

	public void deleteAccountContactByEmailAddresContactEmailAddressRole(
			String agentName, String agentUID, String accountKey,
			String contactEmailAddress, String[] contactRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse
			deleteAccountContactByEmailAddresContactEmailAddressRoleHttpResponse(
				String agentName, String agentUID, String accountKey,
				String contactEmailAddress, String[] contactRoleKeys)
		throws Exception;

	public void putAccountContactByEmailAddresContactEmailAddressRole(
			String agentName, String agentUID, String accountKey,
			String contactEmailAddress, String[] contactRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse
			putAccountContactByEmailAddresContactEmailAddressRoleHttpResponse(
				String agentName, String agentUID, String accountKey,
				String contactEmailAddress, String[] contactRoleKeys)
		throws Exception;

	public void deleteAccountContactByUuidContactUuidRole(
			String agentName, String agentUID, String accountKey,
			String contactUuid, String[] contactRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse
			deleteAccountContactByUuidContactUuidRoleHttpResponse(
				String agentName, String agentUID, String accountKey,
				String contactUuid, String[] contactRoleKeys)
		throws Exception;

	public void putAccountContactByUuidContactUuidRole(
			String agentName, String agentUID, String accountKey,
			String contactUuid, String[] contactRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse
			putAccountContactByUuidContactUuidRoleHttpResponse(
				String agentName, String agentUID, String accountKey,
				String contactUuid, String[] contactRoleKeys)
		throws Exception;

	public void deleteAccountCustomerContactByEmailAddres(
			String agentName, String agentUID, String accountKey,
			String[] contactEmailAddresses)
		throws Exception;

	public HttpInvoker.HttpResponse
			deleteAccountCustomerContactByEmailAddresHttpResponse(
				String agentName, String agentUID, String accountKey,
				String[] contactEmailAddresses)
		throws Exception;

	public void deleteAccountCustomerContactByUuid(
			String agentName, String agentUID, String accountKey,
			String[] contactUuids)
		throws Exception;

	public HttpInvoker.HttpResponse
			deleteAccountCustomerContactByUuidHttpResponse(
				String agentName, String agentUID, String accountKey,
				String[] contactUuids)
		throws Exception;

	public void deleteAccountWorkerContactByEmailAddres(
			String agentName, String agentUID, String accountKey,
			String[] contactEmailAddresses)
		throws Exception;

	public HttpInvoker.HttpResponse
			deleteAccountWorkerContactByEmailAddresHttpResponse(
				String agentName, String agentUID, String accountKey,
				String[] contactEmailAddresses)
		throws Exception;

	public void deleteAccountWorkerContactByUuid(
			String agentName, String agentUID, String accountKey,
			String[] contactUuids)
		throws Exception;

	public HttpInvoker.HttpResponse
			deleteAccountWorkerContactByUuidHttpResponse(
				String agentName, String agentUID, String accountKey,
				String[] contactUuids)
		throws Exception;

	public Page<Account> getContactByUuidContactUuidAccountsPage(
			String contactUuid, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getContactByUuidContactUuidAccountsPageHttpResponse(
				String contactUuid, Pagination pagination)
		throws Exception;

	public Page<Account> getTeamTeamKeyAssignedAccountsPage(
			String teamKey, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getTeamTeamKeyAssignedAccountsPageHttpResponse(
				String teamKey, Pagination pagination)
		throws Exception;

	public static class Builder {

		public Builder authentication(String login, String password) {
			_login = login;
			_password = password;

			return this;
		}

		public AccountResource build() {
			return new AccountResourceImpl(this);
		}

		public Builder contextPath(String contextPath) {
			_contextPath = contextPath;

			return this;
		}

		public Builder endpoint(String host, int port, String scheme) {
			_host = host;
			_port = port;
			_scheme = scheme;

			return this;
		}

		public Builder header(String key, String value) {
			_headers.put(key, value);

			return this;
		}

		public Builder locale(Locale locale) {
			_locale = locale;

			return this;
		}

		public Builder parameter(String key, String value) {
			_parameters.put(key, value);

			return this;
		}

		public Builder parameters(String... parameters) {
			if ((parameters.length % 2) != 0) {
				throw new IllegalArgumentException(
					"Parameters length is not an even number");
			}

			for (int i = 0; i < parameters.length; i += 2) {
				String parameterName = String.valueOf(parameters[i]);
				String parameterValue = String.valueOf(parameters[i + 1]);

				_parameters.put(parameterName, parameterValue);
			}

			return this;
		}

		private Builder() {
		}

		private String _contextPath = "";
		private Map<String, String> _headers = new LinkedHashMap<>();
		private String _host = "localhost";
		private Locale _locale;
		private String _login = "";
		private String _password = "";
		private Map<String, String> _parameters = new LinkedHashMap<>();
		private int _port = 8080;
		private String _scheme = "http";

	}

	public static class AccountResourceImpl implements AccountResource {

		public Page<Account> getAccountsPage(
				String search, String filterString, Pagination pagination,
				String sortString)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse = getAccountsPageHttpResponse(
				search, filterString, pagination, sortString);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return Page.of(content, AccountSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse getAccountsPageHttpResponse(
				String search, String filterString, Pagination pagination,
				String sortString)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (search != null) {
				httpInvoker.parameter("search", String.valueOf(search));
			}

			if (filterString != null) {
				httpInvoker.parameter("filter", filterString);
			}

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			if (sortString != null) {
				httpInvoker.parameter("sort", sortString);
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/koroneiki-rest/v1.0/accounts");

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Account postAccount(
				String agentName, String agentUID, Account account)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse = postAccountHttpResponse(
				agentName, agentUID, account);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return AccountSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse postAccountHttpResponse(
				String agentName, String agentUID, Account account)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(account.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/koroneiki-rest/v1.0/accounts");

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<Account> getAccountByExternalLinkDomainEntityNameEntityPage(
				String domain, String entityName, String entityId,
				Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getAccountByExternalLinkDomainEntityNameEntityPageHttpResponse(
					domain, entityName, entityId, pagination);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return Page.of(content, AccountSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getAccountByExternalLinkDomainEntityNameEntityPageHttpResponse(
					String domain, String entityName, String entityId,
					Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/koroneiki-rest/v1.0/accounts/by-external-link/{domain}/{entityName}/{entityId}");

			httpInvoker.path("domain", domain);
			httpInvoker.path("entityName", entityName);
			httpInvoker.path("entityId", entityId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteAccount(
				String agentName, String agentUID, String accountKey)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse = deleteAccountHttpResponse(
				agentName, agentUID, accountKey);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return;
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse deleteAccountHttpResponse(
				String agentName, String agentUID, String accountKey)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(agentUID.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}");

			httpInvoker.path("accountKey", accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Account getAccount(String accountKey) throws Exception {
			HttpInvoker.HttpResponse httpResponse = getAccountHttpResponse(
				accountKey);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return AccountSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse getAccountHttpResponse(
				String accountKey)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}");

			httpInvoker.path("accountKey", accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Account putAccount(
				String agentName, String agentUID, String accountKey,
				Account account)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse = putAccountHttpResponse(
				agentName, agentUID, accountKey, account);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return AccountSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse putAccountHttpResponse(
				String agentName, String agentUID, String accountKey,
				Account account)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(account.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}");

			httpInvoker.path("accountKey", accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteAccountAccountPermission(
				String agentName, String agentUID, String accountKey,
				AccountPermission accountPermission)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteAccountAccountPermissionHttpResponse(
					agentName, agentUID, accountKey, accountPermission);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return;
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				deleteAccountAccountPermissionHttpResponse(
					String agentName, String agentUID, String accountKey,
					AccountPermission accountPermission)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(accountPermission.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/account-permissions");

			httpInvoker.path("accountKey", accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putAccountAccountPermission(
				String agentName, String agentUID, String accountKey,
				AccountPermission accountPermission)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putAccountAccountPermissionHttpResponse(
					agentName, agentUID, accountKey, accountPermission);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return;
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse putAccountAccountPermissionHttpResponse(
				String agentName, String agentUID, String accountKey,
				AccountPermission accountPermission)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(accountPermission.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/account-permissions");

			httpInvoker.path("accountKey", accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteAccountAssignedTeamTeamKeyRole(
				String agentName, String agentUID, String accountKey,
				String teamKey, String[] teamRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteAccountAssignedTeamTeamKeyRoleHttpResponse(
					agentName, agentUID, accountKey, teamKey, teamRoleKeys);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return;
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				deleteAccountAssignedTeamTeamKeyRoleHttpResponse(
					String agentName, String agentUID, String accountKey,
					String teamKey, String[] teamRoleKeys)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				Stream.of(
					teamRoleKeys
				).map(
					value -> "\"" + String.valueOf(value) + "\""
				).collect(
					Collectors.toList()
				).toString(),
				"application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			if (teamRoleKeys != null) {
				for (int i = 0; i < teamRoleKeys.length; i++) {
					httpInvoker.parameter(
						"teamRoleKeys", String.valueOf(teamRoleKeys[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/assigned-teams/{teamKey}/roles");

			httpInvoker.path("accountKey", accountKey);
			httpInvoker.path("teamKey", teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putAccountAssignedTeamTeamKeyRole(
				String agentName, String agentUID, String accountKey,
				String teamKey, String[] teamRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putAccountAssignedTeamTeamKeyRoleHttpResponse(
					agentName, agentUID, accountKey, teamKey, teamRoleKeys);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return;
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				putAccountAssignedTeamTeamKeyRoleHttpResponse(
					String agentName, String agentUID, String accountKey,
					String teamKey, String[] teamRoleKeys)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				Stream.of(
					teamRoleKeys
				).map(
					value -> "\"" + String.valueOf(value) + "\""
				).collect(
					Collectors.toList()
				).toString(),
				"application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			if (teamRoleKeys != null) {
				for (int i = 0; i < teamRoleKeys.length; i++) {
					httpInvoker.parameter(
						"teamRoleKeys", String.valueOf(teamRoleKeys[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/assigned-teams/{teamKey}/roles");

			httpInvoker.path("accountKey", accountKey);
			httpInvoker.path("teamKey", teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<Account> getAccountChildAccountsPage(
				String accountKey, Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getAccountChildAccountsPageHttpResponse(accountKey, pagination);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return Page.of(content, AccountSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse getAccountChildAccountsPageHttpResponse(
				String accountKey, Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/child-accounts");

			httpInvoker.path("accountKey", accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteAccountContactByEmailAddresContactEmailAddressRole(
				String agentName, String agentUID, String accountKey,
				String contactEmailAddress, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteAccountContactByEmailAddresContactEmailAddressRoleHttpResponse(
					agentName, agentUID, accountKey, contactEmailAddress,
					contactRoleKeys);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return;
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				deleteAccountContactByEmailAddresContactEmailAddressRoleHttpResponse(
					String agentName, String agentUID, String accountKey,
					String contactEmailAddress, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				Stream.of(
					contactRoleKeys
				).map(
					value -> "\"" + String.valueOf(value) + "\""
				).collect(
					Collectors.toList()
				).toString(),
				"application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			if (contactRoleKeys != null) {
				for (int i = 0; i < contactRoleKeys.length; i++) {
					httpInvoker.parameter(
						"contactRoleKeys", String.valueOf(contactRoleKeys[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-email-address/{contactEmailAddress}/roles");

			httpInvoker.path("accountKey", accountKey);
			httpInvoker.path("contactEmailAddress", contactEmailAddress);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putAccountContactByEmailAddresContactEmailAddressRole(
				String agentName, String agentUID, String accountKey,
				String contactEmailAddress, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putAccountContactByEmailAddresContactEmailAddressRoleHttpResponse(
					agentName, agentUID, accountKey, contactEmailAddress,
					contactRoleKeys);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return;
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				putAccountContactByEmailAddresContactEmailAddressRoleHttpResponse(
					String agentName, String agentUID, String accountKey,
					String contactEmailAddress, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				Stream.of(
					contactRoleKeys
				).map(
					value -> "\"" + String.valueOf(value) + "\""
				).collect(
					Collectors.toList()
				).toString(),
				"application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			if (contactRoleKeys != null) {
				for (int i = 0; i < contactRoleKeys.length; i++) {
					httpInvoker.parameter(
						"contactRoleKeys", String.valueOf(contactRoleKeys[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-email-address/{contactEmailAddress}/roles");

			httpInvoker.path("accountKey", accountKey);
			httpInvoker.path("contactEmailAddress", contactEmailAddress);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteAccountContactByUuidContactUuidRole(
				String agentName, String agentUID, String accountKey,
				String contactUuid, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteAccountContactByUuidContactUuidRoleHttpResponse(
					agentName, agentUID, accountKey, contactUuid,
					contactRoleKeys);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return;
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				deleteAccountContactByUuidContactUuidRoleHttpResponse(
					String agentName, String agentUID, String accountKey,
					String contactUuid, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				Stream.of(
					contactRoleKeys
				).map(
					value -> "\"" + String.valueOf(value) + "\""
				).collect(
					Collectors.toList()
				).toString(),
				"application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			if (contactRoleKeys != null) {
				for (int i = 0; i < contactRoleKeys.length; i++) {
					httpInvoker.parameter(
						"contactRoleKeys", String.valueOf(contactRoleKeys[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-uuid/{contactUuid}/roles");

			httpInvoker.path("accountKey", accountKey);
			httpInvoker.path("contactUuid", contactUuid);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putAccountContactByUuidContactUuidRole(
				String agentName, String agentUID, String accountKey,
				String contactUuid, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putAccountContactByUuidContactUuidRoleHttpResponse(
					agentName, agentUID, accountKey, contactUuid,
					contactRoleKeys);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return;
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				putAccountContactByUuidContactUuidRoleHttpResponse(
					String agentName, String agentUID, String accountKey,
					String contactUuid, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				Stream.of(
					contactRoleKeys
				).map(
					value -> "\"" + String.valueOf(value) + "\""
				).collect(
					Collectors.toList()
				).toString(),
				"application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			if (contactRoleKeys != null) {
				for (int i = 0; i < contactRoleKeys.length; i++) {
					httpInvoker.parameter(
						"contactRoleKeys", String.valueOf(contactRoleKeys[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/contacts/by-uuid/{contactUuid}/roles");

			httpInvoker.path("accountKey", accountKey);
			httpInvoker.path("contactUuid", contactUuid);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteAccountCustomerContactByEmailAddres(
				String agentName, String agentUID, String accountKey,
				String[] contactEmailAddresses)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteAccountCustomerContactByEmailAddresHttpResponse(
					agentName, agentUID, accountKey, contactEmailAddresses);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return;
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				deleteAccountCustomerContactByEmailAddresHttpResponse(
					String agentName, String agentUID, String accountKey,
					String[] contactEmailAddresses)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				Stream.of(
					contactEmailAddresses
				).map(
					value -> "\"" + String.valueOf(value) + "\""
				).collect(
					Collectors.toList()
				).toString(),
				"application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			if (contactEmailAddresses != null) {
				for (int i = 0; i < contactEmailAddresses.length; i++) {
					httpInvoker.parameter(
						"contactEmailAddresses",
						String.valueOf(contactEmailAddresses[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/customer-contacts/by-email-address");

			httpInvoker.path("accountKey", accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteAccountCustomerContactByUuid(
				String agentName, String agentUID, String accountKey,
				String[] contactUuids)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteAccountCustomerContactByUuidHttpResponse(
					agentName, agentUID, accountKey, contactUuids);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return;
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				deleteAccountCustomerContactByUuidHttpResponse(
					String agentName, String agentUID, String accountKey,
					String[] contactUuids)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				Stream.of(
					contactUuids
				).map(
					value -> "\"" + String.valueOf(value) + "\""
				).collect(
					Collectors.toList()
				).toString(),
				"application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			if (contactUuids != null) {
				for (int i = 0; i < contactUuids.length; i++) {
					httpInvoker.parameter(
						"contactUuids", String.valueOf(contactUuids[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/customer-contacts/by-uuid");

			httpInvoker.path("accountKey", accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteAccountWorkerContactByEmailAddres(
				String agentName, String agentUID, String accountKey,
				String[] contactEmailAddresses)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteAccountWorkerContactByEmailAddresHttpResponse(
					agentName, agentUID, accountKey, contactEmailAddresses);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return;
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				deleteAccountWorkerContactByEmailAddresHttpResponse(
					String agentName, String agentUID, String accountKey,
					String[] contactEmailAddresses)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				Stream.of(
					contactEmailAddresses
				).map(
					value -> "\"" + String.valueOf(value) + "\""
				).collect(
					Collectors.toList()
				).toString(),
				"application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			if (contactEmailAddresses != null) {
				for (int i = 0; i < contactEmailAddresses.length; i++) {
					httpInvoker.parameter(
						"contactEmailAddresses",
						String.valueOf(contactEmailAddresses[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/worker-contacts/by-email-address");

			httpInvoker.path("accountKey", accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteAccountWorkerContactByUuid(
				String agentName, String agentUID, String accountKey,
				String[] contactUuids)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteAccountWorkerContactByUuidHttpResponse(
					agentName, agentUID, accountKey, contactUuids);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return;
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				deleteAccountWorkerContactByUuidHttpResponse(
					String agentName, String agentUID, String accountKey,
					String[] contactUuids)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				Stream.of(
					contactUuids
				).map(
					value -> "\"" + String.valueOf(value) + "\""
				).collect(
					Collectors.toList()
				).toString(),
				"application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (agentName != null) {
				httpInvoker.parameter("agentName", String.valueOf(agentName));
			}

			if (agentUID != null) {
				httpInvoker.parameter("agentUID", String.valueOf(agentUID));
			}

			if (contactUuids != null) {
				for (int i = 0; i < contactUuids.length; i++) {
					httpInvoker.parameter(
						"contactUuids", String.valueOf(contactUuids[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/worker-contacts/by-uuid");

			httpInvoker.path("accountKey", accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<Account> getContactByUuidContactUuidAccountsPage(
				String contactUuid, Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getContactByUuidContactUuidAccountsPageHttpResponse(
					contactUuid, pagination);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return Page.of(content, AccountSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getContactByUuidContactUuidAccountsPageHttpResponse(
					String contactUuid, Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/koroneiki-rest/v1.0/contacts/by-uuid/{contactUuid}/accounts");

			httpInvoker.path("contactUuid", contactUuid);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<Account> getTeamTeamKeyAssignedAccountsPage(
				String teamKey, Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getTeamTeamKeyAssignedAccountsPageHttpResponse(
					teamKey, pagination);

			String content = httpResponse.getContent();

			if ((httpResponse.getStatusCode() / 100) != 2) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response content: " + content);
				_logger.log(
					Level.WARNING,
					"HTTP response message: " + httpResponse.getMessage());
				_logger.log(
					Level.WARNING,
					"HTTP response status code: " +
						httpResponse.getStatusCode());

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
			else {
				_logger.fine("HTTP response content: " + content);
				_logger.fine(
					"HTTP response message: " + httpResponse.getMessage());
				_logger.fine(
					"HTTP response status code: " +
						httpResponse.getStatusCode());
			}

			try {
				return Page.of(content, AccountSerDes::toDTO);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw new Problem.ProblemException(Problem.toDTO(content));
			}
		}

		public HttpInvoker.HttpResponse
				getTeamTeamKeyAssignedAccountsPageHttpResponse(
					String teamKey, Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + _builder._contextPath +
						"/o/koroneiki-rest/v1.0/teams/{teamKey}/assigned-accounts");

			httpInvoker.path("teamKey", teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		private AccountResourceImpl(Builder builder) {
			_builder = builder;
		}

		private static final Logger _logger = Logger.getLogger(
			AccountResource.class.getName());

		private Builder _builder;

	}

}