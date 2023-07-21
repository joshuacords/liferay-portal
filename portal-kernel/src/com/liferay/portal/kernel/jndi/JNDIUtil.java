/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.jndi;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;

import javax.naming.Context;
import javax.naming.NamingException;

/**
 * @author Brian Wing Shun Chan
 * @author Sandeep Soni
 */
public class JNDIUtil {

	public static Object lookup(Context context, String location)
		throws NamingException {

		return _lookup(context, location);
	}

	private static Object _lookup(Context context, String location)
		throws NamingException {

		if (_log.isDebugEnabled()) {
			_log.debug("Lookup " + location);
		}

		Object obj = null;

		try {
			obj = context.lookup(location);
		}
		catch (NamingException namingException1) {

			// java:comp/env/ObjectName to ObjectName

			if (location.contains("java:comp/env/")) {
				try {
					String newLocation = StringUtil.removeSubstring(
						location, "java:comp/env/");

					if (_log.isDebugEnabled()) {
						_log.debug(namingException1.getMessage());
						_log.debug("Attempt " + newLocation);
					}

					obj = context.lookup(newLocation);
				}
				catch (NamingException namingException2) {

					// java:comp/env/ObjectName to java:ObjectName

					String newLocation = StringUtil.removeSubstring(
						location, "comp/env/");

					if (_log.isDebugEnabled()) {
						_log.debug(namingException2.getMessage());
						_log.debug("Attempt " + newLocation);
					}

					obj = context.lookup(newLocation);
				}
			}
			else if (location.contains("java:")) {

				// java:ObjectName to ObjectName

				try {
					String newLocation = StringUtil.removeSubstring(
						location, "java:");

					if (_log.isDebugEnabled()) {
						_log.debug(namingException1.getMessage());
						_log.debug("Attempt " + newLocation);
					}

					obj = context.lookup(newLocation);
				}
				catch (NamingException namingException2) {

					// java:ObjectName to java:comp/env/ObjectName

					String newLocation = StringUtil.replace(
						location, "java:", "java:comp/env/");

					if (_log.isDebugEnabled()) {
						_log.debug(namingException2.getMessage());
						_log.debug("Attempt " + newLocation);
					}

					obj = context.lookup(newLocation);
				}
			}
			else if (!location.contains("java:")) {

				// ObjectName to java:ObjectName

				try {
					String newLocation = "java:" + location;

					if (_log.isDebugEnabled()) {
						_log.debug(namingException1.getMessage());
						_log.debug("Attempt " + newLocation);
					}

					obj = context.lookup(newLocation);
				}
				catch (NamingException namingException2) {

					// ObjectName to java:comp/env/ObjectName

					String newLocation = "java:comp/env/" + location;

					if (_log.isDebugEnabled()) {
						_log.debug(namingException2.getMessage());
						_log.debug("Attempt " + newLocation);
					}

					obj = context.lookup(newLocation);
				}
			}
			else {
				throw new NamingException();
			}
		}

		return obj;
	}

	private static final Log _log = LogFactoryUtil.getLog(JNDIUtil.class);

}