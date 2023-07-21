/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import getCN from 'classnames';
import React from 'react';

export default class Icon extends React.Component {
	render() {
		const {elementClasses, iconName} = this.props;
		const classes = getCN(
			'lexicon-icon',
			`lexicon-icon-${iconName}`,
			elementClasses
		);

		const useTag = `<use xlink:href="${Liferay.ThemeDisplay.getPathThemeImages()}/lexicon/icons.svg#${iconName}" />`;

		return (
			<svg
				className={classes}
				dangerouslySetInnerHTML={{__html: useTag}}
				data-testid="icon"
				focusable="false"
				role="presentation"
			/>
		);
	}
}
