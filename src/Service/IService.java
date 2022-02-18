/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;

/**
 *
 * @author Ghiloufi
 * @param <T>
 */
public interface IService<T> {
    
    public void Create(T t);
    public void Update(T t);
    public void Delete(int id);
    public List<T> Read();
    
}   