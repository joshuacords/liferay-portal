/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;
import com.liferay.portal.kernel.util.LocaleThreadLocal;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the MDRAction service. Represents a row in the &quot;MDRAction&quot; database table, with each column mapped to a property of this class.
 *
 * @author Edward C. Han
 * @see MDRActionModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.mobile.device.rules.model.impl.MDRActionImpl"
)
@ProviderType
public interface MDRAction extends MDRActionModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.mobile.device.rules.model.impl.MDRActionImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MDRAction, Long> ACTION_ID_ACCESSOR =
		new Accessor<MDRAction, Long>() {

			@Override
			public Long get(MDRAction mdrAction) {
				return mdrAction.getActionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<MDRAction> getTypeClass() {
				return MDRAction.class;
			}

		};
	public static final Accessor<MDRAction, String> NAME_ACCESSOR =
		new Accessor<MDRAction, String>() {

			@Override
			public String get(MDRAction mdrAction) {
				return mdrAction.getName(
					LocaleThreadLocal.getThemeDisplayLocale());
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<MDRAction> getTypeClass() {
				return MDRAction.class;
			}

		};

	public com.liferay.portal.kernel.util.UnicodeProperties
		getTypeSettingsProperties();

	public void setTypeSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties
			typeSettingsProperties);

}