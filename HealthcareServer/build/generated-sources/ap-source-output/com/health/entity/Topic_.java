package com.health.entity;

import com.health.entity.Account;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-10T22:52:17")
@StaticMetamodel(Topic.class)
public class Topic_ { 

    public static volatile SingularAttribute<Topic, Integer> id;
    public static volatile SingularAttribute<Topic, String> title;
    public static volatile SingularAttribute<Topic, String> body;
    public static volatile SingularAttribute<Topic, Account> authorId;

}