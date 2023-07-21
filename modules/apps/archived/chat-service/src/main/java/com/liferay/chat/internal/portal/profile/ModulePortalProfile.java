/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.chat.internal.portal.profile;

import com.liferay.chat.internal.events.LoginPostAction;
import com.liferay.chat.internal.events.SessionDestroyAction;
import com.liferay.chat.internal.jabber.JabberImpl;
import com.liferay.chat.internal.jabber.JabberUtil;
import com.liferay.chat.internal.model.listener.UserModelListener;
import com.liferay.chat.internal.poller.ChatPollerProcessor;
import com.liferay.chat.internal.settings.definition.ChatGroupServiceConfigurationBeanDeclaration;
import com.liferay.chat.internal.util.DefaultBuddyFinderImpl;
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
			ChatGroupServiceConfigurationBeanDeclaration.class.getName(),
			ChatPollerProcessor.class.getName(),
			DefaultBuddyFinderImpl.class.getName(), JabberImpl.class.getName(),
			JabberUtil.class.getName(), LoginPostAction.class.getName(),
			SessionDestroyAction.class.getName(),
			UserModelListener.class.getName());
	}

}