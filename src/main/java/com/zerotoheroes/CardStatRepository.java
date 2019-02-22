package com.zerotoheroes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardStatRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public CardStatRepository(SecretManager secretManager) {
        JSONObject rds = secretManager.getRdsConnectionInfo();
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(com.zerotoheroes.CardStat.class);
        configuration.setProperty("connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://" + rds.getString("host") + ":" + rds.getInt("port") + "/replay_summary");
        configuration.setProperty("hibernate.connection.username", rds.getString("username"));
        configuration.setProperty("hibernate.connection.password", rds.getString("password"));

        configuration.setProperty("show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.connection.CharSet", "utf8");
        configuration.setProperty("hibernate.connection.characterEncoding", "utf8");
        configuration.setProperty("hibernate.connection.useUnicode", "true");
        configuration.setProperty("connection.pool_size", "5");
        configuration.setProperty("hibernate.connection.pool_size", "5");

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    public void saveCardStat(CardStat cardStat) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(cardStat);
        session.getTransaction().commit();
        session.close();
    }
}
