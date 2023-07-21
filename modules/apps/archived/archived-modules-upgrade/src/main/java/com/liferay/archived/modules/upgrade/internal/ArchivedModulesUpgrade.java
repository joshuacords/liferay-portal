/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.archived.modules.upgrade.internal;

import com.liferay.message.boards.service.MBThreadLocalService;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.service.ImageLocalService;
import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import java.util.Map;
import java.util.function.Supplier;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(
	configurationPid = "com.liferay.archived.modules.upgrade.internal.ArchivedModulesUpgradeConfiguration",
	immediate = true, service = UpgradeStepRegistrator.class
)
public class ArchivedModulesUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		try {
			_removeModuleData(
				_archivedModulesUpgradeConfiguration::removeChatModuleData,
				"com.liferay.chat.service", UpgradeChat::new);

			_removeModuleData(
				_archivedModulesUpgradeConfiguration::
					removeInvitationModuleData,
				"com.liferay.invitation.web", UpgradeInvitation::new);

			_removeModuleData(
				_archivedModulesUpgradeConfiguration::
					removeMailReaderModuleData,
				"com.liferay.mail.reader.service", UpgradeMailReader::new);

			_removeModuleData(
				_archivedModulesUpgradeConfiguration::
					removePrivateMessagingModuleData,
				"com.liferay.social.privatemessaging.service",
				() -> new UpgradePrivateMessaging(_mbThreadLocalService));

			_removeModuleData(
				_archivedModulesUpgradeConfiguration::removeShoppingModuleData,
				"com.liferay.shopping.service",
				() -> new UpgradeShopping(_imageLocalService));

			_removeModuleData(
				_archivedModulesUpgradeConfiguration::removeTwitterModuleData,
				"com.liferay.twitter.service", UpgradeTwitter::new);
		}
		catch (UpgradeException upgradeException) {
			ReflectionUtil.throwException(upgradeException);
		}
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_archivedModulesUpgradeConfiguration =
			ConfigurableUtil.createConfigurable(
				ArchivedModulesUpgradeConfiguration.class, properties);
	}

	private void _removeModuleData(
			Supplier<Boolean> booleanSupplier, String servletContextName,
			Supplier<UpgradeProcess> upgradeProcessSupplier)
		throws UpgradeException {

		if (booleanSupplier.get()) {
			Release release = _releaseLocalService.fetchRelease(
				servletContextName);

			if (release != null) {
				UpgradeProcess upgradeProcess = upgradeProcessSupplier.get();

				upgradeProcess.upgrade();

				CacheRegistryUtil.clear();
			}
		}
	}

	private ArchivedModulesUpgradeConfiguration
		_archivedModulesUpgradeConfiguration;

	@Reference
	private ImageLocalService _imageLocalService;

	@Reference
	private MBThreadLocalService _mbThreadLocalService;

	@Reference
	private ReleaseLocalService _releaseLocalService;

}