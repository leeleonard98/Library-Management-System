/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author leele
 */
@Entity
public class LendAndReturnEntity implements Serializable {
 private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lendId;
    private Long memberId;
    private Long bookId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lendDate;
    @Temporal(TemporalType.TIMESTAMP)
    
    private Date returnDate;
    private BigDecimal fineAmount;
    
    @ManyToOne (optional = false)
    @JoinColumn (nullable = false)
    private MemberEntity member;
    
    @ManyToOne (optional = false)
    @JoinColumn (nullable = false)
    private BookEntity book;
    
    public LendAndReturnEntity() {
        
    }

    public LendAndReturnEntity(Long memberId, Long bookId, Date lendDate, Date returnDate, BigDecimal fineAmount,MemberEntity member, BookEntity book) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.lendDate = lendDate;
        this.returnDate = returnDate;
        this.fineAmount = fineAmount;
        this.member = member;
        this.book=book;
    }
    
    
    /**
     * @return the memberId
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * @return the bookId
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * @param bookId the bookId to set
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * @return the lendDate
     */
    public Date getLendDate() {
        return lendDate;
    }

    /**
     * @param lendDate the lendDate to set
     */
    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    /**
     * @return the returnDate
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

   
    

    public Long getLendId() {
        return lendId;
    }

    public void setLendId(Long lendId) {
        this.lendId = lendId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lendId != null ? lendId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the lendId fields are not set
        if (!(object instanceof LendAndReturnEntity)) {
            return false;
        }
        LendAndReturnEntity other = (LendAndReturnEntity) object;
        if ((this.lendId == null && other.lendId != null) || (this.lendId != null && !this.lendId.equals(other.lendId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LendAndReturn[ id=" + lendId + " ]";
    }

    /**
     * @return the member
     */
    public MemberEntity getMember() {
        return member;
    }

    /**
     * @param member the member to set
     */
    public void setMember(MemberEntity member) {
        this.member = member;
    }

    /**
     * @return the fineAmount
     */
    public BigDecimal getFineAmount() {
        return fineAmount;
    }

    /**
     * @param fineAmount the fineAmount to set
     */
    public void setFineAmount(BigDecimal fineAmount) {
        this.fineAmount = fineAmount;
    }
    
}
