/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystemclient;

import ejb.session.stateless.BookEntitySessionBeanRemote;
import ejb.session.stateless.LendAndReturnSessionBeanRemote;
import ejb.session.stateless.MemberEntitySessionBeanRemote;
import entity.BookEntity;
import entity.LendAndReturnEntity;
import entity.MemberEntity;
import exception.BookNotFoundException;
import exception.LendingNotFoundException;
import exception.MemberNotFoundException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author leele
 */
class LendBookModule {
    private LendAndReturnSessionBeanRemote lendAndReturnSessionBeanRemote;
    private BookEntitySessionBeanRemote bookEntitySessionBeanRemote;
    private MemberEntitySessionBeanRemote memberEntitySessionBeanRemote;

    public LendBookModule() {
    }

    public LendBookModule(LendAndReturnSessionBeanRemote lendAndReturnSessionBeanRemote, BookEntitySessionBeanRemote bookEntitySessionBeanRemote, MemberEntitySessionBeanRemote memberEntitySessionBeanRemote) {
        this.lendAndReturnSessionBeanRemote = lendAndReturnSessionBeanRemote;
        this.bookEntitySessionBeanRemote = bookEntitySessionBeanRemote;
        this.memberEntitySessionBeanRemote = memberEntitySessionBeanRemote;
    }

    public void apply() {
        Scanner sc = new Scanner(System.in);
                
        while(true)
        {
            System.out.println("*** LMS :: Library Operaton :: Lend Book ***\n");
            
            System.out.print("Enter Book Id> ");
            Long bookId = sc.nextLong();
            sc.nextLine().trim();
            System.out.print("Enter Member Identity Number> ");
            String memberId =  sc.nextLine().trim();
            
            try{
            BookEntity currentBook = bookEntitySessionBeanRemote.retrieveBookByBookId(bookId);
            MemberEntity memberEntity = memberEntitySessionBeanRemote.retrieveMemberByMemberId(memberId);
            
            Date timeStamp = new Date();
            Boolean check = lendAndReturnSessionBeanRemote.retrieveReturnDate(bookId);
            
            if(check == true) {   
                LendAndReturnEntity tempLend = new LendAndReturnEntity(memberEntity.getMemberId(), bookId,timeStamp,null,new BigDecimal(0),memberEntity,currentBook);
                lendAndReturnSessionBeanRemote.createNewLendAndReturn(tempLend);
                System.out.println("Book: " + currentBook.getTitle() + " lent to " + memberEntity.getFirstName() +" "+  memberEntity.getLastName() +".\n");
                break;
                } else {
                System.out.println("Book is lent out!\n");
                break;
            }
          
            
            
            } catch (BookNotFoundException ex){
                System.out.println(ex);
                break;
            } catch (MemberNotFoundException ex) {
                System.out.println(ex);
                break;
            } catch (InputMismatchException ex) {
                System.out.println("Wrong input!\n");
                break;
            }
    }
}
    
    
}
