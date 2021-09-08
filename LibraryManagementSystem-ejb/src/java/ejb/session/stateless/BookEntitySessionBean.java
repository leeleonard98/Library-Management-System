/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.BookEntity;
import exception.BookNotFoundException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author leele
 */
@Stateless
public class BookEntitySessionBean implements BookEntitySessionBeanRemote, BookEntitySessionBeanLocal {

    @PersistenceContext(unitName = "LibraryManagementSystem-ejbPU")
    private EntityManager em;

    public BookEntitySessionBean() {
    }
    
    
    @Override
    public List<BookEntity> retrieveAllBooks() {
        Query query = em.createQuery("SELECT r FROM RecordEntity r");
        return query.getResultList();
    }
    
    @Override
    public Long createNewBook(BookEntity newBookEntity) {
        em.persist(newBookEntity);
        em.flush();
        
        return newBookEntity.getBookId();
        
    }
    
    
    @Override
    public BookEntity retrieveBookByBookId(Long bookId) throws BookNotFoundException
    {
        BookEntity bookEntity = em.find(BookEntity.class, bookId);
        
        if(bookEntity != null)
        {
            return bookEntity;
        }
        else
        {
            throw new BookNotFoundException("Book ID " + bookId + " does not exist!\n");
        }
    }
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
