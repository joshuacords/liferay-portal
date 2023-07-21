/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.commerce.admin.pricing.internal.resource.v2_0;

import com.liferay.headless.commerce.admin.pricing.dto.v2_0.Channel;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.DiscountChannel;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.PriceListChannel;
import com.liferay.headless.commerce.admin.pricing.internal.dto.v2_0.converter.ChannelDTOConverter;
import com.liferay.headless.commerce.admin.pricing.resource.v2_0.ChannelResource;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.fields.NestedFieldId;
import com.liferay.portal.vulcan.fields.NestedFieldSupport;

import javax.validation.constraints.NotNull;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v2_0/channel.properties",
	scope = ServiceScope.PROTOTYPE,
	service = {ChannelResource.class, NestedFieldSupport.class}
)
public class ChannelResourceImpl
	extends BaseChannelResourceImpl implements NestedFieldSupport {

	@NestedField(parentClass = DiscountChannel.class, value = "channel")
	@Override
	public Channel getDiscountIdChannel(
			@NestedFieldId(value = "channelId") @NotNull Long id)
		throws Exception {

		return _channelDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				id, contextAcceptLanguage.getPreferredLocale()));
	}

	@NestedField(parentClass = PriceListChannel.class, value = "channel")
	@Override
	public Channel getPriceListIdChannel(
			@NestedFieldId(value = "channelId") @NotNull Long id)
		throws Exception {

		return _channelDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				id, contextAcceptLanguage.getPreferredLocale()));
	}

	@Reference
	private ChannelDTOConverter _channelDTOConverter;

}