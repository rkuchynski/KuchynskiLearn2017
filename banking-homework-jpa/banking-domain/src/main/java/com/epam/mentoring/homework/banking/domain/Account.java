package com.epam.mentoring.homework.banking.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Account domain object.
 * <p/>
 * Date: 09.03.2017
 *
 * @author Raman Kuchynski
 */
@Entity(name = "account")
@Table(name = "ba_account", schema = "ba_schema")
public class Account implements Serializable {

    private static final long serialVersionUID = -5421041812268681070L;

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, updatable = false, insertable = false)
    private User user;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "amount", nullable = false)
    private double amount;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountIdValue) {
        this.accountId = accountIdValue;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double value) {
        this.amount = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userValue) {
        this.user = userValue;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userIdValue) {
        this.userId = userIdValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        return new EqualsBuilder()
                .append(amount, account.amount)
                .append(accountId, account.accountId)
                .append(user, account.user)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(accountId)
                .append(amount)
                .append(user)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("accountId", accountId)
                .append("amount", amount)
                .append("user", user)
                .toString();
    }
}
