/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SearchTerm;
import com.sg.superherosightings.dao.SightingSearchTerm;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Superhero;
import com.sg.superherosightings.service.SuperheroSightingsServiceLayer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author mirandabeamer
 */
@Controller
public class SearchController {
        
    SuperheroSightingsServiceLayer service;
    
    @Inject
    public SearchController(SuperheroSightingsServiceLayer service){
        this.service = service;
    }
    
    @RequestMapping(value="/search/heroes", method=RequestMethod.POST)
    @ResponseBody
    public String displaySearchHeroesPage() {
        return "search";
    }
    

    
    @RequestMapping(value="/search/sightings", method=RequestMethod.POST)
    @ResponseBody
    public List<Sighting> searchSightings(@RequestBody Map<String, String> searchMap){
        Map<SightingSearchTerm, String> criteria = new HashMap<>();
        //NAME, STATE, CITY, ZIP, DATE
        String currentTerm = searchMap.get("location-name");
        if(currentTerm !=null && ! currentTerm.isEmpty()) {
            criteria.put(SightingSearchTerm.NAME, currentTerm);
        }
        currentTerm = searchMap.get("city");
        if(currentTerm !=null && ! currentTerm.isEmpty()) {
            criteria.put(SightingSearchTerm.CITY, currentTerm);
        }
        currentTerm = searchMap.get("state");
        if(currentTerm !=null && ! currentTerm.isEmpty()) {
            criteria.put(SightingSearchTerm.STATE, currentTerm);
        }
        currentTerm = searchMap.get("zip");
        if(currentTerm !=null && ! currentTerm.isEmpty()) {
            criteria.put(SightingSearchTerm.ZIP, currentTerm);
        }
        currentTerm = searchMap.get("date");
        if(currentTerm !=null && ! currentTerm.isEmpty()) {
            criteria.put(SightingSearchTerm.DATE, currentTerm);
        }
        currentTerm = searchMap.get("superhero");
        if(currentTerm !=null && !currentTerm.isEmpty()){
            criteria.put(SightingSearchTerm.SUPERHERO_NAME, currentTerm);
        }
        
        List<Sighting> sightings = service.searchSightings(criteria);
        return sightings;
    }
    
    @RequestMapping(value="/search/superheroes", method=RequestMethod.POST)
    @ResponseBody
    public List<Superhero> searchHeroes(@RequestBody Map<String, String> searchMap){
        Map<SearchTerm, String> criteria = new HashMap<>();
        //SUPERHERO_NAME, HEROTYPE, ORGANIZATION, SUPERPOWER_ID
        String currentTerm=searchMap.get("superhero_name");
        if(currentTerm !=null && ! currentTerm.isEmpty()){
            criteria.put(SearchTerm.SUPERHERO_NAME, currentTerm);
        }
        
        currentTerm=searchMap.get("herotype");
        if(currentTerm !=null && ! currentTerm.isEmpty()){
            criteria.put(SearchTerm.HEROTYPE, currentTerm);
        }
        
        currentTerm=searchMap.get("organization");
        if(currentTerm !=null && ! currentTerm.isEmpty()){
            criteria.put(SearchTerm.ORGANIZATION_NAME, currentTerm);
        }
        
        currentTerm=searchMap.get("superpower");
        if(currentTerm !=null && ! currentTerm.isEmpty()){
            criteria.put(SearchTerm.SUPERPOWER_TYPE, currentTerm);
        }
        
        List<Superhero> heroes = service.searchHeroes(criteria);
        return heroes;
    }
}
