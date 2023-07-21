/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.ccpp;

import java.util.Set;

import javax.ccpp.Attribute;
import javax.ccpp.Component;
import javax.ccpp.Profile;
import javax.ccpp.ProfileDescription;

/**
 * @author Jorge Ferrer
 */
public class EmptyProfile implements Profile {

	@Override
	public Attribute getAttribute(String name) {
		return null;
	}

	@Override
	public Set<Attribute> getAttributes() {
		return null;
	}

	@Override
	public Component getComponent(String localtype) {
		return null;
	}

	@Override
	public Set<Component> getComponents() {
		return null;
	}

	@Override
	public ProfileDescription getDescription() {
		return null;
	}

}