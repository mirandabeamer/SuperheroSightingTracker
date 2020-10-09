/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.SearchTerm;
import com.sg.superherosightings.dao.SightingSearchTerm;
import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Superhero;
import com.sg.superherosightings.model.Superpower;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Picture;
import java.util.List;
import java.util.Map;


/**
 *
 * @author mirandabeamer
 */
public interface SuperheroSightingsServiceLayer {
   
    public Superhero addHero(Superhero hero);
    
    public Superhero getHero(int superheroId);
    
    public void deleteHero(int superheroId);
    
    public void editHero(Superhero hero);
    
    public List<Superhero> getAllHeroes();
    
    public List<Superhero> searchHeroes(Map<SearchTerm, String> criteria);
    
    public List<Sighting> getSightingByHero(int heroId);
    
    public Sighting addSighting(Sighting sighting);
    
    public Sighting getSighting(int sightingId);
    
    public void deleteSighting(int sightingId);
    
    public void editSighting(Sighting sighting);
    
    public List<Sighting> getAllSightings();
    
    public List<Sighting> searchSightings(Map<SightingSearchTerm, String> criteria);
    
    public List<Sighting> getMostRecentSightings();
    
    public Location addLocation(Location location);
    
    public Location getLocation(int locationId);
    
    public void deleteLocation(int locationId);
    
    public Location editLocation(Location location);
    
    public List<Location> getAllLocations();
    
    public Superpower addSuperpower(Superpower power);
    
    public Superpower getSuperpower(int superpowerId);
    
    public void deleteSuperpower(int superpowerId);
    
    public void editSuperpower(Superpower superpower);
    
    public List<Superpower> getAllSuperpowers();

    public Organization addOrganization(Organization organization);
    
    public void deleteOrganization(int organizationId);
    
    public void editOrgnization(Organization organization);
    
    public Organization getOrganization(int organizationId);
    
    public Organization getOrganizationByName(String organizationName);
    
    public List<Organization> getAllOrganizations();
    
    public Address addAddress(Address address);
    
    public Address getAddress(int addressId);
    
    public Address editAddress(Address address);
        
    public Picture addPicture(Picture picture);

    public void deletePicture(int pictureId);

    public Picture getPictureById(int pictureId); 

    public List<Picture> getAllPictures();

}
