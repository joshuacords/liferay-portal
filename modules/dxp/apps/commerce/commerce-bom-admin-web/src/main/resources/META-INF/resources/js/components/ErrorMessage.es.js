/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

function ErrorMessage(props) {
	return (
		<div className="alert-container container">
			<div className="alert-notifications alert-notifications-absolute">
				<div
					className="alert alert-danger alert-dismissible"
					role="alert"
				>
					{Liferay.Language.get('unexpected-error')}
					{props.closeIcon && (
						<button
							aria-label="Close"
							className="close"
							data-dismiss="alert"
							onClick={props.onClose}
							type="button"
						>
							{props.closeIcon}
						</button>
					)}
				</div>
			</div>
		</div>
	);
}

export default ErrorMessage;
