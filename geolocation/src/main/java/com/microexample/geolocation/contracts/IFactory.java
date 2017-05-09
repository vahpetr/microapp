package com.microexample.geolocation.contracts;

// TODO move to common code project

/**
 * Generic factory interface
 */
public interface IFactory<T> {
    /**
     * Create class
     * @return new instance of class
     */
    T create();
}
