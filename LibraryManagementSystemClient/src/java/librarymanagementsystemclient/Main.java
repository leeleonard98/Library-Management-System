/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagementsystemclient;

import ejb.session.stateless.BookEntitySessionBeanRemote;
import ejb.session.stateless.LendAndReturnSessionBeanRemote;
import ejb.session.stateless.MemberEntitySessionBeanRemote;
import ejb.session.stateless.StaffEntitySessionBeanRemote;
import entity.StaffEntity;
import exception.EntityManagerException;
import exception.InvalidLoginException;
import java.util.List;
import java.util.Scanner;
import javax.ejb.EJB;

/**
 *
 * @author leele
 */
public class Main {

    @EJB(name = "LendAndReturnSessionBeanRemote")
    private static LendAndReturnSessionBeanRemote lendAndReturnSessionBeanRemote;

    @EJB(name = "BookEntitySessionBeanRemote")
    private static BookEntitySessionBeanRemote bookEntitySessionBeanRemote;

    @EJB(name = "MemberEntitySessionBeanRemote")
    private static MemberEntitySessionBeanRemote memberEntitySessionBeanRemote;

    @EJB(name = "StaffEntitySessionBeanRemote")
    private static StaffEntitySessionBeanRemote staffEntitySessionBeanRemote;

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        Integer response =0;
        
        
        while(true) {
        System.out.println("*** Welcome to Library Management System (LMS) ***\n");
        System.out.println("1: Login");
        System.out.println("2: Logout\n");
        response = 0;
        
        
        while(response<1 || response >2) {
            System.out.print("> ");
            response = sc.nextInt();
            
            if(response == 1) {
                try {
                    StaffEntity currentStaff = doLogin();
                    RegisterMemberModule regMember = new RegisterMemberModule(memberEntitySessionBeanRemote);
                    LendBookModule lendBook = new LendBookModule(lendAndReturnSessionBeanRemote,bookEntitySessionBeanRemote,memberEntitySessionBeanRemote);
                    ViewFineAmountModule viewFine = new ViewFineAmountModule(lendAndReturnSessionBeanRemote);
                    ReturnBookModule returnBook = new ReturnBookModule(memberEntitySessionBeanRemote,lendAndReturnSessionBeanRemote,bookEntitySessionBeanRemote);
                    System.out.println("Login successful!\n");
                    libraryOperation(currentStaff, regMember, lendBook, viewFine, returnBook);
              
                    
                } catch (InvalidLoginException | EntityManagerException ex) {
                    System.out.println("Invalid login credential: " + ex.getMessage() + "\n");
                }
            } else {
                break;
            }
            
            
        } if(response ==2) {
            break;
        }
        }
    }
    
    
        private static void libraryOperation (StaffEntity currentStaff,RegisterMemberModule regMember, LendBookModule lendBook, ViewFineAmountModule viewFine, ReturnBookModule returnBook) {
            Scanner sc = new Scanner(System.in);
            Integer response =0;
            
            while(true) {
            System.out.println("*** LMS :: Library Operation ***\n");
            System.out.println("You are login as " + currentStaff.getFirstName() + " " + currentStaff.getLastName() +"\n");
            System.out.println("1: Library Operation");
            System.out.println("2: Logout\n");
            response =0;
            
            while(response<1 || response > 2) {
            System.out.print("> ");
            response = sc.nextInt();
            if (response == 1) {
                mainMenu(regMember,lendBook,viewFine, returnBook);
            }
            }
            }
            
        }
    
        
        private static StaffEntity doLogin() throws InvalidLoginException, EntityManagerException
    {
        Scanner scanner = new Scanner(System.in);
        String username = "";
        String password = "";
        
        System.out.println("*** POS System :: Login ***\n");
        System.out.print("Enter username> ");
        username = scanner.nextLine().trim();
        System.out.print("Enter password> ");
        password = scanner.nextLine().trim();
        
        if(username.length() > 0 && password.length() > 0)
        {
           return staffEntitySessionBeanRemote.staffLogin(username, password);      
        }
        else
        {
            throw new InvalidLoginException("Missing login credential!\n");
        }
    }

    private static void mainMenu(RegisterMemberModule regMember, LendBookModule lendBook, ViewFineAmountModule viewFine, ReturnBookModule returnBook) {
        Scanner sc = new Scanner(System.in);
        Integer response;
        
        while(true) {
            
            System.out.println("*** LMS :: Library Operation ***\n");
            System.out.println("1: Register Member");
            System.out.println("2: Lend Book");
            System.out.println("3: View fine amount");
            System.out.println("4: Return Book");
            System.out.println("5: Back\n");
            response =0;
            
            while(response <1 || response > 5) {
                System.out.print(">");
                response = sc.nextInt();
                
                if(response == 1) {
                    regMember.apply();
                } else if(response == 2) {
                    lendBook.apply();
                } else if(response ==3) {
                    viewFine.apply();
                } else if(response ==4) {
                    returnBook.apply();
                } else if(response ==5){
                    break;
                } else {
                    System.out.println("Invalid option, please try again!\n");
                }
            }
            if(response ==5) {
            break;
           
        }
        
        }
    }
        
        
        
      
    }

    

