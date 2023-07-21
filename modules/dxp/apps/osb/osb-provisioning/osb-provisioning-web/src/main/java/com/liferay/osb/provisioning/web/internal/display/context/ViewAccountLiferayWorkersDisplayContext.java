/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItemList;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletURL;

/**
 * @author Amos Fong
 */
public class ViewAccountLiferayWorkersDisplayContext
	extends ViewAccountDisplayContext {

	public ViewAccountLiferayWorkersDisplayContext() {
	}

	@Override
	public void doInit() throws Exception {
		super.doInit();

		_contact = (Contact)renderRequest.getAttribute(
			ProvisioningWebKeys.CONTACT);

		setWindowTitle();
	}

	public Map<String, Object> getAssignLiferayWorkerData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		data.put("accountName", account.getName());
		data.put("allRoles", _getContactRoleJSONObjects());

		if (!SessionErrors.isEmpty(renderRequest)) {
			data.put(
				"currentRoles",
				ParamUtil.getStringValues(renderRequest, "addContactRoleKeys"));
		}
		else if (_contact != null) {
			data.put("currentRoles", _getContactRoleKeys(_contact));
		}

		data.put(
			"emailAddress",
			BeanParamUtil.getString(_contact, renderRequest, "emailAddress"));
		data.put(
			"firstName",
			BeanParamUtil.getString(_contact, renderRequest, "firstName"));
		data.put(
			"lastName",
			BeanParamUtil.getString(_contact, renderRequest, "lastName"));
		data.put("redirect", ParamUtil.getString(renderRequest, "redirect"));

		if (_contact != null) {
			data.put("uuid", _contact.getUuid());
		}

		return data;
	}

	public String getAssignLiferayWorkerTitle() {
		if (_contact != null) {
			return "edit-roles";
		}

		return "assign-liferay-worker";
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
							"mvcRenderCommandName",
							"/accounts/assign_liferay_workers", "redirect",
							getCurrentURL(), "accountKey", account.getKey());
						dropdownItem.setLabel(
							LanguageUtil.get(
								httpServletRequest, "assign-liferay-worker"));
					});
			}
		};
	}

	public List<DropdownItem> getFilterDropdownItems() throws Exception {
		return new DropdownItemList() {
			{
				addGroup(
					dropdownGroupItem -> {
						dropdownGroupItem.setDropdownItems(
							_getFilterRoleDropdownItems());
						dropdownGroupItem.setLabel(
							LanguageUtil.get(
								httpServletRequest, "filter-by-role"));
					});
			}
		};
	}

	public List<LabelItem> getFilterLabelItems() {
		return new LabelItemList() {
			{
				String[] contactRoleKeys = ParamUtil.getStringValues(
					renderRequest, "contactRoleKeys");

				for (String contactRoleKey : contactRoleKeys) {
					add(
						labelItem -> {
							PortletURL removeLabelURL = PortletURLUtil.clone(
								currentURLObj, renderResponse);

							String[] removeContactRoleKeys = ArrayUtil.remove(
								contactRoleKeys, contactRoleKey);

							removeLabelURL.setParameter(
								"contactRoleKeys",
								StringUtil.merge(removeContactRoleKeys));

							labelItem.putData(
								"removeLabelURL", removeLabelURL.toString());

							labelItem.setCloseable(true);

							ContactRole contactRole =
								contactRoleWebService.getContactRole(
									contactRoleKey);

							String label = String.format(
								"%s: %s",
								LanguageUtil.get(
									httpServletRequest, "contact-role"),
								contactRole.getName());

							labelItem.setLabel(label);
						});
				}
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
			true, "workerAccountKeys", account.getKey());

		List<Contact> contacts = contactWebService.search(
			keywords, filterQuery, searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(), "firstName");

		searchContainer.setResults(
			TransformUtil.transform(
				contacts,
				contact -> {
					List<ContactRole> contactRoles =
						contactRoleWebService.getAccountWorkerContactRoles(
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

		String title = "assign-liferay-worker";

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
			contactRoleWebService.getAccountWorkerContactRoles(
				account.getKey(), contact.getEmailAddress(), 1, 1000);

		for (ContactRole contactRole : contactRoles) {
			contactRoleKeys.add(contactRole.getKey());
		}

		return contactRoleKeys;
	}

	private List<ContactRole> _getContactRoles() throws Exception {
		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addEquals(
			true, "type", ContactRole.Type.ACCOUNT_WORKER.toString());

		return contactRoleWebService.search(filterQuery, 1, 1000, "name");
	}

	private List<DropdownItem> _getFilterRoleDropdownItems() throws Exception {
		String[] contactRoleKeys = ParamUtil.getStringValues(
			renderRequest, "contactRoleKeys");

		return new DropdownItemList() {
			{
				for (ContactRole contactRole : _getContactRoles()) {
					add(
						dropdownItem -> {
							dropdownItem.setActive(
								ArrayUtil.contains(
									contactRoleKeys, contactRole.getKey()));

							PortletURL portletURL = PortletURLUtil.clone(
								currentURLObj, renderResponse);

							String[] newContactRoleKeys = ArrayUtil.append(
								contactRoleKeys, contactRole.getKey());

							dropdownItem.setHref(
								portletURL, "contactRoleKeys",
								StringUtil.merge(newContactRoleKeys));

							dropdownItem.setLabel(contactRole.getName());
						});
				}
			}
		};
	}

	private Contact _contact;

}