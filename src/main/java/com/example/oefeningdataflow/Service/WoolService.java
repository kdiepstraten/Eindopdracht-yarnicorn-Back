package com.example.oefeningdataflow.Service;

import com.example.oefeningdataflow.DTO.WoolDTO;
import com.example.oefeningdataflow.Exceptions.IdNotFoundException;
import com.example.oefeningdataflow.Models.Wool;
import com.example.oefeningdataflow.Repository.WoolRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WoolService {

    private final WoolRepository woolRepository;

    public WoolService(WoolRepository woolRepository){
        this.woolRepository = woolRepository;
    }

    public List<WoolDTO> getAllWool(){

        List<Wool> wools = woolRepository.findAll();
        List<WoolDTO> woolDtos = new ArrayList<>();

        for (Wool w : wools) {
            WoolDTO wdto = new WoolDTO();
            woolToWoolDTO(w, wdto);

            woolDtos.add(wdto);
        }
        return woolDtos;
    }

    private static void woolToWoolDTO(Wool w, WoolDTO wdto) {
        wdto.setName(w.getName());
        wdto.setBrand(w.getBrand());
        wdto.setColor(w.getColor());
        wdto.setId(w.getId());
        wdto.setLength(w.getLength());
        wdto.setBlend(w.getBlend());
        wdto.setNeedleSize(w.getNeedleSize());
        wdto.setCategory(w.getCategory());
    }
    private void woolDTOToWool(WoolDTO woolDTO, Wool wool) {
        wool.setName(woolDTO.getName());
        wool.setBrand(woolDTO.getBrand());
        wool.setColor(woolDTO.getColor());
        wool.setLength(woolDTO.getLength());
        wool.setBlend(woolDTO.getBlend());
        wool.setNeedleSize(woolDTO.getNeedleSize());
        wool.setCategory(woolDTO.getCategory());
    }
    public WoolDTO getWool(Long id) {
        Optional<Wool> wool = woolRepository.findById(id);
        if (wool.isPresent()){
            Wool w = wool.get();
            WoolDTO wdto = new WoolDTO();
            woolToWoolDTO(w, wdto);
            return (wdto);
        } else {
            throw new IdNotFoundException("wool not found with id: " + id);
        }
    }

    public WoolDTO createWool(WoolDTO woolDTO) {
        Wool wool = new Wool();
        woolDTOToWool(woolDTO, wool);

        Wool savedWool = woolRepository.save(wool);

        WoolDTO savedWoolDTO = new WoolDTO();
        woolToWoolDTO(savedWool, savedWoolDTO);

        return savedWoolDTO;
    }

    public void deleteWool(@RequestBody Long id){

        woolRepository.deleteById(id);

    }

//    public static List<WoolDTO> getProductsByCategory(String category) {
//        // Implement the logic to retrieve products by category, e.g., from a repository or data source
//        Optional<Wool> products = WoolRepository.findByCategory(category);
//
//        List<WoolDTO> productDtos = new ArrayList<>();
//
//
//        return productDtos;
//    }


}
