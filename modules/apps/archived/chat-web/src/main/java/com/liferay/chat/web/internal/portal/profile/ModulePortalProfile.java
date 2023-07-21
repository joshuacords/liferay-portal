/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.chat.web.internal.portal.profile;

import com.liferay.chat.web.internal.portlet.ChatPortlet;
import com.liferay.chat.web.internal.portlet.route.ChatFriendlyURLMapper;
import com.liferay.chat.web.internal.upgrade.ChatWebUpgrade;
import com.liferay.chat.web.internal.util.BuddyFinderUtil;
import com.liferay.portal.profile.BaseDSModulePortalProfile;
import com.liferay.portal.profile.PortalProfile;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Kenji Heigel
 */
@Component(immediate = true, service = PortalProfile.class)
public class ModulePortalProfile extends BaseDSModulePortalProfile {

	@Activate
	public void activate(ComponentContext componentContext) {
		Set<String> supportedPortalProfileNames = new HashSet<>(
			Arrays.asList(
				PortalProfile.PORTAL_PROFILE_NAME_CE,
				PortalProfile.PORTAL_PROFILE_NAME_DXP));

		init(
			componentContext, supportedPortalProfileNames,
			BuddyFinderUtil.class.getName(),
			ChatFriendlyURLMapper.class.getName(), ChatPortlet.class.getName(),
			ChatWebUpgrade.class.getName());
	}

}