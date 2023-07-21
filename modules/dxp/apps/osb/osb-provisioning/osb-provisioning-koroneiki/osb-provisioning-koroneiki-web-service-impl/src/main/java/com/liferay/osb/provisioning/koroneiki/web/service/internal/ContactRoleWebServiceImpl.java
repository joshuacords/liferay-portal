/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.koroneiki.web.service.internal;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ContactRoleResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactRoleSerDes;
import com.liferay.osb.provisioning.koroneiki.exception.NoSuchContactRoleException;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration",
	immediate = true, service = ContactRoleWebService.class
)
public class ContactRoleWebServiceImpl implements ContactRoleWebService {

	public ContactRole addContactRole(
			String agentName, String agentUID, ContactRole contactRole)
		throws Exception {

		return _contactRoleResource.postContactRole(
			agentName, agentUID, contactRole);
	}

	public ContactRole fetchContactRole(String type, String name)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			_contactRoleResource.
				getContactRoleByTypeContactRoleTypeByNameContactRoleNameHttpResponse(
					_http.encodePath(type), _http.encodePath(name));

		if (httpResponse.getStatusCode() == HttpServletResponse.SC_NOT_FOUND) {
			return null;
		}

		return ContactRoleSerDes.toDTO(httpResponse.getContent());
	}

	public List<ContactRole> getAccountContactRoles(
			String accountKey, String emailAddress, int page, int pageSize)
		throws Exception {

		Page<ContactRole> contactRolesPage =
			_contactRoleResource.
				getAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage(
					accountKey, emailAddress, Pagination.of(page, pageSize));

		if ((contactRolesPage != null) &&
			(contactRolesPage.getItems() != null)) {

			return new ArrayList<>(contactRolesPage.getItems());
		}

		return Collections.emptyList();
	}

	public List<ContactRole> getAccountCustomerContactRoles(
			String accountKey, String emailAddress, int page, int pageSize)
		throws Exception {

		Page<ContactRole> contactRolesPage =
			_contactRoleResource.
				getAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage(
					accountKey, emailAddress, Pagination.of(page, pageSize));

		if ((contactRolesPage != null) &&
			(contactRolesPage.getItems() != null)) {

			return new ArrayList<>(contactRolesPage.getItems());
		}

		return Collections.emptyList();
	}

	public List<ContactRole> getAccountWorkerContactRoles(
			String accountKey, String emailAddress, int page, int pageSize)
		throws Exception {

		Page<ContactRole> contactRolesPage =
			_contactRoleResource.
				getAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage(
					accountKey, emailAddress, Pagination.of(page, pageSize));

		if ((contactRolesPage != null) &&
			(contactRolesPage.getItems() != null)) {

			return new ArrayList<>(contactRolesPage.getItems());
		}

		return Collections.emptyList();
	}

	public ContactRole getContactRole(String contactRoleKey) throws Exception {
		return _contactRoleResource.getContactRole(contactRoleKey);
	}

	public ContactRole getContactRole(String type, String name)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			_contactRoleResource.
				getContactRoleByTypeContactRoleTypeByNameContactRoleNameHttpResponse(
					_http.encodePath(type), _http.encodePath(name));

		if (httpResponse.getStatusCode() == HttpServletResponse.SC_NOT_FOUND) {
			throw new NoSuchContactRoleException(
				StringBundler.concat(
					"No contact role exists with type ", type, " and name ",
					name));
		}

		return ContactRoleSerDes.toDTO(httpResponse.getContent());
	}

	public List<ContactRole> search(
			FilterQuery filterQuery, int page, int pageSize, String sortString)
		throws Exception {

		String filterString = null;

		if (filterQuery != null) {
			filterString = filterQuery.toString();
		}

		Page<ContactRole> contactRolesPage =
			_contactRoleResource.getContactRolesPage(
				StringPool.BLANK, filterString, Pagination.of(page, pageSize),
				sortString);

		if ((contactRolesPage != null) &&
			(contactRolesPage.getItems() != null)) {

			return new ArrayList<>(contactRolesPage.getItems());
		}

		return Collections.emptyList();
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		KoroneikiConfiguration koroneikiConfiguration =
			ConfigurableUtil.createConfigurable(
				KoroneikiConfiguration.class, properties);

		ContactRoleResource.Builder builder = ContactRoleResource.builder();

		_contactRoleResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).build();
	}

	private ContactRoleResource _contactRoleResource;

	@Reference
	private Http _http;

}