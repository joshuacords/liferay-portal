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

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class ViewAccountContactsDisplayContext
	extends ViewAccountDisplayContext {

	public ViewAccountContactsDisplayContext() {
	}

	@Override
	public void doInit() throws Exception {
		super.doInit();

		_contact = (Contact)renderRequest.getAttribute(
			ProvisioningWebKeys.CONTACT);

		setWindowTitle();
	}

	public Map<String, Object> getAssignContactData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		data.put("accountName", account.getName());
		data.put("allRoles", _getContactRoleJSONObjects());

		if (_contact != null) {
			data.put("currentRoles", _getContactRoleKeys(_contact));
		}

		data.put(
			"emailAddress",
			BeanParamUtil.getString(_contact, renderRequest, "emailAddress"));

		if (_contact != null) {
			ContactDisplay contactDisplay = new ContactDisplay(
				httpServletRequest, _contact, null);

			data.put("fullName", contactDisplay.getFullName());
		}

		data.put("redirect", ParamUtil.getString(renderRequest, "redirect"));

		return data;
	}

	public String getAssignContactTitle() {
		if (_contact != null) {
			return "edit-roles";
		}

		return "assign-contact";
	}

	public CreationMenu getCreationMenu() throws Exception {
		if (!hasAssignContactsPermission()) {
			return null;
		}

		return new CreationMenu() {
			{
				addDropdownItem(
					dropdownItem -> {
						dropdownItem.setHref(
							renderResponse.createRenderURL(),
							"mvcRenderCommandName", "/accounts/assign_contacts",
							"redirect", getCurrentURL(), "accountKey",
							account.getKey());
						dropdownItem.setLabel(
							LanguageUtil.get(
								httpServletRequest, "assign-contact"));
					});
			}
		};
	}

	public SearchContainer getSearchContainer() throws Exception {
		String keywords = ParamUtil.getString(renderRequest, "keywords");

		SearchContainer searchContainer = new SearchContainer(
			renderRequest, currentURLObj, Collections.emptyList(),
			"no-contacts-were-found");

		FilterQuery filterQuery = new FilterQuery();

		String[] contactRoleKeys = ParamUtil.getStringValues(
			renderRequest, "contactRoleKeys");

		if (!ArrayUtil.isEmpty(contactRoleKeys)) {
			String[] accountKeysContactRoleKeys =
				new String[contactRoleKeys.length];

			for (int i = 0; i < contactRoleKeys.length; i++) {
				accountKeysContactRoleKeys[i] =
					account.getKey() + "_" + contactRoleKeys[i];
			}

			filterQuery.addLambdaEquals(
				true, "accountKeysContactRoleKeys", accountKeysContactRoleKeys);
		}

		filterQuery.addLambdaEquals(
			true, "customerAccountKeys", account.getKey());

		List<Contact> contacts = contactWebService.search(
			keywords, filterQuery, searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(), "firstName");

		searchContainer.setResults(
			TransformUtil.transform(
				contacts,
				contact -> {
					List<ContactRole> contactRoles =
						contactRoleWebService.getAccountCustomerContactRoles(
							account.getKey(), contact.getEmailAddress(), 1,
							1000);

					return new ContactDisplay(
						httpServletRequest, contact, contactRoles);
				}));

		int count = (int)contactWebService.searchCount(keywords, filterQuery);

		searchContainer.setTotal(count);

		return searchContainer;
	}

	@Override
	protected void setWindowTitle() {
		String tabs1 = ParamUtil.getString(renderRequest, "tabs1");

		if (Validator.isNotNull(tabs1)) {
			return;
		}

		String title = "assign-contact";

		if (_contact != null) {
			title = "edit-roles";
		}

		renderResponse.setTitle(
			StringBundler.concat(
				account.getCode(), StringPool.SPACE,
				LanguageUtil.get(httpServletRequest, title)));
	}

	private List<JSONObject> _getContactRoleJSONObjects() throws Exception {
		List<JSONObject> contactRoleJSONObjects = new ArrayList<>();

		for (ContactRole contactRole : _getContactRoles()) {
			contactRoleJSONObjects.add(
				JSONUtil.put(
					"key", contactRole.getKey()
				).put(
					"name", contactRole.getName()
				));
		}

		return contactRoleJSONObjects;
	}

	private List<String> _getContactRoleKeys(Contact contact) throws Exception {
		List<String> contactRoleKeys = new ArrayList<>();

		List<ContactRole> contactRoles =
			contactRoleWebService.getAccountCustomerContactRoles(
				account.getKey(), contact.getEmailAddress(), 1, 1000);

		for (ContactRole contactRole : contactRoles) {
			contactRoleKeys.add(contactRole.getKey());
		}

		return contactRoleKeys;
	}

	private List<ContactRole> _getContactRoles() throws Exception {
		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addEquals(
			true, "type", ContactRole.Type.ACCOUNT_CUSTOMER.toString());

		return contactRoleWebService.search(filterQuery, 1, 1000, "name");
	}

	private Contact _contact;

}