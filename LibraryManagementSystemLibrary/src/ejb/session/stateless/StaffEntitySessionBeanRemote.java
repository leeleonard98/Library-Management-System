/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.StaffEntity;
import exception.InvalidLoginException;
import exception.StaffNotFoundException;
import javax.ejb.Remote;

/**
 *
 * @author leele
 */
@Remote
public interface StaffEntitySessionBeanRemote {
     public StaffEntity staffLogin(String username, String password) throws InvalidLoginException;
      public StaffEntity retrieveStaffByUsername(String username) throws StaffNotFoundException;
      public Long createNewStaff(StaffEntity newStaffEntity);
}
