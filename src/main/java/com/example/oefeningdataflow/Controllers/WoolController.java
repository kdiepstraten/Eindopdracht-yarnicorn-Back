package com.example.oefeningdataflow.Controllers;

import com.example.oefeningdataflow.DTO.WoolDTO;
import com.example.oefeningdataflow.Service.WoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wools")
public class WoolController {

    private final WoolService woolService;

    public WoolController(WoolService woolService) {
        this.woolService = woolService;
    }

    @GetMapping
    public ResponseEntity<List<WoolDTO>> getAllWool() {
        List<WoolDTO> wdto = woolService.getAllWool();
        return new ResponseEntity<>(wdto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WoolDTO> getOneWool(@PathVariable Long id) {
        WoolDTO wdto = woolService.getWool(id);
        return new ResponseEntity<>(wdto, HttpStatus.OK);
    }

//    @GetMapping("/products")
//    public ResponseEntity<List<WoolDTO>> getProductsByCategory(@RequestParam("category") String category) {
//        List<WoolDTO> products = WoolService.getProductsByCategory(category);
//
//        if (products.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }


    @PostMapping
    public ResponseEntity<WoolDTO> createWool(@RequestBody WoolDTO woolDTO) {
        WoolDTO newWool = woolService.createWool(woolDTO);
        return new ResponseEntity<>(newWool, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WoolDTO> deleteWool(@PathVariable Long id) {
        woolService.deleteWool(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
