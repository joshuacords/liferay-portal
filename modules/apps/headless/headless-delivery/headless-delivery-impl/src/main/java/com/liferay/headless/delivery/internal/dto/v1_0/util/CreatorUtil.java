/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.internal.dto.v1_0.util;

import com.liferay.headless.delivery.dto.v1_0.Creator;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;

import java.util.Optional;

import javax.ws.rs.core.UriInfo;

/**
 * @author Cristina González
 */
public class CreatorUtil {

	public static Creator toCreator(
		Portal portal, Optional<UriInfo> uriInfoOptional, User user) {

		if ((user == null) || user.isDefaultUser()) {
			return null;
		}

		return new Creator() {
			{
				additionalName = user.getMiddleName();
				contentType = "UserAccount";
				familyName = user.getLastName();
				givenName = user.getFirstName();
				id = user.getUserId();
				name = user.getFullName();

				setImage(
					() -> {
						if (user.getPortraitId() == 0) {
							return null;
						}

						ThemeDisplay themeDisplay = new ThemeDisplay() {
							{
								setPathImage(portal.getPathImage());
							}
						};

						return user.getPortraitURL(themeDisplay);
					});
				setProfileURL(
					() -> {
						if (uriInfoOptional.map(
								UriInfo::getQueryParameters
							).map(
								parameters -> parameters.getFirst(
									"nestedFields")
							).map(
								fields -> fields.contains("profileURL")
							).orElse(
								false
							)) {

							Group group = user.getGroup();

							ThemeDisplay themeDisplay = new ThemeDisplay() {
								{
									setPortalURL(StringPool.BLANK);
									setSiteGroupId(group.getGroupId());
								}
							};

							return group.getDisplayURL(themeDisplay);
						}

						return null;
					});
			}
		};
	}

}