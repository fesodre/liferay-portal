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

package com.liferay.document.library.web.internal.display.context;

import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Adolfo Pérez
 */
public class DLFileEntryTypeDetailsDisplayContext {

	public DLFileEntryTypeDetailsDisplayContext(
		HttpServletRequest httpServletRequest) {

		_httpServletRequest = httpServletRequest;
	}

	public DLFileEntryType getDLFileEntryType() {
		return (DLFileEntryType)_httpServletRequest.getAttribute(
			WebKeys.DOCUMENT_LIBRARY_FILE_ENTRY_TYPE);
	}

	public boolean isForeignDLFileEntryType() {
		DDMStructure ddmStructure =
			(DDMStructure)_httpServletRequest.getAttribute(
				WebKeys.DOCUMENT_LIBRARY_DYNAMIC_DATA_MAPPING_STRUCTURE);

		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if ((ddmStructure != null) &&
			(ddmStructure.getGroupId() != themeDisplay.getScopeGroupId())) {

			return true;
		}

		return false;
	}

	private final HttpServletRequest _httpServletRequest;

}