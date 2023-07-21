/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the PollsChoice service. Represents a row in the &quot;PollsChoice&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PollsChoiceModel
 * @generated
 */
@ImplementationClassName("com.liferay.polls.model.impl.PollsChoiceImpl")
@ProviderType
public interface PollsChoice extends PersistedModel, PollsChoiceModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.polls.model.impl.PollsChoiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<PollsChoice, Long> CHOICE_ID_ACCESSOR =
		new Accessor<PollsChoice, Long>() {

			@Override
			public Long get(PollsChoice pollsChoice) {
				return pollsChoice.getChoiceId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<PollsChoice> getTypeClass() {
				return PollsChoice.class;
			}

		};

	public int getVotesCount();

}