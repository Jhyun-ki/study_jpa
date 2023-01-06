package jpabook.jpashop.service;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.Movie;
import jpabook.jpashop.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {
    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void 아이템_저장() throws Exception {
        //given
        Movie movie = new Movie();
        movie.setName("test");
        movie.setPrice(10000);
        movie.setStockQuantity(100);
        movie.setDirector("hk");
        movie.setActor("hk_actor");

        //when
        itemService.saveItem(movie);
        Movie item = (Movie) itemRepository.findOne(movie.getId());

        //then
        Assertions.assertThat(item).isNotNull();
    }

    @Test
    public void 아이템_수정() throws Exception {
        String movieName = "test";

        //given
        Movie movie = new Movie();
        movie.setName(movieName);
        movie.setPrice(10000);
        movie.setStockQuantity(100);
        movie.setDirector("hk");
        movie.setActor("hk_actor");

        //when
        itemService.saveItem(movie);
        movieName = "test_updated";

        movie.setName(movieName);

        itemService.saveItem(movie);

        Movie item = (Movie) itemService.findOne(movie.getId());

        //then
        Assertions.assertThat(item.getName()).isEqualTo(movieName);
    }

    @Test
    public void 아이템_한개_찾기() throws Exception {
        //given
        Movie movie = new Movie();
        movie.setName("movieName");
        movie.setPrice(10000);
        movie.setStockQuantity(100);
        movie.setDirector("hk");
        movie.setActor("hk_actor");

        //when
        itemService.saveItem(movie);
        Movie findMovie = (Movie) itemService.findOne(movie.getId());

        //then
        Assertions.assertThat(findMovie).isNotNull();
    }

    @Test
    public void 아이템_모두_찾기() throws Exception {
        //given
        for(int i = 0; i < 100; i++) {
            Movie movie = new Movie();
            movie.setName("movieName" + i);
            movie.setPrice(10000);
            movie.setStockQuantity(100);
            movie.setDirector("hk" + i);
            movie.setActor("hk_actor" + i);

            itemService.saveItem(movie);
        }

        //when
        List<Item> movieList =  itemService.findItems();

        //then
        assertThat(movieList).isNotNull();
    }

}