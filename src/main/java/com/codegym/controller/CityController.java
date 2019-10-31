package com.codegym.controller;

import com.codegym.model.City;
import com.codegym.model.CityForm;
import com.codegym.model.Country;
import com.codegym.service.CityService;
import com.codegym.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/city")
public class CityController {
    @Autowired
    Environment env;

    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    @ModelAttribute("countries")
    public Page<Country> countries(Pageable pageable) {
        return countryService.findAll(pageable);
    }

    @GetMapping("/list")
    public ModelAndView listCity(@PageableDefault(size = 5) Pageable pageable, @RequestParam("s") Optional<String> s, HttpSession httpSession, HttpServletRequest request) {
        Page<City> cities = cityService.findAll(pageable);
        if(s.isPresent()){
            cities = cityService.findAllByNameContaining(s.get(), pageable);
        } else {
            cities = cityService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/city/list");
        modelAndView.addObject("cities", cities);
        Cookie[] cookies = request.getCookies();
        //iterate each cookie
        for (Cookie ck : cookies) {
            //display only the cookie with the name 'setUser'
            if (ck.getName().equals("setUser")) {
                modelAndView.addObject("cookieValue", ck);
                break;
            }
        }
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/city/create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveCity(@ModelAttribute("city") CityForm cityForm, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/city/create");
            modelAndView.addObject("city", new City());
            return modelAndView;
        }
        // lay ten file
        MultipartFile multipartFile = cityForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();

        // luu file len server
        try {
            //multipartFile.transferTo(imageFile);
            FileCopyUtils.copy(cityForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // tham khảo: https://github.com/codegym-vn/spring-static-resources

        // tao doi tuong de luu vao db
        City cityObject = new City(cityForm.getCountry(),cityForm.getName(),cityForm.getArea(),cityForm.getPopulation(),cityForm.getGdp(),cityForm.getIntroduction(),fileName);

        // luu vao db
        cityService.save(cityObject);

        ModelAndView modelAndView = new ModelAndView("/city/create");
        modelAndView.addObject("message","successes!");
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView editCityForm(@PathVariable Long id) {
        City city = cityService.findById(id);
        if (city != null) {
            ModelAndView modelAndView = new ModelAndView("/city/edit");
            modelAndView.addObject("city", city);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/update")
    public ModelAndView upladeCity(@ModelAttribute("city") CityForm cityForm, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/city/create");
            modelAndView.addObject("city", new City());
            return modelAndView;
        }
        // lay ten file
        MultipartFile multipartFile = cityForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();

        // luu file len server
        try {
            //multipartFile.transferTo(imageFile);
            FileCopyUtils.copy(cityForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // tham khảo: https://github.com/codegym-vn/spring-static-resources

        // tao doi tuong de luu vao db
        City cityObject = new City(cityForm.getCountry(),cityForm.getName(),cityForm.getArea(),cityForm.getPopulation(),cityForm.getGdp(),cityForm.getIntroduction(),fileName);

        // luu vao db
        cityObject.setId(cityForm.getId());
        cityService.save(cityObject);

        ModelAndView modelAndView = new ModelAndView("/city/create");
        modelAndView.addObject("message","successes!");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCity(@PathVariable Long id) {
        City city = cityService.findById(id);
        if (city != null) {
            ModelAndView modelAndView = new ModelAndView("/city/delete");
            modelAndView.addObject("city", city);
            return modelAndView;
        } else {ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/remove")
    public String removeCity(@ModelAttribute("city") City city) {
        cityService.remove(city.getId());
        return "redirect:list";
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewCityForm(@PathVariable Long id) {
        City city = cityService.findById(id);
        if (city != null) {
            ModelAndView modelAndView = new ModelAndView("/city/view");
            modelAndView.addObject("city", city);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @GetMapping("/sapxep")
    public ModelAndView sapxep(@PageableDefault(size = 5) Pageable pageable){
        System.out.println("ok");
        ModelAndView modelAndView=new ModelAndView("/city/list");
        modelAndView.addObject("cities", cityService.findByOrderByAreaAsc(pageable));
        return modelAndView;
    }

}