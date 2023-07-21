/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.LayoutSetVersion;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LayoutSetVersion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LayoutSetVersionCacheModel
	implements CacheModel<LayoutSetVersion>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LayoutSetVersionCacheModel)) {
			return false;
		}

		LayoutSetVersionCacheModel layoutSetVersionCacheModel =
			(LayoutSetVersionCacheModel)object;

		if (layoutSetVersionId ==
				layoutSetVersionCacheModel.layoutSetVersionId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, layoutSetVersionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{layoutSetVersionId=");
		sb.append(layoutSetVersionId);
		sb.append(", version=");
		sb.append(version);
		sb.append(", layoutSetId=");
		sb.append(layoutSetId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", privateLayout=");
		sb.append(privateLayout);
		sb.append(", logoId=");
		sb.append(logoId);
		sb.append(", themeId=");
		sb.append(themeId);
		sb.append(", colorSchemeId=");
		sb.append(colorSchemeId);
		sb.append(", css=");
		sb.append(css);
		sb.append(", pageCount=");
		sb.append(pageCount);
		sb.append(", settings=");
		sb.append(settings);
		sb.append(", layoutSetPrototypeUuid=");
		sb.append(layoutSetPrototypeUuid);
		sb.append(", layoutSetPrototypeLinkEnabled=");
		sb.append(layoutSetPrototypeLinkEnabled);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LayoutSetVersion toEntityModel() {
		LayoutSetVersionImpl layoutSetVersionImpl = new LayoutSetVersionImpl();

		layoutSetVersionImpl.setLayoutSetVersionId(layoutSetVersionId);
		layoutSetVersionImpl.setVersion(version);
		layoutSetVersionImpl.setLayoutSetId(layoutSetId);
		layoutSetVersionImpl.setGroupId(groupId);
		layoutSetVersionImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			layoutSetVersionImpl.setCreateDate(null);
		}
		else {
			layoutSetVersionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			layoutSetVersionImpl.setModifiedDate(null);
		}
		else {
			layoutSetVersionImpl.setModifiedDate(new Date(modifiedDate));
		}

		layoutSetVersionImpl.setPrivateLayout(privateLayout);
		layoutSetVersionImpl.setLogoId(logoId);

		if (themeId == null) {
			layoutSetVersionImpl.setThemeId("");
		}
		else {
			layoutSetVersionImpl.setThemeId(themeId);
		}

		if (colorSchemeId == null) {
			layoutSetVersionImpl.setColorSchemeId("");
		}
		else {
			layoutSetVersionImpl.setColorSchemeId(colorSchemeId);
		}

		if (css == null) {
			layoutSetVersionImpl.setCss("");
		}
		else {
			layoutSetVersionImpl.setCss(css);
		}

		layoutSetVersionImpl.setPageCount(pageCount);

		if (settings == null) {
			layoutSetVersionImpl.setSettings("");
		}
		else {
			layoutSetVersionImpl.setSettings(settings);
		}

		if (layoutSetPrototypeUuid == null) {
			layoutSetVersionImpl.setLayoutSetPrototypeUuid("");
		}
		else {
			layoutSetVersionImpl.setLayoutSetPrototypeUuid(
				layoutSetPrototypeUuid);
		}

		layoutSetVersionImpl.setLayoutSetPrototypeLinkEnabled(
			layoutSetPrototypeLinkEnabled);

		layoutSetVersionImpl.resetOriginalValues();

		return layoutSetVersionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		layoutSetVersionId = objectInput.readLong();

		version = objectInput.readInt();

		layoutSetId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		privateLayout = objectInput.readBoolean();

		logoId = objectInput.readLong();
		themeId = objectInput.readUTF();
		colorSchemeId = objectInput.readUTF();
		css = (String)objectInput.readObject();

		pageCount = objectInput.readInt();
		settings = (String)objectInput.readObject();
		layoutSetPrototypeUuid = objectInput.readUTF();

		layoutSetPrototypeLinkEnabled = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(layoutSetVersionId);

		objectOutput.writeInt(version);

		objectOutput.writeLong(layoutSetId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeBoolean(privateLayout);

		objectOutput.writeLong(logoId);

		if (themeId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(themeId);
		}

		if (colorSchemeId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(colorSchemeId);
		}

		if (css == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(css);
		}

		objectOutput.writeInt(pageCount);

		if (settings == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(settings);
		}

		if (layoutSetPrototypeUuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(layoutSetPrototypeUuid);
		}

		objectOutput.writeBoolean(layoutSetPrototypeLinkEnabled);
	}

	public long layoutSetVersionId;
	public int version;
	public long layoutSetId;
	public long groupId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public boolean privateLayout;
	public long logoId;
	public String themeId;
	public String colorSchemeId;
	public String css;
	public int pageCount;
	public String settings;
	public String layoutSetPrototypeUuid;
	public boolean layoutSetPrototypeLinkEnabled;

}