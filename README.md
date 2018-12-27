# GroStore
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/6eb0ff0c92f64ea78e6d53727bda57b9)](https://app.codacy.com/app/sergeymitrichev/grostore?utm_source=github.com&utm_medium=referral&utm_content=sergeymitrichev/grostore&utm_campaign=Badge_Grade_Settings)
[![Build Status](https://travis-ci.org/sergeymitrichev/grostore.svg?branch=master)](https://travis-ci.org/sergeymitrichev/grostore)

GroStore application based on Spring: Boot, MVC, Data JPA, Flyway, Security (OAuth); Jackson; Vue; NuxtJs.

## v0.7
Update Spring versions

**Model** 
* Change DB model to autogenerated by Hibernate and copy it to Flyway db/migration
* Move up 'created', 'updated', 'createdBy', 'updatedBy' fields to AbstractNamedEntity
* Add Spring Data JPA auditing
* Add entity images to AbstractDescribedEntity 
* Add product modifications

**Test**
Change DB for tests to PostgreSQL.

## v0.6.0
**REST module**. Add described model for publishing articles and SEO items

**Frontend**
* Add basic auth
* Add simple main page, login and registration

**Security module**. Add JWT authentication and account roles

## v0.5.0
**REST module**
* Add price entities and price converters (income, sold, wholesale)
* Add price rules for calculating prices

**Scheduler module**. Change scheduler logic, add price parsing to scheduler. 

## v0.4.0
**REST module**
* All REST API calls should be authorized under ADMIN role
* Move configuration properties from classpath to external YAML file
* Add uCoz products synchronize controller

**Scheduler module**. Scheduler module now can persist images which was downloaded while parsing products

**Model module** 
* Add models validators
* Add Flyway DB migrations  
* Add product Stock entity, and ProductInStock with shelf, there the product stocked in

## v0.3.0
**Admin module**. Add scheduled tasks for product parsing

**Scheduler module**. Scheduler module parse products from remote sites of popular grocery retailers

**Security module**. Add Spring Securiity OAuth2. Now users can register with Google account

## v0.2.0
**Admin module**. Web application for managing products and categories

## v0.1.0
**Admin module**. Provide import of products and categories from .xls/.xlsx files.