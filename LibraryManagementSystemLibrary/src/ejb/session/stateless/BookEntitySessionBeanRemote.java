/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.BookEntity;
import exception.BookNotFoundException;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author leele
 */
@Remote
public interface BookEntitySessionBeanRemote {
    public List<BookEntity> retrieveAllBooks();
    public Long createNewBook(BookEntity newBookEntity);
    public BookEntity retrieveBookByBookId(Long staffId) throws BookNotFoundException;
}
