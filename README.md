Pojot  
=====
Provides test coverage of methods such as basic getters and setters, equals and hashcode testing, and enums.

Features
--------
* Tests getters/setters using range of values as applicable for type: min, max, null.
* Tests equals and hashCode methods given a set of properties.  
* Tests toString method to ensure that default values do not raise an exception. 
* Provides code coverage for Enum methods provided by language: toString and valueOf

Assumptions
------------
* Getters 
    Methods being tested must not contain any special logic and must return the value for the property without modification.
* Setters 
    Methods being tested must not contain any special logic and must set the value for the property without modification.
* Equals/Hashcode 
    If being tested, each uses the same properties in calculating the values.

Usage
