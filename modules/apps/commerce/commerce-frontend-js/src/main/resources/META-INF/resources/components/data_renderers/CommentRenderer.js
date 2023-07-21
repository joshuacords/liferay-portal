/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import ClayIcon from '@clayui/icon';
import {ClayTooltipProvider} from '@clayui/tooltip';
import React from 'react';

function CommentRenderer(props) {
	return (
		<ClayTooltipProvider>
			<ClayButton
				className="cell-comment inline-item ml-2 my-n2 px-1 text-warning"
				data-tooltip-align="top"
				data-tooltip-delay={0}
				displayType="link"
				title={props.children}
			>
				<ClayIcon symbol="info-circle" />
			</ClayButton>
		</ClayTooltipProvider>
	);
}

export default CommentRenderer;
