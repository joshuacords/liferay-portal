/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import ClayIcon from '@clayui/icon';
import PropTypes from 'prop-types';
import React from 'react';

import Loader from '../../common/Loader.es';

const ResolveButton = props => {
	let icon = (
		<span
			className="lfr-portal-tooltip text-lowercase"
			data-title={Liferay.Language.get('resolve')}
		>
			<ClayIcon symbol="check-circle" />
		</span>
	);

	if (props.loading) {
		icon = <Loader />;
	}
	else if (props.resolved) {
		icon = (
			<span
				className="lfr-portal-tooltip text-lowercase text-success"
				data-title={Liferay.Language.get('reopen')}
			>
				<ClayIcon symbol="check-circle-full" />
			</span>
		);
	}

	return (
		<ClayButton
			borderless
			className="flex-shrink-0"
			disabled={props.disabled || props.loading}
			displayType="secondary"
			monospaced
			onClick={props.onClick}
			outline
			small
		>
			{icon}
		</ClayButton>
	);
};

ResolveButton.propTypes = {
	disabled: PropTypes.bool,
	loading: PropTypes.bool.isRequired,
	onClick: PropTypes.func.isRequired,
	resolved: PropTypes.bool.isRequired
};

export {ResolveButton};
export default ResolveButton;
