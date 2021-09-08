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
import exception.BookNotFoundException;
import exception.LendingNotFoundException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 *
 * @author leele
 */
class ReturnBookModule {
    private MemberEntitySessionBeanRemote memberEntitySessionBeanRemote;
    private LendAndReturnSessionBeanRemote lendAndReturnSessionBeanRemote;
    private BookEntitySessionBeanRemote bookEntitySessionBeanRemote;
    
    public ReturnBookModule() {
    }

    public ReturnBookModule(MemberEntitySessionBeanRemote memberEntitySessionBeanRemote, LendAndReturnSessionBeanRemote lendAndReturnSessionBeanRemote,BookEntitySessionBeanRemote bookEntitySessionBeanRemote) {
        this.memberEntitySessionBeanRemote = memberEntitySessionBeanRemote;
        this.lendAndReturnSessionBeanRemote = lendAndReturnSessionBeanRemote;
        this.bookEntitySessionBeanRemote = bookEntitySessionBeanRemote;
    }

    void apply() {
         Scanner sc = new Scanner(System.in);
                
        while(true)
        {
            System.out.println("*** LMS :: Library Operaton :: Return Book ***\n");
            Date date = new Date();
            System.out.print("Enter Lend Id> ");
            Long lendId = sc.nextLong();
            sc.nextLine().trim();
            System.out.print("Enter fine amount paid> ");
            Long fineAmt = sc.nextLong();
            sc.nextLine().trim();
            
            try {
            LendAndReturnEntity currentLend = lendAndReturnSessionBeanRemote.retrieveLendingDate(lendId);
            Long bookId = currentLend.getBookId();
            BookEntity currentBook = bookEntitySessionBeanRemote.retrieveBookByBookId(bookId);
            long difference_In_Time = date.getTime() - currentLend.getLendDate().getTime();
            long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365; 
            double fine = 0.0;
            if(difference_In_Days >14){
            fine = 0.5 * (difference_In_Days -14);
            }
            if(fineAmt == fine) {
            System.out.println(currentBook.getTitle()+ " is successfully returned by " + currentLend.getMember().getFirstName() + " "+ currentLend.getMember().getLastName() + ".\n");
            currentLend.setReturnDate(date);
            lendAndReturnSessionBeanRemote.update(currentLend);
        
            } else {
                System.out.println("Wrong fine amount.\n");
            }
            
            break;
    } catch (LendingNotFoundException ex) {
                System.out.println(ex);
                break;
    } catch(BookNotFoundException ex) {
                System.out.println(ex);
                break;
    } catch (NullPointerException ex) {
                System.out.println("Lend Id does not exist!\n");
                break;
    }catch (InputMismatchException ex) {
                System.out.println("Wrong input!\n");
                break;
            }
    
    
        }
    }
    }
    
    

