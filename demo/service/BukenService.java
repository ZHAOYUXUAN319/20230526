package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Buken;
import com.example.demo.obj.BukenDto;
import com.example.demo.repository.BukenRepository;

@Service
public class BukenService {

	// 物件一覧
	@Autowired
	private BukenRepository bukenRepository;

	public List<BukenDto> searchAll() {
		List<Buken> bukenList = bukenRepository.findAll(Sort.by(Order.asc("propertyId")));
		// List<Buken> bukenList = bukenRepository.findAll();
		List<BukenDto> bukenDtoList = new ArrayList<>();

		for (Buken buken : bukenList) {
			BukenDto bukenDto = convertToDto(buken);
			bukenDtoList.add(bukenDto);
		}

		return bukenDtoList;
	}

	private BukenDto convertToDto(Buken buken) {
		Long id = buken.getPropertyId();
		String name = buken.getPropertyName();
		String address = buken.getAddress();
		String propertyType = buken.getPropertyType();
		String propertyArea = buken.getPropertyArea();
		String price = buken.getPrice();
		String syozokuCompanyId = buken.getSyozokuCompanyId();
		String status = buken.getStatus();

		return new BukenDto(id, name, address, propertyType, propertyArea, price, syozokuCompanyId, status);
	}

	// 物件新規
	@PostMapping("/user/BukenAdd")
	public String addBuken(@ModelAttribute BukenDto bukenDto) {
	    Buken buken = new Buken();
	    //buken.setPropertyId(bukenDto.getPropertyId());
	    buken.setPropertyName(bukenDto.getPropertyName());
	    buken.setAddress(bukenDto.getAddress());
	    buken.setPropertyType(bukenDto.getPropertyType());
	    buken.setPropertyArea(bukenDto.getPropertyArea());
	    buken.setPrice(bukenDto.getPrice());
	    buken.setSyozokuCompanyId(bukenDto.getSyozokuCompanyId());
	    buken.setStatus(bukenDto.getStatus());

	    saveBuken(buken);

	    return "redirect:/user/Buken";
	}


	// 新規の保存

	public Buken saveBuken(Buken buken) {
		// BukenRepository を使って、DBに保存する
		return bukenRepository.save(buken);
	}
	
	//検索
	public List<BukenDto> searchById(Long propertyId) {
	    List<Buken> bukenList = bukenRepository.findByPropertyId(propertyId);
	    
	    List<BukenDto> bukenDtoList = new ArrayList<>();
	    for (Buken buken : bukenList) {
	        BukenDto bukenDto = convertToDto(buken);
	        bukenDtoList.add(bukenDto);
	    }
	    
	    return bukenDtoList;
	}


}
