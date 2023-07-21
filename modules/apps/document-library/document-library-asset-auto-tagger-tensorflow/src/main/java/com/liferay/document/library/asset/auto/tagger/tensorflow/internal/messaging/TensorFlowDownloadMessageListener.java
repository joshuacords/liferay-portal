/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.asset.auto.tagger.tensorflow.internal.messaging;

import com.liferay.document.library.asset.auto.tagger.tensorflow.internal.configuration.TensorFlowImageAssetAutoTagProviderDownloadConfiguration;
import com.liferay.document.library.asset.auto.tagger.tensorflow.internal.constants.TensorFlowDestinationNames;
import com.liferay.document.library.asset.auto.tagger.tensorflow.internal.util.TensorFlowDownloadUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Alejandro Tardín
 */
@Component(
	configurationPid = "com.liferay.document.library.asset.auto.tagger.tensorflow.internal.configuration.TensorFlowImageAssetAutoTagProviderDownloadConfiguration",
	immediate = true,
	property = "destination.name=" + TensorFlowDestinationNames.TENSORFLOW_MODEL_DOWNLOAD,
	service = MessageListener.class
)
public class TensorFlowDownloadMessageListener extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_tensorFlowImageAssetAutoTagProviderDownloadConfiguration =
			ConfigurableUtil.createConfigurable(
				TensorFlowImageAssetAutoTagProviderDownloadConfiguration.class,
				properties);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		TensorFlowDownloadUtil.download(
			_tensorFlowImageAssetAutoTagProviderDownloadConfiguration);
	}

	private volatile TensorFlowImageAssetAutoTagProviderDownloadConfiguration
		_tensorFlowImageAssetAutoTagProviderDownloadConfiguration;

}