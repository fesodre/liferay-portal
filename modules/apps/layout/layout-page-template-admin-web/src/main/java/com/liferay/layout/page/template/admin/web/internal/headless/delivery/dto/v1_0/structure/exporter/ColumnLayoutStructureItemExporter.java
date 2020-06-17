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

package com.liferay.layout.page.template.admin.web.internal.headless.delivery.dto.v1_0.structure.exporter;

import com.liferay.headless.delivery.dto.v1_0.PageColumnDefinition;
import com.liferay.headless.delivery.dto.v1_0.PageElement;
import com.liferay.headless.delivery.dto.v1_0.ViewportColumnConfig;
import com.liferay.headless.delivery.dto.v1_0.ViewportColumnConfigDefinition;
import com.liferay.layout.responsive.ViewportSize;
import com.liferay.layout.util.structure.ColumnLayoutStructureItem;
import com.liferay.layout.util.structure.LayoutStructureItem;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.MapUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jürgen Kappler
 */
@Component(service = LayoutStructureItemExporter.class)
public class ColumnLayoutStructureItemExporter
	implements LayoutStructureItemExporter {

	@Override
	public String getClassName() {
		return ColumnLayoutStructureItem.class.getName();
	}

	@Override
	public PageElement getPageElement(
		long groupId, LayoutStructureItem layoutStructureItem,
		boolean saveInlineContent, boolean saveMappingConfiguration) {

		ColumnLayoutStructureItem columnLayoutStructureItem =
			(ColumnLayoutStructureItem)layoutStructureItem;

		return new PageElement() {
			{
				definition = new PageColumnDefinition() {
					{
						size = columnLayoutStructureItem.getSize();

						setViewportColumnConfig(
							() -> {
								Map<String, JSONObject> viewportConfigurations =
									columnLayoutStructureItem.
										getViewportSizeConfigurations();

								if (MapUtil.isEmpty(viewportConfigurations)) {
									return null;
								}

								return new ViewportColumnConfig() {
									{
										landscapeMobile =
											_toViewportColumnConfigDefinition(
												viewportConfigurations,
												ViewportSize.MOBILE_LANDSCAPE);
										portraitMobile =
											_toViewportColumnConfigDefinition(
												viewportConfigurations,
												ViewportSize.PORTRAIT_MOBILE);
										tablet =
											_toViewportColumnConfigDefinition(
												viewportConfigurations,
												ViewportSize.TABLET);
									}
								};
							});
					}
				};
				type = PageElement.Type.COLUMN;
			}
		};
	}

	private ViewportColumnConfigDefinition _toViewportColumnConfigDefinition(
		Map<String, JSONObject> viewportConfigurations,
		ViewportSize viewportSize) {

		if (!viewportConfigurations.containsKey(
				viewportSize.getViewportSizeId())) {

			return null;
		}

		JSONObject jsonObject = viewportConfigurations.get(
			viewportSize.getViewportSizeId());

		return new ViewportColumnConfigDefinition() {
			{
				if (jsonObject.has("size")) {
					size = jsonObject.getInt("size");
				}
			}
		};
	}

}