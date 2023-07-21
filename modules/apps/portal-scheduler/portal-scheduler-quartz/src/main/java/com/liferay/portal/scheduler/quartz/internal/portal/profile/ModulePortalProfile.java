/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.scheduler.quartz.internal.portal.profile;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.profile.BaseDSModulePortalProfile;
import com.liferay.portal.profile.PortalProfile;
import com.liferay.portal.scheduler.quartz.internal.QuartzSchedulerEngine;
import com.liferay.portal.scheduler.quartz.internal.QuartzSchemaManager;
import com.liferay.portal.scheduler.quartz.internal.QuartzTriggerFactory;
import com.liferay.portal.scheduler.quartz.internal.SchedulerLifecycleInitializer;
import com.liferay.portal.scheduler.quartz.internal.messaging.proxy.QuartzSchedulerProxyMessageListener;
import com.liferay.portal.scheduler.quartz.internal.upgrade.QuartzServiceUpgrade;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Tina Tian
 */
@Component(immediate = true, service = PortalProfile.class)
public class ModulePortalProfile extends BaseDSModulePortalProfile {

	@Activate
	public void activate(ComponentContext componentContext) {
		Set<String> supportedPortalProfileNames = null;

		if (GetterUtil.getBoolean(_props.get(PropsKeys.SCHEDULER_ENABLED))) {
			supportedPortalProfileNames = new HashSet<>();

			supportedPortalProfileNames.add(
				PortalProfile.PORTAL_PROFILE_NAME_CE);
			supportedPortalProfileNames.add(
				PortalProfile.PORTAL_PROFILE_NAME_DXP);
		}
		else {
			supportedPortalProfileNames = Collections.emptySet();
		}

		init(
			componentContext, supportedPortalProfileNames,
			QuartzSchedulerEngine.class.getName(),
			QuartzSchedulerProxyMessageListener.class.getName(),
			QuartzSchemaManager.class.getName(),
			QuartzServiceUpgrade.class.getName(),
			QuartzTriggerFactory.class.getName(),
			SchedulerLifecycleInitializer.class.getName());
	}

	@Reference(unbind = "-")
	protected void setProps(Props props) {
		_props = props;
	}

	private Props _props;

}