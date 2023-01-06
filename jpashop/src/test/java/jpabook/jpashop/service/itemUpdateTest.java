package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
public class itemUpdateTest {
    @Autowired
    EntityManager em;

    @Test
    public void itemUpdateTest() throws Exception {
        Book book = em.find(Book.class, 1L);

        book.setName("asdfasdf");
    }
}
