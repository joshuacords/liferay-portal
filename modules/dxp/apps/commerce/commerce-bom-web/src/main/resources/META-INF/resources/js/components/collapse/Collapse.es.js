/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {useEffect, useRef, useState} from 'react';

export default function Dropdown(props) {
	const [state, setState] = useState(props.open ? 'expanded' : 'collapsed');
	const bodyRef = useRef(null);

	function toggle() {
		switch (true) {
			case state === 'expanded':
				setState('collapsing');
				bodyRef.current.style.maxHeight =
					bodyRef.current.scrollHeight + 'px';
				bodyRef.current.addEventListener(
					'transitionend',
					() => setState('collapsed'),
					{once: true}
				);
				break;
			case state === 'collapsed':
				setState('expanding');
				bodyRef.current.style.maxHeight = '0px';
				bodyRef.current.addEventListener(
					'transitionend',
					() => setState('expanded'),
					{once: true}
				);
				break;
			default:
				break;
		}
	}

	useEffect(() => {
		switch (true) {
			case state === 'expanding':
				bodyRef.current.style.maxHeight =
					bodyRef.current.scrollHeight + 'px';
				break;
			case state === 'expanded':
				bodyRef.current.style.maxHeight = '';
				break;
			case state === 'collapsing':
				bodyRef.current.style.maxHeight = '0px';
				break;
			case state === 'collapsed':
				bodyRef.current.style.maxHeight = '';
				break;
			default:
				break;
		}
	}, [state]);

	return (
		<div className={`commerce-collapse panel`}>
			<button
				aria-controls="collapseTwo"
				aria-expanded={state === 'expanded'}
				className={`commerce-collapse__header commerce-collapse__header--${state}`}
				onClick={toggle}
				role="tab"
				type="button"
			>
				<span className="collapse-title">{props.title}</span>
				<span className="collapse-icon-closed">{props.closedIcon}</span>
				<span className="collapse-icon-open">{props.openIcon}</span>
			</button>
			<div
				className={`commerce-collapse__content commerce-collapse__content--${state}`}
				ref={bodyRef}
				role="tabpanel"
			>
				{props.content}
			</div>
		</div>
	);
}
