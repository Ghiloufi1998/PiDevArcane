/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author bensa
 */
public interface IService <T>{
    void insert (T t) ; 
    List<T> readAll() ;  
    void update (T t) ; 
    void delete (int t) ; 
}
