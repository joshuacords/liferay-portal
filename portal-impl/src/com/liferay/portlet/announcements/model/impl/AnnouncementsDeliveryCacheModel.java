/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.announcements.model.impl;

import com.liferay.announcements.kernel.model.AnnouncementsDelivery;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing AnnouncementsDelivery in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AnnouncementsDeliveryCacheModel
	implements CacheModel<AnnouncementsDelivery>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AnnouncementsDeliveryCacheModel)) {
			return false;
		}

		AnnouncementsDeliveryCacheModel announcementsDeliveryCacheModel =
			(AnnouncementsDeliveryCacheModel)object;

		if (deliveryId == announcementsDeliveryCacheModel.deliveryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, deliveryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{deliveryId=");
		sb.append(deliveryId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", email=");
		sb.append(email);
		sb.append(", sms=");
		sb.append(sms);
		sb.append(", website=");
		sb.append(website);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AnnouncementsDelivery toEntityModel() {
		AnnouncementsDeliveryImpl announcementsDeliveryImpl =
			new AnnouncementsDeliveryImpl();

		announcementsDeliveryImpl.setDeliveryId(deliveryId);
		announcementsDeliveryImpl.setCompanyId(companyId);
		announcementsDeliveryImpl.setUserId(userId);

		if (type == null) {
			announcementsDeliveryImpl.setType("");
		}
		else {
			announcementsDeliveryImpl.setType(type);
		}

		announcementsDeliveryImpl.setEmail(email);
		announcementsDeliveryImpl.setSms(sms);
		announcementsDeliveryImpl.setWebsite(website);

		announcementsDeliveryImpl.resetOriginalValues();

		return announcementsDeliveryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		deliveryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		type = objectInput.readUTF();

		email = objectInput.readBoolean();

		sms = objectInput.readBoolean();

		website = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(deliveryId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeBoolean(email);

		objectOutput.writeBoolean(sms);

		objectOutput.writeBoolean(website);
	}

	public long deliveryId;
	public long companyId;
	public long userId;
	public String type;
	public boolean email;
	public boolean sms;
	public boolean website;

}