package com.prior.newbie.model;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

public class ImeiIdGenerator implements IdentifierGenerator{
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object o) throws HibernateException {
        return UUID.randomUUID().toString(); // generate UUID
    }
}
