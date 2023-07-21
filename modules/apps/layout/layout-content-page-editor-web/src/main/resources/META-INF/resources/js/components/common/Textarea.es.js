/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/* eslint no-unused-vars: "warn" */

import React from 'react';

const Textarea = props => (
	<textarea
		className={`form-control fragments-editor__textarea ${
			!props.value ? 'fragments-editor__textarea--empty' : ''
		}`}
		ref={textarea => props.autoFocus && textarea && textarea.focus()}
		{...props}
	/>
);

export {Textarea};
export default Textarea;
