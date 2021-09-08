/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystemclient;

import ejb.session.stateless.LendAndReturnSessionBeanRemote;
import entity.LendAndReturnEntity;
import exception.LendingNotFoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import javafx.util.converter.LocalDateTimeStringConverter;
import javax.persistence.NoResultException;

/**
 *
 * @author leele
 */
class ViewFineAmountModule {
    private LendAndReturnSessionBeanRemote lendAndReturnSessionBeanRemote;

    public ViewFineAmountModule() {
    }

    public ViewFineAmountModule(LendAndReturnSessionBeanRemote lendAndReturnSessionBeanRemote) {
        this.lendAndReturnSessionBeanRemote = lendAndReturnSessionBeanRemote;
    }

    void apply() {
        Scanner sc = new Scanner(System.in);
                
        while(true)
        {
            System.out.println("*** LMS :: Library Operaton :: View fine amount ***\n");
            Date date = new Date();
            System.out.print("Enter Lend Id> ");
            Long lendId = sc.nextLong();
            sc.nextLine().trim();
            double fine =0.0;
            try {
            LendAndReturnEntity currentLend = lendAndReturnSessionBeanRemote.retrieveLendingDate(lendId);
            long difference_In_Time = date.getTime() - currentLend.getLendDate().getTime();
            long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365; 
            if(difference_In_Days >14){
            fine = 0.5 * (difference_In_Days -14);
            }
            System.out.println(currentLend.getMember().getFirstName() +" "+ currentLend.getMember().getLastName() + " is fined with " +  fine + " $.\n");
            break;
    } catch (LendingNotFoundException ex ) {
                System.out.println(ex);
                break;
    } catch (NoResultException ex) {
            System.out.println("Return does not exist.\n");
            break;
    } catch (NullPointerException ex) {
                System.out.println("Lend Id does not exist\n");
                break;
    } catch (InputMismatchException ex) {
                System.out.println("Wrong input!\n");
                break;
            }
    
    
        }
    }
}
