/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SearchTerm;
import com.sg.superherosightings.dao.SightingSearchTerm;
import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Picture;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Superhero;
import com.sg.superherosightings.model.Superpower;
import com.sg.superherosightings.service.SuperheroSightingsServiceLayer;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author mirandabeamer
 */
@Controller
public class SuperheroController {

    SuperheroSightingsServiceLayer service;
    public static final String pictureFolder = "images/";
    String errorMessage;

    @Inject
    public SuperheroController(SuperheroSightingsServiceLayer service) {
        this.service = service;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String displayHomePage(Model model) {
        //need only 10 most recent sightings
        List<Sighting> sightings = service.getMostRecentSightings();
        List<Location> locations = new ArrayList<>();
        for(Sighting sighting : sightings){
            Location loc = sighting.getLocation();
            locations.add(loc);
        }
        model.addAttribute("locations", locations);
        model.addAttribute("sightings", sightings);
        return "index";
    }
    
    @RequestMapping(value="/getRecentLocations", method=RequestMethod.GET)
    @ResponseBody
    public List<Location> getRecentLocations() {
        List<Sighting> sightings = service.getMostRecentSightings();
        List<Location> locations = new ArrayList<>();
        for(Sighting sighting : sightings){
            Location loc = sighting.getLocation();
            locations.add(loc);
        }
        return locations;
    }

    //SIGHTINGS-----------------------------------------------------------------
    @RequestMapping(value = "/displaySightings", method = RequestMethod.GET)
    public String displaySightings(HttpServletRequest request, Model model) {
        List<Sighting> sightings = service.getAllSightings();
        model.addAttribute("sightings", sightings);
        for (Sighting sighting : sightings) {
            List<Superhero> superheroes = sighting.getSuperheroes();
            model.addAttribute("superheroes", superheroes);
        }
        return "sightings";
    }

    @RequestMapping(value = "/displayAddSightingForm", method = RequestMethod.GET)
    public String displayAddSightingForm(Model model) {
        //need to add list of heroes and locations for options
        List<Superhero> superheroes = service.getAllHeroes();
        model.addAttribute("superheroes", superheroes);
        List<Location> locations = service.getAllLocations();
        model.addAttribute("locations", locations);
        model.addAttribute("errorMessage", errorMessage);
        return "addSighting";
    }

    @RequestMapping(value = "/addSighting", method = RequestMethod.POST)
    public String addSighting(HttpServletRequest request) {
        Sighting sighting = new Sighting();
        //get superheroes 
        List<Superhero> superheroes = new ArrayList();
        String heroIdParameter = request.getParameter("name");
        int heroId = Integer.parseInt(heroIdParameter);
        Superhero hero = service.getHero(heroId);
        superheroes.add(hero);
        sighting.setSuperheroes(superheroes);
        Location loc = new Location();

        String locationIdParameter = request.getParameter("locationName");
        int locationId = Integer.parseInt(locationIdParameter);
        loc = service.getLocation(locationId);

        String date = request.getParameter("date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate dateTime = LocalDate.parse(date, formatter);
        sighting.setDate(dateTime);
        sighting.setLocation(loc);
        service.addSighting(sighting);

        return "redirect:displaySightings";
    }

    @RequestMapping(value = "deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        service.deleteSighting(sightingId);
        return "redirect:displaySightings";
    }

    @RequestMapping(value = "/displayEditSightingForm", method = RequestMethod.GET)
    public String displayEditSightingForm(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");

        int sightingId = Integer.parseInt(sightingIdParameter);
        Sighting sighting = service.getSighting(sightingId);
        model.addAttribute("sighting", sighting);
        Location location = sighting.getLocation();
        model.addAttribute("location", location);
        Address address = location.getAddress();
        model.addAttribute("address", address);
        List<Superhero> superheroes = service.getAllHeroes();
        model.addAttribute("superheroes", superheroes);
        String dateString = sighting.getDate().toString();
        model.addAttribute("dateString", dateString);

        return "editSighting";
    }

    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(HttpServletRequest request, Model model) {
        Sighting sighting = new Sighting();
        //get superheroes list
        List<Superhero> superheroes = new ArrayList();
        String[] superheroIds = request.getParameterValues("superheroes");
        for (String superheroId : superheroIds) {
            int heroId = Integer.parseInt(superheroId);
            Superhero hero = service.getHero(heroId);
            superheroes.add(hero);
        }

        sighting.setSuperheroes(superheroes);

        //get location 
        String locationIdStr = request.getParameter("location.locationId");
        int locationId = Integer.parseInt(locationIdStr);
        Location loc = service.getLocation(locationId);
        //set location
        sighting.setLocation(loc);

        //getsightingId
        String sightingIdStr = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdStr);
        sighting.setSightingId(sightingId);

        //parse date given 
        String date = request.getParameter("date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate dateTime = LocalDate.parse(date, formatter);
            sighting.setDate(dateTime);
            service.editSighting(sighting);
        } catch (DateTimeParseException e) {
            errorMessage = "Please enter valid date";
        }

        return "redirect:displaySightings";
    }

    @RequestMapping(value = "/displaySightingDetails", method = RequestMethod.GET)
    public String displaySightingDetails(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        Sighting sighting = service.getSighting(sightingId);
        model.addAttribute("sighting", sighting);
        //add address and superhero list as well
        Location location = sighting.getLocation();
        model.addAttribute("location", location);
        Address address = location.getAddress();
        model.addAttribute("address", address);
        List<Superhero> superheroes = sighting.getSuperheroes();
        model.addAttribute("superheroes", superheroes);
        return "sightingDetail";
    }

    //LOCATIONS-----------------------------------------------------------------
    @RequestMapping(value = "/displayLocations", method = RequestMethod.GET)
    public String displayLocations(Model model) {
        List<Location> locations = service.getAllLocations();
        for (Location location : locations) {
            Map<SightingSearchTerm, String> criteria = new HashMap<>();
            String locationName = location.getLocationName();
            criteria.put(SightingSearchTerm.NAME, locationName);
            List<Sighting> sightings = service.searchSightings(criteria);
            location.setSightings(sightings);
            model.addAttribute("locations", locations);
            model.addAttribute("sightings", sightings);
            model.addAttribute("errorMessage", errorMessage);
        }
        return "locations";
    }

    @RequestMapping(value = "/displayAddLocationForm", method = RequestMethod.GET)
    public String displayAddLocationForm(Model model) {
        Location location = new Location();
        model.addAttribute("location", location);
        Address address = location.getAddress();
        model.addAttribute("address", address);
        model.addAttribute("errorMessage", errorMessage);
        return "addLocation";
    }

    @RequestMapping(value = "/addLocation", method = RequestMethod.POST)
    public String addLocation(@Valid @ModelAttribute("location") Location location, BindingResult locationResult) {
        if (locationResult.hasErrors()) {
            return "addLocation";
        }

        location = service.addLocation(location);
        errorMessage = "";
        if (location == null) {
            errorMessage = "Location Name must be unique.";
            return "redirect:displayAddLocationForm";
        }
        
        return "redirect:displayLocations";
    }

    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request, Model model) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        try {
            service.deleteLocation(locationId);
            //if successful, need to empty error message
            errorMessage = "";
        } catch (DataIntegrityViolationException e) {
            errorMessage = "Error: Unable to delete location currently in use.";
        }

        return "redirect:displayLocations";
    }

    @RequestMapping(value = "/displayEditLocationForm", method = RequestMethod.GET)
    public String displayEditLocationForm(HttpServletRequest request, Model model) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        Location location = service.getLocation(locationId);
        model.addAttribute("location", location);
        return "editLocation";
    }

    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(@Valid @ModelAttribute("location") Location location, BindingResult result) {
        if (result.hasErrors()) {
            return "editLocation";
        }

        Address address = location.getAddress();
        address = service.editAddress(address);
        location.setAddress(address);
        service.editLocation(location);
        return "redirect:displayLocations";
    }
    


    //SUPERHEROES --------------------------------------------------------------
    @RequestMapping(value = "/displaySuperheroes", method = RequestMethod.GET)
    public String displaySuperheroes(Model model) {
        List<Superhero> superheroList = service.getAllHeroes();
        model.addAttribute("superheroList", superheroList);
        model.addAttribute("errorMessage", errorMessage);
        return "superheroes";
    }

    @RequestMapping(value = "/displayAddSuperheroForm", method = RequestMethod.GET)
    public String displayAddSuperheroForm(Model model) {
        List<Organization> organizations = service.getAllOrganizations();
        model.addAttribute("organizations", organizations);
        List<Superpower> superpowers = service.getAllSuperpowers();
        model.addAttribute("superpowers", superpowers);
        return "addSuperhero";
    }

    @RequestMapping(value = "/addSuperhero", method = RequestMethod.POST)
    public String addSuperhero(HttpServletRequest request, Model model, @RequestParam("picture") MultipartFile pictureFile) {
        Superhero hero = new Superhero();
        hero.setName(request.getParameter("name"));
        hero.setType(request.getParameter("heroType"));

        //get multiple organization selections and add to list 
        List<Organization> organizations = new ArrayList();
        String[] organizationNames = request.getParameterValues("organizations");
        //do not proceed if hero doesn't belong to any organizations
        for (String organizationName : organizationNames) {
            Organization organization = service.getOrganizationByName(organizationName);
            organizations.add(organization);
        }
        hero.setOrganizations(organizations);

        List<Superpower> superpowers = new ArrayList();
        String[] superpowerIds = request.getParameterValues("superpowers");
        for (String superpowerIdStr : superpowerIds) {
            int superpowerId = Integer.parseInt(superpowerIdStr);
            Superpower power = service.getSuperpower(superpowerId);
            superpowers.add(power);
        }
        
        Picture picture = new Picture();
                // only save the pictureFile if the user actually uploaded something
        if (!pictureFile.isEmpty()) {
            try {
                // we want to put the uploaded image into the 
                // <pictureFolder> folder of our application. getRealPath
                // returns the full path to the directory under Tomcat
                // where we can save files.
                String savePath = request
                        .getSession()
                        .getServletContext()
                        .getRealPath("/") + pictureFolder;
                java.io.File dir = new java.io.File(savePath);
                // if <pictureFolder> directory is not there, 
                // go ahead and create it
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // get the filename of the uploaded file - we'll use the
                // same name on the server.
                String filename = pictureFile.getOriginalFilename();
                // transfer the contents of the uploaded pictureFile to 
                // the server
                pictureFile.transferTo(new java.io.File(savePath + filename));

                // we successfully saved the pictureFile, now save a 
                // Picture to the DAO
                
                picture.setFilename(pictureFolder + filename);
                //picture.setTitle(displayTitle);
                picture = service.addPicture(picture);
                
               

                // redirect to home page to redisplay the entire album
//                return "redirect:home";
            } catch (Exception e) {
                // if we encounter an exception, add the error message 
                // to the model and return back to the pictureFile upload 
                // form page
                model.addAttribute("errorMsg", "File upload failed: "
                        + e.getMessage());
//                return "addSuperhero";
            }
        } else {
            // if the user didn't upload anything, add the error 
            // message to the model and return back to the pictureFile 
            // upload form page
            model.addAttribute("errorMsg",
                    "Please specify a non-empty file.");
//            return "addSuperhero";
        }
                //return "addSuperhero";
    
        

        
        
        hero.setSuperpowers(superpowers);
        hero.setSuperpowers(superpowers);
        hero.setDescription(request.getParameter("description"));
        hero.setPicture(picture);
        hero = service.addHero(hero);
        return "redirect:displaySuperheroes";
    }


    @RequestMapping(value = "/displaySuperheroDetails", method = RequestMethod.GET)
    public String displaySuperheroDetails(HttpServletRequest request, Model model) {
        String superheroIdParameter = request.getParameter("superheroId");
        Integer superheroId = Integer.parseInt(superheroIdParameter);
        Superhero superhero = service.getHero(superheroId);
        model.addAttribute("superhero", superhero);
        List<Organization> organizations = superhero.getOrganizations();
        model.addAttribute("organizations", organizations);
        List<Superpower> superpowers = superhero.getSuperpowers();
        model.addAttribute("superpowers", superpowers);
        Picture picture = superhero.getPicture();
        model.addAttribute("picture", picture);

        //get sightings for that superhero
        List<Sighting> sightings = service.getSightingByHero(superheroId);
        model.addAttribute("sightings", sightings);

        return "superheroDetail";
    }

    @RequestMapping(value = "/displayEditSuperheroForm", method = RequestMethod.GET)
    public String displayEditSuperheroForm(HttpServletRequest request, Model model) {
        String superheroIdParameter = request.getParameter("superheroId");
        Integer superheroId = Integer.parseInt(superheroIdParameter);
        Superhero superhero = service.getHero(superheroId);
        model.addAttribute("superhero", superhero);

        List<Organization> organizations = service.getAllOrganizations();
        model.addAttribute("organizations", organizations);

        List<Superpower> superpowers = service.getAllSuperpowers();
        model.addAttribute("superpowers", superpowers);
        return "editSuperhero";
    }

    @RequestMapping(value = "/editSuperhero", method = RequestMethod.POST)
    public String editSuperhero(HttpServletRequest request) {

        Superhero hero = new Superhero();
        hero.setName(request.getParameter("name"));
        hero.setType(request.getParameter("type"));
        String superheroIdParameter = request.getParameter("superheroId");
        Integer superheroId = Integer.parseInt(superheroIdParameter);
        hero.setSuperheroId(superheroId);
//        
        //get multiple organization selections and add to list 
        List<Organization> organizations = new ArrayList();
        String[] organizationNames = request.getParameterValues("organizations");
        for (String organizationName : organizationNames) {
            Organization organization = service.getOrganizationByName(organizationName);
            organizations.add(organization);
        }
        hero.setOrganizations(organizations);

        //allow multiple selections of superpowers 
        List<Superpower> superpowers = new ArrayList();
        String[] superpowerIds = request.getParameterValues("superpowers");
        for (String superpowerIdStr : superpowerIds) {
            int superpowerId = Integer.parseInt(superpowerIdStr);
            Superpower power = service.getSuperpower(superpowerId);
            superpowers.add(power);
        }
        hero.setSuperpowers(superpowers);
        hero.setDescription(request.getParameter("description"));
        service.editHero(hero);
        return "redirect:displaySuperheroes";
    }

    @RequestMapping(value = "/deleteSuperhero", method = RequestMethod.GET)
    public String deleteSuperhero(HttpServletRequest request) {
        String superheroIdParameter = request.getParameter("superheroId");
        Integer superheroId = Integer.parseInt(superheroIdParameter);
        service.deleteHero(superheroId);
        errorMessage = "";
        if (service.getHero(superheroId) != null) {
            errorMessage = "Unable to delete superhero";
        }
        return "redirect:displaySuperheroes";
    }

    //SUPERPOWERS---------------------------------------------------------------
    @RequestMapping(value = "/displaySuperpowers", method = RequestMethod.GET)
    public String displaySuperpowers(HttpServletRequest request, Model model) {
        List<Superpower> superpowerList = service.getAllSuperpowers();
        for (Superpower power : superpowerList) {
            //use search method to display heroes with each superpower
            Map<SearchTerm, String> criteria = new HashMap<>();
            String superpowerName = power.getSuperpowerName();
            criteria.put(SearchTerm.SUPERPOWER_TYPE, superpowerName);
            List<Superhero> superheroes = service.searchHeroes(criteria);
            power.setSuperheroes(superheroes);
            model.addAttribute("heroes", superheroes);
            model.addAttribute("superpowerList", superpowerList);
        }
        return "superpowers";
    }

    @RequestMapping(value = "/displayAddSuperpowerForm", method = RequestMethod.GET)
    public String displayAddSuperpowerForm() {
        return "addSuperpower";
    }

    @RequestMapping(value = "/addSuperpower", method = RequestMethod.POST)
    public String addSuperpower(HttpServletRequest request) {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName(request.getParameter("superpowerName"));
        superpower.setSuperpowerDesc(request.getParameter("superpowerDesc"));
        superpower = service.addSuperpower(superpower);
        return "redirect:displaySuperpowers";
    }

    @RequestMapping(value = "/deleteSuperpower", method = RequestMethod.GET)
    public String deleteSuperpower(HttpServletRequest request) {
        String superpowerIdParameter = request.getParameter("superpowerId");
        int superpowerId = Integer.parseInt(superpowerIdParameter);
        service.deleteSuperpower(superpowerId);
        return "redirect:displaySuperpowers";
    }

    @RequestMapping(value = "/displayEditSuperpowerForm", method = RequestMethod.GET)
    public String displayEditSuperpowerForm(HttpServletRequest request, Model model) {
        String superpowerIdParameter = request.getParameter("superpowerId");
        int superpowerId = Integer.parseInt(superpowerIdParameter);
        Superpower superpower = service.getSuperpower(superpowerId);
        model.addAttribute("superpower", superpower);
        return "editSuperpower";
    }

    @RequestMapping(value = "/editSuperpower", method = RequestMethod.POST)
    public String editSuperpower(@Valid @ModelAttribute("superpower") Superpower superpower, BindingResult result) {
        if (result.hasErrors()) {
            return "editSuperpower";
        }
        service.editSuperpower(superpower);
        return "redirect:displaySuperpowers";
    }

    //ORGANIZATIONS-------------------------------------------------------------
    @RequestMapping(value = "/displayOrganizations", method = RequestMethod.GET)
    public String displayOrganizations(HttpServletRequest request, Model model) {
        List<Organization> organizationList = service.getAllOrganizations();
        model.addAttribute("organizationList", organizationList);
        return "organizations";
    }

    @RequestMapping(value = "/displayAddOrganizationForm", method = RequestMethod.GET)
    public String displayAddOrganizationForm() {
        return "addOrganization";
    }

    @RequestMapping(value = "/addOrganization", method = RequestMethod.POST)
    public String addOrganization(HttpServletRequest request) {
        Organization organization = new Organization();
        organization.setName(request.getParameter("organization_name"));
        organization.setPhone(request.getParameter("phone"));
        Address ad = new Address();
        ad.setStreet(request.getParameter("address"));
        ad.setCity(request.getParameter("city"));
        ad.setZip(request.getParameter("zip"));
        ad.setState(request.getParameter("state"));
        ad = service.addAddress(ad);
        organization.setAddress(ad);
        organization.setDescription(request.getParameter("organization_desc"));
        service.addOrganization(organization);
        return "redirect:displayOrganizations";
    }

    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);
        service.deleteOrganization(organizationId);
        return "redirect:displayOrganizations";
    }

    @RequestMapping(value = "displayEditOrganizationForm", method = RequestMethod.GET)
    public String displayEditOrganizationForm(HttpServletRequest request, Model model) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);
        Organization organization = service.getOrganization(organizationId);
        model.addAttribute("organization", organization);
        Address address = organization.getAddress();
        model.addAttribute("address", address);
        return "editOrganization";
    }

    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(@Valid @ModelAttribute("organization") Organization organization,
            BindingResult result) {
        if (result.hasErrors()) {
            return "editOrganization";
        }
        Address ad = organization.getAddress();
        ad = service.editAddress(ad);
        organization.setAddress(ad);
        service.editOrgnization(organization);
        return "redirect:displayOrganizations";
    }

    @RequestMapping(value = "/displayOrganizationDetails", method = RequestMethod.GET)
    public String displayOrganizationDetails(HttpServletRequest request, Model model) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);
        Organization organization = service.getOrganization(organizationId);
        model.addAttribute("organization", organization);
        Address address = organization.getAddress();
        model.addAttribute("address", address);
        return "organizationDetail";
    }

    //UPLOAD PICTURES -----------------------------------------------------------
//    @RequestMapping(value = "/addPicture", method = RequestMethod.POST)
//    public void addPicture(HttpServletRequest request,
//            Model model,
//            @RequestParam("picture") MultipartFile pictureFile) {
//
//        // only save the pictureFile if the user actually uploaded something
//        if (!pictureFile.isEmpty()) {
//            try {
//                // we want to put the uploaded image into the 
//                // <pictureFolder> folder of our application. getRealPath
//                // returns the full path to the directory under Tomcat
//                // where we can save files.
//                String savePath = request
//                        .getSession()
//                        .getServletContext()
//                        .getRealPath("/") + pictureFolder;
//                java.io.File dir = new java.io.File(savePath);
//                // if <pictureFolder> directory is not there, 
//                // go ahead and create it
//                if (!dir.exists()) {
//                    dir.mkdirs();
//                }
//
//                // get the filename of the uploaded file - we'll use the
//                // same name on the server.
//                String filename = pictureFile.getOriginalFilename();
//                // transfer the contents of the uploaded pictureFile to 
//                // the server
//                pictureFile.transferTo(new java.io.File(savePath + filename));
//
//                // we successfully saved the pictureFile, now save a 
//                // Picture to the DAO
//                Picture picture = new Picture();
//                picture.setFilename(pictureFolder + filename);
//                //picture.setTitle(displayTitle);
//                picture = service.addPicture(picture);
//                
//               
//
//                // redirect to home page to redisplay the entire album
////                return "redirect:home";
//            } catch (Exception e) {
//                // if we encounter an exception, add the error message 
//                // to the model and return back to the pictureFile upload 
//                // form page
//                model.addAttribute("errorMsg", "File upload failed: "
//                        + e.getMessage());
////                return "addSuperhero";
//            }
//        } else {
//            // if the user didn't upload anything, add the error 
//            // message to the model and return back to the pictureFile 
//            // upload form page
//            model.addAttribute("errorMsg",
//                    "Please specify a non-empty file.");
////            return "addSuperhero";
//        }
//                //return "addSuperhero";
//    }
}
