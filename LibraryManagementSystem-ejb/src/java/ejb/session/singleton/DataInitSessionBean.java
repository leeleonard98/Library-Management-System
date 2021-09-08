/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.singleton;

import ejb.session.stateless.BookEntitySessionBeanLocal;
import ejb.session.stateless.MemberEntitySessionBeanLocal;
import ejb.session.stateless.StaffEntitySessionBeanLocal;
import ejb.session.stateless.StaffEntitySessionBeanRemote;

import entity.BookEntity;
import entity.MemberEntity;
import entity.StaffEntity;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author leele
 */
@Singleton
@LocalBean
@Startup

public class DataInitSessionBean {

    @EJB(name = "MemberEntitySessionBeanLocal")
    private MemberEntitySessionBeanLocal memberEntitySessionBeanLocal;

    @EJB(name = "BookEntitySessionBeanLocal")
    private BookEntitySessionBeanLocal bookEntitySessionBeanLocal;

    @PersistenceContext(unitName = "LibraryManagementSystem-ejbPU")
    private EntityManager em;

    @EJB(name = "StaffEntitySessionBeanLocal")
    private StaffEntitySessionBeanLocal staffEntitySessionBeanLocal;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PostConstruct
    public void postConstruct() {

        if (em.find(BookEntity.class, 1L) == null) {
            bookEntitySessionBeanLocal.createNewBook(new BookEntity("Anna Karenina", "0451528611", "Leo Tolstoy"));
            bookEntitySessionBeanLocal.createNewBook(new BookEntity("Madame Bovary", "979-8649042031", "Gustave Flaubert"));
            bookEntitySessionBeanLocal.createNewBook(new BookEntity("Hamlet", "1980625026", "William Shakespeare"));
            bookEntitySessionBeanLocal.createNewBook(new BookEntity("The Hobbit", "9780007458424", "J R R Tolkien"));
            bookEntitySessionBeanLocal.createNewBook(new BookEntity("Great Expectations", "1521853592", "Charles Dickens"));
            bookEntitySessionBeanLocal.createNewBook(new BookEntity("Pride and Prejudice", "979-8653642272", "Jane Austen"));
            bookEntitySessionBeanLocal.createNewBook(new BookEntity("Wuthering Heights", "3961300224", "Emily Brontë"));
        }

        if (em.find(StaffEntity.class, 1L) == null) {
            staffEntitySessionBeanLocal.createNewStaff(new StaffEntity("Eric", "Some", "eric", "password"));
            staffEntitySessionBeanLocal.createNewStaff(new StaffEntity("Sarah", "Brightman", "sarah", "password"));
        }

        if (em.find(MemberEntity.class, 1L) == null) {
            memberEntitySessionBeanLocal.createNewMember(new MemberEntity("Tony", "Shade", 'M', 31, "S8900678A", "83722773", "13 Jurong East, Ave 3"));
            memberEntitySessionBeanLocal.createNewMember(new MemberEntity("Dewi", "Tan", 'F', 35, "S8581028X", "94602711", "15 Computing Dr"));
        }

    }

}
