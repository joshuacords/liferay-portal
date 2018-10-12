/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.dao.orm.hibernate;

import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.ObjectNotFoundException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.model.impl.UserImpl;

import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;

/**
 * @author Brian Wing Shun Chan
 */
public class ExceptionTranslator {

	public static ORMException translate(Exception e) {
		if (e instanceof org.hibernate.ObjectNotFoundException) {
			return new ObjectNotFoundException(e);
		}
		else {
			return new ORMException(e);
		}
	}

	public static ORMException translate(
		Exception e, Session session, Object object) {

		if (e instanceof StaleObjectStateException && _log.isDebugEnabled()) {
			BaseModel<?> baseModel = (BaseModel<?>)object;

			Object currentObject = session.get(
				object.getClass(), baseModel.getPrimaryKeyObj());

			if (object instanceof UserImpl) {
				translateUserSOSE(object, currentObject, e);
			}

			return new ORMException(
				object + " is stale in comparison to " + currentObject, e);
		}
		else {
			return new ORMException(e);
		}
	}

	private static String hideField(String currField) {
		if (currField != null && currField != "") {
			return "****";
		}

		return "";
	}

	private static ORMException translateUserSOSE(
		Object object, Object currentObject, Exception e) {

		User tempUser = (User)object;
		User tempCurrUser = (User)currentObject;

		for (User user : new User[] {tempUser, tempCurrUser}) {
			user.setPassword(hideField(user.getPassword()));
			user.setDigest(hideField(user.getDigest()));
			user.setReminderQueryQuestion(
				hideField(user.getReminderQueryQuestion()));
			user.setReminderQueryAnswer(
				hideField(user.getReminderQueryAnswer()));
			user.setScreenName(hideField(user.getScreenName()));
			user.setEmailAddress(hideField(user.getEmailAddress()));
			user.setFacebookId((user.getFacebookId() != 0) ? 0 : -1);
			user.setGoogleUserId(hideField(user.getGoogleUserId()));
			user.setGreeting(hideField(user.getGreeting()));
			user.setFirstName(hideField(user.getFirstName()));
			user.setMiddleName(hideField(user.getMiddleName()));
			user.setLastName(hideField(user.getLastName()));
			user.setJobTitle(hideField(user.getJobTitle()));
		}

		return new ORMException(
			tempUser + " is stale in comparison to " + tempCurrUser, e);
	}

	private static final Log _log = LogFactoryUtil.getLog(ExceptionTranslator.class);
}