/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.startup.monitor;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.util.ThreadUtil;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author Matthew Tambara
 */
@Component(immediate = true, service = {})
public class PortalStartupMonitor {

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		target = ModuleServiceLifecycle.PORTAL_INITIALIZED
	)
	public void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {

		_componentContext.disableComponent(
			PortalStartupMonitor.class.getName());
	}

	public void unsetModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Activate
	protected void activate(ComponentContext componentContext) {
		_componentContext = componentContext;

		_thread = new Thread("Portal Startup Monitoring Thread") {

			@Override
			public void run() {
				StringBundler sb = new StringBundler(4);

				while (true) {
					try {
						Thread.sleep(_SLEEP);
					}
					catch (InterruptedException interruptedException) {
						break;
					}

					sb.append("Thread dump for portal startup after waited ");
					sb.append(_SLEEP);
					sb.append("ms:\n");
					sb.append(ThreadUtil.threadDump());

					System.out.println(sb.toString());

					sb.setIndex(0);
				}
			}

		};

		_thread.setDaemon(true);

		_thread.start();
	}

	@Deactivate
	protected void deactivate() {
		_thread.interrupt();
	}

	private static final long _SLEEP = 600000;

	private ComponentContext _componentContext;
	private Thread _thread;

}