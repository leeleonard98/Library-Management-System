/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.MemberEntity;
import exception.MemberNotFoundException;
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
public class MemberEntitySessionBean implements MemberEntitySessionBeanRemote, MemberEntitySessionBeanLocal {

    @PersistenceContext(unitName = "LibraryManagementSystem-ejbPU")
    private EntityManager em;

    public MemberEntitySessionBean() {
    }
    
    
    public Long createNewMember(MemberEntity newMemberEntity) {
        em.persist(newMemberEntity);
        em.flush();
        
        return newMemberEntity.getMemberId();
    }
    
    @Override
    public MemberEntity retrieveMemberByMemberId(String memberId) throws MemberNotFoundException
    {
       Query query = em.createQuery("SELECT s FROM MemberEntity s WHERE s.identityNo = :inIdentityno");
            query.setParameter("inIdentityno", memberId);
            
        try {   
        
             return (MemberEntity) query.getSingleResult();
        }
       
        catch (NoResultException ex) {
        
            throw new MemberNotFoundException("Member ID " + memberId + " does not exist!\n");
        }
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
