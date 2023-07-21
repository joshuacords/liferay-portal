/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.user.associated.data.web.internal.configuration;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;

import java.io.IOException;

import java.util.Optional;

import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.cm.Configuration;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Drew Brokke
 */
@Component(immediate = true, service = ModelListener.class)
public class UserModelListener extends BaseModelListener<User> {

	@Override
	public void onAfterRemove(User user) throws ModelListenerException {
		try {
			if (user != null) {
				_deleteAnonymousUserConfiguration(
					user.getCompanyId(), user.getUserId());
			}
		}
		catch (InvalidSyntaxException | IOException exception) {
			throw new ModelListenerException(exception);
		}
	}

	private void _deleteAnonymousUserConfiguration(long companyId, long userId)
		throws InvalidSyntaxException, IOException {

		Optional<Configuration> configurationOptional =
			_anonymousUserConfigurationRetriever.getOptional(companyId, userId);

		if (!configurationOptional.isPresent()) {
			return;
		}

		Configuration configuration = configurationOptional.get();

		configuration.delete();
	}

	@Reference
	private AnonymousUserConfigurationRetriever
		_anonymousUserConfigurationRetriever;

}