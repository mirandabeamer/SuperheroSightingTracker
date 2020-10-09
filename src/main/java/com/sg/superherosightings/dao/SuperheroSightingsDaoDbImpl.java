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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mirandabeamer
 */
public class SuperheroSightingsDaoDbImpl implements SuperheroSightingsDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //INSERTS 
    private static final String SQL_INSERT_SUPERHERO
            = "insert into superhero (superhero_name, superhero_desc, heroType_id, picture_id) values (?, ?, ?, ?)";

    private static final String SQL_INSERT_SUPERPOWER
            = "insert into superpower (superpower_type, superpower_desc) values (?, ?)";

    private static final String SQL_INSERT_SUPERHERO_POWER
            = "insert into superhero_superpower (superhero_id, superpower_id) values(?, ?)";

    private static final String SQL_INSERT_HERO_TYPE
            = "insert ignore into heroType(heroType) values (?)";

    private static final String SQL_INSERT_ORGANIZATION
            = "insert ignore into organization(organization_name, address_id, phone, organization_desc) values(?, ?, ?, ?)";

    private static final String SQL_INSERT_HERO_ORGANIZATION
            = "insert into hero_organization(superhero_id, organization_id) values(?, ?)";

    private static final String SQL_INSERT_ADDRESS
            = "insert into address(street, city, state, zip) values (?, ?, ?, ?)";

    private static final String SQL_INSERT_SIGHTING
            = "insert into sighting(location_id, date) values (?, ?)";

    private static final String SQL_INSERT_LOCATION
            = "insert ignore into location(name, address_id, longitude, latitude, location_desc) values(?, ?, ?, ?, ?)";

    private static final String SQL_INSERT_HERO_SIGHTING
            = "insert into hero_sighting(superhero_id, sighting_id) values(?, ?)";

    private static final String SQL_INSERT_PICTURE
            = "insert into picture(filename) values(?)";

    //DELETES 
    private static final String SQL_DELETE_HERO
            = "delete from superhero where superhero_id = ?";

    private static final String SQL_DELETE_HERO_ORGANIZATION_BY_HERO_ID
            = "delete from hero_organization where superhero_id = ?";

    private static final String SQL_DELETE_SUPERHERO_SUPERPOWER_BY_HERO_ID
            = "delete from superhero_superpower where superhero_id = ?";

    private static final String SQL_DELETE_HERO_ORGANIZATION_BY_ORGANIZATION_ID
            = "delete from hero_organization where organization_id = ?";

    private static final String SQL_DELETE_ORGANIZATION
            = "delete from organization where organization_id = ?";

    private static final String SQL_DELETE_HERO_SIGHTING_BY_HERO_ID
            = "delete from hero_sighting where superhero_id = ?";

    private static final String SQL_DELETE_SIGHTING
            = "delete from sighting where sighting_id = ?";

    private static final String SQL_DELETE_LOCATION
            = "delete from location where location_id = ?";

    private static final String SQL_DELETE_HERO_SIGHTING_BY_SIGHTING_ID
            = "delete from hero_sighting where sighting_id = ?";

    private static final String SQL_DELETE_ADDRESS
            = "delete from address where address_id = ?";

    private static final String SQL_DELETE_SUPERPOWER
            = "delete from superpower where superpower_id = ?";
    
    private static final String SQL_DELETE_PICTURE
            ="delete from picture where picture_id = ?";
    


    //UPDATES 
    private static final String SQL_UPDATE_SUPERHERO
            = "update superhero set superhero_name = ?, superhero_desc = ?, heroType_id = ?"
            + " where superhero_id = ?";

    private static final String SQL_UPDATE_ORGANIZATION
            = "update organization set organization_name = ?, phone = ?, organization_desc = ? "
            + "where organization_id = ?";

    private static final String SQL_UPDATE_HERO_ORGANIZATION
            = "update hero_organization set organization_id = ? where superhero_id = ?";

    private static final String SQL_UPDATE_SUPERHERO_SUPERPOWER
            = "update superhero_superpower set superpower_id = ? where superhero_id = ?";

    private static final String SQL_UPDATE_SIGHTING
            = "update sighting set location_id = ?, date = ? where sighting_id = ?";

    private static final String SQL_UPDATE_LOCATION
            = "update location set name = ?, address_id = ?, longitude = ?, latitude = ?, location_desc = ? where location_id = ?";

    private static final String SQL_UPDATE_ADDRESS
            = "update address set street = ?, state = ?, zip = ?, city = ? where address_id = ?";

    private static final String SQL_UPDATE_POWER
            = "update superpower set superpower_type = ?, superpower_desc = ? where superpower_id = ?";

    //SELECTS
    private static final String SQL_SELECT_SUPERHERO
            = "select superhero_id, superhero_name, superhero_desc, ht.heroType "
            + "from superhero join heroType ht ON superhero.heroType_id = ht.heroType_id "
            + "where superhero_id = ?";

    private static final String SQL_SELECT_ORGANIZATION
            = "select organization_id, organization_name, organization_desc, phone "
            + "from organization where organization_id = ?";

    private static final String SQL_SELECT_ADDRESS
            = "select address_id, street, city, state, zip "
            + "from address where address_id = ?";

    private static final String SQL_SELECT_SUPERPOWER
            = "select superpower_id, superpower_type, superpower_desc "
            + "from superpower where superpower_id = ?";

    private static final String SQL_SELECT_SIGHTING
            = "select sighting_id, date "
            + "from sighting "
            + "where sighting_id = ?";

    private static final String SQL_SELECT_LOCATION
            = "select l.location_id, l.name, l.longitude, l.latitude, l.location_desc "
            + "from location l "
            + " where location_id =?";
    
    private static final String SQL_SELECT_PICTURE
            ="select picture_id, filename from picture where picture_id = ?";

    private static final String SQL_SELECT_ORGANIZATION_ID_BY_ORGANIZATION
            = "select organization_id "
            + "from organization where organization_name = ?";

    private static final String SQL_SELECT_HEROTYPE_ID_BY_HEROTYPE
            = "select herotype_id from herotype where heroType = ?";

    private static final String SQL_SELECT_SUPERPOWER_ID_BY_SUPERPOWER_TYPE
            = "select superpower_id from superpower where superpower_type = ?";

    private static final String SQL_SELECT_SUPERPOWERS_BY_SUPERHERO_ID
            = "select sp.superpower_id, sp.superpower_type, sp.superpower_desc "
            + "from superpower sp join superhero_superpower shsp ON sp.superpower_id = shsp.superpower_id"
            + " join superhero sh ON shsp.superhero_id = sh.superhero_id"
            + " where sh.superhero_id = ?";

    private static final String SQL_SELECT_ALL_HEROES
            = "select superhero_id, superhero_name, superhero_desc, heroType"
            + " from superhero s "
            + "join HeroType ht ON s.heroType_id = ht.HeroType_id";

    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "select * from organization";

    private static final String SQL_SELECT_ALL_ADDRESSES
            = "select * from address";

    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select s.sighting_id, l.name, l.longitude, l.latitude, l.location_desc, l.address_id, s.date "
            + "from sighting s "
            + "join location l ON s.location_id = l.location_id ";

    private static final String SQL_SELECT_ALL_SUPERPOWERS
            = "select * from superpower";

    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from location";
    
    private static final String SQL_SELECT_ALL_PICTURES
            ="select * from picture";

    private static final String SQL_SELECT_ORGANIZATIONS_BY_SUPERHERO_ID
            = "select o.organization_id, organization_name, o.organization_desc, o.phone, a.address_id, a.street, a.city, a.state, a.zip "
            + "from organization o join hero_organization ho ON o.organization_id = ho.organization_id "
            + "join superhero s ON ho.superhero_id = s.superhero_id "
            + "join address a ON o.address_id = a.address_id "
            + "where s.superhero_id = ?";

    private static final String SQL_SElECT_ADDRESS_BY_ORGANIZATION_ID
            = "select a.address_id, street, city, state, zip "
            + "from address a "
            + "join organization o ON a.address_id = o.address_id "
            + "where o.organization_id = ?";

    private static final String SQL_SELECT_HERO_BY_ORGANIZATION_ID
            = "select s.superhero_id, superhero_name, superhero_desc, heroType "
            + "from superhero s "
            + "join hero_organization ho ON s.superhero_id = ho.superhero_id "
            + "join heroType ht ON s.heroType_id = ht.heroType_id "
            + "where ho.organization_id = ?";

    private static final String SQL_SELECT_HEROES_BY_SIGHTING_ID
            = "select sh.superhero_id, superhero_name, superhero_desc, heroType "
            + "from superhero sh "
            + "join heroType ht ON sh.heroType_id = ht.heroType_id "
            + "join hero_sighting hs ON sh.superhero_id = hs.superhero_id "
            + "where hs.sighting_id = ?";

    private static final String SQL_SELECT_ADDRESS_BY_SIGHTING_ID
            = "select a.address_id, street, city, state, zip "
            + "from address a "
            + "join location l ON a.address_id = l.address_id "
            + "join sighting s ON l.location_id = s.location_id "
            + "where sighting_id = ?";

    private static final String SQL_SELECT_LOCATION_BY_SIGHTING_ID
            = "select l.location_id, l.name, l.longitude, l.latitude, l.location_desc "
            + "from location l "
            + "join sighting s on l.location_id = s.location_id "
            + "where sighting_id = ?";

    private static final String SQL_SELECT_ADDRESS_BY_LOCATION_ID
            = "select a.address_id, street, city, state, zip "
            + "from address a "
            + "join location l ON a.address_id = l.address_id "
            + " where location_id = ?";

    private static final String SQL_SELECT_ADDRESS_ID_BY_LOCATION_ID
            = "select a.address_id "
            + "from address a "
            + "join location l on a.address_id = l.address_id where location_id = ?";

    private static final String SQL_GET_LOCATION_ID_BY_SIGHTING_ID
            = "select location_id from sighting where sighting_id = ?";

    private static final String SQL_GET_SIGHTINGS_BY_HERO_ID
            = "select s.sighting_id, l.name, l.longitude, l.latitude, l.location_desc, l.address_id, s.date "
            + "from sighting s "
            + "join location l ON s.location_id = l.location_id "
            + " join hero_sighting hs on s.sighting_id = hs.sighting_id"
            + " where superhero_id = ?";

    private static final String SQL_SELECT_LOCATION_ID_BY_LOCATION_NAME
            = "select location_id from location where name = ?";

    private static final String SQL_SELECT_MOST_RECENT_SIGHTINGS
            = "select s.sighting_id, l.name, l.longitude, l.latitude, l.location_desc, l.address_id, s.date "
            + "from sighting s "
            + "join location l ON s.location_id = l.location_id "
            + "order by s.date desc limit 10";
    
    private static final String SQL_SELECT_PICTURE_BY_SUPERHERO_ID
            = "select picture.picture_id, filename from picture"
            + " join superhero on superhero.picture_id = picture.picture_id where superhero.superhero_id = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Superhero addHero(Superhero hero) {
        //hero type - hero vs villian
        jdbcTemplate.update(SQL_INSERT_HERO_TYPE, hero.getType());
        int heroTypeId = jdbcTemplate.queryForObject(SQL_SELECT_HEROTYPE_ID_BY_HEROTYPE, Integer.class, hero.getType());
        //if no picture is uploaded, ok to insert null as pictureId
        int pictureId = hero.getPicture().getPictureId();
        String pictureIdStr = Integer.toString(pictureId);
        if(pictureId == 0){
            pictureIdStr = null;
        }

        //add to hero table 
        jdbcTemplate.update(SQL_INSERT_SUPERHERO,
                hero.getName(),
                hero.getDescription(),
                heroTypeId,
                pictureIdStr);
        int superheroId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        hero.setSuperheroId(superheroId);

        //add each power to superpower table using helper method
        insertHeroesPowers(hero);

        //add to superhero_organization table using helper method - if hero belongs to organization
        if (hero.getOrganizations().size() > 0) {
            insertHeroesOrganizations(hero);
        }

        return hero;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteHero(int superheroId) {
        //check to ensure hero is not currently in sighting 
        List<Sighting> sightingsForHero = getSightingsForHero(superheroId);
        if (sightingsForHero.size() == 0) {
            //delete from bridge tables first 
            jdbcTemplate.update(SQL_DELETE_HERO_ORGANIZATION_BY_HERO_ID, superheroId);
            jdbcTemplate.update(SQL_DELETE_SUPERHERO_SUPERPOWER_BY_HERO_ID, superheroId);
            jdbcTemplate.update(SQL_DELETE_HERO_SIGHTING_BY_HERO_ID, superheroId);
            jdbcTemplate.update(SQL_DELETE_HERO, superheroId);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void editHero(Superhero hero) {
        //get hero type id 
        int heroTypeId = jdbcTemplate.queryForObject(SQL_SELECT_HEROTYPE_ID_BY_HEROTYPE, Integer.class, hero.getType());

        //update hero table
        jdbcTemplate.update(SQL_UPDATE_SUPERHERO,
                hero.getName(),
                hero.getDescription(),
                heroTypeId,
                hero.getSuperheroId());

        //delete from bridge table and reset them for both organization and superpower
        jdbcTemplate.update(SQL_DELETE_HERO_ORGANIZATION_BY_HERO_ID, hero.getSuperheroId());
        insertHeroesOrganizations(hero);

        jdbcTemplate.update(SQL_DELETE_SUPERHERO_SUPERPOWER_BY_HERO_ID, hero.getSuperheroId());
        insertHeroesPowers(hero);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Superhero getHero(int superheroId) {
        try {
            Superhero hero = jdbcTemplate.queryForObject(SQL_SELECT_SUPERHERO, new SuperheroMapper(), superheroId);
            hero.setOrganizations(findOrganizationsForHero(hero));
            hero.setSuperpowers(findPowersForHero(hero));
            Picture picture = getPictureBySuperheroId(superheroId);
            hero.setPicture(picture);
            return hero;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Superhero> getAllHeroes() {
        List<Superhero> heroes = jdbcTemplate.query(SQL_SELECT_ALL_HEROES, new SuperheroMapper());
        return associateOrganizationAndPowerWithHeroes(heroes);
    }

    @Override
    public List<Superhero> searchHeroes(Map<SearchTerm, String> criteria) {
        if (criteria.isEmpty()) {
            return getAllHeroes();
        } else {

            //build prepared statement based on search
            StringBuilder sQuery = new StringBuilder("select superhero.superhero_id, superhero_name, superhero_desc, ht.heroType "
                    + "from superhero join heroType ht ON superhero.heroType_id = ht.heroType_id "
                    + "join superhero_superpower ON superhero.superhero_id = superhero_superpower.superhero_id "
                    + "join superpower ON superhero_superpower.superpower_id = superpower.superpower_id "
                    + "join hero_organization on superhero.superhero_id = hero_organization.superhero_id "
                    + "join organization on hero_organization.organization_id = organization.organization_id "
                    + "where ");

            //build where clause 
            int numParams = criteria.size();
            int paramPosition = 0;

            //put positional parameter into array
            String[] paramVals = new String[numParams];
            Set<SearchTerm> keySet = criteria.keySet();
            Iterator<SearchTerm> iter = keySet.iterator();

            //build the where clause based on key/value pairs in the map
            while (iter.hasNext()) {
                SearchTerm currentKey = iter.next();
                //if it's not the first one in, will need "and" appended 
                if (paramPosition > 0) {
                    sQuery.append(" and ");
                }

                sQuery.append(currentKey);
                sQuery.append(" =? ");

                //grab value for search criteria and put it into array
                paramVals[paramPosition] = criteria.get(currentKey);
                paramPosition++;
            }
            List<Superhero> heroes = jdbcTemplate.query(sQuery.toString(), new SuperheroMapper(), paramVals);
            return associateOrganizationAndPowerWithHeroes(heroes);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Sighting> getSightingsForHero(int heroId) {
        List<Sighting> sightings = jdbcTemplate.query(SQL_GET_SIGHTINGS_BY_HERO_ID, new SightingMapper(), heroId);
        //set location for each sighting
        for (Sighting sighting : sightings) {
            Location loc = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING_ID, new LocationMapper(), sighting.getSightingId());
            Address ad = jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS_BY_SIGHTING_ID, new AddressMapper(), sighting.getSightingId());
            loc.setAddress(ad);
            sighting.setLocation(loc);
            sighting.setSuperheroes(findHeroesForSightings(sighting));
        }
        return sightings;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization addOrganization(Organization organization) {
        Address address = organization.getAddress();

        //insert organization 
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                organization.getName(),
                address.getAddressId(),
                organization.getPhone(),
                organization.getDescription());
        //get back assigned id, set into object and return. 
        int organizationId = jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION_ID_BY_ORGANIZATION, Integer.class, organization.getName());
        organization.setOrganizationId(organizationId);
        return organization;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteOrganization(int organizationId) {
        //must delete from bridge table first 
        jdbcTemplate.update(SQL_DELETE_HERO_ORGANIZATION_BY_ORGANIZATION_ID, organizationId);
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, organizationId);

    }

    @Override
    public void editOrgnization(Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                organization.getName(),
                organization.getPhone(),
                organization.getDescription(),
                organization.getOrganizationId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization getOrganization(int organizationId) {
        try {
            Address ad = jdbcTemplate.queryForObject(SQL_SElECT_ADDRESS_BY_ORGANIZATION_ID, new AddressMapper(), organizationId);
            Organization org = jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION, new OrganizationMapper(), organizationId);
            org.setAddress(ad);
            return org;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Integer getOrganizationIdByName(String organizationName) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION_ID_BY_ORGANIZATION, Integer.class, organizationName);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Organization> getAllOrganizations() {
        List<Organization> orgs = jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
        for (Organization currentOrg : orgs) {
            Address address = jdbcTemplate.queryForObject(SQL_SElECT_ADDRESS_BY_ORGANIZATION_ID, new AddressMapper(), currentOrg.getOrganizationId());
            currentOrg.setAddress(address);
        }
        return orgs;
    }

    @Override
    public List<Superhero> listOrganizationMembers(int organizationId) {
        List<Superhero> heroes = jdbcTemplate.query(SQL_SELECT_HERO_BY_ORGANIZATION_ID, new SuperheroMapper(), organizationId);
        return associateOrganizationAndPowerWithHeroes(heroes);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting addSighting(Sighting sighting) {
//
//        jdbcTemplate.update(SQL_INSERT_LOCATION,
//                sighting.getLocation().getLocationName(),
//                sighting.getLocation().getAddress().getAddressId(),
//                sighting.getLocation().getLongitude(),
//                sighting.getLocation().getLatitude(),
//                sighting.getLocation().getDescription());
//        int locationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getLocation().getLocationId(),
                sighting.getDate());
        int sightingId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(sightingId);

        insertHeroesSightings(sighting);
        return sighting;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSighting(int sightingId) {
        int locationId = jdbcTemplate.queryForObject(SQL_GET_LOCATION_ID_BY_SIGHTING_ID, Integer.class, sightingId);
        jdbcTemplate.update(SQL_DELETE_HERO_SIGHTING_BY_SIGHTING_ID, sightingId);
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void editSighting(Sighting sighting) {
        int locationId = jdbcTemplate.queryForObject(SQL_GET_LOCATION_ID_BY_SIGHTING_ID, Integer.class, sighting.getSightingId());

        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                sighting.getLocation().getLocationName(),
                sighting.getLocation().getAddress().getAddressId(),
                sighting.getLocation().getLongitude(),
                sighting.getLocation().getLatitude(),
                sighting.getLocation().getDescription(),
                locationId);

        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                locationId,
                sighting.getDate(),
                sighting.getSightingId());

        //delete from bridge table and reset them for both organization and superpower
        jdbcTemplate.update(SQL_DELETE_HERO_SIGHTING_BY_SIGHTING_ID, sighting.getSightingId());
        insertHeroesSightings(sighting);
    }

    @Override
    public Sighting getSighting(int sightingId) {
        try {
            Location loc = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING_ID, new LocationMapper(), sightingId);
            Address ad = jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS_BY_SIGHTING_ID, new AddressMapper(), sightingId);
            Sighting sighting = jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING, new SightingMapper(), sightingId);
            sighting.setSuperheroes(findHeroesForSightings(sighting));
            loc.setAddress(ad);
            sighting.setLocation(loc);
            return sighting;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS, new SightingMapper());
        for (Sighting sighting : sightings) {
            Location loc = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING_ID, new LocationMapper(), sighting.getSightingId());
            Address ad = jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS_BY_SIGHTING_ID, new AddressMapper(), sighting.getSightingId());
            loc.setAddress(ad);
            sighting.setLocation(loc);

            sighting.setSuperheroes(findHeroesForSightings(sighting));
        }
        return sightings;
    }

    @Override
    public List<Sighting> searchSightings(Map<SightingSearchTerm, String> criteria) {
        if (criteria.isEmpty()) {
            return getAllSightings();
        } else {

            //build prepared statement based on search
            StringBuilder sQuery = new StringBuilder("select s.sighting_id, date "
                    + "from sighting s "
                    + "join location l ON s.location_id = l.location_id "
                    + "join address on l.address_id = address.address_id "
                    + "join hero_sighting ss on s.sighting_id = ss.sighting_id "
                    + "join superhero sh on ss.superhero_id = sh.superhero_id where ");

            //build where clause 
            int numParams = criteria.size();
            int paramPosition = 0;

            //put positional parameter into array
            String[] paramVals = new String[numParams];
            Set<SightingSearchTerm> keySet = criteria.keySet();
            Iterator<SightingSearchTerm> iter = keySet.iterator();

            //build the where clause based on key/value pairs in the map
            while (iter.hasNext()) {
                SightingSearchTerm currentKey = iter.next();
                //if it's not the first one in, will need "and" appended 
                if (paramPosition > 0) {
                    sQuery.append(" and ");
                }

                sQuery.append(currentKey);
                sQuery.append(" =? ");

                //grab value for search criteria and put it into array
                paramVals[paramPosition] = criteria.get(currentKey);
                paramPosition++;
            }
            List<Sighting> sightings = jdbcTemplate.query(sQuery.toString(), new SightingMapper(), paramVals);
            for (Sighting sighting : sightings) {
                Location loc = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING_ID, new LocationMapper(), sighting.getSightingId());
                Address ad = jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS_BY_SIGHTING_ID, new AddressMapper(), sighting.getSightingId());
                loc.setAddress(ad);
                sighting.setLocation(loc);

                sighting.setSuperheroes(findHeroesForSightings(sighting));
            }
            return sightings;
        }

    }

    @Override
    public List<Sighting> getMostRecentSightings() {
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_MOST_RECENT_SIGHTINGS, new SightingMapper());
        for (Sighting sighting : sightings) {
            Location loc = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING_ID, new LocationMapper(), sighting.getSightingId());
            Address ad = jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS_BY_SIGHTING_ID, new AddressMapper(), sighting.getSightingId());
            loc.setAddress(ad);
            sighting.setLocation(loc);

            sighting.setSuperheroes(findHeroesForSightings(sighting));
        }
        return sightings;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Superpower addPower(Superpower power) {
        jdbcTemplate.update(SQL_INSERT_SUPERPOWER,
                power.getSuperpowerName(),
                power.getSuperpowerDesc());
        int powerId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        power.setSuperpowerId(powerId);
        return power;

    }

    @Override
    public void deletePower(int powerId) {
        jdbcTemplate.update(SQL_DELETE_SUPERPOWER, powerId);
    }

    @Override
    public void editPower(Superpower power) {
        jdbcTemplate.update(SQL_UPDATE_POWER,
                power.getSuperpowerName(),
                power.getSuperpowerDesc(),
                power.getSuperpowerId());
    }

    @Override
    public Superpower getPower(int powerId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SUPERPOWER, new PowerMapper(), powerId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Integer getSuperpowerIdBySuperpowerName(String superpowerName) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SUPERPOWER_ID_BY_SUPERPOWER_TYPE, Integer.class, superpowerName);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Superpower> getAllPowers() {
        List<Superpower> powers = jdbcTemplate.query(SQL_SELECT_ALL_SUPERPOWERS, new PowerMapper());
        return powers;
    }

    @Override
    public Address addAddress(Address address) {
        jdbcTemplate.update(SQL_INSERT_ADDRESS,
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZip());
        int addressId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        address.setAddressId(addressId);
        return address;
    }

    @Override
    public void deleteAddress(int addressId) {
        jdbcTemplate.update(SQL_DELETE_ADDRESS, addressId);
    }

    @Override
    public void editAddress(Address address) {
        jdbcTemplate.update(SQL_UPDATE_ADDRESS,
                address.getStreet(),
                address.getState(),
                address.getZip(),
                address.getCity(),
                address.getAddressId());
    }

    @Override
    public Address getAddress(int addressId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS, new AddressMapper(), addressId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Address> getAllAddresses() {
        List<Address> addresses = jdbcTemplate.query(SQL_SELECT_ALL_ADDRESSES, new AddressMapper());
        return addresses;
    }

    @Override
    public Location addLocation(Location location) {
        Address address = location.getAddress();
        int addressId = address.getAddressId();
        if (addressId == 0) {
            jdbcTemplate.update(SQL_INSERT_ADDRESS,
                    address.getStreet(),
                    address.getCity(),
                    address.getState(),
                    address.getZip());
            addressId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
            address.setAddressId(addressId);
            location.setAddress(address);
        }
        //ensure that this location name is unique by checking if location id already exists for that name.

        int locationId = 0;

        try {
            locationId = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_ID_BY_LOCATION_NAME, Integer.class, location.getLocationName());
        } catch (EmptyResultDataAccessException e) {

            jdbcTemplate.update(SQL_INSERT_LOCATION,
                    location.getLocationName(),
                    location.getAddress().getAddressId(),
                    location.getLongitude(),
                    location.getLatitude(),
                    location.getDescription());

            locationId = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_ID_BY_LOCATION_NAME, Integer.class, location.getLocationName());
            location.setLocationId(locationId);
            return location;
        }
        return null;

    }

    @Override
    public void deleteLocation(int locationId) {
        int addressId = jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS_ID_BY_LOCATION_ID, Integer.class, locationId);
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
    }

    @Override
    public void editLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getLocationName(),
                location.getAddress().getAddressId(),
                location.getLongitude(),
                location.getLatitude(),
                location.getDescription(),
                location.getLocationId());

    }

    @Override
    public Location getLocation(int locationId) {
        try {
            Address address = jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS_BY_LOCATION_ID, new AddressMapper(), locationId);
            Location location = jdbcTemplate.queryForObject(SQL_SELECT_LOCATION, new LocationMapper(), locationId);
            location.setAddress(address);
            return location;

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        List<Location> locations = jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
        for (Location location : locations) {
            Address address = jdbcTemplate.queryForObject(SQL_SELECT_ADDRESS_BY_LOCATION_ID, new AddressMapper(), location.getLocationId());
            location.setAddress(address);
        }
        return locations;
    }

    //PICTURES------------------------------------------------------------------
    @Override
    public Picture addPicture(Picture picture) {
        jdbcTemplate.update(SQL_INSERT_PICTURE,
                picture.getFilename());
        int pictureId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        picture.setPictureId(pictureId);
        return picture;
    }

    @Override
    public void deletePicture(int pictureId) {
        jdbcTemplate.update(SQL_DELETE_PICTURE, pictureId);
    }

    @Override
    public Picture getPictureById(int pictureId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_PICTURE, new pictureMapper(), pictureId);
        }  catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Picture> getAllPictures() {
        List<Picture> pictureList = jdbcTemplate.query(SQL_SELECT_ALL_PICTURES, new pictureMapper());
        return pictureList;
    }

    @Override
    public Picture getPictureBySuperheroId(int superheroId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_PICTURE_BY_SUPERHERO_ID, new pictureMapper(), superheroId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    //MAPPERS-------------------------------------------------------------------
    private static final class SuperheroMapper implements RowMapper<Superhero> {

        @Override
        public Superhero mapRow(ResultSet rs, int i) throws SQLException {
            Superhero hero = new Superhero();
            hero.setSuperheroId(rs.getInt("superhero_id"));
            hero.setName(rs.getString("superhero_name"));
            hero.setDescription(rs.getString("superhero_desc"));
            hero.setType(rs.getString("heroType"));

            
            return hero;
        }
    }

    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setOrganizationId(rs.getInt("organization_id"));
            org.setName(rs.getString("organization_name"));
            org.setDescription(rs.getString("organization_desc"));
            org.setPhone(rs.getString("phone"));
            return org;
        }
    }

    private static final class AddressMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet rs, int i) throws SQLException {
            Address ad = new Address();
            ad.setAddressId(rs.getInt("address_id"));
            ad.setStreet(rs.getString("street"));
            ad.setCity(rs.getString("city"));
            ad.setState(rs.getString("state"));
            ad.setZip(rs.getString("zip"));
            return ad;
        }
    }

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location location = new Location();
            location.setLocationId(rs.getInt("location_id"));
            location.setLocationName(rs.getString("name"));
            location.setLongitude(rs.getString("longitude"));
            location.setLatitude(rs.getString("latitude"));
            location.setDescription(rs.getString("location_desc"));
            return location;
        }

    }

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getInt("sighting_id"));
            sighting.setDate(rs.getDate("date").toLocalDate());
            return sighting;
        }

    }

    private static final class PowerMapper implements RowMapper<Superpower> {

        @Override
        public Superpower mapRow(ResultSet rs, int i) throws SQLException {
            Superpower power = new Superpower();
            power.setSuperpowerId(rs.getInt("superpower_id"));
            power.setSuperpowerName(rs.getString("superpower_type"));
            power.setSuperpowerDesc(rs.getString("superpower_desc"));
            return power;
        }
    }
    
    private static final class pictureMapper implements RowMapper<Picture> {

        @Override
        public Picture mapRow(ResultSet rs, int i) throws SQLException {
            Picture picture = new Picture();
            picture.setPictureId(rs.getInt("picture_id"));
            picture.setFilename(rs.getString("filename"));
            return picture;
        }
        
    }

    //HELPER METHODS 
    private void insertHeroesOrganizations(Superhero hero) {
        final int heroId = hero.getSuperheroId();
        final List<Organization> orgs = hero.getOrganizations();
        for (Organization org : orgs) {
            jdbcTemplate.update(SQL_INSERT_HERO_ORGANIZATION, heroId, org.getOrganizationId());

        }
    }

    private List<Organization> findOrganizationsForHero(Superhero hero) {
        List<Organization> orgs = jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_BY_SUPERHERO_ID, new OrganizationMapper(), hero.getSuperheroId());
        //for each organization add address 
        for (Organization currentOrg : orgs) {
            Address ad = jdbcTemplate.queryForObject(SQL_SElECT_ADDRESS_BY_ORGANIZATION_ID, new AddressMapper(), currentOrg.getOrganizationId());
            currentOrg.setAddress(ad);
        }
        return orgs;
    }

    private List<Superhero> associateOrganizationAndPowerWithHeroes(List<Superhero> heroes) {
        for (Superhero currentHero : heroes) {
            currentHero.setOrganizations(findOrganizationsForHero(currentHero));
            currentHero.setSuperpowers(findPowersForHero(currentHero));
            Picture picture = getPictureBySuperheroId(currentHero.getSuperheroId());
            currentHero.setPicture(picture);
        }
        return heroes;
    }

    private void insertHeroesPowers(Superhero hero) {
        final int heroId = hero.getSuperheroId();
        final List<Superpower> powers = hero.getSuperpowers();
        for (Superpower power : powers) {
            jdbcTemplate.update(SQL_INSERT_SUPERHERO_POWER, heroId, power.getSuperpowerId());
        }
    }

    private List<Superpower> findPowersForHero(Superhero hero) {
        List<Superpower> powers = jdbcTemplate.query(SQL_SELECT_SUPERPOWERS_BY_SUPERHERO_ID, new PowerMapper(), hero.getSuperheroId());
        return powers;
    }

    private void insertHeroesSightings(Sighting sighting) {
        final int sightingId = sighting.getSightingId();
        final List<Superhero> heroes = sighting.getSuperheroes();
        for (Superhero hero : heroes) {
            jdbcTemplate.update(SQL_INSERT_HERO_SIGHTING, hero.getSuperheroId(), sightingId);
        }
    }

    private List<Superhero> findHeroesForSightings(Sighting sighting) {
        List<Superhero> heroes = jdbcTemplate.query(SQL_SELECT_HEROES_BY_SIGHTING_ID, new SuperheroMapper(), sighting.getSightingId());
        associateOrganizationAndPowerWithHeroes(heroes);
        return heroes;
    }

}
