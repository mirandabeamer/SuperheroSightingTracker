/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Address;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Picture;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Superhero;
import com.sg.superherosightings.model.Superpower;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mirandabeamer
 */
public interface SuperheroSightingsDao {
    
    //add, delete, edit, get hero 
    public Superhero addHero(Superhero hero);
    
    public void deleteHero(int superheroId);
    
    public void editHero(Superhero hero);
    
    public Superhero getHero(int superheroId);
    
    public List<Superhero> getAllHeroes();
    
    //get heroes by location (and name, power, dates seen)
    public List<Superhero> searchHeroes(Map<SearchTerm, String> criteria);
    
    public List<Sighting> getSightingsForHero(int heroId); 
   
    
    //add, delete, edit organization 
    
    public Organization addOrganization(Organization organization);
    
    public void deleteOrganization(int organizationId);
    
    public void editOrgnization(Organization organization);
    
    public Organization getOrganization(int organizationId);
    
    public Integer getOrganizationIdByName(String organizationName);
    
    public List<Organization> getAllOrganizations();
        //list all members of organization
    public List<Superhero> listOrganizationMembers(int organizationId);
    
    //add, delete, edit, get sighting 
    
    public Sighting addSighting(Sighting sighting);
    
    public void deleteSighting(int sightingId);
    
    public void editSighting(Sighting sighting);
    
    public Sighting getSighting(int sightingId);
    
    public List<Sighting> getAllSightings();
    
    public List<Sighting> getMostRecentSightings();
    
    //add, delete, edit, get Superpowers
    
    public Superpower addPower(Superpower power);
    
    public void deletePower(int powerId);
    
    public void editPower(Superpower power);
    
    public Superpower getPower(int powerId);
    
    public Integer getSuperpowerIdBySuperpowerName(String superpowerName);
    
    public List<Superpower> getAllPowers();
    
    //add, delete, edit, get Addresses
    public Address addAddress(Address address);
    
    public void deleteAddress(int addressId);
    
    public void editAddress(Address address);
    
    public Address getAddress(int addressId);
    
    public List<Address> getAllAddresses();
    
        //retrieve all locations superhero has been seen
    public List<Sighting> searchSightings(Map<SightingSearchTerm, String> criteria);
    
    public Location addLocation(Location location);
    
    public void deleteLocation(int locationId);
    
    public void editLocation(Location location);
    
    public Location getLocation(int locationId);
    
    public List<Location> getAllLocations();
    
    public Picture addPicture(Picture picture);

    public void deletePicture(int pictureId);

    public Picture getPictureById(int pictureId); 

    public List<Picture> getAllPictures();
    
    public Picture getPictureBySuperheroId(int superheroId);
}
