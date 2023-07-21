/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.opensocial.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the Gadget service. Represents a row in the &quot;OpenSocial_Gadget&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see GadgetModel
 * @generated
 */
@ImplementationClassName("com.liferay.opensocial.model.impl.GadgetImpl")
@ProviderType
public interface Gadget extends GadgetModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.opensocial.model.impl.GadgetImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Gadget, Long> GADGET_ID_ACCESSOR =
		new Accessor<Gadget, Long>() {

			@Override
			public Long get(Gadget gadget) {
				return gadget.getGadgetId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Gadget> getTypeClass() {
				return Gadget.class;
			}

		};

}