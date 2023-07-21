/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

function processQuery(query, text = '') {
	const regex = new RegExp(`(.*?)(${query || ''})(.*)`, 'gmi');
	const results = regex.exec(text);

	return results
		? Array(3)
				.fill('')
				.map((_, i) => results[i + 1].toString())
		: [text, '', ''];
}

export default function HighlightedText(props) {
	const [firstPart, highlightedPart, thirdPart] = processQuery(
		props.query,
		props.text
	);

	return (
		<span className="autocomplete-item">
			{firstPart}
			{highlightedPart && <strong>{highlightedPart}</strong>}
			{thirdPart}
		</span>
	);
}
