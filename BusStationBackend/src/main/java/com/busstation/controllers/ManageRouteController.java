package com.busstation.controllers;

import com.busstation.pojo.Route;
import com.busstation.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manage/routes")
public class ManageRouteController {

    @Autowired
    private RouteService routeService;

    // Hiển thị danh sách các tuyến đường
    @GetMapping("/")
    public String listRoutes(Model model) {
        model.addAttribute("routes", routeService.getAllRoutes(null));
        return "routes/list";
    }

    // Hiển thị form để thêm mới một tuyến đường
    @GetMapping("/new")
    public String showFormForAdd(Model model) {
        Route route = new Route();
        model.addAttribute("route", route);
        return "routes/new_route_form";
    }

    // Lưu một tuyến đường mới
    @PostMapping("/save")
    public String saveRoute(@ModelAttribute("route") Route route) {
        routeService.saveRoute(route);
        return "redirect:/manage/routes/";
    }

    // Hiển thị form để cập nhật thông tin tuyến đường
    @GetMapping("/edit/{id}")
    public String showFormForUpdate(@PathVariable Long id, Model model) {
        Route route = routeService.getRouteById(id);
        model.addAttribute("route", route);
        return "routes/edit_route_form";
    }

    // Cập nhật thông tin tuyến đường
    @PostMapping("/update")
    public String updateRoute(@ModelAttribute("route") Route route) {
        routeService.updateRoute(route);
        return "redirect:/manage/routes/";
    }

    // Xóa một tuyến đường
    @GetMapping("/delete/{id}")
    public String deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return "redirect:/manage/routes/";
    }
}
