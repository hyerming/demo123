
package com.igomall.controller.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igomall.entity.Area;
import com.igomall.service.AreaService;

/**
 * Controller - 地区
 * 
 * @author blackboy
 * @version 1.0
 */
@Controller("commonAreaController")
@RequestMapping("/common/area")
public class AreaController {

	@Inject
	private AreaService areaService;

	/**
	 * 地区
	 */
	@GetMapping
	public @ResponseBody List<Map<String, Object>> index(Long parentId) {
		List<Map<String, Object>> data = new ArrayList<>();
		Area parent = areaService.find(parentId);
		Collection<Area> areas = parent != null ? parent.getChildren() : areaService.findRoots();
		for (Area area : areas) {
			Map<String, Object> item = new HashMap<>();
			item.put("name", area.getName());
			item.put("value", area.getId());
			data.add(item);
		}
		return data;
	}

}