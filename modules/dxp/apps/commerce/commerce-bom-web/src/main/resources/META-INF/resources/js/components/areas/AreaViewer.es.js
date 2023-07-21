/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React from 'react';

import DetailsBox from './DetailsBox.es';
import PictureBox from './PictureBox.es';

function AreaViewer() {
	return (
		<div className="row">
			<div className="col">
				<PictureBox />
			</div>
			<div className="col col-sm-4">
				<DetailsBox />
			</div>
		</div>
	);
}

export default AreaViewer;
