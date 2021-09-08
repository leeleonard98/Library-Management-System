/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.LendAndReturnEntity;
import exception.LendingNotFoundException;
import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author leele
 */
@Remote
public interface LendAndReturnSessionBeanRemote {
    public Boolean retrieveReturnDate(Long bookId);

    public Long createNewLendAndReturn(LendAndReturnEntity newLendAndReturnEntity);

    public LendAndReturnEntity retrieveLendingDate(Long bookId)  throws LendingNotFoundException;
    
    public void update(LendAndReturnEntity lendAndReturnEntity);
}
