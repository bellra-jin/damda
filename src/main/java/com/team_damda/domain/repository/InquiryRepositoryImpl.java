package com.team_damda.domain.repository;

import com.team_damda.domain.entity.Inquiry;
import com.team_damda.domain.enums.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TemporalType;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class InquiryRepositoryImpl implements InquiryRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Inquiry> searchInquiryByEmail(String classify, String user, String searchContent, Date startDay, Date endDay) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        String searchSql = "SELECT i FROM Inquiry i WHERE 1=1";

        if (classify != null && !classify.isEmpty()) {
            searchSql += " AND i.type = :classify";
        }

        if (user != null && !user.isEmpty()) {
            searchSql += " AND i.user_role = :user";
        }
        /*
        if (startDay != null && endDay != null) {
            searchSql += " AND i.createdAt BETWEEN :startDay AND :endDay";
        }
         */

        if (searchContent != null && !searchContent.isEmpty()) {
            searchSql += " AND i.userEmail LIKE :searchContent";
        }

        TypedQuery<Inquiry> query = em.createQuery(searchSql, Inquiry.class);

        if (classify != null && !classify.isEmpty()) {
            query.setParameter("classify", classify);
        }

        if (user != null && !user.isEmpty()) {
            query.setParameter("user", user);
        }

        /*
        if (startDay != null && endDay != null) {
            query.setParameter("startDay", startDay, TemporalType.TIMESTAMP);
            query.setParameter("endDay", endDay, TemporalType.TIMESTAMP);
        }
        */

        if (searchContent != null && !searchContent.isEmpty()) {
            query.setParameter("searchContent", "%" + searchContent + "%");
        }

        return query.getResultList();
    }

    @Override
    public List<Inquiry> searchAllInquiryByEmail(String classify, String searchContent, Date startDay, Date endDay) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startDayString = sdf.format(startDay);
        String endDayString = sdf.format(endDay);

        String searchSql = "SELECT i FROM Inquiry i WHERE 1=1";

        if (classify != null && !classify.isEmpty()) {
            searchSql += " AND i.type = :classify";
        }

        if (searchContent != null && !searchContent.isEmpty()) {
            searchSql += " AND i.userEmail LIKE :searchContent";
        }

        TypedQuery<Inquiry> query = em.createQuery(searchSql, Inquiry.class);

        if (classify != null && !classify.isEmpty()) {
            query.setParameter("classify", classify);
        }

        if (searchContent != null && !searchContent.isEmpty()) {
            query.setParameter("searchContent", "%" + searchContent + "%");
        }

        return query.getResultList();
    }

    @Override
    public List<Inquiry> searchInquiryByTitle(String classify, String user, String searchContent, Date startDay, Date endDay) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startDayString = sdf.format(startDay);
        String endDayString = sdf.format(endDay);

        String searchSql = "SELECT i FROM Inquiry i WHERE 1=1";

        if (classify != null && !classify.isEmpty()) {
            searchSql += " AND i.type = :classify";
        }

        if (user != null && !user.isEmpty()) {
            searchSql += " AND i.user_role = :user";
        }

        if (searchContent != null && !searchContent.isEmpty()) {
            searchSql += " AND i.title LIKE :searchContent";
        }

        TypedQuery<Inquiry> query = em.createQuery(searchSql, Inquiry.class);

        if (classify != null && !classify.isEmpty()) {
            query.setParameter("classify", classify);
        }

        if (user != null && !user.isEmpty()) {
            query.setParameter("user", user);
        }

        if (searchContent != null && !searchContent.isEmpty()) {
            query.setParameter("searchContent", "%" + searchContent + "%");
        }

        return query.getResultList();
    }

    @Override
    public List<Inquiry> searchAllInquiryByTitle(String classify, String searchContent, Date startDay, Date endDay) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startDayString = sdf.format(startDay);
        String endDayString = sdf.format(endDay);

        String searchSql = "SELECT i FROM Inquiry i WHERE 1=1";

        if (classify != null && !classify.isEmpty()) {
            searchSql += " AND i.type = :classify";
        }

        if (searchContent != null && !searchContent.isEmpty()) {
            searchSql += " AND i.title LIKE :searchContent";
        }

        TypedQuery<Inquiry> query = em.createQuery(searchSql, Inquiry.class);

        if (classify != null && !classify.isEmpty()) {
            query.setParameter("classify", classify);
        }

        if (searchContent != null && !searchContent.isEmpty()) {
            query.setParameter("searchContent", "%" + searchContent + "%");
        }

        return query.getResultList();
    }


}
