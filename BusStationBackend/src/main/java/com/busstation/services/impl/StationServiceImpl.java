package com.busstation.services.impl;

import com.busstation.pojo.Station;
import com.busstation.repositories.StationRepository;
import com.busstation.services.StationService;
import java.util.List;

public class StationServiceImpl implements StationService {
    private final StationRepository stationRepository;

    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public List<Station> getAllStations() {
        return stationRepository.getAll();
    }

    @Override
    public Station getStationById(Long id) {
        return stationRepository.findById(id);
    }

    @Override
    public void addStation(Station station) {
        stationRepository.save(station);
    }

    @Override
    public void updateStation(Station station) {
        stationRepository.update(station);
    }

    @Override
    public void deleteStationById(Long id) {
        stationRepository.deleteById(id);
    }
}
