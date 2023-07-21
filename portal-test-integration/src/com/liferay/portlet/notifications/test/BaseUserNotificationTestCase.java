/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.notifications.test;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserNotificationDelivery;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.service.UserNotificationDeliveryLocalServiceUtil;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.test.mail.MailServiceTestUtil;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author     Roberto Díaz
 * @author     Sergio González
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Deprecated
public abstract class BaseUserNotificationTestCase {

	@Before
	public void setUp() throws Exception {
		user = UserTestUtil.addOmniAdminUser();

		group = GroupTestUtil.addGroup();

		addContainerModel();

		userNotificationDeliveries = getUserNotificationDeliveries(
			user.getUserId());
	}

	@After
	public void tearDown() throws Exception {
		deleteUserNotificationEvents(user.getUserId());

		deleteUserNotificationDeliveries();
	}

	@Test
	public void testAddUserNotification() throws Exception {
		subscribeToContainer();

		BaseModel<?> baseModel = addBaseModel();

		Assert.assertEquals(1, MailServiceTestUtil.getInboxSize());

		List<JSONObject> userNotificationEventsJSONObjects =
			getUserNotificationEventsJSONObjects(
				user.getUserId(), (Long)baseModel.getPrimaryKeyObj());

		Assert.assertEquals(
			userNotificationEventsJSONObjects.toString(), 1,
			userNotificationEventsJSONObjects.size());

		for (JSONObject userNotificationEventsJSONObject :
				userNotificationEventsJSONObjects) {

			Assert.assertTrue(
				isValidUserNotificationEventObject(
					(Long)baseModel.getPrimaryKeyObj(),
					userNotificationEventsJSONObject));
			Assert.assertEquals(
				UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY,
				userNotificationEventsJSONObject.getInt("notificationType"));
		}
	}

	@Test
	public void testAddUserNotificationWhenEmailNotificationsDisabled()
		throws Exception {

		subscribeToContainer();

		updateUserNotificationDelivery(
			UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY,
			UserNotificationDeliveryConstants.TYPE_EMAIL, false);

		BaseModel<?> baseModel = addBaseModel();

		Assert.assertEquals(0, MailServiceTestUtil.getInboxSize());

		List<JSONObject> userNotificationEventsJSONObjects =
			getUserNotificationEventsJSONObjects(
				user.getUserId(), (Long)baseModel.getPrimaryKeyObj());

		Assert.assertEquals(
			userNotificationEventsJSONObjects.toString(), 1,
			userNotificationEventsJSONObjects.size());

		for (JSONObject userNotificationEventsJSONObject :
				userNotificationEventsJSONObjects) {

			Assert.assertTrue(
				isValidUserNotificationEventObject(
					(Long)baseModel.getPrimaryKeyObj(),
					userNotificationEventsJSONObject));
			Assert.assertEquals(
				UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY,
				userNotificationEventsJSONObject.getInt("notificationType"));
		}
	}

	@Test
	public void testAddUserNotificationWhenNotificationsDisabled()
		throws Exception {

		subscribeToContainer();

		updateUserNotificationsDelivery(false);

		BaseModel<?> baseModel = addBaseModel();

		Assert.assertEquals(0, MailServiceTestUtil.getInboxSize());

		List<JSONObject> userNotificationEventsJSONObjects =
			getUserNotificationEventsJSONObjects(
				user.getUserId(), (Long)baseModel.getPrimaryKeyObj());

		Assert.assertEquals(
			userNotificationEventsJSONObjects.toString(), 0,
			userNotificationEventsJSONObjects.size());
	}

	@Test
	public void testAddUserNotificationWhenWebsiteNotificationsDisabled()
		throws Exception {

		subscribeToContainer();

		updateUserNotificationDelivery(
			UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY,
			UserNotificationDeliveryConstants.TYPE_WEBSITE, false);

		BaseModel<?> baseModel = addBaseModel();

		Assert.assertEquals(1, MailServiceTestUtil.getInboxSize());

		List<JSONObject> userNotificationEventsJSONObjects =
			getUserNotificationEventsJSONObjects(
				user.getUserId(), (Long)baseModel.getPrimaryKeyObj());

		Assert.assertEquals(
			userNotificationEventsJSONObjects.toString(), 0,
			userNotificationEventsJSONObjects.size());
	}

	@Test
	public void testUpdateUserNotification() throws Exception {
		BaseModel<?> baseModel = addBaseModel();

		subscribeToContainer();

		BaseModel<?> updatedBasemodel = updateBaseModel(baseModel);

		Assert.assertEquals(1, MailServiceTestUtil.getInboxSize());

		List<JSONObject> userNotificationEventsJSONObjects =
			getUserNotificationEventsJSONObjects(
				user.getUserId(), (Long)updatedBasemodel.getPrimaryKeyObj());

		Assert.assertEquals(
			userNotificationEventsJSONObjects.toString(), 1,
			userNotificationEventsJSONObjects.size());

		int notificationType = -1;

		for (JSONObject userNotificationEventsJSONObject :
				userNotificationEventsJSONObjects) {

			Assert.assertTrue(
				isValidUserNotificationEventObject(
					(Long)updatedBasemodel.getPrimaryKeyObj(),
					userNotificationEventsJSONObject));

			notificationType = userNotificationEventsJSONObject.getInt(
				"notificationType");

			Assert.assertEquals(
				notificationType,
				UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY);
		}
	}

	@Test
	public void testUpdateUserNotificationWhenEmailNotificationsDisabled()
		throws Exception {

		updateUserNotificationDelivery(
			UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY,
			UserNotificationDeliveryConstants.TYPE_EMAIL, false);
		updateUserNotificationDelivery(
			UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY,
			UserNotificationDeliveryConstants.TYPE_EMAIL, false);

		BaseModel<?> baseModel = addBaseModel();

		subscribeToContainer();

		BaseModel<?> updatedBasemodel = updateBaseModel(baseModel);

		Assert.assertEquals(0, MailServiceTestUtil.getInboxSize());

		List<JSONObject> userNotificationEventsJSONObjects =
			getUserNotificationEventsJSONObjects(
				user.getUserId(), (Long)updatedBasemodel.getPrimaryKeyObj());

		Assert.assertEquals(
			userNotificationEventsJSONObjects.toString(), 1,
			userNotificationEventsJSONObjects.size());

		int notificationType = -1;

		for (JSONObject userNotificationEventsJSONObject :
				userNotificationEventsJSONObjects) {

			Assert.assertTrue(
				isValidUserNotificationEventObject(
					(Long)updatedBasemodel.getPrimaryKeyObj(),
					userNotificationEventsJSONObject));

			notificationType = userNotificationEventsJSONObject.getInt(
				"notificationType");

			Assert.assertEquals(
				notificationType,
				UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY);
		}
	}

	@Test
	public void testUpdateUserNotificationWhenNotificationsDisabled()
		throws Exception {

		updateUserNotificationsDelivery(false);

		BaseModel<?> baseModel = addBaseModel();

		subscribeToContainer();

		BaseModel<?> updatedBasemodel = updateBaseModel(baseModel);

		Assert.assertEquals(0, MailServiceTestUtil.getInboxSize());

		List<JSONObject> userNotificationEventsJSONObjects =
			getUserNotificationEventsJSONObjects(
				user.getUserId(), (Long)updatedBasemodel.getPrimaryKeyObj());

		Assert.assertEquals(
			userNotificationEventsJSONObjects.toString(), 0,
			userNotificationEventsJSONObjects.size());
	}

	@Test
	public void testUpdateUserNotificationWhenWebsiteNotificationsDisabled()
		throws Exception {

		updateUserNotificationDelivery(
			UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY,
			UserNotificationDeliveryConstants.TYPE_WEBSITE, false);
		updateUserNotificationDelivery(
			UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY,
			UserNotificationDeliveryConstants.TYPE_WEBSITE, false);

		BaseModel<?> baseModel = addBaseModel();

		subscribeToContainer();

		BaseModel<?> updatedBasemodel = updateBaseModel(baseModel);

		Assert.assertEquals(1, MailServiceTestUtil.getInboxSize());

		List<JSONObject> userNotificationEventsJSONObjects =
			getUserNotificationEventsJSONObjects(
				user.getUserId(), (Long)updatedBasemodel.getPrimaryKeyObj());

		Assert.assertEquals(
			userNotificationEventsJSONObjects.toString(), 0,
			userNotificationEventsJSONObjects.size());
	}

	protected abstract BaseModel<?> addBaseModel() throws Exception;

	protected void addContainerModel() throws Exception {
	}

	protected void deleteUserNotificationDeliveries() throws Exception {
		UserNotificationDeliveryLocalServiceUtil.
			deleteUserNotificationDeliveries(user.getUserId());
	}

	protected void deleteUserNotificationEvents(long userId) throws Exception {
		List<UserNotificationEvent> userNotificationEvents =
			UserNotificationEventLocalServiceUtil.getUserNotificationEvents(
				userId);

		for (UserNotificationEvent userNotificationEvent :
				userNotificationEvents) {

			UserNotificationEventLocalServiceUtil.deleteUserNotificationEvent(
				userNotificationEvent);
		}
	}

	protected abstract String getPortletId();

	protected List<UserNotificationDelivery> getUserNotificationDeliveries(
			long userId)
		throws Exception {

		return ListUtil.fromArray(
			UserNotificationDeliveryLocalServiceUtil.
				getUserNotificationDelivery(
					userId, getPortletId(), 0,
					UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY,
					UserNotificationDeliveryConstants.TYPE_EMAIL, true),
			UserNotificationDeliveryLocalServiceUtil.
				getUserNotificationDelivery(
					userId, getPortletId(), 0,
					UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY,
					UserNotificationDeliveryConstants.TYPE_WEBSITE, true),
			UserNotificationDeliveryLocalServiceUtil.
				getUserNotificationDelivery(
					userId, getPortletId(), 0,
					UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY,
					UserNotificationDeliveryConstants.TYPE_EMAIL, true),
			UserNotificationDeliveryLocalServiceUtil.
				getUserNotificationDelivery(
					userId, getPortletId(), 0,
					UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY,
					UserNotificationDeliveryConstants.TYPE_WEBSITE, true));
	}

	protected List<JSONObject> getUserNotificationEventsJSONObjects(
			long userId, long primaryKey)
		throws Exception {

		List<UserNotificationEvent> userNotificationEvents =
			UserNotificationEventLocalServiceUtil.getUserNotificationEvents(
				userId);

		List<JSONObject> userNotificationEventJSONObjects = new ArrayList<>(
			userNotificationEvents.size());

		for (UserNotificationEvent userNotificationEvent :
				userNotificationEvents) {

			JSONObject userNotificationEventJSONObject =
				JSONFactoryUtil.createJSONObject(
					userNotificationEvent.getPayload());

			userNotificationEventJSONObjects.add(
				userNotificationEventJSONObject);
		}

		return userNotificationEventJSONObjects;
	}

	protected boolean isValidUserNotificationEventObject(
			long primaryKey, JSONObject userNotificationEventJSONObject)
		throws Exception {

		long classPK = userNotificationEventJSONObject.getLong("classPK");

		if (classPK != primaryKey) {
			return false;
		}

		return true;
	}

	protected abstract void subscribeToContainer() throws Exception;

	protected abstract BaseModel<?> updateBaseModel(BaseModel<?> baseModel)
		throws Exception;

	protected void updateUserNotificationDelivery(
			int notificationType, int deliveryType, boolean deliver)
		throws Exception {

		boolean exists = false;

		for (UserNotificationDelivery userNotificationDelivery :
				userNotificationDeliveries) {

			if ((userNotificationDelivery.getNotificationType() !=
					notificationType) ||
				(userNotificationDelivery.getDeliveryType() != deliveryType)) {

				continue;
			}

			UserNotificationDeliveryLocalServiceUtil.
				updateUserNotificationDelivery(
					userNotificationDelivery.getUserNotificationDeliveryId(),
					deliver);

			exists = true;

			break;
		}

		Assert.assertTrue("User notification does not exist", exists);
	}

	protected void updateUserNotificationsDelivery(boolean deliver)
		throws Exception {

		for (UserNotificationDelivery userNotificationDelivery :
				userNotificationDeliveries) {

			UserNotificationDeliveryLocalServiceUtil.
				updateUserNotificationDelivery(
					userNotificationDelivery.getUserNotificationDeliveryId(),
					deliver);
		}
	}

	@DeleteAfterTestRun
	protected Group group;

	@DeleteAfterTestRun
	protected User user;

	protected List<UserNotificationDelivery> userNotificationDeliveries =
		new ArrayList<>();

}