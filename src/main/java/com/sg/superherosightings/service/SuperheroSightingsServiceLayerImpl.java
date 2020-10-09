/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.SearchTerm;
import com.sg.superherosightings.dao.SightingSearchTerm;
import com.sg.superherosightings.dao.SuperheroSightingsDao;
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
public class SuperheroSightingsServiceLayerImpl implements SuperheroSightingsServiceLayer {
    
    SuperheroSightingsDao dao;
    

    public SuperheroSightingsServiceLayerImpl(SuperheroSightingsDao dao){
        this.dao = dao;
    }

    @Override
    public Superhero addHero(Superhero hero) {
        return dao.addHero(hero);
    }

    @Override
    public Superhero getHero(int superheroId) {
        return dao.getHero(superheroId);
    }

    @Override
    public void deleteHero(int superheroId) {
        dao.deleteHero(superheroId);
    }

    @Override
    public void editHero(Superhero hero) {
         dao.editHero(hero);
    }

    @Override
    public List<Superhero> getAllHeroes() {
        return dao.getAllHeroes();
    }

    @Override
    public List<Superhero> searchHeroes(Map<SearchTerm, String> criteria) {
        return dao.searchHeroes(criteria);
    }

    @Override
    public List<Sighting> getSightingByHero(int heroId){
        return dao.getSightingsForHero(heroId);
    }
    
    @Override
    public Sighting addSighting(Sighting sighting) {
        return dao.addSighting(sighting);
    }

    @Override
    public Sighting getSighting(int sightingId) {
        return dao.getSighting(sightingId);
    }

    @Override
    public void deleteSighting(int sightingId) {
        dao.deleteSighting(sightingId);
    }

    @Override
    public void editSighting(Sighting sighting) {
         dao.editSighting(sighting);
    }

    @Override
    public List<Sighting> getAllSightings() {
        return dao.getAllSightings();
    }

    @Override
    public List<Sighting> searchSightings(Map<SightingSearchTerm, String> criteria) {
        return dao.searchSightings(criteria);
    }
    
    @Override
    public List<Sighting> getMostRecentSightings(){
        return dao.getMostRecentSightings();
    }

    @Override
    public Location addLocation(Location location) {
        return dao.addLocation(location);
    }

    @Override
    public Location getLocation(int locationId) {
        return dao.getLocation(locationId);
    }

    @Override
    public void deleteLocation(int locationId) {
        dao.deleteLocation(locationId);
    }

    @Override
    public Location editLocation(Location location) {
         dao.editLocation(location);
         return location;
    }

    @Override
    public List<Location> getAllLocations() {
        return dao.getAllLocations();
    }

    @Override
    public Superpower addSuperpower(Superpower power) {
        return dao.addPower(power);
    }

    @Override
    public Superpower getSuperpower(int superpowerId) {
        return dao.getPower(superpowerId);
    }
   

    @Override
    public void deleteSuperpower(int superpowerId) {
        dao.deletePower(superpowerId);
    }

    @Override
    public void editSuperpower(Superpower superpower) {
        dao.editPower(superpower);
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        return dao.getAllPowers();
    }

    @Override
    public Organization addOrganization(Organization organization) {
        return dao.addOrganization(organization);
    }

    @Override
    public void deleteOrganization(int organizationId) {
        dao.deleteOrganization(organizationId);
    }

    @Override
    public void editOrgnization(Organization organization) {
        dao.editOrgnization(organization);
    }

    @Override
    public Organization getOrganization(int organizationId) {
        return dao.getOrganization(organizationId);
    }
    
    @Override
    public Organization getOrganizationByName(String organizationName){
       int organizationId = dao.getOrganizationIdByName(organizationName);
       return dao.getOrganization(organizationId);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return dao.getAllOrganizations();
    }
    
    @Override
    public Address addAddress(Address address){
        return dao.addAddress(address);
    }
    
    @Override
    public Address editAddress(Address address) {
       dao.editAddress(address);
       return address;
    }
    
    @Override
    public Address getAddress(int addressId){
        return dao.getAddress(addressId);
    }

    @Override
    public Picture addPicture(Picture picture) {
        return dao.addPicture(picture);
    }

    @Override
    public void deletePicture(int pictureId) {
        dao.deletePicture(pictureId);
    }

    @Override
    public Picture getPictureById(int pictureId) {
        return dao.getPictureById(pictureId);
    }

    @Override
    public List<Picture> getAllPictures() {
        return dao.getAllPictures();
    }

}
