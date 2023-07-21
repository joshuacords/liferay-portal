/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

export default function Expose({active, children, onClose}) {
	const content = React.useRef();

	React.useEffect(() => {
		const handleEscKey = e => e.key === 'Escape' && onClose();

		if (active) {
			window.addEventListener('keydown', handleEscKey);
		}
		else {
			window.removeEventListener('keydown', handleEscKey);
		}
	}, [active, onClose]);

	return (
		<div className={`expose mb-4 ${active ? 'is-open' : 'is-closed'}`}>
			<div className="expose__backdrop" onClick={onClose} />
			<div className="expose__content" ref={content}>
				{children}
			</div>
		</div>
	);
}
