/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.MemberEntity;
import exception.MemberNotFoundException;
import javax.ejb.Local;

/**
 *
 * @author leele
 */
@Local
public interface MemberEntitySessionBeanLocal {

    public Long createNewMember(MemberEntity newMemberEntity);

    public MemberEntity retrieveMemberByMemberId(String memberId) throws MemberNotFoundException;
    
}
