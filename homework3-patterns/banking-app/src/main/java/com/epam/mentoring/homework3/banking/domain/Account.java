package com.epam.mentoring.homework3.banking.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Account domain object.
 * <p/>
 * Date: 09.03.2017
 *
 * @author Raman Kuchynski
 */
public class Account {

    private String accountId;
    private String userId;
    private double amount;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return new EqualsBuilder()
                .append(amount, account.amount)
                .append(accountId, account.accountId)
                .append(userId, account.userId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(accountId)
                .append(userId)
                .append(amount)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("accountId", accountId)
                .append("userId", userId)
                .append("amount", amount)
                .toString();
    }
}
