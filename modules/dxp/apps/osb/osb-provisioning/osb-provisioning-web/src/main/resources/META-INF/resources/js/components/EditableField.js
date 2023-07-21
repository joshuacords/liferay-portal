/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import PropTypes from 'prop-types';
import React from 'react';

function EditableField({value}) {
	return (
		<div className="editable-field">
			<div className="field">{value}</div>
			<div className="edit-icon">
				<svg
					aria-label={Liferay.Language.get('edit-field-icon')}
					className="lexicon-icon-pencil"
					role="img"
				>
					<use xlinkHref="#pencil" />
				</svg>
			</div>
		</div>
	);
}

EditableField.propTypes = {
	value: PropTypes.oneOfType([PropTypes.bool, PropTypes.string]).isRequired
};

export default EditableField;
