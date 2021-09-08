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
import entity.MemberEntity;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author leele
 */
public class RegisterMemberModule {
    
 
    private MemberEntitySessionBeanRemote memberEntitySessionBeanRemote;
  
    
    
    public RegisterMemberModule() {
    }

    public RegisterMemberModule(MemberEntitySessionBeanRemote memberEntitySessionBeanRemote) {
        this.memberEntitySessionBeanRemote = memberEntitySessionBeanRemote;
    }
    
    public void apply(){
        Scanner sc = new Scanner(System.in);
        
        while(true)
        {
            try{
            System.out.println("*** LMS :: Library Operaton :: Register Member ***\n");
            System.out.print("Enter First Name> ");
            String firstName = sc.nextLine().trim();
            System.out.print("Enter Last Name> ");
            String lastName = sc.nextLine().trim();
            System.out.print("Enter Gender> ");
            Character gender = sc.next().charAt(0);
            System.out.print("Enter Age> ");
            Integer age = sc.nextInt();
            sc.nextLine().trim();
            System.out.print("Enter Identity Number> ");
            String identityNum = sc.nextLine().trim();            
            System.out.print("Enter Phone> ");
            String phoneNum = sc.nextLine().trim();
            System.out.print("Enter Address> ");
            String address = sc.nextLine().trim();
            
            MemberEntity newMemberEntity = new MemberEntity();
            newMemberEntity.setFirstName(firstName);
            newMemberEntity.setLastName(lastName);
            newMemberEntity.setGender(gender);
            newMemberEntity.setAge(age);
            newMemberEntity.setIdentityNo(identityNum);
            newMemberEntity.setPhone(phoneNum);
            newMemberEntity.setAddress(address);
            
            memberEntitySessionBeanRemote.createNewMember(newMemberEntity);
            System.out.println("Member has been registered successfully!\n");
            break;
            } catch(InputMismatchException ex) {
                System.out.println("Wrong input!\n");
                break;
            }
        }
    }
}
    
    

    
    
    
    

