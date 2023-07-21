/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {getJsModule} from '../../utilities/modules';
import ActionsLinkRenderer from './ActionLinkRenderer';
import ActionsDropdownRenderer from './ActionsDropdownRenderer';
import BooleanRenderer from './BooleanRenderer';
import CheckboxRenderer from './CheckboxRenderer';
import DateRenderer from './DateRenderer';
import DefaultRenderer from './DefaultRenderer';
import ImageRenderer from './ImageRenderer';
import LabelRenderer from './LabelRenderer';
import LinkRenderer from './LinkRenderer';
import ListRenderer from './ListRenderer';
import LocalizedFieldRenderer from './LocalizedFieldRenderer';
import QuantitySelectorRenderer from './QuantitySelectorRenderer';
import StatusRenderer from './StatusRenderer';
import TooltipPriceRenderer from './TooltipPriceRenderer';

const dataRenderers = {
	actionLink: ActionsLinkRenderer,
	actionsDropdown: ActionsDropdownRenderer,
	boolean: BooleanRenderer,
	checkbox: CheckboxRenderer,
	date: DateRenderer,
	default: DefaultRenderer,
	image: ImageRenderer,
	label: LabelRenderer,
	link: LinkRenderer,
	list: ListRenderer,
	localizedField: LocalizedFieldRenderer,
	quantitySelector: QuantitySelectorRenderer,
	status: StatusRenderer,
	tooltipPrice: TooltipPriceRenderer
};

export function getDataRendererById(id) {
	return dataRenderers[id] || DefaultRenderer;
}

export const fetchedContentRenderers = [];

export function getDataRendererByUrl(url) {
	return new Promise((resolve, reject) => {
		const addedDataRenderer = fetchedContentRenderers.find(
			cr => cr.url === url
		);
		if (addedDataRenderer) {
			resolve(addedDataRenderer.component);
		}

		return getJsModule(url)
			.then(fetchedComponent => {
				fetchedContentRenderers.push({
					component: fetchedComponent,
					url
				});

				return resolve(fetchedComponent);
			})
			.catch(reject);
	});
}
