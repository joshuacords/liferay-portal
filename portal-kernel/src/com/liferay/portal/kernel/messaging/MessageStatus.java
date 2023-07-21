/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.messaging;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.StackTraceUtil;

import java.io.Serializable;

/**
 * @author Michael C. Han
 */
public class MessageStatus implements Serializable {

	public long getDuration() {
		return _endTime - _startTime;
	}

	public String getExceptionMessage() {
		return _exceptionMessage;
	}

	public String getExceptionStackTrace() {
		return _exceptionStackTrace;
	}

	public Object getPayload() {
		return _payload;
	}

	public boolean hasException() {
		if (_exceptionStackTrace != null) {
			return true;
		}

		return false;
	}

	public void setException(Exception exception) {
		_exceptionMessage = exception.getMessage();
		_exceptionStackTrace = StackTraceUtil.getStackTrace(exception);
	}

	public void setPayload(Object payload) {
		_payload = payload;
	}

	public void startTimer() {
		_startTime = System.currentTimeMillis();
	}

	public void stopTimer() {
		_endTime = System.currentTimeMillis();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{startTime=");
		sb.append(_startTime);
		sb.append(", endTime=");
		sb.append(_endTime);
		sb.append(", payload=");
		sb.append(_payload);
		sb.append(", errorMessage=");
		sb.append(_exceptionMessage);
		sb.append(", errorStackTrace=");
		sb.append(_exceptionStackTrace);
		sb.append("}");

		return sb.toString();
	}

	private long _endTime;
	private String _exceptionMessage;
	private String _exceptionStackTrace;
	private Object _payload;
	private long _startTime;

}