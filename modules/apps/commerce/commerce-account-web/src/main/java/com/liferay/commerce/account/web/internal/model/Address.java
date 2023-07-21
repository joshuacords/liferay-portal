/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.web.internal.model;

/**
 * @author Alessio Antonio Rendina
 */
public class Address {

	public Address(
		long addressId, String address, String type, String referent,
		String phoneNumber) {

		_addressId = addressId;
		_address = address;
		_type = type;
		_referent = referent;
		_phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return _address;
	}

	public long getAddressId() {
		return _addressId;
	}

	public String getPhoneNumber() {
		return _phoneNumber;
	}

	public String getReferent() {
		return _referent;
	}

	public String getType() {
		return _type;
	}

	private final String _address;
	private final long _addressId;
	private final String _phoneNumber;
	private final String _referent;
	private final String _type;

}