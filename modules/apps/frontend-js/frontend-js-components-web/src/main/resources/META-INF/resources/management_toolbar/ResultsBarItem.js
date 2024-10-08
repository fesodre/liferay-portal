/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import classNames from 'classnames';
import PropTypes from 'prop-types';
import React from 'react';

const ResultsBarItem = ({
	children,
	className,
	expand = false,
	...otherProps
}) => (
	<li
		{...otherProps}
		className={classNames('tbar-item', className, {
			'tbar-item-expand': expand,
		})}
	>
		<div className="tbar-section">{children}</div>
	</li>
);

ResultsBarItem.propTypes = {
	expand: PropTypes.bool,
};

export default ResultsBarItem;
