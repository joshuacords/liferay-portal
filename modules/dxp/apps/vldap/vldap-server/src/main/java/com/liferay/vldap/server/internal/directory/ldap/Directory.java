/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.directory.ldap;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.comparator.UserScreenNameComparator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.vldap.server.internal.util.LdapUtil;
import com.liferay.vldap.server.internal.util.PortletPropsValues;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.directory.api.ldap.model.entry.DefaultEntry;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.exception.LdapInvalidDnException;
import org.apache.directory.api.ldap.model.name.Dn;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 * @author Igor Beslic
 */
public abstract class Directory {

	public void addAttribute(String attributeId, byte[] bytes) {
		Attribute attribute = new Attribute(attributeId, bytes);

		_attributes.add(attribute);
	}

	public void addAttribute(String attributeId, String value) {
		Attribute attribute = new Attribute(attributeId, value);

		_attributes.add(attribute);
	}

	public void addMemberAttributes(
		String top, Company company, LinkedHashMap<String, Object> params) {

		List<User> users = UserLocalServiceUtil.search(
			company.getCompanyId(), null, null, null, null, null,
			WorkflowConstants.STATUS_APPROVED, params, true, 0,
			PortletPropsValues.SEARCH_MAX_SIZE, new UserScreenNameComparator());

		for (User user : users) {
			String value = LdapUtil.buildName(
				user.getScreenName(), top, company, "Users");

			addAttribute("member", value);
		}
	}

	public boolean containsIgnoreCase(List<String> list, String value) {
		if (list == null) {
			return false;
		}

		for (String current : list) {
			if (StringUtil.equalsIgnoreCase(current, value)) {
				return true;
			}
		}

		return false;
	}

	public List<Attribute> getAttributes() {
		return _attributes;
	}

	public List<Attribute> getAttributes(String attributeId) {
		List<Attribute> attributes = new ArrayList<>();

		for (Attribute attribute : getAttributes()) {
			if (StringUtil.equalsIgnoreCase(
					attributeId, attribute.getAttributeId())) {

				attributes.add(attribute);
			}
		}

		return attributes;
	}

	public boolean hasAttribute(String attributeId) {
		List<Attribute> attributes = getAttributes(attributeId);

		if (attributes.isEmpty()) {
			return false;
		}

		return true;
	}

	public boolean hasAttribute(String attributeId, String value) {
		List<Attribute> attributes = getAttributes(attributeId);

		for (Attribute attribute : attributes) {
			if (StringUtil.equalsIgnoreCase(value, attribute.getValue())) {
				return true;
			}
		}

		return false;
	}

	public Entry toEntry(List<String> searchRequestAttributes)
		throws LdapException {

		Entry entry = new DefaultEntry();

		entry.setDn(getDn());

		// According to RFC 2251 Section 4.5.1 we need to return all attributes
		// if the requested attributes are empty or contain a wildcard

		boolean returnAllAttributes = false;

		if (searchRequestAttributes.isEmpty() ||
			searchRequestAttributes.contains(StringPool.STAR)) {

			returnAllAttributes = true;
		}

		for (Attribute attribute : getAttributes()) {
			if (returnAllAttributes ||
				containsIgnoreCase(
					searchRequestAttributes, attribute.getAttributeId())) {

				if (attribute.isBinary()) {
					entry.add(attribute.getAttributeId(), attribute.getBytes());
				}
				else {
					entry.add(attribute.getAttributeId(), attribute.getValue());
				}
			}
		}

		if (containsIgnoreCase(searchRequestAttributes, "hassubordinates")) {
			entry.add("hassubordinates", "true");
		}

		return entry;
	}

	protected Dn getDn() throws LdapInvalidDnException {
		String name = getName();

		try {
			return new Dn(name);
		}
		catch (LdapInvalidDnException ldapInvalidDnException) {
			_log.error("Invalid name " + name);

			throw ldapInvalidDnException;
		}
	}

	protected String getName() {
		return _name;
	}

	protected void setName(
		String top, Company company, String... organizationUnits) {

		_name = LdapUtil.buildName(null, top, company, organizationUnits);
	}

	private static final Log _log = LogFactoryUtil.getLog(Directory.class);

	private final List<Attribute> _attributes = new ArrayList<>();
	private String _name;

}