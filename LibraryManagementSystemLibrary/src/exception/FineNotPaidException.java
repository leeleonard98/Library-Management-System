/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author leele
 */
public class FineNotPaidException extends Exception{

    public FineNotPaidException() {
    }

    public FineNotPaidException(String string) {
        super(string);
    }
    
}
