package com.busstation.controllers;

import com.busstation.pojo.Station;
import com.busstation.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stations")
public class StationController {

    @Autowired
    private StationService stationService;

    // Hiển thị danh sách các trạm
    @GetMapping("/")
    public String listStations(Model model) {
        model.addAttribute("stations", stationService.getAllStations());
        return "stations/list"; // Trả về tên view để hiển thị danh sách các trạm
    }

    // Hiển thị form để thêm mới trạm
    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("station", new Station());
        return "stations/new_form"; // Trả về tên view chứa form để thêm mới trạm
    }

    // Xử lý yêu cầu POST để thêm mới trạm
    @PostMapping("/add")
    public String addStation(@ModelAttribute("station") Station station) {
        stationService.addStation(station);
        return "redirect:/stations/"; // Chuyển hướng về danh sách các trạm sau khi thêm
    }

    // Hiển thị form để cập nhật trạm
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Station station = stationService.getStationById(id);
        if (station != null) {
            model.addAttribute("station", station);
            return "stations/edit_form"; // Trả về tên view chứa form để cập nhật trạm
        } else {
            return "redirect:/stations/"; // Nếu không tìm thấy trạm, chuyển hướng về danh sách các trạm
        }
    }

    // Xử lý yêu cầu POST để cập nhật trạm
    @PostMapping("/update")
    public String updateStation(@ModelAttribute("station") Station station) {
        stationService.updateStation(station);
        return "redirect:/stations/"; // Chuyển hướng về danh sách các trạm sau khi cập nhật
    }

    // Xử lý yêu cầu để xóa trạm
    @GetMapping("/delete/{id}")
    public String deleteStation(@PathVariable Long id) {
        stationService.deleteStationById(id);
        return "redirect:/stations/"; // Chuyển hướng về danh sách các trạm sau khi xóa
    }
}
