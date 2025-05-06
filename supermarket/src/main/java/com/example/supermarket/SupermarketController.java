package com.example.supermarket;

import com.example.supermarket.model.ItemBelanja;
import com.example.supermarket.service.SupermarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SupermarketController {
    
    @GetMapping("/supermarketonline")
    public String showPage() {
        return "supermarketonline"; 
    }

    @Autowired
    private SupermarketService service;

    @GetMapping("/")
    public String tampilKeranjang(Model model) {
        model.addAttribute("keranjang", service.getKeranjang());
        model.addAttribute("total", service.hitungTotal());
        return "keranjang"; // menuju keranjang.html di templates/
    }

    @PostMapping("/tambah")
    public String tambahItem(@RequestParam String nama,
                             @RequestParam int jumlah,
                             @RequestParam double harga) {
        ItemBelanja item = new ItemBelanja(nama, jumlah, harga);
        service.tambahItem(item);
        return "redirect:/";
    }
}
