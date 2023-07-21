/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {ClayIconSpriteContext} from '@clayui/icon';
import React from 'react';
import ReactDOM from 'react-dom';

let counter = 0;

/**
 * Wrapper for ReactDOM render that automatically:
 *
 * - Provides commonly-needed context (for example, the Clay spritemap).
 * - Unmounts when portlets are destroyed based on the received
 *   `portletId` value inside renderData. If none is passed, the
 *   component will be automatically unmounted before the next navigation.
 *
 * The React docs advise not to rely on the render return value, so we
 * don't propagate it.
 *
 * @see https://reactjs.org/docs/react-dom.html#render
 */
export default function render(renderFunction, renderData, container) {
	const {portletId} = renderData;
	const spritemap =
		Liferay.ThemeDisplay.getPathThemeImages() + '/lexicon/icons.svg';

	let {componentId} = renderData;

	const destroyOnNavigate = !portletId;

	if (!componentId) {
		componentId = `__UNNAMED_COMPONENT__${portletId}__${counter++}`;
	}

	Liferay.component(
		componentId,
		{
			destroy: () => {
				ReactDOM.unmountComponentAtNode(container);
			}
		},
		{
			destroyOnNavigate,
			portletId
		}
	);

	// eslint-disable-next-line liferay-portal/no-react-dom-render
	ReactDOM.render(
		<ClayIconSpriteContext.Provider value={spritemap}>
			{renderFunction(renderData)}
		</ClayIconSpriteContext.Provider>,
		container
	);
}
