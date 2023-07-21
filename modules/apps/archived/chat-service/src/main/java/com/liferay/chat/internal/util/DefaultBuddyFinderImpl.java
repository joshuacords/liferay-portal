/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.chat.internal.util;

import com.liferay.chat.internal.configuration.ChatGroupServiceConfiguration;
import com.liferay.chat.internal.jabber.JabberUtil;
import com.liferay.chat.service.StatusLocalServiceUtil;
import com.liferay.chat.util.BuddyFinder;
import com.liferay.chat.util.comparator.BuddyComparator;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Ankit Srivastava
 * @author Tibor Lipusz
 * @author Peter Fellwock
 */
@Component(
	configurationPid = "com.liferay.chat.internal.configuration.ChatGroupServiceConfiguration",
	enabled = false, immediate = true, service = BuddyFinder.class
)
public class DefaultBuddyFinderImpl implements BuddyFinder {

	@Override
	public List<Object[]> getBuddies(long companyId, long userId) {
		long modifiedDate =
			System.currentTimeMillis() - ChatConstants.ONLINE_DELTA;

		List<Object[]> buddies = null;

		String buddyListStrategy =
			_chatGroupServiceConfiguration.buddyListStrategy();

		if (buddyListStrategy.equals("all")) {
			buddies = StatusLocalServiceUtil.getAllStatuses(
				companyId, userId, modifiedDate, 0,
				_chatGroupServiceConfiguration.buddyListMaxBuddies());
		}
		else if (buddyListStrategy.equals("communities") ||
				 buddyListStrategy.equals("sites")) {

			buddies = StatusLocalServiceUtil.getGroupStatuses(
				userId, modifiedDate,
				_chatGroupServiceConfiguration.buddyListSiteExcludes(), 0,
				_chatGroupServiceConfiguration.buddyListMaxBuddies());
		}
		else if (buddyListStrategy.equals("friends") ||
				 buddyListStrategy.equals("social")) {

			buddies = StatusLocalServiceUtil.getSocialStatuses(
				userId,
				_chatGroupServiceConfiguration.
					buddyListAllowedSocialRelationTypes(),
				modifiedDate, 0,
				_chatGroupServiceConfiguration.buddyListMaxBuddies());
		}
		else if (buddyListStrategy.equals("communities,friends") ||
				 buddyListStrategy.equals("sites,social") ||
				 buddyListStrategy.equals("friends,sites")) {

			List<Object[]> groupBuddies =
				StatusLocalServiceUtil.getGroupStatuses(
					userId, modifiedDate,
					_chatGroupServiceConfiguration.buddyListSiteExcludes(), 0,
					_chatGroupServiceConfiguration.buddyListMaxBuddies());
			List<Object[]> socialBuddies =
				StatusLocalServiceUtil.getSocialStatuses(
					userId,
					_chatGroupServiceConfiguration.
						buddyListAllowedSocialRelationTypes(),
					modifiedDate, 0,
					_chatGroupServiceConfiguration.buddyListMaxBuddies());

			buddies = new ArrayList<>(
				groupBuddies.size() + socialBuddies.size());

			buddies.addAll(groupBuddies);

			BuddyComparator buddyComparator = new BuddyComparator(true);

			for (Object[] socialBuddy : socialBuddies) {
				int count = Collections.binarySearch(
					groupBuddies, socialBuddy, buddyComparator);

				if (count < 0) {
					buddies.add(socialBuddy);
				}
			}

			Collections.sort(buddies, buddyComparator);
		}
		else {
			buddies = new ArrayList<>();
		}

		return JabberUtil.getStatuses(companyId, userId, buddies);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_chatGroupServiceConfiguration = ConfigurableUtil.createConfigurable(
			ChatGroupServiceConfiguration.class, properties);
	}

	private ChatGroupServiceConfiguration _chatGroupServiceConfiguration;

}