/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.StaffEntity;
import exception.InvalidLoginException;
import exception.StaffNotFoundException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author leele
 */
@Stateless
public class StaffEntitySessionBean implements StaffEntitySessionBeanRemote, StaffEntitySessionBeanLocal {

    @PersistenceContext(unitName = "LibraryManagementSystem-ejbPU")
    private EntityManager em;

    public StaffEntitySessionBean() {
    }

    public Long createNewStaff(StaffEntity newStaffEntity) {
        em.persist(newStaffEntity);
        em.flush();

        return newStaffEntity.getStaffId();
    }

    @Override
    public StaffEntity retrieveStaffByUsername(String username) throws StaffNotFoundException{
        try {
            Query query = em.createQuery("SELECT s FROM StaffEntity s WHERE s.userName = :inUsername");
            query.setParameter("inUsername", username);

            return (StaffEntity) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new StaffNotFoundException("Staff Username " + username + " does not exist!");
        }
       
    }

    @Override
    public StaffEntity staffLogin(String username, String password) throws InvalidLoginException {
        try
        {
            StaffEntity staffEntity = retrieveStaffByUsername(username);

            if (staffEntity.getPassword().equals(password)) {
                // staffEntity.getSaleTransactionEntities().size();                
                return staffEntity;
            } else {
                throw new InvalidLoginException("Username does not exist or invalid password!");
            }
        } catch (StaffNotFoundException ex) {
            throw new InvalidLoginException("Username does not exist or invalid password!");
        }

    }
}

// Add business logic below. (Right-click in editor and choose
// "Insert Code > Add Business Method")

