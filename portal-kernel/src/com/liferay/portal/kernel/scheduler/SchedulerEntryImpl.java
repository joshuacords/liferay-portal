/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.scheduler;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;

/**
 * @author Shuyang Zhou
 */
public class SchedulerEntryImpl implements SchedulerEntry {

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #SchedulerEntryImpl(String, Trigger, String)}
	 */
	@Deprecated
	public SchedulerEntryImpl() {
		this(StringPool.BLANK, null, StringPool.BLANK);
	}

	public SchedulerEntryImpl(String eventListenerClass, Trigger trigger) {
		this(eventListenerClass, trigger, StringPool.BLANK);
	}

	public SchedulerEntryImpl(
		String eventListenerClass, Trigger trigger, String description) {

		_eventListenerClass = eventListenerClass;
		_trigger = trigger;
		_description = description;
	}

	@Override
	public String getDescription() {
		return _description;
	}

	@Override
	public String getEventListenerClass() {
		return _eventListenerClass;
	}

	@Override
	public Trigger getTrigger() {
		return _trigger;
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public void setDescription(String description) {
		_description = description;
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public void setEventListenerClass(String eventListenerClass) {
		_eventListenerClass = eventListenerClass;
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public void setTrigger(Trigger trigger) {
		_trigger = trigger;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append(", description=, eventListenerClass=");
		sb.append(_eventListenerClass);
		sb.append(", trigger=");
		sb.append(_trigger);
		sb.append("}");

		return sb.toString();
	}

	private String _description;
	private String _eventListenerClass;
	private Trigger _trigger;

}