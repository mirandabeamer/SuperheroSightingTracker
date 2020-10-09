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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author mirandabeamer
 */
public class SuperheroSightingsDaoTest {

    SuperheroSightingsDao dao;
    Address address = new Address();
    Sighting sighting = new Sighting();
    Superhero superhero = new Superhero();
    Superpower superpower = new Superpower();
    Organization organization = new Organization();
    Location location = new Location();
    Picture picture = new Picture();

    

    public SuperheroSightingsDaoTest() {
    }
    
    

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        
        dao = ctx.getBean("superheroDao", SuperheroSightingsDao.class);

        //delete all organizations
        List<Organization> organizations = dao.getAllOrganizations();
        for(Organization currentOrg : organizations){
            dao.deleteOrganization(currentOrg.getOrganizationId());
        }
        //delete all sightings 
        List<Sighting> sightings = dao.getAllSightings();
        for(Sighting currentSighting : sightings){
            dao.deleteSighting(currentSighting.getSightingId());
        }
        
        // delete all superheros 
        List<Superhero> heroes = dao.getAllHeroes();
        for(Superhero currentHero: heroes){
            dao.deleteHero(currentHero.getSuperheroId());
        }
        //delete all locations
        List<Location> locations = dao.getAllLocations();
        for(Location currentLocation : locations){
            dao.deleteLocation(currentLocation.getLocationId());
        }
        
        //delete all addresses 
        List<Address> ads = dao.getAllAddresses();
        for(Address currentAd : ads){
            dao.deleteAddress(currentAd.getAddressId());
        }
        
        //delete all superpowers
        List<Superpower> powers = dao.getAllPowers();
        for(Superpower currentPower : powers){
            dao.deletePower(currentPower.getSuperpowerId());
        }
        
        List<Picture> pictures = dao.getAllPictures();
        for(Picture currentPicture : pictures){
            dao.deletePicture(currentPicture.getPictureId());
        }
        
        
        superpower.setSuperpowerName("Super strength");
        superpower.setSuperpowerDesc(("Can lift up to 2 Tons"));
        dao.addPower(superpower);
        
        address.setStreet("123 Main St");
        address.setCity("New York City");
        address.setState("New York");
        address.setZip("11111");
        address = dao.addAddress(address);
        
        organization.setName("DC");
        organization.setAddress(address);
        organization.setDescription("Good Guys");
        organization = dao.addOrganization(organization);
        
        picture.setFilename("picture.jpg");
        picture = dao.addPicture(picture);
        superhero.setPicture(picture);
        
        superhero.setName("Superman");
        superhero.setDescription("Red, white, and blue");
        superhero.setType("hero");
        List<Superpower> superpowers = new ArrayList();
        superpowers.add(superpower);
        superhero.setSuperpowers(superpowers);
        List<Organization> organizationsToHero = new ArrayList();
        organizationsToHero.add(organization);
        superhero.setOrganizations(organizationsToHero);
        superhero = dao.addHero(superhero);
        
        location.setLocationName("The market");
        location.setAddress(address);
        location.setLatitude("40");
        location.setLongitude("73");
        location = dao.addLocation(location);
       
        String date = "2020-08-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(date, formatter);
        sighting.setLocation(location);
        sighting.setDate(dateTime);
        List<Superhero> heroesSighted = new ArrayList();
        heroesSighted.add(superhero);
        sighting.setSuperheroes(heroesSighted);
        sighting = dao.addSighting(sighting);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addHero method, of class SuperheroSightingsDao.
     */
    @Test
    public void testAddGetHero() {
        Superhero hero = new Superhero();
        hero.setName("BatMan");
        hero.setDescription("Black Bat");
        hero.setType("hero");
        hero.setPicture(picture);
        List<Organization> orgs = new ArrayList();
        orgs.add(organization);
        hero.setOrganizations(orgs);
        List<Superpower> powers = new ArrayList();
        powers.add(superpower);
        hero.setSuperpowers(powers);
        
        hero = dao.addHero(hero);
        Superhero fromDao = dao.getHero(hero.getSuperheroId());
        
        assertEquals(hero, fromDao);
    }

    /**
     * Test of deleteHero method, of class SuperheroSightingsDao.
     */
    @Test
    public void testDeleteHero() {
        Superhero hero = new Superhero();
        hero.setName("BatMan");
        hero.setDescription("Black Bat");
        hero.setType("hero");
        hero.setPicture(picture);
        List<Organization> orgs = new ArrayList();
        orgs.add(organization);
        hero.setOrganizations(orgs);
        List<Superpower> powers = new ArrayList();
        powers.add(superpower);
        hero.setSuperpowers(powers);
        
        hero = dao.addHero(hero);
        Superhero fromDao = dao.getHero(hero.getSuperheroId());
        
        assertEquals(hero, fromDao);
        
        dao.deleteHero(hero.getSuperheroId());
        assertNull(dao.getHero(hero.getSuperheroId()));
        
    }

    /**
     * Test of editHero method, of class SuperheroSightingsDao.
     */
    @Test
    public void testEditHero() {   
        superhero.setName("Captain America");
        dao.editHero(superhero);
        Superhero fromDao = dao.getHero(superhero.getSuperheroId());
        assertEquals(superhero, fromDao);
        
    }

    /**
     * Test of getAllHeroes method, of class SuperheroSightingsDao.
     */
    @Test
    public void testGetAllHeroes() {
        Superhero hero2 = new Superhero();
        hero2.setName("BatMan");
        hero2.setDescription("Black Bat");
        hero2.setType("hero");
        hero2.setPicture(picture);
        List<Organization> orgs = new ArrayList();
        orgs.add(organization);
        hero2.setOrganizations(orgs);
        List<Superpower> powers = new ArrayList();
        powers.add(superpower);
        hero2.setSuperpowers(powers);
        
        hero2 = dao.addHero(hero2);
        List<Superhero> heroes = dao.getAllHeroes();
        assertEquals(2, heroes.size());
    }

    /**
     * Test of searchHeroes method, of class SuperheroSightingsDao.
     */
    @Test
    public void testSearchHeroes() {
        Superhero hero2 = new Superhero();
        hero2.setName("BatMan");
        hero2.setDescription("Black Bat");
        hero2.setType("hero");
        hero2.setPicture(picture);
        List<Organization> orgs = new ArrayList();
        orgs.add(organization);
        hero2.setOrganizations(orgs);
        List<Superpower> powers = new ArrayList();
        powers.add(superpower);
        hero2.setSuperpowers(powers);
        hero2 = dao.addHero(hero2);
        
        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.HEROTYPE, "Hero");
        List<Superhero> heroes = dao.searchHeroes(criteria);
        assertEquals(2, heroes.size());
        
        criteria.put(SearchTerm.SUPERHERO_NAME, "Superman");
        List<Superhero> heroes2 = dao.searchHeroes(criteria);
        assertEquals(1, heroes2.size());
        
    }

//    /**
//     * Test of addOrganization method, of class SuperheroSightingsDao.
//     */
    @Test
    public void testAddGetOrganization() {
        Organization org = new Organization(); 
        Address ad = new Address();
        org.setName("Marvel");
        org.setDescription("Good Guys");
        org.setPhone("5555555555");
        org.setAddress(address);
        org = dao.addOrganization(org);
        Organization fromDao = dao.getOrganization(org.getOrganizationId());
        assertEquals(org, fromDao);
    }

    /**
     * Test of deleteOrganization method, of class SuperheroSightingsDao.
     */
    @Test
    public void testDeleteOrganization() {
        Organization org = new Organization(); 
        Address ad = new Address();
        org.setName("Marvel");
        org.setDescription("Good Guys");
        org.setPhone("5555555555");
        org.setAddress(address);
        org = dao.addOrganization(org);
        Organization fromDao = dao.getOrganization(org.getOrganizationId());
        assertEquals(org, fromDao);
        dao.deleteOrganization(org.getOrganizationId());
        assertNull(dao.getOrganization(org.getOrganizationId()));
    }

    /**
     * Test of editOrgnization method, of class SuperheroSightingsDao.
     */
    @Test
    public void testEditOrgnization() {
        organization.setDescription("Strong guys");
        dao.editOrgnization(organization);
        Organization fromDao = dao.getOrganization(organization.getOrganizationId());
        assertEquals(fromDao, organization);
    }

    /**
     * Test of getAllOrganizations method, of class SuperheroSightingsDao.
     */
    @Test
    public void testGetAllOrganizations() {
        Organization org = new Organization(); 
        Address ad = new Address();
        org.setName("Marvel");
        org.setDescription("Good Guys");
        org.setPhone("5555555555");
        org.setAddress(address);
        org = dao.addOrganization(org);

        List<Organization> fromDao = dao.getAllOrganizations();
        assertEquals(2, fromDao.size());
    }

    /**
     * Test of listOrganizationMembers method, of class SuperheroSightingsDao.
     */
    @Test
    public void testListOrganizationMembers() {
        Superhero hero = new Superhero();
        hero.setName("Batman");
        hero.setDescription("Black Bat");
        hero.setType("hero");
        hero.setPicture(picture);
        List<Organization> orgs = new ArrayList();
        orgs.add(organization);
        hero.setOrganizations(orgs);
        List<Superpower> powers = new ArrayList();
        powers.add(superpower);
        hero.setSuperpowers(powers);
        dao.addHero(hero);
        
        List<Superhero> heroesInOrg = dao.listOrganizationMembers(organization.getOrganizationId());
        assertEquals(2, heroesInOrg.size());
        
        //ensure no error if returns empty list
        Organization org2 = new Organization(); 
        org2.setName("Marvel");
        org2.setAddress(address);
        dao.addOrganization(org2);
        List<Superhero> heroesInMarvel = dao.listOrganizationMembers(org2.getOrganizationId());
        assertEquals(0, heroesInMarvel.size());
    }

    /**
     * Test of addSighting method, of class SuperheroSightingsDao.
     */
    @Test
    public void testAddGetSighting() {
        Sighting sight = new Sighting();
        sight.setLocation(location);
        String date = "2020-09-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(date, formatter);
        sight.setDate(dateTime);
        List<Superhero> heroesSighted = new ArrayList();
        heroesSighted.add(superhero);
        sight.setSuperheroes(heroesSighted);
        dao.addSighting(sight);
        
        
        Sighting fromDao = dao.getSighting(sight.getSightingId());
        assertEquals(sight, fromDao);
    }

    /**
     * Test of deleteSighting method, of class SuperheroSightingsDao.
     */
    @Test
    public void testDeleteSighting() {
        dao.deleteSighting(sighting.getSightingId());
        assertNull(dao.getSighting(sighting.getSightingId()));
    }

    /**
     * Test of editSighting method, of class SuperheroSightingsDao.
     */
    @Test
    public void testEditSighting() {
        String date = "2019-09-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(date, formatter);
        sighting.setDate(dateTime);
        dao.editSighting(sighting);
        Sighting fromDao = dao.getSighting(sighting.getSightingId());
        assertEquals(sighting, fromDao);
    }

    /**
     * Test of getAllSightings method, of class SuperheroSightingsDao.
     */
    @Test
    public void testGetAllSightings() {
        Sighting sight = new Sighting();
        sight.setLocation(location);
        String date = "2020-09-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(date, formatter);
        sight.setDate(dateTime);
        List<Superhero> heroesSighted = new ArrayList();
        heroesSighted.add(superhero);
        sight.setSuperheroes(heroesSighted);
        dao.addSighting(sight);
        
        List<Sighting> sightings = dao.getAllSightings();
        assertEquals(2, sightings.size());
    }

    /**
     * Test of searchSightings method, of class SuperheroSightingsDao.
     */
    @Test
    public void testSearchSightings() {
        Sighting sight = new Sighting();
        Location loc = new Location();
        loc.setAddress(address);
        loc.setLocationName("The hotel");
        loc.setLatitude("10");
        loc.setLongitude("20");
        dao.addLocation(loc);
        sight.setLocation(loc);
        String date = "2020-08-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(date, formatter);
        sight.setDate(dateTime);
        List<Superhero> heroesSighted = new ArrayList();
        heroesSighted.add(superhero);
        sight.setSuperheroes(heroesSighted);
        dao.addSighting(sight);
        
        Map<SightingSearchTerm, String> criteria = new HashMap<>();
        criteria.put(SightingSearchTerm.DATE, date);
        List<Sighting> sightings2 = dao.searchSightings(criteria);
        assertEquals(2, sightings2.size());
        
        criteria.put(SightingSearchTerm.NAME, "The market");
        List<Sighting> Sightings = dao.searchSightings(criteria);
        assertEquals(1, Sightings.size());
        

    }
    
     @Test
     public void addGetpower(){
         Superpower power = new Superpower();
         power.setSuperpowerName("Flying");
         power.setSuperpowerDesc("Can fly for up to 2 hours");
         dao.addPower(power);
         Superpower fromDao = dao.getPower(power.getSuperpowerId());
         assertEquals(power, fromDao);
     }
     
     @Test
     public void deletePower() {
        dao.deletePower(superpower.getSuperpowerId());
        assertNull(dao.getPower(superpower.getSuperpowerId()));
     }

     @Test
     public void editPower() {
         superpower.setSuperpowerDesc("Can lift up to 3 tons");
         dao.editPower(superpower);
         Superpower fromdao = dao.getPower(superpower.getSuperpowerId());
         assertEquals(superpower, fromdao);
     }
     
     
     @Test 
     public void addGetAddress(){
         Address ad = new Address();
         ad.setStreet("4321 First Ave");
         ad.setCity("Chicago");
         ad.setState("Illinois");
         ad.setZip("22222");
         dao.addAddress(ad);
         Address fromDao = dao.getAddress(ad.getAddressId());
         assertEquals(fromDao, ad);
     }
     
     @Test 
     public void deleteAddress(){
         Address ad = new Address();
         ad.setStreet("4321 First Ave");
         ad.setCity("Chicago");
         ad.setState("Illinois");
         ad.setZip("22222");
         ad = dao.addAddress(ad);
         Address fromDao = dao.getAddress(ad.getAddressId());
         assertEquals(fromDao, ad);
         dao.deleteAddress(ad.getAddressId());
         assertNull(dao.getAddress(ad.getAddressId()));
     }

    @Test
    public void editAddress(){
       address.setZip("22222");
       dao.editAddress(address);
       Address fromDao = dao.getAddress(address.getAddressId());
       assertEquals(fromDao, address);
    }
    
    @Test
    public void getAllAddresses() {
         Address ad = new Address();
         ad.setStreet("4321 First Ave");
         ad.setCity("Chicago");
         ad.setState("Illinois");
         ad.setZip("22222");
         ad = dao.addAddress(ad);
         List<Address> ads = dao.getAllAddresses();
         assertEquals(2, ads.size());
    }
    
    @Test
    public void addLocation() {
        Location loc = new Location();
        loc.setLocationName("New location");
        loc.setDescription("back alley");
        loc.setAddress(address);
        loc.setLatitude("45");
        loc.setLongitude("45");
        loc = dao.addLocation(loc);
        Location fromDao = dao.getLocation(loc.getLocationId());
        assertEquals(loc, fromDao);
    }
}
