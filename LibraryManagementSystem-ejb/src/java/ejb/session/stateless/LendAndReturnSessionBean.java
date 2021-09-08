/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.BookEntity;
import entity.LendAndReturnEntity;
import exception.LendingNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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
public class LendAndReturnSessionBean implements LendAndReturnSessionBeanRemote, LendAndReturnSessionBeanLocal {

    @PersistenceContext(unitName = "LibraryManagementSystem-ejbPU")
    private EntityManager em;

    
    public LendAndReturnSessionBean() {
    }
    
  
    @Override
    public Boolean retrieveReturnDate(Long bookId){
        try{
       
            Query query = em.createQuery("SELECT s from LendAndReturnEntity s WHERE s.bookId = :inBookId");
            query.setParameter("inBookId",bookId);
            List<LendAndReturnEntity> list = query.getResultList();
            if(list.size()>0) {
            LendAndReturnEntity lendAndReturnEntity = list.get(list.size()-1);
                if(lendAndReturnEntity.getReturnDate()!=null ) { 
                   
                    return true;
                }
                 else {
                    return false;
        }
            } else{
                return true;
            }
        
    
    } catch (NoResultException ex) {
            System.out.println("Return does not exist.\n");
    } return null;
    }
    
    @Override
    public Long createNewLendAndReturn(LendAndReturnEntity newLendAndReturnEntity) {
        em.persist(newLendAndReturnEntity);
        em.flush();
        
        return newLendAndReturnEntity.getLendId();
    }
    
    
    @Override
    public LendAndReturnEntity retrieveLendingDate(Long lendId) throws LendingNotFoundException{
        try {
        Query query = em.createQuery("SELECT s from LendAndReturnEntity s WHERE s.lendId = :inLendId");
        query.setParameter("inLendId",lendId);
        LendAndReturnEntity lendAndReturnEntity = (LendAndReturnEntity)query.getSingleResult();
        
        if(lendAndReturnEntity !=null && lendAndReturnEntity.getReturnDate() == null) {
            return lendAndReturnEntity;
        } else {
            throw new LendingNotFoundException("Book is not lent.\n");
        }
        
    }
        catch (NoResultException ex) {
            System.out.println("Return does not exist.\n");
    } return null;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

     @Override
    public void update(LendAndReturnEntity lendAndReturnEntity)
    {
        em.merge(lendAndReturnEntity);
    }
    
}
