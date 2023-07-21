/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.address;

import com.liferay.commerce.address.CommerceAddressFormatter;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alec Sloan
 */
@Component(immediate = true, service = CommerceAddressFormatter.class)
public class CommerceAddressFormatterImpl implements CommerceAddressFormatter {

	@Override
	public String getBasicAddress(CommerceAddress commerceAddress)
		throws PortalException {

		StringBundler sb = new StringBundler(14);

		sb.append(commerceAddress.getStreet1());
		sb.append(StringPool.NEW_LINE);

		if (Validator.isNotNull(commerceAddress.getStreet2())) {
			sb.append(commerceAddress.getStreet2());
			sb.append(StringPool.NEW_LINE);
		}

		if (Validator.isNotNull(commerceAddress.getStreet3())) {
			sb.append(commerceAddress.getStreet2());
			sb.append(StringPool.NEW_LINE);
		}

		sb.append(commerceAddress.getCity());
		sb.append(StringPool.SPACE);

		CommerceRegion commerceRegion = commerceAddress.getCommerceRegion();

		if (commerceRegion != null) {
			sb.append(commerceRegion.getCode());
			sb.append(StringPool.SPACE);
		}

		sb.append(commerceAddress.getZip());
		sb.append(StringPool.NEW_LINE);

		CommerceCountry commerceCountry = commerceAddress.getCommerceCountry();

		if (commerceCountry != null) {
			sb.append(commerceCountry.getName());
			sb.append(StringPool.NEW_LINE);
		}

		return sb.toString();
	}

	@Override
	public String getDescriptiveAddress(
			CommerceAddress commerceAddress, boolean showDescription)
		throws PortalException {

		StringBundler sb = new StringBundler(8);

		sb.append(commerceAddress.getName());
		sb.append(StringPool.NEW_LINE);

		if (Validator.isNotNull(commerceAddress.getPhoneNumber())) {
			sb.append(commerceAddress.getPhoneNumber());
			sb.append(StringPool.NEW_LINE);
		}

		sb.append(getBasicAddress(commerceAddress));

		String description = commerceAddress.getDescription();

		if ((description != null) && showDescription) {
			sb.append(StringPool.NEW_LINE);
			sb.append(StringPool.NEW_LINE);
			sb.append(description);
		}

		return sb.toString();
	}

	@Override
	public String getOneLineAddress(CommerceAddress commerceAddress)
		throws PortalException {

		StringBundler sb = new StringBundler(7);

		sb.append(commerceAddress.getStreet1());
		sb.append(StringPool.COMMA_AND_SPACE);

		sb.append(commerceAddress.getCity());
		sb.append(StringPool.SPACE);

		CommerceRegion commerceRegion = commerceAddress.getCommerceRegion();

		if (commerceRegion != null) {
			sb.append(commerceRegion.getCode());
			sb.append(StringPool.SPACE);
		}

		sb.append(commerceAddress.getZip());

		return sb.toString();
	}

}