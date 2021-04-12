package com.health.entity;

import com.health.entity.Topic;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-10T22:52:17")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SingularAttribute<Account, String> password;
    public static volatile CollectionAttribute<Account, Topic> topicCollection;
    public static volatile SingularAttribute<Account, Integer> id;
    public static volatile SingularAttribute<Account, String> username;

}