/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.inventory.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.commerce.inventory.service.http.CommerceInventoryReplenishmentItemServiceSoap}.
 *
 * @author Luca Pellizzon
 * @generated
 */
public class CommerceInventoryReplenishmentItemSoap implements Serializable {

	public static CommerceInventoryReplenishmentItemSoap toSoapModel(
		CommerceInventoryReplenishmentItem model) {

		CommerceInventoryReplenishmentItemSoap soapModel =
			new CommerceInventoryReplenishmentItemSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setCommerceInventoryReplenishmentItemId(
			model.getCommerceInventoryReplenishmentItemId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCommerceInventoryWarehouseId(
			model.getCommerceInventoryWarehouseId());
		soapModel.setSku(model.getSku());
		soapModel.setAvailabilityDate(model.getAvailabilityDate());
		soapModel.setQuantity(model.getQuantity());

		return soapModel;
	}

	public static CommerceInventoryReplenishmentItemSoap[] toSoapModels(
		CommerceInventoryReplenishmentItem[] models) {

		CommerceInventoryReplenishmentItemSoap[] soapModels =
			new CommerceInventoryReplenishmentItemSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceInventoryReplenishmentItemSoap[][] toSoapModels(
		CommerceInventoryReplenishmentItem[][] models) {

		CommerceInventoryReplenishmentItemSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommerceInventoryReplenishmentItemSoap
				[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceInventoryReplenishmentItemSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceInventoryReplenishmentItemSoap[] toSoapModels(
		List<CommerceInventoryReplenishmentItem> models) {

		List<CommerceInventoryReplenishmentItemSoap> soapModels =
			new ArrayList<CommerceInventoryReplenishmentItemSoap>(
				models.size());

		for (CommerceInventoryReplenishmentItem model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new CommerceInventoryReplenishmentItemSoap[soapModels.size()]);
	}

	public CommerceInventoryReplenishmentItemSoap() {
	}

	public long getPrimaryKey() {
		return _commerceInventoryReplenishmentItemId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceInventoryReplenishmentItemId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getCommerceInventoryReplenishmentItemId() {
		return _commerceInventoryReplenishmentItemId;
	}

	public void setCommerceInventoryReplenishmentItemId(
		long commerceInventoryReplenishmentItemId) {

		_commerceInventoryReplenishmentItemId =
			commerceInventoryReplenishmentItemId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getCommerceInventoryWarehouseId() {
		return _commerceInventoryWarehouseId;
	}

	public void setCommerceInventoryWarehouseId(
		long commerceInventoryWarehouseId) {

		_commerceInventoryWarehouseId = commerceInventoryWarehouseId;
	}

	public String getSku() {
		return _sku;
	}

	public void setSku(String sku) {
		_sku = sku;
	}

	public Date getAvailabilityDate() {
		return _availabilityDate;
	}

	public void setAvailabilityDate(Date availabilityDate) {
		_availabilityDate = availabilityDate;
	}

	public int getQuantity() {
		return _quantity;
	}

	public void setQuantity(int quantity) {
		_quantity = quantity;
	}

	private long _mvccVersion;
	private long _commerceInventoryReplenishmentItemId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _commerceInventoryWarehouseId;
	private String _sku;
	private Date _availabilityDate;
	private int _quantity;

}