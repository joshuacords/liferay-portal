/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cluster.multiple.internal.portal.profile;

import com.liferay.portal.cluster.multiple.internal.ClusterExecutorImpl;
import com.liferay.portal.cluster.multiple.internal.ClusterLinkImpl;
import com.liferay.portal.cluster.multiple.internal.ClusterMasterExecutorImpl;
import com.liferay.portal.cluster.multiple.internal.ClusterMasterTokenTransitionListenerTracker;
import com.liferay.portal.cluster.multiple.internal.jgroups.JGroupsClusterChannelFactory;
import com.liferay.portal.kernel.cluster.ClusterExecutor;
import com.liferay.portal.kernel.cluster.ClusterLink;
import com.liferay.portal.kernel.cluster.ClusterMasterExecutor;
import com.liferay.portal.kernel.cluster.ClusterMasterTokenTransitionListener;
import com.liferay.portal.kernel.concurrent.NoticeableFuture;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.ProxyFactory;
import com.liferay.portal.profile.BaseDSModulePortalProfile;
import com.liferay.portal.profile.PortalProfile;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.osgi.framework.BundleContext;
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

		if (GetterUtil.getBoolean(_props.get(PropsKeys.CLUSTER_LINK_ENABLED))) {
			supportedPortalProfileNames = new HashSet<>();

			supportedPortalProfileNames.add(
				PortalProfile.PORTAL_PROFILE_NAME_CE);
			supportedPortalProfileNames.add(
				PortalProfile.PORTAL_PROFILE_NAME_DXP);
		}
		else {
			supportedPortalProfileNames = Collections.emptySet();

			BundleContext bundleContext = componentContext.getBundleContext();

			bundleContext.registerService(
				ClusterLink.class,
				ProxyFactory.newDummyInstance(ClusterLink.class),
				new HashMapDictionary<>());

			bundleContext.registerService(
				ClusterExecutor.class,
				ProxyFactory.newDummyInstance(ClusterExecutor.class),
				new HashMapDictionary<>());

			bundleContext.registerService(
				ClusterMasterExecutor.class,
				new ClusterMasterExecutor() {

					@Override
					public void addClusterMasterTokenTransitionListener(
						ClusterMasterTokenTransitionListener
							clusterMasterTokenAcquisitionListener) {
					}

					@Override
					public <T> NoticeableFuture<T> executeOnMaster(
						MethodHandler methodHandler) {

						return null;
					}

					@Override
					public boolean isEnabled() {
						return false;
					}

					@Override
					public boolean isMaster() {
						return true;
					}

					@Override
					public void removeClusterMasterTokenTransitionListener(
						ClusterMasterTokenTransitionListener
							clusterMasterTokenAcquisitionListener) {
					}

				},
				new HashMapDictionary<>());
		}

		init(
			componentContext, supportedPortalProfileNames,
			JGroupsClusterChannelFactory.class.getName(),
			ClusterExecutorImpl.class.getName(),
			ClusterLinkImpl.class.getName(),
			ClusterMasterExecutorImpl.class.getName(),
			ClusterMasterTokenTransitionListenerTracker.class.getName());
	}

	@Reference
	private Props _props;

}