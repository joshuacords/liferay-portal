/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.model;

/**
 * @author Alessio Antonio Rendina
 * @author Alec Sloan
 */
public class Shipment {

	public Shipment(
		String accountName, String address, String channelName,
		String createDate, String expectedDeliveryDate, String expectedShipDate,
		long shipmentId, LabelField status, String tracking) {

		_accountName = accountName;
		_address = address;
		_channelName = channelName;
		_createDate = createDate;
		_expectedDeliveryDate = expectedDeliveryDate;
		_expectedShipDate = expectedShipDate;
		_shipmentId = shipmentId;
		_status = status;
		_tracking = tracking;
	}

	public String getAccountName() {
		return _accountName;
	}

	public String getAddress() {
		return _address;
	}

	public String getChannelName() {
		return _channelName;
	}

	public String getCreateDate() {
		return _createDate;
	}

	public String getExpectedDeliveryDate() {
		return _expectedDeliveryDate;
	}

	public String getExpectedShipDate() {
		return _expectedShipDate;
	}

	public long getShipmentId() {
		return _shipmentId;
	}

	public LabelField getStatus() {
		return _status;
	}

	public String getTracking() {
		return _tracking;
	}

	private final String _accountName;
	private final String _address;
	private final String _channelName;
	private final String _createDate;
	private final String _expectedDeliveryDate;
	private final String _expectedShipDate;
	private final long _shipmentId;
	private final LabelField _status;
	private final String _tracking;

}