/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.jaas;

import java.io.Serializable;

import java.security.Principal;
import java.security.acl.Group;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalGroup
	extends PortalPrincipal implements Group, Serializable {

	public PortalGroup(String groupName) {
		super(groupName);
	}

	@Override
	public boolean addMember(Principal user) {
		if (!_members.containsKey(user)) {
			_members.put(user, user);

			return true;
		}

		return false;
	}

	@Override
	public boolean isMember(Principal member) {
		if (_members.containsKey(member)) {
			return true;
		}

		for (Principal principal : _members.values()) {
			if (principal instanceof Group) {
				Group group = (Group)principal;

				if (group.isMember(member)) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public Enumeration<Principal> members() {
		return Collections.enumeration(_members.values());
	}

	@Override
	public boolean removeMember(Principal user) {
		Principal principal = _members.remove(user);

		if (principal != null) {
			return true;
		}

		return false;
	}

	private final Map<Principal, Principal> _members = new HashMap<>();

}